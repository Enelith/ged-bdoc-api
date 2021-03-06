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
import org.springframework.util.StringUtils;
import org.springframework.web.context.annotation.RequestScope;

import fr.entoria.ged.bdoc.api.BdocConstants;
import fr.entoria.ged.bdoc.api.Token;
import fr.entoria.ged.bdoc.business.ICaching;
import fr.entoria.ged.bdoc.enums.BdocQueryOptions;
import fr.entoria.ged.bdoc.models.QueryForDocumentsResponse;
import fr.entoria.ged.bdoc.requests.GedBdocApiRequest;
import fr.entoria.ged.bdoc.service.IQueryForDocuments;
import fr.entoria.ged.bdoc.tools.SoapClient;

@Component
@RequestScope
@EnableRetry
public class QueryForDocuments extends BdocFunctions implements IQueryForDocuments {
    private static final String LOGGER_HEADER = "[" + QueryForDocuments.class.getName() + "] ";
    private static final Logger logger = (Logger) LoggerFactory.getLogger(QueryForDocuments.class);

    private String queryMandatory = "[Document System Type] = 1";
    private String queryCriteria = queryMandatory + " AND [Document Logical Page Count] < 0";

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
    public QueryForDocumentsResponse execute(GedBdocApiRequest request, Token apiToken, String bdocEndpointUrl)
		throws SOAPException, Exception {
	logger.info(LOGGER_HEADER + "##### Executing... (attempt to obtain QueryForDocumentsResponse)... #####");

	QueryForDocumentsResponse result = null;

	SoapClient soapClient = new SoapClient(bdocEndpointUrl);

	SOAPMessage soapRequest = generateSoapRequest(
		    request,
		    soapClient.getSoapRequest(),
		    apiToken);
	try {
	    result = soapClient.soapConnectionCall(soapRequest,
			QueryForDocumentsResponse.class);
	} finally {
	    soapClient.close();
	}

	return result;
    }

    @Recover
    private QueryForDocumentsResponse recover(Exception ex, GedBdocApiRequest request, Token apiToken,
		String bdocEndpointUrl) throws SOAPException, Exception {
	logger.warn(LOGGER_HEADER
		    + "##### Execute failed after {} attempts => Recover by reset apiToken from GedBdoc #####",
		    maxAttemptsValue);

	QueryForDocumentsResponse result = null;

	SoapClient soapClient = new SoapClient(bdocEndpointUrl);
	try {
	    apiToken = caching.refreshToken(request, bdocEndpointUrl);

	    SOAPMessage soapRequest = generateSoapRequest(
			request,
			soapClient.getSoapRequest(),
			apiToken);
	    result = soapClient.soapConnectionCall(soapRequest,
			QueryForDocumentsResponse.class);
	} catch (SOAPException e) {
	    logger.error(LOGGER_HEADER +
			 "(SOAPException) RECOVER METHOD FAILED - Error occurred while sending SOAP Request to Server : "
			 +
			 e.getMessage());
	    throw e;
	} catch (Exception e) {
	    logger.error(LOGGER_HEADER +
			 "(Exception) RECOVER METHOD FAILED - Error occurred while sending SOAP Request to Server : " +
			 e.getMessage());
	    throw e;
	} finally {
	    soapClient.close();
	}
	return result;
    }

    @Override
    public SOAPMessage generateSoapRequest(GedBdocApiRequest gedBdocApiRequest, SOAPMessage soapRequest,
		Token apiToken) {
	logger.debug(LOGGER_HEADER + "call execute");

	this.token = apiToken;

	if (!StringUtils.isEmpty(gedBdocApiRequest.getQuery())) {
	    try {
		if (gedBdocApiRequest.getOptions().containsKey(BdocQueryOptions.DOCUMENT_SYSTEM_TYPE)
			    && Integer.parseInt(gedBdocApiRequest.getOptions()
					.get(BdocQueryOptions.DOCUMENT_SYSTEM_TYPE)
					.toString()) > 0) {
		    this.queryCriteria = "[Document System Type] = " + Integer.valueOf(
				gedBdocApiRequest.getOptions().get(BdocQueryOptions.DOCUMENT_SYSTEM_TYPE).toString());
		} else {
		    this.queryCriteria = this.queryMandatory;
		}
	    } catch (NumberFormatException e) {
		this.queryCriteria = this.queryMandatory;
	    }
	    this.queryCriteria += " AND " + gedBdocApiRequest.getQuery();
	}

	logger.info(LOGGER_HEADER + "Initialize > queryCriteria = " + this.queryCriteria);

	return process(gedBdocApiRequest, soapRequest);
    }

    @Override
    @SuppressWarnings("unused")
    protected void _buildBody() throws SOAPException {
	logger.debug(LOGGER_HEADER + "call _buildBody");

	/**
	 * QueryCriteria Block
	 */
	if (!StringUtils.isEmpty(queryCriteria)) {
	    SOAPElement queryCriteraBlock = body.addChildElement("QueryCriteria", request_prefix);
	    queryCriteraBlock.addTextNode(queryCriteria);
	}

	/**
	 * ReqDocInfo Block
	 */
	SOAPElement _reqDocInfoBlock = body.addChildElement("ReqDocInfo", request_prefix);

	SOAPElement docId = _reqDocInfoBlock.addChildElement("DocId", request_prefix);
	docId.addTextNode("0");
	SOAPElement docDbase = _reqDocInfoBlock.addChildElement("DocDbase", request_prefix);
	SOAPElement reqInfo = _reqDocInfoBlock.addChildElement("ReqInfo", request_prefix);

	SOAPElement reqInfo_Option = reqInfo.addChildElement("CWS_REQ_DOC_INFO_ID", request_prefix);
	reqInfo_Option.addTextNode(BdocConstants.BDOC_BALISE_DOC_TYPE);
	reqInfo_Option = reqInfo.addChildElement("CWS_REQ_DOC_INFO_ID", request_prefix);
	reqInfo_Option.addTextNode(BdocConstants.BDOC_BALISE_DOC_TITLE);
	reqInfo_Option = reqInfo.addChildElement("CWS_REQ_DOC_INFO_ID", request_prefix);
	reqInfo_Option.addTextNode(BdocConstants.BDOC_BALISE_DOC_CREATE_TIME);
	reqInfo_Option = reqInfo.addChildElement("CWS_REQ_DOC_INFO_ID", request_prefix);
	reqInfo_Option.addTextNode(BdocConstants.BDOC_BALISE_DOC_TOTAL_PAGES);
	reqInfo_Option = reqInfo.addChildElement("CWS_REQ_DOC_INFO_ID", request_prefix);
	reqInfo_Option.addTextNode(BdocConstants.BDOC_BALISE_DOC_CONTENT);
	reqInfo_Option = reqInfo.addChildElement("CWS_REQ_DOC_INFO_ID", request_prefix);
	reqInfo_Option.addTextNode(BdocConstants.BDOC_BALISE_DOC_INDEX_LIST);

	SOAPElement reqInx = _reqDocInfoBlock.addChildElement("ReqInx", request_prefix);
	SOAPElement reqVars = _reqDocInfoBlock.addChildElement("ReqVars", request_prefix);

	/**
	 * ReqOptQry Block
	 */
	SOAPElement _reqOptQryBlock = body.addChildElement("ReqOptQry", request_prefix);

	SOAPElement options = _reqOptQryBlock.addChildElement("Options", request_prefix);
    }
}
