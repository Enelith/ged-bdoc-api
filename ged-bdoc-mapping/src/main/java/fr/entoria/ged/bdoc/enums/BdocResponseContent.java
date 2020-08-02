package fr.entoria.ged.bdoc.enums;

public enum BdocResponseContent {
    DOCDBASE("DocDbase"),
    DOCID("DocId");

    private String value;

    BdocResponseContent(String value) {
	this.value = value;
    }

    public String value() {
	return value;
    }
}