package org.gtug.lodz.collections;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MultiSetTest {
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private LegacyMultiSet<Object> words = // HashMultiset.create();
	LegacyMultiSet.create();

	@Test
	public void testMultiSetCounts() {
		// given
		// when we add symbols
		words.add("GTUG");
		// several times
		words.add("GTUG", 5);
		// remove symbols
		words.add("JUG");
		// even more times they are inserted
		words.remove("JUG", 2);

		// then we get correct count on multiple inserts
		assertThat(words.count("GTUG")).isEqualTo(6);
		// removed word does not get negative count
		assertThat(words.count("JUG")).isEqualTo(0);
		// never present symbols are counted as 0
		assertThat(words.count("Lodz")).isEqualTo(0);
		// only one represented keys are present
		assertThat(words.elementSet()).containsOnly("GTUG");
	}

	@Test
	public void testMultiSetSetCount() {
		// given
		// when
		words.add("GTUG", 2);
		words.add("JUG");
		
		// using setCount to set count 
		words.setCount("GTUG", 0);
		words.setCount("JUG", 5);

		// then we get correct counts
		assertThat(words.count("GTUG")).isEqualTo(0);
		assertThat(words.count("JUG")).isEqualTo(5);

		assertThat(words.elementSet()).containsOnly("JUG");
	}

	@Test
	public void testMultiSetSetCountInvalid() {
		// given
		thrown.expect(IllegalArgumentException.class);
		// when inserting illegal count
		words.setCount("Illegal", -1);
		// then
	}

}
