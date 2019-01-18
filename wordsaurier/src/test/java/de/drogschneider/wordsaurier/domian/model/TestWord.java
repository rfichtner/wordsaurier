package de.drogschneider.wordsaurier.domian.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TestWord {

	Word word = new Word("hans");

	@Test
	void testGetContent() {
		assertEquals("hans", this.word.getContent());
	}

}
