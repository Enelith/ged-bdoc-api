package fr.entoria.ged.bdoc.service;

import javax.xml.soap.SOAPMessage;

import fr.entoria.ged.bdoc.requests.GedBdocApiRequest;

public interface IBdocFunctions {
    SOAPMessage process(GedBdocApiRequest gedBdocApiRequest, SOAPMessage soapRequest);
}
