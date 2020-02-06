package com.cours.ebenus.dao.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;

import junit.framework.Assert;

public class JUnitQuestEbenusManualList extends JUnitQuestEbenus {

    private static final Log log = LogFactory.getLog(JUnitQuestEbenusManualList.class);

	private static IServiceFacade serviceFacade;
	
	private static final int NB_ROLES_LIST = 5;
	private static List<Role> roles = null;
	
	private static final int NB_USERS_LIST = 28;
	private static List<Utilisateur> utilisateurs = null;
	
    private static final String USERS_FIND_BY_IDENTIFIANT = "admin@gmail.com";
    private static final int NB_USERS_FIND_BY_IDENTIFIANT = 1;
    
    private static final String ROLES_FIND_BY_IDENTIFIANT = "Administrateur";
    private static final int NB_ROLES_FIND_BY_IDENTIFIANT = 1;
    
    private static final String USERS_FIND_BY_PRENOM = "Nicolas";
    private static final int NB_USERS_FIND_BY_PRENOM = 3;

    private static final String USERS_FIND_BY_NOM = "Petit";
    private static final int NB_USERS_FIND_BY_NOM = 3;

    private static final int USERS_FIND_BY_ID_ROLE_UTILISATEUR_2 = 1;
    private static final int NB_USERS_FIND_BY_ID_ROLE_UTILISATEUR_2 = 5;
    
    private static final String USERS_FIND_BY_IDENTIFIANT_ROLE_UTILISATEUR = "Administrateur";
    private static final int NB_USERS_FIND_BY_IDENTIFIANT_ROLE_UTILISATEUR = 5;

    @BeforeClass
    public static void init() throws Exception {
		serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.MANUAL_LIST_DAO_FACTORY);
		roles = serviceFacade.getRoleDao().findAllRoles();
		utilisateurs = serviceFacade.getUtilisateurDao().findAllUtilisateurs();
		
		JUnitQuestEbenus.serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.MANUAL_LIST_DAO_FACTORY);
		JUnitQuestEbenus.roles = JUnitQuestEbenus.serviceFacade.getRoleDao().findAllRoles();
		JUnitQuestEbenus.utilisateurs = JUnitQuestEbenus.serviceFacade.getUtilisateurDao().findAllUtilisateurs();
    }

    
//    @Test
//    public void testFindAllRoles() {
//    	System.out.println("Entrée de la methode testFindAllRoles()");
//    	if (roles != null) {
//    		System.out.println("roles.size(): " + roles.size());
//        	Assert.assertNotNull(roles);
//    	} else {
//    		Assert.fail("Roles not found");
//    	}
//    	System.out.println("Sortie de la methode testFindAllRoles()");
//    }
//    
//    @Test
//    public void testFindAllUtilisateurs() {
//    	System.out.println("Entrée de la methode testFindAllUsers()");
//    	if (utilisateurs != null) {
//    		System.out.println("users.size(): " + utilisateurs.size());
//        	Assert.assertNotNull(utilisateurs);
//    	} else {
//    		Assert.fail("users not found");
//    	}
//    	System.out.println("Sortie de la methode testFindAllUsers()");
//    }
    
    @Test
    public void testFindRoleById() {
    	System.out.println("Entrée de la methode testFindRoleById()");
    	Role roleFound = serviceFacade.getRoleDao().findRoleById(1);
    	System.out.println("Role with ID #1 is not null");
    	Assert.assertNotNull(roleFound);
    	System.out.println("Sortie de la methode testFindRoleById()");
    }
    
    @Test
    public void testFindUtilisateurById() {
    	System.out.println("Entrée de la methode testFindUtilisateurById()");
    	Utilisateur userFound = serviceFacade.getUtilisateurDao().findUtilisateurById(1);
    	System.out.println("User with ID #1 is not null");
    	Assert.assertNotNull(userFound);
    	System.out.println("Sortie de la methode testFindUtilisateurById()");
    }
    
    @Test
    public void testFindUtilisateursByIdentifiant() {
    	System.out.println("Entrée de la methode testFindUtilisateursByIdentifiant()");
    	List<Utilisateur> usersListByIdentifiant = new ArrayList<Utilisateur>();
    	usersListByIdentifiant = serviceFacade.getUtilisateurDao().findUtilisateurByIdentifiant(USERS_FIND_BY_IDENTIFIANT);
    	if (usersListByIdentifiant != null) {
    		System.out.println("NB_USERS_FIND_BY_IDENTIFIANT: " + NB_USERS_FIND_BY_IDENTIFIANT + " | usersListByIdentifiant.size(): " + usersListByIdentifiant.size());
    		Assert.assertEquals(NB_USERS_FIND_BY_IDENTIFIANT, usersListByIdentifiant.size());
    	} else {
    		Assert.fail("users with identifiant '123123' not found");
    	}
    	System.out.println("Sortie de la methode testFindUtilisateursByIdentifiant()");
    }
    
    @Test
    public void testFindRoleByIdentifiant() {
    	System.out.println("Entrée de la methode testFindRoleByIdentifiant()");
    	List<Role> rolesListByIdentifiant = new ArrayList<Role>();
    	rolesListByIdentifiant = serviceFacade.getRoleDao().findRoleByIdentifiant(ROLES_FIND_BY_IDENTIFIANT);
    	if (rolesListByIdentifiant != null) {
    		System.out.println("NB_ROLES_FIND_BY_IDENTIFIANT: " + NB_ROLES_FIND_BY_IDENTIFIANT + " | rolesListByIdentifiant.size(): " + rolesListByIdentifiant.size());
    		Assert.assertEquals(NB_ROLES_FIND_BY_IDENTIFIANT, rolesListByIdentifiant.size());
    	} else {
    		Assert.fail("roles with identifiant 'admin' not found");
    	}
    	System.out.println("Sortie de la methode testFindRoleByIdentifiant()");
    }
    
    @Test
    public void testFindUtilisateursByPrenom() {
    	System.out.println("Entrée de la methode testFindUtilisateursByPrenom()");
    	List<Utilisateur> usersListByPrenom = new ArrayList<Utilisateur>();
    	usersListByPrenom = serviceFacade.getUtilisateurDao().findUtilisateursByPrenom(USERS_FIND_BY_PRENOM);
    	if (usersListByPrenom != null) {
    		System.out.println("NB_USERS_FIND_BY_PRENOM: " + NB_USERS_FIND_BY_PRENOM + " | usersListByPrenom.size(): " + usersListByPrenom.size());
    		Assert.assertEquals(NB_USERS_FIND_BY_PRENOM, usersListByPrenom.size());
    	} else {
    		Assert.fail("users named 'eli' not found");
    	}
    	System.out.println("Sortie de la methode testFindUtilisateursByPrenom()");
    }
    
    @Test
    public void testFindUtilisateursByNom() {
    	System.out.println("Entrée de la methode testFindUtilisateursByNom()");
    	List<Utilisateur> usersListByNom = new ArrayList<Utilisateur>();
    	usersListByNom = serviceFacade.getUtilisateurDao().findUtilisateursByNom(USERS_FIND_BY_NOM);
    	if (usersListByNom != null) {
    		System.out.println("NB_USERS_FIND_BY_NOM: " + NB_USERS_FIND_BY_NOM + " | usersListByNom.size(): " + usersListByNom.size());
    		Assert.assertEquals(NB_USERS_FIND_BY_NOM, usersListByNom.size());
    	} else {
    		Assert.fail("users with nom 'andre' not found");
    	}
    	System.out.println("Sortie de la methode testFindUtilisateursByNom()");
    }
    
    @Test
    public void testFindUtilisateursByIdRole() {
    	System.out.println("Entrée de la methode testFindUtilisateursByIdRole()");
    	List<Utilisateur> usersListByIdRole = new ArrayList<Utilisateur>();
    	usersListByIdRole = serviceFacade.getUtilisateurDao().findUtilisateursByIdRole(USERS_FIND_BY_ID_ROLE_UTILISATEUR_2);
    	if (usersListByIdRole != null) {
    		System.out.println("NB_USERS_FIND_BY_ID_ROLE_UTILISATEUR_2: " + NB_USERS_FIND_BY_ID_ROLE_UTILISATEUR_2 + " | usersListByIdRole.size(): " + usersListByIdRole.size());
    		Assert.assertEquals(NB_USERS_FIND_BY_ID_ROLE_UTILISATEUR_2, usersListByIdRole.size());
    	} else {
    		Assert.fail("users with the role id 2 not found");
    	}
    	System.out.println("Sortie de la methode testFindUtilisateursByIdRole()");
    }
    
    @Test
    public void findUtilisateursByIdentifiantRole() {
    	System.out.println("Entrée de la methode findUtilisateursByIdentifiantRole()");
    	List<Utilisateur> usersListByIdentifiantRole = new ArrayList<Utilisateur>();
    	usersListByIdentifiantRole = serviceFacade.getUtilisateurDao().findUtilisateursByIdentifiantRole(USERS_FIND_BY_IDENTIFIANT_ROLE_UTILISATEUR);
    	if (usersListByIdentifiantRole != null) {
    		System.out.println("NB_USERS_FIND_BY_IDENTIFIANT_ROLE_UTILISATEUR: " + NB_USERS_FIND_BY_IDENTIFIANT_ROLE_UTILISATEUR + " | usersListByIdentifiantRole.size(): " + usersListByIdentifiantRole.size());
    		Assert.assertEquals(NB_USERS_FIND_BY_IDENTIFIANT_ROLE_UTILISATEUR, usersListByIdentifiantRole.size());
    	} else {
    		Assert.fail("users with the identifiant role 'util' not found");
    	}
    	System.out.println("Sortie de la methode findUtilisateursByIdentifiantRole()");
    }
    
    /*@Test
    public void testCreateUtilisateur() {
    	System.out.println("Entrée de la methode testCreateUtilisateur()");
    	Utilisateur newUser = new Utilisateur(29, "mllx", "cat", "arina", "111111", "mdp", new Date(), new Role(1, "admin", "administrateur"));
    	newUser = serviceFacade.getUtilisateurDao().createUtilisateur(newUser);
    	Utilisateur newUserFound = serviceFacade.getUtilisateurDao().findUtilisateurById(29);
    	if (newUserFound != null) { 		
    		System.out.println("New User found with ID #29 is not null");
    		Assert.assertNotNull(newUserFound);
    	} else {
    		Assert.fail("New User not found");
    	}
    	System.out.println("Sortie de la methode testCreateUtilisateur()");
    }
    
    @Test
    public void testCreateRole() {
    	System.out.println("Entrée de la methode testCreateRole()");
    	Role newRole = new Role(6, "super", "superviseur");
    	newRole = serviceFacade.getRoleDao().createRole(newRole);
    	Role newRoleFound = serviceFacade.getRoleDao().findRoleById(3);
    	if (newRoleFound != null) { 		
    		System.out.println("New Role found with ID #3 is not null");
    		Assert.assertNotNull(newRoleFound);
    	} else {
    		Assert.fail("New Role not found");
    	}
    	System.out.println("Sortie de la methode testCreateRole()");
    }
    
    @Test
    public void testUpdateUtilisateur() {
    	System.out.println("Entrée de la methode testUpdateUtilisateur()" + serviceFacade.getUtilisateurDao().findAllUtilisateurs().toString());
    	Utilisateur newUser = new Utilisateur(29, "mme", "eli", "brown", "124124", "mdp", new Date(), new Role(1, "admin", "administrateur"));
    	newUser = serviceFacade.getUtilisateurDao().updateUtilisateur(newUser);
    	Utilisateur userChanged = serviceFacade.getUtilisateurDao().findUtilisateurById(29);
    	if (userChanged != null) { 		
    		System.out.println("User Updated");
    		Assert.assertEquals(newUser, userChanged);
    	} else {
    		Assert.fail("User not updated");
    	}
    	System.out.println("Sortie de la methode testUpdateUtilisateur()");
    }
    
    @Test
    public void testUpdateRole() {
    	System.out.println("Entrée de la methode testUpdateRole()");
    	Role newRole = new Role(6, "achat", "acheteur");
    	newRole = serviceFacade.getRoleDao().updateRole(newRole);
    	Role roleChanged = serviceFacade.getRoleDao().findRoleById(6);
    	if (roleChanged != null) { 		
    		System.out.println("Role Updated" + roleChanged.toString());
    		Assert.assertEquals(newRole, roleChanged);
    	} else {
    		Assert.fail("Role not updated");
    	}
    	System.out.println("Sortie de la methode testUpdateRole()");
    }
 
    @Test
    public void testDeleteUtilisateur() {
    	System.out.println("Entrée de la methode testDeleteUtilisateur()");
    	Utilisateur userToRemove = serviceFacade.getUtilisateurDao().findUtilisateurById(29);
    	boolean isUserRemoved = serviceFacade.getUtilisateurDao().deleteUtilisateur(userToRemove);
    	Utilisateur userFound = serviceFacade.getUtilisateurDao().findUtilisateurById(29);
    	if (isUserRemoved & userFound == null) { 		
    		System.out.println("User removed");
    		Assert.assertNull(userFound);
    	} else {
    		Assert.fail("User not removed");
    	}
    	System.out.println("Sortie de la methode testDeleteUtilisateur()");
    }
    
    @Test
    public void testDeleteRemove() {
    	System.out.println("Entrée de la methode testDeleteRemove()");
    	Role roleToRemove = serviceFacade.getRoleDao().findRoleById(6);
    	boolean isRoleRemoved = serviceFacade.getRoleDao().deleteRole(roleToRemove);
    	Role roleFound = serviceFacade.getRoleDao().findRoleById(6);
    	if (isRoleRemoved & roleFound == null) { 		
    		System.out.println("Role removed");
    		Assert.assertNull(roleFound);
    	} else {
    		Assert.fail("Role not removed");
    	}
    	System.out.println("Sortie de la methode testDeleteRemove()");
    }
    */
    
}
