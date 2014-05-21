package es.caib.seycon.ng.model;

import java.util.GregorianCalendar;
import java.util.Iterator;

import es.caib.seycon.ng.comu.EstatContrasenya;

public class AccountPasswordEntityDaoImpl extends AccountPasswordEntityDaoBase
{

	public void toEstatContrasenya(
			es.caib.seycon.ng.model.AccountPasswordEntity source,
			es.caib.seycon.ng.comu.EstatContrasenya target)
	{
		target.setCaducitat(new GregorianCalendar());
		target.getCaducitat().setTime(source.getExpirationDate());
		target.setData(new GregorianCalendar());
		target.getData().setTime(source.getDate());
		// NOTA: aquí usuari es de tipus CodiUsuariEntity
		target.setAccountName(source.getAccount().getName());
		target.setDispatcher(source.getAccount().getDispatcher().getCodi());
		// Indicador de si és caducada
		target.setCaducada(new Boolean(source.getExpirationDate().before(
				GregorianCalendar.getInstance().getTime())));
		// Obtenim la política de contrasenyes
		// obtenim el tipus d'usuari
		TipusUsuariEntity tipusUsuari = source.getAccount().getPasswordPolicy();
		Iterator<PoliticaContrasenyaEntity> it;
		boolean trobat = false;
		DominiContrasenyaEntity dominiContrasenyes = source.getAccount().getDispatcher().getDomini();
		// cerquem la política de contrasenyes per al tipus d'usuari
		if (dominiContrasenyes.getPoliticaContrasenyes() != null)
		{
			for (it = dominiContrasenyes.getPoliticaContrasenyes().iterator(); !trobat
					&& it.hasNext();)
			{
				PoliticaContrasenyaEntity politica = it.next();
				if (tipusUsuari.getCodi().equals(
						politica.getTipusUsuariDomini().getCodi()))
				{
					target.setTipusPoliticaContrasenya(politica.getTipus());
					trobat = true;
				}
			}
		}
	}

	public AccountPasswordEntity estatContrasenyaToEntity(
			EstatContrasenya estatContrasenya)
	{
		throw new UnsupportedOperationException();
	}

}
