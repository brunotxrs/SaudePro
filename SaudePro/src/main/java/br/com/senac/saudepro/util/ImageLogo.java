package br.com.senac.saudepro.util;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Componente reutilizável para exibir o logo da SaúdePro
 * @author bruno-teixeira
 */
public class ImageLogo extends JPanel {

    private final Image imageLogo;
    
     private int logoWidth = 192;   // Largura desejada do logo
    private int logoHeight = 80;    // Altura desejada do logo
    
    private final String _pathLogo = "src/main/java/resources/img/SaudePro-Logo.png";
    
    public ImageLogo() {
        ImageIcon imageIcon = new ImageIcon(_pathLogo);
        
        imageLogo = imageIcon.getImage();
        
        setOpaque(false);
        setBackground(new java.awt.Color(0, 0, 0, 0));
        
        setPreferredSize(new java.awt.Dimension(logoWidth, logoHeight));
        setLayout(null);
    }

    public ImageLogo(int width, int heigth) {
        this();
        
        this.logoWidth = width;
        this.logoHeight = heigth;
        
        setPreferredSize(new java.awt.Dimension(width, heigth));
    }
    
    
    
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
        if(imageLogo != null){
            g.drawImage(imageLogo, 0, 0, getWidth(), getHeight(), this);
        }
    }
    
}
