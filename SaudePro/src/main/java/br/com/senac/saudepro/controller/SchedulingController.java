package br.com.senac.saudepro.controller;

import br.com.senac.saudepro.dao.MedicoDAO;
import br.com.senac.saudepro.gui.Scheduling;
import br.com.senac.saudepro.model.Medico;
import br.com.senac.saudepro.util.AuxiliaryMethod;
import br.com.senac.saudepro.util.ShadowPanel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;

public class SchedulingController extends BaseViewController {
    
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
 
        configurarBotoes();
        
        // Selecionar Inicio por padrão
        selecionarBotao(baseView.getAllBtns(3), baseView.getAllIncons(3), icoSchedN, icoSchedH, baseView.getLabelsBtns(3));
        
        // Carregar médicos do banco
        carregarMedicos();
        
    
    }
    
    private void carregarMedicos() {
        // Buscar todos os médicos do banco
        medicosList = medicoDAO.getAllMedicos();
        
        // Criar cards dinamicamente
        view.criarCardsMedicos(medicosList);
        
        // Adicionar eventos de clique nos cards
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

                    // Selecionar novo card
                    cardSelecionado = index;
                    cards.get(index).setBackground(COR_SELECIONADO);
                    novoEstadoBotoes();
                    
                    Medico medicoSelecionado = medicosList.get(index);
                    view.getContainerDoctors().setBackground(new Color(0xF5F5F5));
                    view.getContainerDoctors().setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(0xE0E0E0), 1),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)
                    ));
                    //Atribuindo ao elemento o medico e sua especializaçao 
                    view.getLabelDoctors(1).setText(medicoSelecionado.getNome());
                    view.getLabelDoctors(2).setText(medicoSelecionado.getEspecialidade());
    
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
        // Botão Novo Agendamento
        view.getAllPanels(7).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AuxiliaryMethod.mostrarMensagemFlutuante(view, "Abrindo novo agendamento...", 300, 80);
            }
        });
        
        // Botão Lista de Agendados
        view.getAllPanels(8).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AuxiliaryMethod.mostrarMensagemFlutuante(view, "Mostrando lista de agendados...", 300, 80);
            }
        });
    }
    
    
    private void novoEstadoBotoes(){
        view.getAllPanels(7).setEnabled(true);
        view.getAllPanels(8).setEnabled(true);
        view.getAllPanels(7).setBackground(new Color(0x7ED348));
        view.getAllPanels(8).setBackground(new Color(0x4299E1));
        view.getAllPanels(7).setCursor(new Cursor(Cursor.HAND_CURSOR));
        view.getAllPanels(8).setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        //configurarBotoes();
    }
    
    // Método para desselecionar
    private void limparSelecao() {
        List<ShadowPanel> cards = view.getCardsMedicos();
        if (cardSelecionado != -1) {
            cards.get(cardSelecionado).setBackground(COR_PADRAO);
            cardSelecionado = -1;
        }
    }
}




