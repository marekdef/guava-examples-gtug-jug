package org.gtug.lodz.collections;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Map;

import org.junit.Test;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class TableTest {

	@Test
	public void test() {
		//given
		Table<String, String, Double> distance = HashBasedTable.create();

		//when
		distance.put("Barcelona", "Berlin", 1497.61);
		distance.put("Barcelona", "Warsaw", 1862.33);
		distance.put("Barcelona", "Madrid", 504.64);

		Map<String, Double> london = distance.row("London");
		distance.put("London", "Warsaw", 1445.85);

		Map<String, Double> barcelona = distance.row("Barcelona");
		Map<String, Double> warsaw = distance.column("Warsaw");
		Map<String, Double> madrid = distance.column("Madrid");

		warsaw.put("Paris", 1365.91);
		madrid.put("Paris", 1053.40);
		
		//then
		assertThat(barcelona.size()).isEqualTo(3);
		assertThat(distance.row("Warsaw")).isNotNull();
		assertThat(warsaw.size()).isEqualTo(3);
		assertThat(london.size()).isEqualTo(1);
		assertThat(distance.row("Paris").size()).isEqualTo(2);
		assertThat(distance.get("Paris", "Warsaw")).isEqualTo(1365.91);

	}

}
