package com.cours.ebenus.dao.test;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cours.ebenus.maven.ebenus.dao.IRoleDao;
import com.cours.ebenus.maven.ebenus.dao.entities.Classe;
import com.cours.ebenus.maven.ebenus.dao.entities.Role;
import com.cours.ebenus.maven.ebenus.dao.entities.User;
import com.cours.ebenus.maven.ebenus.dao.exception.ProjectException;
import com.cours.ebenus.maven.ebenus.service.IServiceFacade;
import com.cours.ebenus.maven.ebenus.service.ServiceFacade;
import com.cours.ebenus.maven.ebenus.utils.Constants;

public class JUnitUserProjetLibre extends JUnitQuestProjetLibre {
	private static final Log log = LogFactory.getLog(JUnitUserProjetLibre.class);
    
    public void verifyUsersDatas(List<User> users) {
        log.debug("Entree de la methode");
        if (users != null) {
            log.debug("utilisateurs.size(): " + users.size());
            for (User user : users) {
                verifyUserDatas(user);
            }
        } else if (users == null || users.isEmpty()) {
            Assert.fail("Aucun utilisateur n'a ete trouves dans votre liste");
        }
        log.debug("Sortie de la methode");
    }

    public static void verifyUserDatas(User user) {
        log.debug("Entree de la methode");
        if (user != null) {
            log.debug("idUtilisateur : " + user.getId());
            Assert.assertNotNull(user.getId());
            Assert.assertNotNull(user.getPrenom());
            Assert.assertNotNull(user.getNom());
            Assert.assertNotNull(user.getEmail());
            Assert.assertNotNull(user.getRole());
            Assert.assertNotNull(user.getRole().getIdRole());
            Assert.assertNotNull(user.getRole().getIdentifiant());
            Assert.assertNotNull(user.getRole().getDescription());
            Assert.assertNotNull(user.getClasse());
            Assert.assertNotNull(user.getClasse().getIdClasse());
        } else if (user == null) {
            Assert.fail("User null");
        }
        log.debug("Sortie de la methode");
    }

    @Test
    public void testFindAllUsers() {
        log.debug("Entree de la methode");
        if (users != null) {
            log.debug("NB_UTILISATEURS_LIST: " + NB_USERS_LIST + " , utilisateurs.size(): " + users.size());
            Assert.assertEquals(NB_USERS_LIST, users.size());
            verifyUsersDatas(users);
        } else {
            Assert.fail("Aucun users n'a ete trouves dans la base de données");
        }
        log.debug("Sortie de la methode");
    }


 	@Test
    public void testCreateUpdateDeleteUser() {
        log.debug("Entree de la methode");
        Role roleCRUD = new Role("Directeur", "Le rôle directeur");
        Classe classeCRUD = new Classe();
        roleCRUD = serviceFacade.getRoleDao().createRole(roleCRUD);
        classeCRUD = serviceFacade.getClasseDao().createClasse(classeCRUD);
        JUnitRoleProjetLibre.verifyRoleData(roleCRUD);
        JUnitClasseProjetLibre.verifyClasseData(classeCRUD);
        log.debug("Created roleCRUD : " + roleCRUD);
        log.debug("Created roleCRUD.getIdRole : " + roleCRUD.getIdRole());
        roleCRUD = serviceFacade.getRoleDao().findRoleById(roleCRUD.getIdRole());
        log.debug("Created classeCRUD : " + classeCRUD);
        log.debug("Created classeCRUD.getIdClasse : " + classeCRUD.getIdClasse());
        classeCRUD = serviceFacade.getClasseDao().findClasseById(classeCRUD.getIdClasse());
        Assert.assertNotNull(roleCRUD);
        Assert.assertNotNull(classeCRUD);
        User userCRUD = new User("Nicole", "Valentine", "nicole.valentine@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), roleCRUD, classeCRUD);
        userCRUD = serviceFacade.getUserDao().createUser(userCRUD);
        Assert.assertNotNull(userCRUD);
        Assert.assertNotNull(userCRUD.getId());
        Assert.assertNotNull(userCRUD.getPrenom());
        Assert.assertNotNull(userCRUD.getNom());
        Assert.assertNotNull(userCRUD.getDateCreation());
        Assert.assertNotNull(userCRUD.getDateModification());
        log.debug("Created userCRUD : " + userCRUD);
        userCRUD = serviceFacade.getUserDao().findUserById(userCRUD.getId());
        Assert.assertNotNull(userCRUD);
        userCRUD.setPrenom("Nicole Bis");
        userCRUD.setNom("Valentine Bis");
        userCRUD = serviceFacade.getUserDao().updateUser(userCRUD);
        Assert.assertNotNull(userCRUD);
        userCRUD = serviceFacade.getUserDao().findUserById(userCRUD.getId());
        log.debug("Updated userCRUD : " + userCRUD);
        Assert.assertEquals("Nicole Bis", userCRUD.getPrenom());
        Assert.assertEquals("Valentine Bis", userCRUD.getNom());
        Assert.assertTrue(serviceFacade.getUserDao().deleteUser(userCRUD));
        userCRUD = serviceFacade.getUserDao().findUserById(userCRUD.getId());
        Assert.assertNull(userCRUD);
        Assert.assertTrue(serviceFacade.getRoleDao().deleteRole(roleCRUD));
        Assert.assertTrue(serviceFacade.getClasseDao().deleteClasse(classeCRUD));
        classeCRUD = serviceFacade.getClasseDao().findClasseById(classeCRUD.getIdClasse());
        roleCRUD = serviceFacade.getRoleDao().findRoleById(roleCRUD.getIdRole());
        Assert.assertNull(roleCRUD);
        Assert.assertNull(classeCRUD);
        // Cas des gestions des doublons email.
        userCRUD = new User("Admin", "Admin", "gwenstacy@gmail.com", "passw0rd", new Date(System.currentTimeMillis()));
        try {
            userCRUD = serviceFacade.getUserDao().createUser(userCRUD);
            log.debug("Duplicate userCRUD : " + userCRUD.getId());
        } catch (ProjectException e) {
            log.debug("gestion des doublons d'email OK");
            Assert.assertEquals(Constants.EXCEPTION_CODE_USER_ALREADY_EXIST, e.getCode());
        }
        List<User> utilisateursFinal = serviceFacade.getUserDao().findAllUsers();
        if (utilisateursFinal != null) {
            Assert.assertEquals(NB_USERS_LIST, utilisateursFinal.size());
            log.debug("utilisateursFinal.size() : " + utilisateursFinal.size() + " , NB_UTILISATEURS_LIST: " + NB_USERS_LIST);
        }
        log.debug("Sortie de la methode");
    }

    @Test
    public void testFindByCriteria() {
        log.debug("Entree de la methode");
        List<User> utilisateursByPrenom = serviceFacade.getUserDao().findUsersByPrenom(USER_FIND_BY_PRENOM);
        List<User> utilisateursByNom = serviceFacade.getUserDao().findUsersByNom(USER_FIND_BY_NOM);
        List<User> utilisateursByIdentifiantRoleAdmin = serviceFacade.getUserDao().findUsersByIdentifiantRole(USER_FIND_BY_IDENTIFIANT_ROLE_ADMIN);
        List<User> utilisateursByIdentifiantRoleProf = serviceFacade.getUserDao().findUsersByIdentifiantRole(USER_FIND_BY_IDENTIFIANT_ROLE_PROF);
        List<User> utilisateursByIdentifiantRoleEleve = serviceFacade.getUserDao().findUsersByIdentifiantRole(USER_FIND_BY_IDENTIFIANT_ROLE_ELEVE);
        List<User> utilisateursByIdentifiantRoleEleveAndLibelleClasse = serviceFacade.getUserDao().findUsersByLibelleClasseIdentifiantRole(CLASSE_FIND_BY_IDENTIFIANT_4EME, USER_FIND_BY_IDENTIFIANT_ROLE_ELEVE);

        
        if (utilisateursByIdentifiantRoleEleveAndLibelleClasse != null && !utilisateursByIdentifiantRoleEleveAndLibelleClasse.isEmpty()) {
            log.debug("CLASSE_FIND_BY_IDENTIFIANT_4EME: "+ CLASSE_FIND_BY_IDENTIFIANT_4EME +"USER_FIND_BY_IDENTIFIANT_ROLE_ELEVE: " + USER_FIND_BY_IDENTIFIANT_ROLE_ELEVE + " , utilisateursByIdentifiantRoleEleveAndLibelleClasse.size(): " + utilisateursByIdentifiantRoleEleveAndLibelleClasse.size());
            Assert.assertEquals(NB_USERS_FIND_BY_ROLE_AND_LIBELLE_CLASSE, utilisateursByIdentifiantRoleEleveAndLibelleClasse.size());
            verifyUsersDatas(utilisateursByIdentifiantRoleEleveAndLibelleClasse);
        } else {
            Assert.fail("Aucun utilisateur avec de la classe '" + CLASSE_FIND_BY_IDENTIFIANT_4EME + " avec le role '" + USER_FIND_BY_IDENTIFIANT_ROLE_ELEVE + " n'a ete trouve dans la base de données");
        }

       if (utilisateursByPrenom != null && !utilisateursByPrenom.isEmpty()) {
            log.debug("NB_UTILISATEURS_FIND_BY_PRENOM: " + NB_USERS_FIND_BY_PRENOM + " , utilisateursByPrenom.size(): " + utilisateursByPrenom.size());
            Assert.assertEquals(NB_USERS_FIND_BY_PRENOM, utilisateursByPrenom.size());
            verifyUsersDatas(utilisateursByPrenom);
        } else {
            Assert.fail("Aucun utilisateur avec le prenom '" + USER_FIND_BY_PRENOM + "' n'a ete trouve dans la base de données");
        }
        if (utilisateursByNom != null && !utilisateursByNom.isEmpty()) {
            log.debug("NB_UTILISATEURS_FIND_BY_NOM: " + NB_USERS_FIND_BY_NOM + " , utilisateursByNom.size(): " + utilisateursByNom.size());
            Assert.assertEquals(NB_USERS_FIND_BY_NOM, utilisateursByNom.size());
            verifyUsersDatas(utilisateursByNom);
        } else {
            Assert.fail("Aucun utilisateur avec le nom '" + USER_FIND_BY_NOM + "' n'a ete trouve dans la base de données");
        }
        if (utilisateursByIdentifiantRoleEleve != null && !utilisateursByIdentifiantRoleEleve.isEmpty()) {
            log.debug("NB_UTILISATEURS_FIND_BY_IDENTIFIANT_ROLE_ELEVE: " + USER_FIND_BY_IDENTIFIANT_ROLE_ELEVE + " , utilisateursByIdentifiantRoleEleve.size(): " + utilisateursByIdentifiantRoleEleve.size());
            Assert.assertEquals(NB_USERS_FIND_BY_IDENTIFIANT_ROLE_ELEVE, utilisateursByIdentifiantRoleEleve.size());
            verifyUsersDatas(utilisateursByIdentifiantRoleEleve);
        } else {
            Assert.fail("Aucun utilisateur avec le rôle '" + USER_FIND_BY_IDENTIFIANT_ROLE_ELEVE + "' n'a ete trouve dans la base de données");
        }
        if (utilisateursByIdentifiantRoleProf != null && !utilisateursByIdentifiantRoleProf.isEmpty()) {
            log.debug("NB_UTILISATEURS_FIND_BY_IDENTIFIANT_ROLE_PROF: " + USER_FIND_BY_IDENTIFIANT_ROLE_PROF + " , utilisateursByIdentifiantRoleProf.size(): " + utilisateursByIdentifiantRoleProf.size());
            Assert.assertEquals(NB_USERS_FIND_BY_IDENTIFIANT_ROLE_PROF, utilisateursByIdentifiantRoleProf.size());
            verifyUsersDatas(utilisateursByIdentifiantRoleProf);
        } else {
            Assert.fail("Aucun utilisateur avec le rôle '" + USER_FIND_BY_IDENTIFIANT_ROLE_PROF + "' n'a ete trouve dans la base de données");
        }
        if (utilisateursByIdentifiantRoleAdmin != null && !utilisateursByIdentifiantRoleAdmin.isEmpty()) {
            log.debug("NB_UTILISATEURS_FIND_BY_IDENTIFIANT_ROLE_ADMIN: " + NB_USERS_FIND_BY_IDENTIFIANT_ROLE_ADMIN + " , utilisateursByIdentifiantRoleAdmin.size(): " + utilisateursByIdentifiantRoleAdmin.size());
            Assert.assertEquals(NB_USERS_FIND_BY_IDENTIFIANT_ROLE_ADMIN, utilisateursByIdentifiantRoleAdmin.size());
            verifyUsersDatas(utilisateursByIdentifiantRoleAdmin);
        } else {
            Assert.fail("Aucun utilisateur avec le rôle '" + USER_FIND_BY_IDENTIFIANT_ROLE_ADMIN + "' n'a ete trouve dans la base de données");
        }

        log.debug("Sortie de la methode");
    }

}
