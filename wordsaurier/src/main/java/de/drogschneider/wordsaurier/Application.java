package de.drogschneider.wordsaurier;

import de.drogschneider.wordsaurier.application.DocumentService;
import de.drogschneider.wordsaurier.domian.model.Document;

/**
 * Hello world!
 *
 */
public class Application {

	public static void main(final String[] args) {
		final DocumentService documentService = new DocumentService();
		final Document document = documentService.buildDocument("ausatmen", 10, 40);

		System.out.println(document.getContent());

	}
}
