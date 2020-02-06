/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.entities;

/**
 *
 * @author elhad
 */
public class Role {

    private static final long serialVersionUID = 1L;
    private Integer idRole;
    private String identifiant;
    private String description;
    private Integer version = 0;

    public Role() {
    }

    public Role(Integer idRole, String identifiant, String description) {
        this.idRole = idRole;
        this.identifiant = identifiant;
        this.description = description;
    }

    public Role(String identifiant, String description) {
        this(null, identifiant, description);
    }

    public Role(Integer idRole) {
        this(idRole, null, null);
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getIdentifiant() {
        return (this.identifiant);
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getDescription() {
        return (this.description);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
    
    @Override
    public String toString() {
	  return "id: "+ idRole + " | identifiant: "+ identifiant+" | description: "+description+" | version: "+version;  
	}
    
    public boolean equals(int id) {
        if(this.getIdRole() == id)
            return true;
        else
            return false;
    }
    
    @Override
	public int hashCode() {
		int hash = 7;
	    hash = 31 * hash + this.idRole;
	    return hash;
	}
    
}
