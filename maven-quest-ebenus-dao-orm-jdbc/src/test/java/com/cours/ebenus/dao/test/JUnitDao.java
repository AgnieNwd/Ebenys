package com.cours.ebenus.dao.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	JUnitUtilisateurDao.class,
	JUnitRoleDao.class})
public class JUnitDao {

    private static final Log log = LogFactory.getLog(JUnitDao.class);
 
    

}
