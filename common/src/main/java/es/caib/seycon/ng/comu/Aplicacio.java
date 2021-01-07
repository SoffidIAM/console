package es.caib.seycon.ng.comu;

import java.util.Map;

import com.soffid.iam.api.Application;
import com.soffid.iam.api.PagedResult;

public class Aplicacio extends AbstractAplicacio {

	public Aplicacio() {
		super();
	}

	public Aplicacio(AbstractAplicacio otherBean) {
		super(otherBean);
	}

	public Aplicacio(String codi, String relativeName, String nom) {
		super(codi, relativeName, nom);
	}

	@Override
	public void setParent(String parent) {
		if (parent == null ?  getParent() != null: ! parent.equals(getParent())) {
			super.setParent(parent);
			super.setNom(parent == null? getRelativeName(): parent+"/"+getRelativeName());
		}
	}

	@Override
	public void setRelativeName(String relativeName) {
		if (relativeName == null ?  getRelativeName() != null: ! relativeName.equals(getRelativeName())) {
			super.setRelativeName(relativeName);
			super.setNom(getParent() == null? getRelativeName(): getParent()+"/"+getRelativeName());
		}
	}

	@Override
	public void setNom(String nom) {
		if (nom == null ?  getNom() != null: ! nom.equals(getNom())) {
			super.setNom(nom);
			int i = nom.indexOf('/');
			if (i < 0) {
				super.setParent(null);
				super.setRelativeName(nom);
			} else {
				super.setParent(nom.substring(0, i));
				super.setRelativeName(nom.substring(i+1));
			}
		}
	}

	public static PagedResult<Aplicacio> toAplicacioList(PagedResult<Application> s) {
		PagedResult<Aplicacio> r = new PagedResult<Aplicacio>();
		r.setItemsPerPage(s.getItemsPerPage());
		r.setStartIndex(s.getStartIndex());
		r.setTotalResults(s.getTotalResults());
		r.setResources(toAplicacioList(s.getResources()));
		return r;
	}


}
