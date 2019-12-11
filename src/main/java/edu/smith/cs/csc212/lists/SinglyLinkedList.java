package edu.smith.cs.csc212.lists;

import me.jjfoley.adt.ListADT;
import me.jjfoley.adt.errors.BadIndexError;
import me.jjfoley.adt.errors.RanOutOfSpaceError;
import me.jjfoley.adt.errors.TODOErr;
import me.jjfoley.adt.errors.BadIndexError;

/**
 * A Singly-Linked List is a list that has only knowledge of its very first
 * element. Elements after that are chained, ending with a null node.
 * 
 * @author jfoley
 *
 * @param <T> - the type of the item stored in this list.
 */
public class SinglyLinkedList<T> extends ListADT<T> {
	/**
	 * The start of this list. Node is defined at the bottom of this file.
	 */
	Node<T> start;

	@Override
	public T removeFront() {
		checkNotEmpty();
		T item = start.value;
		start = start.next;
		return item;
	}

	@Override
	public T removeBack() {
		checkNotEmpty();
		if (size() == 1) {
			T item = start.value;
			start = null;
			return item;
		} else { 
			T item = start.value;
			for(Node<T> n = this.start; n.next!= null; n = n.next ) {
				// if n.next is null we've found the end need to link them
				if (n.next.next == null) {
					item = n.next.value;
					n.next = null;
					break;
				}			
			} 	
			return item;
		}
	}

	@Override
	public T removeIndex(int index) {
		// check there's something there
		checkNotEmpty();
		if (index < 0 || index > size() ) {
			throw new BadIndexError(index);
		}
		if ( index == 0) {
			return removeFront();
		}
		else if (index == size() - 1) {
			return removeBack();
		}
		else {
			Node<T> n = this.start;
			T item = start.value;
			for (int i = 0; i < size(); i++) {
				// set Index (int index, T value)
				 if (i == index-1) {
					 item = n.next.value;
					 n.next = n.next.next;
					 break;
				 }
				 n = n.next;
			}
			return item;
		}
	}

	@Override
	public void addFront(T item) {
		this.start = new Node<T>(item, start);
		
	}

	@Override
	public void addBack(T item) {
		if (this.start == null) {
			this.start = new Node<T>( item, null);
		} else {
			for(Node<T> n = this.start; n!= null; n = n.next ) {
				// if n.next is null we've found the end need to link them
				if (n.next == null) {
					n.next = new Node<T>(item, null);
					break;
				}			
			} 
		}
	}

	@Override
	public void addIndex(int index, T item) {
		// throw new TODOErr();
		if ( index < 0 || index > size() ) {
			throw new BadIndexError(index);
		}
		if ( index == 0) {
			addFront(item);
		}
		else if (index == size()) {
			addBack(item);
		}
		else {
			Node<T> n = this.start;
			for (int i = 0; i < size(); i++) {
				// set Index (int index, T value)
				 if (i == index-1) {
					 n.next = new Node<T>(item, n.next);
					 break;
				 }
				 n = n.next;
			}
		}
	}

	@Override
	public T getFront() {
		checkNotEmpty();
		return this.getIndex(0);
	}

	@Override
	public T getBack() {
		checkNotEmpty();
		return this.getIndex(this.size()-1);
	}

	@Override
	public T getIndex(int index) {
		checkNotEmpty();
		// this one works
		// start at the starting index (zero)
		int at = 0;
		// loop through every node that isn't null
		for (Node<T> n = this.start; n != null; n = n.next) {
			// if at+1 is the index, return the value there
			if (at++ == index) {
				return n.value;
			}
		}
		throw new BadIndexError(index);
	}

	@Override
	public void setIndex(int index, T value) {
		if ( index < 0 || index >= size() ) {
			throw new BadIndexError(index);
		}
		if (index == size()) {
			addBack(value);
		}
		else {
			Node<T> n = this.start;
			for (int i = 0; i < size(); i++) {
				// set Index (int index, T value)
				 if (i == index) {
					 n.value = value;
					 break;
				 }
				 n = n.next;
			}
		}
	
		
	}

	@Override
	public int size() {
		int count = 0;
		for (Node<T> n = this.start; n != null; n = n.next) {
			count++;
		}
		return count;
	}

	@Override
	public boolean isEmpty() {
		return this.start == null;
	}

	/**
	 * The node on any linked list should not be exposed. Static means we don't need
	 * a "this" of SinglyLinkedList to make a node.
	 * 
	 * @param <T> the type of the values stored.
	 */
	private static class Node<T> {
		/**
		 * What node comes after me?
		 */
		public Node<T> next;
		/**
		 * What value is stored in this node?
		 */
		public T value;

		/**
		 * Create a node with no friends.
		 * 
		 * @param value - the value to put in it.
		 * @param next - the successor to this node.
		 */
		public Node(T value, Node<T> next) {
			this.value = value;
			this.next = next;
		}
	}

}
