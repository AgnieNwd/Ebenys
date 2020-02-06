SET FOREIGN_KEY_CHECKS = 0; 
DROP TABLE IF EXISTS Utilisateur;
DROP TABLE IF EXISTS Role;



 CREATE TABLE Role (
	idRole INTEGER PRIMARY KEY AUTO_INCREMENT,
	identifiant VARCHAR(50),
	description VARCHAR(400),
	version int(15) DEFAULT 0
 )ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
 CREATE TABLE Utilisateur (
	idUtilisateur INTEGER PRIMARY KEY AUTO_INCREMENT,
	idRole INTEGER NOT NULL,
	civilite VARCHAR(100),
	prenom VARCHAR(100),
	nom VARCHAR(100),
	identifiant VARCHAR(100),
	motPasse VARCHAR(100),
	dateNaissance TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	dateCreation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	dateModification TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	actif TINYINT(1) DEFAULT 1,
	marquerEffacer  TINYINT(1) DEFAULT 0,
	version INTEGER DEFAULT 1,
	CONSTRAINT `FK_Utilisateur_Role` FOREIGN KEY (idRole) referenceS Role (idRole)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8;


/* Insertion des Roles */ 
INSERT INTO Role(identifiant,description) VALUES ('Administrateur','Le rôle administrateur');
INSERT INTO Role(identifiant,description) VALUES ('Directeur','Le rôle de directeur de magasin');
INSERT INTO Role(identifiant,description) VALUES ('Standard','Le rôle standard');
INSERT INTO Role(identifiant,description) VALUES ('Vendeur','Le rôle vendeur');
INSERT INTO Role(identifiant,description) VALUES ('Acheteur','Le rôle Acheteur');


/* Utilisateurs avec le role Administrateur  */ 
INSERT INTO Utilisateur(civilite,prenom,nom,idRole,identifiant,motPasse) VALUES ('Mr','Nicolas','Gruber',(SELECT idRole FROM Role WHERE identifiant LIKE  'Administrateur'),'nicolas.gruber@gmail.com','passw0rd');
INSERT INTO Utilisateur(civilite,prenom,nom, idRole,identifiant,motPasse) VALUES ('Mr','Jerome','Cantin',(SELECT idRole FROM Role WHERE identifiant LIKE  'Administrateur'),'admin@gmail.com','passw0rd');
INSERT INTO Utilisateur(civilite,prenom,nom,idRole,identifiant,motPasse) VALUES ('Mr','Kamel','Petit',(SELECT idRole FROM Role WHERE identifiant LIKE  'Administrateur'),'kamel.petit@gmail.com','passw0rd');

/*  Utilisateurs avec le role Directeur  */  
INSERT INTO Utilisateur(civilite,prenom,nom,idRole,identifiant,motPasse) VALUES ('Mr','Eric','Lalitte',(SELECT idRole FROM Role WHERE identifiant LIKE  'Directeur'),'eric.lallite@gmail.com','passw0rd');
INSERT INTO Utilisateur(civilite,prenom,nom,idRole,identifiant,motPasse) VALUES ('Mr','Laurent','Bordet',(SELECT idRole FROM Role WHERE identifiant LIKE  'Directeur'),'laurent.bordet@gmail.com','passw0rd');
INSERT INTO Utilisateur(civilite,prenom,nom,idRole,identifiant,motPasse) VALUES ('Mr','Jimmy','Petit',(SELECT idRole FROM Role WHERE identifiant LIKE  'Directeur'),'jimmy.petit@gmail.com','passw0rd');
/*  Utilisateurs avec le role Standard  */  
INSERT INTO Utilisateur(civilite,prenom,nom,idRole,identifiant,motPasse) VALUES ('Mr','Nicolas','Petit',(SELECT idRole FROM Role WHERE identifiant LIKE  'Standard'),'nicolas.petit@gmail.com','passw0rd');
INSERT INTO Utilisateur(civilite,prenom,nom,idRole,identifiant,motPasse) VALUES ('Mr','Paul','Gilbert',(SELECT idRole FROM Role WHERE identifiant LIKE  'Standard'),'paul.gilbert@gmail.com','passw0rd');
INSERT INTO Utilisateur(civilite,prenom,nom,idRole,identifiant,motPasse) VALUES ('Mr','Xavier','Dupont',(SELECT idRole FROM Role WHERE identifiant LIKE  'Standard'),'xavier.dupont@gmail.com','passw0rd');
INSERT INTO Utilisateur(civilite,prenom,nom,idRole,identifiant,motPasse) VALUES ('Mr','Thierry','Marec',(SELECT idRole FROM Role WHERE identifiant LIKE  'Standard'),'thierry.marec@gmail.com','passw0rd');
INSERT INTO Utilisateur(civilite,prenom,nom,idRole,identifiant,motPasse) VALUES ('Mme','Aurelie','Cassas',(SELECT idRole FROM Role WHERE identifiant LIKE  'Standard'),'aurelie.cassas@gmail.com','passw0rd');
INSERT INTO Utilisateur(civilite,prenom,nom,idRole,identifiant,motPasse) VALUES ('Mr','Alain','Cassas',(SELECT idRole FROM Role WHERE identifiant LIKE  'Standard'),'alain.cassas@gmail.com','passw0rd');
INSERT INTO Utilisateur(civilite,prenom,nom,idRole,identifiant,motPasse) VALUES ('Mr','Julien','Bille',(SELECT idRole FROM Role WHERE identifiant LIKE  'Standard'),'julien.bille@gmail.com','passw0rd');

/*  Utilisateurs avec le role Acheteur  */  
INSERT INTO Utilisateur(civilite,prenom,nom,idRole,identifiant,motPasse) VALUES ('Mr','Nicolas','Berger',(SELECT idRole FROM Role WHERE identifiant LIKE  'Acheteur'),'nicolas.berger@gmail.com','passw0rd');
INSERT INTO Utilisateur(civilite,prenom,nom,idRole,identifiant,motPasse) VALUES ('Mr','Thomas','corgnet',(SELECT idRole FROM Role WHERE identifiant LIKE  'Acheteur'),'thomas.corgnet@gmail.com','passw0rd');
INSERT INTO Utilisateur(civilite,prenom,nom,idRole,identifiant,motPasse) VALUES ('Mr','Harry','Cover',(SELECT idRole FROM Role WHERE identifiant LIKE  'Acheteur'),'harry.cover@gmail.com','passw0rd');
INSERT INTO Utilisateur(civilite,prenom,nom,idRole,identifiant,motPasse) VALUES ('Mr','Mathieu','Ducamp',(SELECT idRole FROM Role WHERE identifiant LIKE  'Acheteur'),'mathieu.ducamp@gmail.com','passw0rd');
INSERT INTO Utilisateur(civilite,prenom,nom,idRole,identifiant,motPasse) VALUES ('Mr','Serge','Lama',(SELECT idRole FROM Role WHERE identifiant LIKE  'Acheteur'),'serge.lama@gmail.com','passw0rd');
INSERT INTO Utilisateur(civilite,prenom,nom,idRole,identifiant,motPasse) VALUES ('Mme','Mathilde','Leduc',(SELECT idRole FROM Role WHERE identifiant LIKE  'Acheteur'),'doudou.leduc@gmail.com','passw0rd');
INSERT INTO Utilisateur(civilite,prenom,nom,idRole,identifiant,motPasse) VALUES ('Mr','André','Naline',(SELECT idRole FROM Role WHERE identifiant LIKE  'Acheteur'),'andré.aline@gmail.com','passw0rd');

