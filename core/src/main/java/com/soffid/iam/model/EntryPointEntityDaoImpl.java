// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.model;

import com.soffid.iam.api.AccessTreeAuthorization;
import com.soffid.iam.api.AccessTreeExecution;
import com.soffid.iam.model.EntryPointEntity;
import com.soffid.iam.model.EntryPointExecutableEntity;
import com.soffid.iam.model.EntryPointGroupEntity;
import com.soffid.iam.model.EntryPointIconEntity;
import com.soffid.iam.model.EntryPointRoleEntity;
import com.soffid.iam.model.EntryPointUserEntity;
import com.soffid.iam.utils.AmbitsExecucioPuntEntrada;
import com.soffid.iam.utils.TipusAutoritzacioPuntEntrada;

import es.caib.seycon.ng.model.*;

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
	public void toAccessTree(com.soffid.iam.model.EntryPointEntity source, com.soffid.iam.api.AccessTree target) {
		// @todo verify behavior of toPuntEntrada
		super.toAccessTree(source, target);
		toPuntEntradaCustom(source, target);
	}

	/**
	 * @see es.caib.seycon.ng.model.PuntEntradaEntityDao#toPuntEntrada(es.caib.seycon.ng.model.PuntEntradaEntity)
	 */
	public com.soffid.iam.api.AccessTree toAccessTree(final com.soffid.iam.model.EntryPointEntity entity) {
		// @todo verify behavior of toPuntEntrada
		return super.toAccessTree(entity);
	}

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the
	 * object store, a new, blank entity is created
	 */
	private com.soffid.iam.model.EntryPointEntity loadPuntEntradaEntityFromPuntEntrada(com.soffid.iam.api.AccessTree puntEntrada) {
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
	public com.soffid.iam.model.EntryPointEntity accessTreeToEntity(com.soffid.iam.api.AccessTree puntEntrada) {
		// @todo verify behavior of puntEntradaToEntity
		com.soffid.iam.model.EntryPointEntity entity = this.loadPuntEntradaEntityFromPuntEntrada(puntEntrada);
		this.accessTreeToEntity(puntEntrada, entity, true);
		return entity;
	}

	/**
	 * @see es.caib.seycon.ng.model.PuntEntradaEntityDao#puntEntradaToEntity(es.caib.seycon.ng.comu.PuntEntrada,
	 *      es.caib.seycon.ng.model.PuntEntradaEntity)
	 */
	public void accessTreeToEntity(com.soffid.iam.api.AccessTree source, com.soffid.iam.model.EntryPointEntity target, boolean copyIfNull) {
		// Cargamos la entidad si ya existe o se crea una nueva
		super.accessTreeToEntity(source, target, copyIfNull);

		puntEntradaToEntityCustom(source, target);
	}

	private void toPuntEntradaCustom(com.soffid.iam.model.EntryPointEntity source, com.soffid.iam.api.AccessTree target) {
		
		// Núm de columnes
		if (source.getNumberOfColumns() != null) target.setColumnsNumber(source.getNumberOfColumns().toString());
		
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
                autoritzacions.add(getEntryPointGroupEntityDao().toAccessTreeAuthorization(auto));
            }
		}
		if (autoritzaRol!=null && autoritzaRol.size()!=0) {
			autoritzaRol = new ArrayList(autoritzaRol);
			for (Iterator it = autoritzaRol.iterator(); it.hasNext(); ) {
                EntryPointRoleEntity auto = (EntryPointRoleEntity) it.next();
                if (source.getId() != null) auto.setEntryPoint(source);
                autoritzacions.add(getEntryPointRoleEntityDao().toAccessTreeAuthorization(auto));
            }
		}
		if (autoritzaUsuari!=null && autoritzaUsuari.size()!=0) {
			autoritzaUsuari = new ArrayList(autoritzaUsuari);
			for (Iterator it = autoritzaUsuari.iterator(); it.hasNext(); ) {
                EntryPointUserEntity auto = (EntryPointUserEntity) it.next();
                if (source.getId() != null) auto.setEntryPoint(source);
                AccessTreeAuthorization autoPUE = getEntryPointUserEntityDao().toAccessTreeAuthorization(auto);
                autoritzacions.add(autoPUE);
            }
		}
		target.setAuthorizations(autoritzacions);
		
		// Métodos de ejecución (se guardan por separado)
		List<AccessTreeExecution> metodes = getEntryPointExecutableEntityDao().toAccessTreeExecutionList(source.getExecutionMethod());
		target.setExecutions(metodes);
	
		// Iconas
		if (source.getIcon1() != null) {
			EntryPointIconEntity ico = getEntryPointIconEntityDao().findById(source.getIcon1());
			if (ico!=null) {
				target.setIcon1Image(ico.getIcon());
				target.setIcon1Id(ico.getId());
			}
		}
		if (source.getIcon2() != null) {
			EntryPointIconEntity ico = getEntryPointIconEntityDao().findById(source.getIcon2());
			if (ico!=null) {
				target.setIcon2Image(ico.getIcon());
				target.setIcon2Id(ico.getId());
			}
		}
		
		target.setXmlAccessTree(source.getXmlEntryPoint());
	}

	private void puntEntradaToEntityCustom(com.soffid.iam.api.AccessTree source, com.soffid.iam.model.EntryPointEntity target) {
		
		// Los valores S/N
		if (source.getVisible() != null) {
			target.setVisible(source.getVisible());
		} else
			target.setVisible("N"); //$NON-NLS-1$
		if ("S".equals(source.getMenu()) ) { //$NON-NLS-1$
			target.setMenu("S"); //$NON-NLS-1$
			target.setMenuType(source.getTypeMenu());
		} else {
			target.setMenu("N"); //$NON-NLS-1$
			target.setMenuType(null);
		}
		if (source.getIsPublic() != null) {
			target.setPublicAccess(source.getIsPublic());
		} else
			target.setPublicAccess("N"); //$NON-NLS-1$

		//Núm. de columnes
		String numCol = source.getColumnsNumber();
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
		if (source.getExecutions() != null) {
			List<EntryPointExecutableEntity> metodesPUE = getEntryPointExecutableEntityDao().accessTreeExecutionToEntityList(source.getExecutions());
			target.getExecutionMethod().clear();
			target.getExecutionMethod().addAll(metodesPUE);
		} else target.setExecutionMethod(new java.util.HashSet());

		// Autoritzacions (totes juntes)
		Collection autoritza = source.getAuthorizations();
		Collection autoritzaUsu = new HashSet();
		Collection autoritzaRol = new HashSet();
		Collection autoritzaGrup = new HashSet();
	
		if (autoritza != null) {
			for (Iterator it = autoritza.iterator(); it.hasNext(); ) {
                AccessTreeAuthorization auto = (AccessTreeAuthorization) it.next();
                String tipus = auto.getAuthorizationEntityType();
                if (TipusAutoritzacioPuntEntrada.USUARI.equals(tipus)) {
                    EntryPointUserEntity autoEntity = getEntryPointUserEntityDao().accessTreeAuthorizationToEntity(auto);
                    autoritzaUsu.add(autoEntity);
                } else if (TipusAutoritzacioPuntEntrada.ROL.equals(tipus)) {
                    autoritzaRol.add(getEntryPointRoleEntityDao().accessTreeAuthorizationToEntity(auto));
                } else if (TipusAutoritzacioPuntEntrada.GRUP.equals(tipus)) {
                    autoritzaGrup.add(getEntryPointGroupEntityDao().accessTreeAuthorizationToEntity(auto));
                }
            }
		}
		
		target.setAuthorizedUsers(autoritzaUsu);
		target.setAuthorizedRoles(autoritzaRol);
		target.setAuthorizedGroups(autoritzaGrup);
	}

}