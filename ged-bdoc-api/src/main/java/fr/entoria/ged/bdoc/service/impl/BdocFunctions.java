package fr.entoria.ged.bdoc.service.impl;

import javax.xml.namespace.QName;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import fr.entoria.ged.bdoc.api.Token;
import fr.entoria.ged.bdoc.requests.GedBdocApiRequest;
import fr.entoria.ged.bdoc.service.IBdocFunctions;

@Component
public abstract class BdocFunctions implements IBdocFunctions {
    private static final String LOGGER_HEADER = "[" + BdocFunctions.class.getName() + "] ";
    private static final Logger logger = (Logger) LoggerFactory.getLogger(BdocFunctions.class);

    @Value("${bdoc.connector.soap.prefix}")
    protected String request_prefix;

    @Value("${bdoc.connector.soap.enveloppe.uri}")
    private String namespace_declaration;

    @Value("${bdoc.connector.soap.action.url}")
    private String bdoc_soap_action_url;

    @Value("${bdoc.connector.soap.enveloppe.uri}")
    private String bdoc_soap_enveloppe_uri;

    @Value("${bdoc.connector.uc.credential.type.useridpassword.filler}")
    private String user_credential_type_useridpassword;

    @Value("${bdoc.connector.uc.credential.type.authenticateid.filler}")
    private String user_credential_type_authenticateid;

    @Value("${bdoc.connector.uc.domain.name.filler}")
    private String user_credential_domain_name;

    @Value("${bdoc.connector.uc.user.name.filler}")
    private String user_credential_username;

    @Value("${bdoc.connector.uc.password.filler}")
    private String user_credential_password;

    protected String soapAction = this.getClass().getSimpleName();

    protected SOAPEnvelope soapEnvelope;
    protected SOAPHeader soapHeader;
    protected SOAPBody soapBody;
    protected SOAPBodyElement body;

    private GedBdocApiRequest apiRequest;

    protected Token token;

    @Override
    public SOAPMessage process(GedBdocApiRequest gedBdocApiRequest, SOAPMessage soapRequest) {
	logger.debug(LOGGER_HEADER + "call process");

	try {
	    apiRequest = gedBdocApiRequest;

	    SOAPPart soapPart = soapRequest.getSOAPPart();

	    soapEnvelope = soapPart.getEnvelope();
	    soapEnvelope.addNamespaceDeclaration(request_prefix, namespace_declaration);

	    soapEnvelope.addNamespaceDeclaration("xs", "http://www.w3.org/2001/XMLSchema");
	    soapEnvelope.addNamespaceDeclaration("xsi", "http://www.w3.org/2001/XMLSchema-instance");

	    buildHeader();
	    buildBody();

	    MimeHeaders headers = soapRequest.getMimeHeaders();
	    headers.addHeader("SOAPAction", bdoc_soap_action_url + soapAction);

	    soapRequest.saveChanges();

	} catch (SOAPException e) {
	    logger.error(LOGGER_HEADER + "Error occurred while creating " + soapAction + " Envelope : "
			 + e.getMessage());
	}

	return soapRequest;
    }

    protected void buildHeader() throws SOAPException {
	logger.debug(LOGGER_HEADER + "call buildHeader");

	try {
	    soapHeader = soapEnvelope.getHeader();

	} catch (SOAPException e) {
	    logger.error(LOGGER_HEADER + "Error occurred while creating " + soapAction + " Envelope Header: "
			 + e.getMessage());
	    throw e;
	}
    }

    protected void buildBody() throws SOAPException {
	logger.debug(LOGGER_HEADER + "call buildBody");

	try {
	    soapBody = soapEnvelope.getBody();

	    QName actionDocument = new QName(bdoc_soap_enveloppe_uri, soapAction);
	    body = soapBody.addBodyElement(actionDocument);

	    /**
	     * UserCredential Block
	     */
	    SOAPElement userCredentials = getCypUserCredentials();
	    body.addChildElement(userCredentials);

	    /**
	     * Build Body Specific to each Functions
	     */
	    _buildBody();

	} catch (SOAPException e) {
	    logger.error(LOGGER_HEADER + "Error occurred while creating " + this.getClass().getSimpleName()
			 + " Envelope Body: " + e.getMessage());
	    throw e;
	}
    }

    protected void _buildBody() throws SOAPException {
	logger.debug(LOGGER_HEADER + "call _buildBody");
    }

    @SuppressWarnings("unused")
    private SOAPElement getCypUserCredentials() {
	SOAPElement result = null;

	try {
	    SOAPFactory factory = SOAPFactory.newInstance();

	    result = factory.createElement(new QName(bdoc_soap_enveloppe_uri,
			"UserCredential", request_prefix));

	    SOAPElement credentialType = addElementTo(result, "CredentialType");

	    if (token != null) {
		credentialType.addTextNode(user_credential_type_authenticateid);

		SOAPElement cypWsUserId = addElementTo(result, "CypWsUserId");
		cypWsUserId.addTextNode(token.getValue());
	    } else {
		credentialType.addTextNode(user_credential_type_useridpassword);

		SOAPElement domainName = addElementTo(result, "DomainName");
		domainName.addTextNode(!StringUtils.isEmpty(apiRequest.getDomain())
			    ? apiRequest.getDomain()
			    : user_credential_domain_name);

		SOAPElement userName = addElementTo(result, "UserName");
		userName.addTextNode(!StringUtils.isEmpty(apiRequest.getUsername())
			    ? apiRequest.getUsername()
			    : user_credential_username);

		SOAPElement password = addElementTo(result, "Password");
		password.addTextNode(!StringUtils.isEmpty(apiRequest.getPassword())
			    ? apiRequest.getPassword()
			    : user_credential_password);
	    }

	    SOAPElement credentialData = addElementTo(result, "CredentialData");

	} catch (SOAPException e) {
	    logger.error(LOGGER_HEADER + "Error occurred while generating UserCredentialsBlock : " + e.getMessage());
	    result = null;
	}

	return result;
    }

    private SOAPElement addElementTo(SOAPElement root, String localName) throws SOAPException {
	return root.addChildElement(
		    new QName(bdoc_soap_enveloppe_uri, localName, request_prefix));
    }
}
