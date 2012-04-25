package org.gtug.lodz.guava.strings;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Map;

import org.junit.Test;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;

public class SplitterTest {
	@Test
	public void testSplitWithTrim() {
		// given
		// when using spliter on ; that trims result
		Iterable<String> split = Splitter.on(";").trimResults()
				.split("JUG; GTUG; Łódź;; Guava");
		// then it has correct size
		assertThat(split).hasSize(5);
		// first eleemnt is JUG
		assertThat(Iterables.getFirst(split, null)).isEqualTo("JUG");

		// last element is Guava without leading space
		assertThat(Iterables.getLast(split)).isEqualTo("Guava");

		assertThat(split).contains("");
	}

	@Test
	public void testSplitWithNoEmptyStrings() {
		// given
		// when using spliter on ; that trims result
		Iterable<String> split = Splitter.on(";").trimResults()
				.omitEmptyStrings().split("JUG; GTUG; Łódź;; Guava");
		// then it has correct size
		assertThat(split).hasSize(4);

		assertThat(Iterables.contains(split, "")).isFalse();

		assertThat(split).contains("Guava");
	}

	@Test
	public void testSplitWithMatcher() {
		// given
		// when using spliter on ; and , that trims result
		Iterable<String> split = Splitter.on(CharMatcher.anyOf(";,"))
				.trimResults().omitEmptyStrings()
				.split("JUG; GTUG, Łódź;; Guava");
		// then it has correct size
		assertThat(split).hasSize(4);

		assertThat(Iterables.contains(split, "")).isFalse();

		assertThat(split).containsOnly("JUG", "GTUG", "Łódź", "Guava");
	}

	@Test
	public void testSplitWithKeyValue() {
		// given like map
		String python = "{'jack': 4098, 'sape': 4139}".replace("{", "")
				.replace("}", "").replace("'", "");
		// when we use map splitter
		Map<String, String> split = Splitter.on(",")
				.trimResults(CharMatcher.anyOf("' {}"))
				.withKeyValueSeparator(":").split(python);
		// then we get a correct size of map
		assertThat(split).hasSize(2);
		// with good keys
		assertThat(split.keySet()).contains("sape", "jack");
	}

	@Test
	public void testSplitWithLimit() {
		// given
		// when using spliter on ; that trims result and stops on 3rd split
		Iterable<String> split = Splitter.on(CharMatcher.anyOf(";,"))
				.limit(3).trimResults().omitEmptyStrings()
				.split("JUG; GTUG, Łódź;; Guava");
		// then it has correct size
		assertThat(split).hasSize(3);
		
		// has fist 2 elements splitted
		assertThat(split).contains("JUG", "GTUG");
		
		// the last is not splites
		assertThat(Iterables.getLast(split)).isEqualTo("Łódź;; Guava");
	}
}
