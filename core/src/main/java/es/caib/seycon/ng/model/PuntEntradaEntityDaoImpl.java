// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;

import com.soffid.iam.model.EntryPointAccountEntity;

import es.caib.seycon.ng.comu.ArbrePuntEntrada;
import es.caib.seycon.ng.comu.AutoritzacioPuntEntrada;
import es.caib.seycon.ng.comu.ExecucioPuntEntrada;
import es.caib.seycon.ng.utils.AmbitsExecucioPuntEntrada;
import es.caib.seycon.ng.utils.TipusAutoritzacioPuntEntrada;

/**
 * @see es.caib.seycon.ng.model.PuntEntradaEntity
 */
public class PuntEntradaEntityDaoImpl extends
		es.caib.seycon.ng.model.PuntEntradaEntityDaoBase {
	/**
	 * @see es.caib.seycon.ng.model.PuntEntradaEntityDao#toPuntEntrada(es.caib.seycon.ng.model.PuntEntradaEntity,
	 *      es.caib.seycon.ng.comu.PuntEntrada)
	 */
	public void toPuntEntrada(es.caib.seycon.ng.model.PuntEntradaEntity source,
			es.caib.seycon.ng.comu.PuntEntrada target) {
		// @todo verify behavior of toPuntEntrada
		super.toPuntEntrada(source, target);
		toPuntEntradaCustom(source, target);
	}

	/**
	 * @see es.caib.seycon.ng.model.PuntEntradaEntityDao#toPuntEntrada(es.caib.seycon.ng.model.PuntEntradaEntity)
	 */
	public es.caib.seycon.ng.comu.PuntEntrada toPuntEntrada(
			final es.caib.seycon.ng.model.PuntEntradaEntity entity) {
		// @todo verify behavior of toPuntEntrada
		return super.toPuntEntrada(entity);
	}

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the
	 * object store, a new, blank entity is created
	 */
	private es.caib.seycon.ng.model.PuntEntradaEntity loadPuntEntradaEntityFromPuntEntrada(
			es.caib.seycon.ng.comu.PuntEntrada puntEntrada) {
		PuntEntradaEntity puntEntradaEntity = null;

		if (puntEntrada.getId() != null)
			puntEntradaEntity = load(puntEntrada.getId());
		if (puntEntradaEntity == null) {
			puntEntradaEntity = newPuntEntradaEntity();
		}
		return puntEntradaEntity;

	}

	/**
	 * @see es.caib.seycon.ng.model.PuntEntradaEntityDao#puntEntradaToEntity(es.caib.seycon.ng.comu.PuntEntrada)
	 */
	public es.caib.seycon.ng.model.PuntEntradaEntity puntEntradaToEntity(
			es.caib.seycon.ng.comu.PuntEntrada puntEntrada) {
		// @todo verify behavior of puntEntradaToEntity
		es.caib.seycon.ng.model.PuntEntradaEntity entity = this
				.loadPuntEntradaEntityFromPuntEntrada(puntEntrada);
		this.puntEntradaToEntity(puntEntrada, entity, true);
		return entity;
	}

	/**
	 * @see es.caib.seycon.ng.model.PuntEntradaEntityDao#puntEntradaToEntity(es.caib.seycon.ng.comu.PuntEntrada,
	 *      es.caib.seycon.ng.model.PuntEntradaEntity)
	 */
	public void puntEntradaToEntity(es.caib.seycon.ng.comu.PuntEntrada source,
			es.caib.seycon.ng.model.PuntEntradaEntity target, boolean copyIfNull) {
		// Cargamos la entidad si ya existe o se crea una nueva
		super.puntEntradaToEntity(source, target, copyIfNull);

		puntEntradaToEntityCustom(source, target);
	}

	private void toPuntEntradaCustom(
			es.caib.seycon.ng.model.PuntEntradaEntity source,
			es.caib.seycon.ng.comu.PuntEntrada target) {
		
		// Núm de columnes
		if (source.getNumcolumnes()!=null) target.setNumColumnes(source.getNumcolumnes().toString());
		
		// Autorizaciones
		Collection autoritzaGrup = source.getAutoritzaGrup();
		Collection autoritzaRol = source.getAutoritzaRol();
		Collection autoritzaUsuari = source.getAutoritzaUsuari();
		Collection<EntryPointAccountEntity> accounts = source.getAuthorizedAccounts();
		Collection autoritzacions = new ArrayList();
		
		if (autoritzaGrup!=null && autoritzaGrup.size()!=0) {
			autoritzaGrup = new ArrayList(autoritzaGrup);//you're so lazy...
			for (Iterator it = autoritzaGrup.iterator(); it.hasNext(); ) {
				AutoritzacioPUEGrupEntity auto = (AutoritzacioPUEGrupEntity) it.next();
				if (source.getId()!=null) auto.setPuntEntrada(source);
				autoritzacions.add(getAutoritzacioPUEGrupEntityDao().toAutoritzacioPuntEntrada(auto));
			}
		}
		if (autoritzaRol!=null && autoritzaRol.size()!=0) {
			autoritzaRol = new ArrayList(autoritzaRol);
			for (Iterator it = autoritzaRol.iterator(); it.hasNext(); ){
				AutoritzacioPUERolEntity auto = (AutoritzacioPUERolEntity) it.next();
				if (source.getId()!=null) auto.setPuntEntrada(source);
				autoritzacions.add(getAutoritzacioPUERolEntityDao().toAutoritzacioPuntEntrada(auto));
			}
		}
		if (accounts!=null && accounts.size()!=0) {
			accounts = new ArrayList<EntryPointAccountEntity>(accounts);
			for (Iterator<EntryPointAccountEntity> it = accounts.iterator(); it.hasNext(); ){
				EntryPointAccountEntity auto = it.next();
				if (source.getId()!=null) auto.setEntryPoint(source);
				autoritzacions.add(getEntryPointAccountEntityDao().toAutoritzacioPuntEntrada(auto));
			}
		}
		if (autoritzaUsuari!=null && autoritzaUsuari.size()!=0) {
			autoritzaUsuari = new ArrayList(autoritzaUsuari);
			for (Iterator it = autoritzaUsuari.iterator(); it.hasNext(); ) {
				AutoritzacioPUEUsuariEntity auto = (AutoritzacioPUEUsuariEntity) it.next();
				if (source.getId()!=null) auto.setPuntEntrada(source);
				AutoritzacioPuntEntrada autoPUE = getAutoritzacioPUEUsuariEntityDao().toAutoritzacioPuntEntrada(auto);
				autoritzacions.add(autoPUE);
			}
		}
		target.setAutoritzacions(autoritzacions);
		
		// Métodos de ejecución (se guardan por separado)
		List<ExecucioPuntEntrada> metodes = getExecucioPuntEntradaEntityDao().toExecucioPuntEntradaList(source.getMetodesExecucio());
		target.setExecucions(metodes);
	
		// Iconas
		if (source.getIcona1()!=null) {
			IconaEntity ico = getIconaEntityDao().findById(source.getIcona1());
			if (ico!=null) {
				target.setImgIcona1(ico.getIcona());
				target.setIdIcona1(ico.getId());
			}
		}
		if (source.getIcona2()!=null) {
			IconaEntity ico = getIconaEntityDao().findById(source.getIcona2());
			if (ico!=null) {
				target.setImgIcona2(ico.getIcona());
				target.setIdIcona2(ico.getId());
			}
		}
		
		target.setXmlPUE(source.getXmlPUE());
	}

	private void puntEntradaToEntityCustom(
			es.caib.seycon.ng.comu.PuntEntrada source,
			es.caib.seycon.ng.model.PuntEntradaEntity target) {
		
		// Los valores S/N
		if (source.getVisible() != null) {
			target.setVisible(source.getVisible());
		} else
			target.setVisible("N"); //$NON-NLS-1$
		if ("S".equals(source.getMenu()) ) { //$NON-NLS-1$
			target.setMenu("S"); //$NON-NLS-1$
			target.setTipusMenu(source.getTipusMenu());
		} else {
			target.setMenu("N"); //$NON-NLS-1$
			target.setTipusMenu(null);
		}
		if (source.getEsPublic() != null) {
			target.setEsPublic(source.getEsPublic());
		} else
			target.setEsPublic("N"); //$NON-NLS-1$

		//Núm. de columnes
		String numCol = source.getNumColumnes();
		if (numCol!=null && !"".equals(numCol.trim())) { //$NON-NLS-1$
			try {
				Long cols = Long.getLong(numCol);
				target.setNumcolumnes(cols);
			} catch(Exception ex) {
				target.setNumcolumnes(null);
			}
		} else 
			target.setNumcolumnes(null);
		
		// Métodes Execució
		if (source.getExecucions()!=null) {
			List<ExecucioPuntEntradaEntity> metodesPUE = getExecucioPuntEntradaEntityDao().execucioPuntEntradaToEntityList(source.getExecucions());
			target.getMetodesExecucio().clear();
			target.getMetodesExecucio().addAll(metodesPUE);
		} else target.setMetodesExecucio(new java.util.HashSet<ExecucioPuntEntradaEntity>());

		// Autoritzacions (totes juntes)
		Collection<AutoritzacioPuntEntrada> autoritza = source.getAutoritzacions();
		Collection<AutoritzacioPUEUsuariEntity> autoritzaUsu = new HashSet<AutoritzacioPUEUsuariEntity>();
		Collection<AutoritzacioPUERolEntity> autoritzaRol = new HashSet<AutoritzacioPUERolEntity>();
		Collection<AutoritzacioPUEGrupEntity> autoritzaGrup = new HashSet<AutoritzacioPUEGrupEntity>();
		Collection<EntryPointAccountEntity> autoritzaAccount = new HashSet<EntryPointAccountEntity>();
	
		if (autoritza != null) {
			for (Iterator<AutoritzacioPuntEntrada> it = autoritza.iterator(); it.hasNext();) {
				AutoritzacioPuntEntrada auto = (AutoritzacioPuntEntrada) it
						.next();
				String tipus = auto.getTipusEntitatAutoritzada();
				if (TipusAutoritzacioPuntEntrada.USUARI.equals(tipus)) {// Usuari
					AutoritzacioPUEUsuariEntity autoEntity = getAutoritzacioPUEUsuariEntityDao()
									.autoritzacioPuntEntradaToEntity(auto);
					autoritzaUsu.add(autoEntity);
				} else if (TipusAutoritzacioPuntEntrada.ROL.equals(tipus)) {// Rol
					autoritzaRol.add(getAutoritzacioPUERolEntityDao()
							.autoritzacioPuntEntradaToEntity(auto));
				} else if (TipusAutoritzacioPuntEntrada.ACCOUNT.equals(tipus)) {// Rol
					autoritzaAccount.add(getEntryPointAccountEntityDao()
							.autoritzacioPuntEntradaToEntity(auto));
				} else if (TipusAutoritzacioPuntEntrada.GRUP.equals(tipus)) {// Grup
					autoritzaGrup.add(getAutoritzacioPUEGrupEntityDao()
							.autoritzacioPuntEntradaToEntity(auto));
				}
			}
		}
		
		target.setAutoritzaUsuari(autoritzaUsu);
		target.setAutoritzaRol(autoritzaRol);
		target.setAutoritzaGrup(autoritzaGrup);
		target.setAuthorizedAccounts(autoritzaAccount);
	}

}