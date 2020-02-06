package com.cours.ebenus.maven.ebenus.dao.entities;

import java.util.Date;

public class User {
    private Integer id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private Date dateNaissance;
    private Date dateCreation;
    private Date dateModification;
    private Role role;
    private Classe classe;
    
    public User() {
    }
    
    public User(Integer id, String nom, String prenom, String email, String password, Date dateNaissance, Date dateCreation, Date dateModification, Role role, Classe classe) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.dateNaissance = dateNaissance;
        this.dateCreation = dateCreation;
        this.dateModification = dateModification;
        this.role = role;
        this.classe = classe;
    }

    public User(Integer id, String nom, String prenom, String email, String password, Date dateNaissance, Role role, Classe classe) {
        this(id, nom, prenom, email, password, dateNaissance, null, null, role, classe);
    }

    public User(String nom, String prenom, String email, String password, Date dateNaissance, Role role, Classe classe) {
        this(null, nom, prenom, email, password, dateNaissance, null, null, role, classe);
    }
    
    public User(String nom, String prenom, String email, String password, Date dateNaissance, Classe classe) {
        this(null, nom, prenom, email, password, dateNaissance, null, null, null, classe);
    }
    
    public User(String nom, String prenom, String email, String password, Date dateNaissance, Role role) {
        this(null, nom, prenom, email, password, dateNaissance, null, null, role, null);
    }

    public User(String nom, String prenom, String email, String password, Date dateNaissance) {
        this(null, nom, prenom, email, password, dateNaissance, null, null, null, null);
    }

    public User(Integer idUtilisateur) {
        this(idUtilisateur, null, null, null, null, null, null, null, null, null);
    }

    
    public Integer getId() {
        return (this.id);
    }

    public void setId(Integer id) {
        this.id = id;
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
    
    public String getEmail() {
        return (this.email);
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return (this.password);
    }

    public void setPassword(String password) {
        this.password = password;
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
    
    public Role getRole() {
        return (this.role);
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    public Classe getClasse() {
        return (this.classe);
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String toString = String.format("\n[id=%s, prenom=%s, nom=%s, email=%s, password=%s, dateNaissance=%s,dateCreation=%s, dateModification=%s", id, prenom, nom, email, password, dateNaissance, dateCreation, dateModification);
        if (role != null) {
            toString += String.format(" ,role=%s ", role);
        }
        if (classe != null) {
        	toString += String.format(" ,classe=%s]\n", classe);
        }
        return toString;
    }

}
