package br.com.senac.saudepro.gui;

import br.com.senac.saudepro.controller.SchedulingController;
import br.com.senac.saudepro.model.Medico;
import br.com.senac.saudepro.util.AuxiliaryMethod;
import br.com.senac.saudepro.util.CompactCalendarPanel;
import br.com.senac.saudepro.util.RoundedPanel;
import br.com.senac.saudepro.util.ShadowPanel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Scheduling extends BaseView {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Scheduling.class.getName());
    
    // Componentes principais
    private static JPanel panelScheduling;
    private static RoundedPanel container;
    private static RoundedPanel containerButtons;
    private static JPanel containerDoctors;
    
    // Componentes dinâmicos para médicos
    private JPanel panelCardsContainer;      // Container dos cards com FlowLayout
    private JScrollPane scrollPaneMedicos;   // Scroll horizontal
    private List<ShadowPanel> cardsMedicos = new ArrayList<>();     // Lista dinâmica de cards
    private List<JLabel> labelsNomes = new ArrayList<>();           // Lista de nomes
    private List<JLabel> labelsEspecialidades = new ArrayList<>();  // Lista de especialidades
    private List<Integer> medicosIds = new ArrayList<>();           // IDs dos médicos
    
    // Botões
    private static RoundedPanel panBtnNovoAgendamento;
    private static JLabel lblNovoAgendamento;
    private static RoundedPanel panListaAgendados;
    private static JLabel lblListaAgendados;
    
    private static JLabel doctorSelected;
    private static JLabel doctorAreaSelected;
    
    private static String _PARTH_IMG_CALENDER = "src/main/java/resources/img/calendar_month_background.png";
    private static JLabel calendarioImagem;
    private final Color greenColor = new Color(0x458C45);
    
    public Scheduling() {
        initComponents();
    }
    
    private void initComponents() {
        String title = "Agendamento - SaúdePro";
        configurationFrame(title);
        panelScheduling = new JPanel();
        configurationPanelScreen(panelScheduling);
    }
    
    @Override
    protected void configurationFrame(String title) {
        super.configurationFrame(title);
    }
    
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
    
    private void componentPanelBody() {
        GridBagConstraints gContainer = new GridBagConstraints();
        
        container = new RoundedPanel(20);
        container.setBackground(Color.WHITE);
        container.setPreferredSize(new Dimension(0, 450));
        container.setLayout(new GridBagLayout());
        
        gContainer.gridx = 0;
        gContainer.gridy = 1;
        gContainer.insets = new Insets(5, 20, 0, 20);
        gContainer.weightx = 1;
        gContainer.weighty = 0;
        gContainer.fill = GridBagConstraints.HORIZONTAL;
        
        bodyMain.add(container, gContainer);
        
        // Container com scroll horizontal para médicos
        containerOfDoctorsDinamico();
        
        // Container de background (calendário)
        containerBackground();
        
        // Botões
        containerButton();
    }
    
    /**
     * Cria container com scroll horizontal para os cards de médicos
     */
    private void containerOfDoctorsDinamico() {
        GridBagConstraints d = new GridBagConstraints();
        d.gridx = 0;
        d.gridy = 0;
        d.insets = new Insets(5, 20, 5, 20);
        d.weightx = 1;
        d.weighty = 0;
        d.fill = GridBagConstraints.HORIZONTAL;
        
        // Container principal dos cards
        panelCardsContainer = new JPanel();
        panelCardsContainer.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
        panelCardsContainer.setBackground(Color.WHITE);
        panelCardsContainer.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // ScrollPane horizontal
        scrollPaneMedicos = new JScrollPane(panelCardsContainer);
        scrollPaneMedicos.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneMedicos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPaneMedicos.setBorder(null);
        scrollPaneMedicos.setPreferredSize(new Dimension(0, 130));
        scrollPaneMedicos.getHorizontalScrollBar().setUnitIncrement(20);
        
        // Estilizar a barra
        JScrollBar barra = scrollPaneMedicos.getHorizontalScrollBar();
        barra.setPreferredSize(new Dimension(0, 8));
        barra.setBackground(new Color(0xF0F0F0));
        barra.setForeground(new Color(0x7ED348));
        barra.setBorder(BorderFactory.createEmptyBorder());

        scrollPaneMedicos.setPreferredSize(new Dimension(0, 140));
        scrollPaneMedicos.getHorizontalScrollBar().setUnitIncrement(20);
        
        container.add(scrollPaneMedicos, d);
    }
    
    /**
     * Cria cards dinamicamente baseado na lista de médicos
     */
    public void criarCardsMedicos(List<br.com.senac.saudepro.model.Medico> medicos) {
        // Limpar cards antigos
        cardsMedicos.clear();
        labelsNomes.clear();
        labelsEspecialidades.clear();
        medicosIds.clear();
        panelCardsContainer.removeAll();
        
        if (medicos == null || medicos.isEmpty()) {
            // Mostrar mensagem quando não há médicos
            JLabel msgLabel = new JLabel("Nenhum médico cadastrado no sistema");
            msgLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            msgLabel.setForeground(Color.GRAY);
            panelCardsContainer.add(msgLabel);
        } else {
            // Criar um card para cada médico
            for (Medico medico : medicos) {
                criarCardMedico(medico);
            }
        }
        
        // Atualizar o container
        panelCardsContainer.revalidate();
        panelCardsContainer.repaint();
    }
    
    /**
     * Cria um card individual para um médico
     */
    private void criarCardMedico(Medico medico) {
        // ShadowPanel para o card
        ShadowPanel cardPanel = new ShadowPanel(8, 15, new Color(0, 0, 0, 80));
        cardPanel.setPreferredSize(new Dimension(180, 90));
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setLayout(new BorderLayout(10, 5));
        cardPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Ícone do médico
        JLabel iconeLabel = new JLabel("👨‍⚕️", SwingConstants.CENTER);
        iconeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 32));
        
        // Painel de informações
        JPanel infoPanel = new JPanel(new GridLayout(3, 1, 0, 2));
        infoPanel.setOpaque(false);
        infoPanel.setBorder(new EmptyBorder(5, 0, 5, 5));
        
        // Nome do médico
        JLabel nomeLabel = new JLabel(medico.getNome());
        nomeLabel.setFont(new Font("Arial", Font.BOLD, 12));
        nomeLabel.setForeground(Color.BLACK);
        
        // Especialidade
        JLabel especialidadeLabel = new JLabel(medico.getEspecialidade());
        especialidadeLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        especialidadeLabel.setForeground(new Color(0x458C45));
        
        // CRM
        JLabel crmLabel = new JLabel("CRM: " + medico.getCrm());
        crmLabel.setFont(new Font("Arial", Font.PLAIN, 9));
        crmLabel.setForeground(Color.GRAY);
        
        infoPanel.add(nomeLabel);
        infoPanel.add(especialidadeLabel);
        infoPanel.add(crmLabel);
        
        cardPanel.add(iconeLabel, BorderLayout.WEST);
        cardPanel.add(infoPanel, BorderLayout.CENTER);
        
        // Armazenar referências
        cardsMedicos.add(cardPanel);
        labelsNomes.add(nomeLabel);
        labelsEspecialidades.add(especialidadeLabel);
        medicosIds.add(medico.getId());
        
        // Adicionar ao container
        panelCardsContainer.add(cardPanel);
    }
    
    /**
     * Retorna o ID do médico pelo índice do card
     */
    public int getIdMedicoPorIndice(int indice) {
        if (indice >= 0 && indice < medicosIds.size()) {
            return medicosIds.get(indice);
        }
        return -1;
    }
    
    /**
     * Retorna a lista de cards para adicionar eventos no controller
     */
    public List<ShadowPanel> getCardsMedicos() {
        return cardsMedicos;
    }
    
    /**
     * Retorna a lista de nomes dos médicos
     */
    public List<JLabel> getLabelsNomes() {
        return labelsNomes;
    }
    
    private void containerBackground() {
        GridBagConstraints b = new GridBagConstraints();
        b.gridx = 0;
        b.gridy = 1;
        b.insets = new Insets(0, 20, 10, 20);
        b.weightx = 1;
        b.weighty = 1;
        b.fill = GridBagConstraints.BOTH;
        
        JPanel cont = new JPanel();
        cont.setLayout(new GridBagLayout());
        cont.setBackground(null);
        
        ImageIcon icon = new ImageIcon(_PARTH_IMG_CALENDER);
        Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        calendarioImagem = new JLabel(new ImageIcon(img));
        calendarioImagem.setHorizontalAlignment(JLabel.CENTER);
        
        GridBagConstraints imgConstraints = new GridBagConstraints();
        imgConstraints.gridx = 0;
        imgConstraints.gridy = 0;
        imgConstraints.weightx = 1;
        imgConstraints.weighty = 1;
        imgConstraints.anchor = GridBagConstraints.CENTER;
        cont.add(calendarioImagem, imgConstraints);
        
        JLabel lblInfo = new JLabel("Selecione um médico para novo agendamento");
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
    
    protected void containerButton() {
        GridBagConstraints g = new GridBagConstraints();
        
        containerButtons = new RoundedPanel(20);
        containerButtons.setBackground(Color.LIGHT_GRAY);
        containerButtons.setPreferredSize(new Dimension(0, 100));
        containerButtons.setBorder(null);
        containerButtons.setLayout(new GridBagLayout());
        
        g.gridx = 0;
        g.gridy = 2;
        g.insets = new Insets(5, 20, 0, 20);
        g.weightx = 1;
        g.weighty = 0;
        g.fill = GridBagConstraints.HORIZONTAL;
        
        bodyMain.add(containerButtons, g);
        
        panBtnNovoAgendamento = new RoundedPanel(10);
        lblNovoAgendamento = new JLabel("Novo Agendamento");
        createButtoms(0, 0, panBtnNovoAgendamento, lblNovoAgendamento, Color.DARK_GRAY);
        
        panListaAgendados = new RoundedPanel(10);
        lblListaAgendados = new JLabel("Lista de Agendados");
        createButtoms(1, 0, panListaAgendados, lblListaAgendados, Color.DARK_GRAY);
        
        GridBagConstraints m = new GridBagConstraints();
        JLabel mgs = new JLabel("Clique em um médico para iniciar o agendamento");
        mgs.setFont(new Font("Arial", Font.PLAIN, 14));
        mgs.setForeground(Color.BLACK);
        
        m.gridx = 0;
        m.gridy = 3;
        m.insets = new Insets(5, 20, 0, 20);
        m.weightx = 1;
        m.weighty = 0;
        m.fill = GridBagConstraints.HORIZONTAL;
        bodyMain.add(mgs, m);
    }
    
    protected void createButtoms(int c, int l, RoundedPanel panel, JLabel label, Color bColor) {
        GridBagConstraints gb = new GridBagConstraints();
        gb.gridx = c;
        gb.gridy = l;
        gb.insets = new Insets(0, 20, 0, 20);
        gb.weightx = 1;
        gb.fill = GridBagConstraints.HORIZONTAL;
        
        panel.setBackground(bColor);
        panel.setPreferredSize(new Dimension(200, 50));
        panel.setLayout(new GridBagLayout());
        panel.setBorder(null);
        
        GridBagConstraints gb1 = new GridBagConstraints();
        gb1.gridx = c;
        gb1.gridy = l;
        gb1.insets = new Insets(0, 20, 0, 20);
        gb1.weightx = 1;
        
        label.setFont(new Font("Arial", Font.BOLD, 17));
        label.setForeground(Color.WHITE);
        
        panel.add(label, gb1);
        containerButtons.add(panel, gb);
    }
    
    @Override
    protected void createSideBarRigth(JPanel panel) {
        super.createSideBarRigth(panel);

        // Limpar e configurar layout
        sideBarRight.removeAll();
        sideBarRight.setLayout(new BorderLayout(0, 5));
        sideBarRight.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ===== PAINEL SUPERIOR (DATA ATUAL) =====
        JPanel dataPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        dataPanel.setBackground(new Color(0xF5F5F5));
        dataPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0xE0E0E0), 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JLabel dataLabel = new JLabel();
        AuxiliaryMethod.showDateActual(dataPanel, greenColor, dataLabel);

    
        CompactCalendarPanel calendario = new CompactCalendarPanel();

        // ===== MONTAR SIDEBAR =====
        sideBarRight.add(dataPanel, BorderLayout.NORTH);
        /*        sideBarRight.add(tituloCalendario, BorderLayout.CENTER);*/
        sideBarRight.add(calendario, BorderLayout.SOUTH);
        
        RoundedPanel p = new RoundedPanel();
        
        
        doctorSelected = new JLabel();
        doctorAreaSelected = new JLabel();
        boxDoctorsSelected(p, doctorSelected, doctorAreaSelected);
    }
    
    // Método auxiliar para pegar o mês atual
    //=============================    
    // Elemento [ Novo Encaixe ] - Component Sidebar_Rigth
    //=============================
    private void boxDoctorsSelected(RoundedPanel shadowPanel, JLabel label, JLabel label1){
        // ===== PAINEL SUPERIOR (DATA ATUAL) =====
        containerDoctors = new JPanel(new GridBagLayout());
        containerDoctors.setBackground(null);
        containerDoctors.setBorder(null);
        
        // Configurar o shadowPanel com layout vertical
        shadowPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        //shadowPanel.setBorder(BorderFactory.createLineBorder(new Color(0x7ED348), 2)); // Borda verde
        shadowPanel.setBackground(null);
        shadowPanel.setBorder(null);
        shadowPanel.setBackground(null);
        shadowPanel.setPreferredSize(new Dimension(0, 80));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 10, 2, 10);

        // Primeiro label (nome do médico)
        gbc.gridy = 0;
        gbc.weighty = 0;
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(new Color(0x458C45));
        label.setHorizontalAlignment(JLabel.CENTER);
        containerDoctors.add(label, gbc);

        // Segundo label (especialidade)
        gbc.gridy = 1;
        gbc.weighty = 1;
        label1.setFont(new Font("Arial", Font.PLAIN, 11));
        label1.setForeground(Color.GRAY);
        label1.setHorizontalAlignment(JLabel.CENTER);
        containerDoctors.add(label1, gbc);

        shadowPanel.add(containerDoctors, BorderLayout.NORTH);
        // Adicionar ao sideBarRight com margem
        sideBarRight.add(shadowPanel);
    }
    
    public JPanel getContainerDoctors(){return containerDoctors;}
    
    // Getters para o Controller
    public JLabel getLabelDoctors(int i){
        
        return switch (i) {
            case 1 -> doctorSelected;
            case 2 -> doctorAreaSelected;
            default -> null;
        };
    }
    
    public RoundedPanel getAllPanels(int selection) {
        return switch (selection) {
            case 7 -> panBtnNovoAgendamento;
            case 8 -> panListaAgendados;
            default -> null;
        };
    }
    
    public JLabel getAllLabels(int i) {
        return switch (i) {
            case 1 -> lblNovoAgendamento;
            case 2 -> lblListaAgendados;
            default -> null;
        };
    }
    
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        EventQueue.invokeLater(() -> {
            Scheduling s = new Scheduling();
            new SchedulingController(s);
            s.setVisible(true);
        });
    }
}