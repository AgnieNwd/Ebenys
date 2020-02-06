package com.cours.ebenus.dao.test;

import org.apache.commons.logging.LogFactory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import org.apache.commons.logging.Log;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	JUnitUserProjetLibre.class,
	JUnitRoleProjetLibre.class,
	JUnitClasseProjetLibre.class,
	JUnitMatiereProjetLibre.class,
	JUnitNoteProjetLibre.class})

public class JUnitAllDao {
    private static final Log log = LogFactory.getLog(JUnitAllDao.class);


}
