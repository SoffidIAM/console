//
// (C) 2020 Soffid
//
//

package com.soffid.iam.exception;
import org.apache.commons.beanutils.PropertyUtils;
/**
 * Exception NeedsAccountNameException
 */
public class NeedsAccountNameException
 extends java.lang.Exception
 {

	/**
	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * The default constructor.
	 */
	public NeedsAccountNameException()
	{}

	/**
	 * Constructs a new instance of NeedsAccountNameException
	 *
	 * @param throwable the parent Throwable
	 */
	public NeedsAccountNameException(Throwable throwable)
	{
		super(findRootCause(throwable));
	}

	/**
	 * Constructs a new instance of NeedsAccountNameException
	 *
	 * @param message the throwable message.
	 */
	public NeedsAccountNameException(String message)
	{
		super(message);
	}

	/**
	 * Constructs a new instance of NeedsAccountNameException
	 *
	 * @param message the throwable message.
	 * @param throwable the parent of this Throwable.
	 */
	public NeedsAccountNameException(String message, Throwable throwable)
	{
		super(message, findRootCause(throwable));
	}

	/**
	 * Finds the root cause of the parent exception
	 * by traveling up the exception tree
	 */
	private static Throwable findRootCause(Throwable th)
	{
		if (th != null)
		{
			// Lets reflectively get any JMX or EJB exception causes.
			try
			{
				Throwable targetException = null;
				// java.lang.reflect.InvocationTargetException
				// or javax.management.ReflectionException
				String exceptionProperty = "targetException";
				if (PropertyUtils.isReadable(th, exceptionProperty))
				{
					targetException = (Throwable)PropertyUtils.getProperty(th, exceptionProperty);
				}
				else
				{
					exceptionProperty = "causedByException";
					//jakarta.ejb.EJBException
					if (PropertyUtils.isReadable(th, exceptionProperty))
					{
						targetException = (Throwable)PropertyUtils.getProperty(th, exceptionProperty);
					}
				}
				if (targetException != null)
				{
					th = targetException;
				}
			}
			catch (Exception ex)
			{
				// just print the exception and continue
				ex.printStackTrace();
			}

			if (th.getCause() != null)
			{
				th = th.getCause();
				th = findRootCause(th);
			}
		}
		return th;
	}
}
