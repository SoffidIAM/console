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
		target.setMenu("S".equals(source.getMenu()));
		target.setPublicAccess("S".equals(source.getPublicAccess()));
		target.setVisible("S".equals(source.getVisible()));
		// Núm de columnes
		if (source.getNumberOfColumns() != null) target.setColumnsNumber(source.getNumberOfColumns());
		
		if (source.getSystem() == null)
			target.setSystem(null);
		else
			target.setSystem(source.getSystem().getName());

		// Autorizaciones
		Collection autoritzaGrup = source.getAuthorizedGroups();
		Collection autoritzaRol = source.getAuthorizedRoles();
		Collection autoritzaUsuari = source.getAuthorizedUsers();
		Collection<EntryPointAccountEntity> accounts = source.getAuthorizedAccounts();
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
		if (accounts!=null && accounts.size()!=0) {
			accounts = new ArrayList<EntryPointAccountEntity>(accounts);
			for (Iterator<EntryPointAccountEntity> it = accounts.iterator(); it.hasNext(); ){
				EntryPointAccountEntity auto = it.next();
				if (source.getId()!=null) auto.setEntryPoint(source);
				autoritzacions.add(getEntryPointAccountEntityDao().toAccessTreeAuthorization(auto));
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
			EntryPointIconEntity ico = getEntryPointIconEntityDao().load(source.getIcon1());
			if (ico!=null) {
				target.setIcon1Image(ico.getIcon());
				target.setIcon1Id(ico.getId());
			}
		}
		if (source.getIcon2() != null) {
			EntryPointIconEntity ico = getEntryPointIconEntityDao().load(source.getIcon2());
			if (ico!=null) {
				target.setIcon2Image(ico.getIcon());
				target.setIcon2Id(ico.getId());
			}
		}
		
		target.setXmlAccessTree(source.getXmlEntryPoint());
		target.setParentId(null);
		if (source.getInformationSystem() == null || source.getInformationSystem().getId() == null || 
				source.getInformationSystem().getId().longValue() == 0)
			target.setInformationSystem(null);
		else
			target.setInformationSystem(source.getInformationSystem().getName());
	}

	private void puntEntradaToEntityCustom(com.soffid.iam.api.AccessTree source, com.soffid.iam.model.EntryPointEntity target) {
		target.setVisible(source.isVisible()?"S":"N");
		target.setMenu(source.isMenu()?"S":"N");
		target.setPublicAccess(source.isPublicAccess()?"S": "N");

		//Núm. de columnes
		target.setNumberOfColumns( source.getColumnsNumber() );
		
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
		Collection<EntryPointAccountEntity> autoritzaAccount = new HashSet<EntryPointAccountEntity>();

		if (autoritza != null) {
			for (Iterator it = autoritza.iterator(); it.hasNext(); ) {
                AccessTreeAuthorization auto = (AccessTreeAuthorization) it.next();
                String tipus = auto.getAuthorizationEntityType();
                if (TipusAutoritzacioPuntEntrada.USUARI.equals(tipus)) {
                    EntryPointUserEntity autoEntity = getEntryPointUserEntityDao().accessTreeAuthorizationToEntity(auto);
                    autoritzaUsu.add(autoEntity);
                } else if (TipusAutoritzacioPuntEntrada.ROL.equals(tipus)) {
                    autoritzaRol.add(getEntryPointRoleEntityDao().accessTreeAuthorizationToEntity(auto));
				} else if (TipusAutoritzacioPuntEntrada.ACCOUNT.equals(tipus)) {// Rol
					autoritzaAccount.add(getEntryPointAccountEntityDao()
							.accessTreeAuthorizationToEntity(auto));
                } else if (TipusAutoritzacioPuntEntrada.GRUP.equals(tipus)) {
                    autoritzaGrup.add(getEntryPointGroupEntityDao().accessTreeAuthorizationToEntity(auto));
                }
            }
		}
		
		target.setAuthorizedUsers(autoritzaUsu);
		target.setAuthorizedRoles(autoritzaRol);
		target.setAuthorizedGroups(autoritzaGrup);
		target.setAuthorizedAccounts(autoritzaAccount);

		if ( source.getSystem() == null || source.getSystem().isEmpty())
			target.setSystem(null);
		else
		{
			SystemEntity d = getSystemEntityDao().findByName(source.getSystem());
			if (d == null)
				throw new RuntimeException ("System not found: "+source.getSystem());
			target.setSystem( d);
		}

		if ( source.getInformationSystem() == null || source.getInformationSystem().isEmpty())
			target.setInformationSystem(null);
		else
		{
			InformationSystemEntity d = getInformationSystemEntityDao().findByCode(source.getInformationSystem());
			if (d == null)
				throw new RuntimeException ("Information system not found: "+source.getSystem());
			target.setInformationSystem( d );
		}
	}

}