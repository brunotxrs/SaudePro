package br.com.senac.saudepro.controller;

import br.com.senac.saudepro.dao.MedicoDAO;
import br.com.senac.saudepro.dao.PacienteDAO;
import br.com.senac.saudepro.gui.Scheduling;
import br.com.senac.saudepro.model.Medico;
import br.com.senac.saudepro.model.Paciente;
import br.com.senac.saudepro.util.AuxiliaryMethod;
import br.com.senac.saudepro.util.ShadowPanel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;

public class SchedulingController extends BaseViewController {
    
    private Medico medicoSelecionado = null;
    private Paciente pacienteSelecionado = null;
    
    private Scheduling view;
    private MedicoDAO medicoDAO;
    private List<Medico> medicosList;
    
    private int cardSelecionado = -1;
    private final Color COR_PADRAO = Color.WHITE;
    private final Color COR_HOVER = new Color(0xb4e892);
    private final Color COR_SELECIONADO = new Color(0x7ED348);
    
    public SchedulingController(Scheduling view) {
        super(view);
        this.view = view;
        this.medicoDAO = new MedicoDAO();
        start();
    }
    
    @Override
    protected void initController() {
        super.initController();
        
        // Configurar busca de paciente UMA ÚNICA VEZ
        baseView.getInputSearch().addActionListener(d -> searchPaciente());
        
        configurarBotoes();
        
        // Selecionar Inicio por padrão
        selecionarBotao(baseView.getAllBtns(3), baseView.getAllIncons(3), icoSchedN, icoSchedH, baseView.getLabelsBtns(3));
        
        // Carregar médicos do banco
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
            
            // Verificar se é CPF (apenas números e 11 dígitos) ou Nome
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
            
            // Atualizar campos na tela
            view.getShowLabels(4).setText(pacienteSelecionado.getNome());
            view.getShowLabels(6).setText(formatarCPF(pacienteSelecionado.getCpf()));
            
            System.out.println("PACIENTE SELECIONADO: " + pacienteSelecionado.getNome());
            
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
                    // Limpar seleção anterior
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
        // Botão principal (Novo Agendamento / Salvar / Deletar)
        view.getAllPanels(7).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String textoAtual = view.getAllLabels(1).getText().trim();
                
                if (textoAtual.equals("Novo Agendamento")) {
                    abrirNovoAgendamento();
                } else if (textoAtual.equals("Salvar Agendamento")) {
                    salvarAgendamento();
                } else if (textoAtual.equals("Deletar Agendamento")) {
                    deletarAgendamento();
                }
            }
        });
        
        // Botão secundário (Lista de Agendados / Cancelar / Voltar)
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
    
    // Metodo para abrir novo agendamento
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
        
        // Limpar paciente selecionado anterior
        pacienteSelecionado = null;
        baseView.getInputSearch().setText("");
        
        showAgendamento();
    }
    
    // Metodo para salvar agendamento
    private void salvarAgendamento() {
        if (medicoSelecionado == null) {
            AuxiliaryMethod.mostrarMensagemFlutuante(view, "Selecione um médico!", 300, 80);
            return;
        }
        
        if (pacienteSelecionado == null) {
            AuxiliaryMethod.mostrarMensagemFlutuante(view, "Busque e selecione um paciente!", 300, 80);
            return;
        }
        
        AuxiliaryMethod.mostrarMensagemFlutuante(view, "Agendamento salvo com sucesso!", 300, 80);
        
        // TODO: Salvar no banco
        // new AgendamentoDAO().salvar(medicoSelecionado, pacienteSelecionado, data, hora);
        
        reloadPage();
    }
    
    // Metodo para deletelar agendamento 
    private void deletarAgendamento() {
        AuxiliaryMethod.mostrarMensagemFlutuante(view, "Deletando agendamento...", 300, 80);
        // TODO: Implementar delete
        reloadPage();
    }
    
    // metodo para abrir lista de agendados
    private void abrirListaAgendados() {
        boxDoctor();
        view.getAllLabels(1).setText("Deletar Agendamento");
        view.getAllPanels(7).setBackground(new Color(0xE11D48));
        view.getAllLabels(2).setText("Voltar");
        view.getAllLabels(4).setText("Selecione um horário para visualizar o agendamento");
        AuxiliaryMethod.mostrarMensagemFlutuante(view, "Mostrando lista de agendados...", 300, 80);
    }
    
    
    // tornando os btns em novos estados
    private void novoEstadoBotoes() {
        view.getAllPanels(7).setEnabled(true);
        view.getAllPanels(8).setEnabled(true);
        view.getAllPanels(7).setBackground(new Color(0x7ED348));
        view.getAllPanels(8).setBackground(new Color(0x4299E1));
        view.getAllPanels(7).setCursor(new Cursor(Cursor.HAND_CURSOR));
        view.getAllPanels(8).setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    // metodo para reload na page
    private void reloadPage() {
        Scheduling s = new Scheduling();
        new SchedulingController(s);
        s.setVisible(true);
        view.dispose();
    }
    
    // Metodo para exibiçao do Medico seleciondo e seus Horarios
    private void boxDoctor() {
        view.getContainerDoctors().setBackground(new Color(0xF5F5F5));
        view.getContainerDoctors().setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0xE0E0E0), 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        view.getLabelDoctors(1).setText(medicoSelecionado.getNome());
        view.getLabelDoctors(2).setText(medicoSelecionado.getEspecialidade());
        
        view.getHorariosPanel().setVisible(true); // tornando visivel o campo dos horarios
    }
    
    private void showAgendamento() {
        // Mostrar todos os campos
        for (int i = 1; i <= 10; i++) {
            view.getShowLabels(i).setVisible(true);
        }
        
        // Preencher com dados
        view.getShowLabels(2).setText(medicoSelecionado != null ? medicoSelecionado.getNome() : "---");
        view.getShowLabels(4).setText(pacienteSelecionado != null ? pacienteSelecionado.getNome() : "Busque um paciente acima");
        view.getShowLabels(6).setText(pacienteSelecionado != null ? formatarCPF(pacienteSelecionado.getCpf()) : "---");
        view.getShowLabels(8).setText("__/__/____");
        view.getShowLabels(10).setText("__:__");
    }
}