package fr.entoria.ged.bdoc.models.subs;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import fr.entoria.ged.bdoc.models.arrays.HitDocs;

@XmlRootElement(name = "QueryForDocumentsResult")
public class CwsRsltQry implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 2356800281069505497L;

    private HitDocs hitDocs;

    private String queryId;

    public CwsRsltQry() {
    }

    public CwsRsltQry(
		HitDocs hitDocs,
		String queryId) {
	this.hitDocs = hitDocs;
	this.queryId = queryId;
    }

    /**
     * Gets the hitDocs value for this CwsRsltQry.
     * 
     * @return hitDocs
     */
    @XmlElement(name = "HitDocs")
    public HitDocs getHitDocs() {
	return hitDocs;
    }

    /**
     * Sets the hitDocs value for this CwsRsltQry.
     * 
     * @param hitDocs HitDocs
     */
    public void setHitDocs(HitDocs hitDocs) {
	this.hitDocs = hitDocs;
    }

    /**
     * Gets the queryId value for this CwsRsltQry.
     * 
     * @return queryId
     */
    @XmlElement(name = "QueryId")
    public String getQueryId() {
	return queryId;
    }

    /**
     * Sets the queryId value for this CwsRsltQry.
     * 
     * @param queryId String
     */
    public void setQueryId(String queryId) {
	this.queryId = queryId;
    }
}