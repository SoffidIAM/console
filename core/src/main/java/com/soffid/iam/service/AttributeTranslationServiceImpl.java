package com.soffid.iam.service;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.AttributeTranslation;
import com.soffid.iam.bpm.service.scim.ScimHelper;
import com.soffid.iam.model.AttributeTranslationEntity;
import com.soffid.iam.model.AttributeTranslationEntityDao;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;

public class AttributeTranslationServiceImpl extends
		AttributeTranslationServiceBase {

	@Override
	protected Collection<AttributeTranslation> handleFindByColumn1(
			String domain, String column1) throws Exception {
		AttributeTranslationEntityDao dao = getAttributeTranslationEntityDao();
		return dao.toAttributeTranslationList(dao.findByColumn1(domain, column1));
	}

	@Override
	protected Collection<AttributeTranslation> handleFindByColumn2(
			String domain, String column2) throws Exception {
		AttributeTranslationEntityDao dao = getAttributeTranslationEntityDao();
		return dao.toAttributeTranslationList(dao.findByColumn2(domain, column2));
	}

	@Override
	protected Collection<AttributeTranslation> handleFindByExample(
			String domain, String column1, String column2) throws Exception {
		AttributeTranslationEntityDao dao = getAttributeTranslationEntityDao();
		List<AttributeTranslation> list = dao.toAttributeTranslationList(dao.findByExample(domain, column1, column2));
		Collections.sort(list, new Comparator<AttributeTranslation>() {

			public int compare(AttributeTranslation o1, AttributeTranslation o2) {
				int i = o1.getDomain().compareTo(o2.getDomain());
				if ( i == 0)
					i = o1.getColumn1().compareTo(o2.getColumn1());
				if ( i == 0)
					i = o1.getColumn2().compareTo(o2.getColumn2());
				return i;
			}
			
		});
		return list;
	}

	@Override
	protected void handleDelete(AttributeTranslation att) throws Exception {
		AttributeTranslationEntityDao dao = getAttributeTranslationEntityDao();
		AttributeTranslationEntity ate = dao.load(att.getId());
		if (ate != null)
			dao.remove(ate);
	}

	@Override
	protected AttributeTranslation handleCreate(AttributeTranslation att)
			throws Exception {
		AttributeTranslationEntityDao dao = getAttributeTranslationEntityDao();
		AttributeTranslationEntity ate = dao.attributeTranslationToEntity(att);
		dao.create(ate);
		return dao.toAttributeTranslation(ate);
	}

	@Override
	protected AttributeTranslation handleUpdate(AttributeTranslation att)
			throws Exception {
		AttributeTranslationEntityDao dao = getAttributeTranslationEntityDao();
		AttributeTranslationEntity ate = dao.attributeTranslationToEntity(att);
		dao.update(ate);
		return dao.toAttributeTranslation(ate);
	}

	@Override
	protected Collection<String> handleFindDomains() throws Exception {
		HashSet<String> domains = new HashSet ();
		for (AttributeTranslationEntity ate: getAttributeTranslationEntityDao().loadAll())
		{
			domains.add(ate.getDomain());
		}
		
		LinkedList<String> domains2 = new LinkedList<String>(domains);
		Collections.sort(domains2);
		
		return domains2;
	}

	@Override
	protected AsyncList<AttributeTranslation> handleFindByTextAndJsonQueryAsync(String text, String jsonQuery) throws Exception {
		final AsyncList<AttributeTranslation> result = new AsyncList<AttributeTranslation>();
		getAsyncRunnerService().run(new Runnable() {
			@Override
			public void run() {
				try {
					handleFindByTextAndJsonQueryAsync(text, jsonQuery, null, null, result);
				} catch (Throwable e) {
					throw new RuntimeException(e);
				}				
			}
		}, result);

		return result;
	}

	private void handleFindByTextAndJsonQueryAsync(String text, String jsonQuery,
			Integer start, Integer pageSize,
			Collection<AttributeTranslation> result) throws Exception {
		final AttributeTranslationEntityDao dao = getAttributeTranslationEntityDao();
		ScimHelper h = new ScimHelper(AttributeTranslation.class);
		h.setPrimaryAttributes(new String[] { "domain", "column1", "column2", "column3", "column4", "column5"});
		CriteriaSearchConfiguration config = new CriteriaSearchConfiguration();
		config.setFirstResult(start);
		config.setMaximumResultSize(pageSize);
		h.setConfig(config);
		h.setGenerator((entity) -> {
			return dao.toAttributeTranslation((AttributeTranslationEntity) entity);
		});
		h.setTenantFilter("tenant.id");
		h.search(text, jsonQuery, (Collection) result); 
	}

	@Override
	protected Collection<AttributeTranslation> handleFindByTextAndJsonQuery(String text, String jsonQuery,
			Integer start, Integer pageSize) throws Exception {
		final LinkedList<AttributeTranslation> result = new LinkedList<AttributeTranslation>();
		handleFindByTextAndJsonQueryAsync(text, jsonQuery, start, pageSize, result);
		return result;
	}

}
