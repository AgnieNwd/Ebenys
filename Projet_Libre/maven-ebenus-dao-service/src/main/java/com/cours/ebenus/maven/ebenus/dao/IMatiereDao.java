package com.cours.ebenus.maven.ebenus.dao;

import java.util.List;

import com.cours.ebenus.maven.ebenus.dao.entities.Matiere;

public interface IMatiereDao {
	public List<Matiere> findAllMatieres();

    public Matiere findMatiereById(int idMatiere);

    public List<Matiere> findMatiereByLibelleMatiere(String libelleMatiere);
    
    public List<Matiere> findMatiereByIdUser(int idUser);
    
    public List<Matiere> findUsersByEmailUser(String emailUser);
    
    public Matiere createMatiere(Matiere Matiere);

    public Matiere updateMatiere(Matiere Matiere);

    public boolean deleteMatiere(Matiere Matiere);
}
