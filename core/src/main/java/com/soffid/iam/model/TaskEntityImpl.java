//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import com.soffid.iam.sync.engine.TaskHandler;

/**
 * Entity TaskEntity implementation
 */
public class TaskEntityImpl extends com.soffid.iam.model.TaskEntity {
    public String toString() {
        String result = getTransaction();
        String transactionCode = getTransaction();

        if (transactionCode.equals(TaskHandler.UPDATE_USER) || transactionCode.equals(TaskHandler.UPDATE_USER_PASSWORD)
                || transactionCode.equals(TaskHandler.PROPAGATE_PASSWORD)
                || transactionCode.equals(TaskHandler.UPDATE_PROPAGATED_PASSWORD)
                || transactionCode.equals(TaskHandler.UPDATE_ACCOUNT) 
                || transactionCode.equals(TaskHandler.UPDATE_ACCOUNT_PASSWORD) 
                || transactionCode.equals(TaskHandler.VALIDATE_PASSWORD)
                || transactionCode.equals(TaskHandler.UPDATE_USER_ALIAS)
                || transactionCode.equals(TaskHandler.EXPIRE_USER_PASSWORD)
                || transactionCode.equals(TaskHandler.EXPIRE_USER_UNTRUSTED_PASSWORD) 
                || transactionCode.equals(TaskHandler.RECONCILE_USER))
            result = result + " " + getUser(); //$NON-NLS-1$

        if (transactionCode.equals(TaskHandler.UPDATE_ACCOUNT) ||
        	transactionCode.equals(TaskHandler.UPDATE_ACCOUNT_PASSWORD)) //$NON-NLS-1$
            result = result + "@" + getSystemName(); //$NON-NLS-1$

        if (transactionCode.equals(TaskHandler.UPDATE_HOST)) //$NON-NLS-1$
            result = result + " " + getHost(); //$NON-NLS-1$

        if (transactionCode.equals(TaskHandler.UPDATE_GROUP)) //$NON-NLS-1$
            result = result + " " + getGroup(); //$NON-NLS-1$

        if (transactionCode.equals(TaskHandler.UPDATE_ROLE)  
        		|| transactionCode.equals(TaskHandler.RECONCILE_ROLE)) //$NON-NLS-1$
            result = result + " " + getRole() + " @ " + getDb(); //$NON-NLS-1$

        if (transactionCode.equals(TaskHandler.CREATE_FOLDER)) //$NON-NLS-1$
            result = result + " " + getFolder(); //$NON-NLS-1$

        if (transactionCode.equals(TaskHandler.UPDATE_OBJECT )
        				|| transactionCode.equals(TaskHandler.DELETE_OBJECT)) //$NON-NLS-1$
            result = result + " " + getEntity()+"#"+getPrimaryKeyValue(); //$NON-NLS-1$ //$NON-NLS-2$

        if (transactionCode.equals(TaskHandler.UPDATE_LIST_ALIAS)) //$NON-NLS-1$
            result = result + " " + getAlias() + "@" + getMailDomain(); //$NON-NLS-1$ //$NON-NLS-2$

        if (transactionCode.equals(TaskHandler.UPDATE_OBJECT)) //$NON-NLS-1$
            result = result + " " + getCustomObjectType() + " " + getCustomObjectName(); //$NON-NLS-1$ //$NON-NLS-2$

        return result;

    }

}
