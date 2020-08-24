package fr.entoria.ged.bdoc.models;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import fr.entoria.ged.bdoc.models.subs.CwsRsltDeleteIndex;

@XmlRootElement(name = "DeleteDocIndexesResponse")
public class DeleteDocIndexesResponse implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 2523963442528424996L;

    private CwsRsltDeleteIndex deleteDocIndexesResult;

    public DeleteDocIndexesResponse() {
    }

    public DeleteDocIndexesResponse(
		CwsRsltDeleteIndex deleteDocIndexesResult) {
	this.deleteDocIndexesResult = deleteDocIndexesResult;
    }

    /**
     * Gets the deleteDocIndexesResult value for this DeleteDocIndexesResponse.
     * 
     * @return deleteDocIndexesResult
     */
    @XmlElement(name = "DeleteDocIndexesResult")
    public CwsRsltDeleteIndex getDeleteDocIndexesResult() {
	return deleteDocIndexesResult;
    }

    /**
     * Sets the deleteDocIndexesResult value for this DeleteDocIndexesResponse.
     * 
     * @param deleteDocIndexesResult CwsRsltDeleteIndex
     */
    public void setDeleteDocIndexesResult(CwsRsltDeleteIndex deleteDocIndexesResult) {
	this.deleteDocIndexesResult = deleteDocIndexesResult;
    }
}
