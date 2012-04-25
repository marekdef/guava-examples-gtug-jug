package org.gtug.lodz.guava.strings;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;

public class JoinerTest {
	@Test
	public void testJoinerWithNull() {
		// given creating joiner with spaces
		Joiner joiner = Joiner.on(" ").skipNulls();

		// when we join six word including null
		String joint = joiner
				.join("GTUG", "Łódź", "meets", "JUG", null, "Łódź");

		// then we get spaces and don't get null
		assertThat(joint).isEqualTo("GTUG Łódź meets JUG Łódź");
	}

	@Test
	public void testJoinerOnObject() {
		// given created joiner with new lines and null! instead of null
		Joiner joiner = Joiner.on("\n").useForNull("NULL!");

		// when we join some
		String joint = joiner.join(new Person("Marek", "Defeciński", 32), null);

		// then null is substituted
		assertThat(joint).contains("NULL!");
		// and object is formatted
		assertThat(joint).contains("Marek Defeciński 32");
	}

	private static class Person {
		private String first;
		private String last;
		private int age;

		public Person(String first, String last, int age) {
			this.first = first;
			this.last = last;
			this.age = age;
		}

		@Override
		public String toString() {
			return String.format("%s %s %d", first, last, age);
		}

		@Override
		public int hashCode() {
			return Objects.hashCode(first, last, age);
		}

	}
}
