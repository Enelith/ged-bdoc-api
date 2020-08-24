package fr.entoria.ged.bdoc.enums;

public enum BdocUpdtScopeOnIndexes {
    DOC_LEVEL_UPDATE("DocLevelUpdate"),
    PAGE_LEVEL_UPDATE("PageLevelUpdate");

    private String value;

    private BdocUpdtScopeOnIndexes(String value) {
	this.value = value;
    }

    public String getValue() {
	return value;
    }

    public void setValue(String value) {
	this.value = value;
    }
}
