//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.3.0 
// Voir <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.10.26 à 09:55:22 AM CET 
//


package fr.entoria.ged.bdoc.soap.models.getDocumentById;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.entoria.ged.bdoc.soap.models.getDocumentById package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.entoria.ged.bdoc.soap.models.getDocumentById
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetDocumentById }
     * 
     */
    public GetDocumentById createGetDocumentById() {
        return new GetDocumentById();
    }

    /**
     * Create an instance of {@link GetDocumentByIdResponse }
     * 
     */
    public GetDocumentByIdResponse createGetDocumentByIdResponse() {
        return new GetDocumentByIdResponse();
    }

    /**
     * Create an instance of {@link GedDocument }
     * 
     */
    public GedDocument createGedDocument() {
        return new GedDocument();
    }

    /**
     * Create an instance of {@link MapItem }
     * 
     */
    public MapItem createMapItem() {
        return new MapItem();
    }

    /**
     * Create an instance of {@link Map }
     * 
     */
    public Map createMap() {
        return new Map();
    }

}
