package com.soffid.iam.sync.service;

import java.util.Map;

import com.soffid.mda.annotation.Service;

@Service ( serverOnly=true,
serverPath="SEU/SyncServerStatsService",
serverRole="SEU_CONSOLE")
public class SyncServerStatsService {
	public void register (String metric, String submetric, int value) {}
	
	public Map<String,int[]> getStats ( String metric, int seconds, int step ) { return null;}
}
