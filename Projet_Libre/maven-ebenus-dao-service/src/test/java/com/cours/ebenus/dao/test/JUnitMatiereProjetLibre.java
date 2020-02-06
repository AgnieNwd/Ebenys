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
import com.cours.ebenus.maven.ebenus.dao.entities.Matiere;
import com.cours.ebenus.maven.ebenus.dao.entities.Role;
import com.cours.ebenus.maven.ebenus.dao.entities.User;
import com.cours.ebenus.maven.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.maven.ebenus.service.IServiceFacade;
import com.cours.ebenus.maven.ebenus.service.ServiceFacade;

public class JUnitMatiereProjetLibre extends JUnitQuestProjetLibre {
	
	private static final Log log = LogFactory.getLog(JUnitMatiereProjetLibre.class);
    
	public static void verifyMatiereData(Matiere matiere) {
		log.debug("Entree de la methode");
		if (matiere != null) {
			log.debug("idMatiere : " + matiere.getIdMatiere());
			Assert.assertNotNull(matiere.getIdMatiere());
			Assert.assertNotNull(matiere.getLibelleMatiere());
			Assert.assertNotNull(matiere.getUser());
			Assert.assertNotNull(matiere.getUser().getId());
			Assert.assertNotNull(matiere.getUser().getEmail());
			Assert.assertNotNull(matiere.getUser().getNom());
			Assert.assertNotNull(matiere.getUser().getPrenom());
		} else if (matiere == null) {
			Assert.fail("Matiere null");
		}
		log.debug("Sortie de la methode");
	}

	@Test
	public void testFindAllMatieres() {
		log.debug("Entree de la methode");
		if (matieres != null) {
			log.debug("NB_MATIERES_LIST: " + NB_MATIERES_LIST + " , matieres.size(): " + matieres.size());
			Assert.assertEquals(NB_MATIERES_LIST, matieres.size());
		} else {
			Assert.fail("Aucune matiere n'a ete trouves dans la base de données");
		}
		log.debug("Sortie de la methode");
	}
	
	@Test
	public void testFindByCriteria() {
		log.debug("Entree de la methode");
		List<Matiere> matiereByLibelle = serviceFacade.getMatiereDao()
				.findMatiereByLibelleMatiere(MATIERE_FIND_BY_LIBELLE);
		List<Matiere> matiereByUserId = serviceFacade.getMatiereDao()
				.findMatiereByIdUser(MATIERE_FIND_BY_USER_ID);
		List<Matiere> matiereByUserEmail = serviceFacade.getMatiereDao()
				.findUsersByEmailUser(MATIERE_FIND_BY_USER_EMAIL);

		if (matiereByLibelle != null) {
			log.debug("NB_MATIERE_FIND_BY_LIBELLE: " + NB_MATIERE_FIND_BY_LIBELLE
					+ " , matiereByLibelle.size(): " + matiereByLibelle.size());
			Assert.assertEquals(NB_MATIERE_FIND_BY_LIBELLE, matiereByLibelle.size());
		} else {
			Assert.fail("Aucune matiere avec l'identifiant " + MATIERE_FIND_BY_LIBELLE
					+ "' n'a ete trouve dans la base de données");
		}
		if (matiereByUserId != null) {
			log.debug("NB_MATIERE_FIND_BY_USER_ID: " + NB_MATIERE_FIND_BY_USER_ID
					+ " , matiereByUserId.size(): " + matiereByUserId.size());
			Assert.assertEquals(NB_MATIERE_FIND_BY_USER_ID, matiereByUserId.size());
		} else {
			Assert.fail("Aucune matiere avec l'identifiant " + NB_MATIERE_FIND_BY_USER_ID
					+ "' n'a ete trouve dans la base de données");
		}
		if (matiereByUserEmail != null) {
			log.debug("NB_MATIERE_FIND_BY_USER_EMAIL: " + NB_MATIERE_FIND_BY_USER_EMAIL
					+ " , matiereByUserEmail.size(): " + matiereByUserEmail.size());
			Assert.assertEquals(NB_MATIERE_FIND_BY_USER_EMAIL, matiereByUserEmail.size());
		} else {
			Assert.fail("Aucune matiere avec l'identifiant " + NB_MATIERE_FIND_BY_USER_EMAIL
					+ "' n'a ete trouve dans la base de données");
		}
	}

	@Test
	public void testCreateUpdateDeleteMatiere() {
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
        User userCRUD = new User("Nicole", "Valentine", "test@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), roleCRUD, classeCRUD);
        userCRUD = serviceFacade.getUserDao().createUser(userCRUD);
        JUnitUserProjetLibre.verifyUserDatas(userCRUD);
        log.debug("Created userCRUD : " + userCRUD);
        log.debug("Created userCRUD.getId : " + userCRUD.getId());
        userCRUD = serviceFacade.getUserDao().findUserById(userCRUD.getId());
        Assert.assertNotNull(userCRUD);
		Matiere matiereCRUD = new Matiere("Anglais", userCRUD);
		matiereCRUD = serviceFacade.getMatiereDao().createMatiere(matiereCRUD);
		verifyMatiereData(matiereCRUD);
		log.debug("Created matiereCRUD : " + matiereCRUD);
		log.debug("Created matiereCRUD.getIdMatiere : " + matiereCRUD.getIdMatiere());
		matiereCRUD = serviceFacade.getMatiereDao().findMatiereById(matiereCRUD.getIdMatiere());
		Assert.assertNotNull(matiereCRUD);
		matiereCRUD.setLibelleMatiere("Arabe");
		matiereCRUD = serviceFacade.getMatiereDao().updateMatiere(matiereCRUD);
		Assert.assertNotNull(matiereCRUD);
		matiereCRUD = serviceFacade.getMatiereDao().findMatiereById(matiereCRUD.getIdMatiere());
		log.debug("Updated matiereCRUD : " + matiereCRUD);
		Assert.assertEquals("Arabe", matiereCRUD.getLibelleMatiere());
		Assert.assertTrue(serviceFacade.getMatiereDao().deleteMatiere(matiereCRUD));
		matiereCRUD = serviceFacade.getMatiereDao().findMatiereById(matiereCRUD.getIdMatiere());
		Assert.assertNull(matiereCRUD);
		List<Matiere> matiereFinal = serviceFacade.getMatiereDao().findAllMatieres();
		if (matiereFinal != null) {
			Assert.assertEquals(NB_MATIERES_LIST, matiereFinal.size());
			log.debug("matiereFinal.size() : " + matiereFinal.size() + " , NB_MATIERES_LIST: " + NB_MATIERES_LIST);
		}
		log.debug("Sortie de la methode");
	}

}
