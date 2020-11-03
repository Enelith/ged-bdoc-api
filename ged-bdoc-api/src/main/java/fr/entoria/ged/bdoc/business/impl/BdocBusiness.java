package fr.entoria.ged.bdoc.business.impl;

import javax.xml.soap.SOAPException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import fr.entoria.ged.bdoc.api.Token;
import fr.entoria.ged.bdoc.business.IBdocBusiness;
import fr.entoria.ged.bdoc.business.ICaching;
import fr.entoria.ged.bdoc.models.IsDocumentExistResponse;
import fr.entoria.ged.bdoc.models.LoadDocContentsResponse;
import fr.entoria.ged.bdoc.models.QueryForDocumentsResponse;
import fr.entoria.ged.bdoc.requests.GedBdocApiRequest;
import fr.entoria.ged.bdoc.service.IIsDocumentExist;
import fr.entoria.ged.bdoc.service.ILoadDocContents;
import fr.entoria.ged.bdoc.service.IQueryForDocuments;

@Component
public class BdocBusiness implements IBdocBusiness {
    private static final String LOGGER_HEADER = "[" + BdocBusiness.class.getName() + "] ";
    private static final Logger logger = (Logger) LoggerFactory.getLogger(BdocBusiness.class);

    /*
     * @Value("${token.ttl.mins}") private Long ttl;
     */

    @Value("${bdoc.connector.soap.endpoint.url}")
    private String bdocEndpointUrl;

    @Autowired
    ICaching caching;

    @Autowired
    IIsDocumentExist isDocumentExistFunction;

    @Autowired
    ILoadDocContents loadDocContentsFunction;

    @Autowired
    IQueryForDocuments queryForDocumentsFunction;

    private Token retrieveToken(GedBdocApiRequest request) throws SOAPException, Exception {
	Token apiToken = caching.getToken(request, bdocEndpointUrl);
	if (apiToken == null) {
	    // || !apiToken.isValid(ttl)) {
	    logger.warn(LOGGER_HEADER + "Token null");
	} else {
	    logger.info(LOGGER_HEADER + "---> Will use Token UserID : " + apiToken.getValue());
	}
	return apiToken;
    }

    @Override
    public QueryForDocumentsResponse queryForDocuments(GedBdocApiRequest request) throws SOAPException, Exception {
	logger.info(LOGGER_HEADER + "queryForDocuments (QueryForDocumentsResponse) : request = " + request.getQuery());

	Token apiToken = retrieveToken(request);

	return queryForDocumentsFunction.execute(request, apiToken, bdocEndpointUrl);
    }

    @Override
    public LoadDocContentsResponse loadDocContents(GedBdocApiRequest request) throws SOAPException, Exception {
	logger.info(LOGGER_HEADER + "loadDocContents (LoadDocContentsResponse) : targetDocId = " + request.getQuery());

	Token apiToken = retrieveToken(request);

	return loadDocContentsFunction.execute(request, apiToken, bdocEndpointUrl);
    }

    @Override
    public IsDocumentExistResponse isDocumentExist(GedBdocApiRequest request) throws SOAPException, Exception {
	logger.info(LOGGER_HEADER + "isDocumentExist (IsDocumentExistResponse) : DocumentId = " + request.getQuery());

	Token apiToken = retrieveToken(request);

	return isDocumentExistFunction.execute(request, apiToken, bdocEndpointUrl);
    }
}
