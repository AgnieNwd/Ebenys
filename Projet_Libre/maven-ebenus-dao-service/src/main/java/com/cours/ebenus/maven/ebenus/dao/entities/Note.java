package com.cours.ebenus.maven.ebenus.dao.entities;

public class Note {
    private Integer idNote;
    private Double libelleNote;
    private User user;
    private Matiere matiere;
    
    public Note() {
    }

    public Note(Integer idNote, Double libelleNote, User user, Matiere matiere) {
        this.idNote = idNote;
        this.libelleNote = libelleNote;
        this.user = user;
        this.matiere = matiere;
    }

    public Note(Integer idNote, Double libelleNote, User user) {
        this(idNote, libelleNote, user, null);
    }
    
    public Note(Double libelleNote, User user, Matiere matiere) {
        this(null, libelleNote, user, matiere);
    }

    public Note(Integer idNote, Double libelleNote) {
        this(idNote, libelleNote, null, null);
    }
    
    public Note(Integer idNote) {
        this(idNote, null, null, null);
    }

    public Integer getIdNote() {
        return idNote;
    }

    public void setidNote(Integer idNote) {
        this.idNote = idNote;
    }

    public Double getLibelleNote() {
        return libelleNote;
    }

    public void setLibelleNote(Double libelleNote) {
        this.libelleNote = libelleNote;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public Matiere getMatiere() {
        return matiere;
    }

    public void setUser(Matiere matiere) {
        this.matiere = matiere;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNote != null ? idNote.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Note)) {
            return false;
        }
        Note other = (Note) object;
        if ((this.idNote == null && other.idNote != null) || (this.idNote != null && !this.idNote.equals(other.idNote))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
    	String toString =  String.format("[idNote=%s, libelleNote=%s]", idNote, libelleNote);
        if (user != null) {
            toString += String.format(" ,user=%s ", user);
        }
        if (matiere != null) {
            toString += String.format(" ,matiere=%s ", matiere);
        }
        return toString;

    }
}
