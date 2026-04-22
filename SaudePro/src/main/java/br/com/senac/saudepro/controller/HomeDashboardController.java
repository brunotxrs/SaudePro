package br.com.senac.saudepro.controller;

import br.com.senac.saudepro.gui.HomeDashboard;
import br.com.senac.saudepro.util.AuxiliaryMethod;
import br.com.senac.saudepro.util.IconTextField;
import br.com.senac.saudepro.util.RoundedPanel;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class HomeDashboardController {
    
    //APLICAR OS DEMAIS ICONES
    
    private final String _PATH_IMG_SEARCH_NORMAL = "src/main/java/resources/img/searchIco.png";
    private final String _PATH_IMG_SEARCH_HOVER = "src/main/java/resources/img/searchIco.png";
    
    private ImageIcon iconSearchNormal;
    private ImageIcon iconSearchHover;
    
    private HomeDashboard dashboard;
    
    // Controle de qual botão está selecionado
    private RoundedPanel selectedPanel = null;
    private JLabel selectedLabel = null;
    
    private final Color SELECTED_COLOR = new Color(0x458C45);
    private final Color HOVER_COLOR = new Color(0x7ED348);
    private final Color CLOSE_COLOR = Color.RED;
    private final Color NORMAL_BG = Color.WHITE;
    private final Color NORMAL_TEXT = Color.GRAY;
    private final Color HOVER_TEXT = Color.WHITE;
    
    public HomeDashboardController(HomeDashboard hd) {
        this.dashboard = hd;
        initControllerHomeDashboard();
    }
    
    private void loadIcones() {
        iconSearchNormal = AuxiliaryMethod.loadedIcone(_PATH_IMG_SEARCH_NORMAL, 30, 30);
        iconSearchHover = AuxiliaryMethod.loadedIcone(_PATH_IMG_SEARCH_HOVER, 30, 30);
    }
    
    private void initControllerHomeDashboard() {
        loadIcones();
        
        AuxiliaryMethod.setPlaceholder(dashboard.getInputSearch(), "Buscar paciente por nome ou CPF...");
        aplicationHover(dashboard.getInputSearch(), dashboard.getIconSearc(), iconSearchNormal, iconSearchHover, dashboard.getPlaceSearch());
        
        // Configurar hover e clique para todos os botões
        configurarBotao(dashboard.getBtnInitial(), dashboard.getLblInitial(), "Inicio");
        configurarBotao(dashboard.getBtnRegister(), dashboard.getLblRegister(), "Cadastro");
        configurarBotao(dashboard.getBtnScheduling(), dashboard.getLblScheduling(), "Agendamento");
        configurarBotao(dashboard.getBtnClose(), dashboard.getLblClose(), "Sair");
        configurarBotao(dashboard.getBtnHelp(), dashboard.getLblHelp(), "Suporte");
        
        // Selecionar Inicio por padrão
        selecionarBotao(dashboard.getBtnInitial(), dashboard.getLblInitial());
    }
    
    private void configurarBotao(RoundedPanel panel, JLabel label, String nomeBotao) {
        if (panel == null || label == null) return;
        
        // ========== HOVER ==========
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Se não for o botão selecionado, aplica hover
                if (selectedPanel != panel) {
                    panel.setBackground(HOVER_COLOR);
                    label.setForeground(HOVER_TEXT);
                    panel.repaint();
                   
                }
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                // Se não for o botão selecionado, volta ao normal
                if (selectedPanel != panel) {
                    panel.setBackground(NORMAL_BG);
                    label.setForeground(NORMAL_TEXT);
                    panel.repaint();
                }
            }
        });
        
        // ========== CLIQUE ==========
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selecionarBotao(panel, label);
                
                // Ações específicas por botão
                if (nomeBotao.equals("Sair")) {
                    System.out.println("Sair - Fechar aplicação");
                    // System.exit(0);
                } else {
                    System.out.println("Clicou em: " + nomeBotao);
                }
            }
        });
    }
    
    private void selecionarBotao(RoundedPanel panel, JLabel label) {
        // Restaurar o botão anteriormente selecionado
        if (selectedPanel != null && selectedLabel != null) {
            selectedPanel.setBackground(NORMAL_BG);
            selectedLabel.setForeground(NORMAL_TEXT);
            selectedPanel.repaint();
        }
        
        // Selecionar o novo botão
        this.selectedPanel = panel;
        this.selectedLabel = label;
        
        // Aplicar estilo de selecionado
        if (panel == dashboard.getBtnClose()) {
            panel.setBackground(CLOSE_COLOR);
        } else {
            panel.setBackground(SELECTED_COLOR);
        }
        label.setForeground(HOVER_TEXT);
        panel.repaint();
    }
    
    private void aplicationHover(JTextField campo, IconTextField iconField, ImageIcon normal, ImageIcon hover, RoundedPanel panel) {
        if (campo == null || iconField == null) return;
        
        campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                panel.setRoundedBorder(HOVER_COLOR, 2);
                iconField.setIcon(hover);
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                panel.setRoundedBorder(NORMAL_TEXT, 1);
                iconField.setIcon(normal);
            }
        });
    }
}