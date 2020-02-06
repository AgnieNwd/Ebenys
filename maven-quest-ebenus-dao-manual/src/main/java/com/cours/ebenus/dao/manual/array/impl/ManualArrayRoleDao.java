/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.manual.array.impl;

import com.cours.ebenus.dao.IRoleDao;
import com.cours.ebenus.dao.entities.Role;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class ManualArrayRoleDao /*extends AbstractArrayDao<Role>*/ implements IRoleDao {

    private static final Log log = LogFactory.getLog(ManualArrayRoleDao.class);
    private Role[] rolesArrayDataSource = null;

    //public ManualArrayRoleDao() {
    //    super(Role.class, DataSource.getInstance().getRolesArrayDataSource());
    //}
    /**
     * Méthode qui retourne la liste de tous les rôles de la database (ici
     * rolesArrayDataSource)
     *
     * @return La liste de tous les rôles de la database
     */
    @Override
    public List<Role> findAllRoles() {
        return null;
    }

    /**
     * Méthode qui retourne le rôle d'id passé en paramètre de la database (ici
     * rolesArrayDataSource)
     *
     * @param idRole L'id du rôle à récupérer
     * @return Le rôle d'id passé en paramètre, null si non trouvé
     */
    @Override
    public Role findRoleById(int idRole) {
        return null;
    }

    /**
     * Méthode qui retourne une liste de tous les rôles de la database dont
     * l'identifiant est égal au paramètre passé
     *
     * @param identifiantRole L'identifiant des rôles à récupérer
     * @return Une liste de tous les rôles dont l'identifiant est égal au
     * paramètre passé
     */
    @Override
    public List<Role> findRoleByIdentifiant(String identifiantRole) {
        return null;
    }

    /**
     * Méthode qui permet d'ajouter à rôle dans la database (ici
     * rolesArrayDataSource)
     *
     * @param role Le rôle à ajouter
     * @return Le rôle ajouté ou null si échec
     */
    @Override
    public Role createRole(Role role) {
        return null;
    }

    /**
     * Méthode qui permet d'update un rôle existant dans la database (ici
     * rolesArrayDataSource)
     *
     * @param role Le rôle à modifier
     * @return Le rôle modifié ou null si ce dernier n'existe pas dans la
     * database
     */
    @Override
    public Role updateRole(Role role) {
        return null;
    }

    /**
     * Méthode qui permet de supprimer un rôle existant dans la database (ici
     * rolesArrayDataSource)
     *
     * @param role Le rôle à supprimer
     * @return True si suppression effectuée, false sinon
     */
    @Override
    public boolean deleteRole(Role role) {
        return false;
    }
}
