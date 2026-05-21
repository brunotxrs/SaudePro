package br.com.senac.saudepro.gui;

import br.com.senac.saudepro.controller.HomeDashboardController;
import br.com.senac.saudepro.util.AuxiliaryMethod;
import br.com.senac.saudepro.util.IconTextField;
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
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * Dashboard - Lista de consultas do dia com horário e médico
 * @author bruno-teixeira
 */
public class HomeDashboard extends BaseView {
    // - Gerar comentario pra isso! 
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(HomeDashboard.class.getName());
    
    //=============================
    // Componentes principais - Serem Usados no [ CONTROLLER ]
    private static JPanel panelDash;

    private static ShadowPanel btnNewFitting; // <- btn [ Novo Encaixe ]
    private static RoundedPanel container; // <- panel para cards informaçoes - Nao irei precisar no Controller
    private static RoundedPanel panelToTable; // <-  Panel para a Tabela - Nao irei precisar no Controller
    

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
    
    //=============================
    // Caminhos das imagem Icons
    private static final String _parthIconAdd = "src/main/java/resources/img/addBlack.png";
    //=============================    
    // Ícones
    private final IconTextField icoAdd = new IconTextField(_parthIconAdd, 30, 30);
    
    //=============================    
    // Table
    private static JTable scheduleTable; // -  irei precisar no Controller
// -  irei precisar no Controller
    
    //=============================    
    // Cores
    private final Color greenColor = new Color(0x458C45);
    //=============================    
    // Elements

    
    private static final GridBagLayout gLayout = new GridBagLayout();
    private static GridBagConstraints gbc;
    
    public HomeDashboard() {
        
        initComponents();
    }
    
    //=============================
    // Componente de inicializaçao de todos os componentes
    //=============================    
    private void initComponents(){
        String title = "Dashboard - SaúdePro";
        configurationFrame(title); // instaciando o metodo.
        
        panelDash = new JPanel();
        
        configurationPanelScreen(panelDash); // chamando o panel
    }
    //=============================    
    // configuraçao o Jrame
    //=============================    
    @Override
    protected void configurationFrame(String title) {
        super.configurationFrame(title);
        
    }
    
    
    //=============================    
    // criando o Painel
    //=============================    
    @Override    
    protected void configurationPanelScreen(JPanel panel){
        super.configurationPanelScreen(panel); 
        
        createSideBarLeft(panelDash);
        
        createBodyMain(panelDash);
        createSideBarRigth(panelDash);
    }

    //=============================
    // Componente - SideBar_Left
    //=============================
    @Override    
    protected void createSideBarLeft(JPanel panel){
        super.createSideBarLeft(panel); 
    }

    //=============================
    //Componente Main
    //=============================

    @Override
    protected void createBodyMain(JPanel panel) {
        super.createBodyMain(panel); 
        
        componentSearch();
        componentPanelInforCards();
        panelTable();
    }
        
    //=============================
    // Metodo de Search
    //=============================
    @Override    
    protected void componentSearch(){
        super.componentSearch(); 

    }

    
    //=============================
    // Panel dos Cards Informativos
    //=============================
    private void componentPanelInforCards() {
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
    @Override
    protected void createSideBarRigth(JPanel panel){
        super.createSideBarRigth(panel);
    
        // add more components
        JLabel lblNexts = new JLabel("Próximos Atendimentos");
        AuxiliaryMethod.showDateActual(sideBarRight, greenColor, lblNexts);
        
        createCards(sideBarRight, peopleCard_1, professionalCard_1, 20, 165);
        createCards(sideBarRight, peopleCard_2, professionalCard_2, 20, 314);
        
        // Componente Novo Encaixe
        
        // Color for shadow
        Color boxShadow = new Color(0, 0, 0, 10); // cor com nivel de transparencia
        btnNewFitting = new ShadowPanel(8, 80, boxShadow);
        newFitting(btnNewFitting);
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
    private void newFitting(ShadowPanel shadowPanel){
        
        
        shadowPanel.setLayout(gLayout);
        shadowPanel.setBounds(115, 500, 80, 80);
        shadowPanel.setBackground(Color.WHITE);
        
        icoAdd.setPreferredSize(new Dimension(40, 50));
        icoAdd.setBackground(null);
        
        shadowPanel.add(icoAdd);
        
        JLabel label = new JLabel("Novo Encaixe");
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        label.setForeground(Color.BLACK);
        label.setBounds(0, 575, 300, 52);
        label.setHorizontalAlignment(JLabel.CENTER);
        

        sideBarRight.add(shadowPanel);
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
        
    public static ShadowPanel getBtnNewFitting(){
        return btnNewFitting;
    }
    
    public String getAllParthIconsDash(int num){
        
        return switch (num){
            case 1 -> _parthIconAdd;
            default -> null;
        };
    }
    
    
    // metodo para pegar todos os icons e reduzir code 
    public IconTextField getAllInconsDash(int num){
        
        return switch (num){
            case 1 -> icoAdd;
            default -> null;
                
        };
        
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
