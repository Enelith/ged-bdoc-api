package fr.entoria.ged.bdoc.models.query;

public class TargetIndex {
    private Integer inxId;
    private String inxVal;

    public TargetIndex() {
	super();
    }

    public TargetIndex(Integer inxId, String inxVal) {
	super();
	this.inxId = inxId;
	this.inxVal = inxVal;
    }

    public Integer getInxId() {
	return inxId;
    }

    public void setInxId(Integer inxId) {
	this.inxId = inxId;
    }

    public String getInxVal() {
	return inxVal;
    }

    public void setInxVal(String inxVal) {
	this.inxVal = inxVal;
    }

}
