package br.com.senac.saudepro.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 * Class For Auxiliary Method
 * @author bruno-teixeira
 */
public class AuxiliaryMethod {
    
    private static Dialog dialog;
    private static JPanel selectedPanel = null;
    
    protected static Color HOVER_COLOR = new Color(0x7ED348);
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
    
    
    // Metodo para carrregar icones
    public static ImageIcon loadedIcone(String path, int width, int height) {
        try {
            ImageIcon icon = new ImageIcon(path);
            Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } catch (Exception e) {
            System.out.println("Erro ao carregar: " + path);
            return new ImageIcon();
        }
    }
    
    public static void aplcateHoverInBtns(JPanel panel, IconTextField field, ImageIcon imgNormal, ImageIcon imgHover, Color h, Color n, Color c){
        
        if(panel == null) return;
        
        // ========== HOVER ==========
        panel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                // Se não for o botão selecionado, aplica hover
                if(selectedPanel != panel){
                    field.setIcon(imgHover);
                    
                    panel.setBackground(h);
                    panel.repaint();
                }
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                if(selectedPanel != panel){
                    field.setIcon(imgNormal);
                    panel.setBackground(n);
                    panel.repaint();
                }
            }
            
        });
        
        // ========== CLIQUE ==========
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                //panel.setBackground(c);
                btnSelected(panel, n, c);
                field.setIcon(imgHover);
            }
        });
        
    }
    
    
    private static void btnSelected(JPanel jp,  Color n, Color c){
        
        if(jp != null){
            jp.setBackground(n);
            jp.repaint();
        }
        
        selectedPanel = jp;
        jp.setBackground(c);
        jp.repaint();
        
    }
    
    
    public static void aplicateHover(JTextField campo, RoundedPanel panel) {
        if (campo == null) return;
        
        campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                panel.setRoundedBorder(HOVER_COLOR, 2);
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                panel.setRoundedBorder(null, 1);

            }
        });
    }

    public static void aplicarHover(JTextArea campo, RoundedPanel panel) {
        if (campo == null) return;
        
        campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                panel.setRoundedBorder(HOVER_COLOR, 2);
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                panel.setRoundedBorder(null, 1);

            }
        });
    }
    
   public static void aplicarHoverLabel(JLabel label, RoundedPanel panel) {
        if (label == null || panel == null) return;

        Color hoverBK = new Color(0xA8E66B);

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setBackground(hoverBK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setBackground(null);
            }
        });
    }
    
    
    // Em AuxiliaryMethod.java
    public static void buttonsHover(JLabel label, RoundedPanel panel, Color hover, Color normal) {
        if (label == null || panel == null) return;

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setBackground(hover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setBackground(normal);
            }
        });
    }
    
    
    public static void showDateActual(JPanel panel, Color c, JLabel label){
        // Area de Data e Hora
        JLabel dateToday = new JLabel();
        
        dateToday.setFont(new Font("Arial", Font.PLAIN, 18));
        dateToday.setForeground(c);
        dateToday.setBounds(0, 25, 300, 70);
        
        
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "EEEE dd 'de' MMMM 'de' yyyy", 
                new Locale("pt", "BR")
        ); 
        
        // Data e Formatando
        String dataFormatada = today.format(formatter);
        dataFormatada = dataFormatada.substring(0, 1).toUpperCase() + dataFormatada.substring(1);
        
        // Hora e Formatando 
        Date horaActual = new Date();
        String timeActual = new SimpleDateFormat("HH:mm:ss").format(horaActual);
        
        
        dateToday.setText(
                "<html>"
                        + 
                        "<div style='text-align:center; 'width=300px'; height='45px'>"
                            + dataFormatada + "<br>Hora " + timeActual +
                        "</div>"
             + "</html>" 
        );
        dateToday.setHorizontalAlignment(JLabel.CENTER);
        dateToday.setVerticalAlignment(JLabel.CENTER);
        
        
        // Area de proximos atendimentos

        label.setFont(new Font("Arial", Font.PLAIN, 18));
        label.setForeground(Color.BLACK);
        label.setBounds(20, 100, 250, 40);
        label.setHorizontalAlignment(JLabel.CENTER);
        
        label.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, c));
        
        

        panel.add(dateToday);
        panel.add(label);
    }
    
    /**
    * Diálogo de confirmação personalizado (estilo mensagem flutuante)
    * @param viewFrame JFrame pai
    * @param message Mensagem de confirmação
    * @param width Largura do diálogo
    * @param height Altura do diálogo
    * @return true se usuário confirmou, false caso contrário
    */
    public static boolean mostrarConfirmacaoFlutuante(JFrame viewFrame, String message, int width, int height) {

       // Criar o diálogo
       JDialog dialogConfirm = new JDialog(viewFrame, "Confirmação", true);
       dialogConfirm.setSize(width, height);
       dialogConfirm.setLocationRelativeTo(viewFrame);
       dialogConfirm.setUndecorated(true);
       dialogConfirm.setLayout(new BorderLayout());

       // Painel arredondado
       RoundedPanel painel = new RoundedPanel(20);
       painel.setBackground(Color.WHITE);
       painel.setBorder(BorderFactory.createLineBorder(new Color(0x7ED348), 2));
       painel.setLayout(new BorderLayout());

       // Mensagem
       JLabel mensagem = new JLabel(message, JLabel.CENTER);
       mensagem.setFont(new Font("Arial", Font.PLAIN, 14));
       mensagem.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
       painel.add(mensagem, BorderLayout.CENTER);

       // Painel dos botões
       JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
       painelBotoes.setBackground(Color.WHITE);
       painelBotoes.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

       // Botão Confirmar (Sim/Sair)
       RoundedPanel btnConfirmar = new RoundedPanel(10);
       btnConfirmar.setBackground(new Color(0x7ED348));
       btnConfirmar.setPreferredSize(new Dimension(100, 35));
       btnConfirmar.setLayout(new GridBagLayout());

       JLabel lblConfirmar = new JLabel("Sim");
       lblConfirmar.setFont(new Font("Arial", Font.BOLD, 14));
       lblConfirmar.setForeground(Color.WHITE);
       btnConfirmar.add(lblConfirmar);

       // Botão Cancelar (Não)
       RoundedPanel btnCancelar = new RoundedPanel(10);
       btnCancelar.setBackground(Color.LIGHT_GRAY);
       btnCancelar.setPreferredSize(new Dimension(100, 35));
       btnCancelar.setLayout(new GridBagLayout());

       JLabel lblCancelar = new JLabel("Não");
       lblCancelar.setFont(new Font("Arial", Font.BOLD, 14));
       lblCancelar.setForeground(Color.BLACK);
       btnCancelar.add(lblCancelar);

       painelBotoes.add(btnConfirmar);
       painelBotoes.add(btnCancelar);
       painel.add(painelBotoes, BorderLayout.SOUTH);

       dialogConfirm.add(painel);

       // Controle de resposta
       final boolean[] resposta = {false};

       // Ação do botão Confirmar
       btnConfirmar.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e) {
               resposta[0] = true;
               dialogConfirm.dispose();
           }

           @Override
           public void mouseEntered(MouseEvent e) {
               btnConfirmar.setBackground(new Color(0x458C45));
           }

           @Override
           public void mouseExited(MouseEvent e) {
               btnConfirmar.setBackground(new Color(0x7ED348));
           }
       });

       // Ação do botão Cancelar
       btnCancelar.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e) {
               resposta[0] = false;
               dialogConfirm.dispose();
           }

           @Override
           public void mouseEntered(MouseEvent e) {
               btnCancelar.setBackground(new Color(200, 200, 200));
           }

           @Override
           public void mouseExited(MouseEvent e) {
               btnCancelar.setBackground(Color.LIGHT_GRAY);
           }
       });

       // Cursores de mão
       btnConfirmar.setCursor(new Cursor(Cursor.HAND_CURSOR));
       btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));

       dialogConfirm.setVisible(true);

       return resposta[0];
   }
    
    
    public static void configurarAcoes(JPanel panel, JFrame frame) {
        
        
        // Botão "Novo Agendamento"
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AuxiliaryMethod.mostrarMensagemFlutuante(
                    frame, 
                    "Funcionalidade em desenvolvimento", 
                    300, 
                    80
                );
            }
        });
    }
}
