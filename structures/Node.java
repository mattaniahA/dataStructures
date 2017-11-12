public class Node<K extends Comparable<K>, T> {
	K key;
	T data;
	Node<K, T> left;
	Node<K, T> right;
	
	Node(K k, T o){
		key = k;
		data = o;
	}
	
	public String toString(){
		return "(" + key + ", " + data + ")";
	}

	
	public boolean equals(Object o){
		if(o instanceof Node<?,?>){
			if(this.key.equals( ((Node<?,?>)o).key) )
				return true;
		}
		return false;
	}
	
}
