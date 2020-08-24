package fr.entoria.ged.bdoc.models.subs;

import javax.xml.bind.annotation.XmlElement;

public abstract class AbstractCwsRsltActionIndex {
    protected boolean status;

    public AbstractCwsRsltActionIndex() {
	super();
    }

    public AbstractCwsRsltActionIndex(boolean status) {
	super();
	this.status = status;
    }

    @XmlElement(name = "status")
    public boolean isStatus() {
	return status;
    }

    public void setStatus(boolean status) {
	this.status = status;
    }
}
