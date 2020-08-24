package fr.entoria.ged.bdoc.business;

import javax.xml.soap.SOAPException;

import fr.entoria.ged.bdoc.exceptions.InvalidGedBdocApiArgumentsException;
import fr.entoria.ged.bdoc.models.AddDocIndexesResponse;
import fr.entoria.ged.bdoc.models.DeleteDocIndexesResponse;
import fr.entoria.ged.bdoc.models.IsDocumentExistResponse;
import fr.entoria.ged.bdoc.models.LoadDocContentsResponse;
import fr.entoria.ged.bdoc.models.QueryForDocumentsResponse;
import fr.entoria.ged.bdoc.requests.GedBdocApiRequest;

public interface IBdocBusiness {
    public QueryForDocumentsResponse queryForDocuments(GedBdocApiRequest request) throws SOAPException, Exception;

    public LoadDocContentsResponse loadDocContents(GedBdocApiRequest request) throws SOAPException, Exception;

    public IsDocumentExistResponse isDocumentExist(GedBdocApiRequest request) throws SOAPException, Exception;

    public AddDocIndexesResponse addDocIndexes(GedBdocApiRequest request)
		throws SOAPException, InvalidGedBdocApiArgumentsException, Exception;

    public DeleteDocIndexesResponse deleteDocIndexes(GedBdocApiRequest request)
		throws SOAPException, InvalidGedBdocApiArgumentsException, Exception;
}
