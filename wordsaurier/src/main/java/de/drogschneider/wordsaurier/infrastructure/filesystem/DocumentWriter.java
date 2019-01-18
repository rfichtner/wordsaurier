package de.drogschneider.wordsaurier.infrastructure.filesystem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.drogschneider.wordsaurier.application.DocumentSpecification;
import de.drogschneider.wordsaurier.domian.model.Document;

public class DocumentWriter {

	private static final Logger LOG = LoggerFactory.getLogger(DocumentWriter.class);
	
	private final Document document;
	private final DocumentSpecification documentSpecification;

	public DocumentWriter(final Document document, final DocumentSpecification documentSpecification) {
		super();
		this.document = document;
		this.documentSpecification = documentSpecification;
	}

	public void write() throws IOException {

		// create a temporary file
		final String timeLog = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_hh-mm-ss"));
		final File file = new File("./target/" + timeLog + ".txt");

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

			writer.write("Wordsaurier document\r\n");
			writer.write(this.documentSpecification.toString() + "\r\n");
			writer.write("---------------------\r\n");
			writer.write(this.document.getContent());
			LOG.info("document was written to {}", file.getCanonicalPath());
			
		} catch (final IOException e) {
			throw e;
		}
	}

}
