package br.com.senac.saudepro.gui;

import br.com.senac.saudepro.util.IconTextField;
import br.com.senac.saudepro.util.ImageLogo;
import br.com.senac.saudepro.util.RoundedPanel;
import br.com.senac.saudepro.util.ShadowPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Register - view area para registro de pacientes
 * @author bruno-teixeira
 */
public class Register extends JFrame {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Register.class.getName());
    
    //=============================
    // Componentes principais - Serem Usados no [ CONTROLLER ]
    private static JPanel panelRegister;
    private static RoundedPanel btnInitial; // <- btn [ Inicio ]
    private static RoundedPanel btnRegister; // <- btn [ Cadastro ]
    private static RoundedPanel btnScheduling; // <- btn [ Agendamento ]
    private static RoundedPanel btnClose; // <- btn [ Sair ]
    private static RoundedPanel btnHelp; // <- btn [ Suporte ]
    private static ShadowPanel btnNewFitting; // <- btn [ Novo Encaixe ]
    private static RoundedPanel placeSearch; // <- campo de perguisar
    private static RoundedPanel container; // <- panel para cards informaçoes - Nao irei precisar no Controller
    
    private static JLabel lblInitial; // - Nao irei precisar no Controller
    private static JLabel lblRegister; // - Nao irei precisar no Controller
    private static JLabel lblScheduling; // - Nao irei precisar no Controller
    private static JLabel lblClose;    // - Nao irei precisar no Controller
    private static JLabel lblHelp; // - Nao irei precisar no Controller
    private static JLabel peopleCard_1; // - irei precisar no Controller
    private static JLabel peopleCard_2; // -  irei precisar no Controller
    
    private static JTextField inputSearch; // - rei precisar no Controller
    
    //=============================
    // Caminhos das imagem Icons
    private final String _parthIconInitial = "src/main/java/resources/img/icoInitialNormal.png";
    private final String _parthIconRegister = "src/main/java/resources/img/icoRegisterHover.png";
    private final String _parthIconScheduling = "src/main/java/resources/img/calendarIco.png";
    private final String _parthIconClose = "src/main/java/resources/img/closeIco.png";
    private final String _parthIconHelp = "src/main/java/resources/img/helpIco.png";
    private final String _parthIconAdd = "src/main/java/resources/img/addBlack.png";
    private final String _parthIconSearch = "src/main/java/resources/img/searchIco.png";
    
    //=============================    
    // Ícones
    private final IconTextField icoInit = new IconTextField(_parthIconInitial, 30, 30);
    private final IconTextField icoRegis = new IconTextField(_parthIconRegister, 30, 30);    
    private final IconTextField icoSched = new IconTextField(_parthIconScheduling, 30, 30);
    private final IconTextField icoClos = new IconTextField(_parthIconClose, 30, 30);
    private final IconTextField icoHelp = new IconTextField(_parthIconHelp, 30, 30);
    private final IconTextField icoAdd = new IconTextField(_parthIconAdd, 30, 30);
    private final IconTextField iconSearc = new IconTextField(_parthIconSearch, 30, 30);
    
    
    //=============================    
    // side Bars
    private static JPanel sideBarLeft;
    private static JPanel sideBarRight;
    private static JPanel bodyMain;
    
    private static final GridBagLayout gLayout = new GridBagLayout();
    private static GridBagConstraints gbc;
    
    
    //=============================    
    // Cores
    private final Color greenColor = new Color(0x458C45);

    
    public Register() throws HeadlessException {
    
        initComponents();
    }
    
    private void initComponents(){
        configurationFrame();
        
        configurationPanelScreen();
    }
    
    //=============================    
    // configuraçao o Jrame
    //=============================
    private void configurationFrame(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registro - SaúdePro");
        setSize(1440, 900);
        
        setLocationRelativeTo(null);
    }
    
    
    //=============================    
    // criando o Painel
    //=============================
    private void configurationPanelScreen(){
        
        panelRegister = new JPanel();
        panelRegister.setBackground(Color.LIGHT_GRAY);
        panelRegister.setLayout(gLayout);
        
        setContentPane(panelRegister);
        
        createSideBarLeft();
        
        createBodyMain();
        
        createSideBarRigth();
    }
    
    
    //=============================
    // Componente - SideBar_Left
    //=============================
    private void createSideBarLeft(){
        // grydbag ajustavel 
        gbc = new GridBagConstraints();
        gbc.gridx = 0; // coluna 0
        gbc.gridy = 0; // linha 0
        gbc.weightx = 0; // não expande horizontalmente
        gbc.weighty = 1; // ocupa toda altura disponível
        gbc.fill = GridBagConstraints.VERTICAL;

        // crinado o side bar left
        sideBarLeft = new JPanel();
        sideBarLeft.setLayout(null); // Layout absoluto para controle total
        sideBarLeft.setBackground(Color.WHITE);
        sideBarLeft.setPreferredSize(new java.awt.Dimension(300, 0));
        
        
        // Criar o logo com tamanho personalizado
        ImageLogo logo = new ImageLogo(144, 60);
        logo.setBounds(78, 40, 144, 60);
        
        sideBarLeft.add(logo); // add componente
        
        panelRegister.add(sideBarLeft, gbc);
        
        // Criando os panel
        btnInitial = new RoundedPanel(10);
        btnRegister = new RoundedPanel(10);
        btnScheduling = new RoundedPanel(10);
        btnClose = new RoundedPanel(10);
        btnHelp = new RoundedPanel(10);
        
        // Labels
        lblInitial = new JLabel("Inicio");
        lblRegister = new JLabel("Cadastro");
        lblScheduling = new JLabel("Agendamento");
        lblClose = new JLabel("Sair");
        lblHelp = new JLabel("Suporte");
        
        // add here btns
        buttonsNavegations(btnInitial, Color.WHITE, 143, icoInit, lblInitial, Color.GRAY, sideBarLeft);
        buttonsNavegations(btnRegister, greenColor, 193, icoRegis, lblRegister, Color.WHITE, sideBarLeft);
        buttonsNavegations(btnScheduling, Color.WHITE, 243, icoSched, lblScheduling, Color.GRAY, sideBarLeft);
        buttonsNavegations(btnClose, Color.WHITE, 293, icoClos, lblClose, Color.GRAY, sideBarLeft);
        buttonsNavegations(btnHelp, Color.WHITE, 550, icoHelp, lblHelp, Color.GRAY, sideBarLeft);
        
        
    }
    //=============================    
    // metodo reaprovetavel pra criar btn navegations - Component Sidebar_Left
    //=============================
    private void buttonsNavegations(
            RoundedPanel panel, 
            Color colorBg, 
            int heigth, 
            IconTextField iconText, 
            JLabel l, 
            Color colorFo, 
            JPanel main 
    ){
        
        
        panel.setLayout(null);
        panel.setBackground(colorBg);
        panel.setBorder(null);
        panel.setBounds(0, heigth, 300, 50);
        
        
        
        // Configurar o ico (IconTextField)
        iconText.setBounds(25, 10, 30, 30);
        iconText.setBackground(null);
        iconText.setLayout(new GridBagLayout());
        
        // criando labels
        l.setBorder(null);
        l.setBounds(65, 10, 200, 30);
        l.setFont(new Font("Arial", Font.BOLD, 16));
        l.setForeground(colorFo);
        
        // ADICIONAR AO PAINEL
        panel.add(iconText);
        panel.add(l);
        
        
        main.add(panel);
        
    }

    
    //=============================
    //Componente Main
    //=============================    
    private void createBodyMain(){
        // grydbag ajustavel 
        gbc = new GridBagConstraints();
        gbc.gridx = 1; // coluna 1
        gbc.gridy = 0; // linha 0
        gbc.weightx = 1; // expande horizontalmente
        gbc.weighty = 1; // ocupa toda altura disponível
        gbc.fill = GridBagConstraints.BOTH;

        bodyMain = new JPanel();
        bodyMain.setLayout(gLayout); // Layout absoluto para controle total
        bodyMain.setBackground(Color.LIGHT_GRAY);
        
        panelRegister.add(bodyMain, gbc);
        
        componentSearch();
                
    }
    
    //=============================
    // Metodo de Search
    //=============================
    private void componentSearch(){
        GridBagConstraints gbcS = new GridBagConstraints();
        
        // ==== CRIANDO CONTAINER =========
        placeSearch = new RoundedPanel(30);
        placeSearch.setBackground(Color.WHITE);
        placeSearch.setPreferredSize(new Dimension(0, 56)); // altura fixa
        placeSearch.setLayout(gLayout);
        placeSearch.setBorder(null);

        // ===== CONFIG DO CONTAINER =====
        gbcS.gridx = 0;
        gbcS.gridy = 0;
        gbcS.insets = new java.awt.Insets(5, 20, 0, 20); // Espaçamento externo (MARGEM)
        gbcS.weightx = 1; // Crescer horizontalmente
        gbcS.weighty = 0; // NÃO crescer verticalmente
        gbcS.fill = GridBagConstraints.HORIZONTAL;
        // ======================================
        
        
        // ===== ICON =====
        GridBagConstraints gbcIcon = new GridBagConstraints();
        gbcIcon.gridx = 0;
        gbcIcon.gridy = 0;
        gbcIcon.insets = new Insets(0, 15, 0, 10); // espaçamento interno
        gbcIcon.anchor = GridBagConstraints.WEST;
        
        
        // ===== INPUT =====
        inputSearch = new JTextField();
        inputSearch.setBorder(null);
        inputSearch.setFont(new Font("Arial", Font.PLAIN, 18));
        inputSearch.setHorizontalAlignment(JTextField.CENTER);

        
        GridBagConstraints gbcInput = new GridBagConstraints();
        gbcInput.gridx = 1;
        gbcInput.gridy = 0;
        gbcInput.weightx = 1; // FAZ CRESCER
        gbcInput.fill = GridBagConstraints.HORIZONTAL;
        gbcInput.insets = new Insets(0, 0, 0, 15);
       
        // ======================
        
        // ADD COMPONENTES
        placeSearch.add(iconSearc, gbcIcon);
        placeSearch.add(inputSearch, gbcInput);
        
        bodyMain.add(placeSearch, gbcS);

        componentPanelInforRegister();
    }
    
    
    //=============================
    // Panel Register Informations
    //=============================
    private void componentPanelInforRegister(){
        GridBagConstraints gContainer = new GridBagConstraints();
        
        container = new RoundedPanel(20);
        container.setBackground(Color.WHITE);
        container.setPreferredSize(new Dimension(0, 450));
        container.setLayout(gLayout);
        
        // ===== CONFIG DO CONTAINER =====
        gContainer.gridx = 0;
        gContainer.gridy = 1;
        gContainer.insets = new java.awt.Insets(5, 20, 0, 20); // Espaçamento externo (MARGEM)
        gContainer.weightx = 1; // Crescer horizontalmente
        gContainer.weighty = 0; // NÃO crescer verticalmente
        gContainer.fill = GridBagConstraints.HORIZONTAL;
        //===============================
        
        
        // ADD COMPONENTES
        bodyMain.add(container, gContainer);
        
        Color v = new Color(0x52B788);
        Color a = new Color(0x4299E1);
        Color o = new Color(0xF6AD55);
        Color al = new Color(0x64B5F6);
        
    }
    
    
        //=============================
    // Componente SideBar_Rigth
    //=============================    
    private void createSideBarRigth(){
        // grydbag ajustavel 
        gbc = new GridBagConstraints();
        gbc.gridx = 2; // coluna 2
        gbc.gridy = 0; // linha 0
        gbc.weightx = 0; // não expande horizontalmente
        gbc.weighty = 1; // ocupa toda altura disponível
        gbc.fill = GridBagConstraints.VERTICAL;
        
        // crinado o side bar Rigth
        sideBarRight = new JPanel();
        sideBarRight.setLayout(null); // Layout absoluto para controle total
        sideBarRight.setBackground(Color.WHITE);
        sideBarRight.setPreferredSize(new java.awt.Dimension(300, 0));
        
        panelRegister.add(sideBarRight, gbc);
        
        peopleCard_1 = new JLabel("Paciente 1");
        peopleCard_2 = new JLabel("Paciente 2");
        
        createCards(sideBarRight, peopleCard_1, 20, 165);
        createCards(sideBarRight, peopleCard_2, 20, 314);
        
        
    }
    
    
        //=============================    
    // Cards de Proximos Atendimentos - Component Sidebar_Rigth
    //=============================
    private void createCards(JPanel main,  JLabel u, int x, int y){
        
        Color pretoTransparente = new Color(0, 0, 0, 80); //  transparência

        // Branco Gelo
        Color brancoGelo = new Color(0xF0F4F8);
        // Color for shadow
        Color boxShadow = new Color(0, 0, 0, 80);
        
        ShadowPanel cardPanel = new ShadowPanel(8, 15,boxShadow);
        cardPanel.setLayout(null);
        cardPanel.setPreferredSize(new Dimension(260, 50));
        cardPanel.setBounds(x, y, 260, 50);
        cardPanel.setLayout(gLayout);
        cardPanel.setBackground(brancoGelo);
        
        
        u.setFont(new Font("Arial", Font.BOLD, 17));
        u.setForeground(Color.BLACK);
        u.setBounds(0, 25, 260, 34);
        u.setVerticalAlignment(JLabel.TOP);
        u.setHorizontalAlignment(JLabel.CENTER);
        
        cardPanel.add(u);
        
        main.add(cardPanel);
        
    }
    
    
    
    
    //=============================    
    // metodo pra exibiçao
    //=============================    
    public static void main(String[] args) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(() -> {
            Register register = new Register();
            
            //new RegisterController(register);
            
            register.setVisible(true);
        });
    }
}
