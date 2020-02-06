package com.cours.ebenus.maven.ebenus.dao.entities;

public class Matiere {
    private Integer idMatiere;
    private String libelleMatiere;
    private User user;
    
    public Matiere() {
    }

    public Matiere(Integer idMatiere, String libelleMatiere, User user) {
        this.idMatiere = idMatiere;
        this.libelleMatiere = libelleMatiere;
        this.user = user;
    }

    public Matiere(Integer idMatiere, String libelleMatiere) {
        this(idMatiere, libelleMatiere, null);
    }
    public Matiere(String libelleMatiere, User user) {
        this(null, libelleMatiere, user);
    }

    public Matiere(Integer idMatiere) {
        this(idMatiere, null, null);
    }

    public Integer getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(Integer idMatiere) {
        this.idMatiere = idMatiere;
    }

    public String getLibelleMatiere() {
        return libelleMatiere;
    }

    public void setLibelleMatiere(String libelleMatiere) {
        this.libelleMatiere = libelleMatiere;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMatiere != null ? idMatiere.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Matiere)) {
            return false;
        }
        Matiere other = (Matiere) object;
        if ((this.idMatiere == null && other.idMatiere != null) || (this.idMatiere != null && !this.idMatiere.equals(other.idMatiere))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
    	String toString =  String.format("[idMatiere=%s, libelleMatiere=%s]", idMatiere, libelleMatiere);
        if (user != null) {
            toString += String.format(" ,user=%s ", user);
        }
        return toString;

    }
}
