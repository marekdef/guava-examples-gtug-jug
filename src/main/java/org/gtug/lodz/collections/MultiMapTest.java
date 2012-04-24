package org.gtug.lodz.collections;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableSet;

public class MultiMapTest {
	private HashMultimap<String, String> family;

	@Before
	public void setUp() {
		family = HashMultimap.create();

		family.put("Marek", "Ania");

		family.put("Maria", "Eugeniusz");
		family.put("Maria", "Marek");

		family.put("Eugeniusz", "Maria");
		family.put("Eugeniusz", "Marek");
		family.put("Eugeniusz", "Marek");

		family.get("Krzysiek").add("Ania");
		family.get("Piotr").remove("Ania");
	}

	@Test
	public void testMultiMapCreation() {
		assertThat(family.get("Krzysiek")).contains("Ania");
		assertThat(family.get("Marek").size()).isEqualTo(1);
		assertThat(family.get("Maria").size()).isEqualTo(2);
		assertThat(family.get("Eugeniusz").size()).isEqualTo(2);
	}

	@Test
	public void testMultiMapNoNullsValues() {
		// LegacyMultiMap<String, String> family = LegacyMultiMap.create();
		// when

		// then
		assertThat(family.get("Ania")).isNotNull();
		assertThat(family.get("Piotr").contains("Ania")).isFalse();
	}

	@Test
	public void testMultiMapAdditionOnViews() {

		// when
		family.get("Piotr").addAll(ImmutableSet.of("Ania", "Krzysiek"));
		// then
		assertThat(family.get("Piotr")).contains("Krzysiek", "Ania");
	}

	@Test
	public void testMultiMapOnNull() {
		// given
		// when
		family.put(null, null);
		// then
		assertThat(family.get(null)).hasSize(1);
		assertThat(family.get(null)).containsOnly(new Object[] { null });
	}

}
