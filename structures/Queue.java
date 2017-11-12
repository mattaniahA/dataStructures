
public class Queue {

	int front;
	int length;
	int capacity;
	private Object[] storage;

	Queue(int capacit){
		if(capacit < 1)
			throw new IllegalArgumentException("Queue's capacity must be postive");
		capacity = capacit;
		storage = new Object[capacit];
		front = 0;
		length = 0;
	}

	void enqueue(Object value){
		if(length > storage.length){
			length = storage.length;
			throw new RuntimeException("Queue overflow");
		}
		int back = (front + length) % capacity;
		storage[back] = value;
		length++;	
	}

	Object dequeue(){
		if(length <= 0){
			length = 0;
			throw new RuntimeException("Queue is empty");
		}
		Object temp = storage[front];
		front++;
		front = front % storage.length;			//TODO: check
		length--;
		return temp;
	}

	Object peek(){
		if(length <= 0){
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
	}

}
