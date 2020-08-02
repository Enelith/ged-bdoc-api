package fr.entoria.ged.bdoc.response;

import org.springframework.http.HttpStatus;

public class GedBdocApiResponse {

    private HttpStatus code;
    private String message;
    private Object result;

    public GedBdocApiResponse() {
	super();
    }

    public GedBdocApiResponse(HttpStatus code, String message, Object result) {
	super();
	this.code = code;
	this.message = message;
	this.result = result;
    }

    public HttpStatus getCode() {
	return code;
    }

    public void setCode(HttpStatus code) {
	this.code = code;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public Object getResult() {
	return result;
    }

    public void setResult(Object result) {
	this.result = result;
    }

}
