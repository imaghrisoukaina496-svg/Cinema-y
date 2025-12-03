# 🎬 Projet Cinéma — Application Java Swing + DAO + JDBC

## 📌 Description
Ce projet consiste à développer une application **Java Swing** de gestion d’un cinéma, connectée à une base de données relationnelle (**MySQL ou PostgreSQL**) via **JDBC**.  
L’application permet de gérer la programmation des films, les séances, les salles, et d’assurer un suivi des ventes et recettes avec un module de recherche/filtrage et une visualisation graphique.

---

## ✅ Fonctionnalités principales

### 🎥 Gestion des Films
- Ajouter / modifier / supprimer un film
- Afficher la liste des films
- Informations gérées :
  - `titre`
  - `genre`
  - `duree`
  - `realisateur`

### 🏛️ Gestion des Salles
- Ajouter / modifier / supprimer une salle
- Afficher la liste des salles
- Informations gérées :
  - `nom`
  - `capacite`

### 📅 Gestion des Séances
- Ajouter / modifier / supprimer une séance
- Associer un film et une salle à une séance
- Suivre les ventes de billets
- Informations gérées :
  - `film`
  - `salle`
  - `dateProjection`
  - `prix`
  - `ticketsVendus`

---

## 🔒 Règles métiers
- **Empêcher les doublons de séances**  
  ⇒ Une séance ayant le même `film`, la même `salle` et la même `dateProjection` est interdite.
- **Calculer les recettes automatiquement**  
  ⇒ `recettes = prix × ticketsVendus`
- **Suivre les ventes de billets** par séance.
- **Gérer la programmation** des films dans le cinéma.

---

## 🔎 Recherche & Filtrage
- Filtrer les données par :
  - **genre**
  - **date de projection**
- Implémentation attendue :
  - `TableRowSorter` côté Swing
  - ou requêtes SQL dynamiques côté DAO

---

## 📊 Statistiques
- Graphique intégré : **Recettes par mois**
- Bibliothèque recommandée : **JFreeChart** (ou équivalent)
- Accès via bouton / panneau "Statistiques"

---

## 🗃️ Schéma relationnel (simplifié)

### Table `Film`
| Champ | Type | Contraintes |
|------|------|-------------|
| id| INT | PK, auto-incrément |
| titre | VARCHAR | NOT NULL |
| genre | VARCHAR | NOT NULL |
| duree | INT | NOT NULL |
| realisateur | VARCHAR | NOT NULL |

### Table `Salle`
| Champ | Type | Contraintes |
|------|------|-------------|
| id| INT | PK, auto-incrément |
| nom | VARCHAR | NOT NULL |
| capacite | INT | NOT NULL |

### Table `Seance`
| Champ | Type | Contraintes |
|------|------|-------------|
| id | INT | PK, auto-incrément |
| film_id | INT | FK → Film(id_film) |
| salle_id | INT | FK → Salle(id_salle) |
| dateProjection | TIMESTAMP/DATE | NOT NULL |
| prix | DOUBLE | NOT NULL |
| ticketsVendus | INT | NOT NULL |
---


## 🧱 Architecture du projet (MVC léger)
Cinema
 └── Source Packages
     │
     ├── connexion
     │    └── Connexion.java
     │
     ├── model
     │    ├── Film.java
     │    ├── Salle.java
     │    ├── Seance.java
     │    └── Utilisateur.java
     │
     ├── dao
     │    ├── IDao.java
     ├── services
     │    ├── FilmService.java
     │    ├── SalleService.java
     │    ├── SeanceService.java
     │    └── UtilisateurService.java
     │
     └── ul
          ├── LoginForm.java
          ├── RegisterForm.java
          ├── ForgotPasswordDialog.java
          │
          ├── FilmForm.java
          ├── SalleForm.java
          ├── SeanceForm.java
          │
          ├── FiltrageSeanceForm.java
          └── RecettesChartForm.java

## Créer la base de données
CREATE DATABASE IF NOT EXISTS cinema;
USE cinema;

CREATE TABLE Film (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titre VARCHAR(100) NOT NULL,
    genre VARCHAR(50),
    duree INT NOT NULL,  -- durée en minutes
    realisateur VARCHAR(100)
);

CREATE TABLE Salle (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL UNIQUE,
    capacite INT NOT NULL
);

CREATE TABLE Seance (
    id INT AUTO_INCREMENT PRIMARY KEY,
    film_id INT NOT NULL,
    salle_id INT NOT NULL,
    dateProjection DATETIME NOT NULL,
    prix DOUBLE NOT NULL,
    ticketsVendus INT DEFAULT 0,

    -- Relations
    FOREIGN KEY (film_id) REFERENCES Film(id) ON DELETE CASCADE,
    FOREIGN KEY (salle_id) REFERENCES Salle(id) ON DELETE CASCADE,

    -- Empêcher les doublons de séance (même film, même salle, même date)
    UNIQUE (film_id, salle_id, dateProjection)
);

CREATE TABLE utilisateur (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(150) NOT NULL
);

## Configurer la connexion JDBC
    private static String url = "jdbc:mysql://localhost:3306/cinema?     useSSL=false&serverTimezone=UTC";
    private static String login = "root";
    private static String password = "";
    private static Connection connection = null;


[](url)
