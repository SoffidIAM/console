package com.soffid.iam.api;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class AsyncList<E> implements List<E>, java.util.concurrent.Future<Collection<E>> {
	Entry<E> first = null;
	Entry<E> last = null;
	int size = 0;
	AsyncList<?> parent = null;
	private long timeout = 0;

	boolean done = false;
	boolean cancelled = false;
	private AsyncList<?> source;
	private Throwable exceptionToThrow;
	
	public AsyncList () {
		
	}
	
	public AsyncList (AsyncList<?> source) {
		synchronized (source)
		{
			for (Entry<?> e = source.first; e != null; e = e.next)
			{
				addedOnChild(e.element);
			}
			this.source = source;
			source.parent = this;
			this.cancelled = source.cancelled;
			this.done = source.done;
			this.exceptionToThrow = source.exceptionToThrow;
		}
	}
	
	public synchronized void done() {
		done = true;
		synchronized (this) {
			notifyAll();
		}
		if (parent != null)
			parent.done();
	}
	
	public synchronized void cancel() {
		if (! cancelled)
		{
			cancelled = true;
			synchronized (this) {
				notifyAll();
			}
			if (parent != null)
				parent.cancel();
			if (source != null)
				source.cancel();
		}
	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public boolean contains(Object o) {
		for (Entry<?> e = source.first; e != null; e = e.next)
		{
			if (e.element.equals(o))
				return true;
		}
		return false;
	}

	public Iterator<E> iterator() {
		AsyncIterator<E> it = new AsyncIterator<E>(this);
		return it;
	}

	public synchronized Object[] toArray() {
		Object[] ar = new Object [size];
		int i = 0;
		for (Entry<?> e = first; e != null; e = e.next)
		{
			ar [i++] = e.element;
		}
		
		return ar;
	}

	public synchronized <T> T[] toArray(T[] a) {
		if (a.length < size)
            a = (T[])java.lang.reflect.Array.newInstance(
                    a.getClass().getComponentType(), size);
		int i = 0;
		for (Entry<?> e = first; e != null; e = e.next)
		{
			a [i++] = (T) e.element;
		}
		
		return a;
	}

	public synchronized boolean add(E e) {
		if (timeout != 0 && java.lang.System.currentTimeMillis() > timeout)
			cancel ();

		checkMemoryUsage();

		Entry<E> entry = new Entry<E>();
		entry.previous = last;
		entry.next = null;
		entry.element = e;
		if (first == null)
			first = entry;
		else
			last.next = entry;
		last = entry;
		size ++;
		if (parent != null)
			parent.addedOnChild (e);
		return true;
	}

	Runtime runtime = Runtime.getRuntime();
	public void checkMemoryUsage() {
		long max = runtime.maxMemory();
		long used = runtime.totalMemory() - runtime.freeMemory();
		long free = max - used ;
		long pct = free * 100L / max;
		if (pct < 15) {
			cancel(new OutOfMemoryError());
			runtime.gc();
		}
	}

	protected boolean addedOnChild(Object e) {
		return add ((E)e);
	}

	public boolean remove(Object o) {
		throw new IllegalArgumentException("Cannot remove objects on async lists");
	}

	public boolean containsAll(Collection<?> c) {
		for (Object o: c)
			if ( !contains(o))
				return false;
		return true;
	}

	public boolean addAll(Collection<? extends E> c) {
		for (E e: c)
		{
			add(e);
			parent.addedOnChild(e);
		}
		return true;
	}

	public boolean removeAll(Collection<?> c) {
		throw new IllegalArgumentException("Cannot remove objects on async lists");
	}

	public boolean retainAll(Collection<?> c) {
		throw new IllegalArgumentException("Cannot remove objects on async lists");
	}

	public void clear() {
		throw new IllegalArgumentException("Cannot remove objects on async lists");
	}

	public boolean isDone() {
		return done;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public boolean addAll(int index, Collection<? extends E> c) {
		if (index == size)
			addAll(c);
		else	
			throw new IllegalArgumentException("Cannot remove objects on async lists");
		return true;
	}

	public E get(int index) {
		int i = 0;
		for (Entry<E> entry = first; entry != null; entry = entry.next)
		{
			if (i++ == index)
				return entry.element;
		}
		return null;
	}

	public E set(int index, E element) {
		throw new IllegalArgumentException("Cannot modify objects on async lists");
	}

	public void add(int index, E element) {
		if (index != size)
			throw new IllegalArgumentException("Cannot insert objects on async lists");
		add (element);
	}

	public E remove(int index) {
		throw new IllegalArgumentException("Cannot remove objects on async lists");
	}

	public int indexOf(Object o) {
		int i = 0;
		for (Entry<E> entry = first; entry != null; entry = entry.next)
		{
			if (entry.element == null && o == null ||
					entry.element != null && entry.element.equals(o))
				return i;
			i++;
		}
		return -1;
	}

	public int lastIndexOf(Object o) {
		throw new IllegalArgumentException("Not supparted");
	}

	public boolean cancel(boolean mayInterruptIfRunning) {
		cancel();
		return true;
	}

	public List<E> get() throws InterruptedException, ExecutionException {
		synchronized (this) {
			while (true)
			{
				if (!done && !cancelled)
					this.wait();
				if (exceptionToThrow != null)
					throw new ExecutionException(exceptionToThrow);
				else if (done)
					return new LinkedList<E>(this);
				else
					throw new CancellationException();
			}
		}
	}

	public Collection<E> get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		synchronized (this) {
			while (true) 
			{
				this.wait( unit.toMillis(timeout));
				if (done)
					return this;
				else if (exceptionToThrow != null)
					throw new ExecutionException(exceptionToThrow);
				else
					throw new CancellationException();
			}
		}
	}

	public synchronized void cancel(Throwable th) {
		if (! cancelled)
		{
			exceptionToThrow = th;
			cancelled = true;
			cancelled = true;
			synchronized (this) {
				notifyAll();
			}
			if (parent != null)
				parent.cancel(th);
			if (source != null)
				source.cancel(th);
		}
	}

	public Throwable getExceptionToThrow() {
		return exceptionToThrow;
	}

	public void clearExceptionToThrow() {
		exceptionToThrow = null;
	}

	public void setExceptionToThrow(Throwable exceptionToThrow) {
		this.exceptionToThrow = exceptionToThrow;
	}
	
	public void setTimeout(long millis)
	{
		timeout = java.lang.System.currentTimeMillis() + millis;
	}

	@Override
	public ListIterator<E> listIterator() {
		return new AsyncIterator(this);
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		AsyncIterator it = new AsyncIterator(this);
		while (it.hasNext() && index >= 0) {
			it.next();
			index --;
		}
		return it;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		throw new IllegalArgumentException("Sublists are not supported");
	}

}


class Entry<E> {
	Entry<E> previous;
	Entry<E> next;
	E element;
}

class AsyncIterator<E> implements ListIterator<E>
{
	int index;
	AsyncList<E> list;
	Entry<E> current = null;
	boolean deleted;
	protected AsyncIterator(AsyncList<E> l) {
		list = l;
		current = null;
		index = -1;
	}
	
	@Override
	public boolean hasNext() {
		return current == null ? list.first != null : current.next != null;
	}

	@Override
	public E next() {
		if (current == null) {
			index = 0;
			current = list.first;
		}
		else {
			current = current.next;
			if (!deleted) index ++;
		}
		deleted = false;
		return current.element;
	}

	@Override
	public void remove() {
		synchronized (list) {
			if (!deleted) {
				if (current.previous == null)
					list.first = current.next;
				else
					current.previous.next = current.next;
				
				if (current.next == null)
					list.last = current.previous;
				else
					current.next.previous = current.previous;
				list.size --;
				deleted = true;
			}
		}
	}

	@Override
	public boolean hasPrevious() {
		return current == null ? list.last != null : current.previous != null;
	}

	@Override
	public E previous() {
		if (current == null) {
			index = list.size;
			current = list.first;
		}
		else {
			current = current.next;
			if (!deleted) index ++;
		}
		deleted = false;
		return current.element;
	}

	@Override
	public int nextIndex() {
		return index + 1;
	}

	@Override
	public int previousIndex() {
		if (index < 0)
			return list.size - 1;
		else
			return index - 1;
	}

	@Override
	public void set(E e) {
		current.element = e;
	}

	@Override
	public void add(E e) {
		throw new IllegalArgumentException("Cannot insert objects on async lists");
	}
	
}