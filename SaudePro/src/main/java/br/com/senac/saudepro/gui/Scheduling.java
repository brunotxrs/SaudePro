package br.com.senac.saudepro.gui;

import br.com.senac.saudepro.controller.SchedulingController;
import br.com.senac.saudepro.model.Medico;
import br.com.senac.saudepro.util.AuxiliaryMethod;
import br.com.senac.saudepro.util.CompactCalendarPanel;
import br.com.senac.saudepro.util.RoundedPanel;
import br.com.senac.saudepro.util.ShadowPanel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
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
    private JPanel panelCardsContainer;
    private JScrollPane scrollPaneMedicos;
    private List<ShadowPanel> cardsMedicos = new ArrayList<>();
    private List<JLabel> labelsNomes = new ArrayList<>();
    private List<JLabel> labelsEspecialidades = new ArrayList<>();
    private List<Integer> medicosIds = new ArrayList<>();
    
    // Botões
    private static RoundedPanel panBtnNovoAgendamento;
    private static JLabel lblNovoAgendamento;
    private static RoundedPanel panListaAgendados;
    private static JLabel lblListaAgendados;
    
    private static JLabel doctorSelected;
    private static JLabel doctorAreaSelected;
    
    private static JLabel lblInfo;
    
    private static JLabel lblMedico;
    private static JLabel i_Medico;
    
    private static JLabel lblPaciente;
    private static JLabel i_Paciente;
    
    private static JLabel lblCPF;
    private static JLabel i_CPF;

    private static JLabel lblData;
    private static JLabel i_Data;
    
    private static JLabel lblHora;
    private static JLabel i_Hora;
    private static JPanel horariosPanel;
    private List<RoundedPanel> horarioCards = new ArrayList<>();
    
    private static String _PARTH_IMG_CALENDER = "src/main/java/resources/img/calendar_month_background.png";
    private static JLabel calendarioImagem;
    private final Color greenColor = new Color(0x458C45);
    
    // Cores para horários
    public static final Color COR_HORARIO_DISPONIVEL = new Color(0xF0F8FF);
    public static final Color COR_HORARIO_OCUPADO = new Color(0xFF6B6B);
    public static final Color COR_HORARIO_SELECIONADO = new Color(0x7ED348);
    
    // Callback para quando a data mudar
    private Runnable onDataChangeListener;
    
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
        
        containerOfDoctorsDinamico();
        containerBackground();
        containerButton();
    }
    
    private void containerOfDoctorsDinamico() {
        GridBagConstraints d = new GridBagConstraints();
        d.gridx = 0;
        d.gridy = 0;
        d.insets = new Insets(5, 20, 5, 20);
        d.weightx = 1;
        d.weighty = 0;
        d.fill = GridBagConstraints.HORIZONTAL;
        
        panelCardsContainer = new JPanel();
        panelCardsContainer.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
        panelCardsContainer.setBackground(Color.WHITE);
        panelCardsContainer.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        scrollPaneMedicos = new JScrollPane(panelCardsContainer);
        scrollPaneMedicos.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneMedicos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPaneMedicos.setBorder(null);
        
        JScrollBar barra = scrollPaneMedicos.getHorizontalScrollBar();
        barra.setPreferredSize(new Dimension(0, 8));
        barra.setBackground(new Color(0xF0F0F0));
        barra.setForeground(new Color(0x7ED348));
        barra.setBorder(BorderFactory.createEmptyBorder());
        scrollPaneMedicos.setPreferredSize(new Dimension(0, 140));
        scrollPaneMedicos.getHorizontalScrollBar().setUnitIncrement(20);
        
        container.add(scrollPaneMedicos, d);
    }
    
    public void criarCardsMedicos(List<Medico> medicos) {
        cardsMedicos.clear();
        labelsNomes.clear();
        labelsEspecialidades.clear();
        medicosIds.clear();
        panelCardsContainer.removeAll();
        
        if (medicos == null || medicos.isEmpty()) {
            JLabel msgLabel = new JLabel("Nenhum médico cadastrado no sistema");
            msgLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            msgLabel.setForeground(Color.GRAY);
            panelCardsContainer.add(msgLabel);
        } else {
            for (Medico medico : medicos) {
                criarCardMedico(medico);
            }
        }
        
        panelCardsContainer.revalidate();
        panelCardsContainer.repaint();
    }
    
    private void criarCardMedico(Medico medico) {
        ShadowPanel cardPanel = new ShadowPanel(8, 15, new Color(0, 0, 0, 80));
        cardPanel.setPreferredSize(new Dimension(180, 90));
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setLayout(new BorderLayout(10, 5));
        cardPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JLabel iconeLabel = new JLabel("👨‍⚕️", SwingConstants.CENTER);
        iconeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 32));
        
        JPanel infoPanel = new JPanel(new GridLayout(3, 1, 0, 2));
        infoPanel.setOpaque(false);
        infoPanel.setBorder(new EmptyBorder(5, 0, 5, 5));
        
        JLabel nomeLabel = new JLabel(medico.getNome());
        nomeLabel.setFont(new Font("Arial", Font.BOLD, 12));
        nomeLabel.setForeground(Color.BLACK);
        
        JLabel especialidadeLabel = new JLabel(medico.getEspecialidade());
        especialidadeLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        especialidadeLabel.setForeground(new Color(0x458C45));
        
        JLabel crmLabel = new JLabel("CRM: " + medico.getCrm());
        crmLabel.setFont(new Font("Arial", Font.PLAIN, 9));
        crmLabel.setForeground(Color.GRAY);
        
        infoPanel.add(nomeLabel);
        infoPanel.add(especialidadeLabel);
        infoPanel.add(crmLabel);
        
        cardPanel.add(iconeLabel, BorderLayout.WEST);
        cardPanel.add(infoPanel, BorderLayout.CENTER);
        
        cardsMedicos.add(cardPanel);
        labelsNomes.add(nomeLabel);
        labelsEspecialidades.add(especialidadeLabel);
        medicosIds.add(medico.getId());
        
        panelCardsContainer.add(cardPanel);
    }
    
    public List<ShadowPanel> getCardsMedicos() {
        return cardsMedicos;
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
        imgConstraints.gridwidth = 4;
        imgConstraints.weightx = 1;
        imgConstraints.weighty = 1;
        imgConstraints.anchor = GridBagConstraints.CENTER;
        cont.add(calendarioImagem, imgConstraints);

        lblInfo = new JLabel("Selecione um médico para novo agendamento");
        lblInfo.setFont(new Font("Arial", Font.PLAIN, 20));
        lblInfo.setForeground(Color.GRAY);

        GridBagConstraints textConstraints = new GridBagConstraints();
        textConstraints.gridx = 0;
        textConstraints.gridy = 1;
        textConstraints.gridwidth = 4;
        textConstraints.weightx = 0;
        textConstraints.anchor = GridBagConstraints.CENTER;
        textConstraints.insets = new Insets(5, 0, 100, 0);
        cont.add(lblInfo, textConstraints);

        int linhaAtual = 2;

        lblMedico = new JLabel("Médico:*");
        i_Medico = new JLabel();
        lblMedico.setVisible(false);
        i_Medico.setVisible(false);
        createLabel(cont, 0, linhaAtual, 1, 0, lblMedico, 10, 5, false, 50, 5);
        createLabel(cont, 1, linhaAtual, 1, 0, i_Medico, 5, -100, true, 50, 5);

        linhaAtual++;

        lblPaciente = new JLabel("Paciente:*");
        i_Paciente = new JLabel();
        lblCPF = new JLabel("CPF:*");
        i_CPF = new JLabel();

        lblPaciente.setVisible(false);
        i_Paciente.setVisible(false);
        lblCPF.setVisible(false);
        i_CPF.setVisible(false);

        createLabel(cont, 0, linhaAtual, 1, 1, lblPaciente, 10, 5, false, 30, 5);
        createLabel(cont, 1, linhaAtual, 1, 1, i_Paciente, 5, 10, true, 30, 5);
        createLabel(cont, 2, linhaAtual, 1, 1, lblCPF, 10, 5, false, 30, 5);
        createLabel(cont, 3, linhaAtual, 1, 1, i_CPF, 5, 10, true, 30, 5);

        linhaAtual++;

        lblData = new JLabel("Data:*");
        i_Data = new JLabel();
        lblHora = new JLabel("Hora:*");
        i_Hora = new JLabel();

        lblData.setVisible(false);
        i_Data.setVisible(false);
        lblHora.setVisible(false);
        i_Hora.setVisible(false);

        createLabel(cont, 0, linhaAtual, 1, 1, lblData, 10, 5, false, 5, 50);
        createLabel(cont, 1, linhaAtual, 1, 1, i_Data, 5, 10, true, 5, 50);
        createLabel(cont, 2, linhaAtual, 1, 1, lblHora, 10, 5, false, 5, 50);
        createLabel(cont, 3, linhaAtual, 1, 1, i_Hora, 5, 10, true, 5, 50);

        container.add(cont, b);
    }

    protected void createLabel(JPanel container, int c, int l, int h, int v, JLabel label, int left, int rigth, boolean bold, int up, int down){
        GridBagConstraints gb = new GridBagConstraints();
        gb.gridx = c;
        gb.gridy = l;
        gb.weightx = h;
        gb.weighty = v;
        gb.insets = new Insets(up, left, down, rigth);
        gb.anchor = GridBagConstraints.WEST;

        if(!bold){
            label.setFont(new Font("Arial", Font.PLAIN, 14));
            label.setForeground(Color.GRAY);
        } else {
            label.setFont(new Font("Arial", Font.BOLD, 14));
            label.setForeground(new Color(0x458C45));
            label.setHorizontalAlignment(JLabel.LEFT);
        }

        container.add(label, gb);
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
    
    public void setOnDataChangeListener(Runnable listener) {
        this.onDataChangeListener = listener;
    }
    
    @Override
    protected void createSideBarRigth(JPanel panel) {
        super.createSideBarRigth(panel);

        sideBarRight.removeAll();
        sideBarRight.setLayout(new BorderLayout(0, 5));
        sideBarRight.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));

        JPanel dataPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        dataPanel.setBackground(new Color(0xF5F5F5));
        dataPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0xE0E0E0), 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JLabel dataLabel = new JLabel();
        AuxiliaryMethod.showDateActual(dataPanel, greenColor, dataLabel);

        CompactCalendarPanel calendario = new CompactCalendarPanel();
        
        // Configurar callback para quando a data mudar
        calendario.setOnDataChangeListener(() -> {
            if (onDataChangeListener != null) {
                onDataChangeListener.run();
            }
        });
        
        calendario.setDataLabelExterna(i_Data);

        sideBarRight.add(dataPanel, BorderLayout.NORTH);
        sideBarRight.add(calendario, BorderLayout.SOUTH);

        RoundedPanel p = new RoundedPanel();

        doctorSelected = new JLabel();
        doctorAreaSelected = new JLabel();
        boxDoctorsSelected(p, doctorSelected, doctorAreaSelected);
    }
    
    private void boxDoctorsSelected(RoundedPanel shadowPanel, JLabel label, JLabel label1){
        containerDoctors = new JPanel(new GridBagLayout());
        containerDoctors.setBackground(null);
        containerDoctors.setBorder(null);

        shadowPanel.setLayout(new BorderLayout(0, 10));
        shadowPanel.setBackground(null);
        shadowPanel.setBorder(null);
        shadowPanel.setPreferredSize(new Dimension(0, 160));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 10, 2, 10);

        gbc.gridy = 0;
        gbc.weighty = 0;
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(new Color(0x458C45));
        label.setHorizontalAlignment(JLabel.CENTER);
        containerDoctors.add(label, gbc);

        gbc.gridy = 1;
        gbc.weighty = 1;
        label1.setFont(new Font("Arial", Font.PLAIN, 11));
        label1.setForeground(Color.GRAY);
        label1.setHorizontalAlignment(JLabel.CENTER);
        containerDoctors.add(label1, gbc);

        shadowPanel.add(containerDoctors, BorderLayout.NORTH);

        horariosPanel = new JPanel();
        horariosPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 5));
        horariosPanel.setBackground(null);
        horariosPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        horarioCards.clear();

        String[] horarios = {"09:00", "10:00", "11:00", "14:00", "15:00", "16:00"};

        for (String horario : horarios) {
            RoundedPanel horarioCard = new RoundedPanel(10);
            horarioCard.setBackground(COR_HORARIO_DISPONIVEL);
            horarioCard.setPreferredSize(new Dimension(60, 30));
            horarioCard.setCursor(new Cursor(Cursor.HAND_CURSOR));

            JLabel horarioLabel = new JLabel(horario);
            horarioLabel.setFont(new Font("Arial", Font.PLAIN, 11));
            horarioLabel.setForeground(new Color(0x458C45));
            horarioLabel.setHorizontalAlignment(JLabel.CENTER);

            horarioCard.add(horarioLabel);
            horarioCards.add(horarioCard);
            horariosPanel.add(horarioCard);
        }

        horariosPanel.setVisible(false);
        shadowPanel.add(horariosPanel, BorderLayout.CENTER);
        sideBarRight.add(shadowPanel);
    }
    
    public void atualizarCorHorario(int index, Color cor, Color corTexto) {
        if (index >= 0 && index < horarioCards.size()) {
            RoundedPanel card = horarioCards.get(index);
            card.setBackground(cor);
            JLabel label = (JLabel) card.getComponent(0);
            label.setForeground(corTexto);
            
            if (cor.equals(COR_HORARIO_OCUPADO)) {
                card.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                for (MouseListener ml : card.getMouseListeners()) {
                    card.removeMouseListener(ml);
                }
            } else {
                card.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        }
    }
    
    public void adicionarEventoHorario(int index, String horario, Consumer<String> aoClicar) {
        if (index >= 0 && index < horarioCards.size()) {
            RoundedPanel card = horarioCards.get(index);
            for (MouseListener ml : card.getMouseListeners()) {
                card.removeMouseListener(ml);
            }
            
            card.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (!card.getBackground().equals(COR_HORARIO_OCUPADO)) {
                        aoClicar.accept(horario);
                    }
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (!card.getBackground().equals(COR_HORARIO_OCUPADO) && !card.getBackground().equals(COR_HORARIO_SELECIONADO)) {
                        card.setBackground(new Color(0xE8F5E9));
                    }
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    if (!card.getBackground().equals(COR_HORARIO_OCUPADO) && !card.getBackground().equals(COR_HORARIO_SELECIONADO)) {
                        card.setBackground(COR_HORARIO_DISPONIVEL);
                        ((JLabel)card.getComponent(0)).setForeground(new Color(0x458C45));
                    }
                }
            });
        }
    }
    
    public void resetarCoresHorarios() {
        for (int i = 0; i < horarioCards.size(); i++) {
            RoundedPanel card = horarioCards.get(i);
            if (!card.getBackground().equals(COR_HORARIO_OCUPADO)) {
                card.setBackground(COR_HORARIO_DISPONIVEL);
                ((JLabel)card.getComponent(0)).setForeground(new Color(0x458C45));
            }
        }
    }
    
    public int getQuantidadeHorarios() {
        return horarioCards.size();
    }
    
    public String getHorarioPorIndex(int index) {
        if (index >= 0 && index < horarioCards.size()) {
            return ((JLabel)horarioCards.get(index).getComponent(0)).getText();
        }
        return null;
    }
    
    public void marcarHorarioSelecionado(String horario) {
        for (int i = 0; i < horarioCards.size(); i++) {
            RoundedPanel card = horarioCards.get(i);
            String horarioCard = ((JLabel)card.getComponent(0)).getText();
            if (horarioCard.equals(horario)) {
                card.setBackground(COR_HORARIO_SELECIONADO);
                ((JLabel)card.getComponent(0)).setForeground(Color.WHITE);
            } else if (!card.getBackground().equals(COR_HORARIO_OCUPADO)) {
                card.setBackground(COR_HORARIO_DISPONIVEL);
                ((JLabel)card.getComponent(0)).setForeground(new Color(0x458C45));
            }
        }
    }
    
    public JPanel getHorariosPanel(){return horariosPanel;}
    public JPanel getContainerDoctors(){return containerDoctors;}
    
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
            case 3 -> calendarioImagem;
            case 4 -> lblInfo;
            default -> null;
        };
    }
    
    public JLabel getShowLabels(int i){
        return switch (i) {
            case 1 -> lblMedico;
            case 2 -> i_Medico;
            case 3 -> lblPaciente;
            case 4 -> i_Paciente;
            case 5 -> lblCPF;
            case 6 -> i_CPF;
            case 7 -> lblData;
            case 8 -> i_Data;
            case 9 -> lblHora;
            case 10 -> i_Hora;
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