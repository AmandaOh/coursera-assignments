import java.util.Iterator; 

public class Deque<Item> implements Iterable<Item> {
    
	private Node first; 
	private Node last; 
	private int size;
	
	private class Node {
		private Item item; 
		private Node next; 
		private Node previous; 
	} 
		
	public Deque() {
		first = null; 
		last = null; 
	} 
	
	public boolean isEmpty() {
		if (size == 0) {
			return true; 
		} 
		return false; 
	} 
	
	public int size() {
		if (isEmpty()) { size = 0; }
		return size; 
	}  
	
	public void addFirst(Item item) {
		if (item == null) { throw new java.lang.NullPointerException("Unable to add a null item"); } 
		Node newNode = new Node(); 
		newNode.item = item; 
		newNode.previous = null; 
		if (isEmpty()) {
			newNode.next = null; 
			first = newNode; 
			last = newNode; 
		} else {
			first.previous = newNode; 
			newNode.next = first; 
			first = newNode; 
		} 
		size++;
	}  
	
	public void addLast(Item item) {
		if (item == null) { throw new java.lang.NullPointerException("Unable to add a null item"); } 
		if (isEmpty()) {
		    addFirst(item); 
		}  else {
			Node newNode = new Node(); 
			last.next = newNode; 
			newNode.item = item; 
			newNode.next = null; 
			newNode.previous = last; 
			last = newNode; 
			size++;
		} 
	} 
	
	public Item removeFirst() {
		if (isEmpty()) { throw new java.util.NoSuchElementException("list is empty!"); } 
		Item item = first.item; 
		first = first.next;
		if (first != null) {
			first.previous = null; 
		} else {
			last = null; 
		} 
		size--;
		return item; 

	} 
	
	public Item removeLast() {
		if (isEmpty()) { throw new java.util.NoSuchElementException("list is empty!"); } 
		Item item = last.item; 
		last = last.previous; 
		if (last != null) {
			last.next = null; 
		} else {
			first = null; 
		} 
		size--;
		return item; 
	} 
	
	public Iterator<Item> iterator() {
		return new ListIterator(); 
	} 
	
	private class ListIterator implements Iterator<Item> {
	    private Node current = first; 
		public boolean hasNext() {
			return current != null; 
		} 
		public Item next() {
			if (hasNext()) {
				Item item = current.item; 
				current = current.next; 
				return item; 
			} else {
				throw new java.util.NoSuchElementException("No more items"); 
			} 
			
		} 
		public void remove() {
			throw new java.lang.UnsupportedOperationException(); 
		} 
		
	} 
	
	public static void main(String[] args) {
		Deque<String> d = new Deque<String>(); 
		
		for (int i = 0; i < args.length;  i++) {
			d.addFirst(args[i]); 
		} 
		
		Iterator<String> dq = d.iterator(); 
		
		while (dq.hasNext()) {
			System.out.println(dq.next()); 
		} 
	} 
} 
