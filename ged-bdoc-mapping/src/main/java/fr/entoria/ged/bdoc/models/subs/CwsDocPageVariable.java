package fr.entoria.ged.bdoc.models.subs;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CwsDocPageVariable")
public class CwsDocPageVariable implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 8266770081068473152L;

    private Integer pageNo;

    private String name;

    private String val;

    public CwsDocPageVariable() {
    }

    public CwsDocPageVariable(
		Integer pageNo,
		String name,
		String val) {
	this.pageNo = pageNo;
	this.name = name;
	this.val = val;
    }

    /**
     * Gets the pageNo value for this CwsDocPageVariable.
     * 
     * @return pageNo
     */
    @XmlElement(name = "PageNo")
    public Integer getPageNo() {
	return pageNo;
    }

    /**
     * Sets the pageNo value for this CwsDocPageVariable.
     * 
     * @param pageNo Integer pageNo
     */
    public void setPageNo(Integer pageNo) {
	this.pageNo = pageNo;
    }

    /**
     * Gets the name value for this CwsDocPageVariable.
     * 
     * @return name
     */
    @XmlElement(name = "Name")
    public String getName() {
	return name;
    }

    /**
     * Sets the name value for this CwsDocPageVariable.
     * 
     * @param name String
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * Gets the val value for this CwsDocPageVariable.
     * 
     * @return val
     */
    @XmlElement(name = "Val")
    public String getVal() {
	return val;
    }

    /**
     * Sets the val value for this CwsDocPageVariable.
     * 
     * @param val String
     */
    public void setVal(String val) {
	this.val = val;
    }
}