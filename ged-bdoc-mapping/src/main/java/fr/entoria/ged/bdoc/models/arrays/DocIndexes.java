package fr.entoria.ged.bdoc.models.arrays;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import fr.entoria.ged.bdoc.models.subs.CwsDocIndex;

@XmlRootElement(name = "DocIndexes")
public class DocIndexes implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 5562855439654057175L;

    private CwsDocIndex[] docIndexes;

    public DocIndexes() {
    }

    public DocIndexes(
		CwsDocIndex[] docIndexes) {
	this.docIndexes = docIndexes;
    }

    /**
     * Gets the docIndexes value for this DocIndexes.
     * 
     * @return docIndexes
     */
    @XmlElement(name = "CwsDocIndex")
    public CwsDocIndex[] getDocIndexes() {
	return docIndexes;
    }

    /**
     * Sets the docIndexes value for this DocIndexes.
     * 
     * @param docIndexes CwsDocIndex[]
     */
    public void setDocIndexes(CwsDocIndex[] docIndexes) {
	this.docIndexes = docIndexes;
    }
}
