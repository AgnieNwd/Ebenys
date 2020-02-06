package com.cours.ebenus.dao.test;

import com.cours.ebenus.dao.DriverManagerSingleton;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.dao.exception.EbenusException;
import com.cours.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;
import com.cours.ebenus.utils.Constants;
import com.ibatis.common.jdbc.ScriptRunner;
import com.ibatis.common.resources.Resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class JUnitQuestEbenus {

    private static final Log log = LogFactory.getLog(JUnitQuestEbenus.class);
    private static IServiceFacade serviceFacade = null;
    // Compter le nombre d'utilisateurs et de roles dans votre base de données.
    private static final int NB_UTILISATEURS_LIST = 20;
    private static final int NB_ROLES_LIST = 5;

    private static final String UTILISATEUR_FIND_BY_PRENOM = "Nicolas";
    private static final int NB_UTILISATEURS_FIND_BY_PRENOM = 3;

    private static final String UTILISATEUR_FIND_BY_NOM = "Petit";
    private static final int NB_UTILISATEURS_FIND_BY_NOM = 3;

    private static final String UTILISATEUR_FIND_BY_IDENTIFIANT_ROLE_STANDARD = "Standard";
    private static final int NB_UTILISATEURS_FIND_BY_IDENTIFIANT_ROLE_STANDARD = 7;

    private static final String UTILISATEUR_FIND_BY_IDENTIFIANT_ROLE_ACHETEUR = "Acheteur";
    private static final int NB_UTILISATEURS_FIND_BY_IDENTIFIANT_ROLE_ACHETEUR = 7;

    private static final String UTILISATEUR_FIND_BY_IDENTIFIANT_ROLE_ADMIN = "Administrateur";
    private static final int NB_UTILISATEURS_FIND_BY_IDENTIFIANT_ROLE_ADMIN = 3;

    private static final int NB_ROLES_FIND_BY_IDENTIFIANT_ADMIN = 1;
    private static final String ROLES_FIND_BY_IDENTIFIANT_ADMIN = "Administrateur";

    private static final int NB_ROLES_FIND_BY_IDENTIFIANT_ACHETEUR = 1;
    private static final String ROLES_FIND_BY_IDENTIFIANT_ACHETEUR = "Acheteur";

    private static final int NB_ROLES_FIND_BY_IDENTIFIANT_STANDARD = 1;
    private static final String ROLES_FIND_BY_IDENTIFIANT_STANDARD = "Standard";

    private static List<Utilisateur> utilisateurs = null;
    private static List<Role> roles = null;

    @BeforeClass
    public static void init() throws Exception {
        // Configuration de l'application
    	serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);
    	roles = serviceFacade.getRoleDao().findAllRoles();
    	utilisateurs = serviceFacade.getUtilisateurDao().findAllUtilisateurs();
    }

    @BeforeClass
    public static void initDataBase() throws FileNotFoundException, IOException, SQLException {
        String scriptSqlPath = Constants.SQL_JUNIT_PATH_FILE;
        Statement statement = null;
    	BufferedReader reader = null;
    	Connection connectionState = null;
    	try {
        	connectionState = DriverManagerSingleton.getConnectionInstance();
        	// create ScripRunner object
    		ScriptRunner scriptExecutor = new ScriptRunner(connectionState, false, false);
    		// initialize file reader
    		reader = new BufferedReader(new FileReader(scriptSqlPath));
    		// execute script with file reader as input
    		scriptExecutor.runScript(reader);
        } catch (Exception ex) {
        	ex.printStackTrace();
        } finally {
    		// close file reader
    		if (reader != null) {
    			reader.close();
    		}
    		// close db connection
    		if (connectionState != null) {
    			connectionState.close();
    		}
    	}
    }

    public void verifyRoleData(Role role) {
        log.debug("Entree de la methode");
        if (role != null) {
            log.debug("idRole : " + role.getIdRole());
            Assert.assertNotNull(role.getIdRole());
            Assert.assertNotNull(role.getIdentifiant());
            Assert.assertNotNull(role.getDescription());
        } else if (role == null) {
            Assert.fail("Role null");
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

    @Test
    public void testFindAllRoles() {
        log.debug("Entree de la methode");
        if (roles != null) {
            log.debug("NB_ROLES_LIST: " + NB_ROLES_LIST + " , roles.size(): " + roles.size());
            Assert.assertEquals(NB_ROLES_LIST, roles.size());
        } else {
            Assert.fail("Aucun Role n'a ete trouves dans votre base de données");
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

        List<Role> rolesByIdentifiantAdmin = serviceFacade.getRoleDao().findRoleByIdentifiant(ROLES_FIND_BY_IDENTIFIANT_ADMIN);
        List<Role> rolesByIdentifiantAcheteur = serviceFacade.getRoleDao().findRoleByIdentifiant(ROLES_FIND_BY_IDENTIFIANT_ACHETEUR);
        List<Role> rolesByIdentifiantStandard = serviceFacade.getRoleDao().findRoleByIdentifiant(ROLES_FIND_BY_IDENTIFIANT_STANDARD);

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

        if (rolesByIdentifiantAdmin != null) {
            log.debug("NB_ROLES_FIND_BY_IDENTIFIANT_ADMIN: " + NB_ROLES_FIND_BY_IDENTIFIANT_ADMIN + " , rolesByIdentifiantAdmin.size(): " + rolesByIdentifiantAdmin.size());
            Assert.assertEquals(NB_ROLES_FIND_BY_IDENTIFIANT_ADMIN, rolesByIdentifiantAdmin.size());
        } else {
            Assert.fail("Aucun rôle avec l'identifiant " + ROLES_FIND_BY_IDENTIFIANT_ADMIN + "' n'a ete trouve dans votre base de données");
        }
        if (rolesByIdentifiantAcheteur != null) {
            log.debug("NB_ROLES_FIND_BY_IDENTIFIANT_ACHETEUR: " + NB_ROLES_FIND_BY_IDENTIFIANT_ACHETEUR + " , rolesByIdentifiantAcheteur.size(): " + rolesByIdentifiantAcheteur.size());
            Assert.assertEquals(NB_ROLES_FIND_BY_IDENTIFIANT_ACHETEUR, rolesByIdentifiantAcheteur.size());
        } else {
            Assert.fail("Aucun rôle avec l'identifiant " + ROLES_FIND_BY_IDENTIFIANT_ACHETEUR + "' n'a ete trouve dans votre base de données");
        }
        if (rolesByIdentifiantStandard != null) {
            log.debug("NB_ROLES_FIND_BY_IDENTIFIANT_STANDARD: " + NB_ROLES_FIND_BY_IDENTIFIANT_STANDARD + " , rolesByIdentifiantStandard.size(): " + rolesByIdentifiantStandard.size());
            Assert.assertEquals(NB_ROLES_FIND_BY_IDENTIFIANT_STANDARD, rolesByIdentifiantStandard.size());
        } else {
            Assert.fail("Aucun rôle avec l'identifiant " + ROLES_FIND_BY_IDENTIFIANT_STANDARD + "' n'a ete trouve dans votre base de données");
        }
        log.debug("Sortie de la methode");
    }

    @Test
    public void testCreateUpdateDeleteUtilisateur() {
        log.debug("Entree de la methode");
        Role roleCRUD = new Role("Superviseur", "Le rôle superviseur");
        roleCRUD = serviceFacade.getRoleDao().createRole(roleCRUD);
        verifyRoleData(roleCRUD);
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
        
        System.out.println("<<<<<<<<< " + userCRUD);
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
    public void testCreateUpdateDeleteRole() {
        log.debug("Entree de la methode");
        Role roleCRUD = new Role("Superviseur", "Le rôle superviseur");
        roleCRUD = serviceFacade.getRoleDao().createRole(roleCRUD);
        verifyRoleData(roleCRUD);
        log.debug("Created roleCRUD : " + roleCRUD);
        log.debug("Created roleCRUD.getIdRole : " + roleCRUD.getIdRole());
        roleCRUD = serviceFacade.getRoleDao().findRoleById(roleCRUD.getIdRole());
        Assert.assertNotNull(roleCRUD);
        roleCRUD.setIdentifiant("Superviseur Bis");
        roleCRUD.setDescription("Le rôle superviseur Bis");
        roleCRUD = serviceFacade.getRoleDao().updateRole(roleCRUD);
        Assert.assertNotNull(roleCRUD);
        roleCRUD = serviceFacade.getRoleDao().findRoleById(roleCRUD.getIdRole());
        log.debug("Updated roleCRUD : " + roleCRUD);
        Assert.assertEquals("Superviseur Bis", roleCRUD.getIdentifiant());
        Assert.assertEquals("Le rôle superviseur Bis", roleCRUD.getDescription());
        Assert.assertTrue(serviceFacade.getRoleDao().deleteRole(roleCRUD));
        roleCRUD = serviceFacade.getRoleDao().findRoleById(roleCRUD.getIdRole());
        Assert.assertNull(roleCRUD);
        List<Role> rolesFinal = serviceFacade.getRoleDao().findAllRoles();
        if (rolesFinal != null) {
            Assert.assertEquals(NB_ROLES_LIST, rolesFinal.size());
            log.debug("rolesFinal.size() : " + rolesFinal.size() + " , NB_ROLES_LIST: " + NB_ROLES_LIST);
        }
        log.debug("Sortie de la methode");
    }

    @AfterClass
    public static void terminate() throws Exception {
        log.debug("Entree de la methode");
        serviceFacade = null;
        utilisateurs = null;
        roles = null;
        log.debug("Sortie de la methode");
    }
}
