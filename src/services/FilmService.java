/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.IDao;
import model.Film;
import connexion.Connexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Original Computer
 */
public class FilmService implements IDao<Film> {

    @Override
    public boolean create(Film o) {
        try {
            String req = "INSERT INTO film (titre, genre, duree, realisateur) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setString(1, o.getTitre());
            ps.setString(2, o.getGenre());
            ps.setInt(3, o.getDuree());
            ps.setString(4, o.getRealisateur());

            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(FilmService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean update(Film o) {
        try {
            String req = "UPDATE film SET titre = ?, genre = ?, duree = ?, realisateur = ? WHERE id = ?";
            PreparedStatement ps;
            ps = Connexion.getConnection().prepareStatement(req);
            ps.setString(1, o.getTitre());
            ps.setString(2, o.getGenre());
            ps.setInt(3, o.getDuree());
            ps.setString(4, o.getRealisateur());
            ps.setInt(5, o.getId());

            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(FilmService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    @Override
    public boolean delete(Film o) {
        try {
            String req = "DELETE FROM film WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, o.getId());

            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(FilmService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Film findById(int id) {
        try {
            String req = "SELECT * FROM film WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Film(
                        rs.getInt("id"),
                        rs.getString("titre"),
                        rs.getString("genre"),
                        rs.getInt("duree"),
                        rs.getString("realisateur")
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(FilmService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public List<Film> findAll() {
        List<Film> films = new ArrayList<>();
        try {
            String req = "SELECT * FROM film";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                films.add(new Film(
                        rs.getInt("id"),
                        rs.getString("titre"),
                        rs.getString("genre"),
                        rs.getInt("duree"),
                        rs.getString("realisateur")
                ));
            }

        } catch (SQLException ex) {
            Logger.getLogger(FilmService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return films;
    }

    /*public List<Film> filtrerParGenre(String genre) {
        return findAll().stream()
                .filter(f -> f.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }*/

}
