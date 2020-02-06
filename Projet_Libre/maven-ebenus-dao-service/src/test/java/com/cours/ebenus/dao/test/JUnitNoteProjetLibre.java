package com.cours.ebenus.dao.test;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;

import com.cours.ebenus.maven.ebenus.dao.entities.Classe;
import com.cours.ebenus.maven.ebenus.dao.entities.Matiere;
import com.cours.ebenus.maven.ebenus.dao.entities.Note;
import com.cours.ebenus.maven.ebenus.dao.entities.Role;
import com.cours.ebenus.maven.ebenus.dao.entities.User;

public class JUnitNoteProjetLibre extends JUnitQuestProjetLibre {
	
private static final Log log = LogFactory.getLog(JUnitNoteProjetLibre.class);
    
	public static void verifyNoteData(Note note) {
		log.debug("Entree de la methode");
		if (note != null) {
			log.debug("idNote : " + note.getIdNote());
			Assert.assertNotNull(note.getIdNote());
			Assert.assertNotNull(note.getLibelleNote());
			Assert.assertNotNull(note.getUser());
			Assert.assertNotNull(note.getUser().getId());
			Assert.assertNotNull(note.getUser().getEmail());
			Assert.assertNotNull(note.getUser().getNom());
			Assert.assertNotNull(note.getUser().getPrenom());
			Assert.assertNotNull(note.getMatiere());
			Assert.assertNotNull(note.getMatiere().getLibelleMatiere());
			Assert.assertNotNull(note.getMatiere().getLibelleMatiere());
		} else if (note == null) {
			Assert.fail("Note null");
		}
		log.debug("Sortie de la methode");
	}

	@Test
	public void testFindAllNotes() {
		log.debug("Entree de la methode");
		if (notes != null) {
			log.debug("NB_NOTES_LIST: " + NB_NOTES_LIST + " , notes.size(): " + notes.size());
			Assert.assertEquals(NB_NOTES_LIST, notes.size());
		} else {
			Assert.fail("Aucune note n'a ete trouves dans la base de données");
		}
		log.debug("Sortie de la methode");
	}
	
	@Test
	public void testFindByCriteria() {
		log.debug("Entree de la methode");
		List<Note> noteByLibelle = serviceFacade.getNoteDao()
				.findNoteByLibelleNote(NOTE_FIND_BY_LIBELLE);
		List<Note> noteByUserId = serviceFacade.getNoteDao()
				.findNoteByIdUser(NOTE_FIND_BY_USER_ID);
		List<Note> noteByUserEmail = serviceFacade.getNoteDao()
				.findNoteByEmailUser(NOTE_FIND_BY_USER_EMAIL);
		List<Note> noteByMatiereId = serviceFacade.getNoteDao()
				.findNoteByIdMatiere(NOTE_FIND_BY_ID_MATIERE);
		List<Note> noteByMatiereLibelle = serviceFacade.getNoteDao()
				.findNoteByLibelleMatiere(NOTE_FIND_BY_LIBELLE_MATIERE);

		if (noteByLibelle != null) {
			log.debug("NB_NOTE_FIND_BY_LIBELLE: " + NB_NOTE_FIND_BY_LIBELLE
					+ " , noteByLibelle.size(): " + noteByLibelle.size());
			Assert.assertEquals(NB_NOTE_FIND_BY_LIBELLE, noteByLibelle.size());
		} else {
			Assert.fail("Aucune note avec l'identifiant " + NOTE_FIND_BY_LIBELLE
					+ "' n'a ete trouve dans la base de données");
		}
		if (noteByUserId != null) {
			log.debug("NB_NOTE_FIND_BY_USER_ID: " + NB_NOTE_FIND_BY_USER_ID
					+ " , noteByUserId.size(): " + noteByUserId.size());
			Assert.assertEquals(NB_NOTE_FIND_BY_USER_ID, noteByUserId.size());
		} else {
			Assert.fail("Aucune note avec l'identifiant " + NOTE_FIND_BY_USER_ID
					+ "' n'a ete trouve dans la base de données");
		}
		if (noteByUserEmail != null) {
			log.debug("NB_NOTE_FIND_BY_USER_EMAIL: " + NB_NOTE_FIND_BY_USER_EMAIL
					+ " , noteByUserEmail.size(): " + noteByUserEmail.size());
			Assert.assertEquals(NB_NOTE_FIND_BY_USER_EMAIL, noteByUserEmail.size());
		} else {
			Assert.fail("Aucune note avec l'identifiant " + NOTE_FIND_BY_USER_EMAIL
					+ "' n'a ete trouve dans la base de données");
		}
		if (noteByMatiereId != null) {
			log.debug("NB_NOTE_FIND_BY_ID_MATIERE: " + NB_NOTE_FIND_BY_ID_MATIERE
					+ " , noteByMatiereId.size(): " + noteByMatiereId.size());
			Assert.assertEquals(NB_NOTE_FIND_BY_ID_MATIERE, noteByMatiereId.size());
		} else {
			Assert.fail("Aucune note avec l'identifiant " + NOTE_FIND_BY_ID_MATIERE
					+ "' n'a ete trouve dans la base de données");
		}
		if (noteByMatiereLibelle != null) {
			log.debug("NB_NOTE_FIND_BY_LIBELLE_MATIERE: " + NB_NOTE_FIND_BY_LIBELLE_MATIERE
					+ " , noteByMatiereLibelle.size(): " + noteByMatiereLibelle.size());
			Assert.assertEquals(NB_NOTE_FIND_BY_LIBELLE_MATIERE, noteByMatiereLibelle.size());
		} else {
			Assert.fail("Aucune note avec l'identifiant " + NOTE_FIND_BY_LIBELLE_MATIERE
					+ "' n'a ete trouve dans la base de données");
		}
	}

	@Test
	public void testCreateUpdateDeleteNote() {
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
        User userCRUD = new User("Nicole", "Valentine", "note@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), roleCRUD, classeCRUD);
        userCRUD = serviceFacade.getUserDao().createUser(userCRUD);
        JUnitUserProjetLibre.verifyUserDatas(userCRUD);
        log.debug("Created userCRUD : " + userCRUD);
        log.debug("Created userCRUD.getId : " + userCRUD.getId());
        userCRUD = serviceFacade.getUserDao().findUserById(userCRUD.getId());
        Assert.assertNotNull(userCRUD);
		Matiere matiereCRUD = new Matiere("Anglais", userCRUD);
		matiereCRUD = serviceFacade.getMatiereDao().createMatiere(matiereCRUD);
		JUnitMatiereProjetLibre.verifyMatiereData(matiereCRUD);
		log.debug("Created matiereCRUD : " + matiereCRUD);
		log.debug("Created matiereCRUD.getIdMatiere : " + matiereCRUD.getIdMatiere());
		Note noteCRUD = new Note(15.8, userCRUD, matiereCRUD);
		noteCRUD = serviceFacade.getNoteDao().createNote(noteCRUD);
        verifyNoteData(noteCRUD);
        noteCRUD = serviceFacade.getNoteDao().findNoteById(noteCRUD.getIdNote());
		Assert.assertNotNull(noteCRUD);
		noteCRUD.setLibelleNote(17.5);
		noteCRUD = serviceFacade.getNoteDao().updateNote(noteCRUD);
		Assert.assertNotNull(noteCRUD);
		noteCRUD = serviceFacade.getNoteDao().findNoteById(noteCRUD.getIdNote());
		log.debug("Updated matiereCRUD : " + noteCRUD);
		Double newNote = 17.5;
		Assert.assertEquals(newNote, noteCRUD.getLibelleNote());
		Assert.assertTrue(serviceFacade.getNoteDao().deleteNote(noteCRUD));
		noteCRUD = serviceFacade.getNoteDao().findNoteById(noteCRUD.getIdNote());
		Assert.assertNull(noteCRUD);
		List<Note> noteFinal = serviceFacade.getNoteDao().findAllNotes();
		if (noteFinal != null) {
			Assert.assertEquals(NB_NOTES_LIST, noteFinal.size());
			log.debug("noteFinal.size() : " + noteFinal.size() + " , NB_NOTES_LIST: " + NB_NOTES_LIST);
		}
		log.debug("Sortie de la methode");
	}

}
