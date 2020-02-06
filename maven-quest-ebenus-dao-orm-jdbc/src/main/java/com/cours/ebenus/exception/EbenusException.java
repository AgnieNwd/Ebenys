package com.cours.ebenus.exception;

@SuppressWarnings("serial")
public class EbenusException extends RuntimeException {

    // code d'erreur
    private int code;

    public EbenusException(int code) {
        super();
        this.code = code;
    }

    public EbenusException(String message, int code) {
        super(message);
        this.code = code;
    }

    public EbenusException(Throwable cause, int code) {
        super(cause);
        this.code = code;
    }

    public EbenusException(String message, Throwable cause, int code) {
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
