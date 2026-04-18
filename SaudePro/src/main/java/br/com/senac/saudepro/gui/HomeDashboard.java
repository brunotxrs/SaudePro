package br.com.senac.saudepro.gui;

import br.com.senac.saudepro.util.AuxiliaryMethod;
import br.com.senac.saudepro.util.IconTextField;
import br.com.senac.saudepro.util.ImageLogo;
import br.com.senac.saudepro.util.RoundedPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Dashboard - Lista de consultas do dia com horário e médico
 * @author bruno-teixeira
 */
public class HomeDashboard extends JFrame {
    // - Gerar comentario pra isso! 
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(HomeDashboard.class.getName());

    // // Componentes principais
    private static JPanel panelDash;
    private static RoundedPanel btnInitial;
    private static RoundedPanel btnRegister;
    private static RoundedPanel btnScheduling;
    private static RoundedPanel btnClose;
    private static RoundedPanel btnHelp;
    
    private static JLabel lblInitial;
    private static JLabel lblRegister;
    private static JLabel lblScheduling;
    private static JLabel lblClose;    
    private static JLabel lblHelp;
    
    // Caminhos das imagem Icons
    private String _parthIconInitial = "src/main/java/resources/img/icoInitial.png";
    private String _parthIconRegister = "src/main/java/resources/img/registerIco.png";
    private String _parthIconScheduling = "src/main/java/resources/img/calendarIco.png";
    private String _parthIconClose = "src/main/java/resources/img/closeIco.png";
    private String _parthIconHelp = "src/main/java/resources/img/helpIco.png";
    // Ícones
    private final IconTextField icoInit = new IconTextField(_parthIconInitial, 30, 30);
    private final IconTextField icoRegis = new IconTextField(_parthIconRegister, 30, 30);    
    private final IconTextField icoSched = new IconTextField(_parthIconScheduling, 30, 30);
    private final IconTextField icoClos = new IconTextField(_parthIconClose, 30, 30);
    private final IconTextField icoHelp = new IconTextField(_parthIconHelp, 30, 30);
    // side Bars
    private static JPanel sideBarLeft;
    
    // Cores
    private Color greenColor = new Color(0x458C45);
    
    
    public HomeDashboard() {
        
        initComponents();
    }
    
    private void initComponents(){
        configurationFrame(); // instaciando o metodo.
        
        configurationPanelScreen(); // chamando o panel
    }
    
    // configuraçao o Jrame
    private void configurationFrame(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dashbord - SaúdePro");
        setSize(1440, 900); // largura e altura
       
        setLocationRelativeTo(null);
    }
    
    
    // criando o Painel
    private void configurationPanelScreen(){
        panelDash = new JPanel();
        panelDash.setBackground(Color.LIGHT_GRAY);
        panelDash.setLayout(null);
        
        setContentPane(panelDash);
        
        createSideBarLeft();
    }
    
    
    private void createSideBarLeft(){
        // crinado o side bar left
        sideBarLeft = new JPanel();
        sideBarLeft.setLayout(null); // Layout absoluto para controle total
        sideBarLeft.setBackground(Color.WHITE);
        sideBarLeft.setSize(new java.awt.Dimension(300, 900));
        
        // Criar o logo com tamanho personalizado
        ImageLogo logo = new ImageLogo(144, 60);
        logo.setBounds(78, 40, 144, 60);
        
        sideBarLeft.add(logo); // add componente
        
        panelDash.add(sideBarLeft);
        
        // add here btns
        buttonsNavegations(btnInitial, greenColor, 143, icoInit, lblInitial, Color.WHITE,"Inicio", sideBarLeft);
        buttonsNavegations(btnRegister, Color.WHITE, 193, icoRegis, lblRegister, Color.GRAY, "Cadastro", sideBarLeft);
        buttonsNavegations(btnScheduling, Color.WHITE, 243, icoSched, lblScheduling, Color.GRAY, "Agendamento", sideBarLeft);
        buttonsNavegations(btnClose, Color.WHITE, 293, icoClos, lblClose, Color.GRAY, "Sair", sideBarLeft);
        buttonsNavegations(btnHelp, Color.WHITE, 550, icoHelp, lblHelp, Color.GRAY, "Suporte", sideBarLeft);
        
        
    }
    
    private void buttonsNavegations(RoundedPanel panel, Color colorBg, int heigth, IconTextField iconText, JLabel l, Color colorFo, String msn, JPanel main ){
        
        panel = new RoundedPanel(10);
        panel.setLayout(null);
        panel.setBackground(colorBg);
        panel.setBorder(null);
        panel.setBounds(0, heigth, 300, 50);
        
        
        
        // Configurar o ico (IconTextField)
        iconText.setBounds(25, 10, 30, 30);
        iconText.setBackground(null);
        
        // criando labels
        l = new JLabel(msn);
        l.setBorder(null);
        l.setBounds(65, 10, 200, 30);
        l.setFont(new Font("Arial", Font.BOLD, 16));
        l.setForeground(colorFo);
        
        // ADICIONAR AO PAINEL
        panel.add(iconText);
        panel.add(l);
        
        
        main.add(panel);
        
    }
    
    
    
    
    
    
    // metodo pra exibiçao de teste
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
        
        java.awt.EventQueue.invokeLater(() -> new HomeDashboard().setVisible(true));
    }
}
