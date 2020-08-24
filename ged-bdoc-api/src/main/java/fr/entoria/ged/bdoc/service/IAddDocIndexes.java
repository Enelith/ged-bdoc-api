package fr.entoria.ged.bdoc.service;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import fr.entoria.ged.bdoc.api.Token;
import fr.entoria.ged.bdoc.exceptions.InvalidGedBdocApiArgumentsException;
import fr.entoria.ged.bdoc.models.AddDocIndexesResponse;
import fr.entoria.ged.bdoc.requests.GedBdocApiRequest;

public interface IAddDocIndexes {

    public void validate(GedBdocApiRequest request) throws InvalidGedBdocApiArgumentsException;

    public AddDocIndexesResponse execute(GedBdocApiRequest request, Token apiToken, String bdocEndpointUrl)
		throws SOAPException, Exception;

    public SOAPMessage generateSoapRequest(GedBdocApiRequest request, SOAPMessage soapRequest, Token apiToken);
}
