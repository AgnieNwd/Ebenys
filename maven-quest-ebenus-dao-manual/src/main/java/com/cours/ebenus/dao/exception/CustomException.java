package com.cours.ebenus.dao.exception;

@SuppressWarnings("serial")
public class CustomException extends RuntimeException
{
    /**
     * Le code d'erreur
     */
    private int code;

    public CustomException(int code)
    {
        super();
        this.code = code;
    }

    public CustomException(String message, int code)
    {
        super(message);
        this.code = code;
    }

    public CustomException(Throwable cause, int code)
    {
        super(cause);
        this.code = code;
    }

    public CustomException(String message, Throwable cause, int code)
    {
        super(message, cause);
        this.code = code;
    }

  // getter et setter
    public int getCode() {
        return (this.code);
    }

    public void setCode(int code) {
        this.code = code;
    }
}
