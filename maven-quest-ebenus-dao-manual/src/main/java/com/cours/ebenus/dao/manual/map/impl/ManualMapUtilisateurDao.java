/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.manual.map.impl;

import com.cours.ebenus.dao.DataSource;
import com.cours.ebenus.dao.IUtilisateurDao;
import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.dao.exception.CustomException;

import java.util.ArrayList;
import java.util.Collections;
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
public class ManualMapUtilisateurDao extends AbstractMapDao<Utilisateur> implements IUtilisateurDao {

    private static final Log log = LogFactory.getLog(ManualMapUtilisateurDao.class);
    private Map<Integer, Utilisateur> utilisateursMapDataSource = new HashMap<Integer, Utilisateur>();

    public ManualMapUtilisateurDao() {
    	super(Utilisateur.class, DataSource.getInstance().getUtilisateursMapDataSource());
    	/*utilisateursMapDataSource.put(1, new Utilisateur(1, "mllx", "eli", "zabeth", "123123", "mdp", new Date(), new Role(1, "admin", "administrateur")));
    	utilisateursMapDataSource.put(2, new Utilisateur(2, "m", "will", "iam", "122122", "mdp", new Date(), new Role(2, "util", "utilisateur")));
    	utilisateursMapDataSource.put(3, new Utilisateur(3, "m", "alex", "andre", "133133", "mdp", new Date(), new Role(2, "util", "utilisateur")));
    	utilisateursMapDataSource.put(4, new Utilisateur(4, "mllx", "lau", "rence", "132132", "mdp", new Date(), new Role(2, "util", "utilisateur")));*/
    	utilisateursMapDataSource = DataSource.getInstance().getUtilisateursMapDataSource();
    }
    /**
     * Méthode qui retourne la liste de tous les utilisateurs de la database
     * (ici utilisateursMapDataSource)
     *
     * @return La liste de tous les utilisateurs de la database
     */
    @Override
    public List<Utilisateur> findAllUtilisateurs() {
    	List<Utilisateur> list = new ArrayList<Utilisateur>(utilisateursMapDataSource.values());
        return list;
    }

    /**
     * Méthode qui retourne l'utilisateur d'id passé en paramètre de la database
     * (ici utilisateursMapDataSource)
     *
     * @param idUtilisateur L'id de l'user à récupérer
     * @return L'utilisateur d'id passé en paramètre, null si non trouvé
     */
    @Override
    public Utilisateur findUtilisateurById(int idUtilisateur) {
    	//List<Utilisateur> list = this.findAllUtilisateurs();
    	for (Utilisateur util : utilisateursMapDataSource.values()) {
    		if  (util.getIdUtilisateur() == idUtilisateur) {
    			return util;
    		}
    	}
        return null;
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursMapDataSource) dont le prénom est égal au paramètre
     * passé
     *
     * @param prenom Le prénom des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont le prénom est égal au
     * paramètre passé
     */
    @Override
    public List<Utilisateur> findUtilisateursByPrenom(String prenom) {
    	List<Utilisateur> utilisateursList = new ArrayList<Utilisateur>();
    	
    	for (Utilisateur util : utilisateursMapDataSource.values()) {
    		if  (util.getPrenom() == prenom) {
    			utilisateursList.add(util);
    		}
    	}
    	return utilisateursList;
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursMapDataSource) dont le nom est égal au paramètre passé
     *
     * @param nom Le nom des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont le nom est égal au
     * paramètre passé
     */
    @Override
    public List<Utilisateur> findUtilisateursByNom(String nom) {
    	List<Utilisateur> utilisateursList = new ArrayList<Utilisateur>();
    	
    	for (Utilisateur util : utilisateursMapDataSource.values()) {
    		if  (util.getNom() == nom) {
    			utilisateursList.add(util);
    		}
    	}
    	return utilisateursList;    
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursListDataSource) dont l'identifiant est égal au paramètre
     * passé
     *
     * @param identifiant Le nom des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont l'identifiant est égal au
     * paramètre passé
     */
    @Override
    public List<Utilisateur> findUtilisateurByIdentifiant(String identifiant) {
    	List<Utilisateur> utilisateursList = new ArrayList<Utilisateur>();
    	
    	for (Utilisateur util : utilisateursMapDataSource.values()) {
    		if  (util.getIdentifiant() == identifiant) {
    			utilisateursList.add(util);
    		}
    	}
    	return utilisateursList; 
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursMapDataSource) dont le rôle a comme id celui passé en
     * paramètre
     *
     * @param idRole L'id du rôle des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont le rôle a comme id celui
     * passé en paramètre
     */
    @Override
    public List<Utilisateur> findUtilisateursByIdRole(int idRole) {
    	List<Utilisateur> utilisateursList = new ArrayList<Utilisateur>();
    	
    	for (Utilisateur util : utilisateursMapDataSource.values()) {
    		if  (util.getRole().getIdRole() == idRole) {
    			utilisateursList.add(util);
    		}
    	}
    	System.out.println("siiiiizzzzze" + utilisateursList.toString());

    	return utilisateursList; 
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursMapDataSource) dont le rôle a comme identifiant celui
     * passé en paramètre
     *
     * @param identifiantRole L'identifiant du rôle des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont le rôle a comme
     * identifiant celui passé en paramètre
     */
    @Override
    public List<Utilisateur> findUtilisateursByIdentifiantRole(String identifiantRole) {
    	List<Utilisateur> utilisateursList = new ArrayList<Utilisateur>();
    	
    	for (Utilisateur util : utilisateursMapDataSource.values()) {
    		if  (util.getRole().getIdentifiant() == identifiantRole) {
    			utilisateursList.add(util);
    		}
    	}
    	return utilisateursList; 
    }

    /**
     * Méthode qui permet d'ajouter un utilisateur dans la database (ici
     * utilisateursMapDataSource)
     *
     * @param user L'utilisateur à ajouter
     * @return L'utilisateur ajouté ou null si échec
     */
    @Override
    public Utilisateur createUtilisateur(Utilisateur user) {
    	if(user == null)
    		return null;
    	if (this.findUtilisateurByIdentifiant(user.getIdentifiant()).isEmpty()) {
	    	if(user.getDateCreation() == null)
	    		user.setDateCreation(new Date());
	    	if(user.getDateModification() == null)
	    		user.setDateModification(user.getDateCreation());
	    	if(user.getIdUtilisateur() == null) {
	    		int size = this.findAllUtilisateurs().size();
	    		user.setIdUtilisateur(size + 1);
	    	}
    		utilisateursMapDataSource.put(user.getIdUtilisateur(), user);
	    	return user;
        } else {
        	throw new CustomException("Une erreur s’est produite, il existe déjà un utilisateur avec l’identifiant "+ user.getIdentifiant() +" dans l’application", -1);
        }
    }

    /**
     * Méthode qui permet d'update un utilisateur existant dans la database (ici
     * utilisateursMapDataSource)
     *
     * @param user L'utilisateur à modifier
     * @return L'utilisateur modifié ou null si ce dernier n'existe pas dans la
     * database
     */
    @Override
    public Utilisateur updateUtilisateur(Utilisateur user) {
    	Utilisateur userOld = this.findUtilisateurById(user.getIdUtilisateur());

    	Integer key = getKey(utilisateursMapDataSource, userOld);
    	 	
    	if (userOld != null) {
    		utilisateursMapDataSource.replace(key, user); 
    		return user;
    	}
        return null;
    }

    /**
     * Méthode qui permet de supprimer un utilisateur existant dans la database
     * (ici utilisateursMapDataSource)
     *
     * @param user L'utilisateur à supprimer
     * @return True si suppression effectuée, false sinon
     */
    @Override
    public boolean deleteUtilisateur(Utilisateur user) {
    	Utilisateur userFound = this.findUtilisateurById(user.getIdUtilisateur());
    	
    	if (userFound.equals(user)) {
    		Integer key = getKey(utilisateursMapDataSource, user);
        	
        	Utilisateur userRemoved = utilisateursMapDataSource.remove(key);

        	if (userRemoved != null) {
        		return true;
        	}
    	}
    	
        return false;
    }
    
    public static <K, V> K getKey(Map<K, V> map, V value) {
		for (Map.Entry<K, V> entry : map.entrySet()) {
			if (value.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		return null;
	}
}
