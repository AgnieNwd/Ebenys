/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.utils;

/**
 *
 * @author ElHadji
 */
public class Constants {

    // Url de connexion en base de donnée
    public static String DATABASE_URL = "jdbc:mysql://localhost:3306/base_quest_ebenus?useSSL=false";
    // Utilisateur de la base de données
    public static String DATABASE_USER = "application";
    // Mot de passe de la base de données
    public static String DATABASE_PASSWORD = "passw0rd";

    // Drivers Jdbc
    public static String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    public static String SQL_JUNIT_PATH_FILE = "script_base_test_junit_quest_ebenus.sql";

    public static int EXCEPTION_CODE_USER_ALREADY_EXIST = -1;
}
