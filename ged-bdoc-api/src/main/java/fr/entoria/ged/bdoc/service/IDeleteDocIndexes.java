package fr.entoria.ged.bdoc.service;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import fr.entoria.ged.bdoc.api.Token;
import fr.entoria.ged.bdoc.exceptions.InvalidGedBdocApiArgumentsException;
import fr.entoria.ged.bdoc.models.DeleteDocIndexesResponse;
import fr.entoria.ged.bdoc.requests.GedBdocApiRequest;

public interface IDeleteDocIndexes {

    public void validate(GedBdocApiRequest request) throws InvalidGedBdocApiArgumentsException;

    public DeleteDocIndexesResponse execute(GedBdocApiRequest request, Token apiToken, String bdocEndpointUrl)
		throws SOAPException, Exception;

    public SOAPMessage generateSoapRequest(GedBdocApiRequest request, SOAPMessage soapRequest, Token apiToken);
}
