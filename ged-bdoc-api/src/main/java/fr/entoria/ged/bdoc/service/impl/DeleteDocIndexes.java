package fr.entoria.ged.bdoc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.annotation.RequestScope;

import fr.entoria.ged.bdoc.api.Token;
import fr.entoria.ged.bdoc.business.ICaching;
import fr.entoria.ged.bdoc.enums.BdocUpdtScopeOnIndexes;
import fr.entoria.ged.bdoc.exceptions.InvalidGedBdocApiArgumentsException;
import fr.entoria.ged.bdoc.models.DeleteDocIndexesResponse;
import fr.entoria.ged.bdoc.models.query.ActionOnIndexes;
import fr.entoria.ged.bdoc.models.query.TargetIndex;
import fr.entoria.ged.bdoc.requests.GedBdocApiRequest;
import fr.entoria.ged.bdoc.service.IDeleteDocIndexes;
import fr.entoria.ged.bdoc.tools.SoapClient;

@Component
@RequestScope
@EnableRetry
public class DeleteDocIndexes extends AbstractBdocFunctions implements IDeleteDocIndexes {
    private static final String LOGGER_HEADER = "[" + DeleteDocIndexes.class.getName() + "] ";
    private static final Logger logger = (Logger) LoggerFactory.getLogger(DeleteDocIndexes.class);

    private List<ActionOnIndexes> actionsOnIndexes = new ArrayList<>();

    @Value("${soap.call.retry.max.attempts}")
    private String maxAttemptsValue;

    @Autowired
    ICaching caching;

    @Override
    public void validate(GedBdocApiRequest request) throws InvalidGedBdocApiArgumentsException {
	logger.info(LOGGER_HEADER + "call (DeleteDocIndexes) validate");

	StringJoiner err = new StringJoiner(" ; ");
	if (!CollectionUtils.isEmpty(request.getActionsOnIndexes())) {
	    Integer originalSize = request.getActionsOnIndexes().size();
	    this.actionsOnIndexes = request.getActionsOnIndexes()
			.stream()
			.filter(aoi -> !CollectionUtils.isEmpty(aoi.getTargetDocumentsId())
				    && !CollectionUtils.isEmpty(aoi.getTargetIndexes()))
			.collect(Collectors.toList());
	    if (!CollectionUtils.isEmpty(this.actionsOnIndexes)) {
		logger.info(LOGGER_HEADER + "ActionsOnIndexes collection size of "
			    + this.actionsOnIndexes.size() + " / " + originalSize + " (original size)");
	    } else {
		err.add("Entity (List) ActionsOnIndexes is not properly set ~ neither TargetDocumentsId nor TargetIndexes have been defined");
	    }
	} else {
	    err.add("Entity (List) ActionsOnIndexes missing or undefined");
	}

	if (!StringUtils.isEmpty(err.toString())) {
	    throw new InvalidGedBdocApiArgumentsException(HttpStatus.NOT_ACCEPTABLE.toString(),
			err.toString());
	}
    }

    @Override
    @Retryable(
		value = {
			Exception.class },
		maxAttemptsExpression = "#{${soap.call.retry.max.attempts}}",
		backoff = @Backoff(delay = 1000, maxDelay = 10000, multiplier = 2))
    public DeleteDocIndexesResponse execute(GedBdocApiRequest request, Token apiToken, String bdocEndpointUrl)
		throws SOAPException, Exception {
	logger.info(LOGGER_HEADER + "##### Executing... (attempt to obtain DeleteDocIndexesResponse)... #####");

	DeleteDocIndexesResponse result = null;

	SoapClient soapClient = new SoapClient(bdocEndpointUrl);

	SOAPMessage soapRequest = generateSoapRequest(
		    request,
		    soapClient.getSoapRequest(),
		    apiToken);
	try {
	    result = soapClient.soapConnectionCall(soapRequest,
			DeleteDocIndexesResponse.class);
	} finally {
	    soapClient.close();
	}

	return result;
    }

    @Recover
    private DeleteDocIndexesResponse recover(Exception ex, GedBdocApiRequest request, Token apiToken,
		String bdocEndpointUrl) throws SOAPException, Exception {
	logger.warn(LOGGER_HEADER
		    + "##### Execute failed after {} attempts => Recover by reset apiToken from GedBdoc #####",
		    maxAttemptsValue);

	DeleteDocIndexesResponse result = null;

	SoapClient soapClient = new SoapClient(bdocEndpointUrl);
	try {
	    apiToken = caching.refreshToken(request, bdocEndpointUrl);

	    SOAPMessage soapRequest = generateSoapRequest(
			request,
			soapClient.getSoapRequest(),
			apiToken);
	    result = soapClient.soapConnectionCall(soapRequest,
			DeleteDocIndexesResponse.class);
	} catch (SOAPException e) {
	    logger.error(LOGGER_HEADER +
			 "(SOAPException) RECOVER METHOD FAILED - Error occurred while sending SOAP Request to Server : "
			 +
			 e.getMessage());
	    throw e;
	} catch (InvalidGedBdocApiArgumentsException e) {
	    logger.error(LOGGER_HEADER +
			 "(InvalidGedBdocApiArgumentsException) Invalids arguments for DeleteDocIndexes action : "
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

	return process(gedBdocApiRequest, soapRequest);
    }

    @Override
    protected void _buildBody() throws SOAPException {
	logger.debug(LOGGER_HEADER + "call _buildBody");

	/**
	 * ReqDeleteDocInx Block
	 */
	SOAPElement _ReqDeleteDocInx = body.addChildElement("ReqDeleteDocInx", request_prefix);

	SOAPElement _DeleteActnList = _ReqDeleteDocInx.addChildElement("DeleteActnList", request_prefix);
	_DeleteActnList.addAttribute(new QName("", "type", "xsi"), "cyp:CwsDeleteIndexActn");

	for (ActionOnIndexes aoi : this.actionsOnIndexes) {
	    SOAPElement _CwsDeleteIndexActn = _DeleteActnList.addChildElement("CwsDeleteIndexActn", request_prefix);

	    SOAPElement _UseTempToCvtVals = _CwsDeleteIndexActn.addChildElement("UseTempToCvtVals", request_prefix);
	    _UseTempToCvtVals.addTextNode("false");
	    SOAPElement _UpdtScope = _CwsDeleteIndexActn.addChildElement("UpdtScope", request_prefix);
	    _UpdtScope.addTextNode(BdocUpdtScopeOnIndexes.DOC_LEVEL_UPDATE.getValue());

	    // Init InxList
	    SOAPElement _InxList = _CwsDeleteIndexActn.addChildElement("InxList", request_prefix);
	    for (TargetIndex ti : aoi.getTargetIndexes()) {
		SOAPElement _CwsDelInxVal = _InxList.addChildElement("CwsDelInxVal", request_prefix);

		SOAPElement _InxId = _CwsDelInxVal.addChildElement("InxId", request_prefix);
		_InxId.addTextNode(String.valueOf(ti.getInxId()));

		SOAPElement _InxVal = _CwsDelInxVal.addChildElement("InxVal", request_prefix);
		_InxVal.addAttribute(new QName("", "type", "xsi"), "xsd:string");
		_InxVal.addTextNode(String.valueOf(ti.getInxVal()));
	    }

	    // Init TgtDocs
	    SOAPElement _TgtDocs = _CwsDeleteIndexActn.addChildElement("TgtDocs", request_prefix);
	    SOAPElement _DdocIds = _TgtDocs.addChildElement("DdocIds", request_prefix);
	    for (Integer docId : aoi.getTargetDocumentsId()) {
		SOAPElement _unsignedInt = _DdocIds.addChildElement("unsignedInt", request_prefix);
		_unsignedInt.addTextNode(String.valueOf(docId));
	    }
	}
    }
}
