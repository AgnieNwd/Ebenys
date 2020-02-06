package com.cours.ebenus.dao.test;

import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cours.ebenus.maven.ebenus.dao.DriverManagerSingleton;
import com.cours.ebenus.maven.ebenus.dao.entities.Classe;
import com.cours.ebenus.maven.ebenus.dao.entities.Matiere;
import com.cours.ebenus.maven.ebenus.dao.entities.Note;
import com.cours.ebenus.maven.ebenus.dao.entities.Role;
import com.cours.ebenus.maven.ebenus.dao.entities.User;
import com.cours.ebenus.maven.ebenus.dao.exception.ProjectException;
import com.cours.ebenus.maven.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.maven.ebenus.service.IServiceFacade;
import com.cours.ebenus.maven.ebenus.service.ServiceFacade;
import com.cours.ebenus.maven.ebenus.utils.Constants;
import com.ibatis.common.jdbc.ScriptRunner;

public class JUnitQuestProjetLibre {

    private static final Log log = LogFactory.getLog(JUnitQuestProjetLibre.class);
    protected static IServiceFacade serviceFacade = null;
    // Compter le nombre d'utilisateurs et de roles dans votre base de données.
    protected static final int NB_ROLES_LIST = 4;
    protected static final int NB_MATIERES_LIST = 3; 
    protected static final int NB_USERS_LIST = 8;
    protected static final int NB_CLASSES_LIST = 3;
    protected static final int NB_NOTES_LIST = 18;

    protected static final String USER_FIND_BY_PRENOM = "Nicolas";
    protected static final int NB_USERS_FIND_BY_PRENOM = 2;

    public static final String USER_FIND_BY_NOM = "West";
    protected static final int NB_USERS_FIND_BY_NOM = 1;

    protected static final String USER_FIND_BY_IDENTIFIANT_ROLE_ELEVE = "Eleve";
    protected static final int NB_USERS_FIND_BY_IDENTIFIANT_ROLE_ELEVE = 3;
    protected static final int NB_USERS_FIND_BY_ROLE_AND_LIBELLE_CLASSE = 3;

    protected static final String USER_FIND_BY_IDENTIFIANT_ROLE_PROF = "Professeur";
    protected static final int NB_USERS_FIND_BY_IDENTIFIANT_ROLE_PROF = 3;

    protected static final String USER_FIND_BY_IDENTIFIANT_ROLE_ADMIN = "Administrateur";
    protected static final int NB_USERS_FIND_BY_IDENTIFIANT_ROLE_ADMIN = 1;

    protected static final int NB_ROLES_FIND_BY_IDENTIFIANT_ADMIN = 1;
    protected static final String ROLES_FIND_BY_IDENTIFIANT_ADMIN = "Administrateur";

    protected static final int NB_ROLES_FIND_BY_IDENTIFIANT_PROF = 1;
    protected static final String ROLES_FIND_BY_IDENTIFIANT_PROF = "Professeur";

    protected static final int NB_ROLES_FIND_BY_IDENTIFIANT_ELEVE = 1;
    protected static final String ROLES_FIND_BY_IDENTIFIANT_ELEVE = "Eleve";

    protected static final String MATIERE_FIND_BY_LIBELLE = "Mathématiques";
    protected static final int NB_MATIERE_FIND_BY_LIBELLE = 1;
    
    protected static final int MATIERE_FIND_BY_USER_ID = 2;
    protected static final int NB_MATIERE_FIND_BY_USER_ID = 0;
    
    
    protected static final String MATIERE_FIND_BY_USER_EMAIL = "jj@gmail.com";
    protected static final int NB_MATIERE_FIND_BY_USER_EMAIL = 1;
    
    protected static final String CLASSE_FIND_BY_IDENTIFIANT_4EME = "4e";
    protected static final int NB_CLASSE_FIND_BY_IDENTIFIANT_4EME = 1;
    
    protected static final double NOTE_FIND_BY_LIBELLE = 18.5;
    protected static final int NB_NOTE_FIND_BY_LIBELLE = 2;
    
    protected static final int NOTE_FIND_BY_USER_ID = 2;
    protected static final int NB_NOTE_FIND_BY_USER_ID = 0;
    
    protected static final String NOTE_FIND_BY_USER_EMAIL = "jj@gmail.com";
    protected static final int NB_NOTE_FIND_BY_USER_EMAIL = 0;
    
    protected static final int NOTE_FIND_BY_ID_MATIERE = 2;
    protected static final int NB_NOTE_FIND_BY_ID_MATIERE = 6;
    
    protected static final String NOTE_FIND_BY_LIBELLE_MATIERE = "Mathématiques";
    protected static final int NB_NOTE_FIND_BY_LIBELLE_MATIERE = 6;
    
    protected static List<User> users = null;
    protected static List<Role> roles = null;
    protected static List<Matiere> matieres = null;
    protected static List<Classe> classes = null;
    protected static List<Note> notes = null;

    @BeforeClass
    public static void init() throws Exception {
        // Configuration de l'application
    	serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);
    	roles = serviceFacade.getRoleDao().findAllRoles();
    	matieres = serviceFacade.getMatiereDao().findAllMatieres();
    	users = serviceFacade.getUserDao().findAllUsers();
    	classes = serviceFacade.getClasseDao().findAllClasses();
    	notes = serviceFacade.getNoteDao().findAllNotes();
    }
    
    @BeforeClass
    public static void initDataBase() throws FileNotFoundException, IOException, SQLException {
        String scriptSqlPath = Constants.SQL_JUNIT_PATH_FILE;
        try
        {
            BufferedReader rd = new BufferedReader(new FileReader(scriptSqlPath));
            ScriptRunner sr = new ScriptRunner(DriverManagerSingleton.getConnectionInstance(), false, false);
            sr.runScript(rd);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            throw new ProjectException("Cannot initialise the database : file not found", 1);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new ProjectException("Cannot initialise the database : SQL issue", 1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new ProjectException("Cannot initialise the database : unknown error", 1);
        }
    }


	
	
    @AfterClass
    public static void terminate() throws Exception {
        log.debug("Entree de la methode");
        serviceFacade = null;
        users = null;
        roles = null;
        log.debug("Sortie de la methode");
    }


}