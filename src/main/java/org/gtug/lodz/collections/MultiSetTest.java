package org.gtug.lodz.collections;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MultiSetTest {
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	private LegacyMultiSet<Object> words = //HashMultiset.create();
			LegacyMultiSet.create();
	
	@Test
	public void testMultiSetCounts() {
		// given
		// when
		words.add("GTUG");
		words.add("GTUG", 5);
		words.add("JUG");
		words.remove("JUG", 2);

		// then
		assertThat(words.count("GTUG")).isEqualTo(6);
		assertThat(words.count("JUG")).isEqualTo(0);
		assertThat(words.count("Lodz")).isEqualTo(0);

		assertThat(words.elementSet()).containsOnly("GTUG");
	}

	@Test
	public void testMultiSetSetCount() {
		// given
		// when
		words.add("GTUG", 5);
		words.add("JUG");
		words.remove("JUG", 2);
		words.setCount("GTUG", 5);
		words.setCount("JUG", 0);

		// then
		assertThat(words.count("GTUG")).isEqualTo(5);
		assertThat(words.count("JUG")).isEqualTo(0);
		assertThat(words.count("Lodz")).isEqualTo(0);

		assertThat(words.elementSet()).containsOnly("GTUG");
	}

	@Test
	public void testMultiSetSetCountInvalid() {
		// given
		thrown.expect(IllegalArgumentException.class);
		// when
		words.setCount("Illegal", -1);
		// then
	}

}
