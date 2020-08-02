package fr.entoria.ged.bdoc.models.subs;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CwsDocMetadata")
public class CwsDocMetadata implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 3105756801781246049L;

    private String id;

    private String name;

    private String val;

    public CwsDocMetadata() {
    }

    public CwsDocMetadata(
		String id,
		String name,
		String val) {
	this.id = id;
	this.name = name;
	this.val = val;
    }

    /**
     * Gets the id value for this CwsDocMetadata.
     * 
     * @return id
     */
    @XmlElement(name = "Id")
    public String getId() {
	return id;
    }

    /**
     * Sets the id value for this CwsDocMetadata.
     * 
     * @param id String
     */
    public void setId(String id) {
	this.id = id;
    }

    /**
     * Gets the name value for this CwsDocMetadata.
     * 
     * @return name
     */
    @XmlElement(name = "Name")
    public String getName() {
	return name;
    }

    /**
     * Sets the name value for this CwsDocMetadata.
     * 
     * @param name String
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * Gets the val value for this CwsDocMetadata.
     * 
     * @return val
     */
    @XmlElement(name = "Val")
    public String getVal() {
	return val;
    }

    /**
     * Sets the val value for this CwsDocMetadata.
     * 
     * @param val String
     */
    public void setVal(String val) {
	this.val = val;
    }
}