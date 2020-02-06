package com.cours.ebenus.maven.ebenus.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class ConnectionHelper {
	private static final Log log = LogFactory.getLog(ConnectionHelper.class);
    public final static String className = ConnectionHelper.class.getName();

    public static void closeSqlResources(PreparedStatement preparedStatement, ResultSet result) {
        if (preparedStatement != null) {
        	try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        if (result != null) {
            try {
				result.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

}
