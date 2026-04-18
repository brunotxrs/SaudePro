package br.com.senac.saudepro.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 * Class For Auxiliary Method
 * @author bruno-teixeira
 */
public class AuxiliaryMethod {
    
    private static Dialog dialog;
    
    /**
     *
     * @param field
     * @param placeholder
     */
    public static void setPlaceholder(JTextField field, String placeholder) {
        field.setText(placeholder);
        field.setForeground(java.awt.Color.GRAY);

        field.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(java.awt.Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    
                    field.setForeground(java.awt.Color.GRAY);
                }
            }
        });
    }
    
   
    // Metodo pra exibir mensageDialog Personalisada Flutuante
    public static void mostrarMensagemFlutuante(JFrame viewFrame, String message, int width, int heigth) {
        
        // Criar o diálogo
        dialog = new JDialog(viewFrame, "Aviso", true);
        dialog.setSize(width, heigth);
        dialog.setLocationRelativeTo(viewFrame);
        dialog.setUndecorated(true);
        dialog.setLayout(new BorderLayout());

        RoundedPanel painel = new RoundedPanel(15);
        painel.setBackground(Color.WHITE);
        painel.setBorder(BorderFactory.createLineBorder(new Color(0x7ED348), 2));
        painel.setLayout(new BorderLayout());

        JLabel mensagem = new JLabel(message, JLabel.CENTER);
        mensagem.setFont(new Font("Arial", Font.PLAIN, 14));
        mensagem.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        painel.add(mensagem, BorderLayout.CENTER);
        dialog.add(painel);
        
       
        
        Timer timer = new Timer(4000, e -> {
            dialog.dispose();
        });
        
        timer.setRepeats(false);
        timer.start();
        
         // Mostrar o diálogo
        dialog.setVisible(true);
    }
    
    // Metodo para mudar a cor do campo para Vermelho caso de erro
    public static void aplicateColorRed(RoundedPanel p, IconTextField iconField, ImageIcon imageIcon){
       p.setRoundedBorder(Color.red, 2);
       iconField.setIcon(imageIcon);
    }
    
    // Metodo pra voltar ao estado incial
    public static void aplicateColorGray(RoundedPanel p, IconTextField iconField, ImageIcon imageIcon){
       p.setRoundedBorder(Color.GRAY, 1);
       iconField.setIcon(imageIcon);
    }
    
}
