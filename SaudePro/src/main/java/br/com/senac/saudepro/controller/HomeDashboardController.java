package br.com.senac.saudepro.controller;

import br.com.senac.saudepro.gui.HomeDashboard;
import br.com.senac.saudepro.util.AuxiliaryMethod;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;

public class HomeDashboardController extends BaseViewController {
    
    //APLICAR OS DEMAIS ICONES
    private final String _PARTH_IMG_ADD_HOVER = "src/main/java/resources/img/addIco.png";
    
    private ImageIcon icoAddN;
    private ImageIcon icoAddH;
    
    private HomeDashboard dashboard;
    
    private final Color SELECTED_COLOR = new Color(0x458C45);
    private final Color HOVER_COLOR = new Color(0x7ED348);
    
    public HomeDashboardController(HomeDashboard hd) {
        super(hd);
        
        this.dashboard = hd;
        
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
        
    }
    
    
    
}