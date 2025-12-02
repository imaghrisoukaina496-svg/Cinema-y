/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Original Computer
 */
public class Film {

    private int id;
    private String titre;
    private String genre;
    private int duree;
    private String realisateur;

    public Film() {
    }

    public Film(int id, String titre, String genre, int duree, String realisateur) {
        this.id = id;
        this.titre = titre;
        this.genre = genre;
        this.duree = duree;
        this.realisateur = realisateur;
    }

    public Film(String titre, String genre, int duree, String realisateur) {
        this.titre = titre;
        this.genre = genre;
        this.duree = duree;
        this.realisateur = realisateur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }

    @Override
    public String toString() {
        return titre;
    }

}
