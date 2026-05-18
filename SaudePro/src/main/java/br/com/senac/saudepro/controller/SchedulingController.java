package br.com.senac.saudepro.controller;

import br.com.senac.saudepro.gui.Scheduling;
import br.com.senac.saudepro.util.AuxiliaryMethod;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SchedulingController extends BaseViewController {
    
    private Scheduling view;
    
    public SchedulingController(Scheduling view) {
        super(view);
        this.view = view;
        start();
    }
    
    @Override
    protected void loadIcones() {
        super.loadIcones();
        // Carregar ícones específicos do Agendamento (se houver)
    }
    
    @Override
    protected void initController() {
        super.initController();
        
        // Selecionar botão "Agendamento" por padrão
        selecionarBotao(
            baseView.getAllBtns(3), 
            baseView.getAllIncons(3), 
            icoSchedN, 
            icoSchedH, 
            baseView.getLabelsBtns(3)
        );
        
        // Cursores dos botões
        view.getAllPanels(7).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        view.getAllPanels(8).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        // Hover nos cards dos médicos (se quiser)
        // configurarHoverMedico(view.getPanelMedico1(), ...);
        
        // Ações dos botões
        configurarAcoes();
    }
    
    private void configurarAcoes() {
        // Botão "Novo Agendamento"
        view.getAllPanels(7).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AuxiliaryMethod.mostrarMensagemFlutuante(
                    view, 
                    "Funcionalidade em desenvolvimento", 
                    300, 
                    80
                );
            }
        });
        
        // Botão "Lista de Agendados"
        view.getAllPanels(8).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AuxiliaryMethod.mostrarMensagemFlutuante(
                    view, 
                    "Funcionalidade em desenvolvimento", 
                    300, 
                    80
                );
            }
        });
    }
    
    
    
}