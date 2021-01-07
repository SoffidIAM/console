package es.caib.seycon.ng.comu;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import com.soffid.iam.api.PagedResult;
import com.soffid.iam.api.Role;

public class Rol extends AbstractRol {

	public Rol() {
		super();
	}

	public Rol(AbstractRol otherBean) {
		super(otherBean);
	}

	public Rol(String nom, String descripcio, String baseDeDades, String category, Boolean defecte, Boolean contrasenya,
			String codiAplicacio, Long id, String domini, Collection<RolGrant> ownerRoles, Collection<Grup> ownerGroups,
			Collection<RolGrant> granteeGroups, Collection<RolGrant> ownedRoles, Boolean gestionableWF,
			Date approvalStart, Date approvalEnd, Map<String, Object> attributes) {
		super(nom, descripcio, baseDeDades, category, defecte, contrasenya, codiAplicacio, id, domini, ownerRoles, ownerGroups,
				granteeGroups, ownedRoles, gestionableWF, approvalStart, approvalEnd, attributes);
	}

	public Rol(String nom, String descripcio, String baseDeDades, String codiAplicacio) {
		super(nom, descripcio, baseDeDades, codiAplicacio);
	}

	public static PagedResult<Rol> toRolList(PagedResult<Role> s) {
		PagedResult<Rol> r = new PagedResult<Rol>();
		r.setItemsPerPage(s.getItemsPerPage());
		r.setStartIndex(s.getStartIndex());
		r.setTotalResults(s.getTotalResults());
		r.setResources(toRolList(s.getResources()));
		return r;
	}

}
