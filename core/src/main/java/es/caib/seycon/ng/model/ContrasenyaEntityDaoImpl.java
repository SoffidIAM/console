// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import java.security.MessageDigest;
import java.sql.CallableStatement;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.security.MessageDigest;

import es.caib.seycon.ng.comu.EstatContrasenya;
import es.caib.seycon.ng.comu.Password;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.utils.ExceptionTranslator;

import org.hibernate.Session;

/**
 * @see es.caib.seycon.ng.model.ContrasenyaEntity
 */
public class ContrasenyaEntityDaoImpl extends
        es.caib.seycon.ng.model.ContrasenyaEntityDaoBase {

    @Override
    public void update(ContrasenyaEntity contrasenyaEntity) {
        try {
            super.update(contrasenyaEntity);
            getSession().flush();
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
                        throw new SeyconException(String.format(Messages.getString("ContrasenyaEntityDaoImpl.0"), //$NON-NLS-1$
                                        contrasenyaEntity.getContrasenya(), 
                                        message));
        }
    }

    public void create(
            es.caib.seycon.ng.model.ContrasenyaEntity contrasenya)
            throws RuntimeException {
        try {
            super.create(contrasenya);
            getSession(false).flush();
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("ContrasenyaEntityDaoImpl.1"), //$NON-NLS-1$
					contrasenya.getContrasenya(), 
					message));
        }
    }

    public void remove(es.caib.seycon.ng.model.ContrasenyaEntity contrasenya)
            throws RuntimeException {
        try {
            super.remove(contrasenya);
            getSession(false).flush();
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(
					String.format(Messages.getString("ContrasenyaEntityDaoImpl.2"),  //$NON-NLS-1$
							contrasenya.getContrasenya(), 
							message));
        }
    }

	public void toEstatContrasenya(es.caib.seycon.ng.model.ContrasenyaEntity source, es.caib.seycon.ng.comu.EstatContrasenya target) {
            target.setCaducitat(new GregorianCalendar());
            target.getCaducitat().setTime(source.getDataCaducitat());
            target.setData(new GregorianCalendar());
            target.getData().setTime(source.getData());
		// NOTA: aquí usuari es de tipus CodiUsuariEntity
		target.setUsuari(source.getUsuari().getCodi());
		DominiContrasenyaEntity dominiContrasenyes = source.getDomini();
		target.setDominiContrasenyes(dominiContrasenyes.getCodi());
		// Indicador de si és caducada
		target.setCaducada(new Boolean(source.getDataCaducitat().before(GregorianCalendar.getInstance().getTime())));
		// Obtenim la política de contrasenyes
		// obtenim el tipus d'usuari
		TipusUsuariEntity tipusUsuari = source.getUsuari().getTipusUsuari();
		Iterator<PoliticaContrasenyaEntity> it;
		boolean trobat = false;
		// cerquem la política de contrasenyes per al tipus d'usuari 
		if (dominiContrasenyes.getPoliticaContrasenyes() != null) {
			for (it = dominiContrasenyes.getPoliticaContrasenyes().iterator(); !trobat && it.hasNext();) {
				PoliticaContrasenyaEntity politica = it.next();
				if (tipusUsuari.getCodi().equals(politica.getTipusUsuariDomini().getCodi())) {
					target.setTipusPoliticaContrasenya(politica.getTipus());
					trobat = true;
				}
			}
		}
        }

    public ContrasenyaEntity estatContrasenyaToEntity(
            EstatContrasenya estatContrasenya) {
        throw new UnsupportedOperationException();
    }

}
