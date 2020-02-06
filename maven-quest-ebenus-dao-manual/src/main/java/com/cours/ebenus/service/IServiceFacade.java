/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.service;

import com.cours.ebenus.dao.IRoleDao;
import com.cours.ebenus.dao.IUtilisateurDao;

/**
 *
 * @author ElHadji
 */
public interface IServiceFacade
{
    public IUtilisateurDao getUtilisateurDao();

    public IRoleDao getRoleDao();
}
