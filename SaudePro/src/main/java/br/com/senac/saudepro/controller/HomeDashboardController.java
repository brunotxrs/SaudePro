package br.com.senac.saudepro.controller;

import br.com.senac.saudepro.dao.AgendamentoDAO;
import br.com.senac.saudepro.dao.MedicoDAO;
import br.com.senac.saudepro.gui.HomeDashboard;
import br.com.senac.saudepro.model.Agendamento;
import br.com.senac.saudepro.model.Medico;
import br.com.senac.saudepro.model.Users;
import br.com.senac.saudepro.util.AuxiliaryMethod;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class HomeDashboardController extends BaseViewController {
    
    //APLICAR OS DEMAIS ICONES
    private final String _PARTH_IMG_ADD_HOVER = "src/main/java/resources/img/addIco.png";
    
    private ImageIcon icoAddN;
    private ImageIcon icoAddH;
    
    private HomeDashboard dashboard;
    private Users usuarioLogado = new Users();
    
    private AgendamentoDAO agendamentoDAO;
    private MedicoDAO medicoDAO;
    private List<Agendamento> agendamentosHoje;
    
    private final Color SELECTED_COLOR = new Color(0x458C45);
    private final Color HOVER_COLOR = new Color(0x7ED348);
    
    public HomeDashboardController(HomeDashboard hd, Users u) {
        super(hd);
        
        this.dashboard = hd;
        this.usuarioLogado = u;
        this.agendamentoDAO = new AgendamentoDAO();
        this.medicoDAO = new MedicoDAO();
        
        start();
    }
    
    @Override
    protected void loadIcones() {
        super.loadIcones();
        
        icoAddN = AuxiliaryMethod.loadedIcone(dashboard.getAllParthIconsDash(1), 30, 30);
        icoAddH = AuxiliaryMethod.loadedIcone(_PARTH_IMG_ADD_HOVER, 30, 30);
    }

    
    @Override
    protected void initController() {
        super.initController(); 
        
        dashboard.getBtnNewFitting().setCursor(new Cursor(Cursor.HAND_CURSOR));
        // Novo Encaixe
        dashboard.getAllInconsDash(1).setLayout(new GridBagLayout());
        AuxiliaryMethod.aplcateHoverInBtns(dashboard.getBtnNewFitting(), dashboard.getAllInconsDash(1), icoAddN, icoAddH, HOVER_COLOR, Color.WHITE, SELECTED_COLOR);
        
        // Selecionar Inicio por padrão
        selecionarBotao(baseView.getAllBtns(1), baseView.getAllIncons(1), icoInitN, icoInitH, baseView.getLabelsBtns(1));
        
        // btn novo encaixe 
        AuxiliaryMethod.configurarAcoes(dashboard.getBtnNewFitting(), dashboard);
        
        // ===== CARREGAR DADOS DO DASHBOARD =====
        carregarCardsInformativos();
        carregarTabelaAgendamentosHoje();
        carregarProximosAtendimentos();
    }
    
    private void carregarCardsInformativos() {
        try {
            LocalDate hoje = LocalDate.now();
            agendamentosHoje = agendamentoDAO.buscarPorData(hoje);
            if (agendamentosHoje == null) agendamentosHoje = new java.util.ArrayList<>();
            
            // Pacientes do Dia
            if (dashboard.getNumPeoleDay() != null) {
                dashboard.getNumPeoleDay().setText(String.valueOf(agendamentosHoje.size()));
            }
            
            // Consultas em Aberto
            long consultasAberto = agendamentosHoje.stream()
                    .filter(a -> a.getStatus().equals("AGENDADO") || a.getStatus().equals("CONFIRMADO"))
                    .count();
            if (dashboard.getNumConsul() != null) {
                dashboard.getNumConsul().setText(String.valueOf(consultasAberto));
            }
            
            // Aguardando Atendimento
            long aguardando = agendamentosHoje.stream()
                    .filter(a -> a.getStatus().equals("AGENDADO"))
                    .count();
            if (dashboard.getNumAwit() != null) {
                dashboard.getNumAwit().setText(String.valueOf(aguardando));
            }
            
            // Médicos de Plantão
            List<Medico> medicos = medicoDAO.getAllMedicos();
            if (dashboard.getNumMedics() != null) {
                dashboard.getNumMedics().setText(String.valueOf(medicos.size()));
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar cards: " + e.getMessage());
        }
    }
    
    private void carregarTabelaAgendamentosHoje() {
        try {
            LocalDate hoje = LocalDate.now();
            agendamentosHoje = agendamentoDAO.buscarPorData(hoje);
            if (agendamentosHoje == null) agendamentosHoje = new java.util.ArrayList<>();
            agendamentosHoje.sort((a1, a2) -> a1.getHorario().compareTo(a2.getHorario()));
            
            DefaultTableModel model = (DefaultTableModel) dashboard.getScheduleTable().getModel();
            model.setRowCount(0);
            
            for (Agendamento a : agendamentosHoje) {
                String status = a.getStatus().equals("CONFIRMADO") ? "Confirmado" : "Confirmar?";
                model.addRow(new Object[]{a.getHorario(), a.getPaciente().getNome(), a.getMedico().getNome(), status});
            }
            
            // Remover listeners antigos para não acumular
            for (MouseListener ml : dashboard.getScheduleTable().getMouseListeners()) {
                dashboard.getScheduleTable().removeMouseListener(ml);
            }
            
            // Adicionar evento de clique na tabela para confirmar chegada
            dashboard.getScheduleTable().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int row = dashboard.getScheduleTable().rowAtPoint(e.getPoint());
                    int col = dashboard.getScheduleTable().columnAtPoint(e.getPoint());
                    
                    // Verificar se clicou na coluna "Confirmar Chegada" (índice 3)
                    if (col == 3 && row >= 0 && row < agendamentosHoje.size()) {
                        String status = dashboard.getScheduleTable().getValueAt(row, col).toString();
                        if (status.equals("Confirmar?")) {
                            boolean confirm = AuxiliaryMethod.mostrarConfirmacaoFlutuante(dashboard, "Confirmar chegada do paciente?", 350, 150);
                            if (confirm) {
                                Agendamento a = agendamentosHoje.get(row);
                                agendamentoDAO.atualizarStatus(a.getId(), "CONFIRMADO");
                                a.setStatus("CONFIRMADO");
                                
                                // Atualizar apenas a célula clicada
                                dashboard.getScheduleTable().setValueAt("Confirmado", row, col);
                                dashboard.getScheduleTable().repaint();
                                
                                // Atualizar cards
                                carregarCardsInformativos();
                                carregarProximosAtendimentos();
                                
                                AuxiliaryMethod.mostrarMensagemFlutuante(dashboard, "Chegada confirmada!", 300, 80);
                            }
                        }
                    }
                }
            });
            
        } catch (Exception e) {
            System.err.println("Erro ao carregar tabela: " + e.getMessage());
        }
    }
    
    private void carregarProximosAtendimentos() {
        try {
            LocalTime agora = LocalTime.now();
            
            // Filtrar agendamentos do dia que ainda não passaram
            List<Agendamento> proximos = agendamentosHoje.stream()
                    .filter(a -> {
                        LocalTime horario = LocalTime.parse(a.getHorario());
                        return horario.isAfter(agora) || horario.equals(agora);
                    })
                    .sorted((a1, a2) -> a1.getHorario().compareTo(a2.getHorario()))
                    .collect(Collectors.toList());
            
            // Card 1 (primeiro próximo atendimento)
            JLabel people1 = dashboard.getPeopleCard(1);
            JLabel prof1 = dashboard.getProfessionalCard(1);
            JPanel cardPanel1 = dashboard.getCardPanel(1);
            
            if (people1 != null && prof1 != null && cardPanel1 != null) {
                if (proximos.size() >= 1) {
                    Agendamento a = proximos.get(0);
                    people1.setText(a.getHorario() + " - " + a.getPaciente().getNome());
                    prof1.setText(a.getMedico().getNome());
                    cardPanel1.setVisible(true);
                } else {
                    cardPanel1.setVisible(false);
                }
            }
            
            // Card 2 (segundo próximo atendimento)
            JLabel people2 = dashboard.getPeopleCard(2);
            JLabel prof2 = dashboard.getProfessionalCard(2);
            JPanel cardPanel2 = dashboard.getCardPanel(2);
            
            if (people2 != null && prof2 != null && cardPanel2 != null) {
                if (proximos.size() >= 2) {
                    Agendamento a = proximos.get(1);
                    people2.setText(a.getHorario() + " - " + a.getPaciente().getNome());
                    prof2.setText(a.getMedico().getNome());
                    cardPanel2.setVisible(true);
                } else {
                    cardPanel2.setVisible(false);
                }
            }
            
            // Atualizar visual dos cards
            dashboard.repaint();
            
        } catch (Exception e) {
            System.err.println("Erro ao carregar próximos atendimentos: " + e.getMessage());
        }
    }
}