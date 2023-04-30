package com.soffid.iam.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.FieldInfo.IndexOptions;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queries.BooleanFilter;
import org.apache.lucene.queries.FilterClause;
import org.apache.lucene.queries.TermsFilter;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.Collector;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.TermRangeFilter;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.Version;
import org.jbpm.graph.exe.ProcessInstance;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.soffid.iam.api.CustomObject;
import com.soffid.iam.api.DataType;
import com.soffid.iam.bpm.index.DirectoryFactory;
import com.soffid.iam.bpm.index.Messages;
import com.soffid.iam.model.CustomObjectTypeEntity;
import com.soffid.iam.model.MetaDataEntity;
import com.soffid.iam.service.impl.LuceneIndexStatus;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;

public class LuceneIndexServiceImpl extends LuceneIndexServiceBase implements InitializingBean {
	Log log = LogFactory.getLog(getClass());
	public static final FieldType INDEXED_STRING = new FieldType();
	static {
		INDEXED_STRING.setIndexed(true);
		INDEXED_STRING.setOmitNorms(true);
		INDEXED_STRING.setIndexOptions(IndexOptions.DOCS_ONLY);
		INDEXED_STRING.setStored(false);
		INDEXED_STRING.setTokenized(true);
		INDEXED_STRING.freeze();
	}
	
    Hashtable<String, LuceneIndexStatus> status = new Hashtable<>();
	DateFormat dfDateTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	DateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	protected void handleAddDocument(String index, Document doc) throws Exception {
		LuceneIndexStatus s = getStatus(index);
		
		s.fetchIfNeeded();
		
		IndexWriter w;
		final Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_CURRENT,
				new CharArraySet(Version.LUCENE_CURRENT, 0, true));
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_CURRENT, analyzer);
		iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
		w = new IndexWriter (s.getDirectory(), iwc);
		try { 
			// Delete pre-existing document
			w.deleteDocuments(new Term ("id", doc.get("id"))); //$NON-NLS-1$ //$NON-NLS-2$
			w.addDocument(doc);
			s.setDirty();
		} finally {
			w.close();
		}
	}

	@Override
	protected void handleIndexObject(String index, Object o) throws Exception {
		Document doc = documentFromObject(o);
		if (doc != null)
			handleAddDocument(index, doc);

	}

	private Document documentFromObject(Object o) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InternalErrorException {
		CustomObjectTypeEntity type;
		if (o instanceof CustomObject)
			type = getCustomObjectTypeEntityDao().findByName(((CustomObject) o).getType());
		else
			type = getCustomObjectTypeEntityDao().findByName(o.getClass().getName());
		
		if (type == null)
			return null;
		
		Document doc = new Document();
		doc.add(new LongField("id", (Long) PropertyUtils.getProperty(o, "id"), Store.YES ));
		StringBuffer contents = new StringBuffer();
		for (DataType att: getAdditionalDataService().findDataTypesByObjectTypeAndName2(type.getName(), null)) {
			if (Boolean.TRUE.equals(att.getSearchCriteria())) {
				Object value;
				if (Boolean.TRUE.equals(att.getBuiltin()))
					value = PropertyUtils.getProperty(o, att.getName());
				else
					value = PropertyUtils.getMappedProperty(o, "attributes", att.getName());

				if (value != null) {
					if (value instanceof Number) {
						doc.add(new Field(att.getName(), value.toString(), INDEXED_STRING ));
						contents.append(value.toString()).append(" ");
					}
					else if (value instanceof Date) {
						String s;
						if (att.getType() == TypeEnumeration.DATE_TYPE) 
							s = dfDate.format(((Date) value));
						else
							s = dfDateTime.format(((Date) value));
						doc.add(new Field(att.getName(), s, INDEXED_STRING ));
						contents.append(s).append(" ");
					}
					else {
						doc.add(new Field(att.getName(), value.toString(), INDEXED_STRING ));
						contents.append(value.toString()).append(" ");
					}
				}
			}
		}
		doc.add(new Field("$contents", contents.toString(), INDEXED_STRING));
		return doc;
	}

	@Override
	protected void handleSearch(String index, String query, Collector collector) throws Exception {
		LuceneIndexStatus s = getStatus(index);
		
		s.fetchIfNeeded();
		
		IndexReader reader = DirectoryReader.open(s.getDirectory());
		IndexSearcher is;
		is = new IndexSearcher(reader);
		QueryParser qp = new QueryParser(Version.LUCENE_CURRENT,
				"$contents", //$NON-NLS-1$
				DirectoryFactory.getAnalyzer());
		org.apache.lucene.search.Query q = null;
		if (query != null && query.trim().length() > 0)
			q = qp.parse(query);
		else
			q = new MatchAllDocsQuery();
		is.search(q, collector); // Sense cap filtre
	}
	
	private LuceneIndexStatus getStatus(String index) throws FileNotFoundException, IOException, InternalErrorException {
		String realName = Security.getCurrentTenantName()+"/"+index;
		LuceneIndexStatus s = status.get(realName);
		if (s == null)
		{
			s = new LuceneIndexStatus(getLuceneIndexEntityDao(), getLuceneIndexPartEntityDao(), realName );
			status.put(index, s);
			s.fetchFromDatabase( );
		}
		else
		{
			s.fetchIfNeeded();
		}
		return s;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Thread t = new Thread( () ->  {
			while (true) {
				try {
					for (LuceneIndexStatus s: new java.util.LinkedList<LuceneIndexStatus>( status.values() )) {
						try {
							getAsyncRunnerService().runNewTransaction(() -> {
								s.saveIfNeeded();
								return null;
							});
						} catch (Exception e) {
							log.warn("Error indexing data "+s.getIndexDir().getPath(), e);
						}
					}
				} catch (Exception e) {
					log.warn("Error indexing data ", e);
				}
				try {
					Thread.sleep(60000);
				} catch (Exception e) {}
			}
		});
		t.setName("index-saver");
		t.setDaemon(true);
		t.start();
	}

	@Override
	protected void handleResetIndex(String index) throws Exception {
		LuceneIndexStatus s = status.get(index);
		if (s != null) {
			s.reset();
		}
	}

}
