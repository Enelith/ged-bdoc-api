package fr.entoria.ged.bdoc.service.impl;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import fr.entoria.ged.bdoc.api.Token;
import fr.entoria.ged.bdoc.business.ICaching;
import fr.entoria.ged.bdoc.models.IsDocumentExistResponse;
import fr.entoria.ged.bdoc.requests.GedBdocApiRequest;
import fr.entoria.ged.bdoc.service.IIsDocumentExist;
import fr.entoria.ged.bdoc.tools.SoapClient;

@Component
@RequestScope
@EnableRetry
public class IsDocumentExist extends AbstractBdocFunctions implements IIsDocumentExist {
    private static final String LOGGER_HEADER = "[" + IsDocumentExist.class.getName() + "] ";
    private static final Logger logger = (Logger) LoggerFactory.getLogger(IsDocumentExist.class);

    private String targetDocId = "0";

    @Value("${bdoc.connector.docuvault}")
    private String docuVault;

    @Value("${soap.call.retry.max.attempts}")
    private String maxAttemptsValue;

    @Autowired
    ICaching caching;

    @Override
    @Retryable(
		value = {
			Exception.class },
		maxAttemptsExpression = "#{${soap.call.retry.max.attempts}}",
		backoff = @Backoff(delay = 1000, maxDelay = 10000, multiplier = 2))
    public IsDocumentExistResponse execute(GedBdocApiRequest request, Token apiToken, String bdocEndpointUrl)
		throws SOAPException, Exception {
	logger.info(LOGGER_HEADER + "##### Executing... (attempt to obtain IsDocumentExistResponse)... #####");

	IsDocumentExistResponse result = null;

	SoapClient soapClient = new SoapClient(bdocEndpointUrl);

	SOAPMessage soapRequest = generateSoapRequest(
		    request,
		    soapClient.getSoapRequest(),
		    apiToken);
	try {
	    result = soapClient.soapConnectionCall(soapRequest,
			IsDocumentExistResponse.class);
	} finally {
	    soapClient.close();
	}

	return result;
    }

    @Recover
    private IsDocumentExistResponse recover(Exception ex, GedBdocApiRequest request, Token apiToken,
		String bdocEndpointUrl) {
	logger.warn(LOGGER_HEADER
		    + "##### Execute failed after {} attempts => Recover by reset apiToken from GedBdoc #####",
		    maxAttemptsValue);

	IsDocumentExistResponse result = null;

	SoapClient soapClient = new SoapClient(bdocEndpointUrl);
	try {
	    apiToken = caching.refreshToken(request, bdocEndpointUrl);

	    SOAPMessage soapRequest = generateSoapRequest(
			request,
			soapClient.getSoapRequest(),
			apiToken);
	    result = soapClient.soapConnectionCall(soapRequest,
			IsDocumentExistResponse.class);
	} catch (SOAPException e) {
	    logger.error(LOGGER_HEADER +
			 "(SOAPException) RECOVER METHOD FAILED - Error occurred while sending SOAP Request to Server : "
			 +
			 e.getMessage());
	} catch (Exception e) {
	    logger.error(LOGGER_HEADER +
			 "(Exception) RECOVER METHOD FAILED - Error occurred while sending SOAP Request to Server : " +
			 e.getMessage());
	} finally {
	    soapClient.close();
	}
	return result;
    }

    @Override
    public SOAPMessage generateSoapRequest(GedBdocApiRequest request, SOAPMessage soapRequest, Token apiToken) {
	// TODO Auto-generated method stub
	logger.debug(LOGGER_HEADER + "call generateSoapRequest");

	this.token = apiToken;

	if (Integer.valueOf(request.getQuery()) > 0) {
	    this.targetDocId = request.getQuery();
	}

	logger.info(LOGGER_HEADER + "Initialize > DocuVault = " + docuVault + " | DocumentId = " + this.targetDocId);

	return process(request, soapRequest);
    }

    @Override
    protected void _buildBody() throws SOAPException {
	logger.debug(LOGGER_HEADER + "call _buildBody");

	/**
	 * DocuVault Block
	 */
	SOAPElement _docuVaultBlock = body.addChildElement("DocuVault", request_prefix);
	_docuVaultBlock.addTextNode(docuVault);

	/**
	 * DocumentId Block
	 */
	SOAPElement _documentIdBlock = body.addChildElement("DocumentId", request_prefix);
	_documentIdBlock.addTextNode(targetDocId);
    }
}
