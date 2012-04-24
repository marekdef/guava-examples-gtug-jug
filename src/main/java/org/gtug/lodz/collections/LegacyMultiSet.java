package org.gtug.lodz.collections;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Preconditions;

public class LegacyMultiSet<E> {
	public static <E> LegacyMultiSet<E> create() {
		return new LegacyMultiSet<E>();
	}

	private LegacyMultiSet() {

	}

	Map<E, Integer> objectCounts = new HashMap<E, Integer>();

	public void add(E obj) {
		Integer count = objectCounts.get(obj);
		if (count == null) {
			objectCounts.put(obj, 1);
		} else {
			objectCounts.put(obj, ++count);
		}
	}

	public void add(E obj, int times) {
		for (int i = 0; i < times; ++i) {
			add(obj);
		}
	}

	public void remove(E obj) {
		Integer count = objectCounts.remove(obj);
		if (count != null && count > 1) {
			objectCounts.put(obj, --count);
		}
	}

	public void remove(E obj, int times) {
		for (int i = 0; i < times; ++i) {
			remove(obj);
		}
	}

	public int count(E obj) {
		Integer count = objectCounts.get(obj);
		if (count == null) {
			return 0;
		} else {
			return count;
		}
	}

	public Set<E> elementSet() {
		return Collections.unmodifiableSet(objectCounts.keySet());
	}

	public void setCount(E object, int count) {
		Preconditions.checkArgument(count >= 0,
				"Arguments should be at least zero %d given", count);
		if (count == 0)
			objectCounts.remove(object);
		else
			objectCounts.put(object, count);

	}
}
