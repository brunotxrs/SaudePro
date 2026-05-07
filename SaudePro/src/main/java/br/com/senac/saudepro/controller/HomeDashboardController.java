package br.com.senac.saudepro.controller;

import br.com.senac.saudepro.gui.HomeDashboard;
import br.com.senac.saudepro.util.AuxiliaryMethod;
import br.com.senac.saudepro.util.IconTextField;
import br.com.senac.saudepro.util.RoundedPanel;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class HomeDashboardController extends BaseViewController {
    
    //APLICAR OS DEMAIS ICONES
    private final String _PARTH_IMG_SEARCH_HOVER = "src/main/java/resources/img/searchHover.png";
    private final String _PARTH_IMG_ADD_HOVER = "src/main/java/resources/img/addIco.png";
    
    
    private ImageIcon iconSearchN;
    private ImageIcon iconSearchH;
    
    private ImageIcon icoAddN;
    private ImageIcon icoAddH;
    
    
    private HomeDashboard dashboard;
    
    // Controle de qual botão está selecionado
    private RoundedPanel selectedPanel = null;
    private JLabel selectedLabel = null;
    
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
        
        iconSearchN = AuxiliaryMethod.loadedIcone(dashboard.getAllParthIconsDash(2), 30, 30);
        iconSearchH = AuxiliaryMethod.loadedIcone(_PARTH_IMG_SEARCH_HOVER, 30, 30);
        
    }

    @Override
    protected void initController() {
        super.initController(); 
        
        AuxiliaryMethod.setPlaceholder(dashboard.getInputSearch(), "Buscar paciente por nome ou CPF...");
        aplicationHover(dashboard.getInputSearch(), dashboard.getAllInconsDash(2), iconSearchN, iconSearchH, dashboard.getPlaceSearch());
        
        
        // Novo Encaixe
        dashboard.getAllInconsDash(1).setLayout(new GridBagLayout());
        AuxiliaryMethod.aplcateHoverInBtns(dashboard.getBtnNewFitting(), dashboard.getAllInconsDash(1), icoAddN, icoAddH, HOVER_COLOR, Color.WHITE, SELECTED_COLOR);
    }
    
     
    private void aplicationHover(JTextField campo, IconTextField iconField, ImageIcon normal, ImageIcon hover, RoundedPanel panel) {
        if (campo == null || iconField == null) return;
        
        campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                panel.setRoundedBorder(HOVER_COLOR, 2);
                iconField.setIcon(hover);
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                panel.setRoundedBorder(null, 1);
                iconField.setIcon(normal);
            }
        });
    }
}