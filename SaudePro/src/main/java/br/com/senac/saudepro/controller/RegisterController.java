package br.com.senac.saudepro.controller;

import br.com.senac.saudepro.gui.Register;
import br.com.senac.saudepro.util.AuxiliaryMethod;
import java.awt.Color;
import java.awt.Cursor;

/**
 * RegisterControler controle de toda a view Register
 * @author bruno-teixeira
 */
public class RegisterController extends BaseViewController {
    
    
    private Register r;
    
    public RegisterController(Register register) {
        super(register);
        
        this.r = register;
        
        start();
    }

    @Override
    protected void loadIcones() {
        super.loadIcones();
        
        
    }

    @Override
    protected void initController() {
        super.initController();
        
        // Selecionar Inicio por padrão
        selecionarBotao(baseView.getAllBtns(2), baseView.getAllIncons(2), icoRegisN, icoRegisH, baseView.getLabelsBtns(2));
        
        AuxiliaryMethod.aplicateHover(r.getAllInputs(1), r.getAllPanels(1));
        AuxiliaryMethod.aplicateHover(r.getAllInputs(2), r.getAllPanels(2));
        AuxiliaryMethod.aplicateHover(r.getAllInputs(3), r.getAllPanels(3));
        AuxiliaryMethod.aplicateHover(r.getAllInputs(4), r.getAllPanels(4));
        AuxiliaryMethod.aplicateHover(r.getAllInputs(5), r.getAllPanels(5));
    
        AuxiliaryMethod.aplicarHover(r.getInpuintDetails(), r.getAllPanels(6));
        
        AuxiliaryMethod.buttonsHover(r.getAllLabels(1), r.getAllPanels(7), new Color(0x458C45), new Color(0x7ED348));
        AuxiliaryMethod.buttonsHover(r.getAllLabels(2), r.getAllPanels(8), new Color(0x3182CE), new Color(0x4299E1));
        AuxiliaryMethod.buttonsHover(r.getAllLabels(3), r.getAllPanels(9), new Color(0xED8936), new Color(0xF6AD55));
    
        // CURSOR DA MAOZINHA
        r.getAllPanels(7).setCursor(new Cursor(Cursor.HAND_CURSOR));
        r.getAllPanels(8).setCursor(new Cursor(Cursor.HAND_CURSOR));
        r.getAllPanels(9).setCursor(new Cursor(Cursor.HAND_CURSOR));
        
    }
    
    
        
}
