package hashing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.AbstractMap.SimpleEntry;

/**
 * An academic map that does not support null keys
 * or values and makes no guarantees about run
 * time.
 */
public class MyHashMap<K, V>
{
	private ArrayList<LinkedList<Map.Entry<K, V>>> theBuckets;
	private int size; // number of mappings
	private static final int CAPACITY = 100; // number

	// of
	// buckets

	public MyHashMap()
	{
		theBuckets = new ArrayList<LinkedList<Map.Entry<K, V>>>(CAPACITY);

		for (int i = 0; i <= CAPACITY; i++)
			theBuckets.add(new LinkedList<Map.Entry<K, V>>());
	}

	public int size()
	{
		return size;
	}

	public boolean containsKey(Object key)
	{
		return getEntry(key) != null;
	}

	public V put(K key, V value)
	{
		if (key == null || value == null) 
			throw new IllegalArgumentException("no null keys or values allowed");

		if (getIndex(key) != -1)
		{
			theBuckets.get(getIndex(key)).add(new SimpleEntry<K, V>(key, value));
			size++;
		}
		else 
			return null;
		return value;
	}

	public V get(Object key)
	{
		if (getIndex(key) == -1 || getEntry(key) == null)
			return null;
		return getEntry(key).getValue();
	}

	public V remove(Object key)
	{
		if (key == null || getIndex(key) == -1) 
			return null;
		Map.Entry<K, V> entry = getEntry(key);

		theBuckets.remove(getEntry(key));
		size--;
		return entry.getValue();
	}

	public Set<K> keySet()
	{
		Set<K> keys = new HashSet<K>();
		LinkedList<Map.Entry<K, V>> bucket;
		for (int i = 0; i < theBuckets.size(); i++)
		{
			bucket = theBuckets.get(i);

			if (bucket.size() != 0) 
				for (int j = 0; j < bucket.size(); j++)
					keys.add(bucket.get(j).getKey());
		}
		return keys;
	}

	/**
	 * @return the bucket of the key or -1 if null.
	 */
	private int getIndex(Object key)
	{
		if (key == null)
			return -1;
		int hash = key.hashCode();
		if (hash > CAPACITY) 
		{
			while (hash > CAPACITY)
				hash = hash % 10;
			return hash;
		}	
			
		return key.hashCode();
	}

	/**
	 * @return the value that matches the key
	 */
	private Map.Entry<K, V> getEntry(Object key)
	{
		LinkedList<Map.Entry<K, V>> bucket = theBuckets.get(getIndex(key));
		for (int i = 0; i < bucket.size(); i++)
			if (bucket.get(i).getKey().equals(key)) 
				return bucket.get(i);
		return null;
	}
}