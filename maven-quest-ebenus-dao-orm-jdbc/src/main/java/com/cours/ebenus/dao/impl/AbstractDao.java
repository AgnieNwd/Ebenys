/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.impl;

import com.cours.ebenus.dao.ConnectionHelper;
import com.cours.ebenus.dao.DataSourceSingleton;
import com.cours.ebenus.dao.IDao;
import com.cours.ebenus.exception.EbenusException;
import com.cours.ebenus.utils.ReflectionToSqlTranformer;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 * @param <T>
 */
public abstract class AbstractDao<T> implements IDao<T> {

	private static final Log log = LogFactory.getLog(AbstractDao.class);

	private Class<T> myClass = null;

	public AbstractDao(Class<T> myClass) {
		this.myClass = myClass;
	}

	@Override
	public List<T> findAll() {
		ResultSet resultat = null;
		PreparedStatement statement = null;
		List<T> entitys = new ArrayList<>();
		Connection connection = null;
		try {
			connection = DataSourceSingleton.getConnection();
			statement = ReflectionToSqlTranformer.generateSelectStatement(connection, myClass, null, null);
			resultat = statement.executeQuery();
			while (resultat.next()) {
				entitys.add(getEntityFromResutlSet(resultat));
			}
		} catch (SQLException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException
				| SecurityException e) {
			log.error(e.getStackTrace());
		} finally {
			ConnectionHelper.closeSqlResources(connection, statement, resultat);
		}
		return entitys;
	}

	@Override
	public T findById(int id) {
		ResultSet resultat = null;
		PreparedStatement statement = null;
		T entity = null;
		Connection connection = null;
		try {
			connection = DataSourceSingleton.getConnection();
			statement = ReflectionToSqlTranformer.generateSelectStatement(connection, myClass, "id"+myClass.getSimpleName(), id);
			resultat = statement.executeQuery();
			if (resultat.next()) {
				entity = getEntityFromResutlSet(resultat);
			}
		} catch (SQLException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException
				| SecurityException e)
		{
			log.debug(e.getStackTrace());
		} finally {
			ConnectionHelper.closeSqlResources(connection, statement, resultat);
		}
		return entity;
	}

	@Override
	public List<T> findByCriteria(String criteria, Object valueCriteria) {
		ResultSet resultat = null;
		PreparedStatement statement = null;
		List<T> entitys = new ArrayList<>();
		Connection connection = null;
		try {
			connection = DataSourceSingleton.getConnection();
			statement = ReflectionToSqlTranformer.generateSelectStatement(connection, myClass, criteria, valueCriteria);
			resultat = statement.executeQuery();
			while (resultat.next()) {
				entitys.add(getEntityFromResutlSet(resultat));
			}
		} catch (SQLException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException
				| SecurityException e) {
			log.debug(e.getStackTrace());
		} finally {
			ConnectionHelper.closeSqlResources(connection, statement, resultat);
		}
		return entitys;
	}

	@Override
	public T create(T t) {
		PreparedStatement statement = null;
		ResultSet result = null;

		if (t == null) {
			throw new EbenusException("Une erreur s'est produite, l'entite que vous voulez créer est null", 1);
		} else {
			Connection connection = null;

			try {
				connection = DataSourceSingleton.getConnection();
				statement = ReflectionToSqlTranformer.generateCreateStatement(connection, myClass, t);
				int row = statement.executeUpdate();
				if (row == 1) {
					result = statement.getGeneratedKeys();
					if (result.next()) {
						t = ReflectionToSqlTranformer.UpdateEntityId(t, result.getInt(1));
					}
				}
			} catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException
					| SQLException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException
					| SecurityException e) {
				log.error(e.getStackTrace());
			} finally {
				ConnectionHelper.closeSqlResources(connection, statement, result);
			}
		}
		return t;
	}

	@Override
	public T update(T t) {
		PreparedStatement statement = null;
		ResultSet result = null;

		if (t == null) {
			throw new EbenusException("Une erreur s'est produite, l'entite que vous voulez créer est null", 1);
		} else {
			Connection connection = null;

			try {
				connection = DataSourceSingleton.getConnection();
				statement = ReflectionToSqlTranformer.generateUpdateStatement(connection, myClass, t);
				statement.executeUpdate();
			} catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException
					| SQLException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException
					| SecurityException e) {
				log.error(e.getStackTrace());
			} finally {
				ConnectionHelper.closeSqlResources(connection, statement, result);
			}
		}
		return t;
	}

	@Override
	public boolean delete(T t) {
		PreparedStatement statement = null;
		int deleted = 0;
		Connection connection = null;
		try {
			connection = DataSourceSingleton.getConnection();
			statement = ReflectionToSqlTranformer.generateDeleteStatement(connection, myClass, t);
			deleted = statement.executeUpdate();
		} catch (SQLException | NoSuchFieldException | SecurityException | IllegalArgumentException
				| IllegalAccessException e) {
			log.debug(e.getStackTrace());
		} finally {
			ConnectionHelper.closeSqlResources(connection, statement, null);
		}
		return deleted > 0;
	}

	abstract public T getEntityFromResutlSet(ResultSet res) throws SQLException;

}
