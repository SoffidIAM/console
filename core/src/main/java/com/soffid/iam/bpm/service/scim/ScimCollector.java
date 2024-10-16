package com.soffid.iam.bpm.service.scim;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.LeafReaderContext;
import org.apache.lucene.search.ScoreMode;
import org.apache.lucene.search.SimpleCollector;
import org.hibernate.Session;

import com.soffid.iam.api.AsyncList;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
import com.soffid.scimquery.HQLQuery;
import com.soffid.scimquery.expr.AbstractExpression;

public class ScimCollector extends SimpleCollector {
	private Collection<Object> result;
	private String hqlQuery;
	private Session session;
	private Map<String, Object> parameters;
	int pos = 0;
	int count = 0;
	private CriteriaSearchConfiguration config;
	boolean cancelled = false;
	boolean end = false;
	private ValueObjectGenerator generator;
	private HQLQuery hql;
	private AbstractExpression expr;
	private LeafReaderContext context;
	Set<String> fields = Set.of("_id");
			
	public ScimCollector(Collection<Object> result) {
		this.result = result;
	}

	protected void doSetNextReader(LeafReaderContext context) throws IOException {
		this.context = context;
	}
	

	public void setResult(List resultado) {
		this.result = resultado;
	}

	@Override
	public void collect(int id) throws IOException {
		if (cancelled) return;
		
		org.apache.lucene.document.Document d = context.reader().document(id, fields);
		IndexableField f = d.getField("_id"); //$NON-NLS-1$
		if (f != null) {
			if (end) {
				count++;
				return;
			}
			long objectId = Long.parseLong(f.stringValue());
			try {
				org.hibernate.Query queryObject = session.createQuery( hqlQuery );
				for (Entry<String, Object> entry: parameters.entrySet())
				{
					if (entry.getValue() == null)
						queryObject.setParameter(entry.getKey(), entry.getValue(), 
								org.hibernate.Hibernate.STRING);
					else
						queryObject.setParameter(entry.getKey(), entry.getValue());
				}
				queryObject.setParameter("id", objectId);
				for (Iterator it = queryObject.iterate(); it.hasNext(); ) {
					Object e = it.next();
					if ( applies()) {
						if (result instanceof AsyncList)
						{
							if (((AsyncList) result).isCancelled()) {
								cancelled = true;
							}
						}
						
						Object vo = generator.toValueObject(e);
						if (vo != null) {
							Object v = vo.getClass().isArray() ? Array.get(vo, 0): vo;
							if (! hql.isNonHQLAttributeUsed() || expr.evaluate(v)) {
								result.add(vo);
								count ++;
							}
						}
					}					
				}
			} catch (Exception e) {
				// Ignorar
			}
		}
		
	}

	private boolean applies() {
		if (config == null)
			return true;
		if (config.getFirstResult() != null && pos < config.getFirstResult().intValue())
			return false;
		if (config.getMaximumResultSize() == null)
			return true;
		if (pos >= (config.getFirstResult() == null ? config.getMaximumResultSize().intValue(): 
					config.getFirstResult().intValue() + config.getMaximumResultSize().intValue())) {
			end = true;
			return false;
		}
		return true;
	}

	public void setConfig(CriteriaSearchConfiguration config) {
		this.config = config;
	}

	public int size() {
		return count;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public void setHqlQuery(String hqlQuery) {
		this.hqlQuery = hqlQuery;
		
	}

	public void setParams(Map<String, Object> parameters) {
		this.parameters = parameters;
		
	}

	public void setGenerator(ValueObjectGenerator generator) {
		this.generator = generator;
	}

	public void setExpression(AbstractExpression expr) {
		this.expr = expr;
	}

	public void setHql(HQLQuery hql) {
		this.hql = hql;
	}

	@Override
	public ScoreMode scoreMode() {
		return ScoreMode.COMPLETE_NO_SCORES;
	}

}
