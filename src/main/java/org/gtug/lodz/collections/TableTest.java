package org.gtug.lodz.collections;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Table;
import com.google.common.collect.TreeBasedTable;

public class TableTest {

	@Test
	public void testTableCreation() {
		//given
		Table<String, String, Double> distance = TreeBasedTable.create();

		//when
		distance.put("Barcelona", "Berlin", 1497.61);
		distance.put("Barcelona", "Warsaw", 1862.33);
		distance.put("Barcelona", "Madrid", 504.64);
		
		Map<String, Double> barcelonaRow = distance.row("Barcelona");

		//then
		assertThat(barcelonaRow).hasSize(3);
		assertThat(distance.get("Barcelona", "Berlin")).isEqualTo(1497.61);
	}
	
	@Test
	public void testTableNonNullViews() {
		//given
		Table<String, String, Double> distance = TreeBasedTable.create();
		//when
		
		//then
		assertThat(distance.row("Warsaw")).isNotNull();
		assertThat(distance.column("Barcelona")).isNotNull();
	}
	
	
	@Test
	public void testTableManipulatingOnViews() {
		//given
		Table<String, String, Double> distance = TreeBasedTable.create();

		//when
		distance.put("Barcelona", "Berlin", 1497.61);
		distance.put("Barcelona", "Warsaw", 1862.33);
		distance.put("Barcelona", "Madrid", 504.64);

		Map<String, Double> londonRow = distance.row("London");
		distance.put("London", "Warsaw", 1445.85);
		londonRow.put("Istambul", 2496.39);

		Map<String, Double> barcelonaRow = distance.row("Barcelona");
		Map<String, Double> warsawColumn = distance.column("Warsaw");
		Map<String, Double> madridColumn = distance.column("Madrid");

		barcelonaRow.clear();
		
		warsawColumn.put("Paris", 1365.91);
		madridColumn.put("Paris", 1053.40);
		
		//then
		assertThat(barcelonaRow).isEmpty();
		assertThat(warsawColumn).hasSize(2);
		assertThat(londonRow).hasSize(2);
		assertThat(distance.row("Paris")).hasSize(2);
		assertThat(distance.get("Paris", "Warsaw")).isEqualTo(1365.91);
	}
}
