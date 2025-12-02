/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.IDao;
import model.Salle;
import connexion.Connexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Original Computer
 */
public class SalleService implements IDao<Salle> {

    @Override
    public boolean create(Salle o) {
        try {
            String req = "INSERT INTO salle (nom, capacite) VALUES (?, ?);";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setString(1, o.getNom());
            ps.setInt(2, o.getCapacite());
            ps.executeUpdate();
            ps.close();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(FilmService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean update(Salle o) {
        try {
            String req = "UPDATE salle SET nom = ?, capacite = ? WHERE id = ?";
            PreparedStatement ps;
            ps = Connexion.getConnection().prepareStatement(req);
            ps.setString(1, o.getNom());
            ps.setInt(2, o.getCapacite());
            ps.setInt(3, o.getId());
            ps.executeUpdate();
            ps.close();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(FilmService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    @Override
    public boolean delete(Salle o) {
        try {
            String req = "DELETE FROM salle WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, o.getId());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SalleService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    @Override
    public Salle findById(int id) {
        try {
            String req = "SELECT * FROM salle WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Salle salle = new Salle(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getInt("capacite")
                );
                rs.close();
                ps.close();
                return salle;
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(SalleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public List<Salle> findAll() {
        List<Salle> salles = new ArrayList<>();
        try {
            String req = "SELECT * FROM salle";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                salles.add(new Salle(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getInt("capacite")
                ));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(SalleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return salles;

    }

    

}
