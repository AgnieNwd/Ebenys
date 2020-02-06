package com.cours.ebenus.maven.ebenus.dao;

import com.cours.ebenus.maven.ebenus.dao.entities.Role;
import java.util.List;

public interface IRoleDao {
	public List<Role> findAllRoles();

    public Role findRoleById(int idRole);

    public List<Role> findRoleByIdentifiant(String identifiant);

    public Role createRole(Role role);

    public Role updateRole(Role role);

    public boolean deleteRole(Role role);
}
