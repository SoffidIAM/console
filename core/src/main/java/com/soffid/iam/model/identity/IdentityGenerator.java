/**
 * 
 */
package com.soffid.iam.model.identity;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.exception.JDBCExceptionHelper;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.type.Type;
import org.hibernate.util.PropertiesHelper;

/**
 * @author bubu
 *
 */
public class IdentityGenerator implements IdentifierGenerator {

	public Serializable generate(SessionImplementor session, Object obj) 
	throws HibernateException {
		try {
			return IdentityGeneratorBean.instance().getNext(session);
		} catch (Exception e) {
			throw new HibernateException(e);
		}
	}

}
