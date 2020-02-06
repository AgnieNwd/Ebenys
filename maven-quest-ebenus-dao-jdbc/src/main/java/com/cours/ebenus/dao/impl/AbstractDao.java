/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.impl;

import com.cours.ebenus.dao.IDao;
import java.util.List;

/**
 *
 * @author ElHadji
 * @param <T>
 */
public abstract class AbstractDao<T> implements IDao<T> {

    private Class<T> myClass = null;

    public AbstractDao(Class<T> myClass) {
        this.myClass = myClass;
    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public T findById(int id) {
        return null;
    }

    @Override
    public List<T> findByCriteria(String criteria, Object valueCriteria) {
        return null;
    }

    @Override
    public T create(T t) {
        return null;
    }

    @Override
    public T update(T t) {
        return null;
    }

    @Override
    public boolean delete(T t) {
        return false;
    }
}
