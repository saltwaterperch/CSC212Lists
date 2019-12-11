package edu.smith.cs.csc212.lists;

import me.jjfoley.adt.ArrayWrapper;
import me.jjfoley.adt.ListADT;
import me.jjfoley.adt.errors.RanOutOfSpaceError;
import me.jjfoley.adt.errors.TODOErr;

/**
 * FixedSizeList is a List with a maximum size.
 * @author jfoley
 *
 * @param <T>
 */
public class FixedSizeList<T> extends ListADT<T> {
	/**
	 * This is the array of fixed size.
	 */
	private ArrayWrapper<T> array;
	/**
	 * This keeps track of what we have used and what is left.
	 */
	private int fill;

	/**
	 * Construct a new FixedSizeList with a given maximum size.
	 * @param maximumSize - the size of the array to use.
	 */
	public FixedSizeList(int maximumSize) {
		this.array = new ArrayWrapper<>(maximumSize);
		this.fill = 0;
	}

	@Override
	public boolean isEmpty() {
		return this.fill == 0;
	}

	@Override
	public int size() {
		return this.fill;
	}

	@Override
	public void setIndex(int index, T value) {
		checkNotEmpty();
		this.checkExclusiveIndex(index);
		this.array.setIndex(index, value);
	}

	@Override
	public T getIndex(int index) {
		checkNotEmpty();
		this.checkExclusiveIndex(index);
		return this.array.getIndex(index);
	}

	@Override
	public T getFront() {
		return this.array.getIndex(0);
	}

	@Override
	public T getBack() {
		return this.array.getIndex(fill-1);
	}

	@Override
	public void addIndex(int index, T value) {
		// slide to the right
		// add one to fill
		// if (no more space)
		if (isFull() == true) {
			throw new RanOutOfSpaceError();
		}
		
	 for (int i = fill; i> index; i--) {
		 this.array.setIndex(i, array.getIndex(i-1));
	}
	 	fill++;
		this.array.setIndex( index, value);
		
	}
	
	
	@Override
	public void addFront(T value) {
		this.addIndex(0, value);
	}

	@Override
	public void addBack(T value) {
		if (fill < array.size()) {
			array.setIndex(fill++, value);
		} else {
			throw new RanOutOfSpaceError();
		}
	}

	@Override
	public T removeIndex(int index) {
		// slide to the left
		this.checkNotEmpty();
		// make sure the index is reasonable
		this.checkExclusiveIndex(index);
		T youAreDeleting = this.array.getIndex(index);
		// loop through index, 
		// looking for the one we want to delete
		// subtract 1 from the index of spots to the right
		for (int i = index; i < fill-1; i++) {
			this.array.setIndex(i, array.getIndex(i+1));
			} 
		// Erase the duplicate, last-item
		this.array.setIndex(fill-1, null);
		fill --;
		return youAreDeleting;
		
		
	}

	@Override
	public T removeBack() {
		this.checkNotEmpty();
		return removeIndex(fill-1);
	}

	@Override
	public T removeFront() {
		this.checkNotEmpty();
		return removeIndex(0);
	}

	/**
	 * Is this data structure full? Used in challenge: {@linkplain ChunkyArrayList}.
	 * 
	 * @return if true this FixedSizeList is full.
	 */
	public boolean isFull() {
		return this.fill == this.array.size();
	}

}
