/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.impl;

import com.cours.ebenus.dao.ConnectionHelper;
import com.cours.ebenus.dao.DriverManagerSingleton;
import com.cours.ebenus.dao.IUtilisateurDao;
import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.dao.exception.EbenusException;
import com.cours.ebenus.utils.Constants;

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

/**
 *
 * @author ElHadji
 */
public class UtilisateurDao /*extends AbstractDao<Utilisateur>*/ implements IUtilisateurDao {

    private static final Log log = LogFactory.getLog(UtilisateurDao.class);
    
    //public UtilisateurDao() {
    //    super(Utilisateur.class);
    //}
    
    private Utilisateur setInfosUtilisateur(ResultSet result) throws SQLException {
		Role userRole = new Role(result.getInt("idRole"), result.getString("Role.identifiant"), result.getString("description"), result.getInt("Role.version"));
        return new Utilisateur(
        		result.getInt("idUtilisateur"), 
        		result.getString("civilite"),
                result.getString("prenom"), 
                result.getString("nom"), 
                result.getString("identifiant"),
                result.getString("motPasse"), 
                result.getDate("dateNaissance"), 
                result.getTimestamp("dateCreation"),
                result.getTimestamp("dateModification"), 
                result.getBoolean("actif"),
                result.getBoolean("marquerEffacer"), 
                result.getInt("version"),
                userRole);
    }
    
    @Override
    public List<Utilisateur> findAllUtilisateurs() {
        log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
        
    	List<Utilisateur> utilisateurList = new ArrayList<Utilisateur>();
    	
    	String selectSQL = "SELECT * FROM Utilisateur INNER JOIN Role ON Utilisateur.idRole = Role.idRole;";
    	try {
    		preparedState = connectionState.prepareStatement(selectSQL);
    		result = preparedState.executeQuery(selectSQL);
    		while (result.next()) {
    			Utilisateur newUtilisateur = setInfosUtilisateur(result);
    			utilisateurList.add(newUtilisateur);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            ConnectionHelper.closeSqlResources(preparedState, result);
        }
        return utilisateurList;
    }

    @Override
    public Utilisateur findUtilisateurById(int idUtilisateur) {
        log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
    	
    	String selectSQL = "SELECT * FROM Utilisateur INNER JOIN Role ON Utilisateur.idRole = Role.idRole WHERE Utilisateur.idUtilisateur = ?;";

    	Utilisateur newUtilisateur = null;
    	
    	try {
    		preparedState = connectionState.prepareStatement(selectSQL);
    		preparedState.setInt(1, idUtilisateur);

    		result = preparedState.executeQuery();
    		while (result.next()) {
    			newUtilisateur = setInfosUtilisateur(result);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            ConnectionHelper.closeSqlResources(preparedState, result);
        }
        return newUtilisateur;
    }

    @Override
    public List<Utilisateur> findUtilisateursByPrenom(String prenom) {
        log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
    	
    	String selectSQL = "SELECT * FROM Utilisateur INNER JOIN Role ON Utilisateur.idRole = Role.idRole WHERE Utilisateur.prenom = ?;";

    	List<Utilisateur> utilisateurList = new ArrayList<Utilisateur>();
    	
    	try {
    		preparedState = connectionState.prepareStatement(selectSQL);
    		preparedState.setString(1, prenom);

    		result = preparedState.executeQuery();
    		while (result.next()) {
    			Utilisateur newUtilisateur = setInfosUtilisateur(result);
    			
    			utilisateurList.add(newUtilisateur);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            ConnectionHelper.closeSqlResources(preparedState, result);
        }
        return utilisateurList;
    }

    @Override
    public List<Utilisateur> findUtilisateursByNom(String nom) {
        log.debug("Entree de la methode");
        Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
    	
    	String selectSQL = "SELECT * FROM Utilisateur INNER JOIN Role ON Utilisateur.idRole = Role.idRole WHERE Utilisateur.nom = ?;";

    	List<Utilisateur> utilisateurList = new ArrayList<Utilisateur>();
    	
    	try {
    		preparedState = connectionState.prepareStatement(selectSQL);
    		preparedState.setString(1, nom);

    		result = preparedState.executeQuery();
    		while (result.next()) {
    			Utilisateur newUtilisateur = setInfosUtilisateur(result);
    			
    			utilisateurList.add(newUtilisateur);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            ConnectionHelper.closeSqlResources(preparedState, result);
        }
        return utilisateurList;
    }

    @Override
    public List<Utilisateur> findUtilisateurByIdentifiant(String identifiant) {
        log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
    	
    	String selectSQL = "SELECT * FROM Utilisateur INNER JOIN Role ON Utilisateur.idRole = Role.idRole WHERE Utilisateur.identifiant = ?;";

    	List<Utilisateur> utilisateurList = new ArrayList<Utilisateur>();
    	
    	try {
    		preparedState = connectionState.prepareStatement(selectSQL);
    		preparedState.setString(1, identifiant);

    		result = preparedState.executeQuery();
    		while (result.next()) {
    			Utilisateur newUtilisateur = setInfosUtilisateur(result);
    			
    			utilisateurList.add(newUtilisateur);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            ConnectionHelper.closeSqlResources(preparedState, result);
        }
        return utilisateurList;
    }

    @Override
    public List<Utilisateur> findUtilisateursByIdRole(int idRole) {
        log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
    	
    	String selectSQL = "SELECT * FROM Utilisateur INNER JOIN Role ON Utilisateur.idRole = Role.idRole WHERE Role.idRole = ?;";

    	List<Utilisateur> utilisateurList = new ArrayList<Utilisateur>();
    	
    	try {
    		preparedState = connectionState.prepareStatement(selectSQL);
    		preparedState.setInt(1, idRole);

    		result = preparedState.executeQuery();
    		while (result.next()) {
    			Utilisateur newUtilisateur = setInfosUtilisateur(result);
    			
    			utilisateurList.add(newUtilisateur);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            ConnectionHelper.closeSqlResources(preparedState, result);
        }
        return utilisateurList;
    }

    @Override
    public List<Utilisateur> findUtilisateursByIdentifiantRole(String identifiantRole) {
        log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
    	
    	String selectSQL = "SELECT * FROM Utilisateur INNER JOIN Role ON Utilisateur.idRole = Role.idRole WHERE Role.identifiant = ?;";

    	List<Utilisateur> utilisateurList = new ArrayList<Utilisateur>();
    	
    	try {
    		preparedState = connectionState.prepareStatement(selectSQL);
    		preparedState.setString(1, identifiantRole);

    		result = preparedState.executeQuery();
    		while (result.next()) {
    			Utilisateur newUtilisateur = setInfosUtilisateur(result);
    			
    			utilisateurList.add(newUtilisateur);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            ConnectionHelper.closeSqlResources(preparedState, result);
        }
        return utilisateurList;
    }

    @Override
    public Utilisateur createUtilisateur(Utilisateur user) {
        log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        PreparedStatement preparedState = null;
        ResultSet result = null;
        
        Timestamp dateBorn = new java.sql.Timestamp(user.getDateNaissance().getTime());
        Timestamp dateCreation = new java.sql.Timestamp(new Date(System.currentTimeMillis()).getTime());
        
    	String selectSQL = "INSERT INTO Utilisateur(civilite,prenom,nom,idRole,identifiant,motPasse,dateNaissance,dateCreation,dateModification) VALUES (?,?,?,?,?,?,?,?,?)";
		if (user.getIdUtilisateur() == null) {// || findUtilisateurById(user.getIdUtilisateur()) == null) {
	    	if ( findUtilisateurByIdentifiant(user.getIdentifiant()).isEmpty()) {
				try {
		    		preparedState = connectionState.prepareStatement(selectSQL, Statement.RETURN_GENERATED_KEYS);
		    		preparedState.setString(1, user.getCivilite());
		    		preparedState.setString(2, user.getPrenom());
		    		preparedState.setString(3, user.getNom());
		    		preparedState.setInt(4, user.getRole().getIdRole());
		    		preparedState.setString(5, user.getIdentifiant());
		    		preparedState.setString(6, user.getMotPasse());
		    		preparedState.setTimestamp(7, dateBorn);
		    		preparedState.setTimestamp(8, dateCreation);
		    		preparedState.setTimestamp(9, dateCreation);
		
		    		preparedState.execute();
		
		    		result = preparedState.getGeneratedKeys();
		    		if (result.next()) {
		    			user.setIdUtilisateur(result.getInt(1));
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
	        	throw new EbenusException("Une erreur s’est produite, il existe déjà un utilisateur avec l’identifiant "+ user.getIdentifiant() +" dans l’application", -1);
	    	}

		}
        return null;
    }

    @Override
    public Utilisateur updateUtilisateur(Utilisateur user) {
        log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        PreparedStatement preparedState = null;
        ResultSet result = null;
        
        Timestamp dateBorn = new java.sql.Timestamp(user.getDateNaissance().getTime());
        Timestamp dateEdit = new java.sql.Timestamp(new Date(System.currentTimeMillis()).getTime());
        
        System.out.println("updateUtilisateur" + user.toString());
        System.out.println("updateUtilisateur dateBorn" + user.getDateNaissance().getTime() + dateBorn);
        System.out.println("updateUtilisateur dateEdit" + dateEdit);
        
    	String selectSQL = "UPDATE `Utilisateur` SET `civilite` = ? , `prenom` = ? , `nom` = ? , `idRole` = ? , `identifiant` = ? , `motPasse` = ? , `dateNaissance` = ? , `dateModification` = ? WHERE `idUtilisateur` = ?";
		if (findUtilisateurById(user.getIdUtilisateur()) != null) {
	    	try {
	    		preparedState = connectionState.prepareStatement(selectSQL);
	    		preparedState.setString(1, user.getCivilite());
	    		preparedState.setString(2, user.getPrenom());
	    		preparedState.setString(3, user.getNom());
	    		preparedState.setInt(4, user.getRole().getIdRole());
	    		preparedState.setString(5, user.getIdentifiant());
	    		preparedState.setString(6, user.getMotPasse());
	    		preparedState.setTimestamp(7, dateBorn);
	    		preparedState.setTimestamp(8, dateEdit);
	    		preparedState.setInt(9, user.getIdUtilisateur());
	
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
    public boolean deleteUtilisateur(Utilisateur user) {
        log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        PreparedStatement preparedState = null;
        ResultSet result = null;
    	
    	String selectSQL = "DELETE FROM `Utilisateur` WHERE `idUtilisateur` = ?";
    	if (findUtilisateurById(user.getIdUtilisateur()) != null) {	
	    	try {
	    		preparedState = connectionState.prepareStatement(selectSQL);
	    		System.out.println();
	    		preparedState.setInt(1, user.getIdUtilisateur());
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

    /**
     * Méthode qui vérifie les logs email / password d'un utilisateur dans la
     * base de données
     *
     * @param email L'email de l'utilisateur
     * @param password Le password de l'utilisateur
     * @return L'utilisateur qui tente de se logger si trouvé, null sinon
     */
    @Override
    public Utilisateur authenticate(String email, String password) {
    	log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        PreparedStatement preparedState = null;
        ResultSet result = null;
    	
    	Utilisateur newUtilisateur = null;

    	String selectSQL = "SELECT * FROM Utilisateur INNER JOIN Role ON Utilisateur.idRole = Role.idRole WHERE Utilisateur.identifiant = ? AND Utilisateur.motPasse = ?";
    	try {
    		preparedState = connectionState.prepareStatement(selectSQL);
    		preparedState.setString(1, email);
    		preparedState.setString(2, password);

    		result = preparedState.executeQuery();
    		while (result.next()) {
    			newUtilisateur = setInfosUtilisateur(result);
 
    			return newUtilisateur;
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            ConnectionHelper.closeSqlResources(preparedState, result);
        }
    	
    	return null;
    }

	@Override
	public Utilisateur updateUserWithoutPassword(Utilisateur user) {
		log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        PreparedStatement preparedState = null;
        ResultSet result = null;
        
        Timestamp dateBorn = new java.sql.Timestamp(user.getDateNaissance().getTime());
        Timestamp dateEdit = new java.sql.Timestamp(new Date(System.currentTimeMillis()).getTime());
                
    	String selectSQL = "UPDATE `Utilisateur` SET `civilite` = ? , `prenom` = ? , `nom` = ? , `idRole` = ? , `identifiant` = ? , `dateNaissance` = ? , `dateModification` = ? WHERE `idUtilisateur` = ?";
		if (findUtilisateurById(user.getIdUtilisateur()) != null) {
	    	try {
	    		preparedState = connectionState.prepareStatement(selectSQL);
	    		preparedState.setString(1, user.getCivilite());
	    		preparedState.setString(2, user.getPrenom());
	    		preparedState.setString(3, user.getNom());
	    		preparedState.setInt(4, user.getRole().getIdRole());
	    		preparedState.setString(5, user.getIdentifiant());
	    		preparedState.setTimestamp(6, dateBorn);
	    		preparedState.setTimestamp(7, dateEdit);
	    		preparedState.setInt(8, user.getIdUtilisateur());
	
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
}
