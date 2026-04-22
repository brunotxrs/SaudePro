package br.com.senac.saudepro.gui;

import br.com.senac.saudepro.controller.HomeDashboardController;
import br.com.senac.saudepro.util.IconTextField;
import br.com.senac.saudepro.util.ImageLogo;
import br.com.senac.saudepro.util.RoundedPanel;
import br.com.senac.saudepro.util.ShadowPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

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
    private static RoundedPanel container; // <- panel para cards informaçoes - Nao irei precisar no Controller
    private static RoundedPanel panelToTable; // <-  Panel para a Tabela - Nao irei precisar no Controller
    
    private static JLabel lblInitial; // - Nao irei precisar no Controller
    private static JLabel lblRegister; // - Nao irei precisar no Controller
    private static JLabel lblScheduling; // - Nao irei precisar no Controller
    private static JLabel lblClose;    // - Nao irei precisar no Controller
    private static JLabel lblHelp; // - Nao irei precisar no Controller
    private static JLabel peopleCard_1; // - irei precisar no Controller
    private static JLabel peopleCard_2; // -  irei precisar no Controller
    private static JLabel professionalCard_1; // -  irei precisar no Controller
    private static JLabel professionalCard_2; // -  irei precisar no Controller
    private static JLabel peopleDay; // -  irei precisar no Controller
    private static JLabel numPeoleDay;  // -  irei precisar no Controller
    private static JLabel consultDay;  // -  irei precisar no Controller
    private static JLabel numConsul;  // -  irei precisar no Controller
    private static JLabel awaitCall; // -  irei precisar no Controller
    private static JLabel numAwit; // -  irei precisar no Controller
    private static JLabel medics; // - irei precisar no Controller
    private static JLabel numMedics; // -  irei precisar no Controller
    
    private static JTextField inputSearch; // - rei precisar no Controller
    
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
    // Table
    private static JTable scheduleTable; // -  irei precisar no Controller
    
    
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
        
        panelDash.add(bodyMain, gbc);
        
        componentSearch();
        componentPanelInforCards();
        panelTable();
        
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

    }
    
    //=============================
    // Panel dos Cards Informativos
    //=============================
    private void componentPanelInforCards(){
        GridBagConstraints gContainer = new GridBagConstraints();
        
        container = new RoundedPanel(20);
        container.setBackground(Color.LIGHT_GRAY);
        container.setPreferredSize(new Dimension(0, 300));
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
    
    // =====================
    // Panel Tabela
    // =====================
    private void panelTable(){
        GridBagConstraints gContainerTable = new GridBagConstraints();
        
        panelToTable = new RoundedPanel(20);
        panelToTable.setBackground(Color.WHITE);
        panelToTable.setPreferredSize(new Dimension(0, 250));
        panelToTable.setLayout(new BorderLayout());
        
        // 🔥 ADICIONAR ESPAÇAMENTO INTERNO PARA O SCROLLPANE NÃO ENCOBRIR A BORDA
        panelToTable.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
    
        // ===== CONFIG DO CONTAINER =====
        gContainerTable.gridx = 0;
        gContainerTable.gridy = 2;
        gContainerTable.insets = new java.awt.Insets(5, 20, 20, 20); // Espaçamento externo (MARGEM)
        gContainerTable.weightx = 1; // Crescer horizontalmente
        gContainerTable.weighty = 0; // NÃO crescer verticalmente
        gContainerTable.fill = GridBagConstraints.HORIZONTAL;
        //===============================
        
        // ADD COMPONENTES
        bodyMain.add(panelToTable, gContainerTable);
        
        // ADD HERE COMPONENT TABLE
        tableSchedule();
        
    }
    
    // =================
    // Component Tabela
    // =================
    private void tableSchedule(){
        
        scheduleTable = new JTable();
        String[] columns = {"Horário", "Paciente", "Médico", "Confirmar Chegada"} ;
        DefaultTableModel model = new DefaultTableModel(columns, 0);
       
        scheduleTable = new JTable(model);
        
       // 🔥 CONFIGURAÇÕES PARA DEIXAR IGUAL AO DESIGN
       scheduleTable.setRowHeight(40);
       scheduleTable.setFont(new Font("Arial", Font.PLAIN, 14));
       scheduleTable.setForeground(Color.BLACK);
       scheduleTable.setBackground(Color.WHITE);
       scheduleTable.setGridColor(new Color(230, 230, 230));
       scheduleTable.setShowGrid(true);
       scheduleTable.setIntercellSpacing(new Dimension(1, 1));

       // Configurar cabeçalho
       scheduleTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
       scheduleTable.getTableHeader().setBackground(new Color(240, 240, 240));
       scheduleTable.getTableHeader().setForeground(Color.BLACK);
       scheduleTable.getTableHeader().setPreferredSize(new Dimension(0, 35));
       
       ((DefaultTableCellRenderer) scheduleTable.getTableHeader().getDefaultRenderer())
        .setHorizontalAlignment(JLabel.CENTER); // Centralizar o Titulo

       // Largura das colunas
       scheduleTable.getColumnModel().getColumn(0).setPreferredWidth(80);
       scheduleTable.getColumnModel().getColumn(1).setPreferredWidth(180);
       scheduleTable.getColumnModel().getColumn(2).setPreferredWidth(180);
       scheduleTable.getColumnModel().getColumn(3).setPreferredWidth(150);

       // Centralizar o texto das colunas
       DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
       centerRenderer.setHorizontalAlignment(JLabel.CENTER);
       scheduleTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
       scheduleTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

       // Coluna "Confirmar Chegada" com cor especial
       scheduleTable.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
           @Override
           public Component getTableCellRendererComponent(JTable table, Object value,
                   boolean isSelected, boolean hasFocus, int row, int column) {
               Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

               String texto = value.toString();
               if (texto.equals("Confirmado")) {
                   setForeground(new Color(0x458C45)); // Verde
                   setFont(new Font("Arial", Font.BOLD, 13));
               } else if (texto.equals("Confirmar?")) {
                   setForeground(new Color(0xE67E22)); // Laranja
                   setFont(new Font("Arial", Font.BOLD, 13));
               } else {
                   setForeground(Color.GRAY);
               }
               setHorizontalAlignment(JLabel.CENTER);
               return c;
           }
       });

       // JScrollPane
       JScrollPane scrollPane = new JScrollPane(scheduleTable);
       scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
       scrollPane.getViewport().setBackground(Color.WHITE);
       scrollPane.setBorder(BorderFactory.createEmptyBorder());

       panelToTable.add(scrollPane, BorderLayout.CENTER);
        
        
        
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
        
        Color pretoTransparente = new Color(0, 0, 0, 80); //  transparência

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
        
        java.awt.EventQueue.invokeLater(() -> {
            HomeDashboard hd = new HomeDashboard();
            
            new HomeDashboardController(hd);
            
            hd.setVisible(true);
        });
    }
    
    
    // =============================================
    // GETTERS para o Controller acessar os componentes
    // =============================================
    
    public static RoundedPanel getBtnInitial(){return btnInitial;}

    public static JLabel getLblInitial() {return lblInitial;}
    
    public static RoundedPanel getBtnRegister(){return btnRegister;}
    
    public static JLabel getLblRegister(){return lblRegister;}
    
    public static RoundedPanel getBtnScheduling(){return btnScheduling;}

    public static JLabel getLblScheduling() {return lblScheduling;}
    
    public static RoundedPanel getBtnClose(){return btnClose;}

    public static JLabel getLblClose() {return lblClose;}
    
    public static RoundedPanel getBtnHelp(){return btnHelp;}

    public static JLabel getLblHelp() {return lblHelp;}
    
    
    public static ShadowPanel getBtnNewFitting(){
        return btnNewFitting;
    }
    
    public static RoundedPanel getPlaceSearch(){
        return placeSearch;
    }
    
    public static JTextField getInputSearch(){
        return inputSearch;
    }
    
    public IconTextField getIconSearc(){
        return iconSearc;
    }
    
    public static JLabel getPeopleCard(int num){
        
        return switch (num) {
            case 1 -> peopleCard_1;
            case 2 -> peopleCard_2;
            default -> null;
        };
    } 
    
    public static JLabel getProfessionalCard(int profi){
        return switch (profi){
            case 1 -> peopleCard_1;
            case 2 -> peopleCard_2;
            default -> null;
        };
    }

    public static JLabel getPeopleDay() {
        return peopleDay;
    }

    public static JLabel getNumPeoleDay() {
        return numPeoleDay;
    }

    public static JLabel getConsultDay() {
        return consultDay;
    }

    public static JLabel getNumConsul() {
        return numConsul;
    }

    public static JLabel getAwaitCall() {
        return awaitCall;
    }

    public static JLabel getNumAwit() {
        return numAwit;
    }

    public static JLabel getMedics() {
        return medics;
    }

    public static JLabel getNumMedics() {
        return numMedics;
    }
    
    public JTable getScheduleTable(){
        return scheduleTable;
    }
}
