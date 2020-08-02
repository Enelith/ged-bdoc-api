package fr.entoria.ged.bdoc.api;

public class BdocConstants {
    /**
     * BALISES BDOC
     */
    public static String BDOC_BALISE_DOC_TYPE = "DocType";
    public static String BDOC_BALISE_DOC_TITLE = "DocTitle";
    public static String BDOC_BALISE_DOC_CREATE_TIME = "DocCreateTstamp";
    public static String BDOC_BALISE_DOC_CONTENT = "DocContent";
    public static String BDOC_BALISE_DOC_TOTAL_PAGES = "DocTotalPages";
    public static String BDOC_BALISE_DOC_FILE_EXTENSION = "DocFileExt";
    public static String BDOC_BALISE_DOC_PGSET = "DocPgSet";
    public static String BDOC_BALISE_DOC_RES_HEADER = "DocResHeader";
    public static String BDOC_BALISE_DOC_INDEX_LIST = "DocIndexList";

    public static String DIALECT_RASTER_NEVER = "raster(never)";

    /**
     * DOCUMENT SENS
     */
    public static String BDOC_DOCUMENT_SENS_IN = "IN";
    public static String BDOC_DOCUMENT_SENS_OUT = "OUT";

    /**
     * OUTPUTFORMAT
     */
    public static String BDOC_OUTPUTFORMAT_NATIVE = "1";
    public static String BDOC_OUTPUTFORMAT_CYP_DDOC = "2";
    public static String BDOC_OUTPUTFORMAT_PDF = "3";
    public static String BDOC_OUTPUTFORMAT_IMG = "4";
    public static String BDOC_OUTPUTFORMAT_HTML = "7";
    public static String BDOC_OUTPUTFORMAT_TEXT = "8";
    public static String BDOC_OUTPUTFORMAT_XML = "9";
    public static String BDOC_OUTPUTFORMAT_XLS = "11";

    /**
     * EXTENSION
     */
    public static String[] EXTENSION_HTML = {
	    ".htm", ".html" };
    public static String[] EXTENSION_IMG_GIF = {
	    ".gif" };
    public static String[] EXTENSION_IMG_JPEG = {
	    ".jpeg", ".jpg" };
    public static String[] EXTENSION_IMG_PNG = {
	    ".png" };
    public static String[] EXTENSION_PDF = {
	    ".pdf" };

    public static String[] EXTENSION_OPENDOCUMENT_SPREADSHEET = {
	    ".ods" };
    public static String[] EXTENSION_OPENDOCUMENT_TEXT = {
	    ".odt" };

    public static String[] EXTENSION_EXCEL = {
	    ".xls" };
    public static String[] EXTENSION_EXCEL_OPENXML = {
	    ".xlsx" };
    public static String[] EXTENSION_WORD = {
	    ".doc" };
    public static String[] EXTENSION_WORD_OPENXML = {
	    ".docx" };

    /**
     * MIMETYPE
     */
    public static String MIMETYPE_HTML = "text/html";
    public static String MIMETYPE_IMG_GIF = "image/gif";
    public static String MIMETYPE_IMG_JPEG = "image/jpeg";
    public static String MIMETYPE_IMG_PNG = "image/png";
    public static String MIMETYPE_PDF = "application/pdf";

    public static String MIMETYPE_OPENDOCUMENT_SPREADSHEET = "application/vnd.oasis.opendocument.spreadsheet";
    public static String MIMETYPE_OPENDOCUMENT_TEXT = "application/vnd.oasis.opendocument.text";

    public static String MIMETYPE_EXCEL = "application/vnd.ms-excel";
    public static String MIMETYPE_EXCEL_OPENXML = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static String MIMETYPE_WORD = "application/msword";
    public static String MIMETYPE_WORD_OPENXML =
		"application/vnd.openxmlformats-officedocument.wordprocessingml.document";
}
