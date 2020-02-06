/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.main;

import com.cours.ebenus.dao.IRoleDao;
import com.cours.ebenus.dao.IUtilisateurDao;
import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.dao.manual.list.impl.ManualListRoleDao;
import com.cours.ebenus.dao.manual.list.impl.ManualListUtilisateurDao;
import com.cours.ebenus.dao.manual.map.impl.ManualMapRoleDao;
import com.cours.ebenus.dao.manual.map.impl.ManualMapUtilisateurDao;
import com.cours.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author elhad
 */
public class Main {

    private static final Log log = LogFactory.getLog(Main.class);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    	IServiceFacade serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.MANUAL_MAP_DAO_FACTORY);
    	
    	
       	//IRoleDao roleDao = new ManualListRoleDao();
    	IUtilisateurDao utilDao = new ManualListUtilisateurDao();

    	

    	Role role = new Role(3, "god", "God");
        Date firstDate = new Date();
//
//        Utilisateur utili = new Utilisateur(1, "mllx", "elli", "sabeth", "123123", "mdp", firstDate, role);
//        System.out.println(role);
//        System.out.println(utili);
    	
    	// List 
//    	System.out.println(roleDao.findAllRoles().size());
//    	
//    	System.out.println(utilDao.findAllUtilisateurs().size());
    	
    	// Map 
//    	IRoleDao roleMapDao = new ManualMapRoleDao();
//    	System.out.println(roleMapDao.findAllRoles().size());
    	
    	IUtilisateurDao utilMapDao = new ManualMapUtilisateurDao();
//    	System.out.println(utilMapDao.findAllUtilisateurs().get(1));
    	Utilisateur user = new Utilisateur(1, "mllx", "billie", "eilish", "1111", "mdp", firstDate, role);
    	
    	// Update & Create & Delete user
    	System.out.println(serviceFacade.getUtilisateurDao().findAllUtilisateurs().toString());
    	serviceFacade.getUtilisateurDao().createUtilisateur(user);
    	// utilMapDao.updateUtilisateur(user);
    	// utilMapDao.deleteUtilisateur(user);
    	System.out.println(serviceFacade.getUtilisateurDao().findAllUtilisateurs().toString());

    	// Create & Delete Role List
//    	System.out.println(roleDao.findAllRoles().toString());
//    	System.out.println(roleDao.findAllRoles().size());
//    	roleDao.createRole(role);
    	//System.out.println(roleDao.updateRole(role));
//    	System.out.println(roleDao.findAllRoles().toString());
//    	System.out.println(roleDao.findAllRoles().size());
    	
    	
    	
    }
}
