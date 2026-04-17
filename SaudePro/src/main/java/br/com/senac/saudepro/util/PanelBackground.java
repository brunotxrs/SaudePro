package br.com.senac.saudepro.util;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *  @author bruno-teixeira
 * 
 */
public class PanelBackground extends JPanel {

    // guarda a imagem original (carregada uma única vez)
    private final Image imageBackground;
    
    private final String _pathBackground = "src/main/java/resources/img/background-SaudePro.png";
    
    public PanelBackground() {
        // Carregar a imagem original
        ImageIcon imageIcon = new ImageIcon(_pathBackground);
        
        imageBackground = imageIcon.getImage();
    
        // o painel não tem layout padrão
        // quem adicionar componentes vai precisar posicionar manualmente
        setLayout(null);
    }
    
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
        if(imageBackground != null){
            
            // Desenha a imagem ocupando todo o espaço do painel
            // getWidth() e getHeight() retornam o tamanho ATUAL
            g.drawImage(imageBackground, 0, 0, getWidth(), getHeight(), this);
        }
    }
    
}
