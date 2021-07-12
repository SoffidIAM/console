/* AbstractComponent.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Mon May 30 21:49:42     2005, Created by tomyeh
}}IS_NOTE

Copyright (C) 2005 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.zk.ui;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.AbstractSequentialList;
import java.util.LinkedList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.io.Writer;
import java.io.StringWriter;
import java.io.IOException;

import org.zkoss.lang.D;
import org.zkoss.lang.Library;
import org.zkoss.lang.Classes;
import org.zkoss.lang.Strings;
import org.zkoss.lang.Objects;
import org.zkoss.util.CollectionsX;
import org.zkoss.util.logging.Log;
import org.zkoss.io.Serializables;
import org.zkoss.xml.HTMLs;

import org.zkoss.zk.mesg.MZk;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Deferrable;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.ext.Macro;
import org.zkoss.zk.ui.ext.RawId;
import org.zkoss.zk.ui.ext.NonFellow;
import org.zkoss.zk.ui.ext.render.ZidRequired;
import org.zkoss.zk.ui.render.ComponentRenderer;
import org.zkoss.zk.ui.util.ComponentSerializationListener;
import org.zkoss.zk.ui.util.ComponentCloneListener;
import org.zkoss.zk.ui.util.DeferredValue;
import org.zkoss.zk.ui.sys.ExecutionCtrl;
import org.zkoss.zk.ui.sys.ExecutionsCtrl;
import org.zkoss.zk.ui.sys.ComponentCtrl;
import org.zkoss.zk.ui.sys.ComponentsCtrl;
import org.zkoss.zk.ui.sys.PageCtrl;
import org.zkoss.zk.ui.sys.DesktopCtrl;
import org.zkoss.zk.ui.sys.SessionCtrl;
import org.zkoss.zk.ui.sys.WebAppCtrl;
import org.zkoss.zk.ui.sys.UiEngine;
import org.zkoss.zk.ui.sys.IdGenerator;
import org.zkoss.zk.ui.sys.Names;
import org.zkoss.zk.ui.metainfo.AnnotationMap;
import org.zkoss.zk.ui.metainfo.Annotation;
import org.zkoss.zk.ui.metainfo.EventHandlerMap;
import org.zkoss.zk.ui.metainfo.ComponentDefinition;
import org.zkoss.zk.ui.metainfo.PageDefinition;
import org.zkoss.zk.ui.metainfo.LanguageDefinition;
import org.zkoss.zk.ui.metainfo.ComponentInfo;
import org.zkoss.zk.ui.metainfo.ComponentDefinition;
import org.zkoss.zk.ui.metainfo.ComponentDefinitionMap;
import org.zkoss.zk.ui.metainfo.DefinitionNotFoundException;
import org.zkoss.zk.ui.metainfo.EventHandler;
import org.zkoss.zk.ui.metainfo.ZScript;
import org.zkoss.zk.ui.impl.ListenerIterator;
import org.zkoss.zk.fn.ZkFns;
import org.zkoss.zk.au.Command;
import org.zkoss.zk.au.AuResponse;
import org.zkoss.zk.au.out.AuClientInfo;
import org.zkoss.zk.scripting.Namespace;
import org.zkoss.zk.scripting.Interpreter;
import org.zkoss.zk.scripting.util.SimpleNamespace;

/**
 * A skeletal implementation of {@link Component}. Though it is OK
 * to implement Component from scratch, this class simplifies some of
 * the chores.
 *
 * @author tomyeh
 */
public class AbstractComponent
implements Component, ComponentCtrl, java.io.Serializable {
//	private static final Log log = Log.lookup(AbstractComponent.class);
    private static final long serialVersionUID = 20070920L;

	private transient Page _page;
	private String _id;
	private String _uuid;
	private transient ComponentDefinition _def;
	/** The mold (default: "default"). */
	private String _mold = "default";
	/** The info of the ID space, or null if IdSpace is NOT implemented. */
	private transient SpaceInfo _spaceInfo;
	private transient Map _attrs;
		//don't create it dynamically because _ip bind it at constructor
	/** A map of event listener: Map(evtnm, EventListener)). */
	private transient Map _listeners;
	/** The extra controls. */
	private transient Object _xtrl;

	/** The list used for {@link #getChildren} only. */
	private transient List _apiChildren;
	private transient AbstractComponent _parent;
	/** The next sibling. */
	private transient AbstractComponent _next;
	/** The previous sibling. */
	private transient AbstractComponent _prev;
	/** The first child. */
	private transient AbstractComponent _first;
	/** The last child. */
	private transient AbstractComponent _last;
	/** # of children. */
	private int _nChild;
	/** The modification count used to avoid co-modification of _next, _prev..
	 */
	private transient int _modCntChd;
	/** A set of components that are being removed.
	 * It is used to prevent dead-loop between {@link #removeChild}
	 * and {@link #setParent}.
	 */
	private transient Set _rming;
	/** A set of components that are being added.
	 * It is used to prevent dead-loop between {@link #insertBefore}
	 * and {@link #setParent}.
	 */
	private transient Set _adding;

	/** A map of annotations. Serializable since a component might have
	 * its own annotations.
	 */
	private AnnotationMap _annots;
	/** A map of event handler to handle events. */
	private EventHandlerMap _evthds;
	/** A map of forward conditions:
	 * Map(String orgEvt, [listener, List([target or targetPath,targetEvent])]).
	 */
	private transient Map _forwards;
	/** Whether _annots is shared with other components. */
	private transient boolean _annotsShared;
	/** Whether _evthds is shared with other components. */
	private transient boolean _evthdsShared;
	/** Whether this component is visible. */
	private boolean _visible = true;

	/** Constructs a component with auto-generated ID.
	 * @since 3.0.7 (becomes public)
	 */
	public AbstractComponent() {
		final Execution exec = Executions.getCurrent();

		final Object curInfo = ComponentsCtrl.getCurrentInfo();
		if (curInfo != null) {
			ComponentsCtrl.setCurrentInfo((ComponentInfo)null); //to avoid mis-use
			if (curInfo instanceof ComponentInfo) {
				final ComponentInfo compInfo = (ComponentInfo)curInfo;
				_def = compInfo.getComponentDefinition();
				addSharedAnnotationMap(_def.getAnnotationMap());
				addSharedAnnotationMap(compInfo.getAnnotationMap());
			} else {
				_def = (ComponentDefinition)curInfo;
				addSharedAnnotationMap(_def.getAnnotationMap());
			}
		} else {
			_def = lookupDefinition(exec, getClass());
			if (_def != null)
				addSharedAnnotationMap(_def.getAnnotationMap());
			else if ((this instanceof Macro)
			&& Library.getProperty("org.zkoss.zk.MacroNoDefinitionAllowed") == null)
				//3.0.3: check addition prop to allow user to maintain backward compatibility
				throw new DefinitionNotFoundException(
					"Component definition not found for the macro "+this.getClass()
					+". Current page definition: "
					+(exec != null ? ""+((ExecutionCtrl)exec).getCurrentPageDefinition(): "n/a")
					+". Current page: "
					+(exec != null ? ""+((ExecutionCtrl)exec).getCurrentPage(): "n/a"));
			else
				_def = ComponentsCtrl.DUMMY;
		}

		init(false);

		_spaceInfo = this instanceof IdSpace ? new SpaceInfo(this): null;

//		if (D.ON && log.debugable()) log.debug("Create comp: "+this);
	}
	private static final
	ComponentDefinition lookupDefinition(Execution exec, Class cls) {
		if (exec != null) {
			final ExecutionCtrl execCtrl = (ExecutionCtrl)exec;
			final PageDefinition pgdef = execCtrl.getCurrentPageDefinition();
			final Page page = execCtrl.getCurrentPage();

			final ComponentDefinition compdef =
				pgdef != null ? pgdef.getComponentDefinition(cls, true):
				page != null ? 	page.getComponentDefinition(cls, true): null;
			if (compdef != null) return compdef;

			return lookupDefinitionByDeviceType(exec.getDesktop().getDeviceType(), cls);
		}

		for (Iterator it = LanguageDefinition.getDeviceTypes().iterator(); it.hasNext();) {
			final ComponentDefinition compdef =
				lookupDefinitionByDeviceType((String)it.next(), cls);
			if (compdef != null)
				return compdef;
		}
		return null;
	}
	private static final ComponentDefinition
	lookupDefinitionByDeviceType(String deviceType, Class cls) {
		for (Iterator it = LanguageDefinition.getByDeviceType(deviceType).iterator();
		it.hasNext();) {
			final LanguageDefinition ld = (LanguageDefinition)it.next();
			try {
				return ld.getComponentDefinition(cls);
			} catch (DefinitionNotFoundException ex) { //ignore
			}
		}
		return null;
	}
	/** Initialize for contructor and serialization.
	 * @param cloning whether this method is called by clone()
	 */
	private void init(boolean cloning) {
		_apiChildren = new AbstractSequentialList() {
			public int size() {
				return _nChild;
			}
			public ListIterator listIterator(int index) {
				return new ChildIter(index);
			}
		};

		if (!cloning)
			_attrs = new HashMap(4);
	}

	/** Adds to the ID spaces, if any, when ID is changed.
	 * Caller has to make sure the uniqueness (and not auto id).
	 */
	private static void addToIdSpaces(final Component comp) {
		if (comp instanceof IdSpace)
			((AbstractComponent)comp).bindToIdSpace(comp);

		final IdSpace is = getSpaceOwnerOfParent(comp);
		if (is instanceof Component)
			((AbstractComponent)is).bindToIdSpace(comp);
		else if (is != null)
			((PageCtrl)is).addFellow(comp);
	}
	private static final IdSpace getSpaceOwnerOfParent(Component comp) {
		final Component parent = comp.getParent();
		if (parent != null) return parent.getSpaceOwner();
		else return comp.getPage();
	}
	/** Removes from the ID spaces, if any, when ID is changed. */
	private static void removeFromIdSpaces(final Component comp) {
		final String compId = getIdDirectly(comp);
		if (comp instanceof NonFellow || ComponentsCtrl.isAutoId(compId))
			return; //nothing to do

		if (comp instanceof IdSpace)
			((AbstractComponent)comp).unbindFromIdSpace(compId);

		final IdSpace is = getSpaceOwnerOfParent(comp);
		if (is instanceof Component)
			((AbstractComponent)is).unbindFromIdSpace(compId);
		else if (is != null)
			((PageCtrl)is).removeFellow(comp);
	}
	/** Checks the uniqueness in ID space when changing ID. */
	private static void checkIdSpaces(final AbstractComponent comp, String newId) {
		if (comp instanceof NonFellow)
			return; //no need to check

		if (comp instanceof IdSpace
		&& comp._spaceInfo.fellows.containsKey(newId))
			throw new UiException("Not unique in the ID space of "+comp);

		final IdSpace is = getSpaceOwnerOfParent(comp);
		if (is instanceof Component) {
			if (((AbstractComponent)is)._spaceInfo.fellows.containsKey(newId))
				throw new UiException("Not unique in the ID space of "+is);
		} else if (is != null) {
			if (((PageCtrl)is).hasFellow(newId))
				throw new UiException("Not unique in the ID space of "+is);
		}
	}

	/** Adds its descendants to the ID space when parent or page is changed,
	 * excluding comp.
	 */
	private static void addToIdSpacesDown(Component comp) {
		final IdSpace is = getSpaceOwnerOfParent(comp);
		if (is instanceof Component)
			addToIdSpacesDown(comp, (Component)is);
		else if (is != null)
			addToIdSpacesDown(comp, (PageCtrl)is);
	}
	/** comp's ID might be auto id. */
	private static void addToIdSpacesDown(Component comp, Component owner) {
		if (!(comp instanceof NonFellow)
		&& !ComponentsCtrl.isAutoId(getIdDirectly(comp)))
			((AbstractComponent)owner).bindToIdSpace(comp);
		if (!(comp instanceof IdSpace))
			for (Iterator it = comp.getChildren().iterator(); it.hasNext();)
				addToIdSpacesDown((Component)it.next(), owner); //recursive
	}
	/** comp's ID might be auto id. */
	private static void addToIdSpacesDown(Component comp, PageCtrl owner) {
		if (!(comp instanceof NonFellow)
		&& !ComponentsCtrl.isAutoId(getIdDirectly(comp)))
			owner.addFellow(comp);
		if (!(comp instanceof IdSpace))
			for (Iterator it = comp.getChildren().iterator(); it.hasNext();)
				addToIdSpacesDown((Component)it.next(), owner); //recursive
	}
	/** Similar to {@link #getId} except it won't generate one if not
	 * available.
	 */
	private static String getIdDirectly(Component comp) {
		return ((AbstractComponent)comp)._id;
	}

	/** Adds its descendants to the ID space when parent or page is changed,
	 * excluding comp.
	 */
	private static void removeFromIdSpacesDown(Component comp) {
		final IdSpace is = getSpaceOwnerOfParent(comp);
		if (is instanceof Component)
			removeFromIdSpacesDown(comp, (Component)is);
		else if (is != null)
			removeFromIdSpacesDown(comp, (PageCtrl)is);
	}
	private static void removeFromIdSpacesDown(Component comp, Component owner) {
		final String compId = getIdDirectly(comp);
		if (!(comp instanceof NonFellow)
		&& !ComponentsCtrl.isAutoId(compId))
			((AbstractComponent)owner).unbindFromIdSpace(compId);
		if (!(comp instanceof IdSpace))
			for (Iterator it = comp.getChildren().iterator(); it.hasNext();)
				removeFromIdSpacesDown((Component)it.next(), owner); //recursive
	}
	private static void removeFromIdSpacesDown(Component comp, PageCtrl owner) {
		if (!(comp instanceof NonFellow)
		&& !ComponentsCtrl.isAutoId(getIdDirectly(comp)))
			owner.removeFellow(comp);
		if (!(comp instanceof IdSpace))
			for (Iterator it = comp.getChildren().iterator(); it.hasNext();)
				removeFromIdSpacesDown((Component)it.next(), owner); //recursive
	}

	/** Checks the uniqueness in ID space when changing parent. */
	private static void checkIdSpacesDown(Component comp, Component newparent) {
		final IdSpace is = newparent.getSpaceOwner();
		if (is instanceof Component)
			checkIdSpacesDown(comp, ((AbstractComponent)is)._spaceInfo);
		else if (is != null)
			checkIdSpacesDown(comp, (PageCtrl)is);
	}
	/** Checks comp and its descendants for the specified SpaceInfo. */
	private static void checkIdSpacesDown(Component comp, SpaceInfo si) {
		final String compId = getIdDirectly(comp);
		if (!(comp instanceof NonFellow)
		&& !ComponentsCtrl.isAutoId(compId) && si.fellows.containsKey(compId))
			throw new UiException("Not unique in the new ID space: "+compId);
		if (!(comp instanceof IdSpace))
			for (Iterator it = comp.getChildren().iterator(); it.hasNext();)
				checkIdSpacesDown((Component)it.next(), si); //recursive
	}
	/** Checks comp and its descendants for the specified page. */
	private static void checkIdSpacesDown(Component comp, PageCtrl pageCtrl) {
		final String compId = getIdDirectly(comp);
		if (!(comp instanceof NonFellow)
		&& !ComponentsCtrl.isAutoId(compId) && pageCtrl.hasFellow(compId))
			throw new UiException("Not unique in the ID space of "+pageCtrl+": "+compId);
		if (!(comp instanceof IdSpace))
			for (Iterator it = comp.getChildren().iterator(); it.hasNext();)
				checkIdSpacesDown((Component)it.next(), pageCtrl); //recursive
	}

	/** Bind comp to this ID space (owned by this component).
	 * Called only if IdSpace is implemented.
	 * comp's ID must be unquie (and not auto id)
	 */
	private void bindToIdSpace(Component comp) {
		_spaceInfo.fellows.put(getIdDirectly(comp), comp);
	}
	/** Unbind comp from this ID space (owned by this component).
	 * Called only if IdSpace is implemented.
	 */
	private void unbindFromIdSpace(String compId) {
		_spaceInfo.fellows.remove(compId);
	}

	//-- Extra utlities --//
	/** Returns the mold URI based on {@link #getMold}
	 * and the molds defined in the component definition
	 * ({@link ComponentDefinition}).
	 *
	 * <p>As of release 3.0.0, it may return a String instance representing
	 * the URI, or a {@link ComponentRenderer} instance responsible for
	 * redrawing.
	 *
	 * <p>Used only for component implementation.
	 */
	protected Object getMoldURI() {
		return _def.getMoldURI(this, getMold());
	}

	/** Returns the UI engine based on {@link #_page}'s getDesktop().
	 * Don't call this method when _page is null.
	 */
	private final UiEngine getThisUiEngine() {
		return ((WebAppCtrl)_page.getDesktop().getWebApp()).getUiEngine();
	}

	//-- Component --//
	public final Page getPage() {
		return _page;
	}
	public final Desktop getDesktop() {
		return _page != null ? _page.getDesktop(): null;
	}

	public void setPage(Page page) {
		if (page != _page)
			setPageBefore(page, null); //append
	}
	public void setPageBefore(Page page, Component refRoot) {
		if (refRoot != null && (page == null || refRoot == this
		|| refRoot.getParent() != null || refRoot.getPage() != page))
			refRoot = null;

		if (_parent != null)
			throw new UiException("Only the parent of a root component can be changed: "+this);

		final Page oldpg = _page;
		final boolean samepg = page == _page;
		if (!samepg) {
			if (page != null) {
				if (_page != null && _page.getDesktop() != page.getDesktop())
					throw new UiException("The new page must be in the same desktop: "+page);
					//Not allow developers to access two desktops simutaneously
				checkIdSpacesDown(this, (PageCtrl)page);

				//No need to check UUID since checkIdSpacesDown covers it
				//-- a page is an ID space
			} else { //detach from a page
				checkDetach(_page);
			}

			if (_page != null) removeFromIdSpacesDown(this);
		}

		addMoved(_parent, _page, page); //Not depends on UUID
		if (!samepg)
			setPage0(page); //UUID might be changed here
		if (page != null && (samepg || refRoot != null))
			((PageCtrl)page).moveRoot(this, refRoot);

		if (!samepg && _page != null) addToIdSpacesDown(this);

		afterComponentPageChanged(page, oldpg);
	}
	/** Checks whether it is OK to detach the specified page.
	 * @param page the page to detach (never null).
	 */
	private static void checkDetach(Page page) {
		final Execution exec = Executions.getCurrent();
		if (exec == null)
			throw new UiException("You cannot access a desktop other than an event listener");
		if (page.getDesktop() != exec.getDesktop())
			throw new UiException("You cannot access components belong to other desktop");
	}
	/** Called when this component is moved from the specified parent
	 * and/or page to the new page.
	 *
	 * <p>Default: it notifies {@link UiEngine} to update the component
	 * at the client (usually remove-and-add).
	 *
	 * <p>It is designed to let derived classes overriding this method
	 * to disable this update. However, you rarely need to override it.
	 * One possible but rare case: the component's
	 * visual part at the client updates the visual representation
	 * at the client and then notify the component at the server
	 * to update its children accordingly. In this case, it is redudant
	 * if we ask UI Engine to send the updates to client.
	 *
	 * @param oldparent the parent before moved.
	 * The new parent can be found by calling {@link #getParent}.
	 * @param oldpg the parent before moved.
	 * @param newpg the new page. {@link #getPage} might return
	 * the old page.
	 */
	protected void addMoved(Component oldparent, Page oldpg, Page newpg) {
		final Desktop dt;
		if (oldpg != null) dt = oldpg.getDesktop();
		else if (newpg != null) dt = newpg.getDesktop();
		else return;

		((WebAppCtrl)dt.getWebApp())
			.getUiEngine().addMoved(this, oldparent, oldpg, newpg);
	}

	/** Ses the page without fixing IdSpace
	 */
	private void setPage0(Page page) {
		if (page == _page)
			return; //nothing changed

		//assert D.OFF || _parent == null || _parent.getPage() == page;
		//detach
		final boolean bRoot = _parent == null;
		if (_page != null) {
			if (bRoot) ((PageCtrl)_page).removeRoot(this);
			if (page == null) {
				((DesktopCtrl)_page.getDesktop()).removeComponent(this);
			}
		}

		final Page oldpage = _page;
		_page = page;

		if (_page != null) {
			if (bRoot) ((PageCtrl)_page).addRoot(this); //Not depends on uuid
			final Desktop desktop = _page.getDesktop();
			if (oldpage == null) {
				if (_uuid == null || _uuid == ComponentsCtrl.ANONYMOUS_ID
				|| desktop.getComponentByUuidIfAny(_uuid) != null)
					_uuid = nextUuid(desktop);
				if (_id == null || (this instanceof RawId))
					_id = _uuid;
					//no need to handle ID space since it is either
					//anonymous or uuid is not changed

				((DesktopCtrl)desktop).addComponent(this); //depends on uuid
			}

			onPageAttached(_page, oldpage);
		} else {
			onPageDetached(oldpage);
		}

		if (_spaceInfo != null && _parent == null)
			_spaceInfo.ns.setParent(page != null ? page.getNamespace(): null);

		//process all children recursively
		for (AbstractComponent p = _first; p != null; p = p._next)
			p.setPage0(page); //recursive
	}

	private String nextUuid(Desktop desktop) {
		final IdGenerator idgen =
			((WebAppCtrl)desktop.getWebApp()).getIdGenerator();
		String uuid;
		do {
			uuid = idgen != null ? idgen.nextComponentUuid(desktop, this): null;
			if (uuid == null)
				uuid = ((DesktopCtrl)desktop).getNextUuid();
		} while (desktop.getComponentByUuidIfAny(uuid) != null);
		return uuid;
	}
	public String getId() {
		if (_id == null)
			_id = getUuid();
		return _id;
	}
	public void setId(String id) {
		if (id == null || id.length() == 0)
			throw new UiException("ID cannot be empty");

		if (!Objects.equals(_id, id)) {
			if (Names.isReserved(id)
			|| (!(this instanceof NonFellow) && ComponentsCtrl.isAutoId(id)))
				throw new UiException("Invalid ID: "+id+". Cause: reserved words not allowed: "+Names.getReservedNames());

			final boolean rawId = this instanceof RawId;
			if (rawId && _page != null
			&& _page.getDesktop().getComponentByUuidIfAny(id) != null)
				throw new UiException("Replicated ID is not allowed for "+getClass()+": "+id+"\nNote: HTML/WML tags, ID must be unique");

			checkIdSpaces(this, id);

			removeFromIdSpaces(this);
			if (rawId) { //we have to change UUID
				if (_page != null) {
					getThisUiEngine().addUuidChanged(this, false);
						//called before uuid is changed
					((DesktopCtrl)_page.getDesktop()).removeComponent(this);
				}

				_uuid = _id = id;

				if (_page != null) {
					((DesktopCtrl)_page.getDesktop()).addComponent(this);
					addMoved(_parent, _page, _page);
				}
			} else {
				_id = id;
			}
			addToIdSpaces(this);

			final Object xc = getExtraCtrl();
			if ((xc instanceof ZidRequired) && ((ZidRequired)xc).isZidRequired())
				smartUpdate("z.zid", _id);
		}
	}

	public final String getUuid() {
		if (_uuid == null) {
			final Execution exec = Executions.getCurrent();
			_uuid = exec == null ?
				ComponentsCtrl.ANONYMOUS_ID: nextUuid(exec.getDesktop());
			if (_id == null || (this instanceof RawId))
				_id = _uuid;
		}
		return _uuid;
	}

	public final IdSpace getSpaceOwner() {
		Component p = this;
		do {
			if (p instanceof IdSpace)
				return (IdSpace)p;
		} while ((p = p.getParent()) != null);
		return _page;
	}
	public Component getFellow(String compId) {
		if (this instanceof IdSpace) {
			final Component comp = (Component)_spaceInfo.fellows.get(compId);
			if (comp == null)
				if (compId != null && ComponentsCtrl.isAutoId(compId))
					throw new ComponentNotFoundException(MZk.AUTO_ID_NOT_LOCATABLE, compId);
				else
					throw new ComponentNotFoundException("Fellow component not found: "+compId);
			return comp;
		}

		final IdSpace idspace = getSpaceOwner();
		if (idspace == null)
			throw new ComponentNotFoundException("This component doesn't belong to any ID space: "+this);
		return idspace.getFellow(compId);
	}
	public Component getFellowIfAny(String compId) {
		if (this instanceof IdSpace)
			return (Component)_spaceInfo.fellows.get(compId);

		final IdSpace idspace = getSpaceOwner();
		return idspace == null ? null: idspace.getFellowIfAny(compId);
	}
	public Collection getFellows() {
		if (this instanceof IdSpace)
			return Collections.unmodifiableCollection(_spaceInfo.fellows.values());

		final IdSpace idspace = getSpaceOwner();
		return idspace == null ? Collections.EMPTY_LIST: idspace.getFellows();
	}

	public Component getNextSibling() {
		return _next;
	}
	public Component getPreviousSibling() {
		return _prev;
	}
	public Component getFirstChild() {
		return _first;
	}
	public Component getLastChild() {
		return _last;
	}

	public Map getAttributes(int scope) {
		switch (scope) {
		case SPACE_SCOPE:
			if (this instanceof IdSpace)
				return _spaceInfo.attrs;
			final IdSpace idspace = getSpaceOwner();
			return idspace instanceof Page ? ((Page)idspace).getAttributes():
				idspace == null ? Collections.EMPTY_MAP:
					((Component)idspace).getAttributes(SPACE_SCOPE);
		case PAGE_SCOPE:
			return _page != null ?
				_page.getAttributes(): Collections.EMPTY_MAP;
		case DESKTOP_SCOPE:
			return _page != null ?
				_page.getDesktop().getAttributes(): Collections.EMPTY_MAP;
		case SESSION_SCOPE:
			return _page != null ?
				_page.getDesktop().getSession().getAttributes(): Collections.EMPTY_MAP;
		case APPLICATION_SCOPE:
			return _page != null ?
				_page.getDesktop().getWebApp().getAttributes(): Collections.EMPTY_MAP;
		case COMPONENT_SCOPE:
			return _attrs;
		case REQUEST_SCOPE:
			final Execution exec = getExecution();
			if (exec != null) return exec.getAttributes();
			//fall thru
		default:
			return Collections.EMPTY_MAP;
		}
	}
	private final Execution getExecution() {
		return _page != null ? _page.getDesktop().getExecution():
			Executions.getCurrent();
	}
	public Object getAttribute(String name, int scope) {
		return getAttributes(scope).get(name);
	}
	public Object setAttribute(String name, Object value, int scope) {
		if (value != null) {
			final Map attrs = getAttributes(scope);
			if (attrs == Collections.EMPTY_MAP)
				throw new IllegalStateException("This component, "+this
					+", doesn't belong to the "+Components.scopeToString(scope)+" scope");
			return attrs.put(name, value);
		} else {
			return removeAttribute(name, scope);
		}
	}
	public Object removeAttribute(String name, int scope) {
			final Map attrs = getAttributes(scope);
			if (attrs == Collections.EMPTY_MAP)
				throw new IllegalStateException("This component doesn't belong to any ID space: "+this);
		return attrs.remove(name);
	}

	public final Map getAttributes() {
		return _attrs;
	}
	public final Object getAttribute(String name) {
		return _attrs.get(name);
	}
	public final Object setAttribute(String name, Object value) {
		return value != null ? _attrs.put(name, value): _attrs.remove(name);
	}
	public final Object removeAttribute(String name) {
		return _attrs.remove(name);
	}

	public void setVariable(String name, Object val, boolean local) {
		getNamespace().setVariable(name, val, local);
	}
	public boolean containsVariable(String name, boolean local) {
		return getNamespace().containsVariable(name, local);
	}
	public Object getVariable(String name, boolean local) {
		return getNamespace().getVariable(name, local);
	}
	public void unsetVariable(String name, boolean local) {
		getNamespace().unsetVariable(name, local);
	}

	public Component getParent() {
		return _parent;
	}
	public void setParent(Component parent) {
		if (_parent == parent)
			return; //nothing changed

		checkParentChild(parent, this);

		final boolean idSpaceChanged =
			parent != null ?
				parent.getSpaceOwner() !=
					(_parent != null ? _parent.getSpaceOwner(): _page):
				_page != null;

		if (idSpaceChanged) removeFromIdSpacesDown(this);

		//call removeChild and clear _parent
		final AbstractComponent op = _parent;
		if (op != null) {
			if (!op.inRemoving(this)) {
				op.markRemoving(this, true);
				try {
					op.removeChild(this); //spec: call back removeChild
				} finally {
					op.markRemoving(this, false);
				}
			}
			_parent = null; //op.removeChild assumes _parent not changed yet
		} else {
			if (_page != null)
				((PageCtrl)_page).removeRoot(this); //Not depends on uuid
		}

		//call insertBefore and set _parent
		if (parent != null) {
			final AbstractComponent np = (AbstractComponent)parent;
			if (!np.inAdding(this)) {
				np.markAdding(this, true);
				try {
					if (!np.insertBefore(this, null))
						return; //spec: call back inserBefore
				} finally {
					np.markAdding(this, false);
				}
			}
			_parent = np; //np.insertBefore assumes _parent not changed yet
		} //if parent == null, assume no page at all (so no addRoot)

		//correct _page
		final Page newpg = _parent != null ? _parent.getPage(): null,
			oldpg = _page;
		addMoved(op, _page, newpg); //Not depends on UUID
		setPage0(newpg); //UUID might be changed here

		if (_spaceInfo != null) //ID space owner
			_spaceInfo.ns.setParent(
				_parent != null ? _parent.getNamespace(): null);
		if (idSpaceChanged) addToIdSpacesDown(this); //called after setPage

		//call back UiLifeCycle
		afterComponentPageChanged(newpg, oldpg);
		if (newpg != null || oldpg != null) {
			final Desktop desktop = (oldpg != null ? oldpg: newpg).getDesktop();
			if (desktop != null) {
				((DesktopCtrl)desktop).afterComponentMoved(parent, this, op);
				desktop.getWebApp().getConfiguration().afterComponentMoved(parent, this, op);
			}
		}
	}
	private void afterComponentPageChanged(Page newpg, Page oldpg) {
		if (newpg == oldpg) return;

		final Desktop desktop = (oldpg != null ? oldpg: newpg).getDesktop();
		if (desktop == null) return; //just in case

		//Note: if newpg and oldpg both non-null, they must be the same
		if (oldpg != null) {
			((DesktopCtrl)desktop).afterComponentDetached(this, oldpg);
			desktop.getWebApp().getConfiguration().afterComponentDetached(this, oldpg);
		} else {
			((DesktopCtrl)desktop).afterComponentAttached(this, newpg);
			desktop.getWebApp().getConfiguration().afterComponentAttached(this, newpg);
		}
	}

	/** Returns whether the child is being removed.
	 */
	private boolean inRemoving(Component child) {
		return _rming != null && _rming.contains(child);
	}
	/** Sets if the child is being removed.
	 */
	private void markRemoving(Component child, boolean set) {
		if (set) {
			if (_rming == null) _rming = new HashSet(2);
			_rming.add(child);
		} else {
			if (_rming != null && _rming.remove(child) && _rming.isEmpty())
				_rming = null;
		}
	}
	/** Returns whether the child is being added.
	 */
	private boolean inAdding(Component child) {
		return _adding != null && _adding.contains(child);
	}
	/** Sets if the child is being added.
	 */
	private void markAdding(Component child, boolean set) {
		if (set) {
			if (_adding == null) _adding = new HashSet(2);
			_adding.add(child);
		} else {
			if (_adding != null && _adding.remove(child) && _adding.isEmpty())
				_adding = null;
		}
	}

	/**
	 * @param parent the parent (will-be). It may be null.
	 * @param child the child (will-be). It cannot be null.
	 */
	private static void checkParentChild(Component parent, Component child)
	throws UiException {
		if (parent != null) {
			if (((AbstractComponent)parent).inAdding(child))
				return; //check only once

			if (Components.isAncestor(child, parent))
				throw new UiException("A child cannot be a parent of its ancestor: "+child);
			if (!parent.isChildable())
				throw new UiException(parent+" doesn't allow any child, "+child);

			final Page parentpg = parent.getPage(), childpg = child.getPage();
			if (parentpg != null && childpg != null
			&& parentpg.getDesktop() != childpg.getDesktop())
				throw new UiException("The parent and child must be in the same desktop: "+parent);

			final Component oldparent = child.getParent();
			if (parent.getSpaceOwner() !=
			(oldparent != null ? oldparent.getSpaceOwner(): childpg))
				checkIdSpacesDown(child, parent);
		} else {
			final Page childpg = child.getPage();
			if (childpg != null)
				checkDetach(childpg);
		}
	}

	public boolean insertBefore(Component newChild, Component refChild) {
		checkParentChild(this, newChild);

		if (refChild != null && refChild.getParent() != this)
			refChild = null;

		if (newChild == refChild)
			return false; //nothing changed (Listbox and other assumes this)

		final AbstractComponent nc = (AbstractComponent)newChild;
		final boolean moved = nc._parent == this; //moved in the same parent
		if (moved) {
			if (nc._next == refChild)
				return false; //nothing changed
			nc.addMoved(this, _page, _page);

			//detach from original place
			setNext(nc._prev, nc._next);
			setPrev(nc._next, nc._prev);
		} else { //new added
			//Note: call setParent to detach nc from old parent, if any,
			//before maintaining nc's _next, _prev...
			if (!inAdding(nc)) {
				markAdding(nc, true);
				try {
					nc.setParent(this); //spec: callback setParent
				} finally {
					markAdding(nc, false);
				}
			} else {
				nc._parent = this;
				//Set it since deriving class might assume parent is correct
				//after insertBefore. For example, Tabs.insertBefore().
				//
				//However, we don't call setPage0 and other here,
				//since the codes will become too complex.
				//In other words, when super.insertBefore() returns in a
				//deriving class, _parent is correct but _page may or may not
			}
		}

		if (refChild != null) {
			final AbstractComponent ref = (AbstractComponent)refChild;
			setNext(nc, ref);
			setPrev(nc, ref._prev);
			setNext(ref._prev, nc);
			setPrev(ref, nc);
		} else {
			if (_last == null) {
				_first = _last = nc;
				nc._next = nc._prev = null;
			} else {
				_last._next = nc;
				nc._prev = _last;
				nc._next = null;
				_last = nc;
			}
		}

		++_modCntChd;
		if (!moved) { //new added
			++_nChild;
			onChildAdded(nc);
		}
		return true;
	}
	private final
	void setNext(AbstractComponent comp, AbstractComponent next) {
		if (comp != null) comp._next = next;
		else _first = next;
	}
	private final
	void setPrev(AbstractComponent comp, AbstractComponent prev) {
		if (comp != null) comp._prev = prev;
		else _last = prev;
	}

	/** Appends a child to the end of all children.
	 * It calls {@link #insertBefore} with refChild to be null.
	 * Derives cannot override this method, and they shall override
	 * {@link #insertBefore} instead.
	 */
	public final boolean appendChild(Component child) { //Yes, final; see below
		return insertBefore(child, null); //NOTE: we must go thru insertBefore
			//such that deriving is easy to override
	}
	public boolean removeChild(Component child) {
		if (child.getParent() != this)
			return false; //nothing to do

		final AbstractComponent oc = (AbstractComponent)child;
		setNext(oc._prev, oc._next);
		setPrev(oc._next, oc._prev);
		oc._next = oc._prev = null;

		if (!inRemoving(oc)) {
			markRemoving(oc, true);
			try {
				oc.setParent(null); //spec: call back setParent
			} finally {
				markRemoving(oc, false);
			}
		} else {
			oc._parent = null;
				//Correct it since deriving class might assume parent is
				//correct after insertBefore() returns.
				//refer to insertBefore for more info.
		}

		++_modCntChd;
		--_nChild;
		onChildRemoved(child);
		return true;
	}

	/** Default: return true (allows to have children).
	 */
	public boolean isChildable() {
		return true;
	}
	public List getChildren() {
		return _apiChildren;
	}
	/** Returns the root of the specified component.
	 */
	public Component getRoot() {
		for (Component comp = this;;) {
			final Component parent = comp.getParent();
			if (parent == null)
				return comp;
			comp = parent;
		}
	}


	public boolean isVisible() {
		return _visible;
	}
	public boolean setVisible(boolean visible) {
		final boolean old = _visible;
		if (old != visible) {
			_visible = visible;
			smartUpdate("visibility", _visible);
		}
		return old;
	}

	public boolean isInvalidated() {
		return _page == null || getThisUiEngine().isInvalidated(this);
	}
	public void invalidate() {
		if (_page != null)
			getThisUiEngine().addInvalidate(this);
	}
	public void response(String key, AuResponse response) {
		//if response not depend on this component, it must be generated
		if (_page != null) {
			getThisUiEngine().addResponse(key, response);
		} else if (response.getDepends() != this) {
			final Execution exec = Executions.getCurrent();
			if (exec != null)
				((WebAppCtrl)exec.getDesktop().getWebApp())
					.getUiEngine().addResponse(key, response);
		}
	}
	public void smartUpdate(String attr, String value) {
		if (_page != null) getThisUiEngine().addSmartUpdate(this, attr, value);
	}
	/** Smart-updates a property with a deferred value.
	 * A deferred value is used to encapsulate a value that shall be retrieved
	 * only in the rendering phase.
	 *
	 * @since 3.0.1
	 */
	public void smartUpdateDeferred(String attr, DeferredValue value) {
		if (_page != null) getThisUiEngine().addSmartUpdate(this, attr, value);
	}
	public void smartUpdateValues(String attr, Object[] values) {
		if (_page != null) getThisUiEngine().addSmartUpdate(this, attr, values);
	}
	/** A special smart-update that update a value in int.
	 * <p>It will invoke {@link #smartUpdate(String,String)} to update
	 * the attribute eventually.
	 */
	public void smartUpdate(String attr, int value) {
		smartUpdate(attr, Integer.toString(value));
	}
	/** A special smart-update that update a value in boolean.
	 * <p>It will invoke {@link #smartUpdate(String,String)} to update
	 * the attribute eventually.
	 */
	public void smartUpdate(String attr, boolean value) {
		smartUpdate(attr, Boolean.toString(value));
	}

	public void detach() {
		if (getParent() != null) setParent(null);
		else setPage(null);
	}

	/** Default: does nothing.
	 * @see Component#onChildAdded
	 */
	public void onChildAdded(Component child) {
	}
	/** Default: does nothing.
	 * @see Component#onChildRemoved
	 */
	public void onChildRemoved(Component child) {
	}
	/** Default: handles special event listeners.
	 * @see Component#onPageAttached
	 * @since 3.0.0
	 */
	public void onPageAttached(Page newpage, Page oldpage) {
		if (oldpage == null) //new added
			onListenerChange(newpage.getDesktop(), true);
	}
	/** Default: handles special event listeners.
	 * @see Component#onPageDetached
	 * @since 3.0.0
	 */
	public void onPageDetached(Page page) {
		onListenerChange(page.getDesktop(), false);
	}

	/** Default: null (no propagation at all).
	 */
	public Component getPropagatee(String evtnm) {
		return null;
	}

	/** Returns the mold used to render this component.
	 * Default: "default"
	 */
	public final String getMold() {
		return _mold;
	}
	public void setMold(String mold) {
		if (mold == null || mold.length() == 0)
			mold = "default";
		if (!Objects.equals(_mold, mold)) {
			if (!_def.hasMold(mold))
				throw new UiException("Unknown mold: "+mold
					+", while allowed include "+_def.getMoldNames());
			_mold = mold;
			invalidate();
		}
	}

	//-- in the redrawing phase --//
	/** Redraws this component.
	 * This method implements the mold mechanism.
	 * <ol>
	 * <li>It first invokes {@link #getMoldURI} to retrieve the mold
	 * to redraw. The mold is either an URI (String) or a
	 * {@link ComponentRenderer} instance.
	 * <li>If URI, it invokes {@link Execution#include} to generate
	 * the output.</li>
	 * <li>If a {@link ComponentRenderer} instance, {@link ComponentRenderer#render}
	 * is called to generate the output.</li>
	 * </ul>
	 */
	public void redraw(Writer out) throws IOException {
		final Object mold = getMoldURI();
		if (mold instanceof ComponentRenderer) {
			((ComponentRenderer)mold)
				.render(this, out != null ? out: ZkFns.getCurrentOut());
		} else {
			final StringBuffer buf = out instanceof StringWriter ?
				((StringWriter)out).getBuffer(): null;
			final int index = buf != null ? buf.length(): 0;

			final Map attrs = new HashMap(2);
			attrs.put("self", this);
			getExecution()
				.include(out, (String)mold, attrs, Execution.PASS_THRU_ATTR);

			//Trim output to have smaller output and to avoid
			//whitespace around the separator and space components
			if (buf != null)
				Strings.trim(buf, index);
		}
	}
	/* Default: does nothing.
	 */
	public void onDrawNewChild(Component child, StringBuffer out)
	throws IOException {
	}

	/** Returns if any non-deferrable (ASAP) event listener is registered
	 * for the specified event.
	 * Returns true if you want the component (on the server)
	 * to process the event immediately.
	 *
	 * <p>Default: return true if any non-deferable event listener of
	 * the specified event is found. In other words, it returns
	 * {@link Events#isListened} with asap = true.
	 *
	 * <p>This method is moved from {@link HtmlBasedComponent} to
	 * {@link AbstractComponent} since 3.0.0.
	 *
	 * @param evtnm the event name, such as onClick
	 * @since 3.0.0
	 */
	protected boolean isAsapRequired(String evtnm) {
		return Events.isListened(this, evtnm, true);
	}

	/** Detects if a non-deferrable event is registered, and appends
	 * a special attribute to denote it if true.
	 * The format of the generated attribute is as follows:
	 * <code>z.onChange="true"</code>.
	 *
	 * <p>This method is moved from {@link HtmlBasedComponent} to
	 * {@link AbstractComponent} since 3.0.0.
	 *
	 * @param sb the string buffer to hold the HTML attribute. If null and
	 * {@link #isAsapRequired} is true, a string buffer is created and returned.
	 * @param evtnm the event name, such as onClick
	 * @return the string buffer. If sb is null and {@link #isAsapRequired}
	 * returns false, null is returned.
	 * If the caller passed non-null sb, the returned value must be the same
	 * as sb (so it usually ignores the returned value).
	 * @see #appendAsapAttr(StringBuffer sb, String, boolean)
	 * @since 3.0.0
	 */
	protected StringBuffer appendAsapAttr(StringBuffer sb, String evtnm) {
		return appendAsapAttr(sb, evtnm, false);
	}
	/** Appends an attribute for the specified event name, say, onChange,
	 * if a non-deferrable listener is registered or enforce is true.
	 * The format of the generated attribute is as follows:
	 * <code>z.onChange="true"</code>.
	 *
	 * <p>appendAsapAttr(sb, evtnm) is the same as
	 * appendAsapAttr(sb, evtnm, false).
	 *
	 * @param enforce whether to append the event attribute even if
	 * {@link #isAsapRequired} returns false.
	 * If enforce is false, this method is the same as
	 * {@link #appendAsapAttr(StringBuffer, String)}
	 * @since 3.0.4
	 */
	protected StringBuffer appendAsapAttr(StringBuffer sb, String evtnm,
	boolean enforce) {
		if (enforce || isAsapRequired(evtnm)) {
			if (sb == null) sb = new StringBuffer(80);
			HTMLs.appendAttribute(sb, getAttrOfEvent(evtnm), true);
		}
		return sb;
	}
	private static String getAttrOfEvent(String evtnm) {
		return Events.ON_CLICK.equals(evtnm) ? "z.lfclk":
			Events.ON_RIGHT_CLICK.equals(evtnm) ? "z.rtclk":
			Events.ON_DOUBLE_CLICK.equals(evtnm) ? "z.dbclk":
				"z." + evtnm;
	}

	public boolean addEventListener(String evtnm, EventListener listener) {
		if (evtnm == null || listener == null)
			throw new IllegalArgumentException("null");
		if (!Events.isValid(evtnm))
			throw new IllegalArgumentException("Invalid event name: "+evtnm);

		final boolean asap = isAsapRequired(evtnm);

		if (_listeners == null) _listeners = new HashMap(8);

		List l = (List)_listeners.get(evtnm);
		if (l != null) {
			for (Iterator it = l.iterator(); it.hasNext();) {
				final EventListener li = (EventListener)it.next();
				if (listener.equals(li))
					return false;
			}
		} else {
			_listeners.put(evtnm, l = new LinkedList());
		}
		l.add(listener);

		final Desktop desktop = getDesktop();
		if (desktop != null) {
			if (Events.ON_CLIENT_INFO.equals(evtnm))
				response("clientInfo", new AuClientInfo(desktop));
			if (Events.ON_PIGGYBACK.equals(evtnm))
				((DesktopCtrl)desktop).onPiggybackListened(this, true);

			if (!asap && isAsapRequired(evtnm))
				smartUpdate(getAttrOfEvent(evtnm), "true");
		}
		return true;
	}
	public boolean removeEventListener(String evtnm, EventListener listener) {
		if (evtnm == null || listener == null)
			throw new IllegalArgumentException("null");

		if (_listeners != null) {
			final boolean asap = isAsapRequired(evtnm);
			final List l = (List)_listeners.get(evtnm);
			if (l != null) {
				for (Iterator it = l.iterator(); it.hasNext();) {
					final EventListener li = (EventListener)it.next();
					if (listener.equals(li)) {
						if (l.size() == 1)
							_listeners.remove(evtnm);
						else
							it.remove();

						final Desktop desktop = getDesktop();
						if (desktop != null) {
							onListenerChange(desktop, false);

							if (asap && !isAsapRequired(evtnm))
								smartUpdate(getAttrOfEvent(evtnm), null);
						}
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean addForward(
	String orgEvent, Component target, String targetEvent) {
		return addForward0(orgEvent, target, targetEvent, null);
	}
	public boolean addForward(
	String orgEvent, String targetPath, String targetEvent) {
		return addForward0(orgEvent, targetPath, targetEvent, null);
	}
	public boolean addForward(
	String orgEvent, Component target, String targetEvent, Object eventData) {
		return addForward0(orgEvent, target, targetEvent, eventData);
	}
	public boolean addForward(
	String orgEvent, String targetPath, String targetEvent, Object eventData) {
		return addForward0(orgEvent, targetPath, targetEvent, eventData);
	}
	/**
	 * @param target the target. It is either a component, or a string,
	 * which is used internal for implementing {@link #writeObject}
	 */
	private boolean addForward0(
	String orgEvent, Object target, String targetEvent, Object eventData) {
		if (orgEvent == null)
			orgEvent = "onClick";
		else if (!Events.isValid(orgEvent))
			throw new IllegalArgumentException("Illegal event name: "+orgEvent);
		if (targetEvent == null)
			targetEvent = orgEvent;
		else if (!Events.isValid(targetEvent))
			throw new IllegalArgumentException("Illegal event name: "+targetEvent);

		if (_forwards == null)
			_forwards = new HashMap(4);

		Object[] info = (Object[])_forwards.get(orgEvent);
		final List fwds;
		if (info != null) {
			fwds = (List)info[1];
			for (Iterator it = fwds.iterator(); it.hasNext();) {
				final Object[] fwd = (Object[])it.next();
				if (Objects.equals(fwd[0], target)
				&& Objects.equals(fwd[1], targetEvent)) { //found
					if (Objects.equals(fwd[2], eventData)) {
						return false;
					} else {
						fwd[2] = eventData;
						return true;
					}
				}
			}
		} else {
			final ForwardListener listener = new ForwardListener(orgEvent);
			addEventListener(orgEvent, listener);
			info = new Object[] {listener, fwds = new LinkedList()};
			_forwards.put(orgEvent, info);
		}

		fwds.add(new Object[] {target, targetEvent, eventData});
		return true;
	}
	public boolean removeForward(
	String orgEvent, Component target, String targetEvent) {
		return removeForward0(orgEvent, target, targetEvent);
	}
	public boolean removeForward(
	String orgEvent, String targetPath, String targetEvent) {
		return removeForward0(orgEvent, targetPath, targetEvent);
	}
	private boolean removeForward0(
	String orgEvent, Object target, String targetEvent) {
		if (_forwards != null) {
			final Object[] info = (Object[])_forwards.get(orgEvent);
			if (info != null) {
				final List fwds = (List)info[1];
				for (Iterator it = fwds.iterator(); it.hasNext();) {
					final Object[] fwd = (Object[])it.next();
					if (Objects.equals(fwd[0], target)
					&& Objects.equals(fwd[1], targetEvent)) { //found
						it.remove(); //remove it

						if (fwds.isEmpty()) { //no more event
							_forwards.remove(orgEvent);
							removeEventListener(
								orgEvent, (EventListener)info[0]);
						}
						return true;
					}
				}
			}
		}
		return false;
	}

	public Namespace getNamespace() {
		if (this instanceof IdSpace)
			return _spaceInfo.ns;

		final IdSpace idspace = getSpaceOwner();
		return idspace instanceof Page ? ((Page)idspace).getNamespace():
			idspace == null ? null: ((Component)idspace).getNamespace();
	}

	public boolean isListenerAvailable(String evtnm, boolean asap) {
		if (_listeners != null) {
			final List l = (List)_listeners.get(evtnm);
			if (l != null) {
				if (!asap)
					return !l.isEmpty();

				for (Iterator it = l.iterator(); it.hasNext();) {
					final EventListener li = (EventListener)it.next();
					if (!(li instanceof Deferrable)
					|| !(((Deferrable)li).isDeferrable()))
						return true;
				}
			}
		}
		return false;
	}
	public Iterator getListenerIterator(String evtnm) {
		if (_listeners != null) {
			final List l = (List)_listeners.get(evtnm);
			if (l != null)
				return new ListenerIterator(l);
		}
		return CollectionsX.EMPTY_ITERATOR;
	}

	public void applyProperties() {
		_def.applyProperties(this);
	}

	public ComponentDefinition getDefinition() {
		return _def;
	}

	//-- ComponentCtrl --//
	public void setComponentDefinition(ComponentDefinition compdef) {
		if (compdef == null)
			throw new IllegalArgumentException("null");
		if (!compdef.isInstance(this))
			throw new IllegalArgumentException("Incompatible "+compdef+" for "+this);
		_def = compdef;
	}

	public ZScript getEventHandler(String evtnm) {
		final EventHandler evthd =
			_evthds != null ? _evthds.get(this, evtnm): null;
		return evthd != null ? evthd.getZScript(): null;
	}
	public void addSharedEventHandlerMap(EventHandlerMap evthds) {
		if (evthds != null && !evthds.isEmpty()) {
			unshareEventHandlerMap(false);
			if (_evthds == null) {
				_evthds = evthds;
				_evthdsShared = true;
			} else {
				_evthds.addAll(evthds);
			}

			final Desktop desktop = getDesktop();
			if (desktop != null)
				onListenerChange(desktop, true);
		}
	}
	public Set getEventHandlerNames() {
		return _evthds != null ? _evthds.getEventNames(): Collections.EMPTY_SET;
	}
	private void onListenerChange(Desktop desktop, boolean listen) {
		if (listen) {
			if (Events.isListened(this, Events.ON_CLIENT_INFO, false)) //asap+deferrable
				response("clientInfo", new AuClientInfo(desktop));
				//We always fire event not a root, since we don't like to
				//check when setParent or setPage is called
			if (Events.isListened(this, Events.ON_PIGGYBACK, false))
				((DesktopCtrl)desktop).onPiggybackListened(this, true);
		} else {
			if (!Events.isListened(this, Events.ON_PIGGYBACK, false))
				((DesktopCtrl)desktop).onPiggybackListened(this, false);
		}
	}
	public void addEventHandler(String name, EventHandler evthd) {
		if (name == null || evthd == null)
			throw new IllegalArgumentException("name and evthd required");

		unshareEventHandlerMap(true);
		_evthds.add(name, evthd);
	}
	/** Clones the shared event handlers, if shared.
	 * @param autocreate whether to create an event handler map if not available.
	 */
	private void unshareEventHandlerMap(boolean autocreate) {
		if (_evthdsShared) {
			_evthds = (EventHandlerMap)_evthds.clone();
			_evthdsShared = false;
		} else if (autocreate && _evthds == null) {
			_evthds = new EventHandlerMap();
		}
	}

	public Annotation getAnnotation(String annotName) {
		return _annots != null ? _annots.getAnnotation(annotName): null;
	}
	public Annotation getAnnotation(String propName, String annotName) {
		return _annots != null ?
			_annots.getAnnotation(propName, annotName): null;
	}
	public Collection getAnnotations() {
		return _annots != null ?
			_annots.getAnnotations(): Collections.EMPTY_LIST;
	}
	public Collection getAnnotations(String propName) {
		return _annots != null ?
			_annots.getAnnotations(propName): Collections.EMPTY_LIST;
	}
	public List getAnnotatedPropertiesBy(String annotName) {
		return _annots != null ?
			_annots.getAnnotatedPropertiesBy(annotName): Collections.EMPTY_LIST;
	}
	public List getAnnotatedProperties() {
		return _annots != null ?
			_annots.getAnnotatedProperties(): Collections.EMPTY_LIST;
	}
	public void addSharedAnnotationMap(AnnotationMap annots) {
		if (annots != null && !annots.isEmpty()) {
			unshareAnnotationMap(false);
			if (_annots == null) {
				_annots = annots;
				_annotsShared = true;
			} else {
				_annots.addAll(annots);
			}
		}
	}
	public void addAnnotation(String annotName, Map annotAttrs) {
		unshareAnnotationMap(true);
		_annots.addAnnotation(annotName, annotAttrs);
	}
	public void addAnnotation(String propName, String annotName, Map annotAttrs) {
		unshareAnnotationMap(true);
		_annots.addAnnotation(propName, annotName, annotAttrs);
	}
	/** Clones the shared annotations, if shared.
	 * @param autocreate whether to create an annotation map if not available.
	 */
	private void unshareAnnotationMap(boolean autocreate) {
		if (_annotsShared) {
			_annots = (AnnotationMap)_annots.clone();
			_annotsShared = false;
		} else if (autocreate && _annots == null) {
			_annots = new AnnotationMap();
		}
	}

	public void sessionWillPassivate(Page page) {
		//nothing to do
	}
	public void sessionDidActivate(Page page) {
		sessionDidActivate0(page, this, true);
	}
	/** 
	 * @param pageLevelIdSpace whether this component's ID space is
	 * at the page level.
	 */
	private static void sessionDidActivate0(Page page,
	AbstractComponent comp, boolean pageLevelIdSpace) {
		comp._page = page;

		//Note: we need only to fix the first-level spaceInfo.
		//Others are handled by readObject
		if (pageLevelIdSpace && comp._spaceInfo != null) {
			pageLevelIdSpace = false;
			comp._spaceInfo.ns.setParent(page.getNamespace());
		}

		for (AbstractComponent p = comp._first; p != null; p = p._next) {
			sessionDidActivate0(page, p, pageLevelIdSpace); //recursive
		}
	}

	/** Returns the extra controls that tell ZK how to handle this component
	 * specially.
	 * It is used only by component developers.
	 *
	 * <p>It is simpler to override {@link #newExtraCtrl} instead of this.
	 * By use of {@link #newExtraCtrl}, you don't need to care of
	 * cloning and serialization.
	 *
	 * <p>Default: return the object being created by {@link #newExtraCtrl},
	 * if any.
	 *
	 * @see ComponentCtrl#getExtraCtrl
	 */
	public Object getExtraCtrl() {
		if (_xtrl == null)
			_xtrl = newExtraCtrl();
				//3.0.3: create as late as possible so component has a chance
				//to customize which object to instantiate
		return _xtrl;
	}
	/** Used by {@link #getExtraCtrl} to create extra controls.
	 * It is used only by component developers.
	 *
	 * <p>Default: return null.
	 *
	 * <p>To provide extra controls, it is simpler to override this method
	 * instead of {@link #getExtraCtrl}.
	 * By use of {@link #newExtraCtrl}, you don't need to care of
	 * cloning and serialization.
	 */
	protected Object newExtraCtrl() {
		return null;
	}

	/** Notifies that an {@link WrongValueException} instance is thrown,
	 * and {@link WrongValueException#getComponent} is this component.
	 * It is a callback and the component can store the error message,
	 * show up the custom information, or even 'eat' the exception.
	 *
	 * <p>Default: does nothing but returns ex.
	 *
	 * @param ex the exception being thrown (never null)
	 * @return the exception to throw, or null to ignore the exception
	 * In most cases, just return ex
	 * @since 2.4.0
	 */
	public WrongValueException onWrongValue(WrongValueException ex) {
		return ex;
	}

	/** Returns the command of the specified command ID, or null if not found.
	 * It searches only the command specific to this component.
	 * For global commands, use {@link org.zkoss.zk.au.AuRequest#getCommand}
	 * instead.
	 * <p>Default: does nothing but return null.
	 *
	 * @since 3.0.5
	 * @see org.zkoss.zk.au.ComponentCommand
	 */
	public Command getCommand(String cmdId) {
		return null;
	}

	//-- Object --//
	public String toString() {
		final String clsnm = getClass().getName();
		final int j = clsnm.lastIndexOf('.');
		return "<"+clsnm.substring(j+1)+' '
			+(_id == null || ComponentsCtrl.isAutoId(_id) ? _uuid: _id)+'>';
	}
	public final boolean equals(Object o) { //no more override
		return this == o;
	}

	/** Holds info shared of the same ID space. */
	private static class SpaceInfo {
		private Map attrs = new HashMap(8);
			//don't create it dynamically because _ip bind it at constructor
		private SimpleNamespace ns;
		/** A map of ((String id, Component fellow). */
		private Map fellows = new HashMap(32);

		private SpaceInfo(Component owner) {
			ns = new SimpleNamespace(owner);
			init(owner);
		}
		private SpaceInfo(Component owner, SimpleNamespace from) {
			ns = new SimpleNamespace(owner);
			ns.copy(from);
			init(owner);
		}
		private void init(Component owner) {
			ns.setVariable("spaceScope", attrs, true);
			ns.setVariable("spaceOwner", owner, true);
		}
	}

	private class ChildIter implements ListIterator  {
		private AbstractComponent _p, _lastRet;
		private int _j;
		private int _modCntSnap;

		private ChildIter(int index) {
			if (index < 0 || index > _nChild)
				throw new IndexOutOfBoundsException("Index: "+index+", Size: "+_nChild);

			if (index < (_nChild >> 1)) {
				_p = _first;
				for (_j = 0; _j < index; _j++)
					_p = _p._next;
			} else {
				_p = null; //means the end of the list
				for (_j = _nChild; _j > index; _j--)
					_p = _p != null ? _p._prev: _last;
			}

			_modCntSnap = _modCntChd;
		}
		public boolean hasNext() {
			checkComodification();
			return _j < _nChild;
		}
		public Object next() {
			if (_j >= _nChild)
				throw new java.util.NoSuchElementException();
			checkComodification();
			
			_lastRet = _p;
			_p = _p._next;
			_j++;
			return _lastRet;
		}
		public boolean hasPrevious() {
			checkComodification();
			return _j > 0;
		}
		public Object previous() {
		    if (_j <= 0)
				throw new java.util.NoSuchElementException();
			checkComodification();

		    _lastRet = _p = _p != null ? _p._prev: _last;
		    _j--;
		    return _lastRet;
		}
		private void checkComodification() {
			if (_modCntChd != _modCntSnap)
				throw new java.util.ConcurrentModificationException();
		}
		public int nextIndex() {
			return _j;
		}
		public int previousIndex() {
			return _j - 1;
		}
		public void add(Object o) {
			final Component newChild = (Component)o;
			if (newChild.getParent() == AbstractComponent.this)
				throw new UnsupportedOperationException("Unable to add component with the same parent: "+o);
				//1. it is confusing to allow adding (with replace)
				//2. the code is sophisticated
			checkComodification();

			insertBefore(newChild, _p);
			++_j;
			_lastRet = null;
				//spec: cause remove to throw ex if no next/previous
			++_modCntSnap;
				//don't assign _modCntChd directly since deriving class
				//might manipulate others in insertBefore
		}
		public void remove() {
			if (_lastRet == null)
				throw new IllegalStateException();
			checkComodification();

			removeChild(_lastRet);

			if (_p == _lastRet) _p = _lastRet._next; //previous was called
			else --_j; //next was called
			_lastRet = null;
			++_modCntSnap;
		}
		public void set(Object o) {
			throw new UnsupportedOperationException();
				//Possible to implement this but confusing to developers
				//if o has the same parent (since we have to move)
		}
	}

	//Cloneable//
	public Object clone() {
		final AbstractComponent clone;
		try {
			clone = (AbstractComponent)super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError();
		}

		//1. make it not belonging to any page
		clone._page = null;
		clone._parent = null;
		clone._xtrl = null; //Bug 1892396: _xtrl is an inner object so recreation is required

		//1a. clone attributes
		clone._attrs = new HashMap(4);
		for (Iterator it = _attrs.entrySet().iterator(); it.hasNext();) {
			final Map.Entry me = (Map.Entry)it.next();
			Object val = me.getValue();
			if (val instanceof ComponentCloneListener) {
				val = ((ComponentCloneListener)val).clone(clone);
				if (val == null) continue; //don't use it in clone
			}
			clone._attrs.put(me.getKey(), val);
		}

		//1b. clone listeners
		if (_listeners != null) {
			clone._listeners = new HashMap(4);
			for (Iterator it = _listeners.entrySet().iterator();
			it.hasNext();) {
				final Map.Entry me = (Map.Entry)it.next();
				final List list = new LinkedList();
				for (Iterator it2 = ((List)me.getValue()).iterator();
				it2.hasNext();) {
					Object val = it2.next();
					if (val instanceof ComponentCloneListener) {
						val = ((ComponentCloneListener)val).clone(clone);
						if (val == null) continue; //don't use it in clone
					}
					list.add(val);
				}
				if (!list.isEmpty())
					clone._listeners.put(me.getKey(), list);
			}
		}

		if (!_annotsShared && _annots != null)
			clone._annots = (AnnotationMap)_annots.clone();
		if (!_evthdsShared && _evthds != null)
			clone._evthds = (EventHandlerMap)_evthds.clone();

		//2. clone children (deep cloning)
		cloneChildren(clone);
		clone.init(true);

		//3. spaceinfo
		if (clone._spaceInfo != null) {
			clone._spaceInfo = new SpaceInfo(clone, _spaceInfo.ns);
			cloneSpaceInfo(clone, this._spaceInfo);
		}

		//4. clone _forwards
		if (clone._forwards != null) {
			clone._forwards = null;
			for (Iterator it = _forwards.entrySet().iterator(); it.hasNext();) {
				final Map.Entry me = (Map.Entry)it.next();
				final String orgEvent = (String)me.getKey();

				final Object[] info = (Object[])me.getValue();
				final List fwds = (List)info[1];
				for (Iterator e = fwds.iterator(); e.hasNext();) {
					final Object[] fwd = (Object[])e.next();
					clone.addForward0(orgEvent, fwd[0], (String)fwd[1], fwd[2]);
				}
			}
		}
		return clone;
	}
	private static final
	void cloneSpaceInfo(AbstractComponent clone, SpaceInfo from) {
		final SpaceInfo to = clone._spaceInfo;
		to.attrs = new HashMap(8);
		for (Iterator it = from.attrs.entrySet().iterator(); it.hasNext();) {
			final Map.Entry me = (Map.Entry)it.next();
			Object val = me.getValue();
			if (val instanceof ComponentCloneListener) {
				val = ((ComponentCloneListener)val).clone(clone);
				if (val == null) continue; //don't use it in clone
			}
			to.attrs.put(me.getKey(), val);
		}

		//rebuild ID space by binding itself and all children
		if (!ComponentsCtrl.isAutoId(getIdDirectly(clone)))
			clone.bindToIdSpace(clone);
		for (AbstractComponent p = clone._first; p != null; p = p._next)
			addToIdSpacesDown(p, clone);
	}
	private static final void cloneChildren(final AbstractComponent comp) {
		AbstractComponent q = null;
		for (AbstractComponent p = comp._first; p != null; p = p._next) {
			AbstractComponent child = (AbstractComponent)p.clone();
			if (q != null) q._next = child;
			else comp._first = child;
			child._prev = q;
			q = child;

			child._parent = comp; //correct it
			if (child._spaceInfo != null)
				child._spaceInfo.ns.setParent(comp.getNamespace());
		}
		comp._last = q;
	}

	//Serializable//
	//NOTE: they must be declared as private
	private synchronized void writeObject(java.io.ObjectOutputStream s)
	throws java.io.IOException {
		//No need to unshare since they are stored as an independent copy
		//unshareAnnotationMap(false);
		//unshareEventHandlerMap(false);

		s.defaultWriteObject();

		//write definition
		if (_def == ComponentsCtrl.DUMMY) {
			s.writeObject(null);
		} else {
			LanguageDefinition langdef = _def.getLanguageDefinition();
			if (langdef != null) {
				s.writeObject(langdef.getName());
				s.writeObject(_def.getName());
			} else {
				s.writeObject(_def);
			}
		}

		//write children
		for (AbstractComponent p = _first; p != null; p = p._next)
			s.writeObject(p);
		s.writeObject(null);

		//write attrs
		willSerialize(_attrs.values());
		Serializables.smartWrite(s, _attrs);

		if (_listeners != null)
			for (Iterator it = _listeners.entrySet().iterator(); it.hasNext();) {
				final Map.Entry me = (Map.Entry)it.next();
				s.writeObject(me.getKey());

				final Collection ls = (Collection)me.getValue();
				willSerialize(ls);
				Serializables.smartWrite(s, ls);
			}
		s.writeObject(null);

		//store _spaceInfo
		if (this instanceof IdSpace) {
			//write _spaceInfo.attrs
			willSerialize(_spaceInfo.attrs.values());
			Serializables.smartWrite(s, _spaceInfo.attrs);

			//write _spaceInfo.ns (only variables that are not fellows)
			for (Iterator it = _spaceInfo.ns.getVariableNames().iterator();
			it.hasNext();) {
				final String nm = (String)it.next();
				final Object val = _spaceInfo.ns.getVariable(nm, true);
				willSerialize(val); //always called even if not serializable

				if (isVariableSerializable(nm, val)
				&& (val instanceof java.io.Serializable || val instanceof java.io.Externalizable)) {
					s.writeObject(nm);
					s.writeObject(val);
				}
			}
			s.writeObject(null); //denote end-of-namespace
		}

		//write _forwards
		if (_forwards != null) {
			for (Iterator it = _forwards.entrySet().iterator(); it.hasNext();) {
				final Map.Entry me = (Map.Entry)it.next();
				s.writeObject(me.getKey()); //original event

				final Object[] info = (Object[])me.getValue();
				final List fwds = (List)info[1];
				s.writeInt(fwds.size());
				for (Iterator e = fwds.iterator(); e.hasNext();) {
					final Object[] fwd = (Object[])e.next();
					s.writeObject( //store target as string
						fwd[0] instanceof Component ?
							Components.componentToPath((Component)fwd[0], this):
							fwd[0]);
					s.writeObject(fwd[1]); //target event
					s.writeObject(fwd[2]); //forward data
				}
			}
		}
		s.writeObject(null);
	}
	private void willSerialize(Collection c) {
		if (c != null)
			for (Iterator it = c.iterator(); it.hasNext();)
				willSerialize(it.next());
	}
	private void willSerialize(Object o) {
		if (o instanceof ComponentSerializationListener)
			((ComponentSerializationListener)o).willSerialize(this);
	}
	private synchronized void readObject(java.io.ObjectInputStream s)
	throws java.io.IOException, ClassNotFoundException {
		s.defaultReadObject();

		init(false);

		//read definition
		Object def = s.readObject();
		if (def == null) {
			_def = ComponentsCtrl.DUMMY;
		} else if (def instanceof String) {
			LanguageDefinition langdef = LanguageDefinition.lookup((String)def);
			_def = langdef.getComponentDefinition((String)s.readObject());
		} else {
			_def = (ComponentDefinition)def;
		}

		//read children
		for (AbstractComponent q = null;;) {
			final AbstractComponent child = (AbstractComponent)s.readObject();
			if (child == null) {
				_last = q;
				break; //no more
			}
			if (q != null) q._next = child;
			else _first = child;
			child._prev = q;
			child._parent = this;
			q = child;
		}

		//read attrs
		Serializables.smartRead(s, _attrs);
		didDeserialize(_attrs.values());

		for (;;) {
			final String evtnm = (String)s.readObject();
			if (evtnm == null) break; //no more

			if (_listeners == null) _listeners = new HashMap(4);
			final Collection ls = Serializables.smartRead(s, (Collection)null);
			_listeners.put(evtnm, ls);
			didDeserialize(ls);
		}

		//restore _spaceInfo
		if (this instanceof IdSpace) {
			_spaceInfo = new SpaceInfo(this);

			//fix child's _spaceInfo's parent
			fixSpaceParentOneLevelDown(this, _spaceInfo.ns);

			//read _spaceInfo.attrs
			Serializables.smartRead(s, _spaceInfo.attrs);
			didDeserialize(_spaceInfo.attrs.values());

			//_spaceInfo.ns
			for (;;) {
				final String nm = (String)s.readObject();
				if (nm == null) break; //no more

				Object val = s.readObject();
				_spaceInfo.ns.setVariable(nm, val, true);
				didDeserialize(val);
			}

			//restore ID space by binding itself and all children
			if (!ComponentsCtrl.isAutoId(getIdDirectly(this)))
				bindToIdSpace(this);
			for (Iterator it = getChildren().iterator(); it.hasNext();)
				addToIdSpacesDown((Component)it.next(), this);
		}

		//restore _forwards
		for (;;) {
			final String orgEvent = (String)s.readObject();
			if (orgEvent == null)
				break;

			int sz = s.readInt();
			while (--sz >= 0)
				addForward0(orgEvent, s.readObject(),
					(String)s.readObject(), s.readObject());
					//Note: we don't call Components.pathToComponent here
					//since the parent doesn't deserialized completely
					//Rather, we handle it until the event is received
		}
	}
	private void didDeserialize(Collection c) {
		if (c != null)
			for (Iterator it = c.iterator(); it.hasNext();)
				didDeserialize(it.next());
	}
	private void didDeserialize(Object o) {
		if (o instanceof ComponentSerializationListener)
			((ComponentSerializationListener)o).didDeserialize(this);
	}
	private static boolean isVariableSerializable(String name, Object value) {
		return !"spaceScope".equals(name) && !"spaceOwner".equals(name)
			&& !(value instanceof Component);
	}
	/** Fixed Namespace's parent of children only one level.
	 */
	private static final
	void fixSpaceParentOneLevelDown(Component comp, Namespace nsparent) {
		for (Iterator it = comp.getChildren().iterator(); it.hasNext();) {
			final AbstractComponent child = (AbstractComponent)it.next();
			//Others are handled by readObject
			if (child._spaceInfo != null)
				child._spaceInfo.ns.setParent(nsparent);
			else
				fixSpaceParentOneLevelDown(child, nsparent); //recursive
		}
	}

	/** Used to forward events (for the forward conditions).
	 */
	private class ForwardListener
	implements EventListener, ComponentCloneListener {
	//Note: it is not serializable since it is handled by
	//AbstractComponent.writeObject

		private final String _orgEvent;
		private ForwardListener(String orgEvent) {
			_orgEvent = orgEvent;
		}

		public void onEvent(Event event) {
			final Object[] info = (Object[])_forwards.get(_orgEvent);
			if (info != null)
				for (Iterator it = ((List)info[1]).iterator(); it.hasNext();) {
					final Object[] fwd = (Object[])it.next();
					Component target =
						fwd[0] instanceof String ?
							Components.pathToComponent(
								(String)fwd[0], AbstractComponent.this):
							(Component)fwd[0];

					if (target == null) {
						final IdSpace owner = getSpaceOwner();
						if (owner instanceof Component) {
							target = (Component)owner;
						} else {
							//Use the root component instead
							for (target = AbstractComponent.this;;) {
								final Component p = target.getParent();
								if (p == null)
									break;
								target = p;
							}
						}
					}

					Events.postEvent(
						new ForwardEvent((String)fwd[1], target, event, fwd[2]));
				}
		}

		//ComponentCloneListener//
		public Object clone(Component comp) {
			return null; //handle by AbstractComponent.clone
		}
	}
}
