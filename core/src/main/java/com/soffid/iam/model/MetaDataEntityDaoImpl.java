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

import com.soffid.iam.model.MetaDataEntity;
import com.soffid.iam.utils.ExceptionTranslator;

import es.caib.seycon.ng.exception.SeyconException;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.soffid.iam.api.MetadataScope;

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
			throw new SeyconException(String.format(Messages.getString("MetaDataEntityDaoImpl.0"), tipusDada.getName(), message));
		}
	}

	public void remove(com.soffid.iam.model.MetaDataEntity tipusDada) throws RuntimeException {
		try {
			super.remove(tipusDada);
			getSession(false).flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("MetaDataEntityDaoImpl.1"), tipusDada.getName(), message));
		}
	}

	public void toDataType(com.soffid.iam.model.MetaDataEntity sourceEntity, com.soffid.iam.api.DataType targetVO) {
		super.toDataType(sourceEntity, targetVO);
		if (sourceEntity.getScope() == null)
			targetVO.setScope(MetadataScope.USER);
		if (sourceEntity.getLabel() == null)
			targetVO.setLabel(sourceEntity.getName());
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
		
		if (sourceVO.getScope() == MetadataScope.CUSTOM)
		{
			CustomObjectTypeEntity cot = getCustomObjectTypeEntityDao().findByName(sourceVO.getCustomObjectType());
			if (cot == null)
				throw new RuntimeException("Invalid custom object type "+sourceVO.getCustomObjectType());
			targetEntity.setObjectType(cot);
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