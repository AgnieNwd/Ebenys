package com.cours.ebenus.dao.test;

import java.util.Date;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;

import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.exception.EbenusException;
import com.cours.ebenus.utils.Constants;

public class JUnitUtilisateurDao extends JUnitQuestEbenus {

    private static final Log log = LogFactory.getLog(JUnitUtilisateurDao.class);
    
    @Test
    public void testFindAllUtilisateurs() {
        log.debug("Entree de la methode");
        if (utilisateurs != null) {
            log.debug("NB_UTILISATEURS_LIST: " + NB_UTILISATEURS_LIST + " , utilisateurs.size(): " + utilisateurs.size());
            Assert.assertEquals(NB_UTILISATEURS_LIST, utilisateurs.size());
            verifyUsersDatas(utilisateurs);
        } else {
            Assert.fail("Aucun utilisateur n'a ete trouves dans votre base de données");
        }
        log.debug("Sortie de la methode");
    }
    
    public void verifyUsersDatas(List<Utilisateur> utilisateurs) {
        log.debug("Entree de la methode");
        if (utilisateurs != null) {
            log.debug("utilisateurs.size(): " + utilisateurs.size());
            for (Utilisateur user : utilisateurs) {
                verifyUserDatas(user);
            }
        } else if (utilisateurs == null || utilisateurs.isEmpty()) {
            Assert.fail("Aucun utilisateur n'a ete trouves dans votre liste");
        }
        log.debug("Sortie de la methode");
    }

    public void verifyUserDatas(Utilisateur user) {
        log.debug("Entree de la methode");
        if (user != null) {
            log.debug("idUtilisateur : " + user.getIdUtilisateur());
            Assert.assertNotNull(user.getIdUtilisateur());
            Assert.assertNotNull(user.getPrenom());
            Assert.assertNotNull(user.getNom());
            Assert.assertNotNull(user.getRole());
            Assert.assertNotNull(user.getRole().getIdRole());
            Assert.assertNotNull(user.getRole().getIdentifiant());
            Assert.assertNotNull(user.getRole().getDescription());
        } else if (user == null) {
            Assert.fail("Utilisateur null");
        }
        log.debug("Sortie de la methode");
    }
    
    @Test
    public void testCreateUpdateDeleteUtilisateur() {
        log.debug("Entree de la methode");
        Role roleCRUD = new Role("Superviseur", "Le rôle superviseur");
        roleCRUD = serviceFacade.getRoleDao().createRole(roleCRUD);
        JUnitRoleDao.verifyRoleData(roleCRUD);
        log.debug("Created roleCRUD : " + roleCRUD);
        log.debug("Created roleCRUD.getIdRole : " + roleCRUD.getIdRole());
        roleCRUD = serviceFacade.getRoleDao().findRoleById(roleCRUD.getIdRole());
        Assert.assertNotNull(roleCRUD);
        Utilisateur userCRUD = new Utilisateur("Mme", "Nicole", "Valentine", "nicole.valentine@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), roleCRUD);
        userCRUD = serviceFacade.getUtilisateurDao().createUtilisateur(userCRUD);
        Assert.assertNotNull(userCRUD);
        Assert.assertNotNull(userCRUD.getIdUtilisateur());
        Assert.assertNotNull(userCRUD.getPrenom());
        Assert.assertNotNull(userCRUD.getNom());
        Assert.assertNotNull(userCRUD.getDateCreation());
        Assert.assertNotNull(userCRUD.getDateModification());
        log.debug("Created userCRUD : " + userCRUD);
        userCRUD = serviceFacade.getUtilisateurDao().findUtilisateurById(userCRUD.getIdUtilisateur());
        Assert.assertNotNull(userCRUD);
        userCRUD.setPrenom("Nicole Bis");
        userCRUD.setNom("Valentine Bis");
        userCRUD = serviceFacade.getUtilisateurDao().updateUtilisateur(userCRUD);
        Assert.assertNotNull(userCRUD);
        userCRUD = serviceFacade.getUtilisateurDao().findUtilisateurById(userCRUD.getIdUtilisateur());
        log.debug("Updated userCRUD : " + userCRUD);
        Assert.assertEquals("Nicole Bis", userCRUD.getPrenom());
        Assert.assertEquals("Valentine Bis", userCRUD.getNom());
        Assert.assertTrue(serviceFacade.getUtilisateurDao().deleteUtilisateur(userCRUD));
        userCRUD = serviceFacade.getUtilisateurDao().findUtilisateurById(userCRUD.getIdUtilisateur());
        Assert.assertNull(userCRUD);
        Assert.assertTrue(serviceFacade.getRoleDao().deleteRole(roleCRUD));
        roleCRUD = serviceFacade.getRoleDao().findRoleById(roleCRUD.getIdRole());
        Assert.assertNull(roleCRUD);
        // Cas des gestions des doublons d'identifiant (mail).
        userCRUD = new Utilisateur("Mr", "Admin", "Admin", "admin@gmail.com", "passw0rd", new Date(System.currentTimeMillis()));
        try {
            userCRUD = serviceFacade.getUtilisateurDao().createUtilisateur(userCRUD);
            log.debug("Duplicate userCRUD : " + userCRUD.getIdentifiant());
        } catch (EbenusException e) {
            log.debug("Bravo la gestion des doublons d'identifiant marche parfaitement");
            Assert.assertEquals(Constants.EXCEPTION_CODE_USER_ALREADY_EXIST, e.getCode());
        }
        List<Utilisateur> utilisateursFinal = serviceFacade.getUtilisateurDao().findAllUtilisateurs();
        if (utilisateursFinal != null) {
            Assert.assertEquals(NB_UTILISATEURS_LIST, utilisateursFinal.size());
            log.debug("utilisateursFinal.size() : " + utilisateursFinal.size() + " , NB_UTILISATEURS_LIST: " + NB_UTILISATEURS_LIST);
        }
        log.debug("Sortie de la methode");
    }

    @Test
    public void testFindByCriteria() {
        log.debug("Entree de la methode");
        List<Utilisateur> utilisateursByPrenom = serviceFacade.getUtilisateurDao().findUtilisateursByPrenom(UTILISATEUR_FIND_BY_PRENOM);
        List<Utilisateur> utilisateursByNom = serviceFacade.getUtilisateurDao().findUtilisateursByNom(UTILISATEUR_FIND_BY_NOM);
        List<Utilisateur> utilisateursByIdentifiantRoleAdmin = serviceFacade.getUtilisateurDao().findUtilisateursByIdentifiantRole(UTILISATEUR_FIND_BY_IDENTIFIANT_ROLE_ADMIN);
        List<Utilisateur> utilisateursByIdentifiantRoleAcheteur = serviceFacade.getUtilisateurDao().findUtilisateursByIdentifiantRole(UTILISATEUR_FIND_BY_IDENTIFIANT_ROLE_ACHETEUR);
        List<Utilisateur> utilisateursByIdentifiantRoleStandard = serviceFacade.getUtilisateurDao().findUtilisateursByIdentifiantRole(UTILISATEUR_FIND_BY_IDENTIFIANT_ROLE_STANDARD);

       if (utilisateursByPrenom != null && !utilisateursByPrenom.isEmpty()) {
            log.debug("NB_UTILISATEURS_FIND_BY_PRENOM: " + NB_UTILISATEURS_FIND_BY_PRENOM + " , utilisateursByPrenom.size(): " + utilisateursByPrenom.size());
            Assert.assertEquals(NB_UTILISATEURS_FIND_BY_PRENOM, utilisateursByPrenom.size());
            verifyUsersDatas(utilisateursByPrenom);
        } else {
            Assert.fail("Aucun utilisateur avec le prenom '" + UTILISATEUR_FIND_BY_PRENOM + "' n'a ete trouve dans votre base de données");
        }
        if (utilisateursByNom != null && !utilisateursByNom.isEmpty()) {
            log.debug("NB_UTILISATEURS_FIND_BY_NOM: " + NB_UTILISATEURS_FIND_BY_NOM + " , utilisateursByNom.size(): " + utilisateursByNom.size());
            Assert.assertEquals(NB_UTILISATEURS_FIND_BY_NOM, utilisateursByNom.size());
            verifyUsersDatas(utilisateursByNom);
        } else {
            Assert.fail("Aucun utilisateur avec le nom '" + UTILISATEUR_FIND_BY_NOM + "' n'a ete trouve dans votre base de données");
        }
        if (utilisateursByIdentifiantRoleStandard != null && !utilisateursByIdentifiantRoleStandard.isEmpty()) {
            log.debug("NB_UTILISATEURS_FIND_BY_IDENTIFIANT_ROLE_STANDARD: " + NB_UTILISATEURS_FIND_BY_IDENTIFIANT_ROLE_STANDARD + " , utilisateursByIdentifiantRoleStandard.size(): " + utilisateursByIdentifiantRoleStandard.size());
            Assert.assertEquals(NB_UTILISATEURS_FIND_BY_IDENTIFIANT_ROLE_STANDARD, utilisateursByIdentifiantRoleStandard.size());
            verifyUsersDatas(utilisateursByIdentifiantRoleStandard);
        } else {
            Assert.fail("Aucun utilisateur avec le rôle '" + UTILISATEUR_FIND_BY_IDENTIFIANT_ROLE_STANDARD + "' n'a ete trouve dans votre base de données");
        }
        if (utilisateursByIdentifiantRoleAcheteur != null && !utilisateursByIdentifiantRoleAcheteur.isEmpty()) {
            log.debug("NB_UTILISATEURS_FIND_BY_IDENTIFIANT_ROLE_ACHETEUR: " + NB_UTILISATEURS_FIND_BY_IDENTIFIANT_ROLE_ACHETEUR + " , utilisateursByIdentifiantRoleAcheteur.size(): " + utilisateursByIdentifiantRoleAcheteur.size());
            Assert.assertEquals(NB_UTILISATEURS_FIND_BY_IDENTIFIANT_ROLE_ACHETEUR, utilisateursByIdentifiantRoleAcheteur.size());
            verifyUsersDatas(utilisateursByIdentifiantRoleAcheteur);
        } else {
            Assert.fail("Aucun utilisateur avec le rôle '" + UTILISATEUR_FIND_BY_IDENTIFIANT_ROLE_ACHETEUR + "' n'a ete trouve dans votre base de données");
        }
        if (utilisateursByIdentifiantRoleAdmin != null && !utilisateursByIdentifiantRoleAdmin.isEmpty()) {
            log.debug("NB_UTILISATEURS_FIND_BY_IDENTIFIANT_ROLE_ADMIN: " + NB_UTILISATEURS_FIND_BY_IDENTIFIANT_ROLE_ADMIN + " , utilisateursByIdentifiantRoleAdmin.size(): " + utilisateursByIdentifiantRoleAdmin.size());
            Assert.assertEquals(NB_UTILISATEURS_FIND_BY_IDENTIFIANT_ROLE_ADMIN, utilisateursByIdentifiantRoleAdmin.size());
            verifyUsersDatas(utilisateursByIdentifiantRoleAdmin);
        } else {
            Assert.fail("Aucun utilisateur avec le rôle '" + UTILISATEUR_FIND_BY_IDENTIFIANT_ROLE_ADMIN + "' n'a ete trouve dans votre base de données");
        }

        log.debug("Sortie de la methode");
    }


}
