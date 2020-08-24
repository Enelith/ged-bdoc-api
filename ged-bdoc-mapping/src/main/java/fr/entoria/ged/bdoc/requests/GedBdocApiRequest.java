package fr.entoria.ged.bdoc.requests;

import java.util.ArrayList;
import java.util.List;

import fr.entoria.ged.bdoc.models.query.ActionOnIndexes;

public class GedBdocApiRequest {
    private Integer requestId;

    private String domain;

    private String username;

    private String password;

    private String query;

    private List<ActionOnIndexes> actionsOnIndexes = new ArrayList<>();

    public GedBdocApiRequest() {
	super();
	// TODO Auto-generated constructor stub
    }

    public GedBdocApiRequest(Integer requestId, String domain, String username, String password, String query,
		List<ActionOnIndexes> actionsOnIndexes) {
	super();
	this.requestId = requestId;
	this.domain = domain;
	this.username = username;
	this.password = password;
	this.query = query;
	this.actionsOnIndexes = actionsOnIndexes;
    }

    public Integer getRequestId() {
	return requestId;
    }

    public void setRequestId(Integer requestId) {
	this.requestId = requestId;
    }

    public String getDomain() {
	return domain;
    }

    public void setDomain(String domain) {
	this.domain = domain;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getQuery() {
	return query;
    }

    public void setQuery(String query) {
	this.query = query;
    }

    public List<ActionOnIndexes> getActionsOnIndexes() {
	return actionsOnIndexes;
    }

    public void setActionsOnIndexes(List<ActionOnIndexes> actionsOnIndexes) {
	this.actionsOnIndexes = actionsOnIndexes;
    }
}
