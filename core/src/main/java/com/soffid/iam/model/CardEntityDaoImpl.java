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

import com.soffid.iam.model.CardCellEntity;
import com.soffid.iam.model.CardEntity;
import com.soffid.iam.model.UserEntity;
import es.caib.seycon.ng.exception.SeyconException;
import java.sql.CallableStatement;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * @see es.caib.seycon.ng.model.ScTarget
 */
public class CardEntityDaoImpl extends com.soffid.iam.model.CardEntityDaoBase {

    /**
     * @see es.caib.seycon.ng.model.ScTargetDao#toTargetaExtranet(es.caib.seycon.ng.model.ScTarget)
     */
    public com.soffid.iam.api.ExtranetCard toExtranetCard(final com.soffid.iam.model.CardEntity entity) {
        // @todo verify behavior of toTargetaExtranet
        return super.toExtranetCard(entity);
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private com.soffid.iam.model.CardEntity loadScTargetFromTargetaExtranet(com.soffid.iam.api.ExtranetCard targetaExtranet) {
        com.soffid.iam.model.CardEntity scTarget = null;
        if (targetaExtranet.getId() != null) {
            scTarget = this.load(targetaExtranet.getId());
        }
        if (scTarget == null) {
            scTarget = newCardEntity();
        }
        return scTarget;

    }

    /**
     * @see es.caib.seycon.ng.model.ScTargetDao#targetaExtranetToEntity(es.caib.seycon.ng.comu.TargetaExtranet)
     */
    public com.soffid.iam.model.CardEntity extranetCardToEntity(com.soffid.iam.api.ExtranetCard targetaExtranet) {
        // @todo verify behavior of targetaExtranetToEntity
        com.soffid.iam.model.CardEntity entity = this.loadScTargetFromTargetaExtranet(targetaExtranet);
        this.extranetCardToEntity(targetaExtranet, entity, true);
        return entity;
    }

    /**
     * @see es.caib.seycon.ng.model.ScTargetDao#targetaExtranetToEntity(es.caib.seycon.ng.comu.TargetaExtranet,
     *      es.caib.seycon.ng.model.ScTarget)
     */
    public void extranetCardToEntity(com.soffid.iam.api.ExtranetCard source, com.soffid.iam.model.CardEntity target, boolean copyIfNull) {
        // @todo verify behavior of targetaExtranetToEntity
        super.extranetCardToEntity(source, target, copyIfNull);
        // No conversion for target.dataEmissio (can't convert
        // source.getDataEmissio():java.util.Date to java.util.Date
        // No conversion for target.dataCaducitat (can't convert
        // source.getDataCaducitat():java.util.Date to java.util.Date
        targetaExtranetToEntityCustom(source, target);
    }

    private void targetaExtranetToEntityCustom(com.soffid.iam.api.ExtranetCard source, com.soffid.iam.model.CardEntity target) {

    }

    /**
     * @see es.caib.seycon.ng.model.ScTargetDao#toTargetaExtranet(es.caib.seycon.ng.model.ScTarget,
     *      es.caib.seycon.ng.comu.TargetaExtranet)
     */
    public void toExtranetCard(com.soffid.iam.model.CardEntity source, com.soffid.iam.api.ExtranetCard target) {
        // @todo verify behavior of toTargetaExtranet
        super.toExtranetCard(source, target);
        // WARNING! No conversion for target.dataEmissio (can't convert
        // source.getDataEmissio():java.util.Date to java.util.Date
        // WARNING! No conversion for target.dataCaducitat (can't convert
        // source.getDataCaducitat():java.util.Date to java.util.Date
        // WARNING! No conversion for target.contingut (can't convert
        // source.getContingut():es.caib.seycon.ng.model.ScContar to
        // es.caib.seycon.ng.comu.ContigutTargetaExtranet
        toTargetaExtranetCustom(source, target);
    }

    private void toTargetaExtranetCustom(com.soffid.iam.model.CardEntity source, com.soffid.iam.api.ExtranetCard target) {

        /*
         * if ("S".equals(source.getActiu())) target.setActiu(new Boolean
         * (true)); else //P es considera con NO activa target.setActiu(new
         * Boolean(false));
         */

        if (source.getIssueDate() != null) {
            Calendar dataEmissio = Calendar.getInstance();
            dataEmissio.setTime(source.getIssueDate());
            target.setOutputDate(dataEmissio);
        }
        if (source.getExpirationDate() != null) {
            Calendar dataCaducitat = Calendar.getInstance();
            dataCaducitat.setTime(source.getExpirationDate());
            target.setExpirationDate(dataCaducitat);
        }

        if (source.getUser() != null)
            target.setUserCode(source.getUser().getUserName());

        if (source.getContent() != null) {
            target.setContent(getCardCellEntityDao().toExtranetCardContentList(source.getContent()));
        }

    }

    public CardEntity handleCreateExtranetCard(String codiUsuari) {
        UserEntity usuari = getUserEntityDao().findByUserName(codiUsuari);
        if (usuari == null || usuari.getId() == null)
            throw new SeyconException(String.format(Messages.getString("CardEntityDaoImpl.userNotFound"), //$NON-NLS-1$
                    codiUsuari));

        try {
            Long idUsuari = usuari.getId();

            // Busca la última tarjeta activa
            List<CardEntity> list = query("select t "
            		+ "from com.soffid.iam.model.CardEntity as t "
            		+ "join on t.user at u with u.userName = :codiUsuari "
            		+ "where t.active in (\'S\', \'N\') "
            		+ "order by t.expirationDate desc", 
            		new Parameter[]{new Parameter("codiUsuari", codiUsuari)}); //$NON-NLS-1$
            Date date;
            String status;
            if (list.isEmpty()) {
                date = new Date();
                status = "P"; //$NON-NLS-1$
            } else {
                date = list.get(0).getExpirationDate();
                status = "S"; //$NON-NLS-1$
            }
            CardEntity targeta = newCardEntity();
            targeta.setActive(status);
            targeta.setIssueDate(date);
            Calendar caducitat = Calendar.getInstance();
            caducitat.setTime(date);
            caducitat.add(Calendar.YEAR, 1);
            targeta.setExpirationDate(caducitat.getTime());
            targeta.setCode(codiUsuari);
            create(targeta);
            String codi = targeta.getId().toString();
            while (codi.length() < 10)
                codi = "0" + codi; //$NON-NLS-1$
            targeta.setCode(codi);
            update(targeta);

            generateContents(targeta);

            return targeta;

        } catch (org.hibernate.HibernateException ex) {
            throw convertHibernateAccessException(ex);
        }
    }

    private void generateContents(CardEntity targeta) {
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 10; j++) {
                char ch1 = (char) generateChar();
                char ch2 = (char) generateChar();
                CardCellEntity cell = getCardCellEntityDao().newCardCellEntity();
                cell.setExpirationDate(new Date());
                StringBuffer filcol = new StringBuffer();
                filcol.append((char) ('A' + j));
                filcol.append(i);
                cell.setCell(filcol.toString());
                cell.setValue("" + ch1 + ch2);
                cell.setExpirationDate(new Date());
                cell.setCard(targeta);
                getCardCellEntityDao().create(cell);
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
