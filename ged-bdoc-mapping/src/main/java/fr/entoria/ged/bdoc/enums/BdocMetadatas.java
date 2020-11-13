package fr.entoria.ged.bdoc.enums;

public enum BdocMetadatas {
    DOC_TYPE("DocType"),
    DOC_TITLE("DocTitle"),
    CREATE_TIME("DocCreateTstamp"),
    TOTAL_PAGES("DocTotalPages")
    ;

    private String value;
    
    BdocMetadatas(String value) {
	this.value = value;
    }
    
    public String value() {
	return value;
    }
}
