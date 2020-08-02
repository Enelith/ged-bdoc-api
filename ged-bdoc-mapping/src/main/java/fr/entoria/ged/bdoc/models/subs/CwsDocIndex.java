package fr.entoria.ged.bdoc.models.subs;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CwsDocIndex")
public class CwsDocIndex implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -3788220850740349250L;

    private String inxType;

    private String inxScope;

    private Integer inxId;

    private String inxName;

    private String inxVal;

    public CwsDocIndex() {
    }

    public CwsDocIndex(
		String inxType,
		String inxScope,
		Integer inxId,
		String inxName,
		String inxVal) {
	this.inxType = inxType;
	this.inxScope = inxScope;
	this.inxId = inxId;
	this.inxName = inxName;
	this.inxVal = inxVal;
    }

    /**
     * Gets the inxType value for this CwsDocIndex.
     * 
     * @return inxType
     */
    @XmlElement(name = "InxType")
    public String getInxType() {
	return inxType;
    }

    /**
     * Sets the inxType value for this CwsDocIndex.
     * 
     * @param inxType String
     */
    public void setInxType(String inxType) {
	this.inxType = inxType;
    }

    /**
     * Gets the inxScope value for this CwsDocIndex.
     * 
     * @return inxScope
     */
    @XmlElement(name = "InxScope")
    public String getInxScope() {
	return inxScope;
    }

    /**
     * Sets the inxScope value for this CwsDocIndex.
     * 
     * @param inxScope String
     */
    public void setInxScope(String inxScope) {
	this.inxScope = inxScope;
    }

    /**
     * Gets the inxId value for this CwsDocIndex.
     * 
     * @return inxId
     */
    @XmlElement(name = "InxId")
    public Integer getInxId() {
	return inxId;
    }

    /**
     * Sets the inxId value for this CwsDocIndex.
     * 
     * @param inxId String
     */
    public void setInxId(Integer inxId) {
	this.inxId = inxId;
    }

    /**
     * Gets the inxName value for this CwsDocIndex.
     * 
     * @return inxName
     */
    @XmlElement(name = "InxName")
    public String getInxName() {
	return inxName;
    }

    /**
     * Sets the inxName value for this CwsDocIndex.
     * 
     * @param inxName String
     */
    public void setInxName(String inxName) {
	this.inxName = inxName;
    }

    /**
     * Gets the inxVal value for this CwsDocIndex.
     * 
     * @return inxVal
     */
    @XmlElement(name = "InxVal")
    public String getInxVal() {
	return inxVal;
    }

    /**
     * Sets the inxVal value for this CwsDocIndex.
     * 
     * @param inxVal String
     */
    public void setInxVal(String inxVal) {
	this.inxVal = inxVal;
    }
}
