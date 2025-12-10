# Système de Gestion E-Learning en Java
Une application E-Learning développée en Java Swing, construite selon une structure de type MVC.  
Le système permet de gérer les étudiants, les cours, ainsi que les affectations étudiant–cours via une interface simple et intuitive.

---

## Vidéo de Démonstration
Une vidéo de démonstration sera ajoutée ici pour présenter le fonctionnement du programme ainsi que ses principales fonctionnalités.




https://github.com/user-attachments/assets/a5e71010-ecd8-4032-a6c5-77b6f5113750


---

## Fonctionnalités

### Gestion des Étudiants
- Ajouter de nouveaux étudiants  
- Modifier les informations d’un étudiant  
- Supprimer un étudiant  
- Afficher la liste complète des étudiants  
- Logique gérée via la classe `StudentService`

### Gestion des Cours
- Ajouter de nouveaux cours  
- Modifier les cours existants  
- Supprimer des cours  
- Afficher la liste des cours  
- Logique gérée via la classe `CoursService`

### Affectation Étudiant–Cours
- Assigner un étudiant à un cours  
- Afficher toutes les relations étudiant–cours  
- Géré via `StudentCoursService`

### Interface Utilisateur (Java Swing)
- Tableau de bord et formulaires créés avec NetBeans GUI Builder  
- Panneaux, boutons et tableaux connectés à la couche de services  
- Interface claire et bien organisée

---
## MCD (Modèle Conceptuel de Données)

Relation :  
Un étudiant peut être inscrit à zéro ou plusieurs cours.  
Un cours peut être suivi par zéro ou plusieurs étudiants.

Schéma :

                ┌──────────────┐
                │   STUDENT     │
                ├──────────────┤
                │ id : int      │
                │ name : varchar│
                │ email : varchar│
                │ date : date   │
                └───────┬──────┘
                        │ 0,N
                        │
                        │
                        │ 0,N
                ┌───────┴────────┐
                │  STUDENTCOURS   │
                ├─────────────────┤       
                │ date_inscription│
                │ score : int     │
                └───────┬────────┘
                        │ 0,N
                        │
                        │
                        │ 0,N
                ┌───────┴────────┐
                │     COURS       │
                ├─────────────────┤
                │ id : int        │
                │ categorie: varchar │
                │ title : varchar │
                │ nb_H : int      │
                └─────────────────┘

## Structure du Projet
Ci-dessous, l’arborescence du projet, protégée afin d’être affichée correctement sur GitHub et dans les navigateurs :

````text
e-learning/
│
├── src/
│   ├── Connexion/          # Classe de connexion (préparation à la base de données)
│   ├── dao/                # Interfaces (IDao)
│   ├── entities/           # Modèles : Student, Course, StudentCourse
│   ├── services/           # Logique métier et opérations CRUD
│   ├── ui/                 # Interface utilisateur Java Swing
│   └── test/               # Classes de tests
│
├── build/                  # Fichiers générés par NetBeans
├── dist/                   # Fichiers JAR empaquetés
├── nbproject/              # Métadonnées du projet NetBeans
├── build.xml               # Script Ant
└── manifest.mf             # Fichier manifeste
````
## Architecture
<img width="1536" height="1024" alt="image" src="https://github.com/user-attachments/assets/c9721bf9-578f-4623-90b0-239db74bf773" />





## Technologies Utilisées
- Java (Swing)
- NetBeans GUI Builder
- Système de build Ant
- Inno Setup (pour générer l’installateur Windows)
- Structure prête pour JDBC

---

## Installation

### 1. Télécharger l’Installateur
Le projet est distribué sous forme d’un exécutable Windows créé avec Inno Setup.

Fichier à télécharger :

e-learning.exe

---

### 2. Lancer l’Installateur
- Double-cliquer sur `e-learning.exe`
- Suivre l’assistant d’installation (Suivant → Installer → Terminer)
- Un raccourci sur le bureau sera créé automatiquement si cette option est activée pendant l’installation

### 3. Lancer l’Application
Vous pouvez exécuter le programme :
- Depuis le raccourci du bureau  
- Depuis le dossier d’installation généré par l’installateur

---

## À Propos de l’Installateur (Inno Setup)
L’application utilise Inno Setup pour générer un installateur Windows professionnel, offrant :
- Création automatique des dossiers d’installation  
- Raccourcis Bureau et Menu Démarrer optionnels  
- Désinstallation propre  
- Expérience d’installation simple et intuitive  
- Exécutable prêt à être distribué  

---

## Architecture du Projet

Le projet est conçu pour être facilement maintenable :

- `entities/` contient les modèles POJO  
- `services/` contient la logique métier et les opérations CRUD  
- `ui/` regroupe toutes les interfaces graphiques et la gestion des événements  
- `dao/` définit la structure d’accès aux données  
- `Connexion/` prépare le projet pour une future intégration de base de données  

---

## Améliorations Futures
- Amélioration de l’interface utilisateur  
- Ajout de rapports (PDF/Excel)  
- Ajout de rôles (administrateur, instructeur, étudiant)
---

## Author
Name : Abdelhadi el mezouari  
Cours : Java orienté objet  
Date : Décembre 2025  
Encadré par : Pr. Mohamed LACHGAR
