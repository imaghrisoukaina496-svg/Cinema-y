# 🎬 Projet Cinéma — Application Java Swing + DAO + JDBC

## 📌 Description
Ce projet consiste à développer une application **Java Swing** de gestion d’un cinéma, connectée à une base de données relationnelle (**MySQL**) via **JDBC**.  
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
- Bibliothèque recommandée : **JFreeChart** 
- Accès via bouton / panneau "Fonctionalite"

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
| film_id | INT | FK → Film(id_film) |
| salle_id | INT | FK → Salle(id_salle) |
| dateProjection | TIMESTAMP/DATE | NOT NULL |
| prix | DOUBLE | NOT NULL |
| ticketsVendus | INT | NOT NULL |
---


## 🧱 Architecture du projet (MVC léger)
```md
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
```

## Créer la base de données
```md
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
```
🧩 MCD (Merise) — Projet Cinéma
Entités
<img width="800" height="472" alt="MCD" src="https://github.com/user-attachments/assets/c6e8090d-a041-45dd-b97f-9b32d5e34ee8" />

FILM

#idFilm

titre

genre

duree

realisateur

SALLE

#idSalle

nom

capacite

SEANCE

#idSeance

dateProjection

prix

ticketsVendus

recettes (calculée : prix × ticketsVendus)

UTILISATEUR (optionnel — authentification)

#idUser

email

password

Associations et cardinalités

PROGRAMMER (projection d’un film)

FILM (1,N) — PROGRAMMER — SEANCE (1,1)

➡️ Un film peut être programmé dans plusieurs séances.
➡️ Une séance concerne un seul film.

SE_DEROULE_DANS (lieu de projection)

SALLE (1,N) — SE_DEROULE_DANS — SEANCE (1,1)

➡️ Une salle peut accueillir plusieurs séances.
➡️ Une séance se déroule dans une seule salle.

Contrainte métier

Unicité d’une séance :
Il est interdit d’avoir deux séances avec :

le même film

la même salle

la même dateProjection

➡️ (film, salle, dateProjection) doit être unique.

<img width="1536" height="1024" alt="structur" src="https://github.com/user-attachments/assets/357981a4-b4b9-442e-a9f4-5cd973b94bc1" />

    
## Installation et Execution
https://github.com/user-attachments/assets/5caedcc9-165c-48a5-a010-12e9d8c0bdff

## Démonstration dans NetBeans
https://github.com/user-attachments/assets/60e4be62-dc43-4f39-b0d7-b8a4c07f0ff9

## Auteur
- **Nom :** Soukaina Imaghri  
- **Projet :** Conception et réalisation d’un projet de cinéma avec NetBeans  
- **Date :** Décembre 2025  
- **Encadré par :** Pr. Mohamed LACHGAR  
