package fr.entoria.ged.bdoc.models.arrays;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import fr.entoria.ged.bdoc.models.subs.CwsRsltDocContents;

@XmlRootElement(name = "HitDocs")
public class HitDocs implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 945083499153433941L;

    private CwsRsltDocContents[] cwsRsltDocContents;

    public HitDocs() {
    }

    public HitDocs(
		CwsRsltDocContents[] cwsRsltDocContents) {
	this.cwsRsltDocContents = cwsRsltDocContents;
    }

    /**
     * Gets the cwsRsltDocContents value for this HitDocs.
     * 
     * @return cwsRsltDocContents
     */
    @XmlElement(name = "CwsRsltDocContents")
    public CwsRsltDocContents[] getCwsRsltDocContents() {
	return cwsRsltDocContents;
    }

    /**
     * Sets the cwsRsltDocContents value for this HitDocs.
     * 
     * @param cwsRsltDocContents CwsRsltDocContents[]
     */
    public void setCwsRsltDocContents(CwsRsltDocContents[] cwsRsltDocContents) {
	this.cwsRsltDocContents = cwsRsltDocContents;
    }
}
