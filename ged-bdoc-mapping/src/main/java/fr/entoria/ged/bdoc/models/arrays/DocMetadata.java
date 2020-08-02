package fr.entoria.ged.bdoc.models.arrays;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import fr.entoria.ged.bdoc.models.subs.CwsDocMetadata;

@XmlRootElement(name = "DocMetadata")
public class DocMetadata implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 3245487394789842547L;

    private CwsDocMetadata[] cwsDocMetadatas;

    public DocMetadata() {
    }

    public DocMetadata(
		CwsDocMetadata[] cwsDocMetadatas) {
	this.cwsDocMetadatas = cwsDocMetadatas;
    }

    /**
     * Gets the cwsDocMetadatas value for this DocMetadatas.
     * 
     * @return cwsDocMetadatas
     */
    @XmlElement(name = "CwsDocMetadata")
    public CwsDocMetadata[] getCwsDocMetadatas() {
	return cwsDocMetadatas;
    }

    /**
     * Sets the cwsDocMetadatas value for this DocMetadatas.
     * 
     * @param cwsDocMetadatas CwsDocMetadata[]
     */
    public void setCwsDocMetadatas(CwsDocMetadata[] cwsDocMetadatas) {
	this.cwsDocMetadatas = cwsDocMetadatas;
    }
}
