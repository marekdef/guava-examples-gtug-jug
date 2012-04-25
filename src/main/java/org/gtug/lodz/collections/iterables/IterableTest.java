package org.gtug.lodz.collections.iterables;

import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class IterableTest {

	@Test
	public void testRemoveIf() {
		List<String> languages = new ArrayList<String>(Arrays.asList("Polish",
				"English", "Spanish"));

		ArrayList<String> programmingLangs = Lists.newArrayList("Java", "C++",
				"Python");

		Iterable<String> removeFrom = Iterables.concat(languages,
				programmingLangs);

		Predicate<? super String> predicate = new Predicate<String>() {

			public boolean apply(String input) {
				return !Arrays.asList("Java", "Python", "Polish", "English")
						.contains(input);
			}
		};

		Iterables.removeIf(removeFrom, predicate);

		assertThat(languages).containsOnly("Polish", "English");
		assertThat(programmingLangs).containsOnly("Java", "Python");
	}

	@Test
	public void testPartitions() {
		// given
		List<String> languages = new ArrayList<String>(Arrays.asList("Polish", "English", "Spanish", "German", "Greek", "Russian", "French"));
		
		// when
		Iterable<List<String>> partition = Iterables.partition(languages, 2);
		List<String> first = Iterables.getFirst(partition, null);
		List<String> lastList = Iterables.getLast(partition);

		// then
		assertThat(partition).hasSize(4);
		assertThat(first).isNotNull();
		assertThat(first).hasSize(2);
		assertThat(Iterables.getOnlyElement(lastList)).isEqualTo("French");
	}
	
	@Test
	public void test() {
		
	}
}
