package com.soffid.iam.web.component.inputField;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.VaultFolder;

import es.caib.seycon.ng.exception.InternalErrorException;


public class VaultFolderAsyncList extends AsyncList<VaultFolder> {
	AsyncList<VaultFolder> target = new AsyncList<>();
	List<AsyncList<VaultFolder>> src;
	Vector<VaultFolder> last;
	String text[];
	
	public VaultFolderAsyncList(List<AsyncList<VaultFolder>> src, String[] text) {
		super();
		this.src = src;
		this.text = text;
		this.last = new Vector<>(src.size());
		for (int i = 0; i < src.size(); i++)
			last.add(null);
	}
	
	public void forEach(Consumer<? super VaultFolder> action) {
		target.forEach(action);
	}
	public void done() {
		target.done();
	}
	public void cancel() {
		target.cancel();
	}
	public int size() {
		return target.size();
	}
	public boolean isEmpty() {
		return target.isEmpty();
	}
	public int hashCode() {
		return target.hashCode();
	}
	public boolean contains(Object o) {
		return target.contains(o);
	}
	public Iterator<VaultFolder> iterator() {
		updateFromSource();
		return target.iterator();
	}

	public Object[] toArray() {
		return target.toArray();
	}
	public <T> T[] toArray(T[] a) {
		return target.toArray(a);
	}
	public boolean add(VaultFolder e) {
		return target.add(e);
	}
	public boolean remove(Object o) {
		return target.remove(o);
	}
	public boolean containsAll(Collection<?> c) {
		return target.containsAll(c);
	}
	public boolean addAll(Collection<? extends VaultFolder> c) {
		return target.addAll(c);
	}
	public boolean removeAll(Collection<?> c) {
		return target.removeAll(c);
	}
	public boolean retainAll(Collection<?> c) {
		return target.retainAll(c);
	}
	public void clear() {
		target.clear();
	}
	public boolean equals(Object obj) {
		return target.equals(obj);
	}
	public boolean isDone() {
		updateFromSource();
		return target.isDone();
	}
	public boolean isCancelled() {
		updateFromSource();
		return target.isCancelled();
	}
	public boolean addAll(int index, Collection<? extends VaultFolder> c) {
		return target.addAll(index, c);
	}
	public VaultFolder get(int index) {
		updateFromSource();
		return target.get(index);
	}
	public VaultFolder set(int index, VaultFolder element) {
		return target.set(index, element);
	}
	public void add(int index, VaultFolder element) {
		target.add(index, element);
	}
	public VaultFolder remove(int index) {
		return target.remove(index);
	}
	public int indexOf(Object o) {
		return target.indexOf(o);
	}
	public int lastIndexOf(Object o) {
		return target.lastIndexOf(o);
	}
	public boolean cancel(boolean mayInterruptIfRunning) {
		return target.cancel(mayInterruptIfRunning);
	}
	public List<VaultFolder> get() throws InterruptedException, ExecutionException {
		return target.get();
	}
	public Collection<VaultFolder> get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		return target.get(timeout, unit);
	}
	public void cancel(Throwable th) {
		target.cancel(th);
	}
	public Throwable getExceptionToThrow() {
		return target.getExceptionToThrow();
	}
	public void clearExceptionToThrow() {
		target.clearExceptionToThrow();
	}
	public void setExceptionToThrow(Throwable exceptionToThrow) {
		target.setExceptionToThrow(exceptionToThrow);
	}
	public void setTimeout(long millis) {
		target.setTimeout(millis);
	}
	public String toString() {
		return target.toString();
	}
	public boolean removeIf(Predicate<? super VaultFolder> filter) {
		return target.removeIf(filter);
	}
	public Spliterator<VaultFolder> spliterator() {
		return target.spliterator();
	}
	public Stream<VaultFolder> stream() {
		return target.stream();
	}
	public Stream<VaultFolder> parallelStream() {
		return target.parallelStream();
	}
	

	private void updateFromSource() {
		boolean allDone = true;
		for (int i = 0; i < src.size(); i++) {
			AsyncList<VaultFolder> s = src.get(i);
			if (!s.isDone()) allDone = false;
			if (s.isCancelled()) {
				cancelAllSource();
				if (s.getExceptionToThrow() == null) 
					target.cancel();
				else
					target.cancel(s.getExceptionToThrow());
				return;
			} else {
				try {
					fill (i);
				} catch (InternalErrorException | NamingException | CreateException e) {
					target.cancel(e);
					cancelAllSource();
				}
			}
		}
		if (allDone)
			target.done();
	}

	public void cancelAllSource() {
		for (AsyncList<VaultFolder> s2: src) {
			if (!s2.isCancelled())
				s2.cancel(true);
		}
	}

	private void fill(int i) throws InternalErrorException, NamingException, CreateException {
		VaultFolder lastVault = last.get(i);
		boolean found = lastVault == null;
		for (Iterator<VaultFolder> it = src.get(i).iterator(); it.hasNext(); ) {
			VaultFolder vault = it.next();
			if (! found ) {
				if (lastVault == vault) found = true;
			} else {
				if (matches (vault))
					target.add(vault);
			}
		}
	}

	Map<Long, String> cache = new HashMap<Long, String>();
	private boolean matches(VaultFolder vault) throws InternalErrorException, NamingException, CreateException {
		if (cache.containsKey(vault.getId()))
			return false; // Already included
		String path = getPath(vault);
		cache.put(vault.getId(), path);
		for (String t: text)
			if (!path.toLowerCase().contains(t.toLowerCase()))
				return false;
		return true;
	}

	private String getPath(VaultFolder vault) throws InternalErrorException, NamingException, CreateException {
		if (vault.getParentId() == null)
			return vault.getName();
		else if (cache.containsKey(vault.getParentId()))
			return cache.get(vault.getParentId())+"/"+vault.getName();
		else {
			VaultFolder parent = EJBLocator.getVaultService().findFolder(vault.getParentId().longValue());
			String p = getPath(parent);
			cache.put(vault.getParentId(), p);
			return getPath(parent)+"/"+vault.getName();
		}
	}
}
