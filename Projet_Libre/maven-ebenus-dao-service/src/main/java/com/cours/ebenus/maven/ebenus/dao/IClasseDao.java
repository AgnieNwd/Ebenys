package com.cours.ebenus.maven.ebenus.dao;

import java.util.List;

import com.cours.ebenus.maven.ebenus.dao.entities.Classe;

public interface IClasseDao {
	public List<Classe> findAllClasses();

    public Classe findClasseById(int idClasse);

    public List<Classe> findClasseByLibelleClasse(String libelleClasse);
    
    public Classe createClasse(Classe classe);

    public Classe updateClasse(Classe classe);

    public boolean deleteClasse(Classe classe);
}
