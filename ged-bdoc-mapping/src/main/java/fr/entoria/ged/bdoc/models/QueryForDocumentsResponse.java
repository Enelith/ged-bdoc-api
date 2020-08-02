package fr.entoria.ged.bdoc.models;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import fr.entoria.ged.bdoc.models.subs.CwsRsltQry;

@XmlRootElement(name = "QueryForDocumentsResponse")
public class QueryForDocumentsResponse implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -8567546279845141876L;

    private CwsRsltQry queryForDocumentsResult;

    public QueryForDocumentsResponse() {
    }

    public QueryForDocumentsResponse(
		CwsRsltQry queryForDocumentsResult) {
	this.queryForDocumentsResult = queryForDocumentsResult;
    }

    /**
     * Gets the queryForDocumentsResult value for this QueryForDocumentsResponse.
     * 
     * @return queryForDocumentsResult
     */
    @XmlElement(name = "QueryForDocumentsResult")
    public CwsRsltQry getQueryForDocumentsResult() {
	return queryForDocumentsResult;
    }

    /**
     * Sets the queryForDocumentsResult value for this QueryForDocumentsResponse.
     * 
     * @param queryForDocumentsResult CwsRsltQry
     */
    public void setQueryForDocumentsResult(CwsRsltQry queryForDocumentsResult) {
	this.queryForDocumentsResult = queryForDocumentsResult;
    }
}
