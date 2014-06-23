// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import es.caib.seycon.ng.PrincipalStore;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.DadaUsuari;
import es.caib.seycon.ng.comu.TipusDada;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.utils.ExceptionTranslator;
import es.caib.seycon.ng.utils.Security;

/**
 * @see es.caib.seycon.ng.model.DadaUsuariEntity
 */
public class DadaUsuariEntityDaoImpl
    extends es.caib.seycon.ng.model.DadaUsuariEntityDaoBase
{
	
	private void assertPhoneExists ()
	{
        org.hibernate.Query queryObject = getSessionFactory().getCurrentSession()
                        .createQuery("select max (tda.ordre) from es.caib.seycon.ng.model.TipusDadaEntity as tda"); //$NON-NLS-1$
        java.util.List results = queryObject.list();
       
		
        Long nou = new Long(2);
        if (!results.isEmpty())
        {
        	Long last =  (Long) results.get(0);
        	if (last != null && last.longValue() >= 2)
        		nou = new Long(last.longValue()+1);
        }
		//Trobar el major ordre existent
        TipusDadaEntity tda = getTipusDadaEntityDao().findTipusDadaByCodi("PHONE"); //$NON-NLS-1$
    	if (tda == null)
    	{
    		tda = getTipusDadaEntityDao().newTipusDadaEntity();
    		tda.setCodi("PHONE"); //$NON-NLS-1$
    		tda.setOrdre(nou);

    		getTipusDadaEntityDao().create(tda);
    	}
	}
	
	public void create(
			es.caib.seycon.ng.model.DadaUsuariEntity dadaUsuari)
			throws RuntimeException {
		try {
			assertPhoneExists();
			super.create(dadaUsuari);
			getSession(false).flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			e.printStackTrace();
			throw new SeyconException(String.format(Messages.getString("DadaUsuariEntityDaoImpl.0"),  //$NON-NLS-1$
					dadaUsuari.getValorDada(),
					message));
		}
	}
	
	public void update(DadaUsuariEntity dadaUsuariEntity) {
		try {
			assertPhoneExists();
			super.update(dadaUsuariEntity);
			getSession(false).flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("DadaUsuariEntityDaoImpl.1"), //$NON-NLS-1$
					dadaUsuariEntity.getValorDada(), message));
		}		
	}	
	
	public void remove(
			es.caib.seycon.ng.model.DadaUsuariEntity dadaUsuari)
			throws RuntimeException {
		try {
			super.remove(dadaUsuari);
			getSession(false).flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("DadaUsuariEntityDaoImpl.2"), //$NON-NLS-1$
					dadaUsuari.getValorDada(), message));
		}
	}
	
    public void toDadaUsuari(
        es.caib.seycon.ng.model.DadaUsuariEntity sourceEntity,
        es.caib.seycon.ng.comu.DadaUsuari targetVO)
    {
        super.toDadaUsuari(sourceEntity, targetVO);
        toDadaUsuariCustom(sourceEntity, targetVO);
    }
    
    private void toDadaUsuariCustom(
    	es.caib.seycon.ng.model.DadaUsuariEntity sourceEntity,
        es.caib.seycon.ng.comu.DadaUsuari targetVO)
    {        
        targetVO.setCodiDada(sourceEntity.getTipusDada().getCodi());
        targetVO.setCodiUsuari(sourceEntity.getUsuari().getCodi());
        if(sourceEntity.getTipusDada()!=null && sourceEntity.getValorDada() != null){
        	if(sourceEntity.getTipusDada().getType()!= null){
        		if(sourceEntity.getTipusDada().getType().toString().equals("D")){ //$NON-NLS-1$
        			try{
        				String data = sourceEntity.getValorDada().toString();
        				SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");  //$NON-NLS-1$
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
    }

    /**
     * @see es.caib.seycon.ng.model.DadaUsuariEntityDao#toDadaUsuari(es.caib.seycon.ng.model.DadaUsuariEntity)
     */
    public es.caib.seycon.ng.comu.DadaUsuari toDadaUsuari(final es.caib.seycon.ng.model.DadaUsuariEntity entity)
    {
        // @todo verify behavior of toDadaUsuari
        DadaUsuari dadaUsuari = super.toDadaUsuari(entity);
        return dadaUsuari;
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private es.caib.seycon.ng.model.DadaUsuariEntity loadDadaUsuariEntityFromDadaUsuari(es.caib.seycon.ng.comu.DadaUsuari dadaUsuari)
    {
        es.caib.seycon.ng.model.DadaUsuariEntity dadaUsuariEntity = null;
        if(dadaUsuari.getId() != null){
        	dadaUsuariEntity = load(dadaUsuari.getId());
        }
        if (dadaUsuariEntity == null)
        {
            dadaUsuariEntity = newDadaUsuariEntity();
        }
        return dadaUsuariEntity;
    }

    
    private  void dadaUsuariToEntityCustom(
            es.caib.seycon.ng.comu.DadaUsuari sourceVO,
            es.caib.seycon.ng.model.DadaUsuariEntity targetEntity)
        {    	
    		assertPhoneExists();
            UsuariEntity usuariEntity = getUsuariEntityDao().findByCodi(sourceVO.getCodiUsuari());
            if(usuariEntity == null){
			throw new SeyconException(String.format(Messages.getString("DadaUsuariEntityDaoImpl.3"), sourceVO.getCodiUsuari()));  //$NON-NLS-1$
            }
            TipusDadaEntity tipusDadaEntity = getTipusDadaEntityDao().findTipusDadaByCodi(sourceVO.getCodiDada());
			if (tipusDadaEntity == null) {
				throw new SeyconException(String.format(Messages.getString("DadaUsuariEntityDaoImpl.4"), sourceVO.getCodiDada())); //$NON-NLS-1$
			}
            targetEntity.setUsuari(usuariEntity);
            targetEntity.setTipusDada(tipusDadaEntity);
        }

    /**
     * @see es.caib.seycon.ng.model.DadaUsuariEntityDao#dadaUsuariToEntity(es.caib.seycon.ng.comu.DadaUsuari)
     */
    public es.caib.seycon.ng.model.DadaUsuariEntity dadaUsuariToEntity(es.caib.seycon.ng.comu.DadaUsuari dadaUsuari)
    {
        // @todo verify behavior of dadaUsuariToEntity
        es.caib.seycon.ng.model.DadaUsuariEntity entity = this.loadDadaUsuariEntityFromDadaUsuari(dadaUsuari);
        this.dadaUsuariToEntity(dadaUsuari, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.DadaUsuariEntityDao#dadaUsuariToEntity(es.caib.seycon.ng.comu.DadaUsuari, es.caib.seycon.ng.model.DadaUsuariEntity)
     */
    public void dadaUsuariToEntity(
        es.caib.seycon.ng.comu.DadaUsuari sourceVO,
        es.caib.seycon.ng.model.DadaUsuariEntity targetEntity,
        boolean copyIfNull)
    {
        // @todo verify behavior of dadaUsuariToEntity
        super.dadaUsuariToEntity(sourceVO, targetEntity, copyIfNull);
        dadaUsuariToEntityCustom(sourceVO, targetEntity);
    }

    public void create(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof DadaUsuariEntity) {
				DadaUsuariEntity entity = (DadaUsuariEntity) obj;
				this.create(entity); // cridem al mètode 1 per 1
			}
		}
	}

	public void update(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof DadaUsuariEntity) {
				DadaUsuariEntity entity = (DadaUsuariEntity) obj;
				this.update(entity);// cridem al mètode 1 per 1
			}
		}
	}

	public void remove(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof DadaUsuariEntity) {
				DadaUsuariEntity entity = (DadaUsuariEntity) obj;
				this.remove(entity);// cridem al mètode 1 per 1
			}
		}
	}

}