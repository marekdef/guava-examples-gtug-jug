package org.gtug.lodz.collections;

import java.util.Arrays;

import org.fest.util.Collections;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.internal.util.ArrayUtils;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import static org.fest.assertions.Assertions.*;

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
						new String[capitals.values().size()]));
	}
	
	

}
