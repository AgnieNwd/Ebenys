package com.cours.ebenus.maven.ebenus.dao.entities;

public class Classe {
    private Integer idClasse;
    private String libelleClasse;
    
    public Classe() {
    }

    public Classe(Integer idClasse, String libelleClasse) {
        this.idClasse = idClasse;
        this.libelleClasse = libelleClasse;
    }

    public Classe( String libelleClasse) {
        this(null, libelleClasse);
    }

    public Classe(Integer idClasse) {
        this(idClasse, null);
    }

    public Integer getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(Integer idClasse) {
        this.idClasse = idClasse;
    }

    public String getLibelleClasse() {
        return libelleClasse;
    }

    public void setLibelleClasse(String libelleClasse) {
        this.libelleClasse = libelleClasse;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClasse != null ? idClasse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Classe)) {
            return false;
        }
        Classe other = (Classe) object;
        if ((this.idClasse == null && other.idClasse != null) || (this.idClasse != null && !this.idClasse.equals(other.idClasse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
    	String toString =  String.format("[idClasse=%s, libelleClasse=%s]", idClasse, libelleClasse);
        return toString;

    }

}
