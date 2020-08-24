package fr.entoria.ged.bdoc.service.impl;

import javax.xml.soap.SOAPMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import fr.entoria.ged.bdoc.requests.GedBdocApiRequest;
import fr.entoria.ged.bdoc.service.ILogonDV;

@Component
public class LogonDV extends AbstractBdocFunctions implements ILogonDV {
    private static final String LOGGER_HEADER = "[" + LogonDV.class.getName() + "] ";
    private static final Logger logger = (Logger) LoggerFactory.getLogger(LogonDV.class);

    @Override
    public SOAPMessage generateSoapRequest(GedBdocApiRequest gedBdocApiRequest, SOAPMessage soapRequest) {
	logger.debug(LOGGER_HEADER + "call execute");

	return process(gedBdocApiRequest, soapRequest);
    }

}
