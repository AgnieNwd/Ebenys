/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.impl;

import com.cours.ebenus.dao.ConnectionHelper;
import com.cours.ebenus.dao.DataSourceSingleton;
import com.cours.ebenus.dao.IUtilisateurDao;
import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.exception.EbenusException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class UtilisateurDao extends AbstractDao<Utilisateur> implements IUtilisateurDao {

	private static final Log log = LogFactory.getLog(UtilisateurDao.class);

	private static final String SQL_QUERY = "SELECT * FROM Utilisateur JOIN Role ON Role.idRole=Utilisateur.idRole WHERE ";

	public UtilisateurDao() {
		super(Utilisateur.class);
	}

	@Override
	public Utilisateur getEntityFromResutlSet(ResultSet resultat) throws SQLException {
		return new Utilisateur(resultat.getInt("idUtilisateur"), resultat.getString("civilite"),
				resultat.getString("prenom"), resultat.getString("nom"), resultat.getString("identifiant"),
				resultat.getString("motPasse"), resultat.getDate("dateNaissance"), resultat.getDate("dateCreation"),
				resultat.getDate("dateModification"), resultat.getBoolean("actif"),
				resultat.getBoolean("marquerEffacer"), resultat.getInt("version"),
                new Role(resultat.getInt("Role.idRole"), resultat.getString("Role.identifiant"),
                        resultat.getString("Role.description"), resultat.getInt("Role.version")));
	}

	@Override
	public List<Utilisateur> findAllUtilisateurs() {
		return super.findAll();
	}

	@Override
	public Utilisateur findUtilisateurById(int idUtilisateur) {
		return super.findById(idUtilisateur);
	}

	@Override
	public List<Utilisateur> findUtilisateursByPrenom(String prenom) {
		return super.findByCriteria("prenom", prenom);
	}

	@Override
	public List<Utilisateur> findUtilisateursByNom(String nom) {
		return super.findByCriteria("nom", nom);
	}

	@Override
	public List<Utilisateur> findUtilisateurByIdentifiant(String identifiant) {
		return super.findByCriteria("Utilisateur.identifiant", identifiant);
	}

	@Override
	public List<Utilisateur> findUtilisateursByIdRole(int idRole) {
		return super.findByCriteria("idRole", idRole);
	}

	@Override
	public List<Utilisateur> findUtilisateursByIdentifiantRole(String identifiantRole) {
		 return super.findByCriteria("Role.identifiant", identifiantRole);
	}

	@Override
	public Utilisateur createUtilisateur(Utilisateur user) {
		Boolean exists = false;
		try {
			exists = (this.findUtilisateurByIdentifiant(user.getIdentifiant()).size() > 0);
		} catch (NullPointerException Ex) {
			exists = false;
		}
		if (exists) {
			throw new EbenusException("Une erreur s'est produite, un utilsateur avec cet id existe déjà", -1);
		}

		if (user.getDateCreation() == null)
			user.setDateCreation(new Date());

		if (user.getDateModification() == null)
			user.setDateModification(new Date());

		if (user.getDateNaissance() == null)
			throw new EbenusException(
					"Une erreur s'est produite, la date de naissance de l'utilisateur que vous voulez créer est null",
					1);

		return super.create(user);

	}

	@Override
	public Utilisateur updateUtilisateur(Utilisateur user) {
		if (user == null) {
			throw new EbenusException("Une erreur s'est produite, l'utilisateur que vous voulez créer est null", 1);
		}

		if (this.findUtilisateurById(user.getIdUtilisateur()) == null) {
			return null;
		}

		return super.update(user);
	}

	@Override
	public boolean deleteUtilisateur(Utilisateur user) {
		return super.delete(user);
	}

	/**
	 * Méthode qui vérifie les logs email / password d'un utilisateur dans la base
	 * de données
	 *
	 * @param email    L'email de l'utilisateur
	 * @param password Le password de l'utilisateur
	 * @return L'utilisateur qui tente de se logger si trouvé, null sinon
	 */
	@Override
	public Utilisateur authenticate(String email, String password) {
		ResultSet resultat = null;
		PreparedStatement statement = null;
		Utilisateur utilisateur = null;
		Connection connection = null;
		try {
			connection = DataSourceSingleton.getConnection();
			statement = connection.prepareStatement(SQL_QUERY + "Utilisateur.identifiant=? AND Utilisateur.motPasse=?");
			statement.setString(1, email);
			statement.setString(2, password);
			resultat = statement.executeQuery();
			while (resultat.next()) {
				utilisateur = getEntityFromResutlSet(resultat);
			}
		} catch (SQLException e) {
			log.debug(e.getStackTrace());
		} finally {
			ConnectionHelper.closeSqlResources(connection, statement, resultat);
		}
		return utilisateur;
	}
}
