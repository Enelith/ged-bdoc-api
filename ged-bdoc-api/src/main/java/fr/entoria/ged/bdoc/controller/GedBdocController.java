package fr.entoria.ged.bdoc.controller;

import java.lang.reflect.UndeclaredThrowableException;

import javax.xml.soap.SOAPException;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.Logger;
import fr.entoria.ged.bdoc.business.IBdocBusiness;
import fr.entoria.ged.bdoc.exceptions.InvalidGedBdocApiArgumentsException;
import fr.entoria.ged.bdoc.requests.GedBdocApiRequest;
import fr.entoria.ged.bdoc.response.GedBdocApiResponse;

@RestController
@RequestMapping("/")
public class GedBdocController {
    private static Logger logger = (Logger) LoggerFactory.getLogger(GedBdocController.class);

    @Value("${build.version}")
    private String version;

    @Autowired
    IBdocBusiness bdocBusiness;

    @GetMapping(path = "version")
    public ResponseEntity<?> version() {
	logger.info("version path called");

	GedBdocApiResponse response = new GedBdocApiResponse();
	response.setCode(HttpStatus.OK);
	response.setResult(version);

	return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "queryForDocuments")
    public ResponseEntity<?> queryForDocuments(@RequestBody GedBdocApiRequest request) {
	GedBdocApiResponse response = new GedBdocApiResponse();
	response.setCode(HttpStatus.INTERNAL_SERVER_ERROR);

	try {
	    response.setResult(bdocBusiness.queryForDocuments(request));
	    response.setCode(HttpStatus.OK);
	} catch (SOAPException e) {
	    logger.error("(SOAPException) Error queryForDocuments", e);
	    response.setMessage(e.getMessage());
	} catch (UndeclaredThrowableException e) {
	    logger.error("(UndeclaredThrowableException) Error queryForDocuments", e);
	    response.setMessage("Erreur de communication avec Cypress");
	} catch (Exception e) {
	    logger.error("(Exception) Error queryForDocuments", e);
	    response.setMessage(e.getMessage());
	}

	return new ResponseEntity<>(response, response.getCode());
    }

    @PostMapping(path = "loadDocContents")
    public ResponseEntity<?> loadDocContents(@RequestBody GedBdocApiRequest request) {
	GedBdocApiResponse response = new GedBdocApiResponse();
	response.setCode(HttpStatus.INTERNAL_SERVER_ERROR);

	try {
	    response.setResult(bdocBusiness.loadDocContents(request));
	    response.setCode(HttpStatus.OK);
	} catch (SOAPException e) {
	    logger.error("(SOAPException) Error loadDocContents", e);
	    response.setMessage(e.getMessage());
	} catch (UndeclaredThrowableException e) {
	    logger.error("(UndeclaredThrowableException) Error queryForDocuments", e);
	    response.setMessage("Erreur de communication avec Cypress");
	} catch (Exception e) {
	    logger.error("(Exception) Error loadDocContents", e);
	    response.setMessage(e.getMessage());
	}

	return new ResponseEntity<>(response, response.getCode());
    }

    @PostMapping(path = "isDocumentExist")
    public ResponseEntity<?> isDocumentExist(@RequestBody GedBdocApiRequest request) {
	GedBdocApiResponse response = new GedBdocApiResponse();
	response.setCode(HttpStatus.INTERNAL_SERVER_ERROR);

	try {
	    response.setResult(bdocBusiness.isDocumentExist(request));
	    response.setCode(HttpStatus.OK);
	} catch (SOAPException e) {
	    logger.error("(SOAPException) Error isDocumentExist", e);
	    response.setMessage(e.getMessage());
	} catch (UndeclaredThrowableException e) {
	    logger.error("(UndeclaredThrowableException) Error queryForDocuments", e);
	    response.setMessage("Erreur de communication avec Cypress");
	} catch (Exception e) {
	    logger.error("(Exception) Error isDocumentExist", e);
	    response.setMessage(e.getMessage());
	}

	return new ResponseEntity<>(response, response.getCode());
    }

    @PostMapping(path = "updateDocIndexes")
    public ResponseEntity<?> updateDocIndexes(@RequestBody GedBdocApiRequest request) {
	GedBdocApiResponse response = new GedBdocApiResponse();
	response.setCode(HttpStatus.NOT_IMPLEMENTED);

	/*
	 * try { response.setResult(bdocBusiness.loadDocContents(request));
	 * response.setCode(HttpStatus.OK); } catch (SOAPException e) {
	 * logger.error("(SOAPException) Error getList", e);
	 * response.setMessage(e.getMessage());
	 * response.setCode(HttpStatus.INTERNAL_SERVER_ERROR); } catch (Exception e) {
	 * logger.error("(Exception) Error getList", e);
	 * response.setMessage(e.getMessage());
	 * response.setCode(HttpStatus.INTERNAL_SERVER_ERROR); }
	 */

	return new ResponseEntity<>(response, response.getCode());
    }

    @PostMapping(path = "addDocIndexes")
    public ResponseEntity<?> addDocIndexes(@RequestBody GedBdocApiRequest request) {
	GedBdocApiResponse response = new GedBdocApiResponse();
	response.setCode(HttpStatus.INTERNAL_SERVER_ERROR);

	try {
	    response.setResult(bdocBusiness.addDocIndexes(request));
	    response.setCode(HttpStatus.OK);
	} catch (SOAPException e) {
	    logger.error("(SOAPException) Error addDocIndexes", e);
	    response.setMessage(e.getMessage());
	} catch (UndeclaredThrowableException e) {
	    logger.error("(UndeclaredThrowableException) Error addDocIndexes", e);
	    response.setMessage("Erreur de communication avec Cypress");
	} catch (InvalidGedBdocApiArgumentsException e) {
	    logger.error("(InvalidGedBdocApiArgumentsException) Error addDocIndexes : " + e.getMessage());
	    response.setMessage(e.getMessage());
	} catch (Exception e) {
	    logger.error("(Exception) Error addDocIndexes", e);
	    response.setMessage(e.getMessage());
	}

	return new ResponseEntity<>(response, response.getCode());
    }

    @PostMapping(path = "deleteDocIndexes")
    public ResponseEntity<?> deleteDocIndexes(@RequestBody GedBdocApiRequest request) {
	GedBdocApiResponse response = new GedBdocApiResponse();
	response.setCode(HttpStatus.INTERNAL_SERVER_ERROR);

	try {
	    response.setResult(bdocBusiness.deleteDocIndexes(request));
	    response.setCode(HttpStatus.OK);
	} catch (SOAPException e) {
	    logger.error("(SOAPException) Error deleteDocIndexes", e);
	    response.setMessage(e.getMessage());
	} catch (UndeclaredThrowableException e) {
	    logger.error("(UndeclaredThrowableException) Error deleteDocIndexes", e);
	    response.setMessage("Erreur de communication avec Cypress");
	} catch (InvalidGedBdocApiArgumentsException e) {
	    logger.error("(InvalidGedBdocApiArgumentsException) Error deleteDocIndexes");
	    response.setMessage(e.getMessage());
	} catch (Exception e) {
	    logger.error("(Exception) Error addDocIndexes", e);
	    response.setMessage(e.getMessage());
	}

	return new ResponseEntity<>(response, response.getCode());
    }
}