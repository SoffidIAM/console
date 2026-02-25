//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.iga.service.MailService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.iga.service.MailService
 */
public abstract class MailServiceBase
	implements com.soffid.iam.iga.service.MailService
 {
	private com.soffid.iam.iga.service.ApplicationService applicationService;

	/**
	 * Sets reference to <code>applicationService</code>.
	 */
	public void setApplicationService (com.soffid.iam.iga.service.ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	/**
	 * Gets reference to <code>applicationService</code>.
	 */
	public com.soffid.iam.iga.service.ApplicationService getApplicationService () {
		return applicationService;
	}

	private com.soffid.iam.base.service.AuthorizationService authorizationService;

	/**
	 * Sets reference to <code>authorizationService</code>.
	 */
	public void setAuthorizationService (com.soffid.iam.base.service.AuthorizationService authorizationService) {
		this.authorizationService = authorizationService;
	}

	/**
	 * Gets reference to <code>authorizationService</code>.
	 */
	public com.soffid.iam.base.service.AuthorizationService getAuthorizationService () {
		return authorizationService;
	}

	private com.soffid.iam.base.service.ConfigurationService configurationService;

	/**
	 * Sets reference to <code>configurationService</code>.
	 */
	public void setConfigurationService (com.soffid.iam.base.service.ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	/**
	 * Gets reference to <code>configurationService</code>.
	 */
	public com.soffid.iam.base.service.ConfigurationService getConfigurationService () {
		return configurationService;
	}

	private com.soffid.iam.iga.service.GroupService groupService;

	/**
	 * Sets reference to <code>groupService</code>.
	 */
	public void setGroupService (com.soffid.iam.iga.service.GroupService groupService) {
		this.groupService = groupService;
	}

	/**
	 * Gets reference to <code>groupService</code>.
	 */
	public com.soffid.iam.iga.service.GroupService getGroupService () {
		return groupService;
	}

	private com.soffid.iam.iga.model.SystemEntityDao systemEntityDao;

	/**
	 * Sets reference to <code>systemEntityDao</code>.
	 */
	public void setSystemEntityDao (com.soffid.iam.iga.model.SystemEntityDao systemEntityDao) {
		this.systemEntityDao = systemEntityDao;
	}

	/**
	 * Gets reference to <code>systemEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.SystemEntityDao getSystemEntityDao () {
		return systemEntityDao;
	}

	private com.soffid.iam.base.service.UserService userService;

	/**
	 * Sets reference to <code>userService</code>.
	 */
	public void setUserService (com.soffid.iam.base.service.UserService userService) {
		this.userService = userService;
	}

	/**
	 * Gets reference to <code>userService</code>.
	 */
	public com.soffid.iam.base.service.UserService getUserService () {
		return userService;
	}


	/**
	 * @see com.soffid.iam.iga.service.MailService#	 * @see com.soffid.iam.iga.service.MailService#void sendHtmlMail(java.lang.String to, java.lang.String subject, java.lang.String body)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void sendHtmlMail(
		final java.lang.String to, 
		final java.lang.String subject, 
		final java.lang.String body)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (to == null || to.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendHtmlMail(java.lang.String to, java.lang.String subject, java.lang.String body) - to cannot be null");
		}
		if (subject == null || subject.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendHtmlMail(java.lang.String to, java.lang.String subject, java.lang.String body) - subject cannot be null");
		}
		if (body == null || body.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendHtmlMail(java.lang.String to, java.lang.String subject, java.lang.String body) - body cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleSendHtmlMail(to, subject, body);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailService.class).
			warn ("Error on MailService.sendHtmlMail", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailService.sendHtmlMail", (Throwable) __r[1]);
	}

	protected abstract void handleSendHtmlMail(java.lang.String to, java.lang.String subject, java.lang.String body) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailService#	 * @see com.soffid.iam.iga.service.MailService#void sendHtmlMail(java.lang.String to, java.lang.String cc, java.lang.String subject, java.lang.String body)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void sendHtmlMail(
		final java.lang.String to, 
		final java.lang.String cc, 
		final java.lang.String subject, 
		final java.lang.String body)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (to == null || to.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendHtmlMail(java.lang.String to, java.lang.String cc, java.lang.String subject, java.lang.String body) - to cannot be null");
		}
		if (cc == null || cc.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendHtmlMail(java.lang.String to, java.lang.String cc, java.lang.String subject, java.lang.String body) - cc cannot be null");
		}
		if (subject == null || subject.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendHtmlMail(java.lang.String to, java.lang.String cc, java.lang.String subject, java.lang.String body) - subject cannot be null");
		}
		if (body == null || body.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendHtmlMail(java.lang.String to, java.lang.String cc, java.lang.String subject, java.lang.String body) - body cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleSendHtmlMail(to, cc, subject, body);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailService.class).
			warn ("Error on MailService.sendHtmlMail", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailService.sendHtmlMail", (Throwable) __r[1]);
	}

	protected abstract void handleSendHtmlMail(java.lang.String to, java.lang.String cc, java.lang.String subject, java.lang.String body) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailService#	 * @see com.soffid.iam.iga.service.MailService#void sendHtmlMail(java.lang.String to, java.lang.String cc, java.lang.String subject, java.lang.String body, java.util.Collection mimeBodyParts)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void sendHtmlMail(
		final java.lang.String to, 
		final java.lang.String cc, 
		final java.lang.String subject, 
		final java.lang.String body, 
		final java.util.Collection mimeBodyParts)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (to == null || to.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendHtmlMail(java.lang.String to, java.lang.String cc, java.lang.String subject, java.lang.String body, java.util.Collection mimeBodyParts) - to cannot be null");
		}
		if (cc == null || cc.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendHtmlMail(java.lang.String to, java.lang.String cc, java.lang.String subject, java.lang.String body, java.util.Collection mimeBodyParts) - cc cannot be null");
		}
		if (subject == null || subject.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendHtmlMail(java.lang.String to, java.lang.String cc, java.lang.String subject, java.lang.String body, java.util.Collection mimeBodyParts) - subject cannot be null");
		}
		if (body == null || body.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendHtmlMail(java.lang.String to, java.lang.String cc, java.lang.String subject, java.lang.String body, java.util.Collection mimeBodyParts) - body cannot be null");
		}
		if (mimeBodyParts == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendHtmlMail(java.lang.String to, java.lang.String cc, java.lang.String subject, java.lang.String body, java.util.Collection mimeBodyParts) - mimeBodyParts cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleSendHtmlMail(to, cc, subject, body, mimeBodyParts);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailService.class).
			warn ("Error on MailService.sendHtmlMail", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailService.sendHtmlMail", (Throwable) __r[1]);
	}

	protected abstract void handleSendHtmlMail(java.lang.String to, java.lang.String cc, java.lang.String subject, java.lang.String body, java.util.Collection mimeBodyParts) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailService#	 * @see com.soffid.iam.iga.service.MailService#void sendHtmlMail(java.lang.String to, java.lang.String subject, java.lang.String body, java.util.Collection mimeBodyParts)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void sendHtmlMail(
		final java.lang.String to, 
		final java.lang.String subject, 
		final java.lang.String body, 
		final java.util.Collection mimeBodyParts)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (to == null || to.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendHtmlMail(java.lang.String to, java.lang.String subject, java.lang.String body, java.util.Collection mimeBodyParts) - to cannot be null");
		}
		if (subject == null || subject.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendHtmlMail(java.lang.String to, java.lang.String subject, java.lang.String body, java.util.Collection mimeBodyParts) - subject cannot be null");
		}
		if (body == null || body.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendHtmlMail(java.lang.String to, java.lang.String subject, java.lang.String body, java.util.Collection mimeBodyParts) - body cannot be null");
		}
		if (mimeBodyParts == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendHtmlMail(java.lang.String to, java.lang.String subject, java.lang.String body, java.util.Collection mimeBodyParts) - mimeBodyParts cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleSendHtmlMail(to, subject, body, mimeBodyParts);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailService.class).
			warn ("Error on MailService.sendHtmlMail", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailService.sendHtmlMail", (Throwable) __r[1]);
	}

	protected abstract void handleSendHtmlMail(java.lang.String to, java.lang.String subject, java.lang.String body, java.util.Collection mimeBodyParts) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailService#	 * @see com.soffid.iam.iga.service.MailService#void sendHtmlMailToActors(java.lang.String[] actors, java.lang.String subject, java.lang.String body)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void sendHtmlMailToActors(
		final java.lang.String[] actors, 
		final java.lang.String subject, 
		final java.lang.String body)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (actors == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendHtmlMailToActors(java.lang.String[] actors, java.lang.String subject, java.lang.String body) - actors cannot be empty");
		}
		if (subject == null || subject.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendHtmlMailToActors(java.lang.String[] actors, java.lang.String subject, java.lang.String body) - subject cannot be null");
		}
		if (body == null || body.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendHtmlMailToActors(java.lang.String[] actors, java.lang.String subject, java.lang.String body) - body cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleSendHtmlMailToActors(actors, subject, body);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailService.class).
			warn ("Error on MailService.sendHtmlMailToActors", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailService.sendHtmlMailToActors", (Throwable) __r[1]);
	}

	protected abstract void handleSendHtmlMailToActors(java.lang.String[] actors, java.lang.String subject, java.lang.String body) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailService#	 * @see com.soffid.iam.iga.service.MailService#void sendHtmlMailToActors(java.lang.String[] actors, java.lang.String cc, java.lang.String subject, java.lang.String body)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void sendHtmlMailToActors(
		final java.lang.String[] actors, 
		final java.lang.String cc, 
		final java.lang.String subject, 
		final java.lang.String body)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (actors == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendHtmlMailToActors(java.lang.String[] actors, java.lang.String cc, java.lang.String subject, java.lang.String body) - actors cannot be empty");
		}
		if (cc == null || cc.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendHtmlMailToActors(java.lang.String[] actors, java.lang.String cc, java.lang.String subject, java.lang.String body) - cc cannot be null");
		}
		if (subject == null || subject.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendHtmlMailToActors(java.lang.String[] actors, java.lang.String cc, java.lang.String subject, java.lang.String body) - subject cannot be null");
		}
		if (body == null || body.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendHtmlMailToActors(java.lang.String[] actors, java.lang.String cc, java.lang.String subject, java.lang.String body) - body cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleSendHtmlMailToActors(actors, cc, subject, body);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailService.class).
			warn ("Error on MailService.sendHtmlMailToActors", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailService.sendHtmlMailToActors", (Throwable) __r[1]);
	}

	protected abstract void handleSendHtmlMailToActors(java.lang.String[] actors, java.lang.String cc, java.lang.String subject, java.lang.String body) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailService#	 * @see com.soffid.iam.iga.service.MailService#void sendTextMail(java.lang.String to, java.lang.String subject, java.lang.String body)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void sendTextMail(
		final java.lang.String to, 
		final java.lang.String subject, 
		final java.lang.String body)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (to == null || to.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendTextMail(java.lang.String to, java.lang.String subject, java.lang.String body) - to cannot be null");
		}
		if (subject == null || subject.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendTextMail(java.lang.String to, java.lang.String subject, java.lang.String body) - subject cannot be null");
		}
		if (body == null || body.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendTextMail(java.lang.String to, java.lang.String subject, java.lang.String body) - body cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleSendTextMail(to, subject, body);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailService.class).
			warn ("Error on MailService.sendTextMail", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailService.sendTextMail", (Throwable) __r[1]);
	}

	protected abstract void handleSendTextMail(java.lang.String to, java.lang.String subject, java.lang.String body) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailService#	 * @see com.soffid.iam.iga.service.MailService#void sendTextMail(java.lang.String to, java.lang.String cc, java.lang.String subject, java.lang.String body)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void sendTextMail(
		final java.lang.String to, 
		final java.lang.String cc, 
		final java.lang.String subject, 
		final java.lang.String body)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (to == null || to.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendTextMail(java.lang.String to, java.lang.String cc, java.lang.String subject, java.lang.String body) - to cannot be null");
		}
		if (cc == null || cc.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendTextMail(java.lang.String to, java.lang.String cc, java.lang.String subject, java.lang.String body) - cc cannot be null");
		}
		if (subject == null || subject.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendTextMail(java.lang.String to, java.lang.String cc, java.lang.String subject, java.lang.String body) - subject cannot be null");
		}
		if (body == null || body.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendTextMail(java.lang.String to, java.lang.String cc, java.lang.String subject, java.lang.String body) - body cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleSendTextMail(to, cc, subject, body);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailService.class).
			warn ("Error on MailService.sendTextMail", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailService.sendTextMail", (Throwable) __r[1]);
	}

	protected abstract void handleSendTextMail(java.lang.String to, java.lang.String cc, java.lang.String subject, java.lang.String body) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailService#	 * @see com.soffid.iam.iga.service.MailService#void sendTextMailToActors(java.lang.String[] actors, java.lang.String subject, java.lang.String body)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void sendTextMailToActors(
		final java.lang.String[] actors, 
		final java.lang.String subject, 
		final java.lang.String body)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (actors == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendTextMailToActors(java.lang.String[] actors, java.lang.String subject, java.lang.String body) - actors cannot be empty");
		}
		if (subject == null || subject.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendTextMailToActors(java.lang.String[] actors, java.lang.String subject, java.lang.String body) - subject cannot be null");
		}
		if (body == null || body.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendTextMailToActors(java.lang.String[] actors, java.lang.String subject, java.lang.String body) - body cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleSendTextMailToActors(actors, subject, body);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailService.class).
			warn ("Error on MailService.sendTextMailToActors", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailService.sendTextMailToActors", (Throwable) __r[1]);
	}

	protected abstract void handleSendTextMailToActors(java.lang.String[] actors, java.lang.String subject, java.lang.String body) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailService#	 * @see com.soffid.iam.iga.service.MailService#void sendTextMailToActors(java.lang.String[] actors, java.lang.String cc, java.lang.String subject, java.lang.String body)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void sendTextMailToActors(
		final java.lang.String[] actors, 
		final java.lang.String cc, 
		final java.lang.String subject, 
		final java.lang.String body)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (actors == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendTextMailToActors(java.lang.String[] actors, java.lang.String cc, java.lang.String subject, java.lang.String body) - actors cannot be empty");
		}
		if (cc == null || cc.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendTextMailToActors(java.lang.String[] actors, java.lang.String cc, java.lang.String subject, java.lang.String body) - cc cannot be null");
		}
		if (subject == null || subject.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendTextMailToActors(java.lang.String[] actors, java.lang.String cc, java.lang.String subject, java.lang.String body) - subject cannot be null");
		}
		if (body == null || body.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendTextMailToActors(java.lang.String[] actors, java.lang.String cc, java.lang.String subject, java.lang.String body) - body cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleSendTextMailToActors(actors, cc, subject, body);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailService.class).
			warn ("Error on MailService.sendTextMailToActors", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailService.sendTextMailToActors", (Throwable) __r[1]);
	}

	protected abstract void handleSendTextMailToActors(java.lang.String[] actors, java.lang.String cc, java.lang.String subject, java.lang.String body) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailService#	 * @see com.soffid.iam.iga.service.MailService#void sendTextMailToActors(java.lang.String[] actors, java.lang.String cc, java.lang.String subject, java.lang.String body, java.util.Collection mimeBodyParts)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void sendTextMailToActors(
		final java.lang.String[] actors, 
		final java.lang.String cc, 
		final java.lang.String subject, 
		final java.lang.String body, 
		final java.util.Collection mimeBodyParts)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (actors == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendTextMailToActors(java.lang.String[] actors, java.lang.String cc, java.lang.String subject, java.lang.String body, java.util.Collection mimeBodyParts) - actors cannot be empty");
		}
		if (cc == null || cc.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendTextMailToActors(java.lang.String[] actors, java.lang.String cc, java.lang.String subject, java.lang.String body, java.util.Collection mimeBodyParts) - cc cannot be null");
		}
		if (subject == null || subject.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendTextMailToActors(java.lang.String[] actors, java.lang.String cc, java.lang.String subject, java.lang.String body, java.util.Collection mimeBodyParts) - subject cannot be null");
		}
		if (body == null || body.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendTextMailToActors(java.lang.String[] actors, java.lang.String cc, java.lang.String subject, java.lang.String body, java.util.Collection mimeBodyParts) - body cannot be null");
		}
		if (mimeBodyParts == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendTextMailToActors(java.lang.String[] actors, java.lang.String cc, java.lang.String subject, java.lang.String body, java.util.Collection mimeBodyParts) - mimeBodyParts cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleSendTextMailToActors(actors, cc, subject, body, mimeBodyParts);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailService.class).
			warn ("Error on MailService.sendTextMailToActors", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailService.sendTextMailToActors", (Throwable) __r[1]);
	}

	protected abstract void handleSendTextMailToActors(java.lang.String[] actors, java.lang.String cc, java.lang.String subject, java.lang.String body, java.util.Collection mimeBodyParts) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailService#	 * @see com.soffid.iam.iga.service.MailService#void sendTextMailToActors(java.lang.String[] actors, java.lang.String subject, java.lang.String body, java.util.Collection mimeBodyParts)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void sendTextMailToActors(
		final java.lang.String[] actors, 
		final java.lang.String subject, 
		final java.lang.String body, 
		final java.util.Collection mimeBodyParts)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (actors == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendTextMailToActors(java.lang.String[] actors, java.lang.String subject, java.lang.String body, java.util.Collection mimeBodyParts) - actors cannot be empty");
		}
		if (subject == null || subject.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendTextMailToActors(java.lang.String[] actors, java.lang.String subject, java.lang.String body, java.util.Collection mimeBodyParts) - subject cannot be null");
		}
		if (body == null || body.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendTextMailToActors(java.lang.String[] actors, java.lang.String subject, java.lang.String body, java.util.Collection mimeBodyParts) - body cannot be null");
		}
		if (mimeBodyParts == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailService.sendTextMailToActors(java.lang.String[] actors, java.lang.String subject, java.lang.String body, java.util.Collection mimeBodyParts) - mimeBodyParts cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleSendTextMailToActors(actors, subject, body, mimeBodyParts);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailService.class).
			warn ("Error on MailService.sendTextMailToActors", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailService.sendTextMailToActors", (Throwable) __r[1]);
	}

	protected abstract void handleSendTextMailToActors(java.lang.String[] actors, java.lang.String subject, java.lang.String body, java.util.Collection mimeBodyParts) throws Exception;

	/**
	 * Gets the current <code>principal</code> if one has been set,
	 * otherwise returns <code>null</code>.
	 *
	 * @return the current principal
	 */
	protected java.security.Principal getPrincipal()
	{
		return com.soffid.iam.PrincipalStore.get();
	}

}
