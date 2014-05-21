// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import java.sql.CallableStatement;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import es.caib.seycon.ng.exception.SeyconException;

/**
 * @see es.caib.seycon.ng.model.ScTarget
 */
public class ScTargetDaoImpl extends es.caib.seycon.ng.model.ScTargetDaoBase {

    /**
     * @see es.caib.seycon.ng.model.ScTargetDao#toTargetaExtranet(es.caib.seycon.ng.model.ScTarget)
     */
    public es.caib.seycon.ng.comu.TargetaExtranet toTargetaExtranet(
            final es.caib.seycon.ng.model.ScTarget entity) {
        // @todo verify behavior of toTargetaExtranet
        return super.toTargetaExtranet(entity);
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private es.caib.seycon.ng.model.ScTarget loadScTargetFromTargetaExtranet(
            es.caib.seycon.ng.comu.TargetaExtranet targetaExtranet) {
        es.caib.seycon.ng.model.ScTarget scTarget = null;
        if (targetaExtranet.getId() != null) {
            scTarget = this.load(targetaExtranet.getId());
        }
        if (scTarget == null) {
            scTarget = newScTarget();
        }
        return scTarget;

    }

    /**
     * @see es.caib.seycon.ng.model.ScTargetDao#targetaExtranetToEntity(es.caib.seycon.ng.comu.TargetaExtranet)
     */
    public es.caib.seycon.ng.model.ScTarget targetaExtranetToEntity(
            es.caib.seycon.ng.comu.TargetaExtranet targetaExtranet) {
        // @todo verify behavior of targetaExtranetToEntity
        es.caib.seycon.ng.model.ScTarget entity = this
                .loadScTargetFromTargetaExtranet(targetaExtranet);
        this.targetaExtranetToEntity(targetaExtranet, entity, true);
        return entity;
    }

    /**
     * @see es.caib.seycon.ng.model.ScTargetDao#targetaExtranetToEntity(es.caib.seycon.ng.comu.TargetaExtranet,
     *      es.caib.seycon.ng.model.ScTarget)
     */
    public void targetaExtranetToEntity(es.caib.seycon.ng.comu.TargetaExtranet source,
            es.caib.seycon.ng.model.ScTarget target, boolean copyIfNull) {
        // @todo verify behavior of targetaExtranetToEntity
        super.targetaExtranetToEntity(source, target, copyIfNull);
        // No conversion for target.dataEmissio (can't convert
        // source.getDataEmissio():java.util.Date to java.util.Date
        // No conversion for target.dataCaducitat (can't convert
        // source.getDataCaducitat():java.util.Date to java.util.Date
        targetaExtranetToEntityCustom(source, target);
    }

    private void targetaExtranetToEntityCustom(es.caib.seycon.ng.comu.TargetaExtranet source,
            es.caib.seycon.ng.model.ScTarget target) {

    }

    /**
     * @see es.caib.seycon.ng.model.ScTargetDao#toTargetaExtranet(es.caib.seycon.ng.model.ScTarget,
     *      es.caib.seycon.ng.comu.TargetaExtranet)
     */
    public void toTargetaExtranet(es.caib.seycon.ng.model.ScTarget source,
            es.caib.seycon.ng.comu.TargetaExtranet target) {
        // @todo verify behavior of toTargetaExtranet
        super.toTargetaExtranet(source, target);
        // WARNING! No conversion for target.dataEmissio (can't convert
        // source.getDataEmissio():java.util.Date to java.util.Date
        // WARNING! No conversion for target.dataCaducitat (can't convert
        // source.getDataCaducitat():java.util.Date to java.util.Date
        // WARNING! No conversion for target.contingut (can't convert
        // source.getContingut():es.caib.seycon.ng.model.ScContar to
        // es.caib.seycon.ng.comu.ContigutTargetaExtranet
        toTargetaExtranetCustom(source, target);
    }

    private void toTargetaExtranetCustom(es.caib.seycon.ng.model.ScTarget source,
            es.caib.seycon.ng.comu.TargetaExtranet target) {

        /*
         * if ("S".equals(source.getActiu())) target.setActiu(new Boolean
         * (true)); else //P es considera con NO activa target.setActiu(new
         * Boolean(false));
         */

        if (source.getDataEmissio() != null) {
            Calendar dataEmissio = Calendar.getInstance();
            dataEmissio.setTime(source.getDataEmissio());
            target.setDataEmissio(dataEmissio);
        }
        if (source.getDataCaducitat() != null) {
            Calendar dataCaducitat = Calendar.getInstance();
            dataCaducitat.setTime(source.getDataCaducitat());
            target.setDataCaducitat(dataCaducitat);
        }

        if (source.getUsuari() != null)
            target.setCodiUsuari(source.getUsuari().getCodi());

        if (source.getContingut() != null) {
            target.setContingut(getScContarDao().toContingutTargetaExtranetList(
                    source.getContingut()));
        }

    }

    public ScTarget creaTargetaExtranet(String codiUsuari) {
        UsuariEntity usuari = getUsuariEntityDao().findByCodi(codiUsuari);
        if (usuari == null || usuari.getId() == null)
            throw new SeyconException(String.format(Messages.getString("ScTargetDaoImpl.userNotFound"), //$NON-NLS-1$
                    codiUsuari));

        try {
            Long idUsuari = usuari.getId();

            // Busca la última tarjeta activa
            List<ScTarget> list = query("select t from es.caib.seycon.ng.ScTarget as t " //$NON-NLS-1$
                    + "join on t.usuari at u with u.codi = :codiUsuari " //$NON-NLS-1$
                    + "where t.actiu in ('S', 'N') " + "order by t.dataCaducitat desc", //$NON-NLS-1$ //$NON-NLS-2$
                    new Parameter[] { new Parameter("codiUsuari", codiUsuari) }); //$NON-NLS-1$
            Date date;
            String status;
            if (list.isEmpty()) {
                date = new Date();
                status = "P"; //$NON-NLS-1$
            } else {
                date = list.get(0).getDataCaducitat();
                status = "S"; //$NON-NLS-1$
            }
            ScTarget targeta = newScTarget();
            targeta.setActiu(status);
            targeta.setDataEmissio(date);
            Calendar caducitat = Calendar.getInstance();
            caducitat.setTime(date);
            caducitat.add(Calendar.YEAR, 1);
            targeta.setDataCaducitat(caducitat.getTime());
            targeta.setCodi(codiUsuari);
            create(targeta);
            String codi = targeta.getId().toString();
            while (codi.length() < 10)
                codi = "0" + codi; //$NON-NLS-1$
            targeta.setCodi(codi);
            update(targeta);

            generateContents(targeta);

            return targeta;

        } catch (org.hibernate.HibernateException ex) {
            throw convertHibernateAccessException(ex);
        }
    }

    private void generateContents(ScTarget targeta) {
        for (int i = 0; i <= 9; i++)
        {
            for (int j = 0; j <= 10; j++) 
            {
                char ch1 = (char) generateChar();
                char ch2 = (char) generateChar();
                
                ScContar cell = getScContarDao().newScContar();
                cell.setDadaUs(new Date());
                
                StringBuffer filcol = new StringBuffer();
                filcol.append((char)( 'A' + j));
                filcol.append(i);
                cell.setFilcol(filcol.toString());
                cell.setValor(""+ch1+ch2); //$NON-NLS-1$
                cell.setDadaUs(new Date());
                cell.setTargeta(targeta);
                getScContarDao().create(cell);
             }
        }
    }

    static Random r = new Random(System.currentTimeMillis());

    private int generateChar() {
        synchronized (r) {
            int i = r.nextInt(61);
            if (i < 26)
                return 'a' + i;
            else if (i < 52)
                return 'A' + i - 26;
            else
                return '1' + i - 52;
        }
    }

}
