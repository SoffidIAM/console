package com.soffid.iam.sync.engine;

import com.soffid.iam.api.Group;
import com.soffid.iam.api.RoleGrant;
import com.soffid.iam.api.Task;
import com.soffid.iam.api.User;
import com.soffid.iam.sync.intf.ExtensibleObject;

import es.caib.seycon.ng.comu.Password;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.util.Base64;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class TaskHandler
{
	Task task;
	Date timeout;
	boolean validated;
	boolean cancelled = false;
	List<TaskHandlerLog> logs;
	boolean offlineTask;
	boolean changed = false;
	String tenant;
	Long tenantId;
	ExtensibleObject result;
	
	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	public boolean isChanged ()
	{
		return changed;
	}

	public void setChanged (boolean changed)
	{
		this.changed = changed;
	}

	public boolean isOfflineTask ()
	{
		return offlineTask;
	}

	public void setOfflineTask (boolean offlineTask)
	{
		this.offlineTask = offlineTask;
	}

	User usuari;
	Group grup;
	Collection<RoleGrant> grants;

	private final long ONE_DAY = 24 * 60 * 60 * 1000;
	private boolean rejected;

	public static final String PROPAGATE_PASSWORD = "PropagatePassword"; //$NON-NLS-1$
	public static final String UPDATE_PROPAGATED_PASSWORD = "UpdatePropagatedPassword"; //$NON-NLS-1$
	public static final String UPDATE_USER_PASSWORD = "UpdateUserPassword"; //$NON-NLS-1$
	public static final String UPDATE_USER = "UpdateUser"; //$NON-NLS-1$
	public static final String VALIDATE_PASSWORD = "ValidatePassword"; //$NON-NLS-1$
	public static final String CREATE_FOLDER = "CreateFolder"; //$NON-NLS-1$
	public static final String UPDATE_ROLE = "UpdateRole"; //$NON-NLS-1$
	public static final String UPDATE_HOST = "UpdateHost"; //$NON-NLS-1$
	public static final String PURGE_HOSTS = "PurgeHosts"; //$NON-NLS-1$
	public static final String UPDATE_PRINTER = "UpdatePrinter"; //$NON-NLS-1$
	public static final String GET_LOG = "GetLog"; //$NON-NLS-1$
	public static final String UPDATE_NETWORKS = "UpdateNetworks"; //$NON-NLS-1$
	public static final String UPDATE_USER_ALIAS = "UpdateUserAlias"; //$NON-NLS-1$
	public static final String UPDATE_LIST_ALIAS = "UpdateListAlias"; //$NON-NLS-1$
	public static final String UPDATE_GROUP = "UpdateGroup"; //$NON-NLS-1$
	public static final String EXPIRE_USER_UNTRUSTED_PASSWORD = "ExpireUserUntrustedPassword"; //$NON-NLS-1$
	public static final String EXPIRE_USER_PASSWORD = "ExpireUserPassword"; //$NON-NLS-1$
	public static final String UPDATE_PROPAGATED_PASSWORD_SINCRONO = "UpdatePropagatedPassowrdSincrono"; //$NON-NLS-1$
	public static final String UPDATE_IBSALUT_PASSWORD = "UpdateIbsalutPassword"; //$NON-NLS-1$
	public static final String UPDATE_ACESS_CONTROL = "UpdateAccessControl"; //$NON-NLS-1$
	public static final String END_RECONCILE = "EndReconcile"; //$NON-NLS-1$
	public static final String RECONCILE_USERS = "ReconcileUsers"; //$NON-NLS-1$
	public static final String RECONCILE_USER = "ReconcileUser"; //$NON-NLS-1$
	public static final String FINISH_RECONCILE_USER = "FinishReconcileUser"; //$NON-NLS-1$
	public static final String RECONCILE_ROLE = "ReconcileRole"; //$NON-NLS-1$
	public static final String RECONCILE_ROLES = "ReconcileRoles"; //$NON-NLS-1$
    public static final String UPDATE_ACCOUNT ="UpdateAccount"; //$NON-NLS-1$
    public static final String UPDATE_ACCOUNT_PASSWORD ="UpdateAccountPassword"; //$NON-NLS-1$
    public static final String VALIDATE_ACCOUNT_PASSWORD = "ValidateAccountPassword"; //$NON-NLS-1$
    public static final String PROPAGATE_ACCOUNT_PASSWORD = "PropagateAccountPassword"; //$NON-NLS-1$
    public static final String NOTIFY_PASSWORD_CHANGE = "NotifyChangePassword"; //$NON-NLS-1$
    public static final String UPDATE_OBJECT = "UpdateObject"; //$NON-NLS-1$
    public static final String DELETE_OBJECT = "DeleteObject"; //$NON-NLS-1$
    
	public Group getGroup() {
		return grup;
	}

	public void setGroup(Group grup) {
		this.grup = grup;
	}

	public User getUser() {
		return usuari;
	}

	public void setUser(User usuari) {
		this.usuari = usuari;
	}

	public Collection<RoleGrant> getGrants() {
		return grants;
	}

	public void setGrants(Collection<RoleGrant> grants) {
		this.grants = grants;
	}

	public List<TaskHandlerLog> getLogs ()
	{
		return logs;
	}

	public void setLogs (List<TaskHandlerLog> logs)
	{
		this.logs = logs;
	}

	public TaskHandlerLog getLog (int i)
	{
		if (logs.size() <= i)
			return null;
		else
			return logs.get(i);
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Date getTimeout ()
	{
		return timeout;
	}

	public void setTimeout (Date timeout)
	{
		this.timeout = timeout;
	}

	public boolean isValidated ()
	{
		return validated;
	}

	public void setValidated (boolean validated)
	{
		this.validated = validated;
	}

	public Password getPassword ()
	{
		if (task == null || task.getPassword() == null)
		{
			return null;
		}
		else
		{
			String p = task.getPassword();
			if (p.startsWith("X ")) { //$NON-NLS-1$
				return Password.decode(p.substring(2));
			}
			else
			{
				return Password.decode(p);
			}
		}
	}

	public int getPriority ()
	{
		String transactionCode = task.getTransaction();

		if (transactionCode.equals(VALIDATE_PASSWORD)
				|| transactionCode.equals(UPDATE_USER_PASSWORD)
				|| transactionCode.equals(UPDATE_PROPAGATED_PASSWORD)
				|| transactionCode.equals(PROPAGATE_PASSWORD)
				|| transactionCode.equals(UPDATE_PROPAGATED_PASSWORD_SINCRONO)
				|| transactionCode.equals(UPDATE_ACESS_CONTROL))
		{
			return 0;
		}

		else if (transactionCode.equals(EXPIRE_USER_PASSWORD) || transactionCode.equals(EXPIRE_USER_UNTRUSTED_PASSWORD) || transactionCode.equals(RECONCILE_ROLE) || transactionCode.equals(RECONCILE_ROLES) || transactionCode.equals(RECONCILE_USERS) || transactionCode.equals(RECONCILE_USER) || transactionCode.equals(END_RECONCILE) || transactionCode.equals(UPDATE_OBJECT) || (System.currentTimeMillis() - task.getTaskDate().getTime().getTime() > ONE_DAY))
		{
			return 2;
		}

		else
		{
			return 1;
		}
	}

	@Override
	public String toString ()
	{
		String transactionCode = task.getTransaction();
		StringBuffer result = new StringBuffer(transactionCode);

		if (transactionCode.equals(UPDATE_USER)
				|| transactionCode.equals(UPDATE_ACCOUNT)
				|| transactionCode.equals(UPDATE_USER_PASSWORD)
				|| transactionCode.equals(UPDATE_ACCOUNT_PASSWORD)
				|| transactionCode.equals(PROPAGATE_PASSWORD)
				|| transactionCode.equals(UPDATE_PROPAGATED_PASSWORD)
				|| transactionCode.equals(VALIDATE_PASSWORD)
				|| transactionCode.equals(VALIDATE_ACCOUNT_PASSWORD)
				|| transactionCode.equals(UPDATE_USER_ALIAS)
				|| transactionCode.equals(EXPIRE_USER_PASSWORD)
				|| transactionCode.equals(EXPIRE_USER_UNTRUSTED_PASSWORD))
		{
			result.append(" "); //$NON-NLS-1$
			result.append(task.getUser());
		}

		if (transactionCode.equals(UPDATE_ACCOUNT)
				|| transactionCode.equals(UPDATE_ACCOUNT_PASSWORD)
				|| transactionCode.equals(VALIDATE_ACCOUNT_PASSWORD)
				|| transactionCode.equals(NOTIFY_PASSWORD_CHANGE))
		{
			result.append(" "); //$NON-NLS-1$
			result.append(task.getSystemName());
		}

		if (transactionCode.equals("UpdateHost")) { //$NON-NLS-1$
			result.append(" "); //$NON-NLS-1$
			result.append(task.getHost());
		}

		if (transactionCode.equals("UpdateGroup")) { //$NON-NLS-1$
			result.append(" "); //$NON-NLS-1$
			result.append(task.getGroup());
		}

		if (transactionCode.equals("UpdateRole")) { //$NON-NLS-1$
			result.append(" "); //$NON-NLS-1$
			result.append(task.getRole());
			result.append("@"); //$NON-NLS-1$
			result.append(task.getDatabase());
		}

		if (transactionCode.equals("CreateFolder")) { //$NON-NLS-1$
			result.append(" "); //$NON-NLS-1$
			result.append(task.getFolder());
		}

		if (transactionCode.equals("UpdateListAlias")) { //$NON-NLS-1$
			result.append(" "); //$NON-NLS-1$
			result.append(task.getAlias());
			result.append("@"); //$NON-NLS-1$
			result.append(task.getMailDomain());
		}

		if (transactionCode.equals(RECONCILE_ROLE))
		{
			result.append(" "); //$NON-NLS-1$
			result.append(task.getRole());
			result.append("@"); //$NON-NLS-1$
			result.append(task.getSystemName());
		}

		if (transactionCode.equals(RECONCILE_USER))
		{
			result.append(" "); //$NON-NLS-1$
			result.append(task.getUser());
			result.append("/"); //$NON-NLS-1$
			result.append(task.getHost());
		}
		
		if (transactionCode.equals(RECONCILE_USERS))
		{
			result.append(" "); //$NON-NLS-1$
			result.append(task.getHost());
		}

		if (transactionCode.equals(END_RECONCILE))
		{
			result.append(" "); //$NON-NLS-1$
			result.append(task.getHost());
		}

		if (transactionCode.equals(RECONCILE_ROLES))
		{
			result.append(" "); //$NON-NLS-1$
			result.append(task.getHost());
		}

		if (transactionCode.equals(UPDATE_OBJECT) || 
						transactionCode.equals(DELETE_OBJECT))
		{
			result.append(" ") //$NON-NLS-1$
				.append(task.getEntity())
				.append ("#") //$NON-NLS-1$
				.append(task.getPrimaryKeyValue());
		}

		return result.toString();
	}

	/**
	 * Remove conflictive tasks.
	 * 
	 * @throws SQLException
	 * @throws InternalErrorException
	 * @throws UnknownHostException
	 */
	public String getHash () throws InternalErrorException
	{
		String transactionCode = task.getTransaction();
		String hash;

		// Generar hash
		if (EXPIRE_USER_PASSWORD.equals(transactionCode)
				|| EXPIRE_USER_UNTRUSTED_PASSWORD.equals(transactionCode)
				|| UPDATE_USER_PASSWORD.equals(transactionCode)
				|| UPDATE_PROPAGATED_PASSWORD.equals(transactionCode))
			hash = "P " + task.getUser() + "@" + task.getPasswordDomain(); //$NON-NLS-1$ //$NON-NLS-2$
		else if (UPDATE_ACCOUNT_PASSWORD.equals(transactionCode))
			hash = "PA " + task.getUser() + "@" + task.getSystemName(); //$NON-NLS-1$ //$NON-NLS-2$
		else if (CREATE_FOLDER.equals(transactionCode))
			hash = "F " + task.getFolderType() + " " + task.getFolder(); //$NON-NLS-1$ //$NON-NLS-2$
		else if (PURGE_HOSTS.equals(transactionCode))
			hash = "PH"; //$NON-NLS-1$
		else if (UPDATE_GROUP.equals(transactionCode))
			hash = "G " + task.getGroup(); //$NON-NLS-1$
		else if (UPDATE_HOST.equals(transactionCode))
			hash = "H " + task.getHost(); //$NON-NLS-1$
		else if (UPDATE_LIST_ALIAS.equals(transactionCode))
			hash = "L " + task.getAlias() + " " + (task.getMailDomain() != null ? task.getMailDomain() : "null"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		else if (UPDATE_NETWORKS.equals(transactionCode))
			hash = "UN"; //$NON-NLS-1$
		else if (UPDATE_ROLE.equals(transactionCode))
			hash = "R " + task.getRole() + " " + task.getDatabase(); //$NON-NLS-1$ //$NON-NLS-2$
		else if (UPDATE_ACCOUNT.equals(transactionCode))
			hash = "UA " + task.getUser() + "@" + task.getSystemName(); //$NON-NLS-1$ //$NON-NLS-2$
		else if (UPDATE_USER.equals(transactionCode))
			hash = "U " + task.getUser(); //$NON-NLS-1$ //$NON-NLS-2$
		else if (UPDATE_USER_ALIAS.equals(transactionCode))
			hash = "A " + task.getUser(); //$NON-NLS-1$
		else if (RECONCILE_USER.equals(transactionCode))
			hash = "RC_U " + task.getUser() + "/" + task.getHost(); //$NON-NLS-1$ //$NON-NLS-2$
		else if (RECONCILE_ROLE.equals(transactionCode))
			hash = "RC_R " + task.getRole() + "/" + task.getDatabase(); //$NON-NLS-1$ //$NON-NLS-2$
		else if (RECONCILE_USERS.equals(transactionCode))
			hash = "RC_U " + task.getHost(); //$NON-NLS-1$
		else if (END_RECONCILE.equals(transactionCode))
			hash = "RC_E " + task.getHost(); //$NON-NLS-1$
		else if (RECONCILE_ROLES.equals(transactionCode))
			hash = "RC_R " + task.getHost(); //$NON-NLS-1$
		else if (UPDATE_OBJECT.equals(transactionCode))
			hash = "UO " + task.getPrimaryKeyValue(); //$NON-NLS-1$
		else if (DELETE_OBJECT.equals(transactionCode))
			hash = "UO " + task.getPrimaryKeyValue(); //$NON-NLS-1$
		else
			hash = Long.toString(task.getId());

		hash = tenant + ":"+ hash;
		if (hash.length() > 200)
		{
			try
			{
				MessageDigest digest = MessageDigest.getInstance("SHA-1"); //$NON-NLS-1$
				byte bytes[] = digest.digest(hash.getBytes("UTF-8")); //$NON-NLS-1$
				hash = Base64.encodeBytes(bytes);
			}
			catch (NoSuchAlgorithmException e)
			{
				throw new InternalErrorException(e.toString());
			}
			catch (UnsupportedEncodingException e)
			{
				throw new InternalErrorException(e.toString());
			}
		}
		return hash;
	}

	public boolean isComplete ()
	{
		if (cancelled || isExpired())
			return true;
		else
			return !"P".equals(task.getStatus()) && !"E".equals(task.getStatus()); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public boolean isExpired ()
	{
		if (timeout == null)
			return false;
		else
			return timeout.before(new Date());
	}

	public void cancel ()
	{
		cancelled = true;
		changed = true;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public void reject() {
		rejected = true;
		changed = true;
	}

	public boolean isRejected() {
		return rejected;
	}

	public ExtensibleObject getResult() {
		return result;
	}

	public void setResult(ExtensibleObject result) {
		this.result = result;
	}
}
