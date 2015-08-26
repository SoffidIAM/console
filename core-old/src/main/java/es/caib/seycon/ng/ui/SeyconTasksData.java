package es.caib.seycon.ng.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SeyconTasksData {

	public final Map<Long, SeyconTask> SEYCON_TASKS_TABLE = new LinkedHashMap<Long, SeyconTask>();
	
	private String urlAgent = null;

	public SeyconTasksData() {

	}

	public SeyconTasksData(Collection<SeyconTask> tasques) {
		setTasques(tasques);
	}

	public void addTasca(SeyconTask tasca) {
		tasca.setUrlAgent(getUrlAgent());
		SEYCON_TASKS_TABLE.put(tasca.getId(), tasca);
	}

	public void setTasques(Collection<SeyconTask> tasques) {
		SEYCON_TASKS_TABLE.clear();
		if (tasques != null)
			for (Iterator<SeyconTask> it = tasques.iterator(); it.hasNext();) {
				addTasca(it.next());
			}
	}

	public List<SeyconTask> getAll() {
		return new ArrayList<SeyconTask>(SEYCON_TASKS_TABLE.values());
	}

	/**
	 * @return the urlAgent
	 */
	public String getUrlAgent ()
	{
		return urlAgent;
	}

	/**
	 * @param urlAgent the urlAgent to set
	 */
	public void setUrlAgent (String urlAgent)
	{
		this.urlAgent = urlAgent;
	}

}
