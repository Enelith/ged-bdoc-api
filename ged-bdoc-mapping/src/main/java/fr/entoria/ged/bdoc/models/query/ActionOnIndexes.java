package fr.entoria.ged.bdoc.models.query;

import java.util.ArrayList;
import java.util.List;

public class ActionOnIndexes {
    private List<TargetIndex> targetIndexes = new ArrayList<>();
    private List<Integer> targetDocumentsId = new ArrayList<>();

    public ActionOnIndexes() {
	super();
    }

    public ActionOnIndexes(List<TargetIndex> targetIndexes, List<Integer> targetDocumentsId) {
	super();
	this.targetIndexes = targetIndexes;
	this.targetDocumentsId = targetDocumentsId;
    }

    public List<TargetIndex> getTargetIndexes() {
	return targetIndexes;
    }

    public void setTargetIndexes(List<TargetIndex> targetIndexes) {
	this.targetIndexes = targetIndexes;
    }

    public List<Integer> getTargetDocumentsId() {
	return targetDocumentsId;
    }

    public void setTargetDocumentsId(List<Integer> targetDocumentsId) {
	this.targetDocumentsId = targetDocumentsId;
    }
}
