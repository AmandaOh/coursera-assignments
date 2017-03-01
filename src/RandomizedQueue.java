import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private Item[] queue;
	private int capacity;
	private int size;

	public static void main(String[] args) {
//		System.out.println(StdRandom.uniform(9));
		RandomizedQueue<String> q = new RandomizedQueue<String> (); 
		q.enqueue("Hello");
		q.enqueue("World");
		q.enqueue("Hey");
		q.enqueue("Test");
		q.enqueue("think");
		q.dequeue();
		Iterator<String> r = q.iterator();
		while(r.hasNext()){
			System.out.println(r.next());
		}
	}

	@SuppressWarnings("unchecked")
	public RandomizedQueue() {
		size = 0;
		capacity = 1;
		queue = (Item[]) new Object[capacity];
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	public void enqueue(Item item){
		if (item == null) {
			throw new java.lang.NullPointerException("Null item cannot be added");
		}
		if (size >= capacity) {
			increaseSize();
		}
		queue[size] = item;
		size++;
	}
	
	private void increaseSize() {
		capacity *= 2;
		@SuppressWarnings("unchecked")
		Item[] newQueue = (Item[]) new Object[capacity];
		for(int i= 0; i < size; i++) {
			newQueue[i] = queue[i];
		}
		queue = newQueue;
	}
	
	public Item dequeue() {
		if(isEmpty()) {throw new java.util.NoSuchElementException();}
		int index = StdRandom.uniform(size);
		Item item = queue[index];
		queue[index] = queue[--size];
		queue[size] = null;
		if(size < capacity * 0.25) { //half capacity when size of array is a quarter of capacity
			decreaseSize();
		}
		return item;
	}
	
	private void decreaseSize() {
		capacity /= 2;
		@SuppressWarnings("unchecked")
		Item[] newQueue = (Item[]) new Object[capacity];
		for(int i = 0; i < size; i++) {
			newQueue[i] = queue[i];
		}
		queue = newQueue;
	}
	
	public Item sample() {
		if(isEmpty()){throw new java.util.NoSuchElementException();}
		return queue[StdRandom.uniform(size)];
	}

	public Iterator<Item> iterator() {
		return new QueueIterator();
	}
	
	private class QueueIterator implements Iterator<Item>{
		int current = 0;
		int[] indicesArray = new int[size];
		
		public QueueIterator() {
			for(int i= 0; i < size; i++){
				indicesArray[i] = i;
			}
			StdRandom.shuffle(indicesArray);
		}
		
		public boolean hasNext() {
			return current < size; 
		}
		public Item next(){
			if(current >= size){throw new java.util.NoSuchElementException();}
			return queue[indicesArray[current++]];
		}
		public void remove(){
			throw new java.lang.UnsupportedOperationException();
		}
	}

}
