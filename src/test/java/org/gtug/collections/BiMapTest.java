package org.gtug.collections;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.MapAssert.entry;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class BiMapTest {
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	public HashBiMap<String, String> create() {
		HashBiMap<String, String> capitals = HashBiMap.create();

		capitals.put("Germany", "Berlin");
		capitals.put("Poland", "Warsaw");
		capitals.put("Spain", "Madrid");

		return capitals;
	}

	@Test
	public void testBiMapCreationOnDuplicateValue() {
		// given
		HashBiMap<String, String> capitals = create();
		thrown.expect(IllegalArgumentException.class);

		// when we put already existing value
		capitals.put("Lithuania", "Warsaw");

		// then test fails if no exception
	}

	@Test
	public void testBiMapInverse() {
		// given
		HashBiMap<String, String> capitals = create();
		// when inverting a BiMap
		BiMap<String, String> inverse = capitals.inverse();

		// then we get inverted entries
		assertThat(inverse).includes(entry("Warsaw", "Poland"),
				entry("Berlin", "Germany"), entry("Spain", "Madrid"));
		// some unreadable assertions that array are invers
		assertThat(inverse.keySet()).containsOnly(
				(Object[]) capitals.values().toArray(
						new String[capitals.size()]));

		assertThat(inverse.values()).containsOnly(
				(Object[]) capitals.keySet().toArray(
						new String[capitals.size()]));
	}
}
