package fr.entoria.ged.bdoc.models;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "LogonDVResponse")
public class LogonDVResponse implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 4265898769802440856L;

    private boolean logonDVResult;

    private String userID;

    private String errorMessage;

    public LogonDVResponse() {
    }

    public LogonDVResponse(boolean logonDVResult, String userID, String errorMessage) {
	super();
	this.logonDVResult = logonDVResult;
	this.userID = userID;
	this.errorMessage = errorMessage;
    }

    @XmlElement(name = "LogonDVResult")
    public boolean isLogonDVResult() {
	return logonDVResult;
    }

    public void setLogonDVResult(boolean logonDVResult) {
	this.logonDVResult = logonDVResult;
    }

    @XmlElement(name = "UserID")
    public String getUserID() {
	return userID;
    }

    public void setUserID(String userID) {
	this.userID = userID;
    }

    @XmlElement(name = "ErrorMessage")
    public String getErrorMessage() {
	return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
	this.errorMessage = errorMessage;
    }

}
