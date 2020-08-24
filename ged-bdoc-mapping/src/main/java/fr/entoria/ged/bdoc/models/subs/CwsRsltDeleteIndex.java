package fr.entoria.ged.bdoc.models.subs;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DeleteDocIndexesResult")
public class CwsRsltDeleteIndex extends AbstractCwsRsltActionIndex implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -7377747898925019640L;

    public CwsRsltDeleteIndex() {
	super();
    }

    public CwsRsltDeleteIndex(boolean status) {
	super(status);
    }
}
