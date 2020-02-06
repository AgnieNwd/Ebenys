package com.cours.ebenus.dao.test;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;

import com.cours.ebenus.maven.ebenus.dao.entities.Classe;
import com.cours.ebenus.maven.ebenus.dao.entities.Role;

public class JUnitClasseProjetLibre extends JUnitQuestProjetLibre {

	private static final Log log = LogFactory.getLog(JUnitClasseProjetLibre.class);
	
	public static void verifyClasseData(Classe classe) {
		log.debug("Entree de la methode");
		if (classe != null) {
			log.debug("idClasse : " + classe.getIdClasse());
			Assert.assertNotNull(classe.getIdClasse());
		} else if (classe == null) {
			Assert.fail("Classe null");
		}
		log.debug("Sortie de la methode");
	}
	
	@Test
	public void testFindAllClasses() {
		log.debug("Entree de la methode");
		if (roles != null) {
			log.debug("NB_CLASSES_LIST: " + NB_CLASSES_LIST + " , classes.size(): " + classes.size());
			Assert.assertEquals(NB_CLASSES_LIST, classes.size());
		} else {
			Assert.fail("Aucune Classe n'a ete trouves dans votre base de données");
		}
		log.debug("Sortie de la methode");
	}
 	
    @Test
	public void testFindByCriteria() {
		log.debug("Entree de la methode");
		List<Classe> classeByIdentifiant4eme = serviceFacade.getClasseDao()
				.findClasseByLibelleClasse(CLASSE_FIND_BY_IDENTIFIANT_4EME);

		if (classeByIdentifiant4eme != null) {
			log.debug("NB_CLASSE_FIND_BY_IDENTIFIANT_4EME: " + NB_CLASSE_FIND_BY_IDENTIFIANT_4EME
					+ " , classeByIdentifiant4eme.size(): " + classeByIdentifiant4eme.size());
			Assert.assertEquals(NB_CLASSE_FIND_BY_IDENTIFIANT_4EME, classeByIdentifiant4eme.size());
		} else {
			Assert.fail("Aucune classe avec l'identifiant " + CLASSE_FIND_BY_IDENTIFIANT_4EME
					+ "' n'a ete trouve dans la base de données");
		}
	}

	@Test
	public void testCreateUpdateDeleteClasse() {
		log.debug("Entree de la methode");
		Classe classeCRUD = new Classe("5e");
		classeCRUD = serviceFacade.getClasseDao().createClasse(classeCRUD);
		verifyClasseData(classeCRUD);
		log.debug("Created classeCRUD : " + classeCRUD);
		log.debug("Created classeCRUD.getIdCLasse : " + classeCRUD.getIdClasse());
		classeCRUD = serviceFacade.getClasseDao().findClasseById(classeCRUD.getIdClasse());
		Assert.assertNotNull(classeCRUD);
		classeCRUD.setLibelleClasse("6e");
		classeCRUD = serviceFacade.getClasseDao().updateClasse(classeCRUD);
		Assert.assertNotNull(classeCRUD);
		classeCRUD = serviceFacade.getClasseDao().findClasseById(classeCRUD.getIdClasse());
		log.debug("Updated classeCRUD : " + classeCRUD);
		Assert.assertEquals("6e", classeCRUD.getLibelleClasse());
		Assert.assertTrue(serviceFacade.getClasseDao().deleteClasse(classeCRUD));
		classeCRUD = serviceFacade.getClasseDao().findClasseById(classeCRUD.getIdClasse());
		Assert.assertNull(classeCRUD);
		List<Classe> classesFinal = serviceFacade.getClasseDao().findAllClasses();
		if (classesFinal != null) {
			Assert.assertEquals(NB_CLASSES_LIST, classesFinal.size());
			log.debug("classesFinal.size() : " + classesFinal.size() + " , NB_CLASSES_LIST: " + NB_CLASSES_LIST);
		}
		log.debug("Sortie de la methode");
	}

}
