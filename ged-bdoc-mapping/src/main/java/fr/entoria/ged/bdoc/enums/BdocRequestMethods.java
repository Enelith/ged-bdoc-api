package fr.entoria.ged.bdoc.enums;

public enum BdocRequestMethods {

    VERSION("version"),
    QUERY_FOR_DOCUMENTS("queryForDocuments"),
    LOAD_DOC_CONTENTS("loadDocContents"),
    IS_DOCUMENT_EXIST("isDocumentExist"),
    UPDATE_DOC_INDEXES("updateDocIndexes");

    private final String apiPathURL;

    BdocRequestMethods(String path) {
	this.apiPathURL = path;
    }

    public String getPath() {
	return this.apiPathURL;
    }
}