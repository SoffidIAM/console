package es.caib.seycon.ng.sync.intf;

import java.util.List;

public class OfflineDatabaseChange extends OfflineChange
{
	public enum Action { UPDATED_ROW, DELETED_ROW };
	Action action;
	String table;
	String primaryKey;
	Long primaryKeyValue;
	List<String> columns;
	public Action getAction()
	{
		return action;
	}
	public void setAction(Action action)
	{
		this.action = action;
	}
	public String getTable()
	{
		return table;
	}
	public void setTable(String table)
	{
		this.table = table;
	}
	public String getPrimaryKey()
	{
		return primaryKey;
	}
	public void setPrimaryKey(String primaryKey)
	{
		this.primaryKey = primaryKey;
	}
	public Long getPrimaryKeyValue()
	{
		return primaryKeyValue;
	}
	public void setPrimaryKeyValue(Long primaryKeyValue)
	{
		this.primaryKeyValue = primaryKeyValue;
	}
	public List<String> getColumns()
	{
		return columns;
	}
	public void setColumns(List<String> columns)
	{
		this.columns = columns;
	}
	public List<Object> getValues()
	{
		return values;
	}
	public void setValues(List<Object> values)
	{
		this.values = values;
	}
	List<Object> values;
	
}
