package de.drogschneider.wordsaurier.interfaces.cli;

import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import de.drogschneider.wordsaurier.application.DocumentService;
import de.drogschneider.wordsaurier.application.DocumentSpecification;
import de.drogschneider.wordsaurier.domian.model.Document;
import de.drogschneider.wordsaurier.infrastructure.filesystem.DocumentWriter;

public class CliUserInterface {

	private final String[] args;

	public CliUserInterface(final String[] args) {
		this.args = args;

	}

	public void compute() throws IOException {

		final Options options = new Options();

		final Option linesInDocument = Option.builder().longOpt("lines").hasArg().desc("lines in document")
				.required().build();
		options.addOption(linesInDocument);

		final Option charactersInLine = Option.builder().longOpt("chars").hasArg()
				.desc("characters in line").required().build();
		options.addOption(charactersInLine);

		final Option allowedCharacters = Option.builder().longOpt("allowedChars").hasArg()
				.desc("allowed characters for words (case sensitive)").required().build();
		options.addOption(allowedCharacters);

		// create the parser
		final CommandLineParser parser = new DefaultParser();
		try {
			// parse the command line arguments
			final CommandLine line = parser.parse(options, this.args, true);

			final DocumentSpecification documentSpecification = new DocumentSpecification(
					Integer.valueOf(line.getOptionValue(charactersInLine.getLongOpt())),
					Integer.valueOf(line.getOptionValue(linesInDocument.getLongOpt())),
					line.getOptionValue(allowedCharacters.getLongOpt()));

			this.writeDocument(documentSpecification);
		} catch (final ParseException exp) {
			// oops, something went wrong
			System.err.println("Parsing failed.  Reason: " + exp.getMessage());
			final HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("wordsaurier", options);
		}
	}

	private void writeDocument(final DocumentSpecification documentSpecification) throws IOException {
		final DocumentService documentService = new DocumentService();
		final Document document = documentService.buildDocument(documentSpecification);

		System.out.println(document.getContent());
		final DocumentWriter documentWriter = new DocumentWriter(document, documentSpecification);
		documentWriter.write();
	}

}
