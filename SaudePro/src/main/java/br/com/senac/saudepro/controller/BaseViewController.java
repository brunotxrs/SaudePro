package br.com.senac.saudepro.controller;

import br.com.senac.saudepro.gui.BaseView;
import br.com.senac.saudepro.gui.HomeDashboard;
import br.com.senac.saudepro.gui.Login;
import br.com.senac.saudepro.gui.Register;
import br.com.senac.saudepro.gui.Scheduling;
import br.com.senac.saudepro.util.AuxiliaryMethod;
import br.com.senac.saudepro.util.IconTextField;
import br.com.senac.saudepro.util.RoundedPanel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Window;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * BaseViewController servira de herança para filhas de controller eliminando codes repetidos
 * @author bruno-teixeira
 */
public abstract class BaseViewController {
    
    //APLICAR OS DEMAIS ICONES
    protected final String _PARTH_IMG_SEARCH_HOVER = "src/main/java/resources/img/searchHover.png";
    protected final String _PARTH_IMG_INITIAL_NORMAL = "src/main/java/resources/img/icoInitialNormal.png";
    protected final String _PARTH_IMG_REGISTER_HOVER = "src/main/java/resources/img/icoRegisterHover.png";
    protected final String _PARTH_IMG_SCHEDULING_HOVER = "src/main/java/resources/img/calendarHover.png";
    protected final String _PARTH_IMG_CLOSE_HOVER = "src/main/java/resources/img/closeHover.png";
    protected final String _PARTH_IMG_HELP_HOVER = "src/main/java/resources/img/helpHover.png";
    
    
    // ELEMENTOS PARA ARMAZENAMENTOS DOS ELEMENTOS SELECIONADOS
    protected IconTextField selectedIconField = null;
    protected ImageIcon selectedImgNormal = null;
    protected ImageIcon selectedImgHover = null;
    
    
    protected ImageIcon iconSearchN;
    protected ImageIcon iconSearchH;
    
    protected ImageIcon icoInitN;
    protected ImageIcon icoInitH;
    protected ImageIcon icoRegisN;
    protected ImageIcon icoRegisH;
    protected ImageIcon icoSchedN;
    protected ImageIcon icoSchedH;
    protected ImageIcon icoClosN;
    protected ImageIcon icoClosH;
    protected ImageIcon icoHelpN;
    protected ImageIcon icoHelpH;
    
    // View Base
    final BaseView baseView;
    
    // Controle de qual botão está selecionado
    protected RoundedPanel selectedPanel = null;
    protected JLabel selectedLabel = null;
    
    protected final Color SELECTED_COLOR = new Color(0x458C45);
    protected final Color HOVER_COLOR = new Color(0x7ED348);
    protected final Color CLOSE_COLOR = Color.RED;
    protected final Color NORMAL_BG = Color.WHITE;
    protected final Color NORMAL_TEXT = Color.GRAY;
    protected final Color HOVER_TEXT = Color.WHITE;

    public BaseViewController(BaseView baseView) {
        this.baseView = baseView;
        
    }
    
    protected void start(){
        initController();
    }

    
    protected void loadIcones() {
        icoInitN = AuxiliaryMethod.loadedIcone(_PARTH_IMG_INITIAL_NORMAL, 30, 30);
        icoRegisN = AuxiliaryMethod.loadedIcone(baseView.getAllParthIcons(2), 30, 30);
        icoSchedN = AuxiliaryMethod.loadedIcone(baseView.getAllParthIcons(3), 30, 30);
        icoClosN = AuxiliaryMethod.loadedIcone(baseView.getAllParthIcons(4), 30, 30);
        icoHelpN = AuxiliaryMethod.loadedIcone(baseView.getAllParthIcons(5), 30, 30);
        
        iconSearchN = AuxiliaryMethod.loadedIcone(baseView.getAllParthIcons(6), 30, 30);
        
        
        
        icoInitH = AuxiliaryMethod.loadedIcone(baseView.getAllParthIcons(1), 30, 30);
        icoRegisH = AuxiliaryMethod.loadedIcone(_PARTH_IMG_REGISTER_HOVER, 30, 30);
        icoSchedH = AuxiliaryMethod.loadedIcone(_PARTH_IMG_SCHEDULING_HOVER, 30, 30);
        icoClosH = AuxiliaryMethod.loadedIcone(_PARTH_IMG_CLOSE_HOVER, 30, 30);
        icoHelpH = AuxiliaryMethod.loadedIcone(_PARTH_IMG_HELP_HOVER, 30, 30);
        
        iconSearchH = AuxiliaryMethod.loadedIcone(_PARTH_IMG_SEARCH_HOVER, 30, 30);
        
    }
    
        // INICIAR TODOS OUTROS COMPONENTES
    protected void initController() {
        loadIcones();
        
        AuxiliaryMethod.setPlaceholder(baseView.getInputSearch(), "Buscar paciente por nome ou CPF...");
        aplicationHover(baseView.getInputSearch(), baseView.getAllIncons(6), iconSearchN, iconSearchH, baseView.getPlaceSearch());
   
        // CURSOR DA MAOZINHA
        baseView.getAllBtns(1).setCursor(new Cursor(Cursor.HAND_CURSOR));
        baseView.getAllBtns(2).setCursor(new Cursor(Cursor.HAND_CURSOR));
        baseView.getAllBtns(3).setCursor(new Cursor(Cursor.HAND_CURSOR));
        baseView.getAllBtns(4).setCursor(new Cursor(Cursor.HAND_CURSOR));
        baseView.getAllBtns(5).setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Configurar hover e clique para todos os botões
        configurarBotao(baseView.getAllBtns(1), baseView.getAllIncons(1), icoInitN, icoInitH, baseView.getLabelsBtns(1), "Inicio");
        
        configurarBotao(baseView.getAllBtns(2), baseView.getAllIncons(2), icoRegisN, icoRegisH, baseView.getLabelsBtns(2), "Cadastro");
        configurarBotao(baseView.getAllBtns(3), baseView.getAllIncons(3), icoSchedN, icoSchedH, baseView.getLabelsBtns(3), "Agendamento");
        
        configurarBotao(baseView.getAllBtns(4), baseView.getAllIncons(4), icoClosN, icoClosH, baseView.getLabelsBtns(4), "Sair");
        configurarBotao(baseView.getAllBtns(5), baseView.getAllIncons(5), icoHelpN, icoHelpH, baseView.getLabelsBtns(5), "Suporte");
        
        // Selecionar Inicio por padrão
        selecionarBotao(baseView.getAllBtns(1), baseView.getAllIncons(1), icoInitN, icoInitH, baseView.getLabelsBtns(1));
        
        
    }
    
    
    // ========== CONFIGURAR BOTÃO ==========
    protected void configurarBotao(RoundedPanel panel, IconTextField iconField, ImageIcon imgNormal, ImageIcon imgHover, JLabel label, String nomeBotao) {
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

        // ========== CLIQUE + NAVEGAÇAO ==========
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Seleciona o botão (aqui os ícones serão tratados)
                selecionarBotao(panel, iconField, imgNormal, imgHover, label);

                switch(nomeBotao){
                    case "Inicio":
                        System.out.println("Já está no Dashboard");
                        openDashBoard();
                        break;
                    case "Cadastro":
                        openRegister();
                        break;
                    case "Agendamento":
                        System.out.println("Abrindo tela de Agendamento...");
                        openScheduling();
                        break;
                    case "Sair":
                        System.out.println("Fechando aplicação...");
                        abrirLogin();
                        break;
                    case "Suporte":
                        System.out.println("Abrindo Suporte...");
                        // abrirSuporte();
                        break;
                    default:
                        break; 
                }
                
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
    protected void selecionarBotao(RoundedPanel panel, IconTextField iconField, ImageIcon imgNormal, ImageIcon imgHover, JLabel label) {
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
        if (panel == baseView.getAllBtns(4)) {
            panel.setBackground(CLOSE_COLOR);
        } else {
            panel.setBackground(SELECTED_COLOR);
        }
        
        label.setForeground(HOVER_TEXT);
        iconField.setIcon(imgHover); 
        panel.repaint();
    }
    
    
    protected void aplicationHover(JTextField campo, IconTextField iconField, ImageIcon normal, ImageIcon hover, RoundedPanel panel) {
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
    
    protected void openDashBoard(){
        // Procura se já existe uma janela do Dashboard aberta
        for (Window window : Window.getWindows()) {
            if (window instanceof HomeDashboard && window.isVisible()) {
                window.toFront();
                return;
            }
        }

        HomeDashboard hd = new HomeDashboard();
        
        new HomeDashboardController(hd);
        
        hd.setVisible(true);
        
        baseView.dispose();
    }
    
    protected void openRegister(){
        // Procura se já existe uma janela do Register aberta
        for (Window window : Window.getWindows()) {
            if (window instanceof Register && window.isVisible()) {
                window.toFront();
                return;
            }
        }

        Register register = new Register();
        
        new RegisterController(register);
        
        register.setVisible(true);
        
        baseView.dispose();
        
    }
    
    protected void openScheduling(){
         // Procura se já existe uma janela do Register aberta
        for (Window window : Window.getWindows()) {
            if (window instanceof Scheduling && window.isVisible()) {
                window.toFront();
                return;
            }
        }
        
        Scheduling scheduling = new Scheduling();
        new SchedulingController(scheduling);
        
        scheduling.setVisible(true);
        
        baseView.dispose();
        
    }
    
    protected void abrirLogin() {
        
        boolean confirmado = AuxiliaryMethod.mostrarConfirmacaoFlutuante(
            baseView,
            "Deseja realmente sair?",
            350,
            150
        );
        
        
        if (confirmado){
            for (Window window : Window.getWindows()) {
                if (window instanceof Login && window.isVisible()) {
                    window.toFront();
                    return;
                }
            }
            
            Login login = new Login();
        
            new LoginController(login);

            login.setVisible(true);

            baseView.dispose();
        }
        
        
        
        
    }
    
}
