/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author Original Computer
 */
public class Seance {

    private int id;
    private Film film;
    private Salle salle;
    private LocalDateTime dateProjection;
    private double prix;
    private int ticketsVendus;
    


    public Seance() {
    }

    public Seance(int id, Film film, Salle salle, LocalDateTime dateProjection, double prix, int ticketsVendus) {
        this.id = id;
        this.film = film;
        this.salle = salle;
        this.dateProjection = dateProjection;
        this.prix = prix;
        this.ticketsVendus = ticketsVendus;
         
    }
    
    

    public Seance(Film film, Salle salle, LocalDateTime dateProjection, double prix, int ticketsVendus) {
        this.film = film;
        this.salle = salle;
        this.dateProjection = dateProjection;
        this.prix = prix;
        this.ticketsVendus = ticketsVendus;
         
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public LocalDateTime getDateProjection() {
        return dateProjection;
    }

    public void setDateProjection(LocalDateTime dateProjection) {
        this.dateProjection = dateProjection;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getTicketsVendus() {
        return ticketsVendus;
    }

    public void setTicketsVendus(int ticketsVendus) {
        this.ticketsVendus = ticketsVendus;
    }

    @Override
    public String toString() {
        return film.getTitre() + " - " + dateProjection.toString();
    }

}
