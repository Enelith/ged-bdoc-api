package fr.entoria.ged.bdoc.exceptions;

public class InvalidGedBdocApiArgumentsException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = -5812728430708702098L;

    /** The code. */
    String code;

    /** The message. */
    String message;

    public InvalidGedBdocApiArgumentsException(String code, String message) {
	super();
	this.code = code;
	this.message = message;
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }
}
