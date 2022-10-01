package com.soffid.iam.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TimeZone;

import org.joda.time.LocalDateTime;

import com.soffid.iam.api.AuthorizationRole;
import com.soffid.iam.api.RoleGrant;
import com.soffid.iam.api.Stats;
import com.soffid.iam.api.StatsSample;
import com.soffid.iam.model.JumpServerEntity;
import com.soffid.iam.model.JumpServerGroupEntity;
import com.soffid.iam.model.StatsEntity;
import com.soffid.iam.utils.ConfigurationCache;

import es.caib.seycon.ng.exception.InternalErrorException;

public class StatsServiceImpl extends StatsServiceBase {

	private static final String DATE_FORMAT = "yyyyMMddHHmm";

	@Override
	protected Stats handleFindStats(String name, Date since, Date until, int step) throws Exception {
		Stats s = new Stats();
		s.setName(name);
		s.setTags( new LinkedList<String>());
		s.setSeries(new HashMap<String, List<StatsSample>>());
		String lastTag = null;
		if (step <= 1) step = 1;
		for ( StatsEntity data: getStatsEntityDao().findByName(name, 
				new SimpleDateFormat(DATE_FORMAT).format(since),
				new SimpleDateFormat(DATE_FORMAT).format(until) ))
		{
			if (data.getValue() != null)
			{
				// Create the serie
				LinkedList<StatsSample> serie = (LinkedList<StatsSample>) s.getSeries().get(data.getSerie());
				if (serie == null)
				{
					serie = new LinkedList<StatsSample>();
					for (int i = 0; i < s.getTags().size(); i++)
						serie.add(new StatsSample());
					s.getSeries().put(data.getSerie(), serie);
				}
				// Create the tag
				String date = data.getDate();
				Date d = new SimpleDateFormat(DATE_FORMAT).parse(date);
				
				long minutes = d.getTime();
				int offset = TimeZone.getDefault().getOffset(minutes);
				minutes = minutes + offset;
				minutes = minutes - minutes % ( step * 60000L);
				minutes = minutes - offset;
				Date realDate = new Date(minutes);
				String date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(realDate);
				if (!date2.equals(lastTag))
				{
					s.getTags().add(date2);
					for ( List<StatsSample> serie2: s.getSeries().values())
						serie2.add(new StatsSample());
					lastTag = date2;
				}
				// Update the value
				StatsSample value = serie.getLast();
				value.setSum(value.getSum()+data.getValue());
				value.setInstances(value.getInstances()+1);
				if (value.getInstances() == 1)
				{
					value.setMax(data.getValue().longValue());
					value.setMin(data.getValue().longValue());
					value.setAverage(data.getValue().longValue());
				} else {
					if (data.getValue().longValue() > value.getMax())
						value.setMax(data.getValue().longValue());
					if (data.getValue().longValue() < value.getMin())
						value.setMin(data.getValue().longValue());
					value.setAverage(value.getSum() / value.getInstances());
				}
			}
		}
		return s;
	}

	@Override
	protected void handlePurge() throws Exception {
		String days = ConfigurationCache.getMasterProperty("stats.days");
		int daysNumber = 30;
		try {
			daysNumber = Integer.parseInt(days);
		} catch (Exception e) {}
		if (days == null) days = "30";
		getStatsEntityDao().purge(daysNumber);
	}

	static long lastRun = 0;
	@Override
	protected void handleUpdateStats() throws Exception {
		updatePamUsers();
		updatePamSessions();
		updateHpaAccounts();
		updatePamAccounts();
		updatePamStorage();
		if (System.currentTimeMillis() - lastRun > 8 * 60  * 60 * 1000L)
		{
			lastRun = System.currentTimeMillis();
			handlePurge();
		}
 	}

	private void updatePamStorage() throws InternalErrorException {
		String date = getDateString();
		
		HashMap<String, Long> usedSpace = new HashMap<String, Long>();
		HashMap<String, Long> freeSpace = new HashMap<String, Long>();
		
		for ( JumpServerGroupEntity jsg: getJumpServerGroupEntityDao().loadAll())
		{
			Long l = getPamSessionService().getConsoleFreeSpace(jsg.getName());
			if (l != null)
				freeSpace.put(jsg.getName(), l);
			Long l2 = getPamSessionService().getConsoleUsedSpace(jsg.getName());
			if (l2 != null)
				usedSpace.put(jsg.getName(), l2);
		}
		
		updateEntry("PAM_STORAGE_FREE", date, true, toArray(freeSpace));
		updateEntry("PAM_STORAGE_USED", date, true, toArray(usedSpace));
	}

	private void updatePamAccounts() {
		String date = getDateString();
		
		Long l = getAccountEntityDao().getPamAccounts();
		Long l2 = getAccountEntityDao().getPamAccountsWrongPassword();
		Long l3 = getAccountEntityDao().getPamAccountsExpiredPassword();
		updateEntry("PAM_ACCOUNTS", date, true, new Object[][] {
			new Object[] {"Good", 
					(l == null ? 0L: l.longValue()) - 
					(l2 == null ? 0L: l2.longValue()) - 
					(l3 == null ? 0L: l3.longValue()) 
			},
			new Object[] {"Expired password", 
					(l3 == null ? 0L: l3.longValue()) 
			},
			new Object[] {"Wrong password", 
					(l2 == null ? 0L: l2.longValue()) 
			}
		});
	}

	private void updateHpaAccounts() {
		String date = getDateString();
		
		Long l = getAccountEntityDao().getHPAccounts();
		Long l2 = getAccountEntityDao().getReservedHPAccounts();
		updateEntry("HPA_ACCOUNTS", date, true, new Object[][] {
			new Object[] {"Reserved", 
					(l2 == null ? 0L: l2.longValue()) 
			},
			new Object[] {"Available", 
					(l == null ? 0L: l.longValue()) - 
					(l2 == null ? 0L: l2.longValue()) 
			}
		});
	}

	private void updatePamSessions() throws InternalErrorException {
		String date = getDateString();
		HashMap<String, Long> servers = new HashMap<String,Long>();
		for (JumpServerGroupEntity group: getJumpServerGroupEntityDao().loadAll())
		{
			for (JumpServerEntity server: group.getJumpServers())
			{
				Integer i = getPamSessionService().getActiveSessions(server.getUrl());
				if (i != null)
					servers.put(server.getUrl(), new Long(i.longValue()));
			}
		}
		
		Object[][] r = toArray(servers);
		updateEntry("PAM_SESSIONS", date, true, r);
	}

	public Object[][] toArray(HashMap<String, Long> servers) {
		Object[][] r = new Object[servers.size()][];
		int i = 0;
		for (Entry<String, Long> entry: servers.entrySet())
		{
			r[i] = new Object[2];
			r[i][0] = entry.getKey();
			r[i][1] = entry.getValue();
			i++;
 		}
		return r;
	}

	private void updatePamUsers() throws InternalErrorException {
		String date = getDateString();
		HashSet<String> users = new HashSet<String>();
		for (AuthorizationRole auts: getAuthorizationService().getAuthorizationRoles("pamSession:create"))
		{
			for ( RoleGrant grant: getApplicationService().findEffectiveRoleGrantsByRoleId(auts.getRole().getId()))
			{
				if (grant.getUser() != null)
					users.add(grant.getUser());
			}
		}
		for (AuthorizationRole auts: getAuthorizationService().getAuthorizationRoles("authorization:all"))
		{
			for ( RoleGrant grant: getApplicationService().findEffectiveRoleGrantsByRoleId(auts.getRole().getId()))
			{
				if (grant.getUser() != null)
					users.add(grant.getUser());
			}
		}
		updateEntry("PAM_USERS", date, true, new Object[][] { 
			new Object [] {"users", new Long( users.size() )}
		});
	}

	private void updateEntry(String name, String date, boolean replace, Object[][] objects) {
		Collection<StatsEntity> s = getStatsEntityDao().findByName(name, date, date);
		for (Object[] row: objects)
		{
			boolean found = false;
			for (StatsEntity entity: s)
			{
				if (entity.getSerie().equals(row[0]))
				{
					if (replace)
					{
						entity.setValue( (Long)row[1] );
					}
					else
					{
						entity.setValue( new Long ( ( (Long) row[1]).longValue() + entity.getValue().longValue() ) );
					}
					getStatsEntityDao().update(entity);
					found = true;
				}
			}
			if ( ! found )
			{
				StatsEntity entity = getStatsEntityDao().newStatsEntity();
				entity.setName(name);
				entity.setSerie(row[0].toString());
				entity.setDate(date);
				entity.setValue((Long) row[1]);
				getStatsEntityDao().create(entity);
			}
		}
	}

	public String getDateString() {
		return new SimpleDateFormat(DATE_FORMAT).format(new Date());
	}

}
