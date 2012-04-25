package org.gtug.lodz.collections;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Table;
import com.google.common.collect.TreeBasedTable;

public class TableTest {

	@Test
	public void testTableCreation() {
		// given a table
		Table<String, String, Double> distance = TreeBasedTable.create();

		// when filling data
		distance.put("Barcelona", "Berlin", 1497.61);
		distance.put("Barcelona", "Warsaw", 1862.33);
		distance.put("Barcelona", "Madrid", 504.64);

		// obtaining a row
		Map<String, Double> barcelonaRow = distance.row("Barcelona");

		// then we can query rows
		assertThat(barcelonaRow).hasSize(3);
		// for elements
		assertThat(barcelonaRow.get("Warsaw")).isEqualTo(1862.33);
		// and a table directly
		assertThat(distance.get("Barcelona", "Berlin")).isEqualTo(1497.61);
	}

	@Test
	public void testTableNonNullViews() {
		// given
		Table<String, String, Double> distance = TreeBasedTable.create();
		// when

		// then we get not null rows for not existing rows
		assertThat(distance.row("Warsaw")).isNotNull();
		assertThat(distance.column("Barcelona")).isNotNull();
	}

	@Test
	public void testTableManipulatingOnViews() {
		// given
		Table<String, String, Double> distance = TreeBasedTable.create();

		// when
		distance.put("Barcelona", "Berlin", 1497.61);
		distance.put("Barcelona", "Warsaw", 1862.33);
		distance.put("Barcelona", "Madrid", 504.64);

		// we obtain a row (view)
		Map<String, Double> londonRow = distance.row("London");
		// update table directly
		distance.put("London", "Warsaw", 1445.85);
		// update using view
		londonRow.put("Istambul", 2496.39);

		Map<String, Double> barcelonaRow = distance.row("Barcelona");
		Map<String, Double> warsawColumn = distance.column("Warsaw");
		Map<String, Double> madridColumn = distance.column("Madrid");

		// we clear barcelona
		barcelonaRow.clear();

		// update warsaw View
		warsawColumn.put("Paris", 1365.91);
		madridColumn.put("Paris", 1053.40);

		// then barcelona is empty
		assertThat(barcelonaRow).isEmpty();
		// warsaw gets connection from London and Paris but not Barcelona
		assertThat(warsawColumn.keySet()).containsOnly("Paris","London");
		// london row updated in mixed mode is trully updated 
		assertThat(londonRow).hasSize(2);
		assertThat(distance.row("Paris")).hasSize(2);
		assertThat(distance.get("Paris", "Warsaw")).isEqualTo(1365.91);
	}
}
