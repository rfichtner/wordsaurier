package de.drogschneider.wordsaurier;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import de.drogschneider.wordsaurier.domian.model.Word;
import de.drogschneider.wordsaurier.domian.model.Wordlist;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(final String[] args) {
		final App app = new App();
		try (InputStream is = app.getClass().getClassLoader().getResourceAsStream("wordlist-german.dic")) {
			final Stream<String> lines = new BufferedReader(new InputStreamReader(is, Charset.forName("ISO-8859-1"))).lines();

			final Wordlist words = new Wordlist(lines.map(s -> new Word(s)).collect(Collectors.toList()));

			words.addFilter(e -> StringUtils.containsOnly(e.getContent(), "ausatmen"));
			
			words.getMatches().forEach(e->System.out.println(e.getContent()));
			
			
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}
}
