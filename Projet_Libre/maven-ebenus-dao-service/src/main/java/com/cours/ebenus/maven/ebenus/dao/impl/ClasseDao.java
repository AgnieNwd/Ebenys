package com.cours.ebenus.maven.ebenus.dao.impl;

import com.cours.ebenus.maven.ebenus.dao.ConnectionHelper;
import com.cours.ebenus.maven.ebenus.dao.DriverManagerSingleton;
import com.cours.ebenus.maven.ebenus.dao.IClasseDao;
import com.cours.ebenus.maven.ebenus.dao.entities.Classe;

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


public class ClasseDao implements IClasseDao {
	
	private static final Log log = LogFactory.getLog(ClasseDao.class);

    private static DriverManagerSingleton DMsingle = DriverManagerSingleton.getInstance();
    
    public ClasseDao() {
    	
    }
    
    private Classe setInfosClasse(ResultSet result) throws SQLException {
    	return new Classe(result.getInt("id"), result.getString("libelle_classe"));
    }

    
	@Override
	public List<Classe> findAllClasses(){
		log.debug("Entree de la methode");
        Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
    	
    	List<Classe> classeList = new ArrayList<Classe>();
    	
    	String selectSQL = "SELECT * FROM Classe;";
    	try {
    		preparedState = connectionState.prepareStatement(selectSQL);
    		result = preparedState.executeQuery(selectSQL);
    		while (result.next()) {
    			Classe newRole = setInfosClasse(result);
  
    			classeList.add(newRole);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            ConnectionHelper.closeSqlResources(preparedState, result);
        }
        return classeList;

		
	}

	@Override
    public Classe findClasseById(int idClasse) {
		log.debug("Entree de la methode");
        Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
    	
    	String selectSQL = "SELECT * FROM Classe WHERE id = ?";

    	Classe newClasse = null;
    	
    	try {
    		preparedState = connectionState.prepareStatement(selectSQL);
    		preparedState.setInt(1, idClasse);
    		result = preparedState.executeQuery();
    		
    		while (result.next()) {
    			newClasse = setInfosClasse(result);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            ConnectionHelper.closeSqlResources(preparedState, result);
        }
        return newClasse;

    }

    @Override
    public List<Classe> findClasseByLibelleClasse(String libelleClasse){
    	log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
    	
    	String selectSQL = "SELECT * FROM Classe WHERE libelle_classe = ?";

    	List<Classe> classeList = new ArrayList<Classe>();
    	
    	try {
    		preparedState = connectionState.prepareStatement(selectSQL);
    		preparedState.setString(1, libelleClasse);
    		result = preparedState.executeQuery();
    		
    		while (result.next()) {
    			Classe newRole = setInfosClasse(result);
    			classeList.add(newRole);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            ConnectionHelper.closeSqlResources(preparedState, result);
        }
        return classeList;

    }
    
    @Override
    public Classe createClasse(Classe classe) {
    	log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
    	        
    	String selectSQL = "INSERT INTO Classe(libelle_classe) VALUES (?)";

    	if (classe.getIdClasse() == null) {//|| findRoleById(role.getIdRole()) == null) {
	    	try {
	    		preparedState = connectionState.prepareStatement(selectSQL, Statement.RETURN_GENERATED_KEYS);
	    		preparedState.setString(1, classe.getLibelleClasse());
	    		preparedState.execute();
	    		
	    		result = preparedState.getGeneratedKeys();
	    		if (result.next()) {
	    			classe.setIdClasse(result.getInt(1));
	            }
	            	
	            return classe;
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    	} finally {
	            ConnectionHelper.closeSqlResources(preparedState, result);
	        }
    	}
        return null;

    }

    @Override
    public Classe updateClasse(Classe classe) {
    	log.debug("Entree de la methode");
        Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;

    	String selectSQL = "UPDATE `Classe` set `libelle_classe` =? WHERE `id` = ?";
    	if (findClasseById(classe.getIdClasse()) != null) {
	    	try {
	    		preparedState = connectionState.prepareStatement(selectSQL);
	    		preparedState.setString(1, classe.getLibelleClasse());
	    		preparedState.setInt(2, classe.getIdClasse());
	    		preparedState.executeUpdate();
	            return classe;
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    	} finally {
	            ConnectionHelper.closeSqlResources(preparedState, result);
	        }
    	}
        return null;

    }

    @Override
    public boolean deleteClasse(Classe classe) {
    	log.debug("Entree de la methode");
    	Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        ResultSet result = null;
        PreparedStatement preparedState = null;
    	
        boolean isUpdated = updateQuery(classe.getIdClasse());
        
    	String selectSQL = "DELETE FROM Classe WHERE `id` = ?";
    	if (findClasseById(classe.getIdClasse()) != null) {
	    	try {
	    		preparedState = connectionState.prepareStatement(selectSQL);
	    		preparedState.setInt(1, classe.getIdClasse());
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
    
	private boolean updateQuery(int id) {
		Connection connectionState = DriverManagerSingleton.getConnectionInstance();
        PreparedStatement preparedState = null;
        ResultSet result = null;
    	String selectSQL = "UPDATE USER SET id_classe = 1 WHERE id_classe = ?;";
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
