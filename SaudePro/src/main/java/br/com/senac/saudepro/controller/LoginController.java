package br.com.senac.saudepro.controller;

import br.com.senac.saudepro.gui.Login;
import br.com.senac.saudepro.util.AuxiliaryMethod;
import br.com.senac.saudepro.util.IconTextField;
import br.com.senac.saudepro.util.RoundedPanel;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

public class LoginController {

    private final String PATH_USER_NORMAL = "src/main/java/resources/img/user.png";
    private final String PATH_USER_HOVER = "src/main/java/resources/img/userHover.png";
    private final String PATH_PASS_NORMAL = "src/main/java/resources/img/block.png";
    private final String PATH_PASS_HOVER = "src/main/java/resources/img/lockHover.png";
    private final String PATH_EYE_NORMAL = "src/main/java/resources/img/visibility0ff.png";
    private final String PATH_EYE_HOVER = "src/main/java/resources/img/visibilityOffHover.png";
    private final String PATH_EYE_VISIBLE = "src/main/java/resources/img/visibility.png";
    private final String PATH_EYE_VISIBLE_HOVER = "src/main/java/resources/img/visibilityHover.png";
    
    private final String PATH_USER_RED = "src/main/java/resources/img/personRed.png";
    private final String PATH_PASS_RED = "src/main/java/resources/img/lockRed.png";
    
    
    
    private Login viewLogin;
    
    private ImageIcon iconUserNormal;
    private ImageIcon iconUserHover;
    private ImageIcon iconPassNormal;
    private ImageIcon iconPassHover;
    private ImageIcon iconEyeNormal;
    private ImageIcon iconEyeHover;
    private ImageIcon iconEyeVisible;
    private ImageIcon iconEyeVisibleHover;
    
    private ImageIcon iconUserRed;
    private ImageIcon iconLockRed;
    
    // Estado da senha
    private boolean isPasswordVisible = false;
    private boolean isMensageHelp = false;
    
    private ScheduledExecutorService scheduler;
    
    private Dialog dialog;
   
    public LoginController(Login l) {
        this.viewLogin = l;
        loadIcones();
        initController();
    }
    
    private void loadIcones() {
        iconUserNormal = AuxiliaryMethod.loadedIcone(PATH_USER_NORMAL, 25, 25);
        iconUserHover = AuxiliaryMethod.loadedIcone(PATH_USER_HOVER, 25, 25);
        iconPassNormal = AuxiliaryMethod.loadedIcone(PATH_PASS_NORMAL, 25, 25);
        iconPassHover = AuxiliaryMethod.loadedIcone(PATH_PASS_HOVER, 25, 25);
        iconEyeNormal = AuxiliaryMethod.loadedIcone(PATH_EYE_NORMAL, 25, 25);
        iconEyeHover = AuxiliaryMethod.loadedIcone(PATH_EYE_HOVER, 25, 25);
        iconEyeVisible = AuxiliaryMethod.loadedIcone(PATH_EYE_VISIBLE, 25, 25);
        iconEyeVisibleHover = AuxiliaryMethod.loadedIcone(PATH_EYE_VISIBLE_HOVER, 25, 25);
        
        iconUserRed = AuxiliaryMethod.loadedIcone(PATH_USER_RED, 25, 25);
        iconLockRed = AuxiliaryMethod.loadedIcone(PATH_PASS_RED, 25, 25);
    }
    
    

    private void initController() {
        AuxiliaryMethod.setPlaceholder(viewLogin.getTxtUser(), "Usuário");
        AuxiliaryMethod.setPlaceholder(viewLogin.getJpfPassword(), "Senha");
        
        // Estado inicial
        viewLogin.getJpfPassword().setEchoChar('•');
        viewLogin.getIconVisibility0ff().setIcon(iconEyeNormal);
        
        // Hover do campo usuário
        aplicationHover(
            viewLogin.getTxtUser(),
            viewLogin.getIcoUser(),
            iconUserNormal,
            iconUserHover,
            viewLogin.getUserPanel()
        );
        
        // Hover do campo senha (apenas para o cadeado)
        aplicationHover(
            viewLogin.getJpfPassword(),
            viewLogin.getIcoPassBlock(),
            iconPassNormal,
            iconPassHover,
            viewLogin.getPassUser()
        );
        
        // Hover MANUAL para o olho (sem FocusListener conflitante)
        configarationEyeHover();
        
        // Clique para mostrar/esconder senha
        configarateEyeClick();
        
        // btn enter
        viewLogin.getButtonEnter().addActionListener(b -> btnEnter());
        
        // btn click help
        configHoverMessageHelp();
        configClickHere();
    }

    private void aplicationHover(JTextField campo, IconTextField iconField, ImageIcon normal, ImageIcon hover, RoundedPanel panel) {
        if (campo == null || iconField == null) return;
        
        campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                panel.setRoundedBorder(new Color(0x7ED348), 2);
                iconField.setIcon(hover);
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                panel.setRoundedBorder(Color.GRAY, 1);
                iconField.setIcon(normal);
            }
        });
    }
    
    // Hover MANUAL para o olho (mouse entra e sai)
    private void configarationEyeHover() { 
        viewLogin.getIconVisibility0ff().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (isPasswordVisible) {
                    viewLogin.getIconVisibility0ff().setIcon(iconEyeVisibleHover);
                } else {
                    viewLogin.getIconVisibility0ff().setIcon(iconEyeHover);
                }
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                if (isPasswordVisible) {
                    viewLogin.getIconVisibility0ff().setIcon(iconEyeVisible);
                } else {
                    viewLogin.getIconVisibility0ff().setIcon(iconEyeNormal);
                }
            }
        });
    }
    
    // Clique para mostrar/esconder senha
    private void configarateEyeClick() {
        viewLogin.getIconVisibility0ff().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                isPasswordVisible = !isPasswordVisible;
                
                if (isPasswordVisible) {
                    // Senha visível
                    viewLogin.getJpfPassword().setEchoChar((char) 0);
                    viewLogin.getIconVisibility0ff().setIcon(iconEyeVisible);
                } else {
                    // Senha oculta
                    viewLogin.getJpfPassword().setEchoChar('•');
                    viewLogin.getIconVisibility0ff().setIcon(iconEyeNormal);
                }
            }
        });
    }
    
    // Metodo Button Entrar
    private void btnEnter(){
        String user = viewLogin.getTxtUser().getText().trim();
        char[] senhaChar = viewLogin.getJpfPassword().getPassword();
        String pass = new String(senhaChar);
        
        java.util.Arrays.fill(senhaChar, '0');
        
        String placerUser = "Usuário";
        String placerPass = "Senha";
        
        scheduler  = Executors.newSingleThreadScheduledExecutor();
        // condicaçao se estiver os campos login e senha, vazio entra aqui 
        if(user.isEmpty() || user.contains(placerUser)){
            viewLogin.getMessageUser().setVisible(true); // exibi mensage de aviso
            
            AuxiliaryMethod.aplicateColorRed(viewLogin.getUserPanel(), viewLogin.getIcoUser(), iconUserRed); //aplica cor vermelho ao campo
            scheduler.schedule(() -> {  // Time para desabilitar elementos 
            
                viewLogin.getMessageUser().setVisible(false); // desabilita a mensagem de aviso
                AuxiliaryMethod.aplicateColorGray(viewLogin.getUserPanel(), viewLogin.getIcoUser(), iconUserNormal); // cor normal
            
            }, 4, TimeUnit.SECONDS);
        }
        
        if(pass.isEmpty() || pass.contains(placerPass)){
            viewLogin.getMessagePass().setVisible(true);
                        AuxiliaryMethod.aplicateColorRed(viewLogin.getPassUser(), viewLogin.getIcoPassBlock(), iconLockRed);
            scheduler.schedule(() -> { 
                
                viewLogin.getMessagePass().setVisible(false);
                AuxiliaryMethod.aplicateColorGray(viewLogin.getPassUser(), viewLogin.getIcoPassBlock(), iconPassNormal); 
            
            }, 4, TimeUnit.SECONDS);
            
        }
        
        scheduler.shutdown();
        
        
        // Criar IF pra outra janela
        if(!user.equals(placerUser) && !pass.equals(placerPass)){
            System.out.println("IR NOVA JANELA");
        }
    }
   
    // Metodo para o clickHere
    private void configClickHere(){
        
        viewLogin.getClickHere().addMouseListener(new MouseAdapter() {
            String mes = "Você será redirecionado ao suporte!";
            @Override
            public void mouseClicked(MouseEvent e) {
                AuxiliaryMethod.mostrarMensagemFlutuante(viewLogin, mes, 300, 80); // metodo auxiliar 
                
            }
        });
    }
    
    // metodo hover no click here
    private void configHoverMessageHelp(){ 
        viewLogin.getMensageHelp().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                viewLogin.getClickHere().setForeground(new Color(0x7ED348));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                viewLogin.getClickHere().setForeground(Color.gray);
            }
        });
        
        viewLogin.getClickHere().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                viewLogin.getClickHere().setForeground(new Color(0x7ED348));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                viewLogin.getClickHere().setForeground(Color.gray);
            }
        });
    }
    
    
    
}