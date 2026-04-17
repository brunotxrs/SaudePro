package br.com.senac.saudepro.gui;

import br.com.senac.saudepro.util.IconTextField;
import br.com.senac.saudepro.util.ImageLogo;
import br.com.senac.saudepro.util.PanelBackground;
import br.com.senac.saudepro.util.RoundedPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Tela de Login do sistema SaúdePro
 * @author bruno-teixeira
 */
public class Login extends JFrame {

    // Componentes principais
    private static final PanelBackground panelBackground = new PanelBackground();
    private RoundedPanel boxLogin;
    
    // Componentes de entrada
    private JTextField txtUser;
    private JPasswordField jpfPassword;
    private JButton buttonEnter;

    
    // Caminhos das imagem Icons
    private final String _pathIconUser = "src/main/java/resources/img/user.png";
    private final String _pathIconBlock = "src/main/java/resources/img/block.png";
    private final String _pathIconVisibility0ff = "src/main/java/resources/img/visibility0ff.png";
    
    // Ícones
    private IconTextField icoUser = new IconTextField(_pathIconUser, 40, 30);
    private IconTextField icoPassBlock = new IconTextField(_pathIconBlock, 40, 30);
    private IconTextField iconVisibility0ff = new IconTextField(_pathIconVisibility0ff, 40, 30);
    
    // Labels de erro
    private JLabel messageUser;
    private JLabel messagePass;
    
    // remover apos testes
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Login.class.getName());
    
    
    public Login(){
        initComponents();
    }
    
    private void initComponents(){
        configurationScreen(); // manter as condiçoes de tela fechar
        
        configurationBackground(); // chamando o panel com imagem background
        
    }
    
    // for conditions of Frame
    private void configurationScreen(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SaúdePro");
        /*getContentPane().setLayout(new java.awt.GridBagLayout());*/

        pack();
        setLocationRelativeTo(null);
        
    }
    
    // Method for image  background panel
    private void configurationBackground(){
        setSize(1440, 900); // largura e altura que ira se iniciar a tela 
        setLocationRelativeTo(null); // centralizar a tela panel
        
        panelBackground.setLayout(null); // Desabilitando layout principal para implementaçao manual
        panelBackground.setBounds(0, 0, 1440, 900); // add manualmente posiçoes e largura e altura do elemento
        
        // Substituir o jPanel1 pelo painelFundo no JFrame
        setContentPane(panelBackground);
        
        createBoxLogin();// chamando o boxLogin onde ha os elementos inputs
        
        getContentPane().setLayout(new java.awt.GridBagLayout()); // deixando posicionado + [centralizado] na tela
        
    }
    
    // Metedo da caixa para LoginTst e Senha 
    protected void createBoxLogin(){
        boxLogin = new RoundedPanel(40);
        // Criar o painel da caixa (400x500)
        boxLogin.setLayout(null); // Layout absoluto para controle total
        boxLogin.setBackground(java.awt.Color.WHITE); // Cor transparente
        boxLogin.setPreferredSize(new java.awt.Dimension(400, 400)); // mantendo o tamanho da caixa com elementos
        
        // Criar o logo com tamanho personalizado
        ImageLogo imageLogo = new ImageLogo(192, 80); // metodo de redimensionamento da imagem do Logo
        imageLogo.setBounds(100, 20, 200, 80); // posicionamento e largura e altura
        boxLogin.add(imageLogo); // o add ao father
        
        createInputs(); // chamando os componentes de inputs para o login
        createButton(); // chamando o componente de btn
        
        panelBackground.add(boxLogin); // add ao father
        
    }
    
    
    protected void createInputs(){
        // Mensagens de erro
        messageUser = new JLabel("Usuário Inválido");
        messageUser.setFont(new Font("Arial", Font.PLAIN, 10));
        messageUser.setForeground(Color.red);
        messageUser.setBounds(60, 110, 100, 30);
        messageUser.setVisible(false);
        
        messagePass = new JLabel("Senha Inválida");
        messagePass.setFont(new Font("Arial", Font.PLAIN, 10));
        messagePass.setForeground(Color.red);
        messagePass.setBounds(60, 270, 100, 30);
        messagePass.setVisible(false);
        
        // Campo Usuário
        RoundedPanel userPanel = new RoundedPanel(20); // arredondamento da bordar 20
        userPanel.setBackground(Color.WHITE); // add background Brando
        userPanel.setRoundedBorder(Color.GRAY, 1); // metodo para cor da borda e especura da borda
        userPanel.setBounds(50, 140, 300, 50); // posicionamento x y w h
        
        txtUser = new JTextField(); // atribuindo novo elemento
        txtUser.setBorder(null); // bordar null
        txtUser.setPreferredSize(new Dimension(245, 45)); // largura e altura
        txtUser.setFont(new Font("Arial", Font.PLAIN, 17)); // fontes
        
        icoUser.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 1)); // posicionamento do elemento
        userPanel.add(icoUser); // add componente
        userPanel.add(txtUser); // add componente
        
        // Campo Senha
        RoundedPanel passUser = new RoundedPanel(20); 
        passUser.setBackground(Color.WHITE);
        passUser.setRoundedBorder(Color.GRAY, 1);
        passUser.setBounds(50, 220, 300, 50);
        
        jpfPassword = new JPasswordField();
        jpfPassword.setBorder(null);
        jpfPassword.setPreferredSize(new Dimension(200, 45));
        jpfPassword.setFont(new Font("Arial", Font.PLAIN, 17));
        
        icoPassBlock.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 1));
        iconVisibility0ff.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 1));
        
        passUser.add(icoPassBlock);
        passUser.add(jpfPassword);
        passUser.add(iconVisibility0ff);
        
        
        
        boxLogin.add(messageUser);
        boxLogin.add(messagePass);
        boxLogin.add(userPanel);
        boxLogin.add(passUser);
        
    }
    
    
    private void createButton(){
        Color greenLigth = new Color(0x7ED348);
        Color withIce = new Color(239, 236, 237);
        buttonEnter = new JButton();
        buttonEnter.setBorder(null); // Muddar 
        buttonEnter.requestFocus(false);
        buttonEnter.setBackground(greenLigth);
        buttonEnter.setFont(new Font("Arial", Font.BOLD, 25));
        buttonEnter.setText("ENTRAR");
        buttonEnter.setForeground(withIce);
        buttonEnter.setBounds(100, 300, 200, 60);
        
        boxLogin.add(buttonEnter);
    }
    
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(() -> {
            
            new Login().setVisible(true);
            
                    
        });
    }
    
    
    // =============================================
    // GETTERS para o Controller acessar os componentes
    // =============================================
    public javax.swing.JTextField getTxtUser(){return txtUser;}
    public javax.swing.JPasswordField getJpfPassword(){return jpfPassword;}
    public javax.swing.JButton getButtonEnter(){return buttonEnter;}
    public javax.swing.JLabel getMessageUser(){return messageUser;}
    public javax.swing.JLabel getMessagePass(){return messagePass;}
    public RoundedPanel getBoxLogin() { return boxLogin;}
}
