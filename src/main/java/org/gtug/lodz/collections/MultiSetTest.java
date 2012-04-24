package org.gtug.lodz.collections;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import com.google.common.collect.HashMultiset;

public class MultiSetTest {

	
	@Test
	public void testMultiSet() {
		//given
		HashMultiset<String> words = HashMultiset.create();
//		LegacyMultiSet<String> words = LegacyMultiSet.create();
		//when
		words.add("GTUG");
		words.add("GTUG", 5);
		
		words.add("JUG");
		words.remove("JUG", 2);
		
		//then
		assertThat(words.count("GTUG")).isEqualTo(6);
		assertThat(words.count("JUG")).isEqualTo(0);
		assertThat(words.count("Lodz")).isEqualTo(0);
	}
}
