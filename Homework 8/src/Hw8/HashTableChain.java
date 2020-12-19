package Hw8;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class HashTableChain<K, V> implements Map<K,V> {
	private LinkedList <Entry<K,V>>[] table;
	private static final int CAPACITY = 101;
	private double LOAD_THRESHOLD = 3.0; 
	private int numKeys; 
	
	@SuppressWarnings("unchecked")
	public HashTableChain() {
		table = new LinkedList[CAPACITY];
		numKeys = 0;
	}
	
	@Override
	public V get(Object key) { 
	int index = key.hashCode() % table.length;
		if (index < 0) 
			index += table.length; 
		if (table[index] == null) 
			return null;
		for (Entry<K, V> nextItem : table[index]) { 
			if (nextItem.getKey().equals(key))
				return nextItem.getValue(); 
		}
		return null;
	}
	
	public V put(K key, V value) {
		 int index = (key.hashCode()) % table.length;
		 	if (index < 0) 
		 		index += table.length;
		 	if (table[index] == null) {
		 		table[index] = new LinkedList<>();
           }
		 	for (Entry<K, V> nextItem : table[index]) { 
		 		if (nextItem.getKey().equals(key)) { 
		 V oldVal = nextItem.getValue(); 
		 nextItem.setValue(value); 
		 return oldVal; 
		 	}
		 }
		 table[index].add(new Entry<K, V>(key, value));
		 numKeys++;
		 if (numKeys > (LOAD_THRESHOLD * table.length))
		 rehash();
		 return null;
		}
	
	@SuppressWarnings("unchecked")
	private void rehash() {
		LinkedList<Entry<K, V>>[] oldTable = table; 
		table = new LinkedList[(int) (table.length * LOAD_THRESHOLD)];
		numKeys = 0;
		for(LinkedList<Entry<K, V>> list: oldTable) {
			if(list != null) {
				for(Entry<K, V> entry: list) { 
					put(entry.getKey(), entry.getValue()); 
				}
			}
		}
		
	}

	@Override
	public int size() {
		return numKeys;
	}

	@Override
	// checks the number of keys to see if the table is empty
	public boolean isEmpty() {
		if (numKeys == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean containsKey(Object key) {
		int index = key.hashCode() % table.length; 
		if(table[index] == null) {
		return false;
		}		
		else {
			for(Entry<K, V> entry : table[index]) {
				if(entry.getKey().equals(key)) { 
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		boolean flag = false; 
		for (LinkedList<Entry<K,V>> list : table) { 
			if(list != null) { 
			for(Entry<K,V> entry : list) {
				if(entry.getValue().equals(value)) {
					flag = true; 
				}
			}
		}
	}	
		return flag; 
	}

	@Override
	public V remove(Object key) {
		int index = key.hashCode() % table.length;
		V oldValue = null;
		if(table[index] == null) {
		return null;
		}
		for (int i = 0; i < table[index].size(); i++) { 
			if(table[index].get(i).getKey().equals(key)) { 
				oldValue = table[index].get(i).getValue(); 
				table[index].remove(i);
			}
		}
		return oldValue;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub
	}

	public int hashCode() {
		int total = 0;
		for (LinkedList<Entry<K, V>> list : table) { 
			if (list != null) {
				for (Entry<K, V> entry : list) { 
					total += entry.hashCode(); 
				}
			}
		}
		return total;
	}
	
	public boolean equals(Object o) {
		@SuppressWarnings("unchecked")
		Map<String, Integer> other = (Map<String, Integer>) o; 
		for (LinkedList<Entry<K, V>> list : table) { 
			if (list != null) { 
				for (Entry<K, V> entry : list) { 
					if (!entry.getValue().equals(other.get(entry.getKey()))) {
						return false;
					}
				}
			}
		}
		return true;
	}
	@Override
	public void clear() { 
		for (int i = 0; i < table.length; i++) { 
			if (table[i] != null) { 
			table[i].clear();
			}
		}
		numKeys = 0;
	}

	@Override
	public Set<K> keySet() {
		Set<K> set = new HashSet<K>();
		for(LinkedList<Entry<K, V>> list : table) { 
			if (list != null) { 
				for (Entry<K, V> entry : list) { 
					set.add(entry.getKey()); 
				}
			}
		}
		return set;
	}

	@Override
	public Collection<V> values() {
		return null;
	}

	@Override
	public Set<Map.Entry<K, V>> entrySet() {
		return new EntrySet();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("["); 
		for (LinkedList<Entry<K,V>> list : table) {
			if (list != null) { 
				for(Entry<K, V> entry : list) { 
					if (entry == null) { 
					} else {
						sb.append(" " + entry + ",");
					}
				}
			}
		}
		sb.deleteCharAt(1); 
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
		return sb.toString();
	}
	
	
	//mostly from the book
private static class Entry<K, V> implements Map.Entry<K, V> {
	private K key;
	private V value;
	
	private Entry(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	//Key getter
	public K getKey() {
		return key;
	}
	//Value getter
	public V getValue() {
		return value;
	}
	//Value setter
	public V setValue(V val) {
		V oldVal = value;
		value = val;
		return oldVal;
	}
	//Uses Java's hashcode
	public int hashCode() {
		return (key.hashCode() ^ value.hashCode());
	}
	//basic toString
	public String toString() {
		return key + "=" + value;
	}
}

private class EntrySet extends AbstractSet<Map.Entry<K, V>> {
	
	public Iterator<Map.Entry<K, V>> iterator() {
		return new SetIterator();
	}
	
	//returns number of keys
	public int size() {
		return numKeys;
	}
}

private class SetIterator implements Iterator<Map.Entry<K, V>> {
	private int index = 0;
	private Entry<K, V> lastItemReturned = null;
	private Iterator<Entry<K, V>> iter = null;
	
	public boolean hasNext() {
		if (iter != null && iter.hasNext()) {
			return true;
		}
		do {
			index++;
			if (index >= table.length) {
				return false;
			}
			} while (table[index] == null);
				iter = table[index].iterator();
				index++;
				return iter.hasNext();
		}
	public Map.Entry<K, V> next() {
		if (hasNext()) {
			lastItemReturned = iter.next();
			return lastItemReturned;
		} else {
			throw new NoSuchElementException();
		}
		}
	}
}

