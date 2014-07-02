//
// (C) 2013 Soffid
//
//

package com.soffid.iam.doc.model;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.Restrictions;

/**
 * DAO FileSystem implementation
 */
public class FileSystemDaoImpl extends FileSystemDaoBase
{
	@Override
	public Long nextNumberFor(String application, int year) {
		//Creamos el criterio de busqueda
		Criteria criteria = getSession().createCriteria(FileSystem.class);

		criteria.add(Restrictions.eq("application", application));
		criteria.add(Restrictions.eq("year", new Integer(year)));
		criteria.setLockMode(LockMode.UPGRADE);
		FileSystem fileSystem = (FileSystem)criteria.uniqueResult();
		
		if(fileSystem!= null)
		{
			long newValue = fileSystem.getNextDocNumber().longValue() + 1; 
			fileSystem.setNextDocNumber(new Long(newValue));
			
			update(fileSystem);
			return newValue;
		}
		else
		{
			fileSystem = newFileSystem();
			
			fileSystem.setApplication(application);
			fileSystem.setYear(new Integer(year));
			fileSystem.setNextDocNumber(new Long(1));

			create(fileSystem);
			return new Long(1);
		}
	}
}
