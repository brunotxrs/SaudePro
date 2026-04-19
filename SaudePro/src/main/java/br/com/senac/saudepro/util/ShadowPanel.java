package br.com.senac.saudepro.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 * Class utilitaria para aplicaçao de shadow
 * @author bruno-teixeira
 */
public class ShadowPanel extends JPanel {
    private final int shadowSize; // intensidade da sombra
    private final int cornerRadius; // arredondamento
    private final Color shadowColor; // transparência da sombra

    public ShadowPanel(int shadowSize, int cornerRadius, Color color) {
        setOpaque(false); // ESSENCIAL
        this.shadowSize = shadowSize;
        this.cornerRadius = cornerRadius;
        this.shadowColor = color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();

        Graphics2D g2 = (Graphics2D) g.create();

        // suavização
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // sombra (camadas)
        for (int i = 0; i < shadowSize; i++) {
            float alpha = (float) (shadowSize - i) / shadowSize;
            g2.setColor(new Color(0, 0, 0, (int) (alpha * 20)));

            g2.fillRoundRect(
                i,
                i,
                width - i * 2,
                height - i * 2,
                cornerRadius,
                cornerRadius
            );
        }

        // fundo do card
        g2.setColor(getBackground());
        g2.fillRoundRect(
            shadowSize,
            shadowSize,
            width - shadowSize * 2,
            height - shadowSize * 2,
            cornerRadius,
            cornerRadius
        );

        g2.dispose();
    }

    public void add(int numbers) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
