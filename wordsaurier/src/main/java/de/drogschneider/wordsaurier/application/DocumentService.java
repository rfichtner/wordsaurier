package de.drogschneider.wordsaurier.application;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.drogschneider.wordsaurier.domian.model.Document;
import de.drogschneider.wordsaurier.domian.model.Line;
import de.drogschneider.wordsaurier.domian.model.Word;
import de.drogschneider.wordsaurier.domian.model.Wordlist;
import de.drogschneider.wordsaurier.domian.service.LetterFilter;

public class DocumentService {

	private static final Logger LOG = LoggerFactory.getLogger(DocumentService.class);

	public Document buildDocument(final DocumentSpecification documentSpecification) {

		try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("wordlist-german.dic")) {
			final Stream<String> fileLines = new BufferedReader(
					new InputStreamReader(is, Charset.forName("ISO-8859-1"))).lines();

			LOG.debug("reading dictonary");
			final Wordlist words = new Wordlist(fileLines.map(s -> new Word(s)).collect(Collectors.toList()));

			words.addFilter(new LetterFilter(documentSpecification.getAllowedCharacters()));

			LOG.debug("filtering words");
			final List<Word> matchingWords = words.getMatches();

			Collections.shuffle(matchingWords);

			final List<Line> lines = new ArrayList<>();
			for (int i = 0; i < documentSpecification.getLinesInDocument(); i++) {

				LOG.debug("building line {}", i + 1);
				final Line line = Line.of(documentSpecification.getCharactersInLine(), matchingWords);
				lines.add(line);

			}

			return Document.of(lines);

		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}
}
