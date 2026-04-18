package br.com.senac.saudepro.gui;

import br.com.senac.saudepro.util.IconTextField;
import br.com.senac.saudepro.util.ImageLogo;
import br.com.senac.saudepro.util.PanelBackground;
import br.com.senac.saudepro.util.RoundedPanel;
import java.awt.Color;
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
    private RoundedPanel userPanel;
    private RoundedPanel passUser; 
    
    // Componentes de entrada
    private JTextField txtUser;
    private JPasswordField jpfPassword;
    private JLabel mensageHelp;
    private JLabel clickhere;
    private JButton buttonEnter;

    
    // Caminhos das imagem Icons
    private final  String _pathIconUser = "src/main/java/resources/img/user.png";
    private final  String _pathIconBlock = "src/main/java/resources/img/block.png";
    private final  String _pathIconVisibility0ff = "src/main/java/resources/img/visibility0ff.png";
    
    /*ImageIcon _pathIconUser = new ImageIcon("src/main/java/resources/img/user.png");*/    
    // Ícones
    private final IconTextField icoUser = new IconTextField(_pathIconUser, 40, 30);
    private final IconTextField icoPassBlock = new IconTextField(_pathIconBlock, 40, 30);
    private final IconTextField iconVisibility0ff;
    
    // Labels de erro
    private JLabel messageUser;
    private JLabel messagePass;
    
    
    public Login(){
        this.iconVisibility0ff = new IconTextField(_pathIconVisibility0ff, 40, 30);
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
        boxLogin.setPreferredSize(new java.awt.Dimension(400, 500)); // mantendo o tamanho da caixa com elementos
        
        // Criar o logo com tamanho personalizado
        ImageLogo imageLogo = new ImageLogo(192, 80); // metodo de redimensionamento da imagem do Logo
        imageLogo.setBounds(100, 50, 200, 80); // posicionamento e largura e altura
        boxLogin.add(imageLogo); // o add ao father
        
        createInputs(); // chamando os componentes de inputs para o login
        createButton(); // chamando o componente de btn
        
        panelBackground.add(boxLogin); // add ao father
        
    }
    
    
    protected void createInputs(){
        // Mensagens de erro
        messageUser = new JLabel("Usuário Inválido");
        messageUser.setFont(new Font("Arial", Font.PLAIN, 12));
        messageUser.setForeground(Color.red);
        messageUser.setBounds(60, 160, 100, 30);
        messageUser.setVisible(false);

        messagePass = new JLabel("Senha Inválida");
        messagePass.setFont(new Font("Arial", Font.PLAIN, 12));
        messagePass.setForeground(Color.red);
        messagePass.setBounds(60, 305, 100, 30);
        messagePass.setVisible(false);

        // =============================================
        // CAMPO USUÁRIO
        // =============================================
        userPanel = new RoundedPanel(20);
        userPanel.setLayout(null);
        userPanel.setBackground(Color.WHITE);
        userPanel.setRoundedBorder(Color.GRAY, 1);
        userPanel.setBounds(50, 190, 300, 50);

        // Configurar o icoUser (IconTextField)
        icoUser.setBounds(5, 10, 35, 30);

        // Criar o txtUser
        txtUser = new JTextField();
        txtUser.setBorder(null);
        txtUser.setBounds(45, 10, 245, 30);
        txtUser.setFont(new Font("Arial", Font.PLAIN, 15));

        // ADICIONAR AO PAINEL
        userPanel.add(icoUser);
        userPanel.add(txtUser);

        // =============================================
        // CAMPO SENHA
        // =============================================
        passUser = new RoundedPanel(20);
        passUser.setLayout(null);
        passUser.setBackground(Color.WHITE);
        passUser.setRoundedBorder(Color.GRAY, 1);
        passUser.setBounds(50, 259, 300, 50);

        // Configurar o icoPassBlock
        icoPassBlock.setBounds(5, 10, 35, 30);

        // Configurar o campo de senha
        jpfPassword = new JPasswordField();
        jpfPassword.setBorder(null);
        jpfPassword.setBounds(45, 10, 200, 30);
        jpfPassword.setFont(new Font("Arial", Font.PLAIN, 15));

        // Ícone de visibilidade
        iconVisibility0ff.setBounds(255, 10, 35, 30);
        
        // Mensage de click aqui pra recuperar acesso
        mensageHelp = new JLabel("Esqueceu a senha?");
        mensageHelp.setFont(new Font("Arial", Font.PLAIN, 12));
        mensageHelp.setForeground(Color.GRAY);
        mensageHelp.setBounds(110, 451, 200, 30);
        
        clickhere = new JLabel("Clique aqui.");
        clickhere.setFont(new Font("Arial", Font.BOLD, 12));
        clickhere.setForeground(Color.GRAY);
        clickhere.setBounds(220, 451, 100, 30);
        
        passUser.add(icoPassBlock);
        passUser.add(jpfPassword);
        passUser.add(iconVisibility0ff);

        boxLogin.add(clickhere);
        boxLogin.add(mensageHelp);
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
        buttonEnter.setBounds(100, 358, 200, 60);
        
        boxLogin.add(buttonEnter);
    }
   
    // =============================================
    // GETTERS para o Controller acessar os componentes
    // =============================================
    public javax.swing.JTextField getTxtUser(){return txtUser;}
    public javax.swing.JPasswordField getJpfPassword(){return jpfPassword;}
    public javax.swing.JButton getButtonEnter(){return buttonEnter;}
    public javax.swing.JLabel getMessageUser(){return messageUser;}
    public javax.swing.JLabel getMessagePass(){return messagePass;}
    public javax.swing.JLabel getMensageHelp(){return mensageHelp;}
    public javax.swing.JLabel getClickHere(){return clickhere;}
    public RoundedPanel getBoxLogin() { return boxLogin;}
    public RoundedPanel getUserPanel(){return userPanel;}
    public RoundedPanel getPassUser(){return passUser;}
    public IconTextField getIcoUser(){return icoUser;}
    public IconTextField getIcoPassBlock(){return icoPassBlock;}
    public IconTextField getIconVisibility0ff(){return iconVisibility0ff;}
}
