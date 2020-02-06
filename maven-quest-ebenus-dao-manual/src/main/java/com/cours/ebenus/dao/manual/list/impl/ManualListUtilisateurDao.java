/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.manual.list.impl;

import com.cours.ebenus.dao.DataSource;
import com.cours.ebenus.dao.IUtilisateurDao;
import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.dao.exception.CustomException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class ManualListUtilisateurDao extends AbstractListDao<Utilisateur>implements IUtilisateurDao {

    private static final Log log = LogFactory.getLog(ManualListUtilisateurDao.class);
    private List<Utilisateur> utilisateursListDataSource = new ArrayList<Utilisateur>();

    public ManualListUtilisateurDao() {
    	/*utilisateursListDataSource.add(new Utilisateur(1, "mllx", "eli", "zabeth", "123123", "mdp", new Date(), new Role(1, "admin", "administrateur")));
    	utilisateursListDataSource.add(new Utilisateur(2, "mr", "will", "iam", "122122", "mdp", new Date(), new Role(2, "util", "utilisateur")));
    	utilisateursListDataSource.add(new Utilisateur(3, "mr", "alex", "andre", "133133", "mdp", new Date(), new Role(2, "util", "utilisateur")));
    	utilisateursListDataSource.add(new Utilisateur(4, "mllx", "lau", "rence", "132132", "mdp", new Date(), new Role(2, "util", "utilisateur")));
    	utilisateursListDataSource.add(new Utilisateur(5, "mme", "eli", "moure", "124124", "mdp", new Date(), new Role(2, "util", "utilisateur")));*/

    	super(Utilisateur.class, DataSource.getInstance().getUtilisateursListDataSource());
    	utilisateursListDataSource = DataSource.getInstance().getUtilisateursListDataSource();

    }
    /**
     * Méthode qui retourne la liste de tous les utilisateurs de la database
     * (ici utilisateursListDataSource)
     *
     * @return La liste de tous les utilisateurs de la database
     */
    @Override
    public List<Utilisateur> findAllUtilisateurs() {
        return utilisateursListDataSource;
    }

    /**
     * Méthode qui retourne l'utilisateur d'id passé en paramètre de la database
     * (ici utilisateursListDataSource)
     *
     * @param idUtilisateur L'id de l'user à récupérer
     * @return L'utilisateur d'id passé en paramètre, null si non trouvé
     */
    @Override
    public Utilisateur findUtilisateurById(int idUtilisateur) {
    	for (Utilisateur util : this.utilisateursListDataSource) {
    		if  (util.getIdUtilisateur() == idUtilisateur) {
    	    	System.out.println("findUtilisateurById()"+ util.toString());
    			return util;
    		}
    	}
    	return null;
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursListDataSource) dont le prénom est égal au paramètre
     * passé
     *
     * @param prenom Le prénom des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont le prénom est égal au
     * paramètre passé
     */
    @Override
    public List<Utilisateur> findUtilisateursByPrenom(String prenom) {
    	List<Utilisateur> utilisateursList = new ArrayList<Utilisateur>();
    	
    	for (Utilisateur util : this.utilisateursListDataSource) {
    		if  (util.getPrenom() == prenom) {
    			utilisateursList.add(util);
    		}
    	}
    	return utilisateursList;
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursListDataSource) dont le nom est égal au paramètre passé
     *
     * @param nom Le nom des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont le nom est égal au
     * paramètre passé
     */
    @Override
    public List<Utilisateur> findUtilisateursByNom(String nom) {
    	List<Utilisateur> utilisateursList = new ArrayList<Utilisateur>();
    	
    	for (Utilisateur util : this.utilisateursListDataSource) {
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
    	
    	for (Utilisateur util : this.utilisateursListDataSource) {
    		if  (util.getIdentifiant() == identifiant) {
    			utilisateursList.add(util);
    		}
    	}
    	return utilisateursList;
    }

    /**
     * Méthode qui permet d'ajouter un utilisateur dans la database (ici
     * utilisateursListDataSource)
     *
     * @param user L'utilisateur à ajouter
     * @return L'utilisateur ajouté ou null si échec
     */
    @Override
    public Utilisateur createUtilisateur(Utilisateur user) {
    	if(user == null)
    		return null;
    	if (this.findUtilisateurByIdentifiant(user.getIdentifiant()).isEmpty()) {
	    	if(user.getIdUtilisateur() == null) {
				int size = this.findAllUtilisateurs().size();
				utilisateursListDataSource.add(user);
				user.setIdUtilisateur(size + 1);
	    	}
	    	if(user.getDateCreation() == null) {
	    		user.setDateCreation(new Date());
	    		
	    	}
	    	if(user.getDateModification() == null)
	    		user.setDateModification(user.getDateCreation());
	    	
	    	return user;
    	} else {
        	throw new CustomException("Une erreur s’est produite, il existe déjà un utilisateur avec l’identifiant "+ user.getIdentifiant() +" dans l’application", -1);
    	}
   
    }

    /**
     * Méthode qui permet d'update un utilisateur existant dans la database (ici
     * utilisateursListDataSource)
     *
     * @param user L'utilisateur à modifier
     * @return L'utilisateur modifié ou null si ce dernier n'existe pas dans la
     * database
     */
    @Override
    public Utilisateur updateUtilisateur(Utilisateur user) {
    	Utilisateur userOld = this.findUtilisateurById(user.getIdUtilisateur());
    	
    	if (userOld != null) {
    		Collections.replaceAll(utilisateursListDataSource, userOld, user);
    		return user;
    	}
        return null;
    }

    /**
     * Méthode qui permet de supprimer un utilisateur existant dans la database
     * (ici utilisateursListDataSource)
     *
     * @param user L'utilisateur à supprimer
     * @return True si suppression effectuée, false sinon
     */
    @Override
    public boolean deleteUtilisateur(Utilisateur user) {
    	if (utilisateursListDataSource.remove(user)) {
    		return true;
    	}
    	return false;
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursListDataSource) dont le rôle a comme id celui passé en
     * paramètre
     *
     * @param idRole L'id du rôle des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont le rôle a comme id celui
     * passé en paramètre
     */
    @Override
    public List<Utilisateur> findUtilisateursByIdRole(int idRole) {
    	List<Utilisateur> utilisateursList = new ArrayList<Utilisateur>();
    	
    	for (Utilisateur util : this.utilisateursListDataSource) {
    		if  (util.getRole().getIdRole() == idRole) {
    			utilisateursList.add(util);
    		}
    	}
    	return utilisateursList;
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursListDataSource) dont le rôle a comme identifiant celui
     * passé en paramètre
     *
     * @param identifiantRole L'identifiant du rôle des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont le rôle a comme
     * identifiant celui passé en paramètre
     */
    @Override
    public List<Utilisateur> findUtilisateursByIdentifiantRole(String identifiantRole) {
    	List<Utilisateur> utilisateursList = new ArrayList<Utilisateur>();
    	
    	for (Utilisateur util : this.utilisateursListDataSource) {
    		if  (util.getRole().getIdentifiant() == identifiantRole) {
    			utilisateursList.add(util);
    		}
    	}
    	return utilisateursList;
    }
}
