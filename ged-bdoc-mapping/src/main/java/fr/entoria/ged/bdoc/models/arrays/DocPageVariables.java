package fr.entoria.ged.bdoc.models.arrays;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import fr.entoria.ged.bdoc.models.subs.CwsDocPageVariable;

@XmlRootElement(name = "DocPageVariables")
public class DocPageVariables implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 5458977711333514184L;

    private CwsDocPageVariable[] cwsDocPageVariables;

    public DocPageVariables() {
    }

    public DocPageVariables(
		CwsDocPageVariable[] cwsDocPageVariables) {
	this.cwsDocPageVariables = cwsDocPageVariables;
    }

    /**
     * Gets the cwsDocPageVariables value for this DocMetadatas.
     * 
     * @return cwsDocPageVariables
     */
    @XmlElement(name = "CwsDocPageVariable")
    public CwsDocPageVariable[] getCwsDocPageVariables() {
	return cwsDocPageVariables;
    }

    /**
     * Sets the cwsDocPageVariables value for this DocMetadatas.
     * 
     * @param cwsDocPageVariables CwsDocPageVariable[]
     */
    public void setCwsDocPageVariables(CwsDocPageVariable[] cwsDocPageVariables) {
	this.cwsDocPageVariables = cwsDocPageVariables;
    }
}
