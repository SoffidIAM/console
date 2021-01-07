package es.caib.seycon.ng.comu;

import java.util.Calendar;
import java.util.Map;

import com.soffid.iam.api.PagedResult;
import com.soffid.iam.api.User;

public class Usuari extends AbstractUsuari {

	public Usuari() {
		super();
	}

	public Usuari(AbstractUsuari otherBean) {
		super(otherBean);
	}

	public Usuari(Long id, String codi, String nom, String primerLlinatge, String segonLlinatge, String fullName,
			String tipusUsuari, String codiGrupPrimari, String descripcioGrupPrimari, String servidorHome,
			String servidorPerfil, String emailAddress, String aliesCorreu, String servidorCorreu, String nomCurt,
			String dominiCorreu, Boolean actiu, Boolean multiSessio, String comentari, String usuariCreacio,
			Calendar dataCreacioUsuari, String usuariDarreraModificacio, Calendar dataDarreraModificacioUsuari,
			Map<String, Object> attributes) {
		super(id, codi, nom, primerLlinatge, segonLlinatge, fullName, tipusUsuari, codiGrupPrimari, descripcioGrupPrimari,
				servidorHome, servidorPerfil, emailAddress, aliesCorreu, servidorCorreu, nomCurt, dominiCorreu, actiu,
				multiSessio, comentari, usuariCreacio, dataCreacioUsuari, usuariDarreraModificacio,
				dataDarreraModificacioUsuari, attributes);
		// TODO Auto-generated constructor stub
	}

	public Usuari(String codi, String nom, String primerLlinatge, String tipusUsuari, String codiGrupPrimari) {
		super(codi, nom, primerLlinatge, tipusUsuari, codiGrupPrimari);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setNomCurt(String nomCurt) {
		super.setNomCurt(nomCurt);
		if (nomCurt == null || nomCurt.trim().isEmpty() ||
				getDominiCorreu() == null || getDominiCorreu().trim().isEmpty()) {
			super.setEmailAddress(null);
		} else {
			super.setEmailAddress(getNomCurt()+"@"+getEmailAddress());
		}
	}

	@Override
	public void setEmailAddress(String emailAddress) {
		super.setEmailAddress(emailAddress);
		if (emailAddress == null) {
			super.setNomCurt(null);
			super.setDominiCorreu(null);
		} else {
			int i = emailAddress.indexOf('@');
			if (i >= 0) {
				super.setNomCurt(emailAddress.substring(0,i));
				super.setDominiCorreu(emailAddress.substring(i+1));
			} else {
				super.setNomCurt(emailAddress);
				super.setDominiCorreu(null);
			}
		}
	}

	@Override
	public void setDominiCorreu(String dominiCorreu) {
		super.setDominiCorreu(dominiCorreu);
		if (getNomCurt() == null || getNomCurt().trim().isEmpty() ||
				getDominiCorreu() == null || getDominiCorreu().trim().isEmpty()) {
			super.setEmailAddress(null);
		} else {
			super.setEmailAddress(getNomCurt()+"@"+getEmailAddress());
		}
	}

	public static PagedResult<Usuari> toUsuariList(PagedResult<User> s) {
		PagedResult<Usuari> r = new PagedResult<Usuari>();
		r.setItemsPerPage(s.getItemsPerPage());
		r.setStartIndex(s.getStartIndex());
		r.setTotalResults(s.getTotalResults());
		r.setResources(toUsuariList(s.getResources()));
		return r;
	}

}
