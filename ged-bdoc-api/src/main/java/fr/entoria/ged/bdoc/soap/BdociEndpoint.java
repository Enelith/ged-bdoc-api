package fr.entoria.ged.bdoc.soap;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ch.qos.logback.classic.Logger;
import fr.entoria.ged.bdoc.business.IBdocBusiness;
import fr.entoria.ged.bdoc.soap.models.getDocumentById.GetDocumentById;
import fr.entoria.ged.bdoc.soap.models.getDocumentById.GetDocumentByIdResponse;
import fr.entoria.ged.bdoc.soap.models.searchDocuments.GedDocument;
import fr.entoria.ged.bdoc.soap.models.searchDocuments.MapItem;
import fr.entoria.ged.bdoc.soap.models.searchDocuments.SearchDocuments;
import fr.entoria.ged.bdoc.soap.models.searchDocuments.SearchDocumentsResponse;

@Endpoint
public class BdociEndpoint {
    private static Logger logger = (Logger) LoggerFactory.getLogger(BdociEndpoint.class);

    @Autowired
    IBdocBusiness bdocBusiness;

    @Autowired
    public BdociEndpoint() {

    }

    @PayloadRoot(
		namespace = "http://wsimpl.ged.business.bdocinteractive.bdoc.com",
		localPart = "searchDocuments")
    @ResponsePayload
    public SearchDocumentsResponse searchDocuments(@RequestPayload SearchDocuments request) {
	System.out.println(request);

	if (request != null
		    && request.getParams() != null
		    && !CollectionUtils.isEmpty(request.getParams().getItem())) {
	    for (MapItem i : request.getParams().getItem()) {
		System.out.println(i.getKey() + " => " + i.getValue());
	    }
	} else {
	    System.out.println("IS NULL");
	}

	SearchDocumentsResponse result = new SearchDocumentsResponse();

	int i = 0;
	while (i++ < 5) {

	    GedDocument gd = new GedDocument();
	    gd.setId(String.valueOf(i));
	    gd.setTitle("GedDocument-Mock-" + i);

	    try {
		Date d = new Date();
		GregorianCalendar gregory = new GregorianCalendar();
		gregory.setTime(d);
		XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);

		gd.setCreationDate(date2);
	    } catch (Exception e) {
		System.err.println("Error init GregorianCalendar");
	    }

	    result.getSearchDocumentsReturn().add(gd);
	}
	return result;
    }

    @PayloadRoot(
		namespace = "http://ged.business.bdocinteractive.bdoc.com",
		localPart = "getDocumentById")
    @ResponsePayload
    public GetDocumentByIdResponse getDocumentById(@RequestPayload GetDocumentById request) {

	String id = request.getId();

	fr.entoria.ged.bdoc.soap.models.getDocumentById.GedDocument gedDocumentResult =
		    new fr.entoria.ged.bdoc.soap.models.getDocumentById.GedDocument();
	gedDocumentResult.setId(id);
	gedDocumentResult.setTitle("GedDocument-Mock-" + id);

	try {
	    Date d = new Date();
	    GregorianCalendar gregory = new GregorianCalendar();
	    gregory.setTime(d);
	    XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);

	    gedDocumentResult.setCreationDate(date2);
	} catch (Exception e) {
	    System.err.println("Error init GregorianCalendar");
	}

	GetDocumentByIdResponse response = new GetDocumentByIdResponse();
	response.setGetDocumentByIdReturn(gedDocumentResult);

	/***********************************************/

	/*
	GedBdocApiRequest gedBdocApiRequest = new GedBdocApiRequest();
	gedBdocApiRequest.setQuery(request.getId());

	try {
	    LoadDocContentsResponse loadDocContentsResponse = bdocBusiness.loadDocContents(gedBdocApiRequest);
	    CwsRsltDocContents rsltDocContents = loadDocContentsResponse.getLoadDocContentsResult();
	    
	    rsltDocContents.getDocMetadata().getCwsDocMetadatas();
	    CwsDocMetadata tmp;
	    
	    gedDocumentResult.setAuthor(value);
	    gedDocumentResult.setContent(rsltDocContents.getDocContent());
	    gedDocumentResult.setCreationDate(value); // BdocIndexes.DOCUMENT_CREATION_TIME;
	    gedDocumentResult.setId(String.valueOf(rsltDocContents.getDocId()));
	    gedDocumentResult.setMetainfo(value);
	    gedDocumentResult.setMimeType(value);
	    gedDocumentResult.setTitle(value);
	    
	    
	    
	} catch (SOAPException e) {
	    logger.error("(SOAPException) Error loadDocContents", e);
	} catch (UndeclaredThrowableException e) {
	    logger.error("(UndeclaredThrowableException) Error queryForDocuments", e);
	} catch (Exception e) {
	    logger.error("(Exception) Error loadDocContents", e);
	}
	*/

	return response;
    }

}
