package br.com.senac.saudepro.gui;

import br.com.senac.saudepro.controller.SchedulingController;
import br.com.senac.saudepro.util.AuxiliaryMethod;
import br.com.senac.saudepro.util.RoundedPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Tela de Agendamento
 * @author bruno-teixeira
 */
public class Scheduling extends BaseView {
        private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Scheduling.class.getName());
        
        
    //=============================
    // Componentes principais - Serem Usados no [ CONTROLLER ]
    private static JPanel panelScheduling;
    
    private static RoundedPanel container;
    private static RoundedPanel containerButtons;
    private static RoundedPanel containerDocts;
    private static RoundedPanel boxDoctor_1;
    private static RoundedPanel boxDoctor_2;
    private static RoundedPanel boxDoctor_3;
    private static RoundedPanel boxDoctor_4;

    private static RoundedPanel panBtnNovoAgendamento;
    private static JLabel lblNovoAgendamento;
    private static RoundedPanel panListaAgendados;
    private static JLabel lblListaAgendados;  
    
    
    private static JLabel lbl_doctor_1;
    private static JLabel lbl_doctor_2;
    private static JLabel lbl_doctor_3;
    private static JLabel lbl_doctor_4;
    
    private static JLabel lbl_areaDoctor_1;
    private static JLabel lbl_areaDoctor_2;
    private static JLabel lbl_areaDoctor_3;
    private static JLabel lbl_areaDoctor_4;
    
    
    private static String _PARTH_IMG_CALENDER = "src/main/java/resources/img/calendar_month_background.png";
    //=============================    
    private static JLabel calendarioImagem;
    
    private final Color greenColor = new Color(0x458C45);
    
    public Scheduling() {
        
        initComponents();
        
        
    
    }
    
    
    private void initComponents(){
        String title = "Agendamento - SaúdePro";
        configurationFrame(title);
    
        panelScheduling = new JPanel();
        
        configurationPanelScreen(panelScheduling);
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
    protected void configurationPanelScreen(JPanel panel) {
        super.configurationPanelScreen(panel);
        
        
        createSideBarLeft(panelScheduling);
        
        createBodyMain(panelScheduling);
        
        createSideBarRigth(panelScheduling);
    }

    @Override
    protected void createSideBarLeft(JPanel panel) {
        super.createSideBarLeft(panel);
    }

    @Override
    protected void createBodyMain(JPanel panel) {
        super.createBodyMain(panel); 
        
        componentSearch();
        
        componentPanelBody();
    }

    //=============================
    // Panel Register Informations
    //=============================
    private void componentPanelBody() {
        GridBagConstraints gContainer = new GridBagConstraints();
        
        container = new RoundedPanel(20);
        container.setBackground(Color.WHITE);
        container.setPreferredSize(new Dimension(0, 450));
        container.setLayout(new GridBagLayout());
        
        // ===== CONFIG DO CONTAINER =====
        gContainer.gridx = 0;
        gContainer.gridy = 1;
        gContainer.insets = new java.awt.Insets(5, 20, 0, 20); // Espaçamento externo (MARGEM)
        gContainer.weightx = 1; // Crescer horizontalmente
        gContainer.weighty = 0; // NÃO crescer verticalmente
        gContainer.fill = GridBagConstraints.HORIZONTAL;
        //===============================
        
        bodyMain.add(container, gContainer);
        
        // Container dos Medicos
        containerOfDoctors();
        
        // aqui o elemento de dentro do body container
        containerBackground();

        // ADD COMPONENTES        
        containerButton();
    }
    
    // Container para posionar cada caixa do elemento medicos
    protected void containerOfDoctors(){
        GridBagConstraints d = new GridBagConstraints();
        
        // ===== CONFIG DO CONTAINER =====
        d.gridx = 0;
        d.gridy = 0;
        d.insets = new java.awt.Insets(5, 20, 0, 20); // Espaçamento externo (MARGEM)
        d.weightx = 1; // Crescer horizontalmente
        d.weighty = 0; // NÃO crescer verticalmente
        d.fill = GridBagConstraints.HORIZONTAL;
        
        containerDocts = new RoundedPanel();
        containerDocts.setBackground(null);
        containerDocts.setPreferredSize(new Dimension(0, 97));
        containerDocts.setBorder(null);
        containerDocts.setLayout(new GridBagLayout());
        
        container.add(containerDocts, d);
        
        
        boxDoctor_1 = new RoundedPanel();
        lbl_doctor_1 = new JLabel("Medico 1");
        lbl_areaDoctor_1 = new JLabel("especialista 1");
        boxDoctors(boxDoctor_1, lbl_doctor_1, lbl_areaDoctor_1, 0, 0);

        boxDoctor_2 = new RoundedPanel();
        lbl_doctor_2 = new JLabel("Medico 2");
        lbl_areaDoctor_2 = new JLabel("especialista 2");
        boxDoctors(boxDoctor_2, lbl_doctor_2, lbl_areaDoctor_2, 1, 0);
        

        boxDoctor_3 = new RoundedPanel();
        lbl_doctor_3 = new JLabel("Medico 3");
        lbl_areaDoctor_3 = new JLabel("especialista 3");
        boxDoctors(boxDoctor_3, lbl_doctor_3, lbl_areaDoctor_3, 2, 0);
        

        boxDoctor_4 = new RoundedPanel();
        lbl_doctor_4 = new JLabel("Medico 4");
        lbl_areaDoctor_4 = new JLabel("especialista 4");
        boxDoctors(boxDoctor_4, lbl_doctor_4, lbl_areaDoctor_4, 3, 0);
                
        
        
        
        
    }
    
    // create of the box for doctors
    protected void boxDoctors(RoundedPanel panel, JLabel prof, JLabel area, int c, int l){
        GridBagConstraints d = new GridBagConstraints();
        
        // ===== CONFIG DO CONTAINER =====
        d.gridx = c;
        d.gridy = l;
        d.insets = new java.awt.Insets(20, 20, 20, 20); // Espaçamento externo (MARGEM)
        d.weightx = 0; // Crescer horizontalmente
        d.weighty = 1; // NÃO crescer verticalmente
        d.fill = GridBagConstraints.VERTICAL;
        
        panel.setPreferredSize(new Dimension(100, 0));
        panel.setBackground(null);
        
        prof.setFont(new Font("Arial", Font.BOLD, 16));
        prof.setForeground(Color.BLACK);
        prof.setBounds(0, 0, 150, 25);
        
        panel.add(prof);
        
        area.setFont(new Font("Arial", Font.PLAIN, 12));
        area.setForeground(Color.LIGHT_GRAY);
        area.setBounds(0, 0, 150, 25);
        
        panel.add(area);
        
        containerDocts.add(panel, d);
        
    }
    
    
    // Container de background
    protected void containerBackground(){
        GridBagConstraints b = new GridBagConstraints();

        b.gridx = 0;
        b.gridy = 1;
        b.insets = new java.awt.Insets(0, 20, 10, 20);
        b.weightx = 1;
        b.weighty = 1;
        b.fill = GridBagConstraints.BOTH;

        JPanel cont = new JPanel();
        cont.setLayout(new GridBagLayout());
        cont.setBackground(null);

         // Inicializar a imagem
        ImageIcon icon = new ImageIcon(_PARTH_IMG_CALENDER);
        Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        calendarioImagem = new JLabel(new ImageIcon(img));
        calendarioImagem.setHorizontalAlignment(JLabel.CENTER);
         
         
        // IMAGEM ALINHAMENTO
        GridBagConstraints imgConstraints = new GridBagConstraints();
        imgConstraints.gridx = 0;
        imgConstraints.gridy = 0;
        imgConstraints.weightx = 1;
        imgConstraints.weighty = 1;
        imgConstraints.anchor = GridBagConstraints.CENTER;

        cont.add(calendarioImagem, imgConstraints);

        // Texto abaixo da imagem
        JLabel lblInfo = new JLabel("Selecione um Medico para novo agendamento ou ver lista de agendados.");
        lblInfo.setFont(new Font("Arial", Font.PLAIN, 20));
        lblInfo.setForeground(Color.GRAY);

        GridBagConstraints textConstraints = new GridBagConstraints();
        textConstraints.gridx = 0;
        textConstraints.gridy = 1;
        textConstraints.weightx = 0;
        textConstraints.anchor = GridBagConstraints.CENTER;
        textConstraints.insets = new Insets(5, 0, 100, 0);

        cont.add(lblInfo, textConstraints);

        container.add(cont, b);
    }
    
    
    
   // ==== CONTAINER BTNS =====
    protected void containerButton(){
        GridBagConstraints g = new GridBagConstraints();
        
        containerButtons = new RoundedPanel(20);
        containerButtons.setBackground(Color.LIGHT_GRAY);
        containerButtons.setPreferredSize(new Dimension(0, 100));
        containerButtons.setBorder(null);
        containerButtons.setLayout(new GridBagLayout());
        
        // ===== CONFIG DO CONTAINER =====
        g.gridx = 0;
        g.gridy = 2;
        g.insets = new java.awt.Insets(5, 20, 0, 20); // Espaçamento externo (MARGEM)
        g.weightx = 1; // Crescer horizontalmente
        g.weighty = 0; // NÃO crescer verticalmente
        g.fill = GridBagConstraints.HORIZONTAL;
        
        bodyMain.add(containerButtons, g);
        
        // ADD BTNs
        panBtnNovoAgendamento = new RoundedPanel(10);
        lblNovoAgendamento = new JLabel("Novo Agendamento");
        createButtoms(0, 0, 0, 0, 0, 0, panBtnNovoAgendamento, lblNovoAgendamento, Color.DARK_GRAY);
        
        panListaAgendados = new RoundedPanel(10);
        lblListaAgendados = new JLabel("Lista de Agendados");
        createButtoms(1, 0, 0, 0, 0, 0, panListaAgendados, lblListaAgendados, Color.DARK_GRAY);
        
        // ===== MENSAGEM ALERT
        GridBagConstraints m = new GridBagConstraints();
        
        JLabel mgs = new JLabel("Selecione um Medico para novo agendamento ou ver lista de agendados.");
        mgs.setFont(new Font("Arial", Font.PLAIN, 14));
        mgs.setForeground(Color.BLACK);
        mgs.setBounds(0, 0, 0, 34);
        
        // ===== CONFIG DO MESSAGE =====
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
        gb.weightx = 1; // NÃO Crescer horizontalmente
        gb.weighty = v; // NÃO crescer verticalmente
        gb.fill = GridBagConstraints.HORIZONTAL;
        
        panel.setBackground(bColor);
        panel.setPreferredSize(new Dimension(200, 50)); // altura fixa
        panel.setLayout(new GridBagLayout());
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
    
    
    
    @Override
    protected void createSideBarRigth(JPanel panel) {
        super.createSideBarRigth(panel);
        
        JLabel l = new JLabel();
        AuxiliaryMethod.showDateActual(sideBarRight, greenColor, l);
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
            Scheduling s = new Scheduling();
            
            new SchedulingController(s);
            
            s.setVisible(true);
        });
    }
    
   
    
    public RoundedPanel getAllPanels(int selection){
        return switch (selection) {
            case 7 -> panBtnNovoAgendamento;
            case 8 -> panListaAgendados;
            default -> null;
        };
    }
}
