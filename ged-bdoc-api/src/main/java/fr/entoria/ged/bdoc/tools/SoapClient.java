package fr.entoria.ged.bdoc.tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.UnmarshallerHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Result;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class SoapClient {
    private static final String LOGGER_HEADER = "[" + SoapClient.class.getName() + "] ";
    private static final Logger logger = (Logger) LoggerFactory.getLogger(SoapClient.class);

    private MessageFactory factory;

    private SOAPConnectionFactory soapConnectionFactory;
    private SOAPConnection soapConnection;

    private SOAPMessage soapRequest;
    private SOAPMessage soapResponse;

    private Map<Class<?>, Unmarshaller> unmarshallers = new HashMap<>();

    private URL soapEndpointUrl;

    public SoapClient(String bdocEndpointUrl) {
	logger.debug(LOGGER_HEADER + "Initialize SoapClient");

	try {
	    soapConnectionFactory = SOAPConnectionFactory.newInstance();

	    factory = MessageFactory.newInstance();

	    soapConnection = soapConnectionFactory.createConnection();
	    soapRequest = factory.createMessage();

	    soapEndpointUrl = new URL(bdocEndpointUrl);
	} catch (MalformedURLException e) {
	    logger.error(LOGGER_HEADER
			 + "(MalformedURLException) Error while initializing SoapClient > soapEndpointUrl : "
			 + e.getMessage());
	} catch (UnsupportedOperationException e) {
	    logger.error(LOGGER_HEADER + "(UnsupportedOperationException) Error while initializing SoapClient : "
			 + e.getMessage());
	} catch (SOAPException e) {
	    logger.error(LOGGER_HEADER + "(SOAPException) Error while initializing SoapClient : " + e.getMessage());
	} catch (Exception e) {
	    logger.error(LOGGER_HEADER + "(Exception) Error while initializing SoapClient : " + e.getMessage());
	}
    }

    public <T> T soapConnectionCall(SOAPMessage customSoapRequest, Class<T> responseClass)
		throws SOAPException, Exception {
	logger.debug(LOGGER_HEADER + "call soapConnectionCall");

	displayMessage(customSoapRequest, "Request", true);

	System.setProperty("java.net.useSystemProxies", "true");
	soapResponse = soapConnection.call(customSoapRequest, soapEndpointUrl);

	displayMessage(soapResponse, "Response", false);

	return result(responseClass);
    }

    public void close() {
	logger.debug(LOGGER_HEADER + "call close");

	try {
	    if (soapConnection != null) {
		soapConnection.close();
		logger.info(LOGGER_HEADER + "SOAPConnection closed");
	    }
	} catch (UnsupportedOperationException e) {
	    logger.error("(UnsupportedOperationException) Error while terminating SOAPConnection : " + e.getMessage());
	} catch (SOAPException e) {
	    logger.error("(SOAPException e) Error while terminating SOAPConnection : " + e.getMessage());
	}
    }

    private void displayMessage(SOAPMessage soapMessage, String typeMessage, boolean print) {
	try {
	    ByteArrayOutputStream out = null;

	    if (soapMessage != null) {
		out = new ByteArrayOutputStream();
		soapMessage.writeTo(out);

		if (print) {
		    logger.info(LOGGER_HEADER + typeMessage + " SOAP Message : " + out.toString("UTF-8"));
		} else {
		    logger.debug(LOGGER_HEADER + typeMessage + " SOAP Message : " + out.toString("UTF-8"));
		}
	    }
	} catch (SOAPException e) {
	    logger.error(
			"(SOAPException) Error while displaying SOAPMessage (" + typeMessage + ") : " + e.getMessage());
	} catch (IOException e) {
	    logger.error("(IOException) Error while displaying SOAPMessage (" + typeMessage + ") : " + e.getMessage());
	}
    }

    private Unmarshaller getUnmarshaller(Class<?> responseClass) throws JAXBException {
	logger.debug(LOGGER_HEADER + "call getUnmarshaller for class : " + responseClass.getClass().getSimpleName());

	Unmarshaller u = null;
	if (unmarshallers.containsKey(responseClass)) {
	    u = unmarshallers.get(responseClass);
	} else {
	    u = JAXBContext.newInstance(responseClass).createUnmarshaller();
	    unmarshallers.put(responseClass, u);
	}
	return u;
    }

    @SuppressWarnings("unchecked")
    private <T> T result(Class<T> responseClass) throws Exception {
	logger.debug(LOGGER_HEADER + "call soapConnectionCall > result");

	SAXParserFactory spf = SAXParserFactory.newInstance();
	SAXParser sp = spf.newSAXParser();
	XMLReader xr = sp.getXMLReader();

	Unmarshaller unmarshaller = getUnmarshaller(responseClass);
	UnmarshallerHandler unmarshallerHandler = unmarshaller.getUnmarshallerHandler();
	xr.setContentHandler(unmarshallerHandler);

	Document doc = soapResponse.getSOAPBody().extractContentAsDocument();
	DOMSource source = new DOMSource(doc);

	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	Result outputTarget = new StreamResult(outputStream);
	TransformerFactory.newInstance().newTransformer().transform(source, outputTarget);
	InputStream is = new ByteArrayInputStream(outputStream.toByteArray());
	InputSource xmlSource = new InputSource(is);

	xr.parse(xmlSource);

	return (T) unmarshallerHandler.getResult();
    }

    public SOAPMessage getSoapRequest() {
	return soapRequest;
    }

    public void setSoapRequest(SOAPMessage soapRequest) {
	this.soapRequest = soapRequest;
    }

    public SOAPMessage getSoapResponse() {
	return soapResponse;
    }

    public void setSoapResponse(SOAPMessage soapResponse) {
	this.soapResponse = soapResponse;
    }
}
