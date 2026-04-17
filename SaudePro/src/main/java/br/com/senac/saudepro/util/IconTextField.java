package br.com.senac.saudepro.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Campo de texto com ícone de imagem à esquerda
 * @author bruno-teixeira
 */
public class IconTextField extends JPanel {

    
    // Construtor para campo de senha
    public IconTextField(String parth, int width, int heigth) {
        
        setLayout(new FlowLayout(FlowLayout.LEADING));
        setBackground(Color.WHITE);
        
        
        // Carregar e redimensionar o ícone
        ImageIcon iconeOriginal = new ImageIcon(parth);
        Image icoRedi = iconeOriginal.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        
        JLabel icoLabel = new JLabel(new ImageIcon(icoRedi));
        icoLabel.setLayout(new FlowLayout(FlowLayout.LEADING));
        
        
        add(icoLabel, BorderLayout.WEST);
        setPreferredSize(new Dimension(width, heigth));
        
    }
    
}
