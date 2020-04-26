package es.caib.bpm.ui.tree;

import java.util.Collection;

import org.zkoss.zul.Treeitem;

public class MenuTreeitem extends Treeitem {
	
	private static final long serialVersionUID = 1L;

	@Override
	public void setOpen(boolean open) {
		super.setOpen(open);
		if (open)
		{
			closeOthers ( this, null );
		}
	}

	private void closeOthers(Treeitem parent, Treeitem menuTreeitem) {
		for ( Treeitem sibling: (Collection<Treeitem>) parent.getTreechildren().getItems())
		{
			if (sibling != menuTreeitem)
			{
				closeChildren (sibling);
			}
		}
		Treeitem grandpa = parent.getParentItem();
		if (grandpa != null)
			closeOthers ( grandpa, parent );
		
	}

	private void closeChildren(Treeitem parent) {
		if (parent.getTreechildren() != null)
		{
			parent.setOpen(false);
			for ( Treeitem child: (Collection<Treeitem>) parent.getTreechildren().getItems())
			{
				closeChildren(child);
			}
		}
	}
}
