package com.cours.ebenus.dao.test;

import com.cours.ebenus.dao.DataSourceSingleton;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.exception.EbenusException;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;
import com.cours.ebenus.utils.Constants;
import com.ibatis.common.jdbc.ScriptRunner;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class JUnitQuestEbenus {

    private static final Log log = LogFactory.getLog(JUnitQuestEbenus.class);
    protected static IServiceFacade serviceFacade = null;
    // Compter le nombre d'utilisateurs et de roles dans votre base de données.
    protected static final int NB_UTILISATEURS_LIST = 20;
    protected static final int NB_ROLES_LIST = 5;

    protected static final String UTILISATEUR_FIND_BY_PRENOM = "Nicolas";
    protected static final int NB_UTILISATEURS_FIND_BY_PRENOM = 3;

    public static final String UTILISATEUR_FIND_BY_NOM = "Petit";
    protected static final int NB_UTILISATEURS_FIND_BY_NOM = 3;

    protected static final String UTILISATEUR_FIND_BY_IDENTIFIANT_ROLE_STANDARD = "Standard";
    protected static final int NB_UTILISATEURS_FIND_BY_IDENTIFIANT_ROLE_STANDARD = 7;

    protected static final String UTILISATEUR_FIND_BY_IDENTIFIANT_ROLE_ACHETEUR = "Acheteur";
    protected static final int NB_UTILISATEURS_FIND_BY_IDENTIFIANT_ROLE_ACHETEUR = 7;

    protected static final String UTILISATEUR_FIND_BY_IDENTIFIANT_ROLE_ADMIN = "Administrateur";
    protected static final int NB_UTILISATEURS_FIND_BY_IDENTIFIANT_ROLE_ADMIN = 3;

    protected static final int NB_ROLES_FIND_BY_IDENTIFIANT_ADMIN = 1;
    protected static final String ROLES_FIND_BY_IDENTIFIANT_ADMIN = "Administrateur";

    protected static final int NB_ROLES_FIND_BY_IDENTIFIANT_ACHETEUR = 1;
    protected static final String ROLES_FIND_BY_IDENTIFIANT_ACHETEUR = "Acheteur";

    protected static final int NB_ROLES_FIND_BY_IDENTIFIANT_STANDARD = 1;
    protected static final String ROLES_FIND_BY_IDENTIFIANT_STANDARD = "Standard";

    protected static List<Utilisateur> utilisateurs = null;
    protected static List<Role> roles = null;

    @BeforeClass
    public static void init() throws Exception {
        // Configuration de l'application
    	serviceFacade = new ServiceFacade();
        utilisateurs = serviceFacade.getUtilisateurDao().findAllUtilisateurs();
        roles = serviceFacade.getRoleDao().findAllRoles();

    }
    
    @BeforeClass
    public static void initDataBase() throws FileNotFoundException, IOException, SQLException {
        String scriptSqlPath = Constants.SQL_JUNIT_PATH_FILE;
        try
        {
            BufferedReader rd = new BufferedReader(new FileReader(scriptSqlPath));
            ScriptRunner sr = new ScriptRunner(DataSourceSingleton.getConnection(), false, false);
            sr.runScript(rd);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            throw new EbenusException("Cannot initialise the database : file not found", 1);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new EbenusException("Cannot initialise the database : SQL issue", 1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new EbenusException("Cannot initialise the database : unknown error", 1);
        }
    }


    @AfterClass
    public static void terminate() throws Exception {
        log.debug("Entree de la methode");
        serviceFacade = null;
        utilisateurs = null;
        roles = null;
        log.debug("Sortie de la methode");
    }
}
