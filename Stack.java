
public class Stack {

	int size;		//where next element is placed
	private Object[] storage;

	Stack(int capacity){
		if(capacity < 1)
			throw new IllegalArgumentException("Stack's capacity must be postive");
		storage = new Object[capacity];
		size = 0;
	}

	
	void push(Object value){
		if(size > storage.length){
			size = storage.length;
			throw new ArrayIndexOutOfBoundsException("Stack overflow");
		}
		storage[size] = value;
		size++;
	}

	
	Object pop(){
		if(size <= 0){
			size = 0;
			throw new RuntimeException("Stack is empty pop");
		}
		size--;
		return storage[size];
	}

	
	Object peek(){
		if(size <= 0){
			size = 0;
			throw new RuntimeException("Stack is empty");
		}
		return storage[size-1];
	}

	
	Boolean isEmpty(){
		if(size <= 0){
			size = 0;
			return true;
		}
		return false;
	}
	
	
	Boolean isFull(){
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
	}
	
}
