package com.cours.ebenus.dao.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cours.ebenus.maven.ebenus.dao.entities.Classe;
import com.cours.ebenus.maven.ebenus.dao.entities.Role;
import com.cours.ebenus.maven.ebenus.dao.entities.User;
import com.cours.ebenus.maven.ebenus.dao.exception.ProjectException;
import com.cours.ebenus.maven.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.maven.ebenus.service.IServiceFacade;
import com.cours.ebenus.maven.ebenus.service.ServiceFacade;
import com.cours.ebenus.maven.ebenus.utils.Constants;

public class JUnitRoleProjetLibre extends JUnitQuestProjetLibre {
	
	private static final Log log = LogFactory.getLog(JUnitRoleProjetLibre.class);
	
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
				.findRoleByIdentifiant(ROLES_FIND_BY_IDENTIFIANT_PROF);
		List<Role> rolesByIdentifiantStandard = serviceFacade.getRoleDao()
				.findRoleByIdentifiant(ROLES_FIND_BY_IDENTIFIANT_ELEVE);

		if (rolesByIdentifiantAdmin != null) {
			log.debug("NB_ROLES_FIND_BY_IDENTIFIANT_ADMIN: " + NB_ROLES_FIND_BY_IDENTIFIANT_ADMIN
					+ " , rolesByIdentifiantAdmin.size(): " + rolesByIdentifiantAdmin.size());
			Assert.assertEquals(NB_ROLES_FIND_BY_IDENTIFIANT_ADMIN, rolesByIdentifiantAdmin.size());
		} else {
			Assert.fail("Aucun rôle avec l'identifiant " + ROLES_FIND_BY_IDENTIFIANT_ADMIN
					+ "' n'a ete trouve dans votre base de données");
		}
		if (rolesByIdentifiantAcheteur != null) {
			log.debug("NB_ROLES_FIND_BY_IDENTIFIANT_ACHETEUR: " + ROLES_FIND_BY_IDENTIFIANT_PROF
					+ " , rolesByIdentifiantAcheteur.size(): " + rolesByIdentifiantAcheteur.size());
			Assert.assertEquals(NB_ROLES_FIND_BY_IDENTIFIANT_PROF, rolesByIdentifiantAcheteur.size());
		} else {
			Assert.fail("Aucun rôle avec l'identifiant " + ROLES_FIND_BY_IDENTIFIANT_PROF
					+ "' n'a ete trouve dans votre base de données");
		}
		if (rolesByIdentifiantStandard != null) {
			log.debug("NB_ROLES_FIND_BY_IDENTIFIANT_STANDARD: " + ROLES_FIND_BY_IDENTIFIANT_ELEVE
					+ " , rolesByIdentifiantStandard.size(): " + rolesByIdentifiantStandard.size());
			Assert.assertEquals(NB_ROLES_FIND_BY_IDENTIFIANT_ELEVE, rolesByIdentifiantStandard.size());
		} else {
			Assert.fail("Aucun rôle avec l'identifiant " + ROLES_FIND_BY_IDENTIFIANT_ELEVE
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
