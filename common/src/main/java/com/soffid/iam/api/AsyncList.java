package com.soffid.iam.api;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class AsyncList<E> implements Collection<E>, java.util.concurrent.Future<Collection<E>> {
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
		AsyncIterator<E> it = new AsyncIterator<E>();
		it.next = first;
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
			
		Entry<E> entry = new Entry<E>();
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

	public Collection<E> get() throws InterruptedException, ExecutionException {
		synchronized (this) {
			while (true)
			{
				if (!done && !cancelled)
					this.wait();
				if (done)
					return new LinkedList<E>(this);
				else if (exceptionToThrow != null)
					throw new ExecutionException(exceptionToThrow);
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

}


class Entry<E> {
	Entry<E> next;
	E element;
}

class AsyncIterator<E> implements Iterator<E>
{
	Entry<E> next;
	
	@Override
	public boolean hasNext() {
		return next != null;
	}

	@Override
	public E next() {
		if (next == null)
			return null;
		E e = next.element;
		next = next.next;
		return e;
	}

	@Override
	public void remove() {
		throw new IllegalStateException("Cannot remove elements");
	}
	
}