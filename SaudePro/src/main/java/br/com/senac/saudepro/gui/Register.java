package br.com.senac.saudepro.gui;

import br.com.senac.saudepro.controller.RegisterController;
import br.com.senac.saudepro.util.RoundedPanel;
import br.com.senac.saudepro.util.ShadowPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Register - view area para registro de pacientes
 * @author bruno-teixeira
 */
public class Register extends BaseView {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Register.class.getName());
    
    //=============================
    // Componentes principais - Serem Usados no [ CONTROLLER ]
    private static JPanel panelRegister;
    
    private static RoundedPanel container; // <- panel para cards informaçoes - Nao irei precisar no Controller*/
    private static RoundedPanel containerButtons;
    private static RoundedPanel cont;
    
    
    private static RoundedPanel panName;
    private static JTextField inputName;
    private static RoundedPanel panCpf;
    private static JTextField inputCpf;
    private static RoundedPanel panDate;
    private static JTextField inputDate;
    
    private static RoundedPanel panPhone;
    private static JTextField inputPhone;
    private static RoundedPanel panE_mail;
    private static JTextField inputE_mail;
    private static RoundedPanel panDetails;
    
    private static JTextArea inputDetails;


    private static RoundedPanel panBtnCadastrar;
    private static JLabel lblCadastrar;
    private static RoundedPanel panBtnAtualizar;
    private static JLabel lblAtualizar;   
    private static RoundedPanel panBtnDeletar;
    private static JLabel lblDeletar;

    private static ShadowPanel cardPanel_1;
    private static ShadowPanel cardPanel_2;
    private static ShadowPanel cardPanel_3;
    private static ShadowPanel cardPanel_4;
    private static ShadowPanel cardPanel_5;

                    
    
    private static JLabel peopleCard_1; // - irei precisar no Controller
    private static JLabel peopleCard_2; // -  irei precisar no Controller
    private static JLabel peopleCard_3; // - irei precisar no Controller
    private static JLabel peopleCard_4; // -  irei precisar no Controller
    private static JLabel peopleCard_5; // - irei precisar no Controller
    
    private static final GridBagLayout gLayout = new GridBagLayout();
    private static GridBagConstraints gbc;
    
    
    //=============================    
    // Cores
    private final Color greenColor = new Color(0x458C45);
    
    public Register() throws HeadlessException {
    
        initComponents();
    }
    
    private void initComponents(){
        String title = "Registro - SaúdePro";
        
        configurationFrame(title);
        
        panelRegister = new JPanel();
        
        configurationPanelScreen(panelRegister);
    }
    
    //=============================    
    // configuraçao o Jrame
    //=============================
    
    @Override
    protected void configurationFrame(String title){
        super.configurationFrame(title); 
    }

    //=============================    
    // criando o Painel
    //=============================
    
    @Override
    protected void configurationPanelScreen(JPanel panel){

        super.configurationPanelScreen(panel);
        
        createSideBarLeft(panelRegister);
        
        createBodyMain(panelRegister);
        
        createSideBarRigth(panelRegister);

    }
    
    
    //=============================
    // Componente - SideBar_Left
    //=============================
    @Override
    protected void createSideBarLeft(JPanel panel){
        super.createSideBarLeft(panel);
        
    }

    //=============================
    // Metodo de Search
    //=============================
    @Override
    protected void componentSearch(){
        super.componentSearch();
        
    }
    
    //=============================
    //Componente Main
    //=============================    
    @Override
    protected void createBodyMain(JPanel panel){
        super.createBodyMain(panel); 
        
        componentSearch();
        
        componentPanelInforRegister();
        
        // ADD BTNS
        containerButton();
        
    }

//=============================
    // Panel Register Informations
    //=============================
    private void componentPanelInforRegister() {
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
        
        bodyMain.add(container, gContainer);

        // ADD COMPONENTES        
        placeRegister();
    }
    
    
    // ===== MEOTOD DE COMPLEMENTO DO BODY =====
    private void placeRegister(){
        // ===== LABEL NAME ===== 
        JLabel nome = new JLabel("Nome:*");
        createLabel(0, 0, 1, 0, nome);
        
        // ===== INPUT NAME =====
        panName = new RoundedPanel(15);
        inputName = new JTextField();
        createPlaceInput(0, 1, 1, 0, panName, 0, 40, inputName);
        
        // ===== CALL O CONTAINER DO INPUTS MENORES =====
        containerOthersInputs();
        
        // ===== LABEL OBSERVAÇOES =====
        JLabel details = new JLabel("Observações:");
        createLabel(0, 3, 1, 0, details);
        
        // ===== INPUT OBSERVAÇOES =====
        panDetails = new RoundedPanel(15);
        inputDetails = new JTextArea(5, 5);
        createPlaceInputDetails(0, 4, 1, 0, panDetails, 0, 140, inputDetails);
    }
        
    // Metodo para inputs curtos, devido o grid quebrar ao add eles 
    protected void containerOthersInputs(){
        GridBagConstraints g = new GridBagConstraints();
        
        cont = new RoundedPanel(20);
        cont.setBackground(Color.WHITE);
        /*        cont.setPreferredSize(new Dimension(0, 450));*/
        cont.setBorder(null);
        cont.setLayout(gLayout);
        
        // ===== CONFIG DO CONTAINER =====
        g.gridx = 0;
        g.gridy = 2;
        g.insets = new java.awt.Insets(0, 0, 0, 0); // Espaçamento externo (MARGEM)
        g.weightx = 1; // Crescer horizontalmente
        g.weighty = 0; // NÃO crescer verticalmente
        g.fill = GridBagConstraints.HORIZONTAL;
        
        container.add(cont, g);
        
        // ===== LABEL CPF =====
        JLabel cpf = new JLabel("CPF:*");
        createLabelSmall(0, 0, 1, 0, cpf);
        
        // ===== INPUT CPF =====
        panCpf = new RoundedPanel(15);
        inputCpf = new JTextField();
        
        createPlaceSmallInput(0, 1, 0, 0, 1, 0, panCpf, inputCpf);
        
        // ===== LABEL DATA =====
        JLabel lblDate = new JLabel("Data de Nascimento:*");
        createLabelSmall(1, 0, 1, 0, lblDate);
        
        // ===== INPUT DATA =====
        panDate = new RoundedPanel(15);
        inputDate = new JTextField();
        createPlaceSmallInput(1, 1, 0, 0, 1, 0, panDate, inputDate);
        
        // ===== LABEL PHONE =====
        JLabel phone = new JLabel("Telefone:");
        createLabelSmall(0, 2, 1, 0, phone);
        
        // ===== INPUT PHONE =====
        panPhone = new RoundedPanel(15);
        inputPhone = new JTextField();
        createPlaceSmallInput(0, 3, 0, 0, 1, 0, panPhone, inputPhone);
        
        // ===== LABEL E-MAIL =====
        JLabel lblE_mail = new JLabel("E-mail:");
        createLabelSmall(1, 2, 1, 0, lblE_mail);

        // ===== INPUT E-MAIL =====        
        panE_mail = new RoundedPanel(15);
        inputE_mail = new JTextField();
        createPlaceSmallInput(1, 3, 0, 0, 1, 0, panE_mail, inputE_mail);
        
        
    }
    
    //Metodos para o input maiores onde ocuda todas as colunas do grid 
    protected void createLabel(int c, int l, int h, int v, JLabel label){
        GridBagConstraints gb = new GridBagConstraints();

        gb.gridx = c; // Colo
        gb.gridy = l; // lin
        gb.weightx = h; // NÃO crescer horizontalmente
        gb.weighty = v; // NÃO crescer verticalmente
        gb.insets = new Insets(5, 15, 10, 10); // espaçamento interno
        gb.anchor = GridBagConstraints.WEST;
        
        label.setFont(new Font("Arial", Font.PLAIN, 17));
        label.setForeground(Color.GRAY);
        label.setBounds(0, 40, 0, 34);
        
        container.add(label, gb);
    }
    protected void createPlaceInput(int c, int l, int h, int v, RoundedPanel panel, int d, int a, JTextField textField){
        GridBagConstraints gb = new GridBagConstraints();
        gb.gridx = c;
        gb.gridy = l;
        gb.insets = new java.awt.Insets(0, 20, 0, 20); // Espaçamento externo (MARGEM)
        gb.weightx = h; // Crescer horizontalmente
        gb.weighty = v; // NÃO crescer verticalmente
        gb.fill = GridBagConstraints.HORIZONTAL;        
        
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setPreferredSize(new Dimension(d, a)); // altura fixa
        panel.setLayout(gLayout);
        panel.setBorder(null);
        
        GridBagConstraints gb1 = new GridBagConstraints();
        gb1.gridx = c; // c
        gb1.gridy = l; // l
        gb1.insets = new java.awt.Insets(0, 20, 0, 20); // Espaçamento externo (MARGEM)
        gb1.weightx = h; // Crescer horizontalmente
        gb1.weighty = v; // NÃO crescer verticalmente
        gb1.fill = GridBagConstraints.HORIZONTAL;
        
        
        textField.setBorder(null);
        textField.setBackground(Color.LIGHT_GRAY);
        textField.setFont(new Font("Arial", Font.PLAIN, 18));
        textField.setHorizontalAlignment(JTextField.LEFT);
        
        panel.add(textField, gb1);
        
        container.add(panel, gb);
        
    }
    
    // Metodo para inputs curtos onde divido o grid
    protected void createLabelSmall(int c, int l, int h, int v, JLabel label){
        GridBagConstraints gb = new GridBagConstraints();

        gb.gridx = c; // Colo
        gb.gridy = l; // lin
        gb.weightx = h; // NÃO crescer horizontalmente
        gb.weighty = v; // NÃO crescer verticalmente
        gb.insets = new Insets(10, 15, 10, 10); // espaçamento interno
        gb.anchor = GridBagConstraints.WEST;
        
        label.setFont(new Font("Arial", Font.PLAIN, 17));
        label.setForeground(Color.GRAY);
        label.setBounds(0, 40, 0, 34);
        
        cont.add(label, gb);
    }
    protected void createPlaceSmallInput(int c, int l, int h, int v, int h1, int v1, RoundedPanel panel, JTextField input){
        
        GridBagConstraints gb = new GridBagConstraints();
        gb.gridx = c; // c
        gb.gridy = l; // l
        gb.insets = new java.awt.Insets(0, 20, 0, 20); // Espaçamento externo (MARGEM)
        gb.weightx = h; // NÃO Crescer horizontalmente
        gb.weighty = v; // NÃO crescer verticalmente
        gb.fill = GridBagConstraints.HORIZONTAL;
        
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setPreferredSize(new Dimension(300, 40)); // altura fixa
        panel.setLayout(gLayout);
        panel.setBorder(null);
        
        
        GridBagConstraints gb1 = new GridBagConstraints();
        gb1.gridx = c; // c
        gb1.gridy = l; // l
        gb1.insets = new java.awt.Insets(0, 20, 0, 20); // Espaçamento externo (MARGEM)
        gb1.weightx = h1; // Crescer horizontalmente
        gb1.weighty = v1; // NÃO crescer verticalmente
        gb1.fill = GridBagConstraints.HORIZONTAL;
        
        input.setBorder(null);
        input.setBackground(Color.LIGHT_GRAY);
        input.setFont(new Font("Arial", Font.PLAIN, 18));
        input.setHorizontalAlignment(JTextField.LEFT);
        
        // ===== ICON =====
        /*        GridBagConstraints gbcIcon = new GridBagConstraints();
        gbcIcon.gridx = c;
        gbcIcon.gridy = l;
        gbcIcon.insets = new Insets(0, 0, 0, 10); // espaçamento interno
        gbcIcon.anchor = GridBagConstraints.WEST;
        */
        
        panel.add(input, gb1);
        /*       panel.add(itf, gbcIcon);*/
        
        cont.add(panel, gb);
    }
    
    // Metodo especifico para observaçoes
    protected void createPlaceInputDetails(int c, int l, int h, int v, RoundedPanel panel, int d, int a, JTextArea textArea){
        GridBagConstraints gb = new GridBagConstraints();
        gb.gridx = c;
        gb.gridy = l;
        gb.insets = new java.awt.Insets(0, 20, 0, 20); // Espaçamento externo (MARGEM)
        gb.weightx = h; // Crescer horizontalmente
        gb.weighty = v; // NÃO crescer verticalmente
        gb.fill = GridBagConstraints.HORIZONTAL;        
        
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setPreferredSize(new Dimension(d, a)); // altura fixa
        panel.setLayout(gLayout);
        panel.setBorder(null);
        
        GridBagConstraints gb1 = new GridBagConstraints();
        gb1.gridx = c; // c
        gb1.gridy = l; // l
        gb1.insets = new java.awt.Insets(0, 20, 0, 20); // Espaçamento externo (MARGEM)
        gb1.weightx = h; // Crescer horizontalmente
        gb1.weighty = v; // NÃO crescer verticalmente
        gb1.fill = GridBagConstraints.HORIZONTAL;
        
        
        // Configurar JTextArea
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(null);
        textArea.setBackground(Color.LIGHT_GRAY);
        textArea.setFont(new Font("Arial", Font.PLAIN, 18));
        
        // Colocar dentro de um JScrollPane (rolagem se necessário)
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setBorder(null);
        scroll.setBackground(Color.LIGHT_GRAY);

        panel.add(scroll, gb1);
        
        container.add(panel, gb);
        
    }
    
    
    // ==== CONTAINER BTNS =====
    protected void containerButton(){
        GridBagConstraints g = new GridBagConstraints();
        
        containerButtons = new RoundedPanel(20);
        containerButtons.setBackground(Color.LIGHT_GRAY);
        containerButtons.setPreferredSize(new Dimension(0, 100));
        containerButtons.setBorder(null);
        containerButtons.setLayout(gLayout);
        
        // ===== CONFIG DO CONTAINER =====
        g.gridx = 0;
        g.gridy = 2;
        g.insets = new java.awt.Insets(5, 20, 0, 20); // Espaçamento externo (MARGEM)
        g.weightx = 1; // Crescer horizontalmente
        g.weighty = 0; // NÃO crescer verticalmente
        g.fill = GridBagConstraints.HORIZONTAL;
        
        bodyMain.add(containerButtons, g);
        
        // ADD BTNs
        panBtnCadastrar = new RoundedPanel(10);
        lblCadastrar = new JLabel("Cadastrar");
        createButtoms(0, 0, 0, 0, 0, 0, panBtnCadastrar, lblCadastrar, new Color(0x7ED348));
        
        panBtnAtualizar = new RoundedPanel(10);
        lblAtualizar = new JLabel("Atualizar");
        Color blueC = new Color(0x4299E1);
        createButtoms(1, 0, 0, 0, 0, 0, panBtnAtualizar, lblAtualizar, blueC);
        
        panBtnDeletar = new RoundedPanel(10);
        lblDeletar = new JLabel("Deletar");
        Color orangeC = new Color(0xF6AD55);
        createButtoms(2, 0, 0, 0, 0, 0, panBtnDeletar, lblDeletar, orangeC);
        
        
        // ===== MENSAGEM ALERT
        GridBagConstraints m = new GridBagConstraints();
        
        JLabel mgs = new JLabel("Campos marcados com (*) são obrigatórios.");
        mgs.setFont(new Font("Arial", Font.PLAIN, 8));
        mgs.setForeground(Color.BLACK);
        mgs.setBounds(0, 0, 0, 34);
        
        // ===== CONFIG DO CONTAINER =====
        m.gridx = 0;
        m.gridy = 3;
        m.insets = new java.awt.Insets(5, 20, 0, 20); // Espaçamento externo (MARGEM)
        m.weightx = 1; // Crescer horizontalmente
        m.weighty = 0; // NÃO crescer verticalmente
        m.fill = GridBagConstraints.HORIZONTAL;
        
        bodyMain.add(mgs, m);
        
    }
    
    // ====== ELEMENTS BTNS
    protected void createButtoms(int c, int l, int h, int v, int h1, int v1, RoundedPanel panel, JLabel label, Color bColor){
        
        GridBagConstraints gb = new GridBagConstraints();
        gb.gridx = c; // c
        gb.gridy = l; // l
        gb.insets = new java.awt.Insets(0, 20, 0, 20); // Espaçamento externo (MARGEM)
        gb.weightx = h; // NÃO Crescer horizontalmente
        gb.weighty = v; // NÃO crescer verticalmente
        gb.fill = GridBagConstraints.HORIZONTAL;
        
        panel.setBackground(bColor);
        panel.setPreferredSize(new Dimension(200, 50)); // altura fixa
        panel.setLayout(gLayout);
        panel.setBorder(null);
        
        
        GridBagConstraints gb1 = new GridBagConstraints();
        gb1.gridx = c; // c
        gb1.gridy = l; // l
        gb1.insets = new java.awt.Insets(0, 20, 0, 20); // Espaçamento externo (MARGEM)
        gb1.weightx = h1; // Crescer horizontalmente
        gb1.weighty = v1; // NÃO crescer verticalmente
        gb1.fill = GridBagConstraints.HORIZONTAL;
        
        label.setFont(new Font("Arial", Font.BOLD, 17));
        label.setForeground(Color.WHITE);
        label.setBounds(0, 40, 0, 34);
        
        
        panel.add(label, gb1);
        
        containerButtons.add(panel, gb);
    }
    
    //=============================
    // Componente SideBar_Rigth
    //============================= 
    @Override
    protected void createSideBarRigth(JPanel panel){
        super.createSideBarRigth(panel); 
        
        
        peopleCard_1 = new JLabel("Paciente 1");
        peopleCard_2 = new JLabel("Paciente 2");
        peopleCard_3 = new JLabel("Paciente 3");
        peopleCard_4 = new JLabel("Paciente 4");
        peopleCard_5 = new JLabel("Paciente 5");
        
        // Color for shadow
        Color boxShadow = new Color(0, 0, 0, 80);
        
        cardPanel_1 = new ShadowPanel(8, 15,boxShadow);
        cardPanel_2 = new ShadowPanel(8, 15,boxShadow);
        cardPanel_3 = new ShadowPanel(8, 15,boxShadow);
        cardPanel_4 = new ShadowPanel(8, 15,boxShadow);
        cardPanel_5 = new ShadowPanel(8, 15,boxShadow);
        
        createCards(sideBarRight, cardPanel_1, peopleCard_1, 20, 145);
        createCards(sideBarRight, cardPanel_2, peopleCard_2, 20, 245);
        createCards(sideBarRight, cardPanel_3, peopleCard_3, 20, 345);
        createCards(sideBarRight, cardPanel_4, peopleCard_4, 20, 445);
        createCards(sideBarRight, cardPanel_5, peopleCard_5, 20, 545);
    }
    
    //=============================    
    // Cards de Proximos Atendimentos - Component Sidebar_Rigth
    //=============================
    private void createCards(JPanel main, JPanel cardPanel,  JLabel u, int x, int y){
        JLabel label = new JLabel("Pacientes Recentes");
        label.setFont(new Font("Arial", Font.PLAIN, 17));
        label.setForeground(Color.BLACK);
        label.setBounds(0, 40, 300, 34);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        
        
        Color pretoTransparente = new Color(0, 0, 0, 80); //  transparência

        // Branco Gelo
        Color brancoGelo = new Color(0xF0F4F8);
        

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
        
        main.add(label);
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
            
            new RegisterController(register);
            
            register.setVisible(true);
        });
    }
    
    //=============
    // GETs
    //=============
    
    //Panel de Input a Btns
    public RoundedPanel getAllPanels(int selection){
      
       return switch (selection){
           case 1 -> panName;
           case 2 -> panCpf;
           case 3 -> panDate;
           case 4 -> panPhone;
           case 5 -> panE_mail;
           case 6 -> panDetails;
           case 7 -> panBtnCadastrar;
           case 8 -> panBtnAtualizar;
           case 9 -> panBtnDeletar;
           default -> null;
       };
       
    };
    
    // Input
    public JTextField getAllInputs(int select){
        return switch (select){
           case 1 -> inputName;
           case 2 -> inputCpf;
           case 3 -> inputDate;
           case 4 -> inputPhone;
           case 5 -> inputE_mail;
           default -> null;
       };
    }
    
    // Detalhes
    public JTextArea getInpuintDetails(){return inputDetails;}
    
    // Todos os label
    public JLabel getAllLabels(int i){
        return switch (i){
           case 1 -> lblCadastrar;
           case 2 -> lblAtualizar;
           case 3 -> lblDeletar;
           default -> null;
       };
    }
    
    public JLabel getCardsPeoples(int i){
        return switch (i){
           case 1 -> peopleCard_1;
           case 2 -> peopleCard_2;
           case 3 -> peopleCard_3;
           case 4 -> peopleCard_4;
           case 5 -> peopleCard_5;               
           default -> null;
       };
    }
    
    public ShadowPanel getCardPanel(int i){
        return switch (i){
           case 1 -> cardPanel_1;
           case 2 -> cardPanel_2;
           case 3 -> cardPanel_3;
           case 4 -> cardPanel_4;
           case 5 -> cardPanel_5;               
           default -> null;
       };
    
    }
}
