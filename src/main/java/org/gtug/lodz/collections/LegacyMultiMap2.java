package org.gtug.lodz.collections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LegacyMultiMap2<K, V> {
	private Map<K, Set<V>> internal = new HashMap<K, Set<V>>();

	public Set<V> get(K key) {
		Set<V> existingValue = internal.get(key);

		if (existingValue != null)
			return existingValue;

		Set<V> value = new HashSet<V>();
		internal.put(key, value);

		return value;
	}

	public void put(K key, V value) {
		Set<V> aSet = internal.get(key);
		if (aSet == null) {
			aSet = new HashSet<V>();
			internal.put(key, aSet);
		}

		aSet.add(value);
	}

	public void remove(K key, V value) {
		Set<V> aSet = internal.get(key);
		if (aSet != null) {
			aSet.remove(value);
		}
	}

	public static <K, V> LegacyMultiMap2<K, V> create() {
		return new LegacyMultiMap2<K, V>();
	}
}
