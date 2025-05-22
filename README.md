# HelloEvents-App
Projet : Application Web de Réservation d’Événements
Contexte
En tant que développeur junior, tu fais partie d’une équipe chargée de concevoir et développer une application web destinée à la réservation d’événements. Cette application doit répondre aux besoins des clients (utilisateurs finaux) et des administrateurs, avec une interface sécurisée et performante.

1. Fonctionnalités pour les Clients
1.1 Page d’Accueil
Afficher une liste d’événements disponibles.

Chaque événement doit afficher les informations essentielles : nom, date, lieu, catégorie, description courte, image.

1.2 Inscription & Connexion
Permettre la création d’un compte utilisateur (inscription).

Authentification sécurisée des utilisateurs via login (connexion).

Gestion du profil utilisateur : récupération et mise à jour des données personnelles (nom, email, mot de passe, etc.).

1.3 Recherche et Filtrage des Événements
Recherche par mots-clés.

Filtrage avancé par date, lieu, catégorie d’événement, prix, etc.

1.4 Réservation d’Événement
Permettre à un utilisateur de réserver une ou plusieurs places pour un événement.

Gestion du stock de places disponibles.

Confirmation de la réservation.

1.5 Page "À Propos"
Afficher des informations sur l’équipe derrière l’application.

Présenter les valeurs et la mission de l’entreprise.

2. Fonctionnalités pour les Administrateurs
2.1 Tableau de Bord
Statistiques sur les utilisateurs (nombre d’inscriptions, nombre de réservations).

Statistiques sur les événements (nombre de ventes, événements populaires).

2.2 Gestion des Comptes Clients
Liste des clients inscrits.

Possibilité de supprimer un compte client en cas de besoin.

2.3 Gestion des Événements
Création d’un nouvel événement.

Mise à jour des détails d’un événement existant.

Suppression d’un événement.

Consultation des détails complets d’un événement.

3. Technologies à Utiliser
Backend : Spring Boot avec Spring MVC

Sécurité : Spring Security avec JWT pour authentification et autorisation

Base de données : MySQL ou PostgreSQL (au choix)

Gestion des données : Spring Data JPA (ORM)

Tests unitaires : JUnit

4. Architecture générale suggérée
Modèle MVC (Model-View-Controller) pour la séparation des responsabilités.

Entités principales : Utilisateur, Événement, Réservation.

Services : Gestion des utilisateurs, gestion des événements, gestion des réservations, statistiques.

Contrôleurs REST exposant les API pour le frontend.

Sécurité : JWT pour sécuriser les endpoints et gérer les rôles (client, admin).

***Diagramme uml ***
*diagramme de use case

<img width="481" alt="image" src="https://github.com/user-attachments/assets/7667e73a-bf73-45fa-9b4e-c28cf42aeeec" />


*diagramme de class 

<img width="384" alt="image" src="https://github.com/user-attachments/assets/52e207dd-6b82-4a91-95c0-af5cc34f9e4e" />

diagramme de sequence

<img width="434" alt="image" src="https://github.com/user-attachments/assets/3662dfce-26bd-4c2e-86f2-c9571c92be50" />


 


