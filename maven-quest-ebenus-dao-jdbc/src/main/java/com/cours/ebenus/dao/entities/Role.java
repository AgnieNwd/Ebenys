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

    public Role(Integer idRole, String identifiant, String description, Integer version) {
        this.idRole = idRole;
        this.identifiant = identifiant;
        this.description = description;
        this.version = version;
    }

    public Role(Integer idRole, String identifiant, String description) {
        this(idRole, identifiant, description, null);
    }

    public Role(String identifiant, String description) {
        this(null, identifiant, description, null);
    }

    public Role(Integer idRole) {
        this(idRole, null, null, null);
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getDescription() {
        return description;
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
    public int hashCode() {
        int hash = 0;
        hash += (idRole != null ? idRole.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        if ((this.idRole == null && other.idRole != null) || (this.idRole != null && !this.idRole.equals(other.idRole))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("[idRole=%s, identifiant=%s, description=%s]", idRole, identifiant, description);
    }

}
