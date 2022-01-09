package com.soffid.iam.web.main;

import java.util.Iterator;

import org.zkoss.zul.Div;

import com.soffid.iam.api.AsyncList;

public abstract class SearchHandler<E> {
	public AsyncList<E> list ;
	int loaded;
	int max = 4;
	Div parentDiv;
	
	public void startSearch(String term) throws Exception {
		parentDiv.getChildren().clear();
		loaded = 0;
	}
	
	public void update() {
		AsyncList<E> list = this.list;
		if (list != null)
		{
			if ( loaded < list.size() && loaded < max ) {
				Iterator<E> iterator = list.iterator();
				for (int i = 0; i < loaded; i++)
					iterator.next();
				
				while (iterator.hasNext() && loaded < max)
				{
					E next = iterator.next();
					addElement (next);
					loaded ++;
				}
				if ( iterator.hasNext())
				{
					addMore ();
					list.cancel();
				}
					
			}
		}
	}
	
	protected abstract void addMore();
	protected abstract void addElement(E next);

	public void cancelSarch() {
		if (list != null)
			list.cancel();
	}
	
	public boolean isFinished() {
		return list == null ||
				list.isCancelled() ||
				list.isDone() && loaded == list.size();
	}

	
	public Div getParentDiv() {
		return parentDiv;
	}

	
	public void setParentDiv(Div parentDiv) {
		this.parentDiv = parentDiv;
	}
}
