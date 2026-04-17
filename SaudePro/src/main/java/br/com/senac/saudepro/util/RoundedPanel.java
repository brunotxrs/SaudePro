package br.com.senac.saudepro.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 * JPanel com cantos arredondados (border radius)
 * @author bruno-teixeira
 */
public class RoundedPanel extends JPanel {
    
    private int cornerRadius = 20; // Raio dos cantos (padrão 20)
    private Color borderColor = null;
    private int borderThickness = 1;
    
    // Construtor padrão
    public RoundedPanel() {
        super();
        setOpaque(false); // Essencial para o desenho personalizado
    }
    
    // Construtor com raio personalizado
    public RoundedPanel(int radius) {
        super();
        this.cornerRadius = radius;
        setOpaque(false);
    }
    
    public void setRoundedBorder(Color color, int thickness){
        this.borderColor = color;
        this.borderThickness = thickness;
        repaint();
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g.create();
        
        // Ativar anti-aliasing para bordas suaves
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Desenhar o fundo arredondado
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        
        if(borderColor != null){
            g2.setColor(borderColor);
            g2.setStroke(new java.awt.BasicStroke(borderThickness));
            g2.drawRoundRect(
                    borderThickness / 2 ,
                    borderThickness / 2, 
                    getWidth() - borderThickness, 
                    getHeight() - borderThickness,
                    cornerRadius,
                    cornerRadius
            );
        }
        
        
        g2.dispose();
    }
    
}

