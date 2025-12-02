/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.IDao;
import model.Seance;
import model.Film;
import model.Salle;
import connexion.Connexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Original Computer
 */
public class SeanceService implements IDao<Seance> {

    private FilmService filmService = new FilmService();
    private SalleService salleService = new SalleService();

    @Override
    public boolean create(Seance o) {
        try {
            // Vérifier doublon : même salle + même date
            String checkReq = "SELECT COUNT(*) FROM seance WHERE salle_id = ? AND dateProjection = ?";
            PreparedStatement checkPs = Connexion.getConnection().prepareStatement(checkReq);
            checkPs.setInt(1, o.getSalle().getId());
            checkPs.setTimestamp(2, Timestamp.valueOf(o.getDateProjection()));
            ResultSet rsCheck = checkPs.executeQuery();

            if (rsCheck.next() && rsCheck.getInt(1) > 0) {
                throw new IllegalArgumentException("Cette salle a déjà une séance à cette date !");
            }

            // Vérifier capacité
            if (o.getTicketsVendus() > o.getSalle().getCapacite()) {
                throw new IllegalArgumentException("Tickets vendus dépassent la capacité de la salle !");
            }

            String req = "INSERT INTO seance (film_id, salle_id, dateProjection, prix, ticketsVendus) "
                    + "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, o.getFilm().getId());
            ps.setInt(2, o.getSalle().getId());
            ps.setTimestamp(3, Timestamp.valueOf(o.getDateProjection()));
            ps.setDouble(4, o.getPrix());
            ps.setInt(5, o.getTicketsVendus());
            ps.executeUpdate();

            ps.close();
            checkPs.close();
            rsCheck.close();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(SeanceService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean update(Seance o) {
        try {
            // Vérifier doublon
            String checkReq = "SELECT COUNT(*) FROM seance WHERE salle_id = ? AND dateProjection = ? AND id != ?";
            PreparedStatement checkPs = Connexion.getConnection().prepareStatement(checkReq);
            checkPs.setInt(1, o.getSalle().getId());
            checkPs.setTimestamp(2, Timestamp.valueOf(o.getDateProjection()));
            checkPs.setInt(3, o.getId());
            ResultSet rsCheck = checkPs.executeQuery();

            if (rsCheck.next() && rsCheck.getInt(1) > 0) {
                throw new IllegalArgumentException("Cette salle a déjà une séance à cette date !");
            }

            // Vérifier capacité
            if (o.getTicketsVendus() > o.getSalle().getCapacite()) {
                throw new IllegalArgumentException("Tickets vendus dépassent la capacité !");
            }

            String req = "UPDATE seance SET film_id = ?, salle_id = ?, dateProjection = ?, prix = ?, ticketsVendus = ? "
                       + "WHERE id = ?";

            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, o.getFilm().getId());
            ps.setInt(2, o.getSalle().getId());
            ps.setTimestamp(3, Timestamp.valueOf(o.getDateProjection()));
            ps.setDouble(4, o.getPrix());
            ps.setInt(5, o.getTicketsVendus());
            ps.setInt(6, o.getId());
            ps.executeUpdate();

            ps.close();
            checkPs.close();
            rsCheck.close();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(SeanceService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean delete(Seance o) {
        try {
            String req = "DELETE FROM seance WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, o.getId());
            ps.executeUpdate();
            ps.close();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(SeanceService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Seance findById(int id) {
        try {
            String req = "SELECT * FROM seance WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Film film = filmService.findById(rs.getInt("film_id"));
                Salle salle = salleService.findById(rs.getInt("salle_id"));

                Seance s = new Seance(
                        rs.getInt("id"),
                        film,
                        salle,
                        rs.getTimestamp("dateProjection").toLocalDateTime(),
                        rs.getDouble("prix"),
                        rs.getInt("ticketsVendus")
                );

                rs.close();
                ps.close();
                return s;
            }

            rs.close();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(SeanceService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Seance> findAll() {
        List<Seance> list = new ArrayList<>();

        try {
            String req = "SELECT * FROM seance";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Film film = filmService.findById(rs.getInt("film_id"));
                Salle salle = salleService.findById(rs.getInt("salle_id"));

                Seance s = new Seance(
                        rs.getInt("id"),
                        film,
                        salle,
                        rs.getTimestamp("dateProjection").toLocalDateTime(),
                        rs.getDouble("prix"),
                        rs.getInt("ticketsVendus")
                );

                list.add(s);
            }

            rs.close();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(SeanceService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    // ------------------------------
    //       Méthodes additionnelles
    // ------------------------------

    public boolean vendreTickets(int seanceId, int nb) {
        Seance s = findById(seanceId);
        if (s == null) return false;

        if (s.getTicketsVendus() + nb > s.getSalle().getCapacite()) {
            throw new IllegalArgumentException("Capacité de la salle dépassée !");
        }

        s.setTicketsVendus(s.getTicketsVendus() + nb);
        return update(s);
    }

    public List<Seance> filtrerParDate(LocalDate date) {
        return findAll().stream()
                .filter(s -> s.getDateProjection().toLocalDate().equals(date))
                .collect(Collectors.toList());
    }

    // Recettes par mois (calcul dynamique)
    public Map<String, Double> recettesParMois() {
        Map<String, Double> map = new HashMap<>();

        for (Seance s : findAll()) {
            String cle = s.getDateProjection().getYear() + "-" + s.getDateProjection().getMonthValue();
            double recette = s.getPrix() * s.getTicketsVendus();

            map.put(cle, map.getOrDefault(cle, 0.0) + recette);
        }

        return map;
    }
}
