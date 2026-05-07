package br.com.senac.saudepro.controller;

import br.com.senac.saudepro.gui.HomeDashboard;
import br.com.senac.saudepro.util.AuxiliaryMethod;
import br.com.senac.saudepro.util.IconTextField;
import br.com.senac.saudepro.util.RoundedPanel;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class HomeDashboardController {
    
    //APLICAR OS DEMAIS ICONES
    private final String _PARTH_IMG_SEARCH_HOVER = "src/main/java/resources/img/searchHover.png";
    private final String _PARTH_IMG_INITIAL_NORMAL = "src/main/java/resources/img/icoInitialNormal.png";
    private final String _PARTH_IMG_REGISTER_HOVER = "src/main/java/resources/img/icoRegisterHover.png";
    private final String _PARTH_IMG_SCHEDULING_HOVER = "src/main/java/resources/img/calendarHover.png";
    private final String _PARTH_IMG_CLOSE_HOVER = "src/main/java/resources/img/closeHover.png";
    private final String _PARTH_IMG_HELP_HOVER = "src/main/java/resources/img/helpHover.png";
    private final String _PARTH_IMG_ADD_HOVER = "src/main/java/resources/img/addIco.png";
    
    // ELEMENTOS PARA ARMAZENAMENTOS DOS ELEMENTOS SELECIONADOS
    private IconTextField selectedIconField = null;
    private ImageIcon selectedImgNormal = null;
    private ImageIcon selectedImgHover = null;
    
    
    private ImageIcon iconSearchN;
    private ImageIcon iconSearchH;
    private ImageIcon icoInitN;
    private ImageIcon icoInitH;
    private ImageIcon icoRegisN;
    private ImageIcon icoRegisH;
    private ImageIcon icoSchedN;
    private ImageIcon icoSchedH;
    private ImageIcon icoClosN;
    private ImageIcon icoClosH;
    private ImageIcon icoHelpN;
    private ImageIcon icoHelpH;
    private ImageIcon icoAddN;
    private ImageIcon icoAddH;
    
    private HomeDashboard dashboard;
    
    // Controle de qual botão está selecionado
    private RoundedPanel selectedPanel = null;
    private JLabel selectedLabel = null;
    
    private final Color SELECTED_COLOR = new Color(0x458C45);
    private final Color HOVER_COLOR = new Color(0x7ED348);
    private final Color CLOSE_COLOR = Color.RED;
    private final Color NORMAL_BG = Color.WHITE;
    private final Color NORMAL_TEXT = Color.GRAY;
    private final Color HOVER_TEXT = Color.WHITE;
    
    public HomeDashboardController(HomeDashboard hd) {
        this.dashboard = hd;
        initControllerHomeDashboard();
    }
    
    private void loadIcones() {
        icoInitN = AuxiliaryMethod.loadedIcone(_PARTH_IMG_INITIAL_NORMAL, 30, 30);
        icoRegisN = AuxiliaryMethod.loadedIcone(dashboard.getAllParthIcons(2), 30, 30);
        icoSchedN = AuxiliaryMethod.loadedIcone(dashboard.getAllParthIcons(3), 30, 30);
        icoClosN = AuxiliaryMethod.loadedIcone(dashboard.getAllParthIcons(4), 30, 30);
        icoHelpN = AuxiliaryMethod.loadedIcone(dashboard.getAllParthIcons(5), 30, 30);
        icoAddN = AuxiliaryMethod.loadedIcone(dashboard.getAllParthIcons(6), 30, 30);
        
        iconSearchN = AuxiliaryMethod.loadedIcone(dashboard.getAllParthIcons(7), 30, 30);
        
        
        
        icoInitH = AuxiliaryMethod.loadedIcone(dashboard.getAllParthIcons(1), 30, 30);
        icoRegisH = AuxiliaryMethod.loadedIcone(_PARTH_IMG_REGISTER_HOVER, 30, 30);
        icoSchedH = AuxiliaryMethod.loadedIcone(_PARTH_IMG_SCHEDULING_HOVER, 30, 30);
        icoClosH = AuxiliaryMethod.loadedIcone(_PARTH_IMG_CLOSE_HOVER, 30, 30);
        icoHelpH = AuxiliaryMethod.loadedIcone(_PARTH_IMG_HELP_HOVER, 30, 30);
        icoAddH = AuxiliaryMethod.loadedIcone(_PARTH_IMG_ADD_HOVER, 30, 30);
        
        iconSearchH = AuxiliaryMethod.loadedIcone(_PARTH_IMG_SEARCH_HOVER, 30, 30);
        

        
    }
    
    // INICIAR TODOS OUTROS COMPONENTES
    private void initControllerHomeDashboard() {
        loadIcones();
        
        AuxiliaryMethod.setPlaceholder(dashboard.getInputSearch(), "Buscar paciente por nome ou CPF...");
        aplicationHover(dashboard.getInputSearch(), dashboard.getAllIncons(7), iconSearchN, iconSearchH, dashboard.getPlaceSearch());
        
        // Configurar hover e clique para todos os botões
        configurarBotao(dashboard.getBtnInitial(), dashboard.getAllIncons(1), icoInitN, icoInitH, dashboard.getLblInitial(), "Inicio");
        configurarBotao(dashboard.getBtnRegister(), dashboard.getAllIncons(2), icoRegisN, icoRegisH, dashboard.getLblRegister(), "Cadastro");
        configurarBotao(dashboard.getBtnScheduling(), dashboard.getAllIncons(3), icoSchedN, icoSchedH, dashboard.getLblScheduling(), "Agendamento");
        
        configurarBotao(dashboard.getBtnClose(), dashboard.getAllIncons(4), icoClosN, icoClosH, dashboard.getLblClose(), "Sair");
        configurarBotao(dashboard.getBtnHelp(), dashboard.getAllIncons(5), icoHelpN, icoHelpH, dashboard.getLblHelp(), "Suporte");
        
        // Selecionar Inicio por padrão
        selecionarBotao(dashboard.getBtnInitial(),dashboard.getAllIncons(1), icoInitN, icoInitH,dashboard.getLblInitial());
        
        // Novo Encaixe
        dashboard.getAllIncons(6).setLayout(new GridBagLayout());
        AuxiliaryMethod.aplcateHoverInBtns(dashboard.getBtnNewFitting(), dashboard.getAllIncons(6), icoAddN, icoAddH, HOVER_COLOR, Color.WHITE, SELECTED_COLOR);
        
    }
    
    // ========== CONFIGURAR BOTÃO ==========
    private void configurarBotao(RoundedPanel panel, IconTextField iconField, ImageIcon imgNormal, ImageIcon imgHover, JLabel label, String nomeBotao) {
        if (panel == null || label == null) return;

        // ========== HOVER ==========
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Se não for o botão selecionado, aplica hover
                if (selectedPanel != panel) {
                    panel.setBackground(HOVER_COLOR);
                    label.setForeground(HOVER_TEXT);
                    iconField.setIcon(imgHover);
                    panel.repaint();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Se não for o botão selecionado, volta ao normal
                if (selectedPanel != panel) {
                    panel.setBackground(NORMAL_BG);
                    label.setForeground(NORMAL_TEXT);
                    iconField.setIcon(imgNormal);
                    panel.repaint();
                }
            }
        });

        // ========== CLIQUE ==========
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Seleciona o botão (aqui os ícones serão tratados)
                selecionarBotao(panel, iconField, imgNormal, imgHover, label);

                // Ações específicas
                if (nomeBotao.equals("Sair")) {
                    System.out.println("Sair - Fechar aplicação");
                } else {
                    System.out.println("Clicou em: " + nomeBotao);
                }
            }
        });
    }
    
    // APLICAR COR FIXA AO BTN SELECIONADO
    private void selecionarBotao(RoundedPanel panel, IconTextField iconField, ImageIcon imgNormal, ImageIcon imgHover, JLabel label) {
        // Restaurar o botão anteriormente selecionado
        if (selectedPanel != null && selectedLabel != null) {
            selectedPanel.setBackground(NORMAL_BG);
            selectedLabel.setForeground(NORMAL_TEXT);
            selectedIconField.setIcon(selectedImgNormal); 
            selectedPanel.repaint();
        }
        
        // Selecionar o novo botão
        this.selectedPanel = panel;
        this.selectedLabel = label;
        this.selectedIconField = iconField;
        this.selectedImgNormal = imgNormal;      // Guarda o ícone normal
        this.selectedImgHover = imgHover;        // Guarda o ícone hover
        
        // Aplicar estilo de selecionado
        if (panel == dashboard.getBtnClose()) {
            panel.setBackground(CLOSE_COLOR);
        } else {
            panel.setBackground(SELECTED_COLOR);
        }
        label.setForeground(HOVER_TEXT);
        iconField.setIcon(imgHover); 
        panel.repaint();
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