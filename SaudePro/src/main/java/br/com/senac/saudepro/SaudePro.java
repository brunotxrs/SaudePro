package br.com.senac.saudepro;

import br.com.senac.saudepro.controller.LoginController;
import br.com.senac.saudepro.gui.Login;

/**
 * Class principal inicar o programa
 * @author bruno-teixeira
 */
public class SaudePro {
     // remover apos testes
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Login.class.getName());

    public static void main(String[] args) {
        // ira rodar a gui principal
        viewLogin();
        
    }
    
    
    private static void viewLogin(){
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
            Login l = new Login();
            
            new LoginController(l);
            
            
            l.setVisible(true);
            
                    
        });
    }
}
