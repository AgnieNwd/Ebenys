package com.cours.ebenus.maven.ebenus.factory;

import com.cours.ebenus.maven.ebenus.dao.IUserDao;
import com.cours.ebenus.maven.ebenus.dao.IRoleDao;
import com.cours.ebenus.maven.ebenus.dao.INoteDao;
import com.cours.ebenus.maven.ebenus.dao.IMatiereDao;
import com.cours.ebenus.maven.ebenus.dao.IClasseDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AbstractDaoFactory {
	public static String className = AbstractDaoFactory.class.getName();
    private static final Log log = LogFactory.getLog(AbstractDaoFactory.class);

    public enum FactoryDaoType {
        JDBC_DAO_FACTORY;
    }

    public abstract IUserDao getUserDao();

    public abstract IRoleDao getRoleDao();
    
    public abstract INoteDao getNoteDao();
    
    public abstract IMatiereDao getMatiereDao();
    
    public abstract IClasseDao getClasseDao();

    /**
     * Méthode pour récupérer une factory de DAO
     *
     * @param daoType
     * @return AbstractDaoFactory
     */
    public static AbstractDaoFactory getFactory(FactoryDaoType daoType) {
    	
    	AbstractDaoFactory factory = null;
    	
    	switch (daoType) {
	    	case JDBC_DAO_FACTORY:
	    		factory = new DaoFactory();
	    		break;
	    	default:
	    		factory = null;
    	}
        return factory;
    }
}
