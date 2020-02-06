package com.cours.ebenus.maven.ebenus.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cours.ebenus.maven.ebenus.dao.ConnectionHelper;
import com.cours.ebenus.maven.ebenus.dao.DriverManagerSingleton;
import com.cours.ebenus.maven.ebenus.dao.INoteDao;
import com.cours.ebenus.maven.ebenus.dao.entities.Classe;
import com.cours.ebenus.maven.ebenus.dao.entities.Matiere;
import com.cours.ebenus.maven.ebenus.dao.entities.Note;
import com.cours.ebenus.maven.ebenus.dao.entities.Role;
import com.cours.ebenus.maven.ebenus.dao.entities.User;
import com.cours.ebenus.maven.ebenus.dao.exception.ProjectException;

public class NoteDao implements INoteDao {
	
	private static final Log log = LogFactory.getLog(ClasseDao.class);

    private static DriverManagerSingleton DMsingle = DriverManagerSingleton.getInstance();
    
    public NoteDao() {
    	
    }
    
    private Note setInfosNote(ResultSet result) throws SQLException {
    	Role userRole = new Role(result.getInt("Role.id"), result.getString("identifiant"), result.getString("description"));
		Classe userClasse = new Classe(result.getInt("Classe.id"), result.getString("libelle_classe"));
        User user = new User(
        		result.getInt("User.id"), 
        		result.getString("nom"),
                result.getString("prenom"), 
                result.getString("email"), 
                result.getString("password"), 
                result.getDate("date_naissance"), 
                result.getTimestamp("date_creation"),
                result.getTimestamp("date_modification"), 
                userRole,
                userClasse);
    	Matiere userMatiere = new Matiere(result.getInt("Matiere.id"), result.getString("libelle_matiere"), user);
    	return new Note(result.getInt("id"), result.getDouble("libelle_note"), user, userMatiere);
    }

	@Override
	public List<Note> findAllNotes(){
		log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
        
    	List<Note> noteList = new ArrayList<Note>();
    	
    	String selectSQL = "SELECT * FROM Note INNER JOIN User ON Note.id_user = User.id INNER JOIN Role ON User.id_role = Role.id INNER JOIN Classe ON User.id_classe = Classe.id INNER JOIN Matiere ON Note.id_matiere = Matiere.id;";
    	try {
    		preparedState = connectionState.prepareStatement(selectSQL);
    		result = preparedState.executeQuery(selectSQL);
    		while (result.next()) {
    			Note newNote = setInfosNote(result);
    			noteList.add(newNote);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            ConnectionHelper.closeSqlResources(preparedState, result);
        }
        return noteList;	
	}

	@Override
    public Note findNoteById(int idNote) {
		log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
    	
    	String selectSQL = "SELECT * FROM Note INNER JOIN User ON Note.id_user = User.id INNER JOIN Role ON User.id_role = Role.id INNER JOIN Classe ON User.id_classe = Classe.id INNER JOIN Matiere ON Note.id_matiere = Matiere.id WHERE Note.id = ?;";

    	Note newNote = null;
    	
    	try {
    		preparedState = connectionState.prepareStatement(selectSQL);
    		preparedState.setInt(1, idNote);

    		result = preparedState.executeQuery();
    		while (result.next()) {
    			newNote = setInfosNote(result);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            ConnectionHelper.closeSqlResources(preparedState, result);
        }
        return newNote;
    }

    @Override
    public List<Note> findNoteByLibelleNote(Double libelleNote){
    	log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
    	
    	String selectSQL = "SELECT * FROM Note INNER JOIN User ON Note.id_user = User.id INNER JOIN Role ON User.id_role = Role.id INNER JOIN Classe ON User.id_classe = Classe.id INNER JOIN Matiere ON Note.id_matiere = Matiere.id WHERE Note.libelle_note = ?;";

    	List<Note> noteList = new ArrayList<Note>();
    	
    	try {
    		preparedState = connectionState.prepareStatement(selectSQL);
    		preparedState.setDouble(1, libelleNote);

    		result = preparedState.executeQuery();
    		while (result.next()) {
    			Note newNote = setInfosNote(result);
    			
    			noteList.add(newNote);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            ConnectionHelper.closeSqlResources(preparedState, result);
        }
        return noteList;	
    }
    
    @Override
    public List<Note> findNoteByIdUser(int idUser){
    	log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
    	
    	String selectSQL = "SELECT * FROM Note INNER JOIN User ON Note.id_user = User.id INNER JOIN Role ON User.id_role = Role.id INNER JOIN Classe ON User.id_classe = Classe.id INNER JOIN Matiere ON Note.id_matiere = Matiere.id WHERE Note.id_user = ?;";

    	List<Note> noteList = new ArrayList<Note>();
    	
    	try {
    		preparedState = connectionState.prepareStatement(selectSQL);
    		preparedState.setInt(1, idUser);

    		result = preparedState.executeQuery();
    		while (result.next()) {
    			Note newNote = setInfosNote(result);
    			
    			noteList.add(newNote);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            ConnectionHelper.closeSqlResources(preparedState, result);
        }
        return noteList;	
    }
    
    @Override
    public List<Note> findNoteByEmailUser(String emailUser){
    	log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
    	
    	String selectSQL = "SELECT * FROM Note INNER JOIN User ON Note.id_user = User.id INNER JOIN Role ON User.id_role = Role.id INNER JOIN Classe ON User.id_classe = Classe.id INNER JOIN Matiere ON Note.id_matiere = Matiere.id WHERE User.email = ?;";

    	List<Note> noteList = new ArrayList<Note>();
    	
    	try {
    		preparedState = connectionState.prepareStatement(selectSQL);
    		preparedState.setString(1, emailUser);

    		result = preparedState.executeQuery();
    		while (result.next()) {
    			Note newNote = setInfosNote(result);
    			
    			noteList.add(newNote);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            ConnectionHelper.closeSqlResources(preparedState, result);
        }
        return noteList;	
    }
    
    @Override
    public List<Note> findNoteByIdMatiere(int idMatiere){
    	log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
    	
    	String selectSQL = "SELECT * FROM Note INNER JOIN User ON Note.id_user = User.id INNER JOIN Role ON User.id_role = Role.id INNER JOIN Classe ON User.id_classe = Classe.id INNER JOIN Matiere ON Note.id_matiere = Matiere.id WHERE Note.id_matiere = ?;";

    	List<Note> noteList = new ArrayList<Note>();
    	
    	try {
    		preparedState = connectionState.prepareStatement(selectSQL);
    		preparedState.setInt(1, idMatiere);

    		result = preparedState.executeQuery();
    		while (result.next()) {
    			Note newNote = setInfosNote(result);
    			
    			noteList.add(newNote);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            ConnectionHelper.closeSqlResources(preparedState, result);
        }
        return noteList;	
    }
    
    @Override
    public List<Note> findNoteByLibelleMatiere(String libelleMatiere){
    	log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
    	
    	String selectSQL = "SELECT * FROM Note INNER JOIN User ON Note.id_user = User.id INNER JOIN Role ON User.id_role = Role.id INNER JOIN Classe ON User.id_classe = Classe.id INNER JOIN Matiere ON Note.id_matiere = Matiere.id WHERE Matiere.libelle_matiere = ?;";

    	List<Note> noteList = new ArrayList<Note>();
    	
    	try {
    		preparedState = connectionState.prepareStatement(selectSQL);
    		preparedState.setString(1, libelleMatiere);

    		result = preparedState.executeQuery();
    		while (result.next()) {
    			Note newNote = setInfosNote(result);
    			
    			noteList.add(newNote);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            ConnectionHelper.closeSqlResources(preparedState, result);
        }
        return noteList;	
    }

    @Override
    public Note createNote(Note note) {
    	log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
    	        
    	String selectSQL = "INSERT INTO Note(libelle_note, id_user, id_matiere) VALUES (?,?,?)";

    	if (note.getIdNote() == null) {//|| findRoleById(role.getIdRole()) == null) {
	    	try {
	    		preparedState = connectionState.prepareStatement(selectSQL, Statement.RETURN_GENERATED_KEYS);
	    		preparedState.setDouble(1, note.getLibelleNote());
	    		preparedState.setInt(2, note.getUser().getId());
	    		preparedState.setInt(3, note.getMatiere().getIdMatiere());
	    		preparedState.execute();
	    		
	    		result = preparedState.getGeneratedKeys();
	    		if (result.next()) {
	    			note.setidNote(result.getInt(1));
	            }
	            	
	            return note;
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    	} finally {
	            ConnectionHelper.closeSqlResources(preparedState, result);
	        }
    	}
        return null;
    }

    @Override
    public Note updateNote(Note note) {
    	log.debug("Entree de la methode");
        Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;

    	String selectSQL = "UPDATE `Note` set `libelle_note` =? , `id_user` =?, `id_matiere` =?  WHERE `id` = ?";
    	if (findNoteById(note.getIdNote()) != null) {
	    	try {
	    		preparedState = connectionState.prepareStatement(selectSQL);
	    		preparedState.setDouble(1, note.getLibelleNote());
	    		preparedState.setInt(2, note.getUser().getId());
	    		preparedState.setInt(3, note.getMatiere().getIdMatiere());
	    		preparedState.setInt(4, note.getIdNote());
	    		preparedState.executeUpdate();
	            return note;
	            
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    	} finally {
	            ConnectionHelper.closeSqlResources(preparedState, result);
	        }
    	}
        return null;
    }

    @Override
    public boolean deleteNote(Note note) {
    	log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
    	
    	String selectSQL = "DELETE FROM Note WHERE `id` = ?";
    	if (findNoteById(note.getIdNote()) != null) {
	    	try {
	    		preparedState = connectionState.prepareStatement(selectSQL);
	    		preparedState.setInt(1, note.getIdNote());
	    		preparedState.executeUpdate();
	    		return true;
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    	} finally {
	            ConnectionHelper.closeSqlResources(preparedState, result);
	        }
    	}
        return false;
    }
}
