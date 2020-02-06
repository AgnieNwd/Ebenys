/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.manual.array.impl;

import com.cours.ebenus.dao.IDao;
import java.util.List;

/**
 *
 * @author ElHadji
 * @param <T>
 */
public abstract class AbstractArrayDao<T> implements IDao<T> {

    private T[] myArray = null;

    public AbstractArrayDao(Class<T> t, T[] myArray) {
        this.myArray = myArray;
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
