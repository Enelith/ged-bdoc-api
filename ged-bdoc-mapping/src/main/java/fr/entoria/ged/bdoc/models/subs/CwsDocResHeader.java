package fr.entoria.ged.bdoc.models.subs;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CwsDocResHeader")
public class CwsDocResHeader implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 4478895224525625080L;

    private String resType;

    private String resDbase;

    private String resName;

    private Integer resId;

    private Integer resSegNo;

    private Integer resItemCount;

    private Integer jobResInx;

    private boolean loaded;

    private boolean heldInSendQ;

    public CwsDocResHeader() {
    }

    public CwsDocResHeader(
		String resType,
		String resDbase,
		String resName,
		Integer resId,
		Integer resSegNo,
		Integer resItemCount,
		Integer jobResInx,
		boolean loaded,
		boolean heldInSendQ) {
	this.resType = resType;
	this.resDbase = resDbase;
	this.resName = resName;
	this.resId = resId;
	this.resSegNo = resSegNo;
	this.resItemCount = resItemCount;
	this.jobResInx = jobResInx;
	this.loaded = loaded;
	this.heldInSendQ = heldInSendQ;
    }

    /**
     * Gets the resType value for this CwsDocResHeader.
     * 
     * @return resType
     */
    @XmlElement(name = "ResType")
    public String getResType() {
	return resType;
    }

    /**
     * Sets the resType value for this CwsDocResHeader.
     * 
     * @param resType String
     */
    public void setResType(String resType) {
	this.resType = resType;
    }

    /**
     * Gets the resDbase value for this CwsDocResHeader.
     * 
     * @return resDbase
     */
    @XmlElement(name = "ResDbase")
    public String getResDbase() {
	return resDbase;
    }

    /**
     * Sets the resDbase value for this CwsDocResHeader.
     * 
     * @param resDbase String
     */
    public void setResDbase(String resDbase) {
	this.resDbase = resDbase;
    }

    /**
     * Gets the resName value for this CwsDocResHeader.
     * 
     * @return resName
     */
    @XmlElement(name = "ResName")
    public String getResName() {
	return resName;
    }

    /**
     * Sets the resName value for this CwsDocResHeader.
     * 
     * @param resName String
     */
    public void setResName(String resName) {
	this.resName = resName;
    }

    /**
     * Gets the resId value for this CwsDocResHeader.
     * 
     * @return resId
     */
    @XmlElement(name = "ResId")
    public Integer getResId() {
	return resId;
    }

    /**
     * Sets the resId value for this CwsDocResHeader.
     * 
     * @param resId Integer
     */
    public void setResId(Integer resId) {
	this.resId = resId;
    }

    /**
     * Gets the resSegNo value for this CwsDocResHeader.
     * 
     * @return resSegNo
     */
    @XmlElement(name = "ResSegNo")
    public Integer getResSegNo() {
	return resSegNo;
    }

    /**
     * Sets the resSegNo value for this CwsDocResHeader.
     * 
     * @param resSegNo Integer
     */
    public void setResSegNo(Integer resSegNo) {
	this.resSegNo = resSegNo;
    }

    /**
     * Gets the resItemCount value for this CwsDocResHeader.
     * 
     * @return resItemCount
     */
    @XmlElement(name = "ResItemCount")
    public Integer getResItemCount() {
	return resItemCount;
    }

    /**
     * Sets the resItemCount value for this CwsDocResHeader.
     * 
     * @param resItemCount Integer
     */
    public void setResItemCount(Integer resItemCount) {
	this.resItemCount = resItemCount;
    }

    /**
     * Gets the jobResInx value for this CwsDocResHeader.
     * 
     * @return jobResInx
     */
    @XmlElement(name = "JobResInx")
    public Integer getJobResInx() {
	return jobResInx;
    }

    /**
     * Sets the jobResInx value for this CwsDocResHeader.
     * 
     * @param jobResInx Integer
     */
    public void setJobResInx(Integer jobResInx) {
	this.jobResInx = jobResInx;
    }

    /**
     * Gets the loaded value for this CwsDocResHeader.
     * 
     * @return loaded
     */
    @XmlElement(name = "Loaded")
    public boolean isLoaded() {
	return loaded;
    }

    /**
     * Sets the loaded value for this CwsDocResHeader.
     * 
     * @param loaded boolean
     */
    public void setLoaded(boolean loaded) {
	this.loaded = loaded;
    }

    /**
     * Gets the heldInSendQ value for this CwsDocResHeader.
     * 
     * @return heldInSendQ
     */
    @XmlElement(name = "HeldInSendQ")
    public boolean isHeldInSendQ() {
	return heldInSendQ;
    }

    /**
     * Sets the heldInSendQ value for this CwsDocResHeader.
     * 
     * @param heldInSendQ boolean
     */
    public void setHeldInSendQ(boolean heldInSendQ) {
	this.heldInSendQ = heldInSendQ;
    }
}
