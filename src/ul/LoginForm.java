/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ul;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import model.Utilisateur;
import services.UtilisateurService;

/**
 *
 * @author Original Computer
 */
public class LoginForm extends javax.swing.JFrame {

    UtilisateurService utilisateurService = new UtilisateurService();
    Utilisateur user;
    //private Object desktopPane; 

    /**
     * Creates new form LoginForm
     */
    public LoginForm() {
        initComponents();

        this.setTitle("Connexion - Gestion Cinéma");
        setLocationRelativeTo(null);

        // -------- HOVER loginBtn (rouge) --------
        loginBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginBtn.setBackground(new java.awt.Color(200, 40, 40)); // rouge + foncé
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginBtn.setBackground(new java.awt.Color(229, 57, 53)); // rouge normal
            }
        });

        // -------- HOVER registerBtn (bleu) --------
        registerBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerBtn.setBackground(new java.awt.Color(10, 90, 180)); // bleu + foncé
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerBtn.setBackground(new java.awt.Color(25, 118, 210)); // bleu normal
            }
        });

        // -------- PLACEHOLDER EMAIL --------
        emailTxt.setText("Email");
        emailTxt.setForeground(new java.awt.Color(160, 160, 160));

        emailTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (emailTxt.getText().equals("Email")) {
                    emailTxt.setText("");
                    emailTxt.setForeground(java.awt.Color.WHITE);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (emailTxt.getText().isEmpty()) {
                    emailTxt.setText("Email");
                    emailTxt.setForeground(new java.awt.Color(160, 160, 160));
                }
            }
        });

        // -------- PLACEHOLDER MOT DE PASSE --------
        passwordTxt.setText("Mot de passe");
        passwordTxt.setEchoChar((char) 0); // montrer texte
        passwordTxt.setForeground(new java.awt.Color(160, 160, 160));

        passwordTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                String pass = new String(passwordTxt.getPassword());
                if (pass.equals("Mot de passe")) {
                    passwordTxt.setText("");
                    passwordTxt.setEchoChar('•'); // cacher
                    passwordTxt.setForeground(java.awt.Color.WHITE);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                String pass = new String(passwordTxt.getPassword());
                if (pass.isEmpty()) {
                    passwordTxt.setText("Mot de passe");
                    passwordTxt.setEchoChar((char) 0);
                    passwordTxt.setForeground(new java.awt.Color(160, 160, 160));
                }
            }
        });

        // Taille fixe (proche photo)
        setSize(900, 700);
        setResizable(false);

        // Fond sombre
        mainPanel.setBackground(new java.awt.Color(10, 10, 10));

        // Titre blanc
        jLabel1.setForeground(java.awt.Color.WHITE);

        // (optionnel) cacher les labels Email/Mdp
        jLabel2.setForeground(java.awt.Color.WHITE);
        passwordLabel.setForeground(java.awt.Color.WHITE);

        // Champs sombres avec bord gris
        emailTxt.setBackground(new java.awt.Color(20, 20, 20));
        emailTxt.setForeground(java.awt.Color.WHITE);
        emailTxt.setCaretColor(java.awt.Color.WHITE);
        emailTxt.setBorder(javax.swing.BorderFactory.createLineBorder(
                new java.awt.Color(120, 120, 120), 1, true));

        passwordTxt.setBackground(new java.awt.Color(20, 20, 20));
        passwordTxt.setForeground(java.awt.Color.WHITE);
        passwordTxt.setCaretColor(java.awt.Color.WHITE);
        passwordTxt.setBorder(javax.swing.BorderFactory.createLineBorder(
                new java.awt.Color(120, 120, 120), 1, true));

        // Bouton rouge style cinéma
        loginBtn.setBackground(new java.awt.Color(229, 57, 53)); // #E53935
        loginBtn.setForeground(java.awt.Color.WHITE);
        loginBtn.setFocusPainted(false);
        loginBtn.setBorderPainted(false);

        // Bouton créer compte (bleu)
        registerBtn.setBackground(new java.awt.Color(25, 118, 210)); // #1976D2
        registerBtn.setForeground(java.awt.Color.WHITE);
        registerBtn.setFocusPainted(false);
        registerBtn.setBorderPainted(false);

        // Lien oublié blanc
        forgotPasswordBtn.setForeground(java.awt.Color.WHITE);
        forgotPasswordBtn.setContentAreaFilled(false);
        forgotPasswordBtn.setBorderPainted(false);
        forgotPasswordBtn.setFocusPainted(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        mainPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        emailTxt = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        forgotPasswordBtn = new javax.swing.JButton();
        loginBtn = new javax.swing.JButton();
        registerBtn = new javax.swing.JButton();
        passwordTxt = new javax.swing.JPasswordField();
        logoLabel = new javax.swing.JLabel();

        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Connexion - Gestion Cinéma");

        mainPanel.setBackground(new java.awt.Color(10, 10, 10));
        mainPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mainPanel.setForeground(new java.awt.Color(10, 10, 10));
        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Connexion Cinéma");
        jLabel1.setAlignmentX(320.0F);
        jLabel1.setAlignmentY(210.0F);
        mainPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 210, 300, 40));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mainPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(244, 205, 122, -1));

        emailTxt.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        emailTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailTxtActionPerformed(evt);
            }
        });
        mainPanel.add(emailTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 280, 520, 50));

        passwordLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mainPanel.add(passwordLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(244, 271, 122, -1));

        forgotPasswordBtn.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        forgotPasswordBtn.setForeground(new java.awt.Color(255, 255, 255));
        forgotPasswordBtn.setText("Mot de passe oublié ?");
        forgotPasswordBtn.setBorderPainted(false);
        forgotPasswordBtn.setContentAreaFilled(false);
        forgotPasswordBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        forgotPasswordBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forgotPasswordBtnActionPerformed(evt);
            }
        });
        mainPanel.add(forgotPasswordBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 580, 250, 30));

        loginBtn.setBackground(new java.awt.Color(0, 153, 51));
        loginBtn.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        loginBtn.setForeground(new java.awt.Color(255, 255, 255));
        loginBtn.setText("Se connecter");
        loginBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });
        mainPanel.add(loginBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 470, 520, 40));

        registerBtn.setBackground(new java.awt.Color(51, 102, 255));
        registerBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        registerBtn.setForeground(new java.awt.Color(255, 255, 255));
        registerBtn.setText("Créer un compte");
        registerBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        registerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerBtnActionPerformed(evt);
            }
        });
        mainPanel.add(registerBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 520, 520, 40));

        passwordTxt.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        passwordTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordTxtActionPerformed(evt);
            }
        });
        mainPanel.add(passwordTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 360, 520, 50));

        logoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoLabel.setIcon(new javax.swing.ImageIcon("C:\\Users\\Original Computer\\Desktop\\log1.png")); // NOI18N
        mainPanel.add(logoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, 200, 110));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 915, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 403, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 808, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void passwordTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordTxtActionPerformed

    private void registerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerBtnActionPerformed
        // TODO add your handling code here:
        RegisterForm rf = new RegisterForm();
        rf.setVisible(true);

    }//GEN-LAST:event_registerBtnActionPerformed

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
        // TODO add your handling code here:
        String email = emailTxt.getText().trim();
        String password = new String(passwordTxt.getPassword());

        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Veuillez entrer votre nom d'utilisateur !",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            emailTxt.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Veuillez entrer votre mot de passe !",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            passwordTxt.requestFocus();
            return;
        }

        Utilisateur user = utilisateurService.authenticate(email, password);

        if (user != null) {
            JOptionPane.showMessageDialog(this, "Bienvenue " + user.getEmail() + " !");
            // Ici tu peux ouvrir ta fenêtre principale

            this.dispose();
            java.awt.EventQueue.invokeLater(() -> {
                new Main().setVisible(true);
            });

        } else {
            JOptionPane.showMessageDialog(this, "Email ou mot de passe incorrect !");
            passwordTxt.setText("");
            emailTxt.requestFocus();
        }
    }//GEN-LAST:event_loginBtnActionPerformed

    private void forgotPasswordBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forgotPasswordBtnActionPerformed
        // TODO add your handling code here:
        ForgotPasswordDialog dialog = new ForgotPasswordDialog(this, true);
        dialog.setVisible(true);
    }//GEN-LAST:event_forgotPasswordBtnActionPerformed

    private void emailTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailTxtActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField emailTxt;
    private javax.swing.JButton forgotPasswordBtn;
    private javax.swing.JButton jButton2;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton loginBtn;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPasswordField passwordTxt;
    private javax.swing.JButton registerBtn;
    // End of variables declaration//GEN-END:variables
}
