package es.caib.seycon.ng.comu;

import java.util.Date;
import java.util.Map;

import com.soffid.iam.api.Application;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.PagedResult;

public class Grup extends AbstractGrup {
	private static final long serialVersionUID = 1;

	public Grup() {
		super();
	}

	public Grup(AbstractGrup otherBean) {
		super(otherBean);
	}

	public Grup(String codi, String descripcio, String quota, String unitatOfimatica, String codiPare, String tipus,
			String nomServidorOfimatic, Long id, Boolean obsolet, Boolean organitzatiu, String seccioPressupostaria,
			Date beginDate, Date endDate,
			Map<String, Object> attributes) {
		super(codi, descripcio, quota, unitatOfimatica, codiPare, tipus, nomServidorOfimatic, id, obsolet, organitzatiu,
				seccioPressupostaria, beginDate, endDate, attributes);
	}

	public Grup(String codi, String descripcio) {
		super(codi, descripcio);
	}

	public static PagedResult<Grup> toGrupList(PagedResult<Group> s) {
		PagedResult<Grup> r = new PagedResult<Grup>();
		r.setItemsPerPage(s.getItemsPerPage());
		r.setStartIndex(s.getStartIndex());
		r.setTotalResults(s.getTotalResults());
		r.setResources(toGrupList(s.getResources()));
		return r;
	}

}
