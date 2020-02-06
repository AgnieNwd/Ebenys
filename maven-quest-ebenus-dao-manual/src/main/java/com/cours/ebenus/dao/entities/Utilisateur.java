/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.entities;

import java.util.Date;

/**
 *
 * @author elhad
 */
public class Utilisateur {

    private static final long serialVersionUID = 1L;
    private Integer idUtilisateur;
    private String civilite;
    private String prenom;
    private String nom;
    private String identifiant;
    private String motPasse;
    private Date dateNaissance;
    private Date dateCreation;
    private Date dateModification;
    private Boolean actif = true;
    private Boolean marquerEffacer = false;
    private Integer version = 0;
    private Role role;

    public Utilisateur() {
    }

    public Utilisateur(Integer idUtilisateur, String civilite, String prenom, String nom, String identifiant, String motPasse, Date dateNaissance, Date dateCreation, Date dateModification, Boolean actif, Boolean marquerEffacer, Integer version, Role role) {
        this.idUtilisateur = idUtilisateur;
        this.civilite = civilite;
        this.prenom = prenom;
        this.nom = nom;
        this.identifiant = identifiant;
        this.motPasse = motPasse;
        this.dateNaissance = dateNaissance;
        this.dateCreation = dateCreation;
        this.dateModification = dateModification;
        this.actif = actif;
        this.marquerEffacer = marquerEffacer;
        this.version = version;
        this.role = role;
    }

    public Utilisateur(Integer idUtilisateur, String civilite, String prenom, String nom, String identifiant, String motPasse, Date dateNaissance, Boolean actif, Boolean marquerEffacer, Integer version, Role role) {
        this(idUtilisateur, civilite, prenom, nom, identifiant, motPasse, dateNaissance, null, null, true, false, 0, role);
    }

    public Utilisateur(Integer idUtilisateur, String civilite, String prenom, String nom, String identifiant, String motPasse, Date dateNaissance, Role role) {
        this(idUtilisateur, civilite, prenom, nom, identifiant, motPasse, dateNaissance, null, null, true, false, 0, role);
    }

    public Utilisateur(String civilite, String prenom, String nom, String identifiant, String motPasse, Date dateNaissance, Role role) {
        this(null, civilite, prenom, nom, identifiant, motPasse, dateNaissance, null, null, true, false, 0, role);
    }

    public Utilisateur(String civilite, String prenom, String nom, String identifiant, String motPasse, Date dateNaissance) {
        this(null, civilite, prenom, nom, identifiant, motPasse, dateNaissance, null, null, true, false, 0, null);
    }

    public Utilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Integer getIdUtilisateur() {
        return (this.idUtilisateur);
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getCivilite() {
        return (this.civilite);
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getPrenom() {
        return (this.prenom);
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return (this.nom);
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getIdentifiant() {
        return (this.identifiant);
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMotPasse() {
        return (this.motPasse);
    }

    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }

    public Date getDateNaissance() {
        return (this.dateNaissance);
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Date getDateCreation() {
        return (this.dateCreation);
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateModification() {
        return (this.dateModification);
    }

    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }

    public Boolean isActif() {
        return (this.actif);
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    public Boolean isMarquerEffacer() {
        return (this.marquerEffacer);
    }

    public void setMarquerEffacer(Boolean marquerEffacer) {
        this.marquerEffacer = marquerEffacer;
    }

    public Integer getVersion() {
        return (this.version);
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Role getRole() {
        return (this.role);
    }

    public void setRole(Role role) {
        this.role = role;
    }
 
    @Override
    public String toString() {
	  return "id: " + idUtilisateur + ", civilite: " + civilite+", prenom: "+prenom+", nom: "+nom+", identifiant: "+identifiant+", dateNaissance: "+dateNaissance+", dateCreation: "+dateCreation+", dateMosdification: "+dateModification+", actif: "+actif+", version: "+version+", ROLE: "+role;  
	}
    
	public boolean equals(int id) {
	    return this.getIdUtilisateur() == id;
    }

	@Override
	public int hashCode() {
		int hash = 7;
	    hash = 31 * hash + (int) this.idUtilisateur;
	    return hash;
	}
}
