package fr.entoria.ged.bdoc.business.impl;

import java.time.LocalDateTime;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import fr.entoria.ged.bdoc.api.Token;
import fr.entoria.ged.bdoc.business.ICaching;
import fr.entoria.ged.bdoc.models.LogonDVResponse;
import fr.entoria.ged.bdoc.requests.GedBdocApiRequest;
import fr.entoria.ged.bdoc.service.ILogonDV;
import fr.entoria.ged.bdoc.tools.SoapClient;

@Component
public class Caching implements ICaching {
    private static final String LOGGER_HEADER = "[" + Caching.class.getName() + "] ";
    private static final Logger logger = (Logger) LoggerFactory.getLogger(Caching.class);

    @Autowired
    ILogonDV logonDVFunction;

    @Override
    @Cacheable(
		//cacheNames = "${spring.cache.cacheName.token}",
		cacheNames = "tokenCache",
		key = "#request.requestId",
		condition = "#request.requestId != null && #request.requestId > 0",
		sync = true)
    public Token getToken(GedBdocApiRequest request, String bdocEndpointUrl) throws SOAPException, Exception {
	return retrieveToken(request, bdocEndpointUrl);
    }

    @Override
    @CachePut(
		//cacheNames = "${spring.cache.cacheName.token}",
		cacheNames = "tokenCache",
		key = "#request.requestId",
		condition = "#request.requestId != null && #request.requestId > 0",
		unless = "#result == null")
    public Token refreshToken(GedBdocApiRequest request, String bdocEndpointUrl) throws SOAPException, Exception {
	logger.info(LOGGER_HEADER + "REFRESH TOKEN REQUIRED (LogonDVResponse) : requestId = "
		    + request.getRequestId());
	return retrieveToken(request, bdocEndpointUrl);
    }

    private Token retrieveToken(GedBdocApiRequest request, String bdocEndpointUrl) throws SOAPException, Exception {
	Token token = null;

	if (request.getRequestId() != null
		    && request.getRequestId() > 0) {
	    SoapClient soapClient = new SoapClient(bdocEndpointUrl);

	    logger.info(LOGGER_HEADER + "GENERATION TOKEN");

	    SOAPMessage soapRequest = logonDVFunction.generateSoapRequest(request, soapClient.getSoapRequest());
	    try {
		LogonDVResponse logonDVResponse = soapClient.soapConnectionCall(soapRequest, LogonDVResponse.class);

		if (logonDVResponse.isLogonDVResult()) {
		    token = new Token(
				String.valueOf(request.getRequestId()),
				logonDVResponse.getUserID(),
				LocalDateTime.now());

		    logger.info(LOGGER_HEADER + "Token UserID generated : " + token.getValue());
		}
	    } catch (SOAPException e) {
		logger.error(LOGGER_HEADER + "(SOAPException) Error occurred while sending SOAP Request to Server : "
			     + e.getMessage());
		throw e;
	    } catch (Exception e) {
		logger.error(LOGGER_HEADER + "(Exception) Error occurred while sending SOAP Request to Server : "
			     + e.getMessage());
		throw e;
	    } finally {
		soapClient.close();
	    }
	}

	if (token == null) {
	    logger.error(LOGGER_HEADER + "ANOMALY DETECTED : TOKEN COULD NOT BE GENERATED");
	}

	return token;
    }

    @Override
    @Scheduled(cron = "${spring.cache.cacheName.token.evict.cron}")
    //@CacheEvict(cacheNames = "${spring.cache.cacheName.token}", allEntries = true)
    @CacheEvict(cacheNames = "tokenCache", allEntries = true)
    public void emptyCache() {
	logger.info(LOGGER_HEADER + "!!! SPRING CACHE MANAGER (CacheEvict) : EMPTY CACHE FOR ALL ENTRIES !!!");
    }
}
