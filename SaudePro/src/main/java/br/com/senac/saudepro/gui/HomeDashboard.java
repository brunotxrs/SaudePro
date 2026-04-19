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
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Dashboard - Lista de consultas do dia com horário e médico
 * @author bruno-teixeira
 */
public class HomeDashboard extends JFrame {
    // - Gerar comentario pra isso! 
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(HomeDashboard.class.getName());
    
    //=============================
    // Componentes principais - Serem Usados no [ CONTROLLER ]
    private static JPanel panelDash;
    private static RoundedPanel btnInitial; // <- btn [ Inicio ]
    private static RoundedPanel btnRegister; // <- btn [ Cadastro ]
    private static RoundedPanel btnScheduling; // <- btn [ Agendamento ]
    private static RoundedPanel btnClose; // <- btn [ Sair ]
    private static RoundedPanel btnHelp; // <- btn [ Suporte ]
    private static ShadowPanel btnNewFitting; // <- btn [ Novo Encaixe ]
    private static RoundedPanel placeSearch; // <- campo de perguisar
    private static RoundedPanel container;
    
    private static JLabel lblInitial;
    private static JLabel lblRegister;
    private static JLabel lblScheduling;
    private static JLabel lblClose;    
    private static JLabel lblHelp;
    private static JLabel peopleCard_1;
    private static JLabel peopleCard_2;
    private static JLabel professionalCard_1;
    private static JLabel professionalCard_2;
    private static JLabel peopleDay;
    private static JLabel numPeoleDay;
    private static JLabel consultDay;
    private static JLabel numConsul;
    private static JLabel awaitCall;
    private static JLabel numAwit;
    private static JLabel medics;
    private static JLabel numMedics;
    
    private static JTextField inputSearch;
    
    //=============================
    // Caminhos das imagem Icons
    private final String _parthIconInitial = "src/main/java/resources/img/icoInitial.png";
    private final String _parthIconRegister = "src/main/java/resources/img/registerIco.png";
    private final String _parthIconScheduling = "src/main/java/resources/img/calendarIco.png";
    private final String _parthIconClose = "src/main/java/resources/img/closeIco.png";
    private final String _parthIconHelp = "src/main/java/resources/img/helpIco.png";
    private final String _parthIconAdd = "src/main/java/resources/img/addIco.png";
    private final String _parthIconSearch = "src/main/java/resources/img/searchIco.png";
    
    //=============================    
    // Ícones
    private final IconTextField icoInit = new IconTextField(_parthIconInitial, 30, 30);
    private final IconTextField icoRegis = new IconTextField(_parthIconRegister, 30, 30);    
    private final IconTextField icoSched = new IconTextField(_parthIconScheduling, 30, 30);
    private final IconTextField icoClos = new IconTextField(_parthIconClose, 30, 30);
    private final IconTextField icoHelp = new IconTextField(_parthIconHelp, 30, 30);
    private final IconTextField icoAdd = new IconTextField(_parthIconAdd, 40, 50);
    private final IconTextField iconSearc = new IconTextField(_parthIconSearch, 30, 30);
    
    //=============================    
    // side Bars
    private static JPanel sideBarLeft;
    private static JPanel sideBarRight;
    private static JPanel bodyMain;
 
    
    //=============================    
    // Cores
    private final Color greenColor = new Color(0x458C45);

    //=============================    
    // Elements
    private static JLabel dateToday;
    
    private static final GridBagLayout gLayout = new GridBagLayout();
    private static GridBagConstraints gbc;
    
    public HomeDashboard() {
        
        initComponents();
    }
    
    //=============================
    // Componente de inicializaçao de todos os componentes
    //=============================    
    private void initComponents(){
        configurationFrame(); // instaciando o metodo.
        
        configurationPanelScreen(); // chamando o panel
    }
    //=============================    
    // configuraçao o Jrame
    //=============================    
    private void configurationFrame(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dashboard - SaúdePro");
        setSize(1440, 900); // largura e altura
       
        setLocationRelativeTo(null);
    }
    
    //=============================    
    // criando o Painel
    //=============================    
    private void configurationPanelScreen(){
        panelDash = new JPanel();
        panelDash.setBackground(Color.LIGHT_GRAY);
        panelDash.setLayout(gLayout);
        
        setContentPane(panelDash);
        
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
        
        panelDash.add(sideBarLeft, gbc);
        
        // add here btns
        buttonsNavegations(btnInitial, greenColor, 143, icoInit, lblInitial, Color.WHITE,"Inicio", sideBarLeft);
        buttonsNavegations(btnRegister, Color.WHITE, 193, icoRegis, lblRegister, Color.GRAY, "Cadastro", sideBarLeft);
        buttonsNavegations(btnScheduling, Color.WHITE, 243, icoSched, lblScheduling, Color.GRAY, "Agendamento", sideBarLeft);
        buttonsNavegations(btnClose, Color.WHITE, 293, icoClos, lblClose, Color.GRAY, "Sair", sideBarLeft);
        buttonsNavegations(btnHelp, Color.WHITE, 550, icoHelp, lblHelp, Color.GRAY, "Suporte", sideBarLeft);
        
        
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
        
        panelDash.add(bodyMain, gbc);
        
        componentSearch();
        componentPanelInforCards();
        
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

        // ===== CONFIG DO CONTAINER =====
        gbcS.gridx = 0;
        gbcS.gridy = 0;
        gbcS.insets = new java.awt.Insets(25, 20, 0, 20); // Espaçamento externo (MARGEM)
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
        gbcInput.weightx = 1; // 🔥 FAZ CRESCER
        gbcInput.fill = GridBagConstraints.HORIZONTAL;
        gbcInput.insets = new Insets(0, 0, 0, 15);
       
        // ======================
        
        // ADD COMPONENTES
        placeSearch.add(iconSearc, gbcIcon);
        placeSearch.add(inputSearch, gbcInput);
        
        bodyMain.add(placeSearch, gbcS);

    }
    
    //=============================
    // Panel dos Cards Informativos
    //=============================
    private void componentPanelInforCards(){
        GridBagConstraints gContainer = new GridBagConstraints();
        
        container = new RoundedPanel(20);
        container.setBackground(Color.WHITE);
        container.setPreferredSize(new Dimension(0, 300));
        container.setLayout(gLayout);
        
        // ===== CONFIG DO CONTAINER =====
        gContainer.gridx = 0;
        gContainer.gridy = 1;
        gContainer.insets = new java.awt.Insets(25, 20, 0, 20); // Espaçamento externo (MARGEM)
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
        
        numPeoleDay = new JLabel("12");
        peopleDay = new JLabel("Pacientes do Dia");
        
        numConsul = new JLabel("12");
        consultDay = new JLabel("Consultas em Aberto");
        
        numAwit = new JLabel("10");
        awaitCall = new JLabel("Aguardando Atendimento");
        
        numMedics = new JLabel("6");
        medics = new JLabel("Médicos de Plantão");
        
        cardInfo(numPeoleDay, peopleDay, v, 0, 0);
        cardInfo(numConsul, consultDay, a, 1, 0);
        cardInfo(numAwit, awaitCall, o, 0, 1);
        cardInfo(numMedics, medics, al, 1, 1);
        
        
    }
    
    // =====================
    // Cards Informativos
    // =====================
    private void cardInfo(JLabel num, JLabel mensages, Color bgColor, int c, int r){

        Color branco = new Color(255, 255, 255, 51);
        
        GridBagConstraints gLF = new GridBagConstraints();
        num.setFont(new Font("Arial", Font.BOLD, 16));
        num.setForeground(Color.WHITE);
        
        // ====== Config 1° Lab
        gLF.gridx = 0;
        gLF.gridy = 0;
        gLF.insets = new Insets(0, 10, 0, 0); // espaçamento interno
        gLF.anchor = GridBagConstraints.CENTER;
        //==================
        
        GridBagConstraints gLF2 = new GridBagConstraints();
        
        RoundedPanel panNum = new RoundedPanel(50);
        panNum.setBackground(branco);
        panNum.setPreferredSize(new Dimension(50, 50));
        panNum.setLayout(gLayout);
        // ====== Config 2° Lab
        gLF2.gridx = 1;
        gLF2.gridy = 0;
        gLF2.insets = new Insets(0, 10, 0, 10); // espaçamento interno
        gLF2.anchor = GridBagConstraints.CENTER;
        
        panNum.add(num, new GridBagConstraints());
        
        mensages.setFont(new Font("Arial", Font.BOLD, 16));
        mensages.setForeground(Color.WHITE);
        
        Color boxShadow = new Color(0, 0, 0, 10); // cor com nivel de transparencia
        
        GridBagConstraints gbcMain = new GridBagConstraints();
        
        ShadowPanel main = new ShadowPanel(8, 20, boxShadow);
        main.setBackground(bgColor);
        main.setPreferredSize(new Dimension(300, 100));
        main.setLayout(gLayout);
        

        
        gbcMain.gridx = c; // Colo
        gbcMain.gridy = r; // lin
        gbcMain.weightx = 0; // NÃO crescer horizontalmente
        gbcMain.weighty = 0; // NÃO crescer verticalmente
        gbcMain.insets = new Insets(25, 15, 20, 10); // espaçamento interno
        gbcMain.anchor = GridBagConstraints.WEST;
        
        
        main.add(panNum, gLF);
        main.add(mensages, gLF2);
        
        container.add(main, gbcMain);
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
        
        panelDash.add(sideBarRight, gbc);
        
        // add more components
        showDateActual();
        
        createCards(sideBarRight, peopleCard_1, professionalCard_1, 20, 165);
        createCards(sideBarRight, peopleCard_2, professionalCard_2, 20, 314);
        
        // Componente Novo Encaixe
        newFitting();
        
    }
    
    //=============================    
    // criando metodo pra exibiçao da Data - Component Sidebar_Rigth
    //=============================
    private void showDateActual(){
        // Area de Data e Hora
        dateToday = new JLabel();
        
        dateToday.setFont(new Font("Arial", Font.PLAIN, 18));
        dateToday.setForeground(greenColor);
        dateToday.setBounds(0, 25, 300, 70);
        
        
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "EEEE dd 'de' MMMM 'de' yyyy", 
                new Locale("pt", "BR")
        ); 
        
        // Data e Formatando
        String dataFormatada = today.format(formatter);
        dataFormatada = dataFormatada.substring(0, 1).toUpperCase() + dataFormatada.substring(1);
        
        // Hora e Formatando 
        Date horaActual = new Date();
        String timeActual = new SimpleDateFormat("HH:mm:ss").format(horaActual);
        
        
        dateToday.setText(
                "<html>"
                        + 
                        "<div style='text-align:center; 'width=300px'; height='45px'>"
                            + dataFormatada + "<br>Hora " + timeActual +
                        "</div>"
             + "</html>" 
        );
        dateToday.setHorizontalAlignment(JLabel.CENTER);
        dateToday.setVerticalAlignment(JLabel.CENTER);
        
        
        // Area de proximos atendimentos
        JLabel lblNexts = new JLabel("Próximos Atendimentos");
        lblNexts.setFont(new Font("Arial", Font.PLAIN, 18));
        lblNexts.setForeground(Color.BLACK);
        lblNexts.setBounds(20, 100, 250, 40);
        lblNexts.setHorizontalAlignment(JLabel.CENTER);
        
        lblNexts.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, greenColor));
        
        

        sideBarRight.add(dateToday);
        sideBarRight.add(lblNexts);
    }

    //=============================    
    // Cards de Proximos Atendimentos - Component Sidebar_Rigth
    //=============================
    private void createCards(JPanel main,  JLabel u, JLabel p, int x, int y){
        
        Color pretoTransparente = new Color(0, 0, 0, 80); // 25 = transparência

        // Branco Gelo
        Color brancoGelo = new Color(0xF0F4F8);
        // Color for shadow
        Color boxShadow = new Color(0, 0, 0, 80);
        
        ShadowPanel cardPanel = new ShadowPanel(8, 15,boxShadow);
        cardPanel.setLayout(null);
        cardPanel.setPreferredSize(new Dimension(260, 107));
        cardPanel.setBounds(x, y, 260, 107);
        cardPanel.setBackground(brancoGelo);
        
        u = new JLabel("09:00 - Paciente");
        u.setFont(new Font("Arial", Font.BOLD, 17));
        u.setForeground(Color.BLACK);
        u.setBounds(0, 25, 260, 34);
        u.setVerticalAlignment(JLabel.TOP);
        u.setHorizontalAlignment(JLabel.CENTER);
        
        p = new JLabel("Doutor");
        p.setFont(new Font("Arial", Font.BOLD, 17));
        p.setForeground(pretoTransparente);
        p.setBounds(0, 45, 260, 33);
        p.setVerticalAlignment(JLabel.BOTTOM);
        p.setHorizontalAlignment(JLabel.CENTER);
        
        cardPanel.add(u);
        cardPanel.add(p);
        
        main.add(cardPanel);
        
    }

    //=============================    
    // Elemento [ Novo Encaixe ] - Component Sidebar_Rigth
    //=============================
    private void newFitting(){
        // Color for shadow
        Color boxShadow = new Color(0, 0, 0, 10); // cor com nivel de transparencia
        
        btnNewFitting = new ShadowPanel(8, 80, boxShadow);
        
        btnNewFitting.setLayout(gLayout);
        btnNewFitting.setBounds(115, 500, 80, 80);
        btnNewFitting.setBackground(greenColor);
        
        icoAdd.setBounds(0, 0, 40, 50);
        icoAdd.setBackground(null);
        btnNewFitting.add(icoAdd);
        
        JLabel label = new JLabel("Novo Encaixe");
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        label.setForeground(Color.BLACK);
        label.setBounds(0, 575, 300, 52);
        label.setHorizontalAlignment(JLabel.CENTER);
        

        sideBarRight.add(btnNewFitting);
        sideBarRight.add(label);
    }

    
    //=============================    
    // metodo pra exibiçao de teste
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
        
        java.awt.EventQueue.invokeLater(() -> new HomeDashboard().setVisible(true));
    }
}
