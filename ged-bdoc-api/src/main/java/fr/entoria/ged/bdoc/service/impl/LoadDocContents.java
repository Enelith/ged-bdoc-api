package fr.entoria.ged.bdoc.service.impl;

import javax.xml.namespace.QName;
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

import fr.entoria.ged.bdoc.api.BdocConstants;
import fr.entoria.ged.bdoc.api.Token;
import fr.entoria.ged.bdoc.business.ICaching;
import fr.entoria.ged.bdoc.models.LoadDocContentsResponse;
import fr.entoria.ged.bdoc.requests.GedBdocApiRequest;
import fr.entoria.ged.bdoc.service.ILoadDocContents;
import fr.entoria.ged.bdoc.tools.SoapClient;

@Component
@RequestScope
@EnableRetry
public class LoadDocContents extends AbstractBdocFunctions implements ILoadDocContents {
    private static final String LOGGER_HEADER = "[" + LoadDocContents.class.getName() + "] ";
    private static final Logger logger = (Logger) LoggerFactory.getLogger(LoadDocContents.class);

    private String targetDocId = "0";

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
    public LoadDocContentsResponse execute(GedBdocApiRequest request, Token apiToken, String bdocEndpointUrl)
		throws SOAPException, Exception {
	logger.info(LOGGER_HEADER + "##### Executing... (attempt to obtain LoadDocContentsResponse)... #####");

	LoadDocContentsResponse result = null;

	SoapClient soapClient = new SoapClient(bdocEndpointUrl);

	SOAPMessage soapRequest = generateSoapRequest(
		    request,
		    soapClient.getSoapRequest(),
		    apiToken);
	try {
	    result = soapClient.soapConnectionCall(soapRequest,
			LoadDocContentsResponse.class);
	} finally {
	    soapClient.close();
	}

	return result;
    }

    @Recover
    private LoadDocContentsResponse recover(Exception ex, GedBdocApiRequest request, Token apiToken,
		String bdocEndpointUrl) {
	logger.warn(LOGGER_HEADER
		    + "##### Execute failed after {} attempts => Recover by reset apiToken from GedBdoc #####",
		    maxAttemptsValue);

	LoadDocContentsResponse result = null;

	SoapClient soapClient = new SoapClient(bdocEndpointUrl);
	try {
	    apiToken = caching.refreshToken(request, bdocEndpointUrl);

	    SOAPMessage soapRequest = generateSoapRequest(
			request,
			soapClient.getSoapRequest(),
			apiToken);
	    result = soapClient.soapConnectionCall(soapRequest,
			LoadDocContentsResponse.class);
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
    public SOAPMessage generateSoapRequest(GedBdocApiRequest gedBdocApiRequest, SOAPMessage soapRequest,
		Token apiToken) {
	logger.debug(LOGGER_HEADER + "call execute");

	this.token = apiToken;

	if (Integer.valueOf(gedBdocApiRequest.getQuery()) > 0) {
	    this.targetDocId = gedBdocApiRequest.getQuery();
	}

	logger.info(LOGGER_HEADER + "Initialize > targetDocId = " + this.targetDocId);

	return process(gedBdocApiRequest, soapRequest);
    }

    @Override
    @SuppressWarnings("unused")
    protected void _buildBody() throws SOAPException {

	logger.debug(LOGGER_HEADER + "call _buildBody");

	/**
	 * ReqDocInfo Block
	 */
	SOAPElement _reqDocInfoBlock = body.addChildElement("ReqDocInfo", request_prefix);

	SOAPElement docId = _reqDocInfoBlock.addChildElement("DocId", request_prefix);
	docId.addTextNode(targetDocId);
	SOAPElement docDbase = _reqDocInfoBlock.addChildElement("DocDbase", request_prefix);
	SOAPElement reqInfo = _reqDocInfoBlock.addChildElement("ReqInfo", request_prefix);

	SOAPElement reqInfo_Option = reqInfo.addChildElement("CWS_REQ_DOC_INFO_ID", request_prefix);
	reqInfo_Option.addTextNode(BdocConstants.BDOC_BALISE_DOC_TITLE);
	reqInfo_Option = reqInfo.addChildElement("CWS_REQ_DOC_INFO_ID", request_prefix);
	reqInfo_Option.addTextNode(BdocConstants.BDOC_BALISE_DOC_TYPE);
	reqInfo_Option = reqInfo.addChildElement("CWS_REQ_DOC_INFO_ID", request_prefix);
	reqInfo_Option.addTextNode(BdocConstants.BDOC_BALISE_DOC_TOTAL_PAGES);
	reqInfo_Option = reqInfo.addChildElement("CWS_REQ_DOC_INFO_ID", request_prefix);
	reqInfo_Option.addTextNode(BdocConstants.BDOC_BALISE_DOC_CREATE_TIME);
	reqInfo_Option = reqInfo.addChildElement("CWS_REQ_DOC_INFO_ID", request_prefix);
	reqInfo_Option.addTextNode(BdocConstants.BDOC_BALISE_DOC_CONTENT);
	reqInfo_Option = reqInfo.addChildElement("CWS_REQ_DOC_INFO_ID", request_prefix);
	reqInfo_Option.addTextNode(BdocConstants.BDOC_BALISE_DOC_PGSET);
	reqInfo_Option = reqInfo.addChildElement("CWS_REQ_DOC_INFO_ID", request_prefix);
	reqInfo_Option.addTextNode(BdocConstants.BDOC_BALISE_DOC_TOTAL_PAGES);
	reqInfo_Option = reqInfo.addChildElement("CWS_REQ_DOC_INFO_ID", request_prefix);
	reqInfo_Option.addTextNode(BdocConstants.BDOC_BALISE_DOC_RES_HEADER);
	reqInfo_Option = reqInfo.addChildElement("CWS_REQ_DOC_INFO_ID", request_prefix);
	reqInfo_Option.addTextNode(BdocConstants.BDOC_BALISE_DOC_FILE_EXTENSION);

	SOAPElement reqInx = _reqDocInfoBlock.addChildElement("ReqInx", request_prefix);
	SOAPElement reqVars = _reqDocInfoBlock.addChildElement("ReqVars", request_prefix);

	/**
	 * ReqOptQry Block
	 */
	SOAPElement _reqOptDocBlock = body.addChildElement("ReqOptDoc", request_prefix);

	SOAPElement options = _reqOptDocBlock.addChildElement("Options", request_prefix);

	SOAPElement cwsReqOptDocItem = options.addChildElement("CwsReqOptDocItem", request_prefix);

	SOAPElement cwsReqOptDocItem_Option = cwsReqOptDocItem.addChildElement("Id", request_prefix);
	cwsReqOptDocItem_Option.addTextNode("OutputFormat");

	cwsReqOptDocItem_Option = cwsReqOptDocItem.addChildElement("Val", request_prefix);
	cwsReqOptDocItem_Option.addAttribute(new QName("http://www.w3.org/2001/XMLSchema", "xmlns", "xs"),
		    "http://www.w3.org/2001/XMLSchema");
	cwsReqOptDocItem_Option.addAttribute(new QName("http://www.w3.org/2001/XMLSchema-instance", "xmlns", "xsi"),
		    "http://www.w3.org/2001/XMLSchema-instance");
	cwsReqOptDocItem_Option.addAttribute(new QName("", "type", "xsi"), "xs:int");
	cwsReqOptDocItem_Option.addTextNode(BdocConstants.BDOC_OUTPUTFORMAT_PDF);

	cwsReqOptDocItem = options.addChildElement("CwsReqOptDocItem", request_prefix);

	cwsReqOptDocItem_Option = cwsReqOptDocItem.addChildElement("Id", request_prefix);
	cwsReqOptDocItem_Option.addTextNode("Dialect");

	cwsReqOptDocItem_Option = cwsReqOptDocItem.addChildElement("Val", request_prefix);
	cwsReqOptDocItem_Option.addAttribute(new QName("http://www.w3.org/2001/XMLSchema", "xmlns", "xs"),
		    "http://www.w3.org/2001/XMLSchema");
	cwsReqOptDocItem_Option.addAttribute(new QName("http://www.w3.org/2001/XMLSchema-instance", "xmlns", "xsi"),
		    "http://www.w3.org/2001/XMLSchema-instance");
	cwsReqOptDocItem_Option.addAttribute(new QName("", "type", "xsi"), "xs:string");
	cwsReqOptDocItem_Option.addTextNode(BdocConstants.DIALECT_RASTER_NEVER);
    }
}