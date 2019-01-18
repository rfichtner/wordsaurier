package de.drogschneider.wordsaurier;

import java.io.IOException;

import de.drogschneider.wordsaurier.application.DocumentService;
import de.drogschneider.wordsaurier.application.DocumentSpecification;
import de.drogschneider.wordsaurier.domian.model.Document;
import de.drogschneider.wordsaurier.infrastructure.filesystem.DocumentWriter;

/**
 * Hello world!
 *
 */
public class Application {

	public static void main(final String[] args) throws IOException {
		final DocumentService documentService = new DocumentService();

		final DocumentSpecification documentSpecification = new DocumentSpecification(80, 20, "Ausatmen");
		final Document document = documentService.buildDocument(documentSpecification);

		System.out.println(document.getContent());
		final DocumentWriter documentWriter = new DocumentWriter(document, documentSpecification);
		documentWriter.write();

	}
}
