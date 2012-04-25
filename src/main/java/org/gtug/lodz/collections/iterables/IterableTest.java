package org.gtug.lodz.collections.iterables;
import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

public class IterableTest {

	@Test
	public void test() {
		List<String> languages = new ArrayList<String>(Arrays.asList("Polish",
				"English", "Spanish"));

		ArrayList<String> programmingLangs = Lists.newArrayList("Java", "C++",
				"Python");

		Iterator<String> removeFrom = Iterators.concat(languages.iterator(),
				programmingLangs.iterator());

		Predicate<? super String> predicate = new Predicate<String>() {

			public boolean apply(String input) {
				return !Arrays.asList("Java", "Python", "Polish", "English")
						.contains(input);
			}
		};

		Iterators.removeIf(removeFrom, predicate);

		assertThat(languages).containsOnly("Polish", "English");
		assertThat(programmingLangs).containsOnly("Java", "Python");
	}
}
