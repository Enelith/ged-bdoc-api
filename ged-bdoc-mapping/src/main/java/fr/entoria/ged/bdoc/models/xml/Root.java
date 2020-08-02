package fr.entoria.ged.bdoc.models.xml;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "root")
public class Root implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 3787849577615199314L;

    private List<Document> DOCUMENT;

    public Root() {
	super();
    }

    public List<Document> getDOCUMENT() {
	return DOCUMENT;
    }

    public void setDOCUMENT(List<Document> document) {
	DOCUMENT = document;
    }
}
