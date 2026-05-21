package br.com.senac.saudepro.controller;

import br.com.senac.saudepro.gui.Scheduling;
import br.com.senac.saudepro.util.AuxiliaryMethod;
import java.awt.Cursor;

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
        view.getAllBoxDoctors(1).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        view.getAllBoxDoctors(2).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        view.getAllBoxDoctors(3).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        view.getAllBoxDoctors(4).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        // Hover nos cards dos médicos (se quiser)
        AuxiliaryMethod.aplicarHoverLabel(view.getAllLabesDoctors(1), view.getAllBoxDoctors(1));
        AuxiliaryMethod.aplicarHoverLabel(view.getAllLabesDoctors(2), view.getAllBoxDoctors(2));
        AuxiliaryMethod.aplicarHoverLabel(view.getAllLabesDoctors(3), view.getAllBoxDoctors(3));
        AuxiliaryMethod.aplicarHoverLabel(view.getAllLabesDoctors(4), view.getAllBoxDoctors(4));
        
        // Ações dos botões
        AuxiliaryMethod.configurarAcoes(view.getAllPanels(7), view);
        AuxiliaryMethod.configurarAcoes(view.getAllPanels(8), view);
    }
    
}