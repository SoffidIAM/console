/* UiVisualizer.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Tue Jun 14 10:57:48     2005, Created by tomyeh
}}IS_NOTE

Copyright (C) 2005 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.zk.ui.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.zkoss.lang.D;
import org.zkoss.lang.Objects;
import org.zkoss.zk.au.AuResponse;
import org.zkoss.zk.au.out.AuAppendChild;
import org.zkoss.zk.au.out.AuInsertAfter;
import org.zkoss.zk.au.out.AuInsertBefore;
import org.zkoss.zk.au.out.AuRemove;
import org.zkoss.zk.au.out.AuRemoveAttribute;
import org.zkoss.zk.au.out.AuReplace;
import org.zkoss.zk.au.out.AuSetAttribute;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.ext.render.ChildChangedAware;
import org.zkoss.zk.ui.ext.render.Cropper;
import org.zkoss.zk.ui.ext.render.MultiBranch;
import org.zkoss.zk.ui.sys.AbortingReason;
import org.zkoss.zk.ui.sys.ComponentCtrl;
import org.zkoss.zk.ui.sys.DesktopCtrl;
import org.zkoss.zk.ui.sys.PageCtrl;
import org.zkoss.zk.ui.sys.Visualizer;
import org.zkoss.zk.ui.util.DeferredValue;

/**
 * An implementation of {@link Visualizer} that works with
 * {@link UiEngineImpl}.
 *
 * @author tomyeh
 */
/*package*/ class UiVisualizer implements Visualizer {
//	private static final Log log = Log.lookup(UiVisualizer.class);

	/** The first exec info that causes a chain of executions (never null).
	 */
	private final UiVisualizer _1stec;

	/** The associated execution. */
	private final Execution _exec;
	/** A set of invalidated pages. */
	private Set _pgInvalid;
	/** A set of removed pages. */
	private Set _pgRemoved;
	/** A set of invalidated components  (Component). */
	private final Set _invalidated = new LinkedHashSet(32);
	/** A map of smart updates (Component comp, Map(String name, TimedValue(comp,name,value))). */
	private final Map _smartUpdated = new HashMap(64); //we use TimedValue for better sequence control
	/** A set of new attached components. */
	private final Set _attached = new LinkedHashSet(32);
	/** A set of moved components (parent changed or page changed). */
	private final Set _moved = new LinkedHashSet(32);
	/** A map of components whose UUID is changed (Component, UUID). */
	private Map _idChgd;
	/** A map of responses being added(Component/Page, Map(key, List/TimedValue(AuResponse))). */
	private Map _responses;
	/** A stack of components that are including new pages (and then
	 * become the owner of the new page, if any).
	 */
	private final List _owners;
	/** Time stamp for smart update and responses (see {@link TimedValue}). */
	private int _timed;
	/** if not null, it means the current executing is aborting
	 * and the content is reason to aborting. Its interpretation depends
	 * on {@link org.zkoss.zk.ui.sys.UiEngine}.
	 */
	private AbortingReason _aborting;
	/** Whether the first execution (_1stec) is for async-update. */
	private final boolean _1stau;
	/** Whether it is in recovering. */
	private final boolean _recovering;
	/** Whether it is ending, i.e., no further update is allowed. */
	private boolean _ending;
	/** Whether it is disabled, i.e., ignore any updates to the client.
	 */
	private boolean _disabled;

	/**
	 * Creates a root execution (without parent).
	 * In other words, it must be the first execution in the current request.
	 *
	 * @param asyncUpdate whether this execution is for async-update
	 * @param recovering whether this execution is in recovering,
	 * i.e., caused by {@link org.zkoss.zk.ui.sys.FailoverManager#recover}.
	 */
	public UiVisualizer(Execution exec, boolean asyncUpdate, boolean recovering) {
		_exec = exec;
		_1stec = this;
		_1stau = asyncUpdate;
		_recovering = recovering;
		_owners = new LinkedList();
	}
	/**
	 * Creates the following execution.
	 * The first execution must use {@link #UiVisualizer(Execution, boolean)}
	 */
	public UiVisualizer(UiVisualizer parent, Execution exec) {
		_exec = exec;
		_1stec = parent._1stec;
		_1stau = parent._1stau;
		_recovering = false;
		_owners = null;
	}

	//-- Visualizer --//
	public final Execution getExecution() {
		return _exec;
	}
	public final boolean isEverAsyncUpdate() {
		return _1stau;
	}
	public final boolean addToFirstAsyncUpdate(List responses) {
		if (!_1stau) return false;

//		if (D.ON && log.finerable()) log.finer("Add to 1st au: "+responses);
		for (Iterator it = responses.iterator(); it.hasNext();)
			_1stec.addResponse(null, (AuResponse)it.next());
		return true;
	}
	public boolean isRecovering() {
		return _recovering;
	}
	public void disable() {
		_disabled = true;
	}

	//-- update/redraw --//
	/** Returns if this component needs to be redrawn.
	 * <p>Note:
	 * <ol>
	 * <li>It always returns true if the current execution is not an
	 * asynchroous update.</li>
	 * <li>If its parent is invalidated, this component will be redrawn
	 * too, but this method returns false since {@link #addInvalidate(Compnent)}
	 * was not called against this component.</li>
	 * </ol>
	 * @since 3.0.5
	 */
	public boolean isInvalidated(Component comp) {
		return !_exec.isAsyncUpdate(comp.getPage())
			|| _invalidated.contains(comp)
			|| _attached.contains(comp)
			|| _moved.contains(comp);
			//No need to check page, recovering... since it won't be
			//part of _invalidated if so.
	}
	/** Invalidates the whole page.
	 */
	public void addInvalidate(Page page) {
		if (_recovering || _disabled || page == null || !_exec.isAsyncUpdate(page))
			return; //nothing to do

		if (_pgInvalid == null)
			_pgInvalid = new LinkedHashSet(7);
		_pgInvalid.add(page);
	}
	/** Adds an invalidated component. Once invalidated, all invocations
	 * to {@link #addSmartUpdate} are ignored in this execution.
	 */
	public void addInvalidate(Component comp) {
		final Page page = comp.getPage();
		if (_recovering || _disabled || page == null || !_exec.isAsyncUpdate(page))
			return; //nothing to do
		if (_ending) throw new IllegalStateException("ended");

		checkDesktop(comp);

		if (_invalidated.add(comp))
			_smartUpdated.remove(comp);
	}
	/** Ensure the use of component is correct. */
	private void checkDesktop(Component comp) {
		final Desktop dt = comp.getDesktop();
		if (dt != null && dt != _exec.getDesktop())
			throw new IllegalStateException("Access denied: component, "+comp+", belongs to another desktop: "+dt);
	}
	/** Smart updates a component's attribute.
	 * Meaningful only if {@link #addInvalidate(Component)} is not called in this
	 * execution
	 */
	public void addSmartUpdate(Component comp, String attr, String value) {
		final Map respmap = getAttrRespMap(comp, attr);
		if (respmap != null)
			respmap.put(attr, new TimedValue(_timed++, comp, attr, value));
	}
	/** Smart updates an attribute of a component with a deferred value.
	 * A deferred value is used to encapsulate a value that shall be retrieved
	 * only in the rendering phase.
	 *
	 * @since 3.0.1
	 * @see Component#smartUpdate(String, DeferredValue);
	 */
	public void addSmartUpdate(Component comp, String attr, DeferredValue value) {
		final Map respmap = getAttrRespMap(comp, attr);
		if (respmap != null)
			respmap.put(attr, new TimedValue(_timed++, comp, attr, value));
	}
	/** Smart updates a component's attribute with an array of values.
	 * Meaningful only if {@link #addInvalidate(Component)} is not called in this
	 * execution
	 * @since 3.0.5
	 */
	public void addSmartUpdate(Component comp, String attr, Object[] values) {
		final Map respmap = getAttrRespMap(comp, attr);
		if (respmap != null)
			respmap.put(attr, new TimedValue(_timed++, comp, attr, values));
	}
	/** Returns the response map for the specified attribute, or null if
	 * nothing to do.
	 */
	private Map getAttrRespMap(Component comp, String attr) {
		final Page page = comp.getPage();
		if (_recovering || _disabled || page == null || !_exec.isAsyncUpdate(page)
		|| _invalidated.contains(comp))
			return null; //nothing to do
		if (_ending) throw new IllegalStateException("ended");

		checkDesktop(comp);

		Map respmap = (Map)_smartUpdated.get(comp);
		if (respmap == null)
			_smartUpdated.put(comp, respmap = new HashMap());
		return respmap;
	}

	/** Called to update (redraw) a component, when a component is moved.
	 * If a component's page or parent is changed, this method need to be
	 * called only once for the top one.
	 *
	 * @param oldparent the parent before moved
	 * @param oldpg the page before moved
	 * @param newpg the page after moved
	 */
	public void addMoved(Component comp, Component oldparent, Page oldpg, Page newpg) {
		if (_recovering || _disabled || (newpg == null && oldpg == null)
		|| (newpg == null && !_exec.isAsyncUpdate(oldpg)) //detach from loading pg
		|| (oldpg == null && !_exec.isAsyncUpdate(newpg))) //attach to loading pg
			return; //to avoid redundant AuRemove
		if (_ending) throw new IllegalStateException("ended");

		if (oldpg == null && !_moved.contains(comp)) { //new attached
			_attached.add(comp);
				//note: we cannot examine _exec.isAsyncUpdate here because
				//comp.getPage might be ready when this method is called
		} else {
			_moved.add(comp);
			_attached.remove(comp);
		}
	}
	/** Called before changing the component's UUID.
	 *
	 * @param addOnlyMoved if true, it is added only if it was moved
	 * before (see {@link #addMoved}).
	 */
	public void addUuidChanged(Component comp, boolean addOnlyMoved) {
		if ((!addOnlyMoved || _moved.contains(comp))
		&& (_idChgd == null || !_idChgd.containsKey(comp))) {
			if (_idChgd == null) _idChgd = new LinkedHashMap(23);
			_idChgd.put(comp, comp.getUuid());
		}
	}

	/** Adds a response directly (which will be returned when
	 * {@link #getResponses} is called).
	 *
	 * <p>If the response is component-dependent, {@link AuResponse#getDepends}
	 * must return a component. And, if the component is removed, the response
	 * is removed, too.
	 *
	 * @param key could be anything. The second invocation of this method
	 * in the same execution with the same key will override the previous one.
	 */
	public void addResponse(String key, AuResponse response) {
		if (response == null)
			throw new IllegalArgumentException();

		if (_responses == null)
			_responses = new HashMap();

		final Object depends = response.getDepends(); //Page or Component
		Map respmap = (Map)_responses.get(depends);
		if (respmap == null)
			_responses.put(depends, respmap = new HashMap());

		final TimedValue tval = new TimedValue(_timed++, response);
		if (key != null) {
			respmap.put(key, tval);
		} else {
			List resps = (List)respmap.get(null);
			if (resps == null)
				respmap.put(null, resps = new LinkedList());
			resps.add(tval); //don't overwrite
		}
	}

	/** Process {@link Cropper} by removing cropped invalidates and so on.
	 */
	private Map doCrop() {
		final Map croppingInfos = new HashMap();
		crop(_attached, croppingInfos, false);
		crop(_smartUpdated.keySet(), croppingInfos, false);
		if (_responses != null)
			crop(_responses.keySet(), croppingInfos, true);
		crop(_invalidated, croppingInfos, false);
		return croppingInfos;
	}
	/** Crop attached and moved.
	 */
	private void crop(Set coll, Map croppingInfos, boolean bResponse) {
		for (Iterator it = coll.iterator(); it.hasNext();) {
			final Object o = it.next();
			if (!(o instanceof Component))
				continue;

			final Component comp = (Component)o;
			final Page page = comp.getPage();
			if (page == null || !_exec.isAsyncUpdate(page)) {
				if (!bResponse) it.remove(); //just in case
				continue;
			}

			for (Component p, c = comp; (p = c.getParent()) != null; c = p) {
				final Set avail = getAvailableAtClient(p, croppingInfos);
				if (avail != null && !avail.contains(c)) {
					it.remove();
					break;
				}
			}
		}
	}
	/** Returns the available children, or null if no cropping.
	 */
	private static Set getAvailableAtClient(Component comp, Map croppingInfos) {
		final Object xc = ((ComponentCtrl)comp).getExtraCtrl();
		if (xc instanceof Cropper) {
			//we don't need to check isCropper first since its component's job
			//to ensure the consistency

			Object crop = croppingInfos.get(comp);
			if (crop == Objects.UNKNOWN)
				return null;
			if (crop != null)
				return (Set)crop;

			crop = ((Cropper)xc).getAvailableAtClient();
			croppingInfos.put(comp, crop != null ? crop: Objects.UNKNOWN);
			return (Set)crop;
		}
		return null;
	}

	/** Process {@link ChildChangedAware}
	 */
	private void doChildChanged() {
		final Set ccawares = new LinkedHashSet(), checked = new HashSet(64);
		doChildChanged(_invalidated, ccawares, checked);
		doChildChanged(_attached, ccawares, checked);
		doChildChanged(_smartUpdated.keySet(), ccawares, checked);

		if (!ccawares.isEmpty())
			for (Iterator it = ccawares.iterator(); it.hasNext();)
				addSmartUpdate((Component)it.next(), "z.chchg", "true");
	}
	private void doChildChanged(Collection col, Set ccawares, Set checked) {
		for (Iterator it = col.iterator(); it.hasNext();) {
			Component comp = (Component)it.next();
			final Page page = comp.getPage();
			if (page == null || !_exec.isAsyncUpdate(page))
				continue;

			while ((comp = comp.getParent()) != null) {
				if (!checked.add(comp))
					break; //already checked

				final Object xc = ((ComponentCtrl)comp).getExtraCtrl();
				if ((xc instanceof ChildChangedAware)
				//&& !_invalidated.contains(comp) && !_attached.contains(comp)
					//No need to check _invalidated... since they are optimized
				&& ((ChildChangedAware)xc).isChildChangedAware())
					ccawares.add(comp);
			}
		}
	}

	/** Prepares {@link #_pgRemoved} to contain set of pages that will
	 * be removed.
	 */
	private void checkPageRemoved(Set removed) {
		//1. scan once
		final Desktop desktop = _exec.getDesktop();
		Set pages = null;
		for (Iterator it = desktop.getPages().iterator(); it.hasNext();) {
			final Page page = (Page)it.next();
			final Component owner = ((PageCtrl)page).getOwner();
			if (owner != null) { //included
				final Page ownerPage = owner.getPage();
				if (ownerPage == null //detached
				|| (_pgInvalid != null && _pgInvalid.contains(ownerPage))
				|| isAncestor(_invalidated, owner, true)
				|| isAncestor(_attached, owner, true)
				|| isAncestor(removed, owner, true)) {
					addPageRemoved(page);
				} else {
					if (pages == null) pages = new LinkedHashSet();
					pages.add(page);
				}
			}
		}
		if (_pgRemoved == null || pages == null) return;
			//done if no page is removed or no more included page

		//2. if a page is ever removed, it might cause chain effect
		//so we have to loop until nothing changed
		boolean pgRemovedFound;
		do {
			pgRemovedFound = false;
			for (Iterator it = pages.iterator(); it.hasNext();) {
				final Page page = (Page)it.next();
				final Component owner = ((PageCtrl)page).getOwner();
				if (_pgRemoved.contains(owner.getPage())) { 
					it.remove();
					addPageRemoved(page);
					pgRemovedFound = true;
				}
			}
		} while (pgRemovedFound); //loop due to chain effect
	}
	private void addPageRemoved(Page page) {
		if (_pgRemoved == null) _pgRemoved = new LinkedHashSet();
		_pgRemoved.add(page);
		if (_pgInvalid != null) _pgInvalid.remove(page);
//		if (D.ON && log.debugable()) log.debug("Page removed: "+page);
	}
	/** Clears components if it belongs to invalidated or removed page. */
	private void clearInInvalidPage(Collection coll) {
		for (Iterator it = coll.iterator(); it.hasNext();) {
			final Component comp = (Component)it.next();
			final Page page = comp.getPage();
			if (page != null
			&& ((_pgRemoved != null && _pgRemoved.contains(page))
			||  (_pgInvalid != null && _pgInvalid.contains(page))))
				it.remove();
		}
	}
	/** Returns whether any component in coll is an ancestor of comp.
	 * @param includingEquals whether to return true if a equals B
	 */
	private
	boolean isAncestor(Collection coll, Component comp, boolean includingEquals) {
		for (Iterator it = coll.iterator(); it.hasNext();) {
			final Component c = (Component)it.next();
			if ((includingEquals || c != comp) && Components.isAncestor(c, comp))
				return true;
		}
		return false;
	}

	/** Returns a list of {@link AuResponse} according to what components
	 * are invalidated and attached.
	 */
	public List getResponses() throws IOException {
/*		if (D.ON && log.finerable())
			log.finer("ei: "+this+"\nInvalidated: "+_invalidated+"\nSmart Upd: "+_smartUpdated
				+"\nAttached: "+_attached+"\nMoved:"+_moved+"\nResponses:"+_responses
				+"\npgInvalid: "+_pgInvalid	+"\nUuidChanged: "+_idChgd);
*/
		final List responses = new LinkedList();

		//1. process dead comonents, cropping and the removed page
		final Map croppingInfos;
		{
			//1a. handle _moved
			//The reason to remove first: some insertion might fail if the old
			//componetns are not removed yet
			//Also, we have to remove both parent and child because, at
			//the client, they might not be parent-child relationship
			Set removed = doMoved(responses);
				//after called, _moved is cleared (add to _attached if necessary)

			//1b. remove reduntant
			removeRedundant(_invalidated);
			removeRedundant(_attached);
			removeCrossRedundant();

			//1c. process Cropper
			croppingInfos = doCrop();

			//1d. prepare removed pages and optimize for invalidate or removed pages
			checkPageRemoved(removed); //maintain _pgRemoved for pages being removed
		}

		//2. Process removed and invalid pages
		//2a. clean up _invalidated and others belonging to invalid pages
		if (_pgInvalid != null && _pgInvalid.isEmpty()) _pgInvalid = null;
		if (_pgRemoved != null && _pgRemoved.isEmpty()) _pgRemoved = null;
		if (_pgInvalid != null || _pgRemoved != null) {
			clearInInvalidPage(_invalidated);
			clearInInvalidPage(_attached);
			clearInInvalidPage(_smartUpdated.keySet());
			if (_idChgd != null) clearInInvalidPage(_idChgd.keySet());
		}

		//2b. remove pages. Note: we don't need to generate rm, becausee they
		//are included pages.
		if (_pgRemoved != null) {
			final DesktopCtrl dtctl = (DesktopCtrl)_exec.getDesktop();
			for (final Iterator it = _pgRemoved.iterator(); it.hasNext();)
				dtctl.removePage((Page)it.next());
		}

		//2c. generate response for invalidated pages
		if (_pgInvalid != null) {
			for (final Iterator it = _pgInvalid.iterator(); it.hasNext();) {
				final Page page = (Page)it.next();
				responses.add(new AuReplace(page, redraw(page)));
			}
		}

		//3. Remove components who is moved and its UUID is changed
		if (_idChgd != null) {
			for (Iterator it = _idChgd.values().iterator(); it.hasNext();)
				responses.add(new AuRemove((String)it.next()));
			_idChgd = null; //just in case
		}

/*		if (log.finerable())
			log.finer("After removing redudant: invalidated: "+_invalidated
			+"\nAttached: "+_attached+"\nSmartUpd:"+_smartUpdated);
*/
		//4. process special interfaces
		doChildChanged(); //ChildChangedAware

		//5. generate replace for invalidated
		for (Iterator it = _invalidated.iterator(); it.hasNext();) {
			final Component comp = (Component)it.next();
			responses.add(new AuReplace(comp, redraw(comp)));
		}

		_ending = true; //no more addSmartUpdate...

		//6. add attached components (including setParent)
		//Due to cyclic references, we have to process all siblings
		//at the same time
		final List desktops = new LinkedList();
		final Component[] attached = (Component[])
			_attached.toArray(new Component[_attached.size()]);
		for (int j = 0; j < attached.length; ++j) {
			final Component comp = attached[j];
			//Note: attached comp might change from another page to
			//the one being created. In this case, no need to add
			if (comp != null) {
				final Page page = comp.getPage();
				if (page != null && _exec.isAsyncUpdate(page)) {
					final Component parent = comp.getParent();
					final Set newsibs = new LinkedHashSet(32);
					newsibs.add(comp);
					desktops.add(newsibs);

					for (int k = j + 1; k < attached.length; ++k) {
						final Component ck = attached[k];
						if (ck != null && ck.getParent() == parent) {
							newsibs.add(ck);
							attached[k] = null;
						}
					}
				}
			}
		}
		for (Iterator it = desktops.iterator(); it.hasNext();) {
			final Set newsibs = (Set)it.next();
			addResponsesForCreatedPerSiblings(responses, newsibs, croppingInfos);
		}

		//7. Adds smart updates and response at once based on their time stamp
		final List tvals = new LinkedList();
		for (Iterator it = _smartUpdated.values().iterator(); it.hasNext();) {
			final Map attrs = (Map)it.next();
			tvals.addAll(attrs.values());
		}
		if (_responses != null) {
			for (Iterator it = _responses.values().iterator(); it.hasNext();) {
				final Map resps = (Map)it.next();
				final List keyless = (List)resps.remove(null); //key == null
				if (keyless != null) tvals.addAll(keyless);
				tvals.addAll(resps.values()); //key != null
			}
		}
		if (!tvals.isEmpty()) {
			final TimedValue[] tvs = (TimedValue[])tvals.toArray(new TimedValue[tvals.size()]);
			Arrays.sort(tvs);
			for (int j = 0; j < tvs.length; ++j)
				responses.add(tvs[j].getResponse());
		}

		//any aborting reason
		//Note: we don't give up other responses (Bug 1647085)
		if (_aborting != null) {
			final AuResponse abtresp = _aborting.getResponse();
			if (abtresp != null)
				responses.add(abtresp); //add to the end
		}

		//free memory
		_invalidated.clear();
		_smartUpdated.clear();
		_attached.clear();
		_pgInvalid = _pgRemoved = null;
		_responses = null;

//		if (D.ON && log.debugable()) log.debug("Return responses: "+responses);
//		System.out.println("Return responses: "+responses);
		return responses;
	}

	/** process moved components.
	 *
	 * <p>After called, _moved becomes empty.
	 * If they are removed, correponding AuRemove are generated.
	 * If not, they are added to _attached.
	 *
	 * @return the dead components (i.e., not belong to any page)
	 */
	private Set doMoved(List responses) {
		//Remove components that have to removed from the client
		final Set removed = new LinkedHashSet();
		for (Iterator it = _moved.iterator(); it.hasNext();) {
			final Component comp = (Component)it.next();
			final Page page = comp.getPage();
			if (page == null) {
				removed.add(comp);

				if (_responses != null) _responses.remove(comp);
				_invalidated.remove(comp);
				_smartUpdated.remove(comp);

				responses.add(new AuRemove(comp));
			} else { //page != null
				if (_exec.isAsyncUpdate(page))
					responses.add(new AuRemove(comp));
				_attached.add(comp);
					//copy to _attached since we handle them later in the same way
			}
		}

		_moved.clear(); //no longer required
		return removed;
	}

	/** Adds responses for a set of siblings which is new attached (or
	 * parent is changed).
	 */
	private static
	void addResponsesForCreatedPerSiblings(List responses, Set newsibs,
	Map croppingInfos) throws IOException {
		final Component parent;
		final Page page;
		{
			final Component comp = (Component)newsibs.iterator().next();
			parent = comp.getParent();
			page = comp.getPage();
		}
		Collection sibs;
		if (parent != null) {
			sibs = getAvailableAtClient(parent, croppingInfos);
			if (sibs == null) //no cropping
				sibs = parent.getChildren();
			else if (sibs.size() > 1 && !(sibs instanceof LinkedHashSet)) {
//				log.warning("Use LinkedHashSet instead of "+sibs.getClass());
				final Set s = new LinkedHashSet(sibs.size() * 2);
				for (Iterator it = parent.getChildren().iterator(); it.hasNext();) {
					final Object o = it.next();
					if (sibs.remove(o)) {
						s.add(o);
						if (sibs.isEmpty())
							break;
					}
				}
				sibs = s;
			}
		} else {
			sibs = page.getRoots();
		}
//		if (D.ON && log.finerable()) log.finer("All sibs: "+sibs+" newsibs: "+newsibs);

		/* Algorithm:
	1. Locate a sibling, say <a>, that already exists.
	2. Then, use AuInsertBefore for all sibling before <a>,
		and AuInsertAfter for all after anchor.
	3. If anchor is not found, use AuAppendChild for the first
		and INSERT_AFTER for the rest
		*/
		final List before = new LinkedList();
		Component anchor = null;
		final ComponentCtrl parentCtrl = (ComponentCtrl)parent;
		final Object parentxc =
			parentCtrl != null ? parentCtrl.getExtraCtrl(): null;
		for (Iterator it = sibs.iterator(); it.hasNext();) {
			final Component comp = (Component)it.next();

			if ((parentxc instanceof MultiBranch)
			&& ((MultiBranch)parentxc).inDifferentBranch(comp))
				continue;

			if (anchor != null) {
				if (newsibs.remove(comp)) {
					responses.add(new AuInsertAfter(anchor, drawNew(comp)));
					if (newsibs.isEmpty())
						return; //done (all newsibs are processed)
					anchor = comp;
				} else {
					anchor = comp;
				}
			} else if (newsibs.remove(comp)) {
				before.add(comp);	
			} else {
				//Generate before in the reverse order and INSERT_BEFORE
				anchor = comp;
				for (ListIterator i2 = before.listIterator(before.size());
				i2.hasPrevious();) {
					final Component c = (Component)i2.previous();
					responses.add(new AuInsertBefore(anchor, drawNew(c)));
					anchor = c;
				}
				if (newsibs.isEmpty())
					return; //done (all newsibs are processed)
				anchor = comp;
			}
		}
		assert D.OFF || (anchor == null && newsibs.isEmpty()): "anchor="+anchor+" newsibs="+newsibs+" sibs="+sibs;


		//all siblings are changed (and none of them is processed)
		final Iterator it = before.iterator();
		if (it.hasNext()) {
			anchor = (Component)it.next();
			responses.add(
				parent != null ?
					new AuAppendChild(parent, drawNew(anchor)):
					new AuAppendChild(page, drawNew(anchor)));

			while (it.hasNext()) {
				final Component comp = (Component)it.next();
				responses.add(new AuInsertAfter(anchor, drawNew(comp)));
				anchor = comp;
			}
		}
	}
	
	/** Removes redundant components (i.e., an descendant of another).
	 */
	private static void removeRedundant(Set comps) {
		Component[] array = (Component[]) comps.toArray(new Component[comps.size()]);

		for (int j = 0; j < array.length; j++)
		{
			final Component cj = array[j];
			for (int k = 0; k < array.length; k++) 
			{
				final Component ck = (Component)array[k];
				if (ck != null && ck != cj && ck.getFirstChild() != null && Components.isAncestor(ck, cj)) {
					array[j] = null;
					comps.remove(cj);
					break;
				}
			}
		}
	}
	
	/** Removes redundant components cross _invalidate, _smartUpdate
	 * and _attached.
	 */
	private void removeCrossRedundant0() {
		invLoop:
		for (Iterator j = _invalidated.iterator(); j.hasNext();) {
			final Component cj = (Component)j.next();

			for (Iterator k = _attached.iterator(); k.hasNext();) {
				final Component ck = (Component)k.next();
				if (Components.isAncestor(ck, cj)) { //includes ck == cj
					j.remove();
					continue invLoop;
				} else if (Components.isAncestor(cj, ck)) {
					k.remove();
				}
			}
		}
		suLoop:
		for (Iterator j = _smartUpdated.keySet().iterator(); j.hasNext();) {
			final Component cj = (Component)j.next();

			for (Iterator k = _invalidated.iterator(); k.hasNext();) {
				final Component ck = (Component)k.next();


				if (Components.isAncestor(ck, cj)) {
					j.remove();
					continue suLoop;
				}
			}
			for (Iterator k = _attached.iterator(); k.hasNext();) {
				final Component ck = (Component)k.next();
				if (Components.isAncestor(ck, cj)) {
					j.remove();
					continue suLoop;
				}
			}
		}
	}

	/** Removes redundant components cross _invalidate, _smartUpdate
	 * and _attached.
	 */
	private void removeCrossRedundant() {
		for (Iterator j = _invalidated.iterator(); j.hasNext();) {
			final Component cj = (Component)j.next();

			for (Iterator k = _attached.iterator(); k.hasNext();) {
				final Component ck = (Component)k.next();
				if (Components.isAncestor(ck, cj)) { //includes ck == cj
					j.remove();
					break;
				} else if (Components.isAncestor(cj, ck)) {
					k.remove();
				}
			}
		}
		for (Iterator j = _smartUpdated.keySet().iterator(); j.hasNext();) {
			Component cj = (Component)j.next();
			for (Iterator k = _invalidated.iterator(); k.hasNext();) {
				final Component ck = (Component)k.next();


				if (Components.isAncestor(ck, cj)) {
					j.remove();
					cj = null;
					break;
				}
			}
			if (cj != null)
			{
				for (Iterator k = _attached.iterator(); k.hasNext();) {
					final Component ck = (Component)k.next();
					if (Components.isAncestor(ck, cj)) {
						j.remove();
						break;
					}
				}
			}
		}
	}


	/** Draws a new attached component into a string.
	 */
	private static String drawNew(Component comp)
	throws IOException {
		final StringWriter out = new StringWriter(1024*8);
		comp.redraw(out);

		final StringBuffer buf = out.getBuffer();
		final Component parent = comp.getParent();
		if (parent != null)
			parent.onDrawNewChild(comp, buf);
		return buf.toString();
	}
	/** Redraw the specified component into a string.
	 */
	private static String redraw(Component comp) throws IOException {
		final StringWriter out = new StringWriter(1024*8);
		comp.redraw(out);
		return out.toString();
	}
	/** Redraws the whole page. */
	private static String redraw(Page page) throws IOException {
		final StringWriter out = new StringWriter(1024*8);
		((PageCtrl)page).redraw(null, out);
		return out.toString();
	}

	/** Called before a component redraws itself if the component might
	 * include another page.
	 */
	public void pushOwner(Component comp) {
		_1stec._owners.add(0, comp);
	}
	/** Called after a component redraws itself if it ever calls
	 * {@link #pushOwner}.
	 */
	public void popOwner() {
		_1stec._owners.remove(0);
	}
	/** Returns the owner component for this execution, or null if
	 * this execution is not owned by any component.
	 * The owner is the top of the stack pushed by {@link #pushOwner}.
	 */
	public Component getOwner() {
		return _1stec._owners.isEmpty() ? null: (Component)_1stec._owners.get(0);
	}

	/** Used to hold smart update and response with a time stamp.
	 */
	private static class TimedValue implements Comparable {
		private final int _timed;
		private final AuResponse _response;
		private TimedValue(int timed, AuResponse response) {
			_timed = timed;
			_response = response;
		}
		private TimedValue(int timed, Component comp, String name, String value) {
			_timed = timed;
			if (value != null)
				_response = new AuSetAttribute(comp, name, value);
			else
				_response = new AuRemoveAttribute(comp, name);
		}
		private TimedValue(int timed, Component comp, String name, DeferredValue value) {
			_timed = timed;
			if (value != null)
				_response = new AuSetAttribute(comp, name, value);
			else
				_response = new AuRemoveAttribute(comp, name);
		}
		private TimedValue(int timed, Component comp, String name, Object[] values) {
			_timed = timed;
			if (values == null || values.length == 0)
				_response = new AuRemoveAttribute(comp, name);
			else
				_response = new AuSetAttribute(comp, name, values);
		}
		public String toString() {
			return '(' + _timed + ":" + _response + ')';
		}
		public int compareTo(Object o) {
			final int t = ((TimedValue)o)._timed;
			return _timed > t  ? 1: _timed == t ? 0: -1;
		}
		/** Returns the response representing this object. */
		private AuResponse getResponse() {
			return _response;
		}
	};

	/** Sets the reason to abort the current execution.
	 * if not null, it means the current execution is aborting
	 * and the specified argument is the reason to aborting.
	 * Its interpretation depends on {@link org.zkoss.zk.ui.sys.UiEngine}.
	 *
	 * <p>Note: if setAbortingReason is ever set with non-null, you
	 * CANNOT set it back to null.
	 *
	 * <p>The aborting flag means no more processing, i.e., dropping pending
	 * requests, events, and rendering.
	 *
	 * <p>After call this method, you shall not keep processing the page
	 * because the rendering is dropped and the client is out-of-sync
	 * with the server.
	 *
	 * <p>This method doesn't really abort pending events and requests.
	 * It just set a flag, {@link #getAbortingReason}, and it is
	 * {@link org.zkoss.zk.ui.sys.UiEngine}'s job to detect this flag
	 * and handling it properly.
	 */
	public void setAbortingReason(AbortingReason reason) {
		if (_aborting != null && reason == null)
			throw new IllegalStateException("Aborting reason is set and you cannot clear it");
				//Reason: some event or request might be skipped
				//so clearing it might cause unexpected results
		_aborting = reason;
	}
	/** Returns the reason to aborting, or null if no aborting at all.
	 * 
	 * @see #setAbortingReason
	 */
	public AbortingReason getAbortingReason() {
		return _aborting;
	}
	/** Returns whether it is aborting.
	 * 
	 * <p>The execution is aborting if {@link #getAbortingReason} returns
	 * not null and the returned reason's {@link AbortingReason#isAborting}
	 * is true.
	 *
	 * <p>Note: {@link Execution#isVoid} means the execution is voided
	 * and no output shall be generated. The request is taken charged
	 * by other servlet.
	 * On the other hand, {@link #isAborting} means the execution
	 * is aborting and the output shall still be generated (and sent).
	 * The request is still taken charged by this execution.
	 */
	public boolean isAborting() {
		return _aborting != null && _aborting.isAborting();
	}
}
