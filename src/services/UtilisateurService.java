/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import connexion.Connexion;
import java.security.SecureRandom;
import model.Utilisateur;
import java.sql.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class UtilisateurService {

    // Authentification (LOGIN)
    public Utilisateur authenticate(String email, String password) {
        try {
            String sql = "SELECT * FROM utilisateur WHERE email = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password");

                if (PasswordUtil.checkPassword(password, storedHash)) {
                    return new Utilisateur(
                            rs.getInt("id"),
                            rs.getString("email"),
                            storedHash
                    );
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur Authentification : " + e.getMessage());
        }
        return null;

    }

    // Création de compte
    public boolean register(Utilisateur u) {
        try {
            String check = "SELECT * FROM utilisateur WHERE email = ?";
            PreparedStatement ps1 = Connexion.getConnection().prepareStatement(check);
            ps1.setString(1, u.getEmail());
            ResultSet rs = ps1.executeQuery();

            if (rs.next()) {
                return false; // email existe déjà
            }

            // ✅ hachage du mot de passe ici
            String hashed = PasswordUtil.hashPassword(u.getPassword());

            String sql = "INSERT INTO utilisateur (email, password) VALUES (?, ?)";
            PreparedStatement ps2 = Connexion.getConnection().prepareStatement(sql);

            ps2.setString(1, u.getEmail());
            ps2.setString(2, hashed);

            ps2.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Erreur Register : " + e.getMessage());
        }
        return false;
    }

    // Récupération du mot de passe
    public String recoverPassword(String email) {
        try {
            String sql = "SELECT password FROM utilisateur WHERE email = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("password");
            }
        } catch (SQLException e) {
            System.out.println("Erreur recoverPassword : " + e.getMessage());
        }
        return null;
    }

    //Ajouter une méthode emailExists
    public boolean emailExists(String email) {
        try {
            String sql = "SELECT 1 FROM utilisateur WHERE email = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            return rs.next(); // true si trouvé

        } catch (SQLException e) {
            System.out.println("Erreur emailExists : " + e.getMessage());
        }
        return false;
    }

    //Ajouter une méthode pour générer un nouveau mot de passe
    public String generateRandomPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    //Ajouter une méthode pour mettre à jour le password
    public boolean updatePassword(String email, String newPassword) {
        try {
            String hashed = PasswordUtil.hashPassword(newPassword);

            String sql = "UPDATE utilisateur SET password = ? WHERE email = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);

            ps.setString(1, hashed);
            ps.setString(2, email);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Erreur updatePassword : " + e.getMessage());
        }
        return false;

    }

    //la fonction d’envoi
    public boolean sendNewPasswordByEmail(String toEmail, String newPass) {

        final String fromEmail = "soukainaimaghri09@gmail.com";  // ton gmail
        final String appPassword = "mvom plyy fooy dlzh"; // celui généré par Google

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        javax.mail.Session session = javax.mail.Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(fromEmail, appPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Récupération de mot de passe");
            message.setText(
                    "Bonjour,\n\nVotre nouveau mot de passe est : " + newPass
                    + "\n\nVeuillez le changer après connexion."
            );

            Transport.send(message);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean recoverAndSendNewPassword(String email) {
        if (!emailExists(email)) {
            return false;
        }

        String newPass = generateRandomPassword(10);

        boolean updated = updatePassword(email, newPass);
        if (!updated) {
            return false;
        }

        return sendNewPasswordByEmail(email, newPass);
    }

}
