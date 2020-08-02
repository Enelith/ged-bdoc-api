package fr.entoria.ged.bdoc.enums;

public enum BdocIndexes {
    AFFILIATION_IDENT("AFFILIATION_IDENT"),
    AGENDA_IDENT("AGENDA_IDENT"),
    BDOC_FICHIER_NOM("BDOC_FICHIER_NOM"),
    CANAL("CANAL"),
    COURTIER_CODE("COURTIER_CODE"),
    DESTINATAIRE_CODE("DESTINATAIRE_CODE"),
    DOCUMENT_CREATION_TIME("Document Creation Time"),
    DOCUMENT_ID_INDEX("DOCUMENT_IDENT"),
    DOCUMENT_SENS("DOCUMENT_SENS"),
    DOCUMENT_SIGNE("DOCUMENT_SIGNE"),
    DOCUMENT_TYPE("DOCUMENT_TYPE"),
    DOMAINE("DOMAINE"),
    GESTIONNAIRE("GESTIONNAIRE"),
    PERSONNE_IDENT("PERSONNE_IDENT"),
    PERSONNE_NOM("PERSONNE_NOM"),
    PERSONNE_PRENOM("PERSONNE_PRENOM"),
    POLICE_NUMERO("POLICE_NUMERO"),
    SECRET_MEDICAL("SECRET_MEDICAL"),
    SED_SOURCE("SED_SOURCE"),
    SED_URL_SUCCESS("SED_URL_SUCCESS"),
    SED_URL_FAIL("SED_URL_FAIL"),
    SED_URL_EXPIRE("SED_URL_EXPIRE"),
    SED_URL_CANCEL("SED_URL_CANCEL"),
    SED_DEMAND_ID("SED_DEMAND_ID"),
    SED_DEMAND_NAME("SED_DEMAND_NAME"),
    SED_SIGNATORY_FIRSTNAME("SED_SIGNATORY_FIRSTNAME"),
    SED_SIGNATORY_LASTNAME("SED_SIGNATORY_LASTNAME"),
    SED_SIGNATORY_EMAIL("SED_SIGNATORY_EMAIL"),
    SED_SIGNATORY_MOBILE("SED_SIGNATORY_MOBILE"),
    SED_STATE("SED_STATE"),
    SED_PROFILE("SED_PROFILE"),
    SIREN("SIREN");

    private String value;

    BdocIndexes(String value) {
	this.value = value;
    }

    public String value() {
	return value;
    }
}
