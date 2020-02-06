package com.cours.ebenus.maven.ebenus.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cours.ebenus.maven.ebenus.dao.ConnectionHelper;
import com.cours.ebenus.maven.ebenus.dao.DriverManagerSingleton;
import com.cours.ebenus.maven.ebenus.dao.IMatiereDao;
import com.cours.ebenus.maven.ebenus.dao.entities.Classe;
import com.cours.ebenus.maven.ebenus.dao.entities.Matiere;
import com.cours.ebenus.maven.ebenus.dao.entities.Role;
import com.cours.ebenus.maven.ebenus.dao.entities.User;

public class MatiereDao implements IMatiereDao {

	private static final Log log = LogFactory.getLog(RoleDao.class);

    private static DriverManagerSingleton DMsingle = DriverManagerSingleton.getInstance();
    
    private Matiere setInfosMatiere(ResultSet result) throws SQLException {
    	Role userRole = new Role(result.getInt("Role.id"), result.getString("identifiant"), result.getString("description"));
    	Classe userClass = new Classe(result.getInt("Classe.id"), result.getString("libelle_classe"));
    	
		User user = new User(result.getInt("User.id"), result.getString("nom"), result.getString("prenom"), result.getString("email"), result.getString("password"), result.getTimestamp("date_naissance"), result.getTimestamp("date_creation"), result.getTimestamp("date_modification"), userRole, userClass);

    	return new Matiere(result.getInt("id"), result.getString("libelle_matiere"), user);
    }
    
	@Override
	public List<Matiere> findAllMatieres(){
		log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
        
    	List<Matiere> utilisateurMatiere = new ArrayList<Matiere>();
    	
    	String selectSQL = "SELECT * FROM Matiere INNER JOIN User ON Matiere.id_user = User.id LEFT JOIN Role ON User.id_role = Role.id INNER JOIN Classe ON User.id_classe = Classe.id;";
    	try {
    		preparedState = connectionState.prepareStatement(selectSQL);
    		result = preparedState.executeQuery(selectSQL);
    		while (result.next()) {
    			Matiere newMatiere = setInfosMatiere(result);
    			utilisateurMatiere.add(newMatiere);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            ConnectionHelper.closeSqlResources(preparedState, result);
        }
        return utilisateurMatiere;
	}

	@Override
    public Matiere findMatiereById(int idMatiere) {
		log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
            	
    	String selectSQL = "SELECT * FROM Matiere INNER JOIN User ON Matiere.id_user = User.id LEFT JOIN Role ON User.id_role = Role.id INNER JOIN Classe ON User.id_classe = Classe.id where matiere.id = ?";
    	Matiere matiere = null;
    	
    	try {
    		preparedState = connectionState.prepareStatement(selectSQL);
    		preparedState.setInt(1, idMatiere);
    		result = preparedState.executeQuery();
    		
    		while (result.next()) {
    			matiere = setInfosMatiere(result);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            ConnectionHelper.closeSqlResources(preparedState, result);
        }
        return matiere;
    }

    @Override
    public List<Matiere> findMatiereByLibelleMatiere(String libelleMatiere){
    	log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
            	
    	String selectSQL = "SELECT * FROM Matiere INNER JOIN User ON Matiere.id_user = User.id LEFT JOIN Role ON User.id_role = Role.id INNER JOIN Classe ON User.id_classe = Classe.id WHERE `libelle_matiere` = ?";
    	List<Matiere> matiereList = new ArrayList<Matiere>();
    	
    	try {
    		preparedState = connectionState.prepareStatement(selectSQL);
    		preparedState.setString(1, libelleMatiere);
    		result = preparedState.executeQuery();
    		
    		while (result.next()) {
    			Matiere matiere = setInfosMatiere(result);
    			matiereList.add(matiere);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            ConnectionHelper.closeSqlResources(preparedState, result);
        }
        return matiereList;
    }
    
    @Override
    public List<Matiere> findMatiereByIdUser(int idUser){
    	log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
            	
    	String selectSQL = "SELECT * FROM Matiere INNER JOIN User ON Matiere.id_user = User.id INNER JOIN Role ON User.id_role = Role.id INNER JOIN Classe ON User.id_classe = Classe.id where user.id = ?";
    	List<Matiere> matiereList = new ArrayList<Matiere>();
    	
    	try {
    		preparedState = connectionState.prepareStatement(selectSQL);
    		preparedState.setInt(1, idUser);
    		result = preparedState.executeQuery();
    		
    		while (result.next()) {
    			Matiere matiere = setInfosMatiere(result);
    			matiereList.add(matiere);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            ConnectionHelper.closeSqlResources(preparedState, result);
        }
        return matiereList;
    }
    
    @Override
    public List<Matiere> findUsersByEmailUser(String emailUser){
    	log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
            	
    	String selectSQL = "SELECT * FROM Matiere INNER JOIN User ON Matiere.id_user = User.id INNER JOIN Role ON User.id_role = Role.id INNER JOIN Classe ON User.id_classe = Classe.id where user.email = ?;";
    	List<Matiere> matiereList = new ArrayList<Matiere>();
    	
    	try {
    		preparedState = connectionState.prepareStatement(selectSQL);
    		preparedState.setString(1, emailUser);
    		result = preparedState.executeQuery();
    		
    		while (result.next()) {
    			Matiere matiere = setInfosMatiere(result);
    			matiereList.add(matiere);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            ConnectionHelper.closeSqlResources(preparedState, result);
        }
        return matiereList;
    }
    
    @Override
    public Matiere createMatiere(Matiere matiere) {
		log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
    	        
    	String selectSQL = "INSERT INTO Matiere(libelle_matiere,id_user) VALUES (?,?)";
    	
    	if (matiere.getIdMatiere() == null) {//|| findRoleById(role.getIdRole()) == null) {
	    	try {
	    		preparedState = connectionState.prepareStatement(selectSQL, Statement.RETURN_GENERATED_KEYS);
	    		preparedState.setString(1, matiere.getLibelleMatiere());
	    		preparedState.setInt(2, matiere.getUser().getId());
	    		preparedState.execute();
	    		
	    		result = preparedState.getGeneratedKeys();
	    		if (result.next()) {
	    			matiere.setIdMatiere(result.getInt(1));
	            }
	            	
	            return matiere;
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    	} finally {
	            ConnectionHelper.closeSqlResources(preparedState, result);
	        }
    	}
        return null;
    }

    @Override
    public Matiere updateMatiere(Matiere matiere) {
		log.debug("Entree de la methode");
        Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;

    	String selectSQL = "UPDATE `Matiere` set `libelle_matiere` =? , `id_user` =? WHERE `id` = ?";
    	if (findMatiereById(matiere.getIdMatiere()) != null) {
	    	try {
	    		preparedState = connectionState.prepareStatement(selectSQL);
	    		preparedState.setString(1, matiere.getLibelleMatiere());
	    		preparedState.setInt(2, matiere.getUser().getId());
	    		preparedState.setInt(3, matiere.getIdMatiere());
	
	    		preparedState.executeUpdate();
	            return matiere;
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    	} finally {
	            ConnectionHelper.closeSqlResources(preparedState, result);
	        }
    	}
        return null;
    }

    @Override
    public boolean deleteMatiere(Matiere matiere) {
    	log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
    	
    	String selectSQL = "DELETE FROM Matiere WHERE `id` = ?";
    	if (findMatiereById(matiere.getIdMatiere()) != null) {
	    	try {
	    		preparedState = connectionState.prepareStatement(selectSQL);
	    		preparedState.setInt(1, matiere.getIdMatiere());
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
