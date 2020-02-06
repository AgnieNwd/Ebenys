package com.cours.ebenus.maven.ebenus.dao;

import com.cours.ebenus.maven.ebenus.utils.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class DriverManagerSingleton {

	private static final Log log = LogFactory.getLog(DriverManagerSingleton.class);

    public final static String className = DriverManagerSingleton.class.getName();
    // Url de connexion en base de donnée
    private static final String url = Constants.DATABASE_URL;

    // Utilisateur de la base de données
    private static final String user = Constants.DATABASE_USER;

    // Mot de passe de la base de données
    private static final String password = Constants.DATABASE_PASSWORD;

    // Drivers Jdbc
    private static final String jdbcDriver = Constants.JDBC_DRIVER;
    
    private static DriverManagerSingleton _instance = null;

    private static Connection connection;

    private DriverManagerSingleton()  
    { 
        /* Creation of an instance of the connection statement*/
    	try {
  			Class.forName(Constants.JDBC_DRIVER);
  			connection = DriverManager.getConnection(url, user, password);
  		} catch (ClassNotFoundException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
    }
    
    public static Connection getConnectionInstance() {
    	if(_instance == null)
    		_instance = new DriverManagerSingleton();
		else
			try {
				if (connection.isClosed()) {
					_instance = new DriverManagerSingleton();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        return connection;
    }

    
    public static synchronized  DriverManagerSingleton getInstance() {
        if(_instance == null)
            _instance = new DriverManagerSingleton();
		else
			try {
				if (getConnectionInstance().isClosed()) {
				    _instance = new DriverManagerSingleton();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        return _instance;
    }


}
