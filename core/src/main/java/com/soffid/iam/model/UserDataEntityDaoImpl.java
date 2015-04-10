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
import com.soffid.iam.model.UserDataEntity;
import com.soffid.iam.model.UserEntity;
import es.caib.seycon.ng.PrincipalStore;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.DadaUsuari;
import es.caib.seycon.ng.comu.TipusDada;
import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.utils.AutoritzacionsUsuari;
import es.caib.seycon.ng.utils.ExceptionTranslator;
import es.caib.seycon.ng.utils.Security;
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
	private static final String DATE_FORMAT = "yyyy.MM.dd HH.mm.ss";

	private void assertPhoneExists ()
	{
        org.hibernate.Query queryObject = getSessionFactory().getCurrentSession()
                        .createQuery("select max (tda.order) from com.soffid.iam.model.MetaDataEntity as tda"); //$NON-NLS-1$
        java.util.List results = queryObject.list();
       
		
        Long nou = new Long(2);
        if (!results.isEmpty())
        {
        	Long last =  (Long) results.get(0);
        	if (last != null && last.longValue() >= 2)
        		nou = new Long(last.longValue()+1);
        }
		//Trobar el major ordre existent
        MetaDataEntity tda = getMetaDataEntityDao().findDataTypeByName("PHONE"); //$NON-NLS-1$
    	if (tda == null)
    	{
    		tda = getMetaDataEntityDao().newMetaDataEntity();
    		tda.setName("PHONE"); //$NON-NLS-1$
    		tda.setOrder(nou);

    		getMetaDataEntityDao().create(tda);
    	}
	}
	
	public void create(com.soffid.iam.model.UserDataEntity dadaUsuari) throws RuntimeException {
		try {
			assertPhoneExists();
			super.create(dadaUsuari);
			if (Hibernate.isInitialized(dadaUsuari.getUser().getUserData()))
				dadaUsuari.getUser().getUserData().add(dadaUsuari);
			getSession(false).flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			e.printStackTrace();
			throw new SeyconException(String.format(Messages.getString("UserDataEntityDaoImpl.0"), dadaUsuari.getValue(), message));
		}
	}
	
	public void update(UserDataEntity dadaUsuariEntity) {
		try {
			assertPhoneExists();
			super.update(dadaUsuariEntity);
			getSession(false).flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("UserDataEntityDaoImpl.1"), dadaUsuariEntity.getValue(), message));
		}		
	}	
	
	public void remove(com.soffid.iam.model.UserDataEntity dadaUsuari) throws RuntimeException {
		try {
			super.remove(dadaUsuari);
			getSession(false).flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("UserDataEntityDaoImpl.2"), dadaUsuari.getValue(), message));
		}
	}
	
    public void toDadaUsuari(com.soffid.iam.model.UserDataEntity sourceEntity, es.caib.seycon.ng.comu.DadaUsuari targetVO) {
        super.toDadaUsuari(sourceEntity, targetVO);
        toDadaUsuariCustom(sourceEntity, targetVO);
    }
    
    private void toDadaUsuariCustom(com.soffid.iam.model.UserDataEntity sourceEntity, es.caib.seycon.ng.comu.DadaUsuari targetVO) {        
        targetVO.setCodiDada(sourceEntity.getDataType().getName());
        targetVO.setCodiUsuari(sourceEntity.getUser().getUserName());
    	targetVO.setDataLabel(sourceEntity.getDataType().getLabel());
    	if (targetVO.getDataLabel() == null || targetVO.getDataLabel().trim().length() == 0) 
    		targetVO.setDataLabel(sourceEntity.getDataType().getName());
        if(sourceEntity.getDataType() != null && sourceEntity.getValue() != null){
        	if(sourceEntity.getDataType().getType() != null){
        		if(sourceEntity.getDataType().getType().toString().equals("D")){ //$NON-NLS-1$
        			try{
        				String data = sourceEntity.getValue().toString();
        				SimpleDateFormat curFormater = new SimpleDateFormat(DATE_FORMAT);  //$NON-NLS-1$
        				Date dateObj = curFormater.parse(data);
        				Calendar calendar = Calendar.getInstance();
        				calendar .setTime(dateObj);
        				targetVO.setValorDadaDate(calendar);
        			}catch (ParseException e){
        				throw new SeyconException(String.format(Messages.getString("UserDataEntityDaoImpl.IsNotDate"), targetVO.getValorDada())); //$NON-NLS-1$
        			}
        		}else 
        			targetVO.setValorDadaDate(null);
        	}
        }
        targetVO.setVisibility(AutoritzacionsUsuari.getAttributeVisibility(sourceEntity.getUser(), sourceEntity.getDataType()));
    }

    /**
     * @see es.caib.seycon.ng.model.DadaUsuariEntityDao#toDadaUsuari(es.caib.seycon.ng.model.DadaUsuariEntity)
     */
    public es.caib.seycon.ng.comu.DadaUsuari toDadaUsuari(final com.soffid.iam.model.UserDataEntity entity) {
        // @todo verify behavior of toDadaUsuari
        DadaUsuari dadaUsuari = super.toDadaUsuari(entity);
        return dadaUsuari;
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private com.soffid.iam.model.UserDataEntity loadDadaUsuariEntityFromDadaUsuari(es.caib.seycon.ng.comu.DadaUsuari dadaUsuari) {
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

    
    private void dadaUsuariToEntityCustom(es.caib.seycon.ng.comu.DadaUsuari sourceVO, com.soffid.iam.model.UserDataEntity targetEntity) {    	
    		assertPhoneExists();
            UserEntity usuariEntity = getUserEntityDao().findByUserName(sourceVO.getCodiUsuari());
            if(usuariEntity == null){
            	throw new SeyconException(String.format(Messages.getString("UserDataEntityDaoImpl.3"), sourceVO.getCodiUsuari()));  //$NON-NLS-1$
            }
            MetaDataEntity tipusDadaEntity = getMetaDataEntityDao().findDataTypeByName(sourceVO.getCodiDada());
			if (tipusDadaEntity == null) {
				throw new SeyconException(String.format(Messages.getString("UserDataEntityDaoImpl.4"), sourceVO.getCodiDada())); //$NON-NLS-1$
			}
            targetEntity.setUser(usuariEntity);
            targetEntity.setDataType(tipusDadaEntity);
            if (tipusDadaEntity != null && TypeEnumeration.DATE_TYPE.equals(tipusDadaEntity.getType()))
            {
            	if (sourceVO.getValorDadaDate() == null && sourceVO.getValorDada() != null)
            	{
            		sourceVO.setValorDadaDate(parseDate(sourceVO.getValorDada()));
            	}
            	if (sourceVO.getValorDadaDate() != null)
	            {
					SimpleDateFormat curFormater = new SimpleDateFormat(DATE_FORMAT);  //$NON-NLS-1$
	            	targetEntity.setValue(curFormater.format(sourceVO.getValorDadaDate().getTime()));
	            }
            }
        }

    private Calendar parseDate(String valorDada) {
    	String leftParse ;
    	String rightParse = "";
    	String leftValue = valorDada;
    	String rightValue = ""; 
    	if (valorDada.endsWith("z") || valorDada.endsWith("Z"))
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
    			rightParse = "Z";
    			leftValue = valorDada.substring(0, last);
    			rightValue = valorDada.substring (last+1);
    		}
    		else if (last > 0)
    		{
    			rightParse = "X";
    		}
    	}
    	
    	if (leftValue.length() > 14 && leftValue.charAt(14) == ',')
        	leftParse = "yyyyMMddHHmmss,SSS";
    	else
    		leftParse = "yyyyMMddHHmmss.SSS";
    		
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
    public com.soffid.iam.model.UserDataEntity dadaUsuariToEntity(es.caib.seycon.ng.comu.DadaUsuari dadaUsuari) {
        // @todo verify behavior of dadaUsuariToEntity
        com.soffid.iam.model.UserDataEntity entity = this.loadDadaUsuariEntityFromDadaUsuari(dadaUsuari);
        this.dadaUsuariToEntity(dadaUsuari, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.DadaUsuariEntityDao#dadaUsuariToEntity(es.caib.seycon.ng.comu.DadaUsuari, es.caib.seycon.ng.model.DadaUsuariEntity)
     */
    public void dadaUsuariToEntity(es.caib.seycon.ng.comu.DadaUsuari sourceVO, com.soffid.iam.model.UserDataEntity targetEntity, boolean copyIfNull) {
        // @todo verify behavior of dadaUsuariToEntity
        super.dadaUsuariToEntity(sourceVO, targetEntity, copyIfNull);
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