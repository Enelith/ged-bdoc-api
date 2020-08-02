package fr.entoria.ged.bdoc.models.xml;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DOCUMENT")
public class Document implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 8570554306116445380L;

    private List<Indexes> INDEXES;

    public Document() {
	super();
    }

    public List<Indexes> getINDEXES() {
	return INDEXES;
    }

    public void setINDEXES(List<Indexes> indexes) {
	INDEXES = indexes;
    }
}
