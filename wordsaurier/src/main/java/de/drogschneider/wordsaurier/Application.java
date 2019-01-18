package de.drogschneider.wordsaurier;

import java.io.IOException;

import de.drogschneider.wordsaurier.interfaces.cli.CliUserInterface;

/**
 * Hello world!
 *
 */
public class Application {

	public static void main(final String[] args) throws IOException {
		final CliUserInterface cliUserInterface = new CliUserInterface(args);
		cliUserInterface.compute();
		
		
		

	}
}
