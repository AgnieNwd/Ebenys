package com.cours.ebenus.maven.ebenus.dao;

import com.cours.ebenus.maven.ebenus.dao.entities.User;
import java.util.List;

public interface IUserDao {

	public List<User> findAllUsers();

    public User findUserById(int idUser);

    public List<User> findUsersByNom(String nom);
    
    public List<User> findUsersByPrenom(String prenom);

    public List<User> findUserByEmail(String email);

    public List<User> findUsersByIdRole(int idRole);

    public List<User> findUsersByIdentifiantRole(String identifiant);

    public List<User> findUsersByLibelleClasseIdentifiantRole(String libelleClasse, String identifiant);

    public User createUser(User user);

    public User updateUser(User user);
    
    public User updateUserWithoutPassword(User user);

    public boolean deleteUser(User user);

    public User authenticate(String email, String password);
}
