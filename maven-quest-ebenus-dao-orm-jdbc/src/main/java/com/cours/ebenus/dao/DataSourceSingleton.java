/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.dbcp.BasicDataSource;
import com.cours.ebenus.utils.Constants;

/**
 *
 * @author elhad
 */
public class DataSourceSingleton {

    private static final Log log = LogFactory.getLog(DataSourceSingleton.class);
    public final static String className = DataSourceSingleton.class.getName();
    // Objet DataSource
    private static DataSource dataSource = null;
    
	public DataSourceSingleton(){
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName(Constants.JDBC_DRIVER);
		bds.setUrl(Constants.DATABASE_URL);
		bds.setUsername(Constants.DATABASE_USER);
		bds.setPassword(Constants.DATABASE_PASSWORD);
		bds.setMaxActive(20);
		bds.setMaxIdle(5);
		bds.setMaxWait(4000);
        dataSource = bds;
	}
    

    public static Connection getConnection() throws SQLException {
        if(dataSource == null)
        	new DataSourceSingleton();

        return dataSource.getConnection();
    }


    
}
