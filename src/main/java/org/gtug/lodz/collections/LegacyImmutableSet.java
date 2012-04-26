package org.gtug.lodz.collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class LegacyImmutableSet {
	public static final Set<String> COUNTRIES;

	static {
		Set<String> internal = new HashSet<String>();
		internal.add("Poland");
		internal.add("Germany");
		internal.add("France");
		internal.add("Spain");
		internal.add("Greece");

		COUNTRIES = Collections.unmodifiableSet(internal);
	}

	static {
		//COUNTRIES = 
		Collections.unmodifiableSet(new HashSet<String>(Arrays.asList("Poland",
				"Germany", "France", "Spain", "Greece")));
	}
}
