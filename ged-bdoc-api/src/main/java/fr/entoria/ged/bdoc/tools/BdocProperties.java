package fr.entoria.ged.bdoc.tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

// Obsolete
public class BdocProperties {
    private static final String LOGGER_HEADER = "[" + BdocProperties.class.getName() + "] ";
    private static final Logger logger = (Logger) LoggerFactory.getLogger(BdocProperties.class);

    private static final String DEFAULT_FILENAME_PROPERTIES = "bdoc.application.properties";
    private static final String VM_EXTERNALISE_VAR_PATH = "bdoc.externalized.properties";

    /**
     * Charge la liste des propriétés contenu dans le fichier spécifié
     *
     * @return un objet Properties contenant les propriétés du fichier
     */
    public static Properties load() {
	logger.debug(LOGGER_HEADER + "Initialisation BdocProperties");

	Properties properties = null;
	InputStream input = null;

	try {
	    properties = new Properties();
	    input = BdocProperties.class.getResourceAsStream("/" + DEFAULT_FILENAME_PROPERTIES);
	    properties.load(input);

	    logger.debug(LOGGER_HEADER + "Checking externalized properties for VM arg : " + VM_EXTERNALISE_VAR_PATH);
	    if (!StringUtils.isEmpty(System.getProperty(VM_EXTERNALISE_VAR_PATH))) {
		Path pathExternalise = Paths.get(System.getProperty(VM_EXTERNALISE_VAR_PATH));

		logger.debug(LOGGER_HEADER + "Loading externalized properties at : "
			     + pathExternalise.toString());
		if (Files.exists(pathExternalise, new LinkOption[] {})
			    && Files.isReadable(pathExternalise)) {
		    FileInputStream fis = new FileInputStream(pathExternalise.toFile());
		    properties.load(fis);
		    fis.close();
		} else {
		    logger.debug(LOGGER_HEADER
				 + "Failed to load externalized properties - file does not exist or is not readable");
		    throw new IOException(
				"Failed to load externalized properties - file does not exist or is not readable");
		}
	    }
	} catch (IOException e) {
	    logger.error(LOGGER_HEADER + "(IOException) Error while initializing BdocProperties : " + e.getMessage());
	} finally {
	    if (input != null) {
		try {
		    input.close();
		} catch (IOException e) {
		    logger.error(LOGGER_HEADER
				 + "(IOException) Error while initializing BdocProperties (Close InputStream) : "
				 + e.getMessage());
		}
	    }
	}

	return properties;
    }
}
