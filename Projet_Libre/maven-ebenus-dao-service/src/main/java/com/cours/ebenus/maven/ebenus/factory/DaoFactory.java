package com.cours.ebenus.maven.ebenus.factory;

import com.cours.ebenus.maven.ebenus.dao.IUserDao;
import com.cours.ebenus.maven.ebenus.dao.IRoleDao;
import com.cours.ebenus.maven.ebenus.dao.INoteDao;
import com.cours.ebenus.maven.ebenus.dao.IMatiereDao;
import com.cours.ebenus.maven.ebenus.dao.IClasseDao;

import com.cours.ebenus.maven.ebenus.dao.impl.UserDao;
import com.cours.ebenus.maven.ebenus.dao.impl.RoleDao;
import com.cours.ebenus.maven.ebenus.dao.impl.NoteDao;
import com.cours.ebenus.maven.ebenus.dao.impl.MatiereDao;
import com.cours.ebenus.maven.ebenus.dao.impl.ClasseDao;


public class DaoFactory extends AbstractDaoFactory {

    @Override
    public IUserDao getUserDao() {
        return new UserDao();
    }

    @Override
    public IRoleDao getRoleDao() {
        return new RoleDao();
    }
    
    @Override
    public INoteDao getNoteDao() {
        return new NoteDao();
    }
    
    @Override
    public IMatiereDao getMatiereDao() {
        return new MatiereDao();
    }
    
    @Override
    public IClasseDao getClasseDao() {
        return new ClasseDao();
    }

}
