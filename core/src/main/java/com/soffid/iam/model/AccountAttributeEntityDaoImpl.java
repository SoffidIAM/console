//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;

import es.caib.seycon.ng.comu.DadaUsuari;
import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.model.AccountEntity;
import es.caib.seycon.ng.model.DadaUsuariEntity;
import es.caib.seycon.ng.model.Messages;
import es.caib.seycon.ng.model.TipusDadaEntity;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.utils.AutoritzacionsUsuari;

/**
 * DAO AccountAttributeEntity implementation
 */
public class AccountAttributeEntityDaoImpl extends AccountAttributeEntityDaoBase
{

	private static final String DATE_FORMAT = "yyyy.MM.dd HH.mm.ss"; //$NON-NLS-1$


	@Override
	public void toDadaUsuari(AccountAttributeEntity sourceEntity, DadaUsuari targetVO) {
		super.toDadaUsuari(sourceEntity, targetVO);
        targetVO.setCodiDada(sourceEntity.getMetadata().getName());
        targetVO.setAccountName(sourceEntity.getAccount().getName());
        targetVO.setSystemName(sourceEntity.getAccount().getDispatcher().getCodi());
    	targetVO.setDataLabel(sourceEntity.getMetadata().getLabel());
    	if (targetVO.getDataLabel() == null || targetVO.getDataLabel().trim().length() == 0) 
    		targetVO.setDataLabel(sourceEntity.getMetadata().getName());
        if(sourceEntity.getMetadata() != null && sourceEntity.getValue() != null) 
        {
        	targetVO.setValorDada(sourceEntity.getValue());
        	if(sourceEntity.getMetadata().getType()!= null){
        		if(sourceEntity.getMetadata().getType().toString().equals("D")){ //$NON-NLS-1$
        			try{
        				String data = sourceEntity.getValue().toString();
        				SimpleDateFormat curFormater = new SimpleDateFormat(DATE_FORMAT);  //$NON-NLS-1$
        				Date dateObj = curFormater.parse(data);
        				Calendar calendar = Calendar.getInstance();
        				calendar .setTime(dateObj);
        				targetVO.setValorDadaDate(calendar);
        			}catch (ParseException e){
        				throw new SeyconException(String.format(Messages.getString("DadaUsuariEntityDaoImpl.IsNotDate"), targetVO.getValorDada())); //$NON-NLS-1$
        			}
        		}else 
        			targetVO.setValorDadaDate(null);
        	}
        }
        targetVO.setVisibility(AutoritzacionsUsuari.getAttributeVisibility(sourceEntity.getAccount(), sourceEntity.getMetadata()));
	}

	@Override
	public void dadaUsuariToEntity(DadaUsuari sourceVO,
			AccountAttributeEntity targetEntity, boolean copyIfNull) {
		super.dadaUsuariToEntity(sourceVO, targetEntity, copyIfNull);
        AccountEntity accountEntity = getAccountEntityDao().findByNameAndDispatcher(sourceVO.getAccountName(), sourceVO.getSystemName());
        if(accountEntity == null) {
        	throw new SeyconException(String.format(com.soffid.iam.model.Messages.getString("AccountAttributeEntityDaoImpl.1"), sourceVO.getAccountName(), sourceVO.getSystemName())); //$NON-NLS-1$
        }
        AccountMetadataEntity metadata = getAccountMetadataEntityDao().findByName(sourceVO.getSystemName(), sourceVO.getCodiDada());
		if (metadata == null) {
			throw new SeyconException(String.format(Messages.getString("DadaUsuariEntityDaoImpl.4"), sourceVO.getCodiDada())); //$NON-NLS-1$
		}
        targetEntity.setAccount(accountEntity);
        targetEntity.setMetadata(metadata);
        targetEntity.setValue(sourceVO.getValorDada());
        if (metadata != null && TypeEnumeration.DATE_TYPE.equals(metadata.getType()))
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

    @Override
	public void create(AccountAttributeEntity entity) {
    	if (entity.getMetadata().getUnique() != null &&
    			entity.getMetadata().getUnique().booleanValue() )
    	{
			for (AccountAttributeEntity du: findByNameAndValue(entity.getMetadata().getSystem().getCodi(), 
					entity.getMetadata().getName(), entity.getValue()))
			{
				throw new SeyconException(String.format(com.soffid.iam.model.Messages.getString("AccountAttributeEntityDaoImpl.2"), 
						du.getValue(), du.getAccount().getName())); //$NON-NLS-1$
			}
    	}
		
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
    	if (entity.getMetadata().getUnique() != null &&
    			entity.getMetadata().getUnique().booleanValue() )
    	{
			for (AccountAttributeEntity du: findByNameAndValue(entity.getMetadata().getSystem().getCodi(), 
					entity.getMetadata().getName(), entity.getValue()) )
			{
				if (!du.getId().equals(entity.getId()))
					throw new SeyconException(String.format(com.soffid.iam.model.Messages.getString("AccountAttributeEntityDaoImpl.2"), 
							du.getValue(), du.getAccount().getName())); //$NON-NLS-1$
			}
    	}
		super.update(entity);
	}
}
