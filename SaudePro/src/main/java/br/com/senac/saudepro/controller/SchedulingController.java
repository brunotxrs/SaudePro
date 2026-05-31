package br.com.senac.saudepro.controller;

import br.com.senac.saudepro.dao.AgendamentoDAO;
import br.com.senac.saudepro.dao.MedicoDAO;
import br.com.senac.saudepro.dao.PacienteDAO;
import br.com.senac.saudepro.gui.Scheduling;
import br.com.senac.saudepro.model.Agendamento;
import br.com.senac.saudepro.model.Medico;
import br.com.senac.saudepro.model.Paciente;
import br.com.senac.saudepro.util.AuxiliaryMethod;
import br.com.senac.saudepro.util.RoundedPanel;
import br.com.senac.saudepro.util.ShadowPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class SchedulingController extends BaseViewController {
    
    private Medico medicoSelecionado = null;
    private Paciente pacienteSelecionado = null;
    private Agendamento agendamentoSelecionado = null;
    
    private Scheduling view;
    private MedicoDAO medicoDAO;
    private AgendamentoDAO agendamentoDAO;
    private List<Medico> medicosList;
    private List<Agendamento> agendamentosList;
    
    private int cardSelecionado = -1;
    private final Color COR_PADRAO = Color.WHITE;
    private final Color COR_HOVER = new Color(0xb4e892);
    private final Color COR_SELECIONADO = new Color(0x7ED348);
    
    public SchedulingController(Scheduling view) {
        super(view);
        this.view = view;
        this.medicoDAO = new MedicoDAO();
        this.agendamentoDAO = new AgendamentoDAO();
        start();
    }
    
    @Override
    protected void initController() {
        super.initController();
        
        baseView.getInputSearch().addActionListener(d -> searchPaciente());
        configurarBotoes();
        
        selecionarBotao(baseView.getAllBtns(3), baseView.getAllIncons(3), icoSchedN, icoSchedH, baseView.getLabelsBtns(3));
        
        carregarMedicos();
    }
    
    private void searchPaciente() {
        try {
            String filter = baseView.getInputSearch().getText().trim();
            
            if (filter.contains("Buscar") || filter.isEmpty()) {
                filter = "";
            }
            
            String filtroLimpo = filter.replaceAll("[^a-zA-Z0-9]", "");
            PacienteDAO pdao = new PacienteDAO();
            Paciente paciente = null;
            
            if (filtroLimpo.matches("\\d+") && filtroLimpo.length() == 11) {
                paciente = pdao.getPacienteByCPF(filtroLimpo);
            } else {
                paciente = pdao.getPacienteByNane(filter);
            }
            
            if (paciente == null) {
                AuxiliaryMethod.mostrarMensagemFlutuante(view, "Paciente não cadastrado!", 300, 80);
                pacienteSelecionado = null;
                view.getShowLabels(4).setText("Paciente não encontrado");
                return;
            }
            
            pacienteSelecionado = paciente;
            
            view.getShowLabels(4).setText(pacienteSelecionado.getNome());
            view.getShowLabels(6).setText(formatarCPF(pacienteSelecionado.getCpf()));
            
        } catch (HeadlessException e) {
            AuxiliaryMethod.mostrarMensagemFlutuante(view, "Erro ao buscar paciente: " + e.getMessage(), 300, 80);
        }
    }
    
    private String formatarCPF(String cpf) {
        if (cpf == null || cpf.length() != 11) return cpf;
        return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
    }
    
    private void carregarMedicos() {
        medicosList = medicoDAO.getAllMedicos();
        view.criarCardsMedicos(medicosList);
        adicionarEventosCards();
    }
    
    private void adicionarEventosCards() {
        List<ShadowPanel> cards = view.getCardsMedicos();

        for (int i = 0; i < cards.size(); i++) {
            final int index = i;

            cards.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (cardSelecionado != -1 && cardSelecionado != index) {
                        cards.get(cardSelecionado).setBackground(COR_PADRAO);
                    }

                    cardSelecionado = index;
                    cards.get(index).setBackground(COR_SELECIONADO);
                    novoEstadoBotoes();
                    
                    view.getAllLabels(1).setText("Novo Agendamento");
                    view.getAllLabels(2).setText("Lista de Agendados");
                    medicoSelecionado = medicosList.get(index);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (cardSelecionado != index) {
                        cards.get(index).setBackground(COR_HOVER);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (cardSelecionado != index) {
                        cards.get(index).setBackground(COR_PADRAO);
                    }
                }
            });

            cards.get(i).setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }
    
    private void configurarBotoes() {
        view.getAllPanels(7).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String textoAtual = view.getAllLabels(1).getText().trim();
                
                if (textoAtual.equals("Novo Agendamento")) {
                    abrirNovoAgendamento();
                } else if (textoAtual.equals("Salvar Agendamento")) {
                    salvarAgendamento();
                } else if (textoAtual.equals("Deletar Agendamento")) {
                    confirmarDeletarAgendamento();
                }
            }
        });
        
        view.getAllPanels(8).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String textoAtual = view.getAllLabels(2).getText().trim();
                
                if (textoAtual.equals("Lista de Agendados")) {
                    abrirListaAgendados();
                } else if (textoAtual.equals("Cancelar") || textoAtual.equals("Voltar")) {
                    reloadPage();
                }
            }
        });
    }
    
    private void abrirNovoAgendamento() {
        if (medicoSelecionado == null) {
            AuxiliaryMethod.mostrarMensagemFlutuante(view, "Selecione um médico primeiro!", 300, 80);
            return;
        }
        
        view.getAllLabels(1).setText("Salvar Agendamento");
        view.getAllLabels(2).setText("Cancelar");
        view.getAllPanels(8).setBackground(new Color(0xF6AD55));
        
        boxDoctor();
        
        view.getAllLabels(3).setVisible(false);
        view.getAllLabels(4).setVisible(false);
        
        pacienteSelecionado = null;
        baseView.getInputSearch().setText("");
        
        showAgendamento();
    }
    
    private void salvarAgendamento() {
        if (medicoSelecionado == null) {
            AuxiliaryMethod.mostrarMensagemFlutuante(view, "Selecione um médico!", 300, 80);
            return;
        }
        
        if (pacienteSelecionado == null) {
            AuxiliaryMethod.mostrarMensagemFlutuante(view, "Busque e selecione um paciente!", 300, 80);
            return;
        }
        
        String dataTexto = view.getShowLabels(8).getText();
        String horario = view.getShowLabels(10).getText();
        
        if (dataTexto == null || dataTexto.equals("__/__/____")) {
            AuxiliaryMethod.mostrarMensagemFlutuante(view, "Selecione uma data no calendário!", 300, 80);
            return;
        }
        
        if (horario == null || horario.equals("__:__")) {
            AuxiliaryMethod.mostrarMensagemFlutuante(view, "Selecione um horário!", 300, 80);
            return;
        }
        
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate data = LocalDate.parse(dataTexto, formatter);
            
            Agendamento agendamento = new Agendamento();
            agendamento.setMedico(medicoSelecionado);
            agendamento.setPaciente(pacienteSelecionado);
            agendamento.setDataAgendamento(data);
            agendamento.setHorario(horario);
            agendamento.setStatus("AGENDADO");
            
            agendamentoDAO.salvar(agendamento);
            
            AuxiliaryMethod.mostrarMensagemFlutuante(view, "Agendamento salvo com sucesso!", 300, 80);
            
            reloadPage();
            
        } catch (Exception e) {
            AuxiliaryMethod.mostrarMensagemFlutuante(view, "Erro ao salvar: " + e.getMessage(), 300, 80);
        }
    }
    
    private void abrirListaAgendados() {
        if (medicoSelecionado == null) {
            AuxiliaryMethod.mostrarMensagemFlutuante(view, "Selecione um médico primeiro!", 300, 80);
            return;
        }
        
        agendamentosList = agendamentoDAO.buscarPorMedico(medicoSelecionado);
        
        if (agendamentosList.isEmpty()) {
            AuxiliaryMethod.mostrarMensagemFlutuante(view, "Nenhum agendamento encontrado para este médico!", 300, 80);
            return;
        }
        
        boxDoctor();
        view.getAllLabels(1).setText("Deletar Agendamento");
        view.getAllPanels(7).setBackground(new Color(0xE11D48));
        view.getAllLabels(2).setText("Voltar");
        view.getAllLabels(3).setVisible(false);
        view.getAllLabels(4).setVisible(false);
        
        mostrarListaAgendamentos();
    }
    
    private void mostrarListaAgendamentos() {
        JPanel listaPanel = new JPanel();
        listaPanel.setLayout(new BoxLayout(listaPanel, BoxLayout.Y_AXIS));
        listaPanel.setBackground(Color.WHITE);
        listaPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        for (Agendamento a : agendamentosList) {
            RoundedPanel card = new RoundedPanel(10);
            card.setBackground(new Color(0xF0F8FF));
            card.setLayout(new BorderLayout());
            card.setCursor(new Cursor(Cursor.HAND_CURSOR));
            card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            
            JLabel info = new JLabel("<html><b>" + a.getPaciente().getNome() + "</b><br>" +
                    a.getDataAgendamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " às " + a.getHorario() + "<br>" +
                    "Status: " + a.getStatus() + "</html>");
            info.setFont(new Font("Arial", Font.PLAIN, 12));
            
            card.add(info, BorderLayout.CENTER);
            
            card.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    agendamentoSelecionado = a;
                    mostrarDetalhesAgendamento();
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    card.setBackground(new Color(0xE8F5E9));
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    card.setBackground(new Color(0xF0F8FF));
                }
            });
            
            listaPanel.add(card);
            listaPanel.add(Box.createVerticalStrut(5));
        }
        
        JScrollPane scrollPane = new JScrollPane(listaPanel);
        scrollPane.setBorder(null);
        
        view.getHorariosPanel().removeAll();
        view.getHorariosPanel().setLayout(new BorderLayout());
        view.getHorariosPanel().add(scrollPane, BorderLayout.CENTER);
        view.getHorariosPanel().setVisible(true);
    }
    
    private void mostrarDetalhesAgendamento() {
        if (agendamentoSelecionado == null) return;
        
        view.getAllLabels(1).setText("Deletar Agendamento");
        view.getAllPanels(7).setBackground(new Color(0xE11D48));
        
        for (int i = 1; i <= 10; i++) {
            view.getShowLabels(i).setVisible(true);
        }
        
        view.getShowLabels(2).setText(agendamentoSelecionado.getMedico().getNome());
        view.getShowLabels(4).setText(agendamentoSelecionado.getPaciente().getNome());
        view.getShowLabels(6).setText(formatarCPF(agendamentoSelecionado.getPaciente().getCpf()));
        view.getShowLabels(8).setText(agendamentoSelecionado.getDataAgendamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        view.getShowLabels(10).setText(agendamentoSelecionado.getHorario());
        
        view.getHorariosPanel().setVisible(false);
        
        AuxiliaryMethod.mostrarMensagemFlutuante(view, "Clique em 'Deletar Agendamento' para remover", 300, 80);
    }
    
    private void confirmarDeletarAgendamento() {
        if (agendamentoSelecionado == null) {
            AuxiliaryMethod.mostrarMensagemFlutuante(view, "Selecione um agendamento na lista!", 300, 80);
            return;
        }
        
        boolean confirm = AuxiliaryMethod.mostrarConfirmacaoFlutuante(
            view,
            "Deseja realmente deletar o agendamento de " + agendamentoSelecionado.getPaciente().getNome() + "?",
            350, 150
        );
        
        if (confirm) {
            agendamentoDAO.deletar(agendamentoSelecionado.getId());
            AuxiliaryMethod.mostrarMensagemFlutuante(view, "Agendamento deletado com sucesso!", 300, 80);
            reloadPage();
        }
    }
    
    private void deletarAgendamento() {
        confirmarDeletarAgendamento();
    }
    
    private void novoEstadoBotoes() {
        view.getAllPanels(7).setEnabled(true);
        view.getAllPanels(8).setEnabled(true);
        view.getAllPanels(7).setBackground(new Color(0x7ED348));
        view.getAllPanels(8).setBackground(new Color(0x4299E1));
        view.getAllPanels(7).setCursor(new Cursor(Cursor.HAND_CURSOR));
        view.getAllPanels(8).setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    private void reloadPage() {
        Scheduling s = new Scheduling();
        new SchedulingController(s);
        s.setVisible(true);
        view.dispose();
    }
    
    private void boxDoctor() {
        view.getContainerDoctors().setBackground(new Color(0xF5F5F5));
        view.getContainerDoctors().setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0xE0E0E0), 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        view.getLabelDoctors(1).setText(medicoSelecionado.getNome());
        view.getLabelDoctors(2).setText(medicoSelecionado.getEspecialidade());
        
        view.getHorariosPanel().setVisible(true);
    }
    
    private void showAgendamento() {
        for (int i = 1; i <= 10; i++) {
            view.getShowLabels(i).setVisible(true);
        }
        
        view.getShowLabels(2).setText(medicoSelecionado != null ? medicoSelecionado.getNome() : "---");
        view.getShowLabels(4).setText(pacienteSelecionado != null ? pacienteSelecionado.getNome() : "Busque um paciente acima");
        view.getShowLabels(6).setText(pacienteSelecionado != null ? formatarCPF(pacienteSelecionado.getCpf()) : "---");
        view.getShowLabels(8).setText("__/__/____");
        view.getShowLabels(10).setText("__:__");
    }
}