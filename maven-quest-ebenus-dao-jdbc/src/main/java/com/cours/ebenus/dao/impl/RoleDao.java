/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.impl;

import com.cours.ebenus.dao.ConnectionHelper;
import com.cours.ebenus.dao.DriverManagerSingleton;
import com.cours.ebenus.dao.IRoleDao;
import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.utils.Constants;
import java.sql.PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class RoleDao /*extends AbstractDao<Role>*/ implements IRoleDao {

    private static final Log log = LogFactory.getLog(RoleDao.class);

    private static DriverManagerSingleton DMsingle = DriverManagerSingleton.getInstance();

    //private static Statement connectionState = DriverManagerSingleton.getConnectionInstance();
    
    public RoleDao() {
        //super(Role.class);
    }
    
    private Role setInfosRole(ResultSet result) throws SQLException {
    	return new Role(result.getInt("idRole"), result.getString("identifiant"), result.getString("description"), result.getInt("version"));
    }
    
    @Override
    public List<Role> findAllRoles() {
        log.debug("Entree de la methode");
        Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
    	
    	List<Role> roleList = new ArrayList<Role>();
    	
    	String selectSQL = "SELECT * FROM Role;";
    	try {
    		preparedState = connectionState.prepareStatement(selectSQL);
    		result = preparedState.executeQuery(selectSQL);
    		while (result.next()) {
    			Role newRole = setInfosRole(result);
  
    			roleList.add(newRole);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            ConnectionHelper.closeSqlResources(preparedState, result);
        }
        return roleList;
    }

    @Override
    public Role findRoleById(int idRole) {
        log.debug("Entree de la methode");
        Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
    	
    	String selectSQL = "SELECT * FROM Role WHERE idRole = ?";

    	Role newRole = null;
    	
    	try {
    		preparedState = connectionState.prepareStatement(selectSQL);
    		preparedState.setInt(1, idRole);
    		result = preparedState.executeQuery();
    		
    		while (result.next()) {
    			newRole = setInfosRole(result);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            ConnectionHelper.closeSqlResources(preparedState, result);
        }
        return newRole;
    }

    @Override
    public List<Role> findRoleByIdentifiant(String identifiantRole) {
        log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
    	
    	String selectSQL = "SELECT * FROM Role WHERE identifiant = ?";

    	List<Role> roleList = new ArrayList<Role>();
    	
    	try {
    		preparedState = connectionState.prepareStatement(selectSQL);
    		preparedState.setString(1, identifiantRole);
    		result = preparedState.executeQuery();
    		
    		while (result.next()) {
    			Role newRole = setInfosRole(result);
    			roleList.add(newRole);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            ConnectionHelper.closeSqlResources(preparedState, result);
        }
        return roleList;
    }

    @Override
    public Role createRole(Role role) {
        log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
    	        
    	String selectSQL = "INSERT INTO Role(identifiant, description, version) VALUES (?,?,?)";

    	if (role.getIdRole() == null) {//|| findRoleById(role.getIdRole()) == null) {
	    	try {
	    		preparedState = connectionState.prepareStatement(selectSQL, Statement.RETURN_GENERATED_KEYS);
	    		preparedState.setString(1, role.getIdentifiant());
	    		preparedState.setString(2, role.getDescription());
	    		preparedState.setInt(3, role.getVersion() != null ? role.getVersion() : 0);
	    		preparedState.execute();
	    		
	    		result = preparedState.getGeneratedKeys();
	    		if (result.next()) {
	    			role.setIdRole(result.getInt(1));
	            }
	            	
	            return role;
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    	} finally {
	            ConnectionHelper.closeSqlResources(preparedState, result);
	        }
    	}
        return null;
    }

    @Override
    public Role updateRole(Role role) {
        log.debug("Entree de la methode");
        Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;

    	String selectSQL = "UPDATE `Role` set `identifiant` =? , `description` =? , `version` =? WHERE `idRole` = ?";
    	if (findRoleById(role.getIdRole()) != null) {
	    	try {
	    		preparedState = connectionState.prepareStatement(selectSQL);
	    		preparedState.setString(1, role.getIdentifiant());
	    		preparedState.setString(2, role.getDescription());
	    		preparedState.setInt(3, role.getVersion() != null ? role.getVersion() : 0);
	    		preparedState.setInt(4, role.getIdRole());
	
	    		preparedState.executeUpdate();
	            return role;
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    	} finally {
	            ConnectionHelper.closeSqlResources(preparedState, result);
	        }
    	}
        return null;
    }

    @Override
    public boolean deleteRole(Role role) {
        log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
    	
    	String selectSQL = "DELETE FROM Role WHERE `idRole` = ?";
    	if (findRoleById(role.getIdRole()) != null) {
	    	try {
	    		preparedState = connectionState.prepareStatement(selectSQL);
	    		preparedState.setInt(1, role.getIdRole());
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
