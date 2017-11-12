import java.util.Random;
import java.util.Scanner;

public class TestAll {
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
		//testPQ(heap1); //this should result in compilation error!!!
		//testHeap(heap3);  // this should result in compilation error!!!
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
//	public static <A extends Comparable<A>, B> void testPQ(PriorityQueue<? extends PQKey<A>, B> heap) {
		int items = 100;
		int priorities = 5;

		for(int i  = 0; i < items; i++) {
//			PQKey<A> p = new PQKey<A>(rd.nextInt(priorities));
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