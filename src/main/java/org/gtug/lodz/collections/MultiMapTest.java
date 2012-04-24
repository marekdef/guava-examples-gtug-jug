package org.gtug.lodz.collections;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class MultiMapTest {
	@Test
	public void testMultMap() {
//		HashMultimap<String, String> family = HashMultimap.create();
		LegacyMultiMap<String, String> family = LegacyMultiMap.craete();
		//when
		family.put("Marek", "Ania");
		
		family.put("Maria", "Eugeniusz");
		family.put("Maria", "Marek");
		
		family.put("Eugeniusz", "Maria");
		family.put("Eugeniusz", "Marek");
		family.put("Eugeniusz", "Marek");
		
		family.get("Krzysiek").add("Ania");
		family.get("Piotr").remove("Ania");
		
		//then
		assertThat(family.get("Ania")).isNotNull();
		assertThat(family.get("Krzysiek")).contains("Ania");
		assertThat(family.get("Marek").size()).isEqualTo(1);
		assertThat(family.get("Maria").size()).isEqualTo(2);
		assertThat(family.get("Eugeniusz").size()).isEqualTo(2);
		assertThat(family.get("Piotr").contains("Ania")).isFalse();
	}
}
