package fr.entoria.ged.bdoc.models.xml;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "INDEXES")
public class Indexes implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -5116144835661055328L;

    private String AGENDA_IDENT;
    private String COURTIER_CODE;
    private String DOCUMENT_SENS;
    private String DOCUMENT_SIGNE;
    private String DOCUMENT_TYPE;
    private String DOMAINE;
    private String PERSONNE_IDENT;
    private String PERSONNE_NOM;
    private String PERSONNE_PRENOM;
    private String POLICE_NUMERO;
    private String SECRET_MEDICAL;
    private String SIREN;
    private String XDATE_DATE_EDITION;

    public Indexes() {
	super();
    }

    public Indexes(String agenda_ident, String document_signe, String courtier_code, String document_sens,
		String document_type,
		String domaine, String personne_ident, String personne_nom, String personne_prenom,
		String police_numero, String secret_medical, String siren, String xdate_date_edition) {
	super();
	this.AGENDA_IDENT = agenda_ident;
	this.DOCUMENT_SIGNE = document_signe;
	this.COURTIER_CODE = courtier_code;
	this.DOCUMENT_SENS = document_sens;
	this.DOCUMENT_TYPE = document_type;
	this.DOMAINE = domaine;
	this.PERSONNE_IDENT = personne_ident;
	this.PERSONNE_NOM = personne_nom;
	this.PERSONNE_PRENOM = personne_prenom;
	this.POLICE_NUMERO = police_numero;
	this.SECRET_MEDICAL = secret_medical;
	this.SIREN = siren;
	this.XDATE_DATE_EDITION = xdate_date_edition;
    }

    /**
     * @return the AGENDA_IDENT
     */
    public String getAGENDA_IDENT() {
	return AGENDA_IDENT;
    }

    /**
     * @param agendaIdent the AGENDA_IDENT to set
     */
    public void setAGENDA_IDENT(String agendaIdent) {
	AGENDA_IDENT = agendaIdent;
    }

    /**
     * @return the COURTIER_CODE
     */
    public String getCOURTIER_CODE() {
	return COURTIER_CODE;
    }

    /**
     * @param courtierCode the COURTIER_CODE to set
     */
    public void setCOURTIER_CODE(String courtierCode) {
	COURTIER_CODE = courtierCode;
    }

    /**
     * @return the DOCUMENT_SENS
     */
    public String getDOCUMENT_SENS() {
	return DOCUMENT_SENS;
    }

    /**
     * @param document_sens the DOCUMENT_SENS to set
     */
    public void setDOCUMENT_SENS(String document_sens) {
	DOCUMENT_SENS = document_sens;
    }

    /**
     * @return the DOCUMENT_SIGNE
     */
    public String getDOCUMENT_SIGNE() {
	return DOCUMENT_SIGNE;
    }

    /**
     * @param document_signe the DOCUMENT_SIGNE to set
     */
    public void setDOCUMENT_SIGNE(String document_signe) {
	DOCUMENT_SIGNE = document_signe;
    }

    /**
     * @return the DOCUMENT_TYPE
     */
    public String getDOCUMENT_TYPE() {
	return DOCUMENT_TYPE;
    }

    /**
     * @param document_type the DOCUMENT_TYPE to set
     */
    public void setDOCUMENT_TYPE(String document_type) {
	DOCUMENT_TYPE = document_type;
    }

    /**
     * @return the DOMAINE
     */
    public String getDOMAINE() {
	return DOMAINE;
    }

    /**
     * @param domaine the DOMAINE to set
     */
    public void setDOMAINE(String domaine) {
	DOMAINE = domaine;
    }

    /**
     * @return the PERSONNE_IDENT
     */
    public String getPERSONNE_IDENT() {
	return PERSONNE_IDENT;
    }

    /**
     * @param personne_ident the PERSONNE_IDENT to set
     */
    public void setPERSONNE_IDENT(String personne_ident) {
	PERSONNE_IDENT = personne_ident;
    }

    /**
     * @return the PERSONNE_NOM
     */
    public String getPERSONNE_NOM() {
	return PERSONNE_NOM;
    }

    /**
     * @param personne_nom the PERSONNE_NOM to set
     */
    public void setPERSONNE_NOM(String personne_nom) {
	PERSONNE_NOM = personne_nom;
    }

    /**
     * @return the PERSONNE_PRENOM
     */
    public String getPERSONNE_PRENOM() {
	return PERSONNE_PRENOM;
    }

    /**
     * @param personne_prenom the PERSONNE_PRENOM to set
     */
    public void setPERSONNE_PRENOM(String personne_prenom) {
	PERSONNE_PRENOM = personne_prenom;
    }

    /**
     * @return the POLICE_NUMERO
     */
    public String getPOLICE_NUMERO() {
	return POLICE_NUMERO;
    }

    /**
     * @param police_numero the POLICE_NUMERO to set
     */
    public void setPOLICE_NUMERO(String police_numero) {
	POLICE_NUMERO = police_numero;
    }

    /**
     * @return the SECRET_MEDICAL
     */
    public String getSECRET_MEDICAL() {
	return SECRET_MEDICAL;
    }

    /**
     * @param secret_medical the SECRET_MEDICAL to set
     */
    public void setSECRET_MEDICAL(String secret_medical) {
	SECRET_MEDICAL = secret_medical;
    }

    /**
     * @return the SIREN
     */
    public String getSIREN() {
	return SIREN;
    }

    /**
     * @param siren the SIREN to set
     */
    public void setSIREN(String siren) {
	SIREN = siren;
    }

    /**
     * @return the XDATE_DATE_EDITION
     */
    public String getXDATE_DATE_EDITION() {
	return XDATE_DATE_EDITION;
    }

    /**
     * @param xdate_date_edition the XDATE_DATE_EDITION to set
     */
    public void setXDATE_DATE_EDITION(String xdate_date_edition) {
	XDATE_DATE_EDITION = xdate_date_edition;
    }
}