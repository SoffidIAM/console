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

import com.soffid.iam.api.UserData;
import com.soffid.iam.utils.AutoritzacionsUsuari;
import com.soffid.iam.utils.ExceptionTranslator;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.PrincipalStore;
import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.model.*;
import es.caib.seycon.util.Base64;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import org.hibernate.Hibernate;

/**
 * @see es.caib.seycon.ng.model.DadaUsuariEntity
 */
public class UserDataEntityDaoImpl
    extends com.soffid.iam.model.UserDataEntityDaoBase
{
	
	/**
	 * 
	 */
	private static final String DATE_FORMAT = ("yyyy-MM-dd'T'HH:mm:ss"); //$NON-NLS-1$

	public void create(com.soffid.iam.model.UserDataEntity dadaUsuari) throws RuntimeException {
		try {
			
	    	if (dadaUsuari.getDataType().getUnique() != null &&
	    			dadaUsuari.getDataType().getUnique().booleanValue() &&
	    			dadaUsuari.getValue() != null &&
	    			! dadaUsuari.getValue().trim().isEmpty())
	    	{
				for (UserDataEntity du: findByTypeAndValue(dadaUsuari.getDataType().getName(), 
						dadaUsuari.getValue()))
				{
					if ( dadaUsuari.getValue().equals(du.getValue()))
						throw new SeyconException(String.format(Messages.getString("UserDataEntityDaoImpl.6"),  //$NON-NLS-1$
							du.getValue(), du.getUser().getUserName())); 
				}
	    	}
			

	    	super.create(dadaUsuari);
			if (Hibernate.isInitialized(dadaUsuari.getUser().getUserData()))
				dadaUsuari.getUser().getUserData().add(dadaUsuari);
			getSession(false).flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			e.printStackTrace();
			throw new SeyconException(String.format(Messages.getString("UserDataEntityDaoImpl.0"), dadaUsuari.getValue(), message), e);  //$NON-NLS-1$
		}
	}
	
	public void update(UserDataEntity dadaUsuariEntity) {
		try {
			
	    	if (dadaUsuariEntity.getDataType().getUnique() != null &&
	    			dadaUsuariEntity.getDataType().getUnique().booleanValue() )
	    	{
				for (UserDataEntity du: findByTypeAndValue(dadaUsuariEntity.getDataType().getName(), 
						dadaUsuariEntity.getValue()))
				{
					if (!dadaUsuariEntity.getId().equals(du.getId()))
						throw new SeyconException(String.format(Messages.getString("UserDataEntityDaoImpl.7"),  //$NON-NLS-1$
							du.getValue(), du.getUser().getUserName())); 
				}
	    	}

	    	super.update(dadaUsuariEntity);
			getSession(false).flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("UserDataEntityDaoImpl.1"), dadaUsuariEntity.getValue(), message), e);  //$NON-NLS-1$
		}		
	}	
	
	public void remove(com.soffid.iam.model.UserDataEntity dadaUsuari) throws RuntimeException {
		try {
			dadaUsuari.getUser().getUserData().remove(dadaUsuari);
			super.remove(dadaUsuari);
			getSession(false).flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("UserDataEntityDaoImpl.2"), dadaUsuari.getValue(), message), e);  //$NON-NLS-1$
		}
	}
	
    public void toUserData(com.soffid.iam.model.UserDataEntity sourceEntity, com.soffid.iam.api.UserData targetVO) {
        super.toUserData(sourceEntity, targetVO);
        toDadaUsuariCustom(sourceEntity, targetVO);
    }
    
    private void toDadaUsuariCustom(com.soffid.iam.model.UserDataEntity sourceEntity, com.soffid.iam.api.UserData targetVO) {        
        targetVO.setAttribute(sourceEntity.getDataType().getName());
        targetVO.setUser(sourceEntity.getUser().getUserName());
    	targetVO.setDataLabel(sourceEntity.getDataType().getLabel());
    	if (targetVO.getDataLabel() == null || targetVO.getDataLabel().trim().length() == 0) 
    		targetVO.setDataLabel(sourceEntity.getDataType().getName());
        if(sourceEntity.getDataType() != null && sourceEntity.getValue() != null){
        	if(sourceEntity.getDataType().getType() != null){
        		targetVO.setDateValue(null);
        		if(sourceEntity.getDataType().getType() == TypeEnumeration.DATE_TYPE ||
        				sourceEntity.getDataType().getType() == TypeEnumeration.DATE_TIME_TYPE) { //$NON-NLS-1$
        			Date dateObj = (Date) sourceEntity.getObjectValue();
        			if (dateObj != null)
        			{
	    				Calendar calendar = Calendar.getInstance();
	    				calendar .setTime(dateObj);
	    				targetVO.setDateValue(calendar);
        			}
        		} 
        	}
        }
        targetVO.setVisibility(sourceEntity.getAttributeVisibility());
    }

    /**
     * @see es.caib.seycon.ng.model.DadaUsuariEntityDao#toDadaUsuari(es.caib.seycon.ng.model.DadaUsuariEntity)
     */
    public com.soffid.iam.api.UserData toUserData(final com.soffid.iam.model.UserDataEntity entity) {
        // @todo verify behavior of toDadaUsuari
        UserData dadaUsuari = super.toUserData(entity);
        return dadaUsuari;
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private com.soffid.iam.model.UserDataEntity loadDadaUsuariEntityFromDadaUsuari(com.soffid.iam.api.UserData dadaUsuari) {
        com.soffid.iam.model.UserDataEntity dadaUsuariEntity = null;
        if(dadaUsuari.getId() != null){
        	dadaUsuariEntity = load(dadaUsuari.getId());
        }
        if (dadaUsuariEntity == null)
        {
            dadaUsuariEntity = newUserDataEntity();
        }
        return dadaUsuariEntity;
    }

    
    private void dadaUsuariToEntityCustom(com.soffid.iam.api.UserData sourceVO, com.soffid.iam.model.UserDataEntity targetEntity) {    	
            UserEntity usuariEntity = getUserEntityDao().findByUserName(sourceVO.getUser());
            if(usuariEntity == null){
            	throw new SeyconException(String.format(Messages.getString("UserDataEntityDaoImpl.3"), sourceVO.getUser()));  //$NON-NLS-1$
            }
            MetaDataEntity tipusDadaEntity = getMetaDataEntityDao().findDataTypeByName(sourceVO.getAttribute());
			if (tipusDadaEntity == null) {
				throw new SeyconException(String.format(Messages.getString("UserDataEntityDaoImpl.4"), sourceVO.getAttribute())); //$NON-NLS-1$
			}
            targetEntity.setUser(usuariEntity);
            targetEntity.setDataType(tipusDadaEntity);
            if (tipusDadaEntity != null && TypeEnumeration.DATE_TYPE.equals(tipusDadaEntity.getType()))
            {
            	if (sourceVO.getDateValue() == null)
            	{
            		targetEntity.setValue(null);
            	} else {
					SimpleDateFormat curFormater = new SimpleDateFormat(DATE_FORMAT);  //$NON-NLS-1$
	            	targetEntity.setValue(curFormater.format(sourceVO.getDateValue().getTime()));
	            }
            }
            if (TypeEnumeration.PHOTO_TYPE.equals(tipusDadaEntity.getType()) ||
            		TypeEnumeration.BINARY_TYPE.equals(tipusDadaEntity.getType()))
            {
            	if (sourceVO.getValue() != null && ! sourceVO.getValue().isEmpty() &&
            			sourceVO.getBlobDataValue() == null)
            	{
	            	targetEntity.setBlobDataValue(Base64.decode(sourceVO.getValue()));
	            }
            }
        }

    private Calendar parseDate(String valorDada) {
    	String leftParse ;
    	String rightParse = ""; //$NON-NLS-1$
    	String leftValue = valorDada;
    	String rightValue = "";  //$NON-NLS-1$
    	if (valorDada.endsWith("z") || valorDada.endsWith("Z")) //$NON-NLS-1$ //$NON-NLS-2$
    	{
        	leftValue = valorDada.substring(0, valorDada.length()-1);
    	}
    	else
    	{
    		int i = valorDada.lastIndexOf('+');
    		int j = valorDada.lastIndexOf('-');
    		int last = i > j ? i : j;
    		if (last > 0 && valorDada.length() - last == 5)
    		{
    			rightParse = "Z"; //$NON-NLS-1$
    			leftValue = valorDada.substring(0, last);
    			rightValue = valorDada.substring (last+1);
    		}
    		else if (last > 0)
    		{
    			rightParse = "X"; //$NON-NLS-1$
    		}
    	}
    	
    	if (leftValue.length() > 14 && leftValue.charAt(14) == ',')
        	leftParse = "yyyyMMddHHmmss,SSS"; //$NON-NLS-1$
    	else
    		leftParse = "yyyyMMddHHmmss.SSS"; //$NON-NLS-1$
    		
    	if (leftValue.length() < leftParse.length())
    		leftParse = leftParse.substring(0, leftValue.length());

    	SimpleDateFormat sdf = new SimpleDateFormat(leftParse + rightParse);
   		try {
			Date t = sdf.parse(leftValue + rightValue);
       		Calendar c = Calendar.getInstance();
       		c.setTime(t);
       		return c;
		} catch (ParseException e) {
    	}
    	return null;

	}

	/**
     * @see es.caib.seycon.ng.model.DadaUsuariEntityDao#dadaUsuariToEntity(es.caib.seycon.ng.comu.DadaUsuari)
     */
    public com.soffid.iam.model.UserDataEntity userDataToEntity(com.soffid.iam.api.UserData dadaUsuari) {
        // @todo verify behavior of dadaUsuariToEntity
        com.soffid.iam.model.UserDataEntity entity = this.loadDadaUsuariEntityFromDadaUsuari(dadaUsuari);
        this.userDataToEntity(dadaUsuari, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.DadaUsuariEntityDao#dadaUsuariToEntity(es.caib.seycon.ng.comu.DadaUsuari, es.caib.seycon.ng.model.DadaUsuariEntity)
     */
    public void userDataToEntity(com.soffid.iam.api.UserData sourceVO, com.soffid.iam.model.UserDataEntity targetEntity, boolean copyIfNull) {
        // @todo verify behavior of dadaUsuariToEntity
        super.userDataToEntity(sourceVO, targetEntity, copyIfNull);
        dadaUsuariToEntityCustom(sourceVO, targetEntity);
    }

    public void create(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof UserDataEntity) {
                UserDataEntity entity = (UserDataEntity) obj;
                this.create(entity);
            }
        }
	}

	public void update(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof UserDataEntity) {
                UserDataEntity entity = (UserDataEntity) obj;
                this.update(entity);
            }
        }
	}

	public void remove(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof UserDataEntity) {
                UserDataEntity entity = (UserDataEntity) obj;
                this.remove(entity);
            }
        }
	}

}
