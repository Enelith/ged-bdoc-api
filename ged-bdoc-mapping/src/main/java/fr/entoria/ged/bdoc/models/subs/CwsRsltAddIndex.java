package fr.entoria.ged.bdoc.models.subs;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "AddDocIndexesResult")
public class CwsRsltAddIndex extends AbstractCwsRsltActionIndex implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -1981691775091162379L;

    public CwsRsltAddIndex() {
	super();
    }

    public CwsRsltAddIndex(boolean status) {
	super(status);
    }
}