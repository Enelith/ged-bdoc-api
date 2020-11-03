package fr.entoria.ged.bdoc.soap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.XsdSchemaCollection;
import org.springframework.xml.xsd.commons.CommonsXsdSchemaCollection;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
	MessageDispatcherServlet servlet = new MessageDispatcherServlet();
	servlet.setApplicationContext(applicationContext);
	servlet.setTransformWsdlLocations(true);
	return new ServletRegistrationBean(servlet, "/ws/*");
    }

    @Bean(name = "searchDocuments")
    public DefaultWsdl11Definition searchDocumentsDefinition() throws Exception {
	DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
	wsdl11Definition.setPortTypeName("WSGedDocumentDAOImpl");
	wsdl11Definition.setLocationUri("/ws/searchDocuments");
	wsdl11Definition.setTargetNamespace("http://wsimpl.ged.business.bdocinteractive.bdoc.com");
	wsdl11Definition.setSchemaCollection(searchDocumentsSchema());
	return wsdl11Definition;
    }

    @Bean(name = "getDocumentById")
    public DefaultWsdl11Definition getDocumentByIdDefinition() throws Exception {
	DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
	wsdl11Definition.setPortTypeName("GEDDaoImpl");
	wsdl11Definition.setLocationUri("/ws/getDocumentById");
	wsdl11Definition.setTargetNamespace("http://ged.business.bdocinteractive.bdoc.com");
	wsdl11Definition.setSchemaCollection(getDocumentByIdSchema());
	return wsdl11Definition;
    }

    @Bean
    public XsdSchemaCollection searchDocumentsSchema() throws Exception {
	CommonsXsdSchemaCollection xsds =
		    new CommonsXsdSchemaCollection(
				new ClassPathResource("/wsdl/map.xsd"),
				new ClassPathResource("/wsdl/searchDocuments.xsd")
			);
	xsds.setInline(true);
	return xsds;
    }

    @Bean
    public XsdSchemaCollection getDocumentByIdSchema() throws Exception {
	CommonsXsdSchemaCollection xsds =
		    new CommonsXsdSchemaCollection(
				new ClassPathResource("/wsdl/map.xsd"),
				new ClassPathResource("/wsdl/getDocumentById.xsd")
			);
	xsds.setInline(true);
	return xsds;
    }
}
