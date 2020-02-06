/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao;

import com.cours.ebenus.dao.entities.Role;
import java.util.List;

/**
 *
 * @author ElHadji
 */
public interface IRoleDao {

    public List<Role> findAllRoles();

    public Role findRoleById(int idRole);

    public List<Role> findRoleByIdentifiant(String identifiantRole);

    public Role createRole(Role role);

    public Role updateRole(Role role);

    public boolean deleteRole(Role role);

}
