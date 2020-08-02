package fr.entoria.ged.bdoc.service;

import javax.xml.soap.SOAPMessage;

import fr.entoria.ged.bdoc.requests.GedBdocApiRequest;

public interface ILogonDV {
    public SOAPMessage generateSoapRequest(GedBdocApiRequest request, SOAPMessage soapRequest);
}
