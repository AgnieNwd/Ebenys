package com.cours.ebenus.maven.ebenus.dao.exception;

public class ProjectException extends RuntimeException {
	// code d'erreur
    private int code;

    public ProjectException(int code) {
        super();
        this.code = code;
    }

    public ProjectException(String message, int code) {
        super(message);
        this.code = code;
    }

    public ProjectException(Throwable cause, int code) {
        super(cause);
        this.code = code;
    }

    public ProjectException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

  // getter et setter
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
