/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.impl;

import com.cours.ebenus.dao.IRoleDao;
import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.exception.EbenusException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class RoleDao extends AbstractDao<Role> implements IRoleDao {

	private static final Log log = LogFactory.getLog(RoleDao.class);

	public RoleDao() {
		super(Role.class);
	}

	@Override
	public Role getEntityFromResutlSet(ResultSet res) throws SQLException {
		return new Role(res.getInt("idRole"), res.getString("identifiant"), res.getString("description"),
				res.getInt("version"));
	}

	@Override
	public List<Role> findAllRoles() {
		return super.findAll();
	}

	@Override
	public Role findRoleById(int idRole) {
		return super.findById(idRole);
	}

	@Override
	public List<Role> findRoleByIdentifiant(String identifiantRole) {
		return super.findByCriteria("identifiant", identifiantRole);
	}

	@Override
	public Role createRole(Role role) {
		if (role == null) {
			throw new EbenusException("Une erreur s'est produite, le rôle que vous voulez créer est null", 1);
		}

		boolean exists;
		try {
			exists = (this.findRoleById(role.getIdRole()) != null);
		} catch (NullPointerException Ex) {
			exists = false;
		}

		if (exists) {
			throw new EbenusException("Une erreur s'est produite, un rôle avec cet id existe déjà", 1);
		}

		return super.create(role);
	}

	@Override
	public Role updateRole(Role role) {
		if (role == null) {
			throw new EbenusException("Une erreur s'est produite, le rôle que vous voulez créer est null", 1);
		} 
		
		if (this.findRoleById(role.getIdRole()) == null) {
			return null;
		}
		return super.update(role);
	}

	@Override
	public boolean deleteRole(Role role) {
		return super.delete(role);
	}
}
