package br.com.senac.saudepro.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class IconTextField extends JPanel {
    
    private final JLabel iconLabel;      // ← ATRIBUTO pra image
    private final JTextField textField;  // ← ATRIBUTO pra compactar imagem
    
    public IconTextField(String path, int width, int height) {
        setLayout(new BorderLayout()); // organizar os elementos
        setBackground(Color.WHITE); 
        setBorder(null); // tirado a borda
        
        // Carregar e redimensionar o ícone
        ImageIcon iconeOriginal = new ImageIcon(path);
        Image icoRedi = iconeOriginal.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        
        // INICIALIZA O ATRIBUTO iconLabel
        iconLabel = new JLabel(new ImageIcon(icoRedi));
        iconLabel.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
        
        // Campo de texto
        textField = new JTextField();
        textField.setBorder(null);
        
        add(iconLabel, BorderLayout.WEST);
        add(textField, BorderLayout.CENTER);
        
        setPreferredSize(new Dimension(width, height));
    }
    
    public void setIcon(Icon icon) {
        if (iconLabel != null) {
            iconLabel.setIcon(icon);
        }
    }
    
    public String getText() {
        return textField.getText();
    }
    
    public JTextField getTextField() {
        return textField;
    }
}