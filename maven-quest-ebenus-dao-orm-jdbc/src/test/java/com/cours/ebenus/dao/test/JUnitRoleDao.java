package com.cours.ebenus.dao.test;

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

public class JUnitRoleDao extends JUnitQuestEbenus {

	private static final Log log = LogFactory.getLog(JUnitRoleDao.class);

	public static void verifyRoleData(Role role) {
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
		List<Role> rolesByIdentifiantAdmin = serviceFacade.getRoleDao()
				.findRoleByIdentifiant(ROLES_FIND_BY_IDENTIFIANT_ADMIN);
		List<Role> rolesByIdentifiantAcheteur = serviceFacade.getRoleDao()
				.findRoleByIdentifiant(ROLES_FIND_BY_IDENTIFIANT_ACHETEUR);
		List<Role> rolesByIdentifiantStandard = serviceFacade.getRoleDao()
				.findRoleByIdentifiant(ROLES_FIND_BY_IDENTIFIANT_STANDARD);

		if (rolesByIdentifiantAdmin != null) {
			log.debug("NB_ROLES_FIND_BY_IDENTIFIANT_ADMIN: " + NB_ROLES_FIND_BY_IDENTIFIANT_ADMIN
					+ " , rolesByIdentifiantAdmin.size(): " + rolesByIdentifiantAdmin.size());
			Assert.assertEquals(NB_ROLES_FIND_BY_IDENTIFIANT_ADMIN, rolesByIdentifiantAdmin.size());
		} else {
			Assert.fail("Aucun rôle avec l'identifiant " + ROLES_FIND_BY_IDENTIFIANT_ADMIN
					+ "' n'a ete trouve dans votre base de données");
		}
		if (rolesByIdentifiantAcheteur != null) {
			log.debug("NB_ROLES_FIND_BY_IDENTIFIANT_ACHETEUR: " + NB_ROLES_FIND_BY_IDENTIFIANT_ACHETEUR
					+ " , rolesByIdentifiantAcheteur.size(): " + rolesByIdentifiantAcheteur.size());
			Assert.assertEquals(NB_ROLES_FIND_BY_IDENTIFIANT_ACHETEUR, rolesByIdentifiantAcheteur.size());
		} else {
			Assert.fail("Aucun rôle avec l'identifiant " + ROLES_FIND_BY_IDENTIFIANT_ACHETEUR
					+ "' n'a ete trouve dans votre base de données");
		}
		if (rolesByIdentifiantStandard != null) {
			log.debug("NB_ROLES_FIND_BY_IDENTIFIANT_STANDARD: " + NB_ROLES_FIND_BY_IDENTIFIANT_STANDARD
					+ " , rolesByIdentifiantStandard.size(): " + rolesByIdentifiantStandard.size());
			Assert.assertEquals(NB_ROLES_FIND_BY_IDENTIFIANT_STANDARD, rolesByIdentifiantStandard.size());
		} else {
			Assert.fail("Aucun rôle avec l'identifiant " + ROLES_FIND_BY_IDENTIFIANT_STANDARD
					+ "' n'a ete trouve dans votre base de données");
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

}
