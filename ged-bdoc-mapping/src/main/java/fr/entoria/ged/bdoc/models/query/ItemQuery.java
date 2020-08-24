package fr.entoria.ged.bdoc.models.query;

public class ItemQuery {
    private String itemKey;

    private String itemValue;

    private String operande;

    public ItemQuery() {
	super();
    }

    public ItemQuery(String itemKey, String itemValue, String operande) {
	super();
	this.itemKey = itemKey;
	this.itemValue = itemValue;
	this.operande = operande;
    }

    /**
     * @return the itemKey
     */
    public String getItemKey() {
	return itemKey;
    }

    /**
     * @param itemKey the itemKey to set
     */
    public void setItemKey(String itemKey) {
	this.itemKey = itemKey;
    }

    /**
     * @return the itemValue
     */
    public String getItemValue() {
	return itemValue;
    }

    /**
     * @param itemValue the itemValue to set
     */
    public void setItemValue(String itemValue) {
	this.itemValue = itemValue;
    }

    /**
     * @return the operande
     */
    public String getOperande() {
	return operande;
    }

    /**
     * @param operande the operande to set
     */
    public void setOperande(String operande) {
	this.operande = operande;
    }

}