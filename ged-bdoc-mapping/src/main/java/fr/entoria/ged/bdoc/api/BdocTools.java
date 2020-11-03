package fr.entoria.ged.bdoc.api;

import fr.entoria.ged.bdoc.models.QueryForDocumentsResponse;
import fr.entoria.ged.bdoc.models.subs.CwsRsltDocContents;

public class BdocTools {

    public static boolean hasElements(QueryForDocumentsResponse response) {
	return (response != null
		    && response.getQueryForDocumentsResult() != null
		    && response.getQueryForDocumentsResult().getHitDocs() != null
		    && response.getQueryForDocumentsResult().getHitDocs().getCwsRsltDocContents() != null
		    && response.getQueryForDocumentsResult().getHitDocs().getCwsRsltDocContents().length > 0);
    }
    
    public static boolean hasIndexes(CwsRsltDocContents rsltDocContents) {
	return (rsltDocContents != null
		    && rsltDocContents.getDocIndexes() != null
		    && rsltDocContents.getDocIndexes().getDocIndexes() != null
		    && rsltDocContents.getDocIndexes().getDocIndexes().length > 0);
    }
    
    public static boolean hasMetadatas(CwsRsltDocContents rsltDocContents) {
	return (rsltDocContents != null
		    && rsltDocContents.getDocMetadata() != null
		    && rsltDocContents.getDocMetadata().getCwsDocMetadatas() != null
		    && rsltDocContents.getDocMetadata().getCwsDocMetadatas().length > 0);
    }
}
