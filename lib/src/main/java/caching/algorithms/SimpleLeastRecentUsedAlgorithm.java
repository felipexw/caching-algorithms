/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package caching.algorithms;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class SimpleLeastRecentUsedAlgorithm<T> {

	private final LinkedHashSet<T> cache;
	private final Integer capacity;

	public SimpleLeastRecentUsedAlgorithm(Integer capacity) {
		this.capacity = capacity;
		cache = new LinkedHashSet<>(capacity);
	}

	public void add(T t) {

		if (cache.size() == capacity) {
			T firstKey = cache.iterator().next();
			cache.remove(firstKey);
		}

		cache.add(t);
	}

	public String lru() {
		final LinkedList<T> values = new LinkedList<>(this.cache);
		final Iterator<T> iterator = values.descendingIterator();

		final StringBuilder strBuilder = new StringBuilder();
		while (iterator.hasNext()) {
			strBuilder.append(" "  + iterator.next() + " ");
		}

		return strBuilder.toString();
	}

}
