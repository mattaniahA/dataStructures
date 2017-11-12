import java.util.Comparator;

public class Heap <K extends Comparable <K>, T> {

	Node<K,T>[] storage;
	int bottom;
	Comparator<K> comp;
	Comparator<K> minComp = new Comparator<K> () {
		public int compare(K k1, K k2) {
			return k2.compareTo(k1);
		}
	};


	Heap(int capacity){
		if(capacity < 1)
			throw new IllegalArgumentException("Heaps's capacity must be postive");
		storage = new Node[capacity];
		bottom = 0;
		comp = new Comparator<K> () {
			public int compare(K k1, K k2) {
				return k1.compareTo(k2);
			}
		}; 
	}//end of heap


	void insert(Node<K, T> inserted){
		if(bottom > storage.length)
			throw new RuntimeException("Cannot insert. Heap is full");
		storage[bottom] = inserted;
		int child = bottom;
		bottom++;

		while(child > 0){
			int parent = (child-1)/2;
			if(comp.compare(storage[parent].key, storage[child].key) < 0){
				Node<K,T> paNo = storage[parent];
				Node<K,T> chNo = storage[child];
				storage[parent] = chNo;
				int paIn = parent;
				parent = child;
				storage[child] = paNo;
				child = paIn;
			}else{
				break;
			}
		}
	}

	void insert(K ke, T val){
		Node<K,T> node = new Node<K,T>(ke, val);
		insert(node);
	}

	Node<K,T> remove(){
		if(storage.length == 0)
			return null;

		Node<K,T> root = storage[0];
		Node<K,T> last = storage[bottom-1];
		bottom--;
		if(storage.length==0)
			return root;
		int parent = 0;
		storage[parent] = last;

		while(2*parent+1 < bottom){
			int child1 = 2*parent+1;
			K key1 = storage[child1].key;
			K keyChosen = key1;
			int childChosen = child1;
			int child2 = 2*parent+2;
			if(child2<bottom){
				K key2 = storage[child2].key;
				if(comp.compare(key1, key2) < 0){
					keyChosen = key2;
					childChosen = child2;
				}
			}			
			if(comp.compare(keyChosen, storage[parent].key)>0){
				Node<K,T> temp = storage[parent];
				storage[parent] = storage[childChosen];
				storage[childChosen] = temp;
				parent = childChosen;
			}else
				break;
		}//end of while
		return root;
	}


	Object peek(){
		if(bottom == 0)
			return null;
		return storage[storage.length-1];
	}


	Boolean isEmpty(){
		if(storage.length==0)
			return true;
		return false;
	}


	Boolean isFull(){
		if(bottom>storage.length)
			return true;
		return false;
	}

	public String toString(){
		String temp = "";
		int nextLevel = 2;
		for (int i = 0; i < bottom; i++) {
			if (i == nextLevel-1) {
				temp += "\n";
				nextLevel *= 2;
			}
			temp += storage[i];

		}
		temp += "";

		return temp;
	}


	public void setComparator(Comparator<K> c){
		/*TODO choose a different Comparator to be used 
		 * indicated by c   (for example, for MinHeap)*/
		minComp = c;
	}


}
