package org.gtug.collections;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableSet;

public class MultiMapTest {

	public HashMultimap<String, String> create() {
		HashMultimap<String, String> family = HashMultimap.create();

		family.put("Marek", "Ania");

		family.put("Maria", "Eugeniusz");
		family.put("Maria", "Marek");

		family.put("Eugeniusz", "Maria");
		family.put("Eugeniusz", "Marek");
		family.put("Eugeniusz", "Marek");

		// does not blow even Piotr does not exist before
		family.get("Piotr").remove("Ania");

		return family;
	}

	@Test
	public void testMultiMapCreation() {
		//given created map 
		HashMultimap<String, String> family = create();
		//when
		
		//then is correcly created
		assertThat(family.get("Marek")).hasSize(1);
		assertThat(family.get("Maria")).hasSize(2);
		assertThat(family.get("Eugeniusz")).hasSize(2);
		assertThat(family.get("Krzysiek")).isNotNull();
	}

	@Test
	public void testMultiMapNoNullsValues() {
		// given
		HashMultimap<String, String> family = create();
		// when

		// then
		assertThat(family.get("Ania")).isNotNull();
		assertThat(family.get("Piotr").contains("Ania")).isFalse();
	}

	@Test
	public void testMultiMapAdditionOnViews() {
		// given
		HashMultimap<String, String> family = create();
		// when operating on view
		family.get("Piotr").addAll(ImmutableSet.of("Ania", "Krzysiek"));
		// then we get all added
		assertThat(family.get("Piotr")).contains("Krzysiek", "Ania");
	}

	@Test
	public void testMultiMapOnNull() {
		// given
		HashMultimap<String, String> family = create();
		// when putting null values
		family.put(null, null);
		// then we get a set containing null
		assertThat(family.get(null)).hasSize(1);
		assertThat(family.get(null)).containsOnly(new Object[] { null });
	}

}
