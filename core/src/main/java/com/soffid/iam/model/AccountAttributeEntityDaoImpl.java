//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import com.soffid.iam.api.Account;
import com.soffid.iam.api.UserData;
import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.SeyconException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.hibernate.Hibernate;

/**
 * DAO AccountAttributeEntity implementation
 */
public class AccountAttributeEntityDaoImpl extends AccountAttributeEntityDaoBase
{

	private static final String DATE_FORMAT = ("yyyy-MM-dd'T'HH:mm:ss"); //$NON-NLS-1$


	@Override
    public void toUserData(AccountAttributeEntity sourceEntity, UserData targetVO) {
		super.toUserData(sourceEntity, targetVO);
        targetVO.setAttribute(sourceEntity.getSystemMetadata() == null ?
        		sourceEntity.getMetadata().getName():
        		sourceEntity.getSystemMetadata().getName());
        targetVO.setAccountName(sourceEntity.getAccount().getName());
        targetVO.setSystemName(sourceEntity.getAccount().getSystem().getName());
    	targetVO.setDataLabel(sourceEntity.getSystemMetadata() == null ?
    			sourceEntity.getMetadata().getLabel():
    	sourceEntity.getSystemMetadata().getLabel() ) ;
    	if (targetVO.getDataLabel() == null || targetVO.getDataLabel().trim().length() == 0) 
    		targetVO.setDataLabel(sourceEntity.getSystemMetadata() == null?
    				sourceEntity.getMetadata().getName() : 
    				sourceEntity.getSystemMetadata().getName());
    	if (sourceEntity.getSystemMetadata() != null && sourceEntity.getValue() != null) {
        	targetVO.setValue(sourceEntity.getValue());
        	if(sourceEntity.getSystemMetadata().getType()!= null){
        		targetVO.setDateValue(null);
        		if(sourceEntity.getSystemMetadata().getType().toString().equals("D")){ //$NON-NLS-1$
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
        if(sourceEntity.getMetadata() != null && sourceEntity.getValue() != null) 
        {
        	targetVO.setValue(sourceEntity.getValue());
        	if(sourceEntity.getMetadata().getType()!= null){
        		targetVO.setDateValue(null);
        		if(sourceEntity.getMetadata().getType().toString().equals("D")){ //$NON-NLS-1$
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

	@Override
    public void userDataToEntity(UserData sourceVO, AccountAttributeEntity targetEntity, boolean copyIfNull) {
		super.userDataToEntity(sourceVO, targetEntity, copyIfNull);
        AccountEntity accountEntity = getAccountEntityDao().findByNameAndSystem(sourceVO.getAccountName(), sourceVO.getSystemName());
        if(accountEntity == null) {
        	throw new SeyconException(String.format(com.soffid.iam.model.Messages.getString("AccountAttributeEntityDaoImpl.1"), sourceVO.getAccountName(), sourceVO.getSystemName())); //$NON-NLS-1$
        }
        MetaDataEntity metadata = null;
        for (MetaDataEntity m: getMetaDataEntityDao()
        		.findByObjectTypeAndName(Account.class.getName(), 
        				sourceVO.getAttribute()))
        {
        	metadata = m;
        }
        AccountMetadataEntity systemMetadata = getAccountMetadataEntityDao().findByName(sourceVO.getSystemName(), sourceVO.getAttribute());
		if (metadata == null && systemMetadata == null) {
			throw new SeyconException(String.format(Messages.getString("DadaUsuariEntityDaoImpl.4"), sourceVO.getAttribute())); //$NON-NLS-1$
		}
        targetEntity.setAccount(accountEntity);
        targetEntity.setSystemMetadata(systemMetadata);
        targetEntity.setMetadata(systemMetadata == null ? metadata: null);
        targetEntity.setValue(sourceVO.getValue());
        if (metadata != null && TypeEnumeration.DATE_TYPE.equals(metadata.getType()))
        {
        	if (sourceVO.getDateValue() == null && sourceVO.getValue() != null)
        	{
        		sourceVO.setDateValue(parseDate(sourceVO.getValue()));
        	}
        	if (sourceVO.getDateValue() != null)
            {
				SimpleDateFormat curFormater = new SimpleDateFormat(DATE_FORMAT);  //$NON-NLS-1$
            	targetEntity.setValue(curFormater.format(sourceVO.getDateValue().getTime()));
            }
        }
	}

    @Override
	public void create(AccountAttributeEntity entity) {
		super.create(entity);
		if (Hibernate.isInitialized(entity.getAccount().getAttributes()))
			entity.getAccount().getAttributes().add(entity);
	}


	@Override
	public void remove(AccountAttributeEntity entity) {
		if (Hibernate.isInitialized(entity.getAccount().getAttributes()))
			entity.getAccount().getAttributes().remove(entity);
		super.remove(entity);
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

	@Override
	public void update(AccountAttributeEntity entity) {
		super.update(entity);
	}
}
