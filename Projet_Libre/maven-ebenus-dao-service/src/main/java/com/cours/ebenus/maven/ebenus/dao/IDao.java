package com.cours.ebenus.maven.ebenus.dao;

import java.util.List;

public interface IDao<T> {

    public List<T> findAll();

    public T findById(int id);

    public List<T> findByCriteria(String criteria, Object valueCriteria);

    public T create(T t);

    public T update(T t);

    public boolean delete(T t);
}