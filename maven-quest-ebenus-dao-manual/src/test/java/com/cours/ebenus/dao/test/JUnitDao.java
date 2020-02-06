package com.cours.ebenus.dao.test;

import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;
import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	
    JUnitQuestEbenusManualList.class,
    JUnitQuestEbenusManualMap.class
})

public class JUnitDao {

}
