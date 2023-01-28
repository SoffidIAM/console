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

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.soffid.iam.api.MetadataScope;
import com.soffid.iam.utils.ExceptionTranslator;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.SeyconException;

/**
 * @see es.caib.seycon.ng.model.TipusDadaEntity
 */
public class MetaDataEntityDaoImpl extends
		com.soffid.iam.model.MetaDataEntityDaoBase {

	public void create(com.soffid.iam.model.MetaDataEntity tipusDada) throws RuntimeException {
		try {
			super.create(tipusDada);
			getSession(false).flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("MetaDataEntityDaoImpl.0"), tipusDada.getName(), message), e);
		}
	}

	public void remove(com.soffid.iam.model.MetaDataEntity tipusDada) throws RuntimeException {
		try {
			getSession().createQuery("delete from com.soffid.iam.model.ApplicationAttributeEntity as app where app.metadata.id=:id")
					.setLong("id", tipusDada.getId()).executeUpdate();
			getSession().createQuery("delete from com.soffid.iam.model.GroupAttributeEntity as app where app.metadata.id=:id")
					.setLong("id", tipusDada.getId()).executeUpdate();
			getSession().createQuery("delete from com.soffid.iam.model.RoleAttributeEntity as app where app.metadata.id=:id")
					.setLong("id", tipusDada.getId()).executeUpdate();
			getSession().createQuery("delete from com.soffid.iam.model.HostAttributeEntity as app where app.metadata.id=:id")
					.setLong("id", tipusDada.getId()).executeUpdate();
			getSession().createQuery("delete from com.soffid.iam.model.CustomObjectAttributeEntity as app where app.metadata.id=:id")
				.setLong("id", tipusDada.getId()).executeUpdate();
			getSession().createQuery("delete from com.soffid.iam.model.MailListAttributeEntity as app where app.metadata.id=:id")
				.setLong("id", tipusDada.getId()).executeUpdate();
			getSession().createQuery("delete from com.soffid.iam.model.UserDataEntity as app where app.dataType.id=:id")
				.setLong("id", tipusDada.getId()).executeUpdate();
			super.remove(tipusDada);
			getSession(false).flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("MetaDataEntityDaoImpl.1"), tipusDada.getName(), message), e);
		}
	}

	public void toDataType(com.soffid.iam.model.MetaDataEntity sourceEntity, com.soffid.iam.api.DataType targetVO) {
		super.toDataType(sourceEntity, targetVO);
		if (sourceEntity.getScope() == null)
			targetVO.setScope(MetadataScope.USER);
		if (sourceEntity.getLabel() == null || sourceEntity.getLabel().trim().isEmpty())
			targetVO.setLabel(sourceEntity.getNlsLabel() == null || sourceEntity.getNlsLabel().trim().isEmpty()?
					sourceEntity.getName():
					null);
		
		if (sourceEntity.getValues() == null || sourceEntity.getValues().length() == 0)
			targetVO.setValues ( new LinkedList<String>() );
		else
		{
			List<String> values = new LinkedList<String>();
			for (String s: sourceEntity.getValues().split(" "))
			{
				try
				{
					values.add (URLDecoder.decode(s, "UTF-8"));
				}
				catch (UnsupportedEncodingException e)
				{
					throw new RuntimeException (e);
				}
			}
			targetVO.setValues(values);
		}
		if (sourceEntity.getObjectType() != null)
			targetVO.setCustomObjectType(sourceEntity.getObjectType().getName());
		if (sourceEntity.getDataObjectType() != null)
			targetVO.setDataObjectType(sourceEntity.getDataObjectType().getName());
	}

	/**
	 * @see es.caib.seycon.ng.model.TipusDadaEntityDao#toTipusDada(es.caib.seycon.ng.model.TipusDadaEntity)
	 */
	public com.soffid.iam.api.DataType toDataType(final com.soffid.iam.model.MetaDataEntity entity) {
		return super.toDataType(entity);
	}

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the
	 * object store, a new, blank entity is created
	 */
	private com.soffid.iam.model.MetaDataEntity loadTipusDadaEntityFromTipusDada(com.soffid.iam.api.DataType tipusDada) {
		MetaDataEntity tipusDadaEntity = null;
		if (tipusDada.getId() != null) {
			tipusDadaEntity = load(tipusDada.getId());
		}
		if (tipusDadaEntity == null) {
			return newMetaDataEntity();
		}
		return tipusDadaEntity;
	}

	/**
	 * @see es.caib.seycon.ng.model.TipusDadaEntityDao#tipusDadaToEntity(es.caib.seycon.ng.comu.TipusDada)
	 */
	public com.soffid.iam.model.MetaDataEntity dataTypeToEntity(com.soffid.iam.api.DataType tipusDada) {
		com.soffid.iam.model.MetaDataEntity entity = this.loadTipusDadaEntityFromTipusDada(tipusDada);
		this.dataTypeToEntity(tipusDada, entity, true);
		return entity;
	}

	/**
	 * @see es.caib.seycon.ng.model.TipusDadaEntityDao#tipusDadaToEntity(es.caib.seycon.ng.comu.TipusDada,
	 *      es.caib.seycon.ng.model.TipusDadaEntity)
	 */
	public void dataTypeToEntity(com.soffid.iam.api.DataType sourceVO, com.soffid.iam.model.MetaDataEntity targetEntity, boolean copyIfNull) {
		super.dataTypeToEntity(sourceVO, targetEntity, copyIfNull);
		if (sourceVO.getValues() == null || sourceVO.getValues().isEmpty())
			targetEntity.setValues(null);
		else
		{
			StringBuffer b = new StringBuffer();
			for (String s: sourceVO.getValues())
			{
				if (b.length() > 0)
					b.append (" ");
				try
				{
					b.append (URLEncoder.encode(s, "UTF-8"));
				}
				catch (UnsupportedEncodingException e)
				{
					throw new RuntimeException (e);
				}
			}
			targetEntity.setValues(b.toString());
		}
		
		CustomObjectTypeEntity cot = getCustomObjectTypeEntityDao().findByName(sourceVO.getCustomObjectType());
		if (cot == null)
			throw new RuntimeException("Invalid object type "+sourceVO.getCustomObjectType());
		targetEntity.setObjectType(cot);
		if (sourceVO.getDataObjectType() == null)
			targetEntity.setDataObjectType(null);
		else {
			CustomObjectTypeEntity vdt = getCustomObjectTypeEntityDao().findByName(sourceVO.getDataObjectType());
			if (vdt == null)
				throw new RuntimeException("Invalid object type "+sourceVO.getDataObjectType());
			targetEntity.setDataObjectType(vdt);
		}
	}
	
	public void create(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof MetaDataEntity) {
                MetaDataEntity entity = (MetaDataEntity) obj;
                this.create(entity);
            }
        }
	}

	public void update(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof MetaDataEntity) {
                MetaDataEntity entity = (MetaDataEntity) obj;
                this.update(entity);
            }
        }
	}

	public void remove(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof MetaDataEntity) {
                MetaDataEntity entity = (MetaDataEntity) obj;
                this.remove(entity);
            }
        }
	}	

}