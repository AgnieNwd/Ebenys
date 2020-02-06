package com.cours.ebenus.maven.ebenus.dao.impl;

import com.cours.ebenus.maven.ebenus.dao.ConnectionHelper;
import com.cours.ebenus.maven.ebenus.dao.DriverManagerSingleton;
import com.cours.ebenus.maven.ebenus.dao.IUserDao;
import com.cours.ebenus.maven.ebenus.dao.entities.Classe;
import com.cours.ebenus.maven.ebenus.dao.entities.Role;
import com.cours.ebenus.maven.ebenus.dao.entities.User;
import com.cours.ebenus.maven.ebenus.dao.exception.ProjectException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class UserDao implements IUserDao{
	
	private static final Log log = LogFactory.getLog(UserDao.class);
	
	private User setInfosUser(ResultSet result) throws SQLException {
		Role userRole = new Role(result.getInt("Role.id"), result.getString("identifiant"), result.getString("description"));
		Classe userClasse = new Classe(result.getInt("Classe.id"), result.getString("libelle_classe"));
        return new User(
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
    }

	
	@Override
	public List<User> findAllUsers(){
		log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
        
    	List<User> userList = new ArrayList<User>();
    	
    	String selectSQL = "SELECT * FROM User INNER JOIN Role ON User.id_role = Role.id INNER JOIN Classe ON User.id_classe = Classe.id ORDER BY Role.id ASC;";
    	try {
    		preparedState = connectionState.prepareStatement(selectSQL);
    		result = preparedState.executeQuery(selectSQL);
    		while (result.next()) {
    			User newUtilisateur = setInfosUser(result);
    			userList.add(newUtilisateur);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            ConnectionHelper.closeSqlResources(preparedState, result);
        }
        return userList;	
	}
	
	@Override
    public User findUserById(int idUser) {
		log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
    	
    	String selectSQL = "SELECT * FROM User INNER JOIN Role ON User.id_role = Role.id INNER JOIN Classe ON User.id_classe = Classe.id WHERE User.id = ?;";

    	User newUser = null;
    	
    	try {
    		preparedState = connectionState.prepareStatement(selectSQL);
    		preparedState.setInt(1, idUser);

    		result = preparedState.executeQuery();
    		while (result.next()) {
    			newUser = setInfosUser(result);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            ConnectionHelper.closeSqlResources(preparedState, result);
        }
        return newUser;

    }
	@Override
    public List<User> findUsersByPrenom(String prenom){
		log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
    	
    	String selectSQL = "SELECT * FROM User INNER JOIN Role ON User.id_role = Role.id INNER JOIN Classe ON User.id_classe = Classe.id WHERE User.prenom = ?;";

    	List<User> userList = new ArrayList<User>();
    	
    	try {
    		preparedState = connectionState.prepareStatement(selectSQL);
    		preparedState.setString(1, prenom);

    		result = preparedState.executeQuery();
    		while (result.next()) {
    			User newUser = setInfosUser(result);
    			
    			userList.add(newUser);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            ConnectionHelper.closeSqlResources(preparedState, result);
        }
        return userList;	
    }

	@Override
    public List<User> findUsersByNom(String nom){
		log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
    	
    	String selectSQL = "SELECT * FROM User INNER JOIN Role ON User.id_role = Role.id INNER JOIN Classe ON User.id_classe = Classe.id WHERE User.nom = ?;";

    	List<User> userList = new ArrayList<User>();
    	
    	try {
    		preparedState = connectionState.prepareStatement(selectSQL);
    		preparedState.setString(1, nom);

    		result = preparedState.executeQuery();
    		while (result.next()) {
    			User newUser = setInfosUser(result);
    			
    			userList.add(newUser);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            ConnectionHelper.closeSqlResources(preparedState, result);
        }
        return userList;	
    }

	@Override
    public List<User> findUserByEmail(String email){
		log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
    	
    	String selectSQL = "SELECT * FROM User INNER JOIN Role ON User.id_role = Role.id INNER JOIN Classe ON User.id_classe = Classe.id WHERE User.email = ?;";

    	List<User> userList = new ArrayList<User>();
    	
    	try {
    		preparedState = connectionState.prepareStatement(selectSQL);
    		preparedState.setString(1, email);

    		result = preparedState.executeQuery();
    		while (result.next()) {
    			User newUser = setInfosUser(result);
    			
    			userList.add(newUser);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            ConnectionHelper.closeSqlResources(preparedState, result);
        }
        return userList;	
    	
    }

	@Override
    public List<User> findUsersByIdRole(int idRole){
		log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
    	
    	String selectSQL = "SELECT * FROM User INNER JOIN Role ON User.id_role = Role.id INNER JOIN Classe ON User.id_classe = Classe.id WHERE Role.id = ?;";

    	List<User> userList = new ArrayList<User>();
    	
    	try {
    		preparedState = connectionState.prepareStatement(selectSQL);
    		preparedState.setInt(1, idRole);

    		result = preparedState.executeQuery();
    		while (result.next()) {
    			User newUser = setInfosUser(result);
    			
    			userList.add(newUser);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            ConnectionHelper.closeSqlResources(preparedState, result);
        }
        return userList;		
    }

	@Override
    public List<User> findUsersByIdentifiantRole(String identifiant){
		log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
    	
    	String selectSQL = "SELECT * FROM User INNER JOIN Role ON User.id_role = Role.id INNER JOIN Classe ON User.id_classe = Classe.id WHERE Role.identifiant = ?;";

    	List<User> userList = new ArrayList<User>();
    	
    	try {
    		preparedState = connectionState.prepareStatement(selectSQL);
    		preparedState.setString(1, identifiant);

    		result = preparedState.executeQuery();
    		while (result.next()) {
    			User newUser = setInfosUser(result);
    			
    			userList.add(newUser);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            ConnectionHelper.closeSqlResources(preparedState, result);
        }
        return userList;		
    	
    }
	
	@Override
    public List<User> findUsersByLibelleClasseIdentifiantRole(String libelleClasse, String identifiant){
		log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
    	
    	String selectSQL = "SELECT * FROM User INNER JOIN Role ON User.id_role = Role.id INNER JOIN Classe ON User.id_classe = Classe.id WHERE Classe.libelle_classe = ? AND Role.identifiant = ?;";

    	List<User> userList = new ArrayList<User>();
    	
    	try {
    		preparedState = connectionState.prepareStatement(selectSQL);
    		preparedState.setString(1, libelleClasse);
    		preparedState.setString(2, identifiant);

    		result = preparedState.executeQuery();
    		while (result.next()) {
    			User newUser = setInfosUser(result);
    			
    			userList.add(newUser);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            ConnectionHelper.closeSqlResources(preparedState, result);
        }
        return userList;		
    	
    }

	@Override
    public User createUser(User user) {
		log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        PreparedStatement preparedState = null;
        ResultSet result = null;
        
        Timestamp dateBorn = new java.sql.Timestamp(user.getDateNaissance().getTime());
        Timestamp dateCreation = new java.sql.Timestamp(new Date(System.currentTimeMillis()).getTime());
        
    	String selectSQL = "INSERT INTO User(nom,prenom,email,id_role,id_classe,password,date_naissance,date_creation,date_modification) VALUES (?,?,?,?,?,?,?,?,?)";
		if (user.getId() == null) {// || findUtilisateurById(user.getIdUtilisateur()) == null) {
	    	if ( findUserByEmail(user.getEmail()).isEmpty()) {
				try {
		    		preparedState = connectionState.prepareStatement(selectSQL, Statement.RETURN_GENERATED_KEYS);
		    		preparedState.setString(1, user.getNom());
		    		preparedState.setString(2, user.getPrenom());
		    		preparedState.setString(3, user.getEmail());
		    		preparedState.setInt(4, user.getRole().getIdRole());
		    		preparedState.setInt(5, user.getClasse().getIdClasse());
		    		preparedState.setString(6, user.getPassword());
		    		preparedState.setTimestamp(7, dateBorn);
		    		preparedState.setTimestamp(8, dateCreation);
		    		preparedState.setTimestamp(9, dateCreation);
		
		    		preparedState.execute();
		
		    		result = preparedState.getGeneratedKeys();
		    		if (result.next()) {
		    			user.setId(result.getInt(1));
		    			user.setDateCreation(dateCreation);
		    			user.setDateModification(dateCreation);
		            }
		            return user;
		    	} catch (SQLException e) {
		    		e.printStackTrace();
		    	} finally {
		            ConnectionHelper.closeSqlResources(preparedState, result);
		        }
	    	} else {
	        	throw new ProjectException("Une erreur s’est produite, il existe déjà un utilisateur avec l’identifiant "+ user.getEmail() +" dans l’application", -1);
	    	}

		}
        return null;

    	
    }

	@Override
    public User updateUser(User user) {
		log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        PreparedStatement preparedState = null;
        ResultSet result = null;
        
        Timestamp dateBorn = new java.sql.Timestamp(user.getDateNaissance().getTime());
        Timestamp dateEdit = new java.sql.Timestamp(new Date(System.currentTimeMillis()).getTime());
        
    	String selectSQL = "UPDATE `User` SET `nom` = ? , `prenom` = ? , `email` = ? , `id_role` = ? , `id_classe` = ? , `password` = ? , `date_naissance` = ? , `date_modification` = ? WHERE `id` = ?";
		if (findUserById(user.getId()) != null) {
	    	try {
	    		preparedState = connectionState.prepareStatement(selectSQL);
	    		preparedState.setString(1, user.getNom());
	    		preparedState.setString(2, user.getPrenom());
	    		preparedState.setString(3, user.getEmail());
	    		preparedState.setInt(4, user.getRole().getIdRole());
	    		preparedState.setInt(5, user.getClasse().getIdClasse());
	    		preparedState.setString(6, user.getPassword());
	    		preparedState.setTimestamp(7, dateBorn);
	    		preparedState.setTimestamp(8, dateEdit);
	    		preparedState.setInt(9, user.getId());
	
	    		preparedState.executeUpdate();
		
	            return user;
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    	} finally {
	            ConnectionHelper.closeSqlResources(preparedState, result);
	        }
		}
        return null;
    }


    
	@Override
    public User updateUserWithoutPassword(User user) {
		log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        PreparedStatement preparedState = null;
        ResultSet result = null;
        
        Timestamp dateBorn = new java.sql.Timestamp(user.getDateNaissance().getTime());
        Timestamp dateEdit = new java.sql.Timestamp(new Date(System.currentTimeMillis()).getTime());
                
    	String selectSQL = "UPDATE `User` SET `nom` = ? , `prenom` = ? , `email` = ? , `id_role` = ? , `id_classe` = ? , `date_naissance` = ? , `date_modification` = ? WHERE `id` = ?";
    	if (findUserById(user.getId()) != null) {
	    	try {
	    		preparedState = connectionState.prepareStatement(selectSQL);
	    		preparedState.setString(1, user.getNom());
	    		preparedState.setString(2, user.getPrenom());
	    		preparedState.setString(3, user.getEmail());
	    		preparedState.setInt(4, user.getRole().getIdRole());
	    		preparedState.setInt(5, user.getClasse().getIdClasse());
	    		preparedState.setTimestamp(6, dateBorn);
	    		preparedState.setTimestamp(7, dateEdit);
	    		preparedState.setInt(8, user.getId());
	
	    		preparedState.executeUpdate();
		
	            return user;
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    	} finally {
	            ConnectionHelper.closeSqlResources(preparedState, result);
	        }
		}
        return null;
	}

	@Override
    public boolean deleteUser(User user) {
		log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        PreparedStatement preparedState = null;
        ResultSet result = null;
    	
        boolean isUpdated = updateQuery(user.getId());

    	String selectSQL = "DELETE FROM `User` WHERE `id` = ?;";
    			//+ "DELETE FROM `User` WHERE `id` = ?;"; // Update de la valeur id_user dans matiere avant de delete le user
    	
    	if (findUserById(user.getId()) != null) {	
	    	try {
	    		preparedState = connectionState.prepareStatement(selectSQL);
	    		preparedState.setInt(1, user.getId());
	    		//preparedState.setInt(2, user.getId());
	    		preparedState.execute();
	    		return true;
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    	} finally {
	            ConnectionHelper.closeSqlResources(preparedState, result);
	        }
    	}
        return false;
    }

	@Override
    public User authenticate(String email, String password) {
		log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        PreparedStatement preparedState = null;
        ResultSet result = null;

        User newUser = null;
    	String selectSQL = "SELECT * FROM User INNER JOIN Role ON User.id_role = Role.id INNER JOIN Classe ON User.id_classe = Classe.id WHERE User.email = ? AND User.password = ?";
    	try {
    		preparedState = connectionState.prepareStatement(selectSQL);
    		preparedState.setString(1, email);
    		preparedState.setString(2, password);

    		result = preparedState.executeQuery();
    		while (result.next()) {
    			newUser = setInfosUser(result);
    			return newUser;
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            ConnectionHelper.closeSqlResources(preparedState, result);
        }
    	
    	return null;

    }
	
	private boolean updateQuery(int id) {
		Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        PreparedStatement preparedState = null;
        ResultSet result = null;
    	String selectSQL = "UPDATE MATIERE SET id_user = 1 WHERE id_user = ?;";
    	try {
    		preparedState = connectionState.prepareStatement(selectSQL);
    		preparedState.setInt(1, id);

    		preparedState.executeUpdate();
    		return true;
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            ConnectionHelper.closeSqlResources(preparedState, result);
        }
		return false;
	}
}
