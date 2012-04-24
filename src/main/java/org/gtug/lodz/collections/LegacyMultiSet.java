package org.gtug.lodz.collections;

import java.util.HashMap;
import java.util.Map;

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
		Integer count = objectCounts.get(obj);
		if (count == null) {
			objectCounts.put(obj, 0);
		} else if (count > 0){
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
}
