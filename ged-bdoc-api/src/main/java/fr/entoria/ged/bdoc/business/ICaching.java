package fr.entoria.ged.bdoc.business;

import javax.xml.soap.SOAPException;

import fr.entoria.ged.bdoc.api.Token;
import fr.entoria.ged.bdoc.requests.GedBdocApiRequest;

public interface ICaching {
    Token getToken(GedBdocApiRequest request, String bdocEndpointUrl) throws SOAPException, Exception;

    Token refreshToken(GedBdocApiRequest request, String bdocEndpointUrl) throws SOAPException, Exception;

    void emptyCache();
}
