package com.soffid.iam.service;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.Collector;

import com.soffid.iam.model.CustomObjectTypeEntity;
import com.soffid.iam.model.LuceneIndexEntity;
import com.soffid.iam.model.LuceneIndexPartEntity;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.Service;

import es.caib.seycon.ng.servei.DadesAddicionalsService;

@Service(internal = true)
@Depends({LuceneIndexEntity.class, LuceneIndexPartEntity.class,
	CustomObjectTypeEntity.class,
	DadesAddicionalsService.class,
	AsyncRunnerService.class})
public class LuceneIndexService {
	public void addDocument(String index, Document doc) {};
	
	public void search (String index, @Nullable String query, Collector collector) {}
	
	public void indexObject(String index, Object o) {}
	
	public void resetIndex(String index) {}
}
