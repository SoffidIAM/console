// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.GregorianCalendar;

import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.utils.Security;

/**
 * @see es.caib.seycon.ng.model.PoliticaContrasenyaEntity
 */
public class PoliticaContrasenyaEntityDaoImpl
    extends es.caib.seycon.ng.model.PoliticaContrasenyaEntityDaoBase
{

    private void audit(String accio, PoliticaContrasenyaEntity politica) {
        String codiUsuari = Security.getCurrentAccount();
        Auditoria auditoria = new Auditoria();
        auditoria.setAccio(accio);
        auditoria.setPasswordDomain(politica.getDominiContrasenya().getCodi());
        auditoria.setUserType(politica.getTipusUsuariDomini().getCodi());
        auditoria.setAutor(codiUsuari);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
        auditoria.setData(dateFormat.format(GregorianCalendar.getInstance()
                .getTime()));
        auditoria.setObjecte("SC_POCODO"); //$NON-NLS-1$
        AuditoriaEntity auditoriaEntity = getAuditoriaEntityDao()
                .auditoriaToEntity(auditoria);
        getAuditoriaEntityDao().create(auditoriaEntity);
    }


    @Override
	public void create (PoliticaContrasenyaEntity entity)
	{
		super.create(entity);
		audit("C", entity); //$NON-NLS-1$
	}


	@Override
	public void update (PoliticaContrasenyaEntity entity)
	{
		super.update(entity);
		audit("U", entity); //$NON-NLS-1$
	}


	@Override
	public void remove (PoliticaContrasenyaEntity entity)
	{
		audit("D", entity); //$NON-NLS-1$
		super.remove(entity);
	}


	/**
     * @see es.caib.seycon.ng.model.PoliticaContrasenyaEntityDao#toPoliticaContrasenya(es.caib.seycon.ng.model.PoliticaContrasenyaEntity, es.caib.seycon.ng.comu.PoliticaContrasenya)
     */
    public void toPoliticaContrasenya(
        es.caib.seycon.ng.model.PoliticaContrasenyaEntity source,
        es.caib.seycon.ng.comu.PoliticaContrasenya target)
    {
        // @todo verify behavior of toPoliticaContrasenya
        super.toPoliticaContrasenya(source, target);
        target.setAllowPasswordChange(source.getAllowPasswordChange() == null ? true: source.getAllowPasswordChange().booleanValue());
        target.setAllowPasswordQuery(source.getAllowPasswordQuery() == null ? true: source.getAllowPasswordQuery().booleanValue());
        // Fem les conversions 
        if (source.getTipusUsuariDomini()!=null) {
        	TipusUsuariEntity tipusUsuariE = source.getTipusUsuariDomini();
        	target.setTipusUsuari(tipusUsuariE.getCodi());
        	target.setDecripcioTipusUsuari(tipusUsuariE.getDescripcio());
        }
        if (source.getDominiContrasenya() != null)
        {
        	target.setCodiDominiContrasenya(source.getDominiContrasenya().getCodi());
        }
    }


    /**
     * @see es.caib.seycon.ng.model.PoliticaContrasenyaEntityDao#toPoliticaContrasenya(es.caib.seycon.ng.model.PoliticaContrasenyaEntity)
     */
    public es.caib.seycon.ng.comu.PoliticaContrasenya toPoliticaContrasenya(final es.caib.seycon.ng.model.PoliticaContrasenyaEntity entity)
    {
        // @todo verify behavior of toPoliticaContrasenya
        return super.toPoliticaContrasenya(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private es.caib.seycon.ng.model.PoliticaContrasenyaEntity loadPoliticaContrasenyaEntityFromPoliticaContrasenya(es.caib.seycon.ng.comu.PoliticaContrasenya politicaContrasenyaDomini)
    {
        es.caib.seycon.ng.model.PoliticaContrasenyaEntity politicaContrasenyaDominiEntity = null;
        if (politicaContrasenyaDomini.getId()!=null) {
        	politicaContrasenyaDominiEntity = this.load(politicaContrasenyaDomini.getId());
        }
        if (politicaContrasenyaDominiEntity == null)
        {
            politicaContrasenyaDominiEntity = newPoliticaContrasenyaEntity();
        }
        return politicaContrasenyaDominiEntity;
    }

    
    /**
     * @see es.caib.seycon.ng.model.PoliticaContrasenyaEntityDao#politicaContrasenyaDominiToEntity(es.caib.seycon.ng.comu.PoliticaContrasenya)
     */
    public es.caib.seycon.ng.model.PoliticaContrasenyaEntity politicaContrasenyaToEntity(es.caib.seycon.ng.comu.PoliticaContrasenya politicaContrasenyaDomini)
    {
        // @todo verify behavior of politicaContrasenyaDominiToEntity
        es.caib.seycon.ng.model.PoliticaContrasenyaEntity entity = this.loadPoliticaContrasenyaEntityFromPoliticaContrasenya(politicaContrasenyaDomini);
        this.politicaContrasenyaToEntity(politicaContrasenyaDomini, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.PoliticaContrasenyaEntityDao#politicaContrasenyaToEntity(es.caib.seycon.ng.comu.PoliticaContrasenya, es.caib.seycon.ng.model.PoliticaContrasenyaEntity)
     */
    public void politicaContrasenyaToEntity(
        es.caib.seycon.ng.comu.PoliticaContrasenya source,
        es.caib.seycon.ng.model.PoliticaContrasenyaEntity target,
        boolean copyIfNull)
    {
        // @todo verify behavior of politicaContrasenyaToEntity
        super.politicaContrasenyaToEntity(source, target, copyIfNull);
        
        target.setAllowPasswordChange(new Boolean(source.isAllowPasswordChange()));
        target.setAllowPasswordQuery(new Boolean(source.isAllowPasswordQuery()));
        // Fem els canvis necessaris
        // Tipus d'usuari
        if (source.getTipusUsuari() !=null) {
        	String codiTipusUsuari = source.getTipusUsuari();
        	TipusUsuariEntity tipusUsuE = getTipusUsuariEntityDao().findByCodi(codiTipusUsuari);
        	if (tipusUsuE!=null) {
        		target.setTipusUsuariDomini(tipusUsuE);
        	} 
        }
        
        // Domini de contrasenya i d'usuaris
        if (source.getCodiDominiContrasenya()!=null) {
        	String codiDominiContrasenya = source.getCodiDominiContrasenya();
        	DominiContrasenyaEntity dominiContraE = getDominiContrasenyaEntityDao().findByCodi(codiDominiContrasenya);
       		target.setDominiContrasenya(dominiContraE);
        }
        
        // Segons el tipus de contrasenya anulem el valor de certs atributs
        // (A)utomàtica i (M)anual
        if ("A".equals(source.getTipus())) { //$NON-NLS-1$
        	target.setDuradaMaxima(null);
        	target.setDuradaMaximaCaducada(null);
        } else if ("M".equals(source.getTipus())) { //$NON-NLS-1$
        	target.setTempsRenovacio(null);
        }
        
        
        
    }

}