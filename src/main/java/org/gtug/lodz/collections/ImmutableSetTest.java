package org.gtug.lodz.collections;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

public class ImmutableSetTest {
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private static final ImmutableSet<String> COUNTRIES = ImmutableSet.of(
			"Poland", "Germany", "France", "Spain", "Greece");

	@Test
	public void testImmutability() {
		// given?
		thrown.expect(UnsupportedOperationException.class);
		// when
		COUNTRIES.add("Portugal");
		// then
	}

	@Test
	public void testImmutabilityOnView() {
		// given?
		thrown.expect(UnsupportedOperationException.class);

		// when?
		ImmutableList<String> asList = COUNTRIES.asList();
		asList.add("Portugal");

		// then
	}

	@Test
	public void testImmutabilitySmart() {
		// given

		// when
		ImmutableSet<String> copyOf = ImmutableSet.copyOf(COUNTRIES);

		// then
		assertThat(copyOf).isSameAs(COUNTRIES);
	}

	@Test
	public void testImmutableEqual() {
		//given
		ImmutableSet<String> copyOf = ImmutableSet.of("Greece", "Spain", "France", "Germany", "Poland");
		//when

		//then
		assertThat(copyOf).isEqualTo(COUNTRIES);
	}
}
