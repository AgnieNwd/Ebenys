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
        this(idUtilisateur, null, null, null, null, null, null, null, null, true, false, 0, null);
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMotPasse() {
        return motPasse;
    }

    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateModification() {
        return dateModification;
    }

    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }

    public Boolean isActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    public Boolean isMarquerEffacer() {
        return marquerEffacer;
    }

    public void setMarquerEffacer(Boolean marquerEffacer) {
        this.marquerEffacer = marquerEffacer;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUtilisateur != null ? idUtilisateur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        if ((this.idUtilisateur == null && other.idUtilisateur != null) || (this.idUtilisateur != null && !this.idUtilisateur.equals(other.idUtilisateur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String toString = String.format("\n[idUtilisateur=%s, civilite=%s, prenom=%s, nom=%s, identifiant=%s, motPasse=%s, dateNaissance=%s,dateCreation=%s, dateModification=%s, actif=%s, marquerEffacer=%s", idUtilisateur, civilite, prenom, nom, identifiant, motPasse, dateNaissance, dateCreation, dateModification, actif, marquerEffacer);
        if (role != null) {
            toString += String.format(" ,role=%s ", role);
        }
        toString += String.format(" ,version=%s]\n", version);
        return toString;
    }
}
