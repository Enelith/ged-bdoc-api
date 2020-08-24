package fr.entoria.ged.bdoc.models;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import fr.entoria.ged.bdoc.models.subs.CwsRsltAddIndex;

@XmlRootElement(name = "AddDocIndexesResponse")
public class AddDocIndexesResponse implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 2523963442528424996L;

    private CwsRsltAddIndex addDocIndexesResult;

    public AddDocIndexesResponse() {
    }

    public AddDocIndexesResponse(
		CwsRsltAddIndex addDocIndexesResult) {
	this.addDocIndexesResult = addDocIndexesResult;
    }

    /**
     * Gets the addDocIndexesResult value for this AddDocIndexesResponse.
     * 
     * @return addDocIndexesResult
     */
    @XmlElement(name = "AddDocIndexesResult")
    public CwsRsltAddIndex getAddDocIndexesResult() {
	return addDocIndexesResult;
    }

    /**
     * Sets the addDocIndexesResult value for this AddDocIndexesResponse.
     * 
     * @param addDocIndexesResult CwsRsltAddIndex
     */
    public void setAddDocIndexesResult(CwsRsltAddIndex addDocIndexesResult) {
	this.addDocIndexesResult = addDocIndexesResult;
    }
}
