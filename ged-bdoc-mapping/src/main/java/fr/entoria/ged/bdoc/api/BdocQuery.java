package fr.entoria.ged.bdoc.api;

import java.util.List;
import java.util.StringJoiner;

import org.apache.commons.validator.GenericValidator;
import org.springframework.util.StringUtils;

import fr.entoria.ged.bdoc.enums.BdocIndexes;
import fr.entoria.ged.bdoc.models.query.ItemQuery;

public class BdocQuery {

    private static String DATE_PATTERN = "yyyy-MM-dd";
    private static String DATE_PATTERN_HHMM = "yyyy-MM-dd-HH-mm";
    private static String DATE_SEPARATOR = "-";
    private static String DATE_BDOC_TSTAMP_SEPARATOR = ",";

    public static String generateQuery(List<ItemQuery> list) {
	StringJoiner result = new StringJoiner(" AND ");

	String itemValue = "";

	for (ItemQuery item : list) {
	    itemValue = "";

	    if (BdocIndexes.DOCUMENT_CREATION_TIME.value().equals(item.getItemKey())
			&& (GenericValidator.isDate(item.getItemValue(), DATE_PATTERN, true)
				    || GenericValidator.isDate(item.getItemValue(), DATE_PATTERN_HHMM, true))) {
		itemValue += "tstamp(" + item.getItemValue().replaceAll(DATE_SEPARATOR, DATE_BDOC_TSTAMP_SEPARATOR)
			     + ")";
	    } else if (BdocIndexes._DOCUMENT_ID.value().equals(item.getItemKey())) {
		itemValue += item.getItemValue();
	}
	    else {
		itemValue += BdocQuery.formatValue(item.getItemValue());
	    }

	    if (!StringUtils.isEmpty(itemValue)) {
		result.add("[" + item.getItemKey() + "]" + item.getOperande() + itemValue);
	    }
	}

	return result.toString();
    }

    private static String formatValue(String value) {
	return "\"" + value + "\"";
    }
}