package fr.entoria.ged.bdoc.models;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import fr.entoria.ged.bdoc.models.subs.CwsRsltDocContents;

@XmlRootElement(name = "LoadDocContentsResponse")
public class LoadDocContentsResponse implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -3891794187968875523L;

    private CwsRsltDocContents loadDocContentsResult;

    public LoadDocContentsResponse() {
    }

    public LoadDocContentsResponse(
		CwsRsltDocContents loadDocContentsResult) {
	this.loadDocContentsResult = loadDocContentsResult;
    }

    /**
     * Gets the loadDocContentsResult value for this LoadDocContentsResponse.
     * 
     * @return loadDocContentsResult
     */
    @XmlElement(name = "LoadDocContentsResult")
    public CwsRsltDocContents getLoadDocContentsResult() {
	return loadDocContentsResult;
    }

    /**
     * Sets the queryForDocumentsResult value for this QueryForDocumentsResponse.
     * 
     * @param loadDocContentsResult CwsRsltDocContents
     */
    public void setLoadDocContentsResult(CwsRsltDocContents loadDocContentsResult) {
	this.loadDocContentsResult = loadDocContentsResult;
    }
}
