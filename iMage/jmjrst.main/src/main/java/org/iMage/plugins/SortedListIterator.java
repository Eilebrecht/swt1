package org.iMage.plugins;

import java.util.ListIterator;

/**
 * the iterator for the SortedList.
 * 
 * @author Joshua Eilebrecht
 *
 * @param <E>
 *            any type of object
 */
public class SortedListIterator<E extends JmjrstPlugin> implements ListIterator<E> {
	private int index;
	private SortedListCell<E> previous;
	private SortedListCell<E> next;
	private E lastReturned;

	/**
	 * constructor for the iterator
	 */
	SortedListIterator() {
		this.index = 0;
		this.previous = null;
		this.next = null;
	}

	@Override
	public boolean hasNext() {
		if (this.next != null) {
			return true;
		}
		return false;
	}

	@Override
	public E next() {
		if (this.next != null) {
			this.previous = this.next;
			this.next = this.next.getNext();
			this.lastReturned = this.previous.get();
			this.index++;
			return this.previous.get();
		}
		return null;
	}

	@Override
	public boolean hasPrevious() {
		if (this.previous != null) {
			return true;
		}
		return false;
	}

	@Override
	public E previous() {
		if (this.previous != null) {
			this.next = this.previous;
			this.previous = this.previous.getPrevious();
			this.lastReturned = this.next.get();
			this.index--;
			return this.next.get();
		}
		return null;
	}

	@Override
	public int nextIndex() {
		return this.index;
	}

	@Override
	public int previousIndex() {
		if (this.index > 0) {
			return this.index - 1;
		}
		return 0;
	}

	@Override
	public void remove() {
		SortedListCell<E> mem = this.next;
		int memIndex = 0;
		int callBack = this.index;
		while (mem != null && mem.getPrevious() != null) {
			mem = mem.getPrevious();
		}
		while (mem != null && !mem.get().equals(lastReturned)) {
			mem = mem.getNext();
			memIndex++;
		}
		if (memIndex <= callBack) {
			callBack--;
		}
		if (mem != null && mem.get().equals(lastReturned)) {
			while (this.index < memIndex) {
				this.internalNext();
			}
			while (this.index > memIndex) {
				this.internalPrevious();
			}
			if (this.previous != null) {
				this.previous.setNext(this.next.getNext());
			}
			this.next.getNext().setPrevious(this.previous);
			this.next = this.next.getNext();
		}

		while (this.index != callBack) {
			if (this.index < callBack) {
				this.internalNext();
			} else {
				this.internalPrevious();
			}
		}
	}

	@Override
	public void set(E e) {
		// not allowed in this list!
	}

	@Override
	public void add(E e) {
		SortedListCell<E> newCell = new SortedListCell<E>(e);
		if (this.next == null && this.previous == null) {
			this.next = newCell;
		} else {
			int callBack = this.index;
			while (this.hasPrevious()) {
				this.internalPrevious();
			}
			boolean isAdded = false;
			while (!isAdded) {
				if (this.next != null) {
					PluginPriority nextPri = this.next.get().priority;
					PluginPriority ePri = e.priority;
					if (nextPri.compareTo(PluginPriority.HIGH) == 0 || nextPri.compareTo(PluginPriority.MID) == 0
							&& (ePri.compareTo(PluginPriority.MID) == 0 || ePri.compareTo(PluginPriority.LOW) == 0)
							|| nextPri.compareTo(PluginPriority.LOW) == 0 && ePri.compareTo(PluginPriority.LOW) == 0) {
						newCell.setNext(this.next);
						if (this.previous != null) {
							newCell.setPrevious(this.previous);
							this.previous.setNext(newCell);
						}
						this.next.setPrevious(newCell);
						this.next = newCell;
						isAdded = true;
					}
				} else {
					if (this.previous != null) {
						newCell.setPrevious(this.previous);
						this.previous.setNext(newCell);
					}
					this.next = newCell;
					isAdded = true;
				}
				this.internalNext();
			}
			while (this.index < callBack) {
				this.internalNext();
			}
			while (this.index > callBack) {
				this.internalPrevious();
			}
		}
	}

	private E internalNext() {
		if (this.next != null) {
			this.previous = this.next;
			this.next = this.next.getNext();
			this.index++;
			return this.previous.get();
		}
		return null;
	}

	private E internalPrevious() {
		if (this.previous != null) {
			this.next = this.previous;
			this.previous = this.previous.getPrevious();
			this.index--;
			return this.next.get();
		}
		return null;
	}
}
