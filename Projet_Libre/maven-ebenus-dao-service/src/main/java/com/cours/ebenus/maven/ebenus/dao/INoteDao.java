package com.cours.ebenus.maven.ebenus.dao;

import java.util.List;

import com.cours.ebenus.maven.ebenus.dao.entities.Note;

public interface INoteDao {
	public List<Note> findAllNotes();

    public Note findNoteById(int idNote);

    public List<Note> findNoteByLibelleNote(Double libelleNote);
    
    public List<Note> findNoteByIdUser(int idUser);
    
    public List<Note> findNoteByEmailUser(String emailUser);
    
    public List<Note> findNoteByIdMatiere(int idMatiere);
    
    public List<Note> findNoteByLibelleMatiere(String libelleMatiere);

    public Note createNote(Note note);

    public Note updateNote(Note note);

    public boolean deleteNote(Note note);
}
