package br.com.senac.saudepro.gui;

import br.com.senac.saudepro.util.IconTextField;
import br.com.senac.saudepro.util.ImageLogo;
import br.com.senac.saudepro.util.RoundedPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.BorderFactory;
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
    private static JLabel peopleCard_1;
    private static JLabel peopleCard_2;
    private static JLabel peopleCard_3;
    private static JLabel professionalCard_1;
    private static JLabel professionalCard_2;
    private static JLabel professionalCard_3;
    // Caminhos das imagem Icons
    private final String _parthIconInitial = "src/main/java/resources/img/icoInitial.png";
    private final String _parthIconRegister = "src/main/java/resources/img/registerIco.png";
    private final String _parthIconScheduling = "src/main/java/resources/img/calendarIco.png";
    private final String _parthIconClose = "src/main/java/resources/img/closeIco.png";
    private final String _parthIconHelp = "src/main/java/resources/img/helpIco.png";
    // Ícones
    private final IconTextField icoInit = new IconTextField(_parthIconInitial, 30, 30);
    private final IconTextField icoRegis = new IconTextField(_parthIconRegister, 30, 30);    
    private final IconTextField icoSched = new IconTextField(_parthIconScheduling, 30, 30);
    private final IconTextField icoClos = new IconTextField(_parthIconClose, 30, 30);
    private final IconTextField icoHelp = new IconTextField(_parthIconHelp, 30, 30);
 
    // side Bars
    private static JPanel sideBarLeft;
    private static JPanel sideBarRight;
    private static JPanel bodyMain;
 
    
    
    // Cores
    private final Color greenColor = new Color(0x458C45);
    
    // Elements
    private static JLabel dateToday;
    
    private static GridBagLayout gLayout = new GridBagLayout();
    private static GridBagConstraints gbc;
    
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
        setTitle("Dashboard - SaúdePro");
        setSize(1440, 900); // largura e altura
       
        setLocationRelativeTo(null);
    }
    
    
    // criando o Painel
    private void configurationPanelScreen(){
        panelDash = new JPanel();
        panelDash.setBackground(Color.LIGHT_GRAY);
        panelDash.setLayout(gLayout);
        
        setContentPane(panelDash);
        
        createSideBarLeft();
        
        createBodyMain();
        
        createSideBarRigth();
    }
    
    
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
        
        panelDash.add(sideBarLeft, gbc);
        
        // add here btns
        buttonsNavegations(btnInitial, greenColor, 143, icoInit, lblInitial, Color.WHITE,"Inicio", sideBarLeft);
        buttonsNavegations(btnRegister, Color.WHITE, 193, icoRegis, lblRegister, Color.GRAY, "Cadastro", sideBarLeft);
        buttonsNavegations(btnScheduling, Color.WHITE, 243, icoSched, lblScheduling, Color.GRAY, "Agendamento", sideBarLeft);
        buttonsNavegations(btnClose, Color.WHITE, 293, icoClos, lblClose, Color.GRAY, "Sair", sideBarLeft);
        buttonsNavegations(btnHelp, Color.WHITE, 550, icoHelp, lblHelp, Color.GRAY, "Suporte", sideBarLeft);
        
        
    }
    
    private void createBodyMain(){
        // grydbag ajustavel 
        gbc = new GridBagConstraints();
        gbc.gridx = 1; // coluna 1
        gbc.gridy = 0; // linha 0
        gbc.weightx = 1; // expande horizontalmente
        gbc.weighty = 1; // ocupa toda altura disponível
        gbc.fill = GridBagConstraints.BOTH;

        bodyMain = new JPanel();
        bodyMain.setLayout(null); // Layout absoluto para controle total
        bodyMain.setBackground(Color.LIGHT_GRAY);
        
        panelDash.add(bodyMain, gbc);
    }
    
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
        
        panelDash.add(sideBarRight, gbc);
        
        // add more components
        showDateActual();
        
        createCards(sideBarRight, peopleCard_1, professionalCard_1, 25, 150);
        createCards(sideBarRight, peopleCard_2, professionalCard_2, 25, 250);
        createCards(sideBarRight, peopleCard_3, professionalCard_3, 25, 350);
        
    }
    
    // metodo reaprovetavel pra criar btn navegations
    private void buttonsNavegations(
            RoundedPanel panel, 
            Color colorBg, 
            int heigth, 
            IconTextField iconText, 
            JLabel l, 
            Color colorFo, 
            String msn, 
            JPanel main 
    ){
        
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
    
    // criando metodo pra exibiçao da Data
    private void showDateActual(){
        JLabel lblNexts = new JLabel("Próximos Atendimentos");
        lblNexts.setFont(new Font("Arial", Font.PLAIN, 18));
        lblNexts.setForeground(Color.BLACK);
        lblNexts.setBounds(20, 95, 250, 35);
        lblNexts.setHorizontalAlignment(JLabel.LEFT);
        
        lblNexts.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
        
        dateToday = new JLabel();
        LocalDate today = LocalDate.now();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "EEEE dd 'de' MMMM 'de' yyyy", 
                new Locale("pt", "BR")
        ); 
        
        String dataFormatada = today.format(formatter);
        dataFormatada = dataFormatada.substring(0, 1).toUpperCase() + dataFormatada.substring(1);
        
        dateToday.setText(dataFormatada);
        dateToday.setFont(new Font("Arial", Font.PLAIN, 18));
        dateToday.setForeground(greenColor);
        dateToday.setBounds(0, 35, 300, 64);
        dateToday.setHorizontalAlignment(JLabel.CENTER);
        
        sideBarRight.add(lblNexts);
        sideBarRight.add(dateToday);
    }
    
    
    private void createCards(JPanel main,  JLabel u, JLabel p, int x, int y){
        Color pretoTransparente = new Color(0, 0, 0, 25); // 25 = transparência

        // Branco Gelo
        Color brancoGelo = new Color(0xF0F4F8);
        
        RoundedPanel shadowPanel = new RoundedPanel(20);
        shadowPanel.setLayout(null);
        shadowPanel.setBackground(pretoTransparente); // Preto transparente
        shadowPanel.setBounds(x - 5, y - 5, 260, 80); // Deslocado para baixo e direita

        
        
        RoundedPanel cardPanel = new RoundedPanel(20);
        cardPanel.setLayout(null);
        cardPanel.setPreferredSize(new Dimension(250, 70));
        cardPanel.setBounds(x, y, 250, 70);
        cardPanel.setBackground(Color.DARK_GRAY);
        cardPanel.setRoundedBorder(Color.white, 1);
        
        
        u = new JLabel("09:00 - Paciente");
        u.setFont(new Font("Arial", Font.BOLD, 17));
        u.setForeground(brancoGelo);
        u.setBounds(0, 5, 250, 30);
        u.setVerticalAlignment(JLabel.TOP);
        u.setHorizontalAlignment(JLabel.CENTER);
        
        p = new JLabel("Doutor");
        p.setFont(new Font("Arial", Font.BOLD, 17));
        p.setForeground(brancoGelo);
        p.setBounds(0, 35, 260, 30);
        p.setVerticalAlignment(JLabel.BOTTOM);
        p.setHorizontalAlignment(JLabel.CENTER);
        
        cardPanel.add(u);
        cardPanel.add(p);
        
        main.add(shadowPanel);
        
        main.add(cardPanel);
        
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
