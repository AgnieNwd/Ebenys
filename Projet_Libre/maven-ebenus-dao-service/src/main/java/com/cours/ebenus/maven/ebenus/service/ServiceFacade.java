package com.cours.ebenus.maven.ebenus.service;

import com.cours.ebenus.maven.ebenus.dao.IRoleDao;
import com.cours.ebenus.maven.ebenus.dao.IUserDao;
import com.cours.ebenus.maven.ebenus.dao.INoteDao;
import com.cours.ebenus.maven.ebenus.dao.IMatiereDao;
import com.cours.ebenus.maven.ebenus.dao.IClasseDao;
import com.cours.ebenus.maven.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.maven.ebenus.factory.AbstractDaoFactory.FactoryDaoType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ServiceFacade implements IServiceFacade {
	
    private static final Log log = LogFactory.getLog(ServiceFacade.class);
    private final AbstractDaoFactory.FactoryDaoType DEFAULT_IMPLEMENTATION = AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY;
    
	private IUserDao userDao = null;

    private IRoleDao roleDao = null;
    
    private INoteDao noteDao = null;

    private IMatiereDao matiereDao = null;

    private IClasseDao classeDao = null;
    
    public ServiceFacade() {
        // mettre tous les DAO
        userDao = AbstractDaoFactory.getFactory(DEFAULT_IMPLEMENTATION).getUserDao();
        roleDao = AbstractDaoFactory.getFactory(DEFAULT_IMPLEMENTATION).getRoleDao();
        noteDao = AbstractDaoFactory.getFactory(DEFAULT_IMPLEMENTATION).getNoteDao();
        matiereDao = AbstractDaoFactory.getFactory(DEFAULT_IMPLEMENTATION).getMatiereDao();
        classeDao = AbstractDaoFactory.getFactory(DEFAULT_IMPLEMENTATION).getClasseDao();

    }

    public ServiceFacade(FactoryDaoType daoType) {
        // mettre tous les DAO
    	userDao = AbstractDaoFactory.getFactory(daoType).getUserDao();
        roleDao = AbstractDaoFactory.getFactory(daoType).getRoleDao();
        noteDao = AbstractDaoFactory.getFactory(daoType).getNoteDao();
        matiereDao = AbstractDaoFactory.getFactory(daoType).getMatiereDao();
        classeDao = AbstractDaoFactory.getFactory(daoType).getClasseDao();
    }
    
    @Override
    public IUserDao getUserDao() {
    	return userDao;
    }
    
	@Override
	public IRoleDao getRoleDao() {
		return roleDao;
	}
    
    @Override
    public INoteDao getNoteDao() {
    	return noteDao;
    }
    
    @Override
	public IMatiereDao getMatiereDao() {
    	return matiereDao;
    }
    
    @Override
    public IClasseDao getClasseDao() {
    	return classeDao;
    }



}
