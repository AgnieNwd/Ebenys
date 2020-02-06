/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose DaoHelper | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao;

import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class DataSource {

    private static final Log log = LogFactory.getLog(DataSource.class);
    private List<Role> rolesListDataSource = null;
    private List<Utilisateur> utilisateursListDataSource = null;
    private Role[] rolesArrayDataSource = null;
    private Utilisateur[] utilisateursArrayDataSource = null;
    private Map<Integer, Role> rolesMapDataSource = null;
    private Map<Integer, Utilisateur> utilisateursMapDataSource = null;

    private DataSource() {
        log.debug("--> ************ Initialisation de " + DataSource.class.getSimpleName() + " ************");
        rolesListDataSource = initRolesListDataSource();
        utilisateursListDataSource = initUtilisateursListDataSource();
        rolesArrayDataSource = initRolesArrayDataSource();
        utilisateursArrayDataSource = initUtilisateursArrayDataSource();
        rolesMapDataSource = initRolesMapDataSource();
        utilisateursMapDataSource = initUtilisateursMapDataSource();
    }

    /**
     * Holder
     */
    private static class MySingletonHolder {

        /**
         * Instance unique non préinitialisée
         */
        private final static DataSource instance = new DataSource();
    }

    /**
     * Point d'accès pour l'instance unique du singleton
     *
     * @return
     */
    public static DataSource getInstance() {
        String methodName = "getInstance"; 
        if (DataSource.MySingletonHolder.instance == null) {
            log.debug("--> Nouvelle Instance de " + DataSource.class.getSimpleName());
        } else {
            log.debug("--> Re-Utilisation de l'instance " + DataSource.class.getSimpleName() + " dejà existante ");
        }
        return DataSource.MySingletonHolder.instance;
    }

    private List<Role> initRolesListDataSource() {
        return new ArrayList<Role>(Arrays.asList(
                new Role(1, "Administrateur", "Le rôle administrateur"),
                new Role(2, "Directeur", "Le rôle de directeur de magasin"),
                new Role(3, "Standard", "Le rôle standard"),
                new Role(4, "Vendeur", "Le rôle vendeur"),
                new Role(5, "Acheteur", "Le rôle Acheteur")));
    }

    private List<Utilisateur> initUtilisateursListDataSource() {
        return new ArrayList<Utilisateur>(Arrays.asList(
                new Utilisateur(1, "Mr", "Jerome", "Cantin", "admin@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(1, "Administrateur", "Le rôle administrateur")),
                new Utilisateur(2, "Mr", "Nicolas", "Petit", "nicolas.petit@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")),
                new Utilisateur(3, "Mr", "Jerome", "Cantin", "jerome@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")),
                new Utilisateur(4, "Mr", "Dimitry", "Gozin", "dimitry.gozin@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(1, "Administrateur", "Le rôle administrateur")),
                new Utilisateur(5, "Mme", "Louise", "Correa", "luise@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")),
                new Utilisateur(6, "Mr", "Nicolas", "Gerard", "nicolas.gerard@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")),
                new Utilisateur(7, "Mr", "Remy", "Collard", "remy.collard@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")),
                new Utilisateur(8, "Mr", "Vincent", "Ducan", "vincent.ducamp@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")),
                new Utilisateur(9, "Mme", "Claire", "Dupond", "claire.dupond@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")),
                new Utilisateur(10, "Mme", "Stephanie", "Job", "stephanie.job@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(4, "Vendeur", "Le rôle vendeur")),
                new Utilisateur(11, "Mme", "Celine", "Premier", "celine.premier@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")),
                new Utilisateur(12, "Mr", "Jean", "Marchand", "jean.marchand@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(1, "Administrateur", "Le rôle administrateur")),
                new Utilisateur(13, "Mr", "Nicolas", "Guayard", "nicolas.guayard@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")),
                new Utilisateur(14, "Mr", "Patrick", "Leduc", "patric.leduc@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(4, "Vendeur", "Le rôle vendeur")),
                new Utilisateur(15, "Mr", "Malick", "Ba", "malick.ba@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")),
                new Utilisateur(16, "Mr", "Erwan", "Petit", "erwan.petit@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")),
                new Utilisateur(17, "Mr", "Marc", "Lambert", "marc.lambert@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")),
                new Utilisateur(18, "Mme", "France", "Lebon", "france.lebon@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(4, "Vendeur", "Le rôle vendeur")),
                new Utilisateur(19, "Mr", "Francis", "Garry", "francis.garry@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")),
                new Utilisateur(20, "Mr", "Thierry", "Garon", "thierry.garon@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")),
                new Utilisateur(21, "Mme", "Fatou", "Diop", "fatou.diop@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(3, "Standard", "Le rôle standard")),
                new Utilisateur(22, "Mme", "Fatima", "Salam", "fatima.salam@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")),
                new Utilisateur(23, "Mr", "Jean", "Dasilva", "jean.dasilva@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(3, "Standard", "Le rôle standard")),
                new Utilisateur(24, "Mr", "Kemi", "Bazare", "kemi.bazare@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(1, "Administrateur", "Le rôle administrateur")),
                new Utilisateur(25, "Mr", "Thibault", "Levrard", "thibault.levrard@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")),
                new Utilisateur(26, "Mr", "Michael", "Petit", "michael.petit@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")),
                new Utilisateur(27, "Mr", "Lionnel", "Richie", "lionnel.richie@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")),
                new Utilisateur(28, "Mr", "Maurice", "Green", "maurice.green@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(1, "Administrateur", "Le rôle administrateur")
                )));
    }

    private Map<Integer, Role> initRolesMapDataSource() {
        Map<Integer, Role> mapDataSourceRoles = new HashMap<Integer, Role>();
        mapDataSourceRoles.put(1, new Role(1, "Administrateur", "Le rôle administrateur"));
        mapDataSourceRoles.put(2, new Role(2, "Directeur", "Le rôle de directeur de magasin"));
        mapDataSourceRoles.put(3, new Role(3, "Standard", "Le rôle standard"));
        mapDataSourceRoles.put(4, new Role(4, "Vendeur", "Le rôle vendeur"));
        mapDataSourceRoles.put(5, new Role(5, "Acheteur", "Le rôle Acheteur"));
        return mapDataSourceRoles;
    }

    private Map<Integer, Utilisateur> initUtilisateursMapDataSource() {
        Map<Integer, Utilisateur> mapDataSourceUtilisateurs = new HashMap<Integer, Utilisateur>();
        mapDataSourceUtilisateurs.put(1, new Utilisateur(1, "Mr", "Jerome", "Cantin", "admin@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(1, "Administrateur", "Le rôle administrateur")));
        mapDataSourceUtilisateurs.put(2, new Utilisateur(2, "Mr", "Nicolas", "Petit", "nicolas.petit@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")));
        mapDataSourceUtilisateurs.put(3, new Utilisateur(3, "Mr", "Jerome", "Cantin", "jerome@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")));
        mapDataSourceUtilisateurs.put(4, new Utilisateur(4, "Mr", "Dimitry", "Gozin", "dimitry.gozin@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(1, "Administrateur", "Le rôle administrateur")));
        mapDataSourceUtilisateurs.put(5, new Utilisateur(5, "Mme", "Louise", "Correa", "luise@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")));
        mapDataSourceUtilisateurs.put(6, new Utilisateur(6, "Mr", "Nicolas", "Gerard", "nicolas.gerard@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")));
        mapDataSourceUtilisateurs.put(7, new Utilisateur(7, "Mr", "Remy", "Collard", "remy.collard@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")));
        mapDataSourceUtilisateurs.put(8, new Utilisateur(8, "Mr", "Vincent", "Ducan", "vincent.ducamp@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")));
        mapDataSourceUtilisateurs.put(9, new Utilisateur(9, "Mme", "Claire", "Dupond", "claire.dupond@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")));
        mapDataSourceUtilisateurs.put(10, new Utilisateur(10, "Mme", "Stephanie", "Job", "stephanie.job@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(4, "Vendeur", "Le rôle vendeur")));
        mapDataSourceUtilisateurs.put(11, new Utilisateur(11, "Mme", "Celine", "Premier", "celine.premier@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")));
        mapDataSourceUtilisateurs.put(12, new Utilisateur(12, "Mr", "Jean", "Marchand", "jean.marchand@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(1, "Administrateur", "Le rôle administrateur")));
        mapDataSourceUtilisateurs.put(13, new Utilisateur(13, "Mr", "Nicolas", "Guayard", "nicolas.guayard@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")));
        mapDataSourceUtilisateurs.put(14, new Utilisateur(14, "Mr", "Patrick", "Leduc", "patric.leduc@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(4, "Vendeur", "Le rôle vendeur")));
        mapDataSourceUtilisateurs.put(15, new Utilisateur(15, "Mr", "Malick", "Ba", "malick.ba@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")));
        mapDataSourceUtilisateurs.put(16, new Utilisateur(16, "Mr", "Erwan", "Petit", "erwan.petit@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")));
        mapDataSourceUtilisateurs.put(17, new Utilisateur(17, "Mr", "Marc", "Lambert", "marc.lambert@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")));
        mapDataSourceUtilisateurs.put(18, new Utilisateur(18, "Mme", "France", "Lebon", "france.lebon@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(4, "Vendeur", "Le rôle vendeur")));
        mapDataSourceUtilisateurs.put(19, new Utilisateur(19, "Mr", "Francis", "Garry", "francis.garry@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")));
        mapDataSourceUtilisateurs.put(20, new Utilisateur(20, "Mr", "Thierry", "Garon", "thierry.garon@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")));
        mapDataSourceUtilisateurs.put(21, new Utilisateur(21, "Mme", "Fatou", "Diop", "fatou.diop@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(3, "Standard", "Le rôle standard")));
        mapDataSourceUtilisateurs.put(22, new Utilisateur(22, "Mme", "Fatima", "Salam", "fatima.salam@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")));
        mapDataSourceUtilisateurs.put(23, new Utilisateur(23, "Mr", "Jean", "Dasilva", "jean.dasilva@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(3, "Standard", "Le rôle standard")));
        mapDataSourceUtilisateurs.put(24, new Utilisateur(24, "Mr", "Kemi", "Bazare", "kemi.bazare@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(1, "Administrateur", "Le rôle administrateur")));
        mapDataSourceUtilisateurs.put(25, new Utilisateur(25, "Mr", "Thibault", "Levrard", "thibault.levrard@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")));
        mapDataSourceUtilisateurs.put(26, new Utilisateur(26, "Mr", "Michael", "Petit", "michael.petit@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")));
        mapDataSourceUtilisateurs.put(27, new Utilisateur(27, "Mr", "Lionnel", "Richie", "lionnel.richie@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")));
        mapDataSourceUtilisateurs.put(28, new Utilisateur(28, "Mr", "Maurice", "Green", "maurice.green@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(1, "Administrateur", "Le rôle administrateur")));
        return mapDataSourceUtilisateurs;
    }

    private Utilisateur[] initUtilisateursArrayDataSource() {
        return new Utilisateur[]{
            new Utilisateur(1, "Mr", "Jerome", "Cantin", "admin@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(1, "Administrateur", "Le rôle administrateur")),
            new Utilisateur(2, "Mr", "Nicolas", "Petit", "nicolas.petit@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")),
            new Utilisateur(3, "Mr", "Jerome", "Cantin", "jerome@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")),
            new Utilisateur(4, "Mr", "Dimitry", "Gozin", "dimitry.gozin@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(1, "Administrateur", "Le rôle administrateur")),
            new Utilisateur(5, "Mme", "Louise", "Correa", "luise@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")),
            new Utilisateur(6, "Mr", "Nicolas", "Gerard", "nicolas.gerard@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")),
            new Utilisateur(7, "Mr", "Remy", "Collard", "remy.collard@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")),
            new Utilisateur(8, "Mr", "Vincent", "Ducan", "vincent.ducamp@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")),
            new Utilisateur(9, "Mme", "Claire", "Dupond", "claire.dupond@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")),
            new Utilisateur(10, "Mme", "Stephanie", "Job", "stephanie.job@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(4, "Vendeur", "Le rôle vendeur")),
            new Utilisateur(11, "Mme", "Celine", "Premier", "celine.premier@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")),
            new Utilisateur(12, "Mr", "Jean", "Marchand", "jean.marchand@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(1, "Administrateur", "Le rôle administrateur")),
            new Utilisateur(13, "Mr", "Nicolas", "Guayard", "nicolas.guayard@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")),
            new Utilisateur(14, "Mr", "Patrick", "Leduc", "patric.leduc@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(4, "Vendeur", "Le rôle vendeur")),
            new Utilisateur(15, "Mr", "Malick", "Ba", "malick.ba@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")),
            new Utilisateur(16, "Mr", "Erwan", "Petit", "erwan.petit@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")),
            new Utilisateur(17, "Mr", "Marc", "Lambert", "marc.lambert@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")),
            new Utilisateur(18, "Mme", "France", "Lebon", "france.lebon@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(4, "Vendeur", "Le rôle vendeur")),
            new Utilisateur(19, "Mr", "Francis", "Garry", "francis.garry@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")),
            new Utilisateur(20, "Mr", "Thierry", "Garon", "thierry.garon@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")),
            new Utilisateur(21, "Mme", "Fatou", "Diop", "fatou.diop@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(3, "Standard", "Le rôle standard")),
            new Utilisateur(22, "Mme", "Fatima", "Salam", "fatima.salam@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")),
            new Utilisateur(23, "Mr", "Jean", "Dasilva", "jean.dasilva@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(3, "Standard", "Le rôle standard")),
            new Utilisateur(24, "Mr", "Kemi", "Bazare", "kemi.bazare@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(1, "Administrateur", "Le rôle administrateur")),
            new Utilisateur(25, "Mr", "Thibault", "Levrard", "thibault.levrard@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")),
            new Utilisateur(26, "Mr", "Michael", "Petit", "michael.petit@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")),
            new Utilisateur(27, "Mr", "Lionnel", "Richie", "lionnel.richie@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(5, "Acheteur", "Le rôle Acheteur")),
            new Utilisateur(28, "Mr", "Maurice", "Green", "maurice.green@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(1, "Administrateur", "Le rôle administrateur"))
        };
    }

    private Role[] initRolesArrayDataSource() {
        return new Role[]{
            new Role(1, "Administrateur", "Le rôle administrateur"),
            new Role(2, "Directeur", "Le rôle de directeur de magasin"),
            new Role(3, "Standard", "Le rôle standard"),
            new Role(4, "Vendeur", "Le rôle vendeur"),
            new Role(5, "Acheteur", "Le rôle Acheteur")};
    }

    public List<Role> getRolesListDataSource() {
        return rolesListDataSource;
    }

    public List<Utilisateur> getUtilisateursListDataSource() {
        return utilisateursListDataSource;
    }

    public Map<Integer, Role> getRolesMapDataSource() {
        return rolesMapDataSource;
    }

    public Map<Integer, Utilisateur> getUtilisateursMapDataSource() {
        return utilisateursMapDataSource;
    }

    public Role[] getRolesArrayDataSource() {
        return rolesArrayDataSource;
    }

    public Utilisateur[] getUtilisateursArrayDataSource() {
        return utilisateursArrayDataSource;
    }
}
