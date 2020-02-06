package com.cours.ebenus.maven.ebenus.utils;

public class Constants {

	// Url de connexion en base de donnée
    public static String DATABASE_URL = "jdbc:mysql://localhost:3306/my_intra?useSSL=false";
    
    // Utilisateur de la base de données
    public static String DATABASE_USER = "application";
    
    // Mot de passe de la base de données
    public static String DATABASE_PASSWORD = "passw0rd";

    // Drivers Jdbc
    public static String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    //Si on veut importer
    public static String SQL_JUNIT_PATH_FILE = "my_intra.sql";

    public static int EXCEPTION_CODE_USER_ALREADY_EXIST = -1;

}
