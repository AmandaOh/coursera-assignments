import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
	
	private Node first;
	private Node last;
	
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
		if (first == null) {
			return true;
		}
		return false;
	}
	
	public int size() {
		int count;
		if( isEmpty() ){
			count = 0;
		}else {
			count = 1;
		}
		return count;
	}
	
	public void addFirst(Item item) {
		if(item == null) { throw new java.lang.NullPointerException("Unable to add a null item");}
		Node newNode = new Node();
		newNode.item= item;
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
	}
	
	public void addLast(Item item)  {
		if(item == null) { throw new java.lang.NullPointerException("Unable to add a null item");}
		if(isEmpty()) {
			addFirst(item);
		} else {
			Node newNode = new Node();
			last.next = newNode;
			newNode.item = item;
			newNode.next = null;
			newNode.previous = last;
			last = newNode;
		}
	}
	
	public Item removeFirst() {
		if (isEmpty()) {throw new java.util.NoSuchElementException("list is empty!");}
		else {
			Item item = first.item;
			first = first.next;
			first.previous = null;
			return item;
		}
	}
	
	public Item removeLast() {
		if (isEmpty()) {throw new java.lang.UnsupportedOperationException("list is empty!");}
		else {
			Item item = last.item;
			last = last.previous;
			last.next = null;
			return item;
		}
	}
	
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item> {
		private Node current = first;
		public boolean hasNext(){
			return current != null;
		}
		public Item next(){
			if (hasNext()){
				Item item = current.item;
				current = current.next;
				return item;
			} else {
				throw new java.lang.NullPointerException("No more items");
			}
			
		}
		public void remove(){
			throw new java.lang.UnsupportedOperationException();
		}
		
	}
	
	public static void main(String[] args) {
		Deque<String> d = new Deque<String>();
		d.addFirst("Hey");
		d.addFirst("Hello");
		d.addLast("World");
		d.addLast("stranger");
		d.addFirst("things");
		
		Iterator<String> dq = d.iterator();
		
		while(dq.hasNext()){
			System.out.println(dq.next());
		}
	}
}
