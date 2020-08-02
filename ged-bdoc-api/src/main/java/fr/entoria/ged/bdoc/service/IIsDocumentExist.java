package fr.entoria.ged.bdoc.service;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import fr.entoria.ged.bdoc.api.Token;
import fr.entoria.ged.bdoc.models.IsDocumentExistResponse;
import fr.entoria.ged.bdoc.requests.GedBdocApiRequest;

public interface IIsDocumentExist {

    public IsDocumentExistResponse execute(GedBdocApiRequest request, Token apiToken, String bdocEndpointUrl)
		throws SOAPException, Exception;

    public SOAPMessage generateSoapRequest(GedBdocApiRequest request, SOAPMessage soapRequest, Token apiToken);
}
