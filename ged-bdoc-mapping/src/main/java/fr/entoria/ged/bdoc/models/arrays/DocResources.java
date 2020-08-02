package fr.entoria.ged.bdoc.models.arrays;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import fr.entoria.ged.bdoc.models.subs.CwsDocResHeader;

@XmlRootElement(name = "DocResources")
public class DocResources implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -1374140854573555462L;

    private CwsDocResHeader[] cwsDocResHeader;

    public DocResources() {
    }

    public DocResources(
		CwsDocResHeader[] cwsDocResHeader) {
	this.cwsDocResHeader = cwsDocResHeader;
    }

    /**
     * Gets the cwsDocResHeader value for this DocResources.
     * 
     * @return cwsDocResHeader
     */
    @XmlElement(name = "CwsDocResHeader")
    public CwsDocResHeader[] getCwsDocResHeader() {
	return cwsDocResHeader;
    }

    /**
     * Sets the cwsDocResHeader value for this DocResources.
     * 
     * @param cwsDocResHeader CwsDocResHeader[]
     */
    public void setCwsDocResHeader(CwsDocResHeader[] cwsDocResHeader) {
	this.cwsDocResHeader = cwsDocResHeader;
    }
}
