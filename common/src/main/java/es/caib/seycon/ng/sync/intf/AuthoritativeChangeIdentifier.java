package es.caib.seycon.ng.sync.intf;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

public class AuthoritativeChangeIdentifier implements Serializable
{
	Object employeeId;
	
	Date date;
	
	Object changeId;

	public Object getEmployeeId()
	{
		return employeeId;
	}

	public void setEmployeeId(Object employeeId)
	{
		this.employeeId = employeeId;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public Object getChangeId()
	{
		return changeId;
	}

	public void setChangeId(Object changeId)
	{
		this.changeId = changeId;
	}

	public AuthoritativeChangeIdentifier()
	{
	}
	
	public String toString ()
	{
		StringBuffer sb = new StringBuffer("{");
		if (employeeId != null)
			sb.append ("employee: ")
				.append (employeeId.toString())
				.append (" ");
		if (date != null)
			sb.append ("date: ")
			.append (DateFormat.getDateTimeInstance().format(date))
			.append (" ");
		if (changeId != null)
			sb.append ("changeId: ")
				.append (changeId)
				.append(" ");
		sb.append ("}");
		return sb.toString();
	}

}
