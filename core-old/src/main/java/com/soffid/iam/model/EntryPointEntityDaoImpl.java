// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.model;

import es.caib.seycon.ng.model.*;

import com.soffid.iam.model.EntryPointEntity;
import com.soffid.iam.model.EntryPointExecutableEntity;
import com.soffid.iam.model.EntryPointGroupEntity;
import com.soffid.iam.model.EntryPointIconEntity;
import com.soffid.iam.model.EntryPointRoleEntity;
import com.soffid.iam.model.EntryPointUserEntity;
import es.caib.seycon.ng.comu.ArbrePuntEntrada;
import es.caib.seycon.ng.comu.AutoritzacioPuntEntrada;
import es.caib.seycon.ng.comu.ExecucioPuntEntrada;
import es.caib.seycon.ng.utils.AmbitsExecucioPuntEntrada;
import es.caib.seycon.ng.utils.TipusAutoritzacioPuntEntrada;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import javax.swing.ImageIcon;

/**
 * @see es.caib.seycon.ng.model.PuntEntradaEntity
 */
public class EntryPointEntityDaoImpl extends
		com.soffid.iam.model.EntryPointEntityDaoBase {
	/**
	 * @see es.caib.seycon.ng.model.PuntEntradaEntityDao#toPuntEntrada(es.caib.seycon.ng.model.PuntEntradaEntity,
	 *      es.caib.seycon.ng.comu.PuntEntrada)
	 */
	public void toPuntEntrada(com.soffid.iam.model.EntryPointEntity source, es.caib.seycon.ng.comu.PuntEntrada target) {
		// @todo verify behavior of toPuntEntrada
		super.toPuntEntrada(source, target);
		toPuntEntradaCustom(source, target);
	}

	/**
	 * @see es.caib.seycon.ng.model.PuntEntradaEntityDao#toPuntEntrada(es.caib.seycon.ng.model.PuntEntradaEntity)
	 */
	public es.caib.seycon.ng.comu.PuntEntrada toPuntEntrada(final com.soffid.iam.model.EntryPointEntity entity) {
		// @todo verify behavior of toPuntEntrada
		return super.toPuntEntrada(entity);
	}

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the
	 * object store, a new, blank entity is created
	 */
	private com.soffid.iam.model.EntryPointEntity loadPuntEntradaEntityFromPuntEntrada(es.caib.seycon.ng.comu.PuntEntrada puntEntrada) {
		EntryPointEntity puntEntradaEntity = null;

		if (puntEntrada.getId() != null)
			puntEntradaEntity = load(puntEntrada.getId());
		if (puntEntradaEntity == null) {
			puntEntradaEntity = newEntryPointEntity();
		}
		return puntEntradaEntity;

	}

	/**
	 * @see es.caib.seycon.ng.model.PuntEntradaEntityDao#puntEntradaToEntity(es.caib.seycon.ng.comu.PuntEntrada)
	 */
	public com.soffid.iam.model.EntryPointEntity puntEntradaToEntity(es.caib.seycon.ng.comu.PuntEntrada puntEntrada) {
		// @todo verify behavior of puntEntradaToEntity
		com.soffid.iam.model.EntryPointEntity entity = this.loadPuntEntradaEntityFromPuntEntrada(puntEntrada);
		this.puntEntradaToEntity(puntEntrada, entity, true);
		return entity;
	}

	/**
	 * @see es.caib.seycon.ng.model.PuntEntradaEntityDao#puntEntradaToEntity(es.caib.seycon.ng.comu.PuntEntrada,
	 *      es.caib.seycon.ng.model.PuntEntradaEntity)
	 */
	public void puntEntradaToEntity(es.caib.seycon.ng.comu.PuntEntrada source, com.soffid.iam.model.EntryPointEntity target, boolean copyIfNull) {
		// Cargamos la entidad si ya existe o se crea una nueva
		super.puntEntradaToEntity(source, target, copyIfNull);

		puntEntradaToEntityCustom(source, target);
	}

	private void toPuntEntradaCustom(com.soffid.iam.model.EntryPointEntity source, es.caib.seycon.ng.comu.PuntEntrada target) {
		
		// Núm de columnes
		if (source.getNumberOfColumns() != null) target.setNumColumnes(source.getNumberOfColumns().toString());
		
		// Autorizaciones
		Collection autoritzaGrup = source.getAuthorizedGroups();
		Collection autoritzaRol = source.getAuthorizedRoles();
		Collection autoritzaUsuari = source.getAuthorizedUsers();
		Collection autoritzacions = new ArrayList();
		
		if (autoritzaGrup!=null && autoritzaGrup.size()!=0) {
			autoritzaGrup = new ArrayList(autoritzaGrup);//you're so lazy...
			for (Iterator it = autoritzaGrup.iterator(); it.hasNext(); ) {
                EntryPointGroupEntity auto = (EntryPointGroupEntity) it.next();
                if (source.getId() != null) auto.setEntryPoint(source);
                autoritzacions.add(getEntryPointGroupEntityDao().toAutoritzacioPuntEntrada(auto));
            }
		}
		if (autoritzaRol!=null && autoritzaRol.size()!=0) {
			autoritzaRol = new ArrayList(autoritzaRol);
			for (Iterator it = autoritzaRol.iterator(); it.hasNext(); ) {
                EntryPointRoleEntity auto = (EntryPointRoleEntity) it.next();
                if (source.getId() != null) auto.setEntryPoint(source);
                autoritzacions.add(getEntryPointRoleEntityDao().toAutoritzacioPuntEntrada(auto));
            }
		}
		if (autoritzaUsuari!=null && autoritzaUsuari.size()!=0) {
			autoritzaUsuari = new ArrayList(autoritzaUsuari);
			for (Iterator it = autoritzaUsuari.iterator(); it.hasNext(); ) {
                EntryPointUserEntity auto = (EntryPointUserEntity) it.next();
                if (source.getId() != null) auto.setEntryPoint(source);
                AutoritzacioPuntEntrada autoPUE = getEntryPointUserEntityDao().toAutoritzacioPuntEntrada(auto);
                autoritzacions.add(autoPUE);
            }
		}
		target.setAutoritzacions(autoritzacions);
		
		// Métodos de ejecución (se guardan por separado)
		List<ExecucioPuntEntrada> metodes = getEntryPointExecutableEntityDao().toExecucioPuntEntradaList(source.getExecutionMethod());
		target.setExecucions(metodes);
	
		// Iconas
		if (source.getIcon1() != null) {
			EntryPointIconEntity ico = getEntryPointIconEntityDao().findById(source.getIcon1());
			if (ico!=null) {
				target.setImgIcona1(ico.getIcon());
				target.setIdIcona1(ico.getId());
			}
		}
		if (source.getIcon2() != null) {
			EntryPointIconEntity ico = getEntryPointIconEntityDao().findById(source.getIcon2());
			if (ico!=null) {
				target.setImgIcona2(ico.getIcon());
				target.setIdIcona2(ico.getId());
			}
		}
		
		target.setXmlPUE(source.getXmlEntryPoint());
	}

	private void puntEntradaToEntityCustom(es.caib.seycon.ng.comu.PuntEntrada source, com.soffid.iam.model.EntryPointEntity target) {
		
		// Los valores S/N
		if (source.getVisible() != null) {
			target.setVisible(source.getVisible());
		} else
			target.setVisible("N"); //$NON-NLS-1$
		if ("S".equals(source.getMenu()) ) { //$NON-NLS-1$
			target.setMenu("S"); //$NON-NLS-1$
			target.setMenuType(source.getTipusMenu());
		} else {
			target.setMenu("N"); //$NON-NLS-1$
			target.setMenuType(null);
		}
		if (source.getEsPublic() != null) {
			target.setPublicAccess(source.getEsPublic());
		} else
			target.setPublicAccess("N"); //$NON-NLS-1$

		//Núm. de columnes
		String numCol = source.getNumColumnes();
		if (numCol!=null && !"".equals(numCol.trim())) { //$NON-NLS-1$
			try {
				Long cols = Long.getLong(numCol);
				target.setNumberOfColumns(cols);
			} catch(Exception ex) {
				target.setNumberOfColumns(null);
			}
		} else 
			target.setNumberOfColumns(null);
		
		// Métodes Execució
		if (source.getExecucions()!=null) {
			List<EntryPointExecutableEntity> metodesPUE = getEntryPointExecutableEntityDao().execucioPuntEntradaToEntityList(source.getExecucions());
			target.getExecutionMethod().clear();
			target.getExecutionMethod().addAll(metodesPUE);
		} else target.setExecutionMethod(new java.util.HashSet());

		// Autoritzacions (totes juntes)
		Collection autoritza = source.getAutoritzacions();
		Collection autoritzaUsu = new HashSet();
		Collection autoritzaRol = new HashSet();
		Collection autoritzaGrup = new HashSet();
	
		if (autoritza != null) {
			for (Iterator it = autoritza.iterator(); it.hasNext(); ) {
                AutoritzacioPuntEntrada auto = (AutoritzacioPuntEntrada) it.next();
                String tipus = auto.getTipusEntitatAutoritzada();
                if (TipusAutoritzacioPuntEntrada.USUARI.equals(tipus)) {
                    EntryPointUserEntity autoEntity = getEntryPointUserEntityDao().autoritzacioPuntEntradaToEntity(auto);
                    autoritzaUsu.add(autoEntity);
                } else if (TipusAutoritzacioPuntEntrada.ROL.equals(tipus)) {
                    autoritzaRol.add(getEntryPointRoleEntityDao().autoritzacioPuntEntradaToEntity(auto));
                } else if (TipusAutoritzacioPuntEntrada.GRUP.equals(tipus)) {
                    autoritzaGrup.add(getEntryPointGroupEntityDao().autoritzacioPuntEntradaToEntity(auto));
                }
            }
		}
		
		target.setAuthorizedUsers(autoritzaUsu);
		target.setAuthorizedRoles(autoritzaRol);
		target.setAuthorizedGroups(autoritzaGrup);
	}

}