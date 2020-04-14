package com.soffid.iam.web.main;

import java.util.Iterator;

import com.soffid.iam.api.AsyncList;

public class JoinAsyncList extends AsyncList<Object> {
	private AsyncList[] lists;
	private int[] sizes;
	
	public JoinAsyncList(AsyncList[] lists) {
		this.lists = lists;
		sizes = new int[lists.length];
	}

	@Override
	public Iterator<Object> iterator() {
		updateList();
		return super.iterator();
	}

	private void updateList() {
		for ( int i = 0; i < lists.length; i++)
		{
			if (lists[i].size() > sizes[i])
			{
				Iterator it = lists[i].iterator();
				for (int j = 0; it.hasNext() && j < sizes[i]; j++)
					it.next();
				while (it.hasNext())
					add(it.next());
			}
		}
	}

	@Override
	public boolean isDone() {
		if (super.isDone()) return true;
		for (AsyncList list: lists)
			if ( list.isDone()) return false;
		done();
		return true;
	}

	@Override
	public synchronized void cancel() {
		for (AsyncList list: lists)
			list.cancel();
		super.cancel();
	}

	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		for (AsyncList list: lists)
			list.cancel(mayInterruptIfRunning);
		return super.cancel(mayInterruptIfRunning);
	}

	@Override
	public synchronized void cancel(Throwable th) {
		for (AsyncList list: lists)
			list.cancel(th);
		super.cancel(th);
	}
}
