package org.gtug.lodz.collections;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class BiMapTest {
	private HashBiMap<String, String> capitals;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void setUp() {
		capitals = HashBiMap.create();

		capitals.put("Germany", "Berlin");
		capitals.put("Poland", "Warsaw");
		capitals.put("Spain", "Madrid");
	}

	@Test
	public void testBiMapCreationOnDuplicateValue() {
		// given
		// a map
		thrown.expect(IllegalArgumentException.class);
		// when

		capitals.put("Lithuania", "Warsaw");
		// then
	}

	@Test
	public void testBiMapInverse() {
		// given
		// when
		BiMap<String, String> inverse = capitals.inverse();

		// then
		assertThat(inverse.keySet()).containsOnly(
				(Object[]) capitals.values().toArray(
						new String[capitals.size()]));

		assertThat(inverse.values()).containsOnly(
				(Object[]) capitals.keySet().toArray(
						new String[capitals.size()]));
	}
}
