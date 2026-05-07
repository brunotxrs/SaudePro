package br.com.senac.saudepro.gui;

import br.com.senac.saudepro.util.IconTextField;
import br.com.senac.saudepro.util.ImageLogo;
import br.com.senac.saudepro.util.RoundedPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * BaseView classe para reaproveitamento de codigos
 * @author bruno-teixeira
 */
public abstract class BaseView extends JFrame {
    
    protected JPanel sideBarLeft;
    protected JPanel sideBarRight;
    protected JPanel bodyMain;
    
    //=============================
    // Componentes principais - Serem Usados no [ CONTROLLER ]
    protected static RoundedPanel btnInitial; // <- btn [ Inicio ]
    protected static RoundedPanel btnRegister; // <- btn [ Cadastro ]
    protected static RoundedPanel btnScheduling; // <- btn [ Agendamento ]
    protected static RoundedPanel btnClose; // <- btn [ Sair ]
    protected static RoundedPanel btnHelp; // <- btn [ Suporte ]
    
    protected static JLabel lblInitial; // - Nao irei precisar no Controller
    protected static JLabel lblRegister; // - Nao irei precisar no Controller
    protected static JLabel lblScheduling; // - Nao irei precisar no Controller
    protected static JLabel lblClose;    // - Nao irei precisar no Controller
    protected static JLabel lblHelp; // - Nao irei precisar no Controller
    
    
    //=============================
    // Caminhos das imagem Icons
    protected final String _parthIconInitial = "src/main/java/resources/img/icoInitial.png";
    protected final String _parthIconRegister = "src/main/java/resources/img/registerIco.png";
    protected final String _parthIconScheduling = "src/main/java/resources/img/calendarIco.png";
    protected final String _parthIconClose = "src/main/java/resources/img/closeIco.png";
    protected final String _parthIconHelp = "src/main/java/resources/img/helpIco.png";
    protected final String _parthIconAdd = "src/main/java/resources/img/addBlack.png";
    protected final String _parthIconSearch = "src/main/java/resources/img/searchIco.png";
    
    //=============================    
    // Ícones
    protected final IconTextField icoInit = new IconTextField(_parthIconInitial, 30, 30);
    protected final IconTextField icoRegis = new IconTextField(_parthIconRegister, 30, 30);    
    protected final IconTextField icoSched = new IconTextField(_parthIconScheduling, 30, 30);
    protected final IconTextField icoClos = new IconTextField(_parthIconClose, 30, 30);
    protected final IconTextField icoHelp = new IconTextField(_parthIconHelp, 30, 30);
    protected final IconTextField icoAdd = new IconTextField(_parthIconAdd, 30, 30);
    protected final IconTextField iconSearc = new IconTextField(_parthIconSearch, 30, 30);
    
    //=============================    
    // Cores
    private final Color greenColor = new Color(0x458C45);

    //=============================    
    // Elements
    private static JLabel dateToday;
    
    private static final GridBagLayout gLayout = new GridBagLayout();
    private static GridBagConstraints gbc;
    
    public BaseView(){
    
    }
    
    
    //=============================    
    // configuraçao o Jrame
    //=============================    
    protected void configurationFrame(String title){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(title);
        setSize(1440, 900); // largura e altura
       
        setLocationRelativeTo(null);
    }
    
    
    //=============================    
    // criando o Painel
    //=============================    
    protected void configurationPanelScreen(JPanel panel){
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(gLayout);
        
        setContentPane(panel);
        
        // add more componets
    }
    
        //=============================
    // Componente - SideBar_Left
    //=============================
    protected void createSideBarLeft(JPanel panel) {
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
        
        panel.add(sideBarLeft, gbc);
        
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
        buttonsNavegations(btnInitial, greenColor, 143, icoInit, lblInitial, Color.WHITE, sideBarLeft);
        buttonsNavegations(btnRegister, Color.WHITE, 193, icoRegis, lblRegister, Color.GRAY, sideBarLeft);
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
    protected void createBodyMain(JPanel panel) {
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
        
        panel.add(bodyMain, gbc);
        
    }
    
    //=============================
    // Componente SideBar_Rigth
    //=============================    
    protected void createSideBarRigth(JPanel panel){
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
        
        panel.add(sideBarRight, gbc);
        
    }
    
    
    // =============================================
    // GETTERS para o Controller acessar os componentes
    // =============================================
    public RoundedPanel getAllBtns(int num){
        return switch (num){
            case 1 -> btnInitial;
            case 2 -> btnRegister;
            case 3 -> btnScheduling;
            case 4 -> btnClose;
            case 5 -> btnHelp;
            default -> null; 
        };
    }

    public JLabel getLabelsBtns(int num){
        return switch (num){
            case 1 -> lblInitial;
            case 2 -> lblRegister;
            case 3 -> lblScheduling;
            case 4 -> lblClose;
            case 5 -> lblHelp;
            default -> null; 
        };
    }
    
    public String getAllParthIcons(int num){
        
        return switch (num){
            case 1 -> _parthIconInitial;
            case 2 -> _parthIconRegister;
            case 3 -> _parthIconScheduling;
            case 4 -> _parthIconClose;
            case 5 -> _parthIconHelp;
            default -> null;
        };
    }
    
    
    // metodo para pegar todos os icons e reduzir code 
    public IconTextField getAllIncons(int num){
        
        return switch (num){
            case 1 -> icoInit;
            case 2 -> icoRegis;
            case 3 -> icoSched;
            case 4 -> icoClos;
            case 5 -> icoHelp;    
            default -> null;
                
        };
        
    }
    
}
