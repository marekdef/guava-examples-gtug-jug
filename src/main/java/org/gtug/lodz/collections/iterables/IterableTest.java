package org.gtug.lodz.collections.iterables;

import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class IterableTest {

	@Test
	public void testRemoveIf() {
		// given
		List<String> languages = new ArrayList<String>(Arrays.asList("Polish",
				"English", "Spanish"));

		ArrayList<String> programmingLangs = Lists.newArrayList("Java", "C++",
				"Python", "PHP");

		Predicate<? super String> predicate = new Predicate<String>() {
			public boolean apply(String input) {
				return !input.startsWith("P");
			}
		};

		// when we create concatenated iterable
		Iterable<String> removeFrom = Iterables.concat(languages,
				programmingLangs);

		// we remove from concate using predicate (not starting with P)
		Iterables.removeIf(removeFrom, predicate);

		// we have only P remaining in _underlying_ collection
		assertThat(languages).containsOnly("Polish");
		assertThat(programmingLangs).containsOnly("Python", "PHP");
	}

	@Test
	public void testPartitions() {
		// given
		List<String> languages = Arrays.asList("Polish", "English", "Spanish",
				"German", "Greek", "Russian", "French");

		// when we split into partition
		Iterable<List<String>> partition = Iterables.partition(languages, 2);
		// and get 1st element in partitions
		List<String> first = Iterables.getFirst(partition, null);
		// and get the last in partition
		List<String> lastList = Iterables.getLast(partition);

		// then we have 4 partitions (7/2+1)
		assertThat(partition).hasSize(4);
		// first partition exists
		assertThat(first).isNotNull();
		// and has 2 eleements
		assertThat(first).containsOnly("Polish", "English");
		// last list has only one element
		assertThat(Iterables.getOnlyElement(lastList)).isEqualTo("French");
	}

	@Test
	public void testGetAtPosition() {
		// given
		List<String> languages = Arrays.asList("Polish", "English", "Spanish",
				"German", "Greek", "Russian", "French");

		// when
		String stringAt3 = Iterables.get(languages, 3);

		// then we get element at 3rd position
		assertThat(stringAt3).isEqualTo("German");
	}

	@Test
	public void testEqual() {
		// given
		List<String> list = Arrays.asList("English", "Polish", "Spanish");
		Set<String> set = new TreeSet<String>(Arrays.asList("Polish",
				"English", "Spanish"));
		// when

		// then set is not equal to list
		assertThat(set.equals(list)).isFalse();
		// but is equal when iterating
		assertThat(Iterables.elementsEqual(set, list)).isTrue();
	}
}
