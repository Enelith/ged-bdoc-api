package fr.entoria.ged.bdoc.models;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IsDocumentExistResponse")
public class IsDocumentExistResponse implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1710511532549800188L;

    private boolean isDocumentExistResult;

    public IsDocumentExistResponse() {
	super();
    }

    public IsDocumentExistResponse(boolean isDocumentExistResult) {
	super();
	this.isDocumentExistResult = isDocumentExistResult;
    }

    @XmlElement(name = "IsDocumentExistResult")
    public boolean isDocumentExistResult() {
	return isDocumentExistResult;
    }

    public void setDocumentExistResult(boolean isDocumentExistResult) {
	this.isDocumentExistResult = isDocumentExistResult;
    }
}
