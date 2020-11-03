package fr.entoria.ged.bdoc.api;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Token implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String id;
    private String value;
    private LocalDateTime createDate;

    public Token(String id, String value, LocalDateTime createDate) {
	this.id = id;
	this.value = value;
	this.createDate = createDate;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getValue() {
	return value;
    }

    public void setValue(String value) {
	this.value = value;
    }

    public LocalDateTime getCreateDate() {
	return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
	this.createDate = createDate;
    }

    @Override
    public String toString() {
	return "Token{" + "id='" + id + '\'' + ", value='" + value + "\', createDate='" + createDate + "\'}";
    }

    public boolean isValid(Long ttl) {
	return LocalDateTime.now()
		    .minusMinutes(ttl)
		    .isBefore(createDate);
    }
}