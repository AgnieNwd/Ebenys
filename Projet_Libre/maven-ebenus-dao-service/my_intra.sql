DROP DATABASE IF EXISTS my_intra;
/*DROP USER IF EXISTS 'application'@'localhost';*/
CREATE DATABASE my_intra DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

GRANT ALL ON my_intra.* TO 'application'@'localhost' IDENTIFIED BY 'passw0rd';
USE my_intra;

SET FOREIGN_KEY_CHECKS = 0; 
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Role;
DROP TABLE IF EXISTS Note;
DROP TABLE IF EXISTS Matiere;
DROP TABLE IF EXISTS Classe;

CREATE TABLE `User` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `nom` varchar(64),
  `prenom` varchar(64),
  `email` varchar(128) UNIQUE,
  `password` varchar(255),
  `date_naissance` timestamp DEFAULT CURRENT_TIMESTAMP,
  `date_creation` timestamp DEFAULT CURRENT_TIMESTAMP,
  `date_modification` timestamp DEFAULT CURRENT_TIMESTAMP,
  `id_role` int,
  `id_classe` int
);

CREATE TABLE `Role` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `identifiant` varchar(128),
  `description` varchar(255)
);

CREATE TABLE `Note` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `libelle_note` double,
  `id_user` int, 
  `id_matiere` int
);

CREATE TABLE `Matiere` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `libelle_matiere` varchar(128),
  `id_user` int
);

CREATE TABLE `Classe` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `libelle_classe` varchar(255)
);

ALTER TABLE `User` ADD FOREIGN KEY (`id_role`) REFERENCES `Role` (`id`);

ALTER TABLE `Note` ADD FOREIGN KEY (`id_user`) REFERENCES `User` (`id`) ON DELETE CASCADE;

ALTER TABLE `Note` ADD FOREIGN KEY (`id_matiere`) REFERENCES `Matiere` (`id`) ON DELETE CASCADE;

ALTER TABLE `Matiere` ADD FOREIGN KEY (`id_user`) REFERENCES `User` (`id`);

ALTER TABLE `User` ADD FOREIGN KEY (`id_classe`) REFERENCES `Classe` (`id`);


/* Insertion des Roles */ 
INSERT INTO Role(identifiant,description) VALUES ('Administrateur','Le rôle administrateur');
INSERT INTO Role(identifiant,description) VALUES ('Directeur','Le rôle de directeur de l\'ecole');
INSERT INTO Role(identifiant,description) VALUES ('Professeur','Le rôle de professeur');
INSERT INTO Role(identifiant,description) VALUES ('Eleve','Le rôle d\'eleve');

/* Insertion des Classe */ 
INSERT INTO Classe(libelle_classe) VALUES ('aucune');
INSERT INTO Classe(libelle_classe) VALUES ('4e');
INSERT INTO Classe(libelle_classe) VALUES ('5e');

/* Utilisateurs nobody  */ 
INSERT INTO User(Prenom, Nom, id_classe) VALUES ('Non', 'assigné', (SELECT id FROM Classe WHERE libelle_classe LIKE  'aucune'));

/* Utilisateurs avec le role Administrateur  */ 
INSERT INTO User(Prenom,Nom,id_role,Email,Password, id_classe) VALUES ('Nicolas','Gruber',(SELECT id FROM Role WHERE identifiant LIKE  'Administrateur'),'nicolas.gruber@gmail.com','password', (SELECT id FROM Classe WHERE libelle_classe LIKE  'aucune'));

/* Users avec le role Directeur  */ 
INSERT INTO User(Prenom,Nom,id_role,Email,Password, id_classe) VALUES ('Harry','West',(SELECT id FROM Role WHERE identifiant LIKE  'Directeur'),'harry.west@gmail.com','password', (SELECT id FROM Classe WHERE libelle_classe LIKE  'aucune'));


/* Users avec le role Professeur  */ 
INSERT INTO User(Prenom,Nom,id_role,Email,Password, id_classe) VALUES ('Laurent','Chassin',(SELECT id FROM Role WHERE identifiant LIKE  'Professeur'),'lau.rent@gmail.com','password', (SELECT id FROM Classe WHERE libelle_classe LIKE  '4e'));
INSERT INTO User(Prenom,Nom,id_role,Email,Password, id_classe) VALUES ('Ellie','Mendez',(SELECT id FROM Role WHERE identifiant LIKE  'Professeur'),'ellie@gmail.com','password', (SELECT id FROM Classe WHERE libelle_classe LIKE  '4e'));
INSERT INTO User(Prenom,Nom,id_role,Email,Password, id_classe) VALUES ('Joe','Will',(SELECT id FROM Role WHERE identifiant LIKE  'Professeur'),'jj@gmail.com','password', (SELECT id FROM Classe WHERE libelle_classe LIKE  '4e'));

/* Users avec le role Eleve  */ 
INSERT INTO User(Prenom,Nom,id_role,Email,Password, id_classe) VALUES ('Nicolas','Wita',(SELECT id FROM Role WHERE identifiant LIKE  'Eleve'),'nico@gmail.com','password', (SELECT id FROM Classe WHERE libelle_classe LIKE  '4e'));
INSERT INTO User(Prenom,Nom,id_role,Email,Password, id_classe) VALUES ('Peter','Parker',(SELECT id FROM Role WHERE identifiant LIKE  'Eleve'),'peter@gmail.com','password', (SELECT id FROM Classe WHERE libelle_classe LIKE  '4e'));
INSERT INTO User(Prenom,Nom,id_role,Email,Password, id_classe) VALUES ('Gwen','Stacy',(SELECT id FROM Role WHERE identifiant LIKE  'Eleve'),'gwenstacy@gmail.com','password', (SELECT id FROM Classe WHERE libelle_classe LIKE  '4e'));

/* Insertion des Matiere */ 
INSERT INTO Matiere(libelle_matiere,id_user) VALUES ('Francais',(SELECT id FROM User WHERE email LIKE 'lau.rent@gmail.com'));
INSERT INTO Matiere(libelle_matiere,id_user) VALUES ('Mathématiques',(SELECT id FROM User WHERE email LIKE 'jj@gmail.com'));
INSERT INTO Matiere(libelle_matiere,id_user) VALUES ('Espagnol',(SELECT id FROM User WHERE email LIKE 'ellie@gmail.com'));

/* Insertion des Notes */ 
INSERT INTO Note(libelle_note,id_user,id_matiere) VALUES ('16',(SELECT id FROM User WHERE email LIKE 'nico@gmail.com'),(SELECT id FROM Matiere WHERE libelle_matiere LIKE 'Francais'));
INSERT INTO Note(libelle_note,id_user,id_matiere) VALUES ('12',(SELECT id FROM User WHERE email LIKE 'peter@gmail.com'),(SELECT id FROM Matiere WHERE libelle_matiere LIKE 'Francais'));
INSERT INTO Note(libelle_note,id_user,id_matiere) VALUES ('18',(SELECT id FROM User WHERE email LIKE 'gwenstacy@gmail.com'),(SELECT id FROM Matiere WHERE libelle_matiere LIKE 'Francais'));
INSERT INTO Note(libelle_note,id_user,id_matiere) VALUES ('9',(SELECT id FROM User WHERE email LIKE 'nico@gmail.com'),(SELECT id FROM Matiere WHERE libelle_matiere LIKE 'Mathématiques'));
INSERT INTO Note(libelle_note,id_user,id_matiere) VALUES ('19',(SELECT id FROM User WHERE email LIKE 'peter@gmail.com'),(SELECT id FROM Matiere WHERE libelle_matiere LIKE 'Mathématiques'));
INSERT INTO Note(libelle_note,id_user,id_matiere) VALUES ('18.5',(SELECT id FROM User WHERE email LIKE 'gwenstacy@gmail.com'),(SELECT id FROM Matiere WHERE libelle_matiere LIKE 'Mathématiques'));
INSERT INTO Note(libelle_note,id_user,id_matiere) VALUES ('11',(SELECT id FROM User WHERE email LIKE 'nico@gmail.com'),(SELECT id FROM Matiere WHERE libelle_matiere LIKE 'Espagnol'));
INSERT INTO Note(libelle_note,id_user,id_matiere) VALUES ('15',(SELECT id FROM User WHERE email LIKE 'peter@gmail.com'),(SELECT id FROM Matiere WHERE libelle_matiere LIKE 'Espagnol'));
INSERT INTO Note(libelle_note,id_user,id_matiere) VALUES ('15',(SELECT id FROM User WHERE email LIKE 'gwenstacy@gmail.com'),(SELECT id FROM Matiere WHERE libelle_matiere LIKE 'Espagnol'));
INSERT INTO Note(libelle_note,id_user,id_matiere) VALUES ('16',(SELECT id FROM User WHERE email LIKE 'nico@gmail.com'),(SELECT id FROM Matiere WHERE libelle_matiere LIKE 'Francais'));
INSERT INTO Note(libelle_note,id_user,id_matiere) VALUES ('12',(SELECT id FROM User WHERE email LIKE 'peter@gmail.com'),(SELECT id FROM Matiere WHERE libelle_matiere LIKE 'Francais'));
INSERT INTO Note(libelle_note,id_user,id_matiere) VALUES ('18',(SELECT id FROM User WHERE email LIKE 'gwenstacy@gmail.com'),(SELECT id FROM Matiere WHERE libelle_matiere LIKE 'Francais'));
INSERT INTO Note(libelle_note,id_user,id_matiere) VALUES ('9',(SELECT id FROM User WHERE email LIKE 'nico@gmail.com'),(SELECT id FROM Matiere WHERE libelle_matiere LIKE 'Mathématiques'));
INSERT INTO Note(libelle_note,id_user,id_matiere) VALUES ('19',(SELECT id FROM User WHERE email LIKE 'peter@gmail.com'),(SELECT id FROM Matiere WHERE libelle_matiere LIKE 'Mathématiques'));
INSERT INTO Note(libelle_note,id_user,id_matiere) VALUES ('18.5',(SELECT id FROM User WHERE email LIKE 'gwenstacy@gmail.com'),(SELECT id FROM Matiere WHERE libelle_matiere LIKE 'Mathématiques'));
INSERT INTO Note(libelle_note,id_user,id_matiere) VALUES ('11',(SELECT id FROM User WHERE email LIKE 'nico@gmail.com'),(SELECT id FROM Matiere WHERE libelle_matiere LIKE 'Espagnol'));
INSERT INTO Note(libelle_note,id_user,id_matiere) VALUES ('15',(SELECT id FROM User WHERE email LIKE 'peter@gmail.com'),(SELECT id FROM Matiere WHERE libelle_matiere LIKE 'Espagnol'));
INSERT INTO Note(libelle_note,id_user,id_matiere) VALUES ('15',(SELECT id FROM User WHERE email LIKE 'gwenstacy@gmail.com'),(SELECT id FROM Matiere WHERE libelle_matiere LIKE 'Espagnol'));

