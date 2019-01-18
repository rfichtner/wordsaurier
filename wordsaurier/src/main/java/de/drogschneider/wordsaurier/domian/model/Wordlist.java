package de.drogschneider.wordsaurier.domian.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Wordlist {

	private final List<Word> words;

	private final List<Predicate<Word>> filters = new ArrayList<>();

	public Wordlist(final List<Word> words) {
		this.words = new ArrayList<>(words);
	}

	public void addFilter(final Predicate<Word> filter) {
		this.filters.add(filter);
	}

	private Predicate<Word> getAllFilters() {
		Predicate<Word> result = null;
		for (final Predicate<Word> filter : this.filters) {
			if (result == null) {
				result = filter;
			} else {
				result.and(filter);
			}
		}
		return result;
	}

	public List<Word> getMatches() {
		return this.words.stream().filter(this.getAllFilters()).limit(1000).collect(Collectors.toList());
	}
}
