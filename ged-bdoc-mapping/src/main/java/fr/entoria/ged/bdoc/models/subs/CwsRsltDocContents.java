package fr.entoria.ged.bdoc.models.subs;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import fr.entoria.ged.bdoc.models.arrays.DocIndexes;
import fr.entoria.ged.bdoc.models.arrays.DocMetadata;
import fr.entoria.ged.bdoc.models.arrays.DocPageVariables;
import fr.entoria.ged.bdoc.models.arrays.DocResources;

@XmlType(
	    propOrder = {
		    "docDbase", "docId", "docMetadata", "docPageVariables", "docIndexes", "docResources", "docContent",
		    "docContentFmt" })
@XmlRootElement(name = "CwsRsltDocContents")
public class CwsRsltDocContents implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 5006704442192864596L;

    private String docDbase;

    private Integer docId;

    private DocMetadata docMetadata;

    private DocPageVariables docPageVariables;

    private DocIndexes docIndexes;

    private DocResources docResources;

    private byte[] docContent;

    private String docContentFmt;

    public CwsRsltDocContents() {

    }

    public CwsRsltDocContents(
		String docDbase,
		Integer docId,
		DocMetadata docMetadata,
		DocPageVariables docPageVariables,
		DocResources docResources,
		String docContentFmt) {
	this.docDbase = docDbase;
	this.docId = docId;
	this.docMetadata = docMetadata;
	this.docPageVariables = docPageVariables;
	this.docResources = docResources;
	this.docContentFmt = docContentFmt;
    }

    /**
     * Gets the docDbase value for this CwsRsltDocContents.
     * 
     * @return docDbase
     */
    @XmlElement(name = "DocDbase")
    public String getDocDbase() {
	return docDbase;
    }

    /**
     * Sets the docDbase value for this CwsRsltDocContents.
     * 
     * @param docDbase String
     */
    public void setDocDbase(String docDbase) {
	this.docDbase = docDbase;
    }

    /**
     * Gets the docId value for this CwsRsltDocContents.
     * 
     * @return docId
     */
    @XmlElement(name = "DocId")
    public Integer getDocId() {
	return docId;
    }

    /**
     * Sets the docId value for this CwsRsltDocContents.
     * 
     * @param docId Integer
     */
    public void setDocId(Integer docId) {
	this.docId = docId;
    }

    /**
     * Gets the docMetadata value for this CwsRsltDocContents.
     * 
     * @return docMetadata
     */
    @XmlElement(name = "DocMetadata")
    public DocMetadata getDocMetadata() {
	return docMetadata;
    }

    /**
     * Sets the docMetadata value for this CwsRsltDocContents.
     * 
     * @param docMetadata DocMetadata
     */
    public void setDocMetadata(DocMetadata docMetadata) {
	this.docMetadata = docMetadata;
    }

    /**
     * Gets the docPageVariables value for this CwsRsltDocContents.
     * 
     * @return docPageVariables
     */
    @XmlElement(name = "DocPageVariables")
    public DocPageVariables getDocPageVariables() {
	return docPageVariables;
    }

    /**
     * Sets the docPageVariables value for this CwsRsltDocContents.
     * 
     * @param docPageVariables DocPageVariables
     */
    public void setDocPageVariables(DocPageVariables docPageVariables) {
	this.docPageVariables = docPageVariables;
    }

    /**
     * Gets the docContentFmt value for this CwsRsltDocContents.
     * 
     * @return docContentFmt
     */
    @XmlElement(name = "DocIndexes")
    public DocIndexes getDocIndexes() {
	return docIndexes;
    }

    /**
     * Sets the docIndexes value for this CwsRsltDocContents.
     * 
     * @param docIndexes DocIndexes
     */
    public void setDocIndexes(DocIndexes docIndexes) {
	this.docIndexes = docIndexes;
    }

    /**
     * Gets the docResources value for this CwsRsltDocContents.
     * 
     * @return docResources
     */
    @XmlElement(name = "DocResources")
    public DocResources getDocResources() {
	return docResources;
    }

    /**
     * Sets the docResources value for this CwsRsltDocContents.
     * 
     * @param docResources DocResources
     */
    public void setDocResources(DocResources docResources) {
	this.docResources = docResources;
    }

    /**
     * Gets the docContent value for this CwsRsltDocContents.
     * 
     * @return docContent
     */
    @XmlElement(name = "DocContent")
    public byte[] getDocContent() {
	return docContent;
    }

    /**
     * Sets the docContent value for this CwsRsltDocContents.
     * 
     * @param docContent byte[]
     */
    public void setDocContent(byte[] docContent) {
	this.docContent = docContent;
    }

    /**
     * Gets the docContentFmt value for this CwsRsltDocContents.
     * 
     * @return docContentFmt
     */
    @XmlElement(name = "DocContentFmt")
    public String getDocContentFmt() {
	return docContentFmt;
    }

    /**
     * Sets the docContentFmt value for this CwsRsltDocContents.
     * 
     * @param docContentFmt String
     */
    public void setDocContentFmt(String docContentFmt) {
	this.docContentFmt = docContentFmt;
    }
}