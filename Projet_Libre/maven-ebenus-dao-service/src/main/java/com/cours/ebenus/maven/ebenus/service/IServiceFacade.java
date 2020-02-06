package com.cours.ebenus.maven.ebenus.service;

import com.cours.ebenus.maven.ebenus.dao.IRoleDao;
import com.cours.ebenus.maven.ebenus.dao.IUserDao;
import com.cours.ebenus.maven.ebenus.dao.INoteDao;
import com.cours.ebenus.maven.ebenus.dao.IMatiereDao;
import com.cours.ebenus.maven.ebenus.dao.IClasseDao;


public interface IServiceFacade {

	 public IUserDao getUserDao();

	 public IRoleDao getRoleDao();

	 public INoteDao getNoteDao();

	 public IMatiereDao getMatiereDao();
	 
	 public IClasseDao getClasseDao();

}
