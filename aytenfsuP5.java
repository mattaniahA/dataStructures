import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;


public class aytenfsuP5 {

	static public class Stack {			// implements a stack data structure using arrays

		int size;						// size of stack where next element is placed
		private Object[] storage;

		Stack(int capacity){		// instantiates array with the size 'capacity'
			if(capacity < 1)
				throw new IllegalArgumentException("Stack's capacity must be postive");
			storage = new Object[capacity];
			size = 0;
		}


		void push(Object value){	// pushes new object onto the stack and throws exception if stack size reaches max capacity
			if(size > storage.length){
				size = storage.length;
				throw new ArrayIndexOutOfBoundsException("Stack overflow");
			}
			storage[size] = value;
			size++;
		}


		Object pop(){		// returns the last object that was pushed onto the stack, throws exception if stack is empty
			if(size <= 0){
				size = 0;
				throw new RuntimeException("Stack is empty");
			}
			size--;
			return storage[size];
		}


		Object peek(){		// checks to see what was the last thing pushed onto the stack
			if(size <= 0){
				size = 0;
				throw new RuntimeException("Stack is empty");
			}
			return storage[size-1];
		}


		Boolean isEmpty(){		// checks if stack is empty
			if(size <= 0){
				size = 0;
				return true;
			}
			return false;
		}


		Boolean isFull(){		// checks if the stack is overflown (ha ha)
			if(size >= storage.length){
				size = storage.length;
				return true;
			}	
			return false;
		}


		public String toString(){
			String temp = "";
			for(int i=0; i<size; i++){
				temp += storage[i] + " / ";
			}
			return temp;
			/*
			  	0 / 1 / 2 / 3 / 4 / 5
				indicates a stack with elements 0, 1, 2, 3, 4, 5
			 */
		}

	}


	static public class Queue {

		int front;					// front of stack where next element is placed
		int length;					
		int capacity;				
		private Object[] storage;

		Queue(int capacit){			// instantiates queue with the size 'capacity'
			if(capacit < 1)
				throw new IllegalArgumentException("Queue's capacity must be postive");
			capacity = capacit;
			storage = new Object[capacit];
			front = 0;
			length = 0;
		}

		void enqueue(Object value){
			if(length > storage.length){		// Check for queue overflow, throw exception if overflow
				length = storage.length;
				throw new RuntimeException("Queue overflow");
			}
			int back = (front + length) % capacity;		// uses an array as a circular buffer to save memory space
			storage[back] = value;				// Place object on back of the queue
			length++;	
		}

		Object dequeue(){
			if(length <= 0){		// Check for empty queue, throw exception if empty
				length = 0;
				throw new RuntimeException("Queue is empty");
			}
			Object temp = storage[front];
			front++;
			front = front % storage.length;			// Update queue front index, mod capacity to get circular
			length--;
			return temp;
		}

		Object peek(){
			if(length <= 0){			// Check for empty queue, throw exception if empty
				length = 0;
				throw new RuntimeException("Queue is empty");
			}
			return storage[front];
		}

		Boolean isEmpty(){
			if(length <= 0){
				length = 0;
				return true;
			}
			return false;
		}

		Boolean isFull(){
			if(length >= storage.length){
				length = storage.length;
				return true;
			}
			return false;
		}

		public String toString(){
			String temp = "";
			temp += "(" + front + ", " + length + ")  ";
			for(int i=0; i<length; i++){
				temp += storage[(front + i) % storage.length] + " / ";
			}
			return temp;
			/*
			  	(6,4) 6.0 / 7.0 / 8.0 / 9.0
				indicates a queue with front = 6 and length = 4 with elements
				 6.0, 7.0, 8.0, 9.0 in the queue
			 */
		}

	}


	static public class Heap <K extends Comparable <K>, T> {

		Node<K,T>[] storage;
		int bottom;					// bottom of the heap
		Comparator<K> comp;			
		Comparator<K> minComp = new Comparator<K> () {		// // used to compare objects for  min heap
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
		}


		void insert(Node<K, T> inserted){
			if(bottom > storage.length)
				throw new RuntimeException("Cannot insert. Heap is full");
			
			storage[bottom] = inserted; 		// adding node to the bottom of the storage guarantees left-to-right on last level
			int child = bottom;
			bottom++;

			while(child > 0){			// swap node with its parent until child hasn't reached the top of the storage
				int parent = (child-1)/2;		// parent index of the child element
				if(comp.compare(storage[parent].key, storage[child].key) < 0){		// if parentKey < childKey: percolate up
					Node<K,T> paNo = storage[parent];
					Node<K,T> chNo = storage[child];
					storage[parent] = chNo;
					int paIn = parent;
					parent = child;
					storage[child] = paNo;
					child = paIn;
				}else{
					break;			// settle at the right place
				}
			}
			
		}

		void insert(K ke, T val){		// insert a node made of key and value
			Node<K,T> node = new Node<K,T>(ke, val);
			insert(node);
		}

		Node<K,T> remove(){
			if(storage.length == 0)		// if heap is empty 
				return null;

			Node<K,T> root = storage[0];			// top of storage
			Node<K,T> last = storage[bottom-1];		// bottom of storage
			bottom--;						// to remove the bottom node
			if(storage.length==0)		// if storage empty after removal
				return root;
			int parent = 0;		// set parent index to top of the storage
			storage[parent] = last;

			while(2*parent+1 < bottom){		// while parent index has at least one child
				int child1 = 2*parent+1;	// calculate first child index
				K key1 = storage[child1].key;
				K keyChosen = key1;
				int childChosen = child1;
				int child2 = 2*parent+2;
				
				if(child2<bottom){			// if top node has a second child (if storage contains second child index)
					K key2 = storage[child2].key;		// get the key of the second child
					
					if(comp.compare(key1, key2) < 0){ 	//percolating up
						keyChosen = key2;
						childChosen = child2;
					}
				}			
				if(comp.compare(keyChosen, storage[parent].key)>0){		// if key of chosen child &gt; key of parent (same as key of node)
					Node<K,T> temp = storage[parent];		//percolate DOWN
					storage[parent] = storage[childChosen];
					storage[childChosen] = temp;
					parent = childChosen;
				}else
					break;			// settle at the right place
			}//end of while
			return root;
		}


		Object peek(){		// returns object on top of storage
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
			
			/*
			 	(93,48.8)
				(53,43.6)(86,48.2)
				(1,76.1)(33,15.5)(5,89.5)
				represents a MaxHeap with keys 53, 1, 5, 33, 93, 86
			 */
		}


		public void setComparator(Comparator<K> c){
			/*choose a different Comparator to be used 
			 * indicated by c   (for example, for MinHeap)*/
			minComp = c;
		}


	}

	static public class PriorityQueue <K extends PQKey<?>, T> extends Heap<PQKey<?>, T>{

		PriorityQueue(int capacity){
			super(capacity);
		}
	}


	static public class PQKey<K extends Comparable<K>> implements Comparable<PQKey<K>> {

		K level;			// indicates priority level
		int keyOrder;
		static int keyCount;

		PQKey(K k){
			level = k;
			keyOrder = keyCount;
			keyCount++;
		}

		public int compareTo(PQKey<K> k){				// compares the levels and if they're not equal, return the result
			int temp =this.level.compareTo(k.level);	// if they are equal, compare the key order and return the result 
			return (temp==0) ? k.keyOrder-this.keyOrder : temp;
		}

		public String toString(){
			return level.toString() + "[" + keyOrder + "]";
			/*
			 	4[10]
				indicate a PQKey with level = 4 and key order = 10
			 */
		}

	}
	
	static public class Node<K extends Comparable<K>, T> {
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
	
	static public class TestAll {
		static Random rd;
		static Scanner sc;

		static final int N = 10;

		public static void main(String[] args) {
			rd = new Random(20);

			testStack(); 
			testQueue();

			//Max heap 
			System.out.println("Max Heap Test");
			Heap<Integer, Double> heap1 = new Heap<Integer, Double>(100);
			testHeap(heap1);
			System.out.println();

			//Min heap 
			System.out.println("Min Heap Test");
			Heap<Integer, Double> heap2 = new Heap<Integer, Double>(100);
			heap2.setComparator(heap2.minComp);
			testHeap(heap2);
			System.out.println();

			//Max PQ 
			System.out.println("Max Priority Queue Test");
			PriorityQueue<PQKey<Integer>, Double> heap3 = 
					new PriorityQueue<PQKey<Integer>, Double>(100);
			testPQ(heap3);
			System.out.println();
		}

		static void testStack() {
			Stack stack = new Stack(N);

			for (int i = 0; i < N; i++) {
				stack.push(i);
				System.out.println(stack);
			}

			final int M = 12;
			for (int i = 0; i < M; i++) {
				try {
					System.out.print(stack.pop() + " pop: ");
				} catch (RuntimeException re) {
					System.out.println(re);
				}
				System.out.println(stack);
			}
			for (int i = 0; i < M; i++) {
				System.out.print("push " + i + ": ");
				try {
					stack.push(i);
				} catch (RuntimeException re) {
					System.out.println(re);
				}
				System.out.println(stack);
			}
			/*
			 * for (int i = 0; i < M; i++) { System.out.print("enqueue " + i +
			 * ": "); list3.enqueue(i); System.out.println(list3); }
			 */
			for (int i = 0; i < M; i++) {
				try {
					System.out.print(stack.pop() + " pop: ");
				} catch (RuntimeException re) {
					System.out.println(re);
				}
				System.out.println(stack);
			}
			/*
			 * for (int i = 0; i < M; i++) { System.out.print(list3.dequeue() +
			 * " deque: "); System.out.println(list3); }
			 */

		}

		static void testQueue() {
			Queue Queue = new Queue(N);

			for (int i = 0; i < N; i++) {
				Queue.enqueue(i*1.0);
				System.out.println(Queue);
			}

			final int M = 12;
			for (int i = 0; i < M; i++) {
				try {
					System.out.print(Queue.dequeue() + " dequeue: ");
				} catch (RuntimeException re) {
					System.out.println(re);
				}
				System.out.println(Queue);
			}
			for (int i = 0; i < M; i++) {
				System.out.print("enqueue " + i + ": ");
				try {
					Queue.enqueue(i);
				} catch (RuntimeException re) {
					System.out.println(re);
				}
				System.out.println(Queue);
			}
			/*
			 * for (int i = 0; i < M; i++) { System.out.print("enqueue " + i +
			 * ": "); list3.enqueue(i); System.out.println(list3); }
			 */
			for (int i = 0; i < M; i++) {
				try {
					System.out.print(Queue.dequeue() + " dequeue: ");
				} catch (RuntimeException re) {
					System.out.println(re);
				}
				System.out.println(Queue);
			}
			/*
			 * for (int i = 0; i < M; i++) { System.out.print(list3.dequeue() +
			 * " deque: "); System.out.println(list3); }
			 */
			int items = 100;
			for (int i = 0; i < items; ) {
				if (rd.nextInt(10) %2 == 0) {
					try {
						System.out.print("dequeue ");
						System.out.print(Queue.dequeue());
						System.out.print(": ");
					} catch (RuntimeException re) {
						System.out.println(re);
					}	
				} else {
					System.out.print(i + " enqueue: ");
					try {
						Queue.enqueue(i);
					} catch (RuntimeException re) {
						System.out.println(re);
					}
					i++;
				}
				System.out.println(Queue);
			}
		}



		public static void testHeap(Heap<Integer, Double> heap) {
			int items = 100;

			for(int i  = 0; i < items; i++) {
				Integer k = new Integer(rd.nextInt(100));
				Double v = rd.nextInt(1000)/10.0;
				System.out.println("Insert (" + k + "," + v +"):");
				heap.insert(k, v);
			}
			System.out.println(heap);			

			for(int i  = 0; i < items; i++) {
				System.out.println("Remove: " + heap.remove());
			}
		}

		public static void testPQ(PriorityQueue<? extends PQKey<Integer>, Double> heap) {	
			//		public static <A extends Comparable<A>, B> void testPQ(PriorityQueue<? extends PQKey<A>, B> heap) {
			int items = 100;
			int priorities = 5;

			for(int i  = 0; i < items; i++) {
				//				PQKey<A> p = new PQKey<A>(rd.nextInt(priorities));
				PQKey<Integer> p = new PQKey<Integer>(rd.nextInt(priorities));
				Double v = rd.nextInt(1000)/10.0;
				System.out.println("Insert (" + p + "," + v +"):");
				heap.insert(p, v);
			}
			System.out.println(heap);			

			for(int i  = 0; i < items; i++) {
				System.out.println("Remove: " + heap.remove());
			}
		}


	}

	


	



}
