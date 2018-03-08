package org.flywind.business.dao.cms.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.flywind.business.common.constants.FBaseConstants;
import org.flywind.business.dao.base.AbstractFBaseDao;
import org.flywind.business.dao.cms.CategoryDao;
import org.flywind.business.entities.base.FSysInfo;
import org.flywind.business.entities.cms.Category;
import org.flywind.widgets.core.dao.FPage;
import org.flywind.widgets.utils.JQueryUtils;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDaoImpl extends AbstractFBaseDao<Category> implements CategoryDao {

	@Override
	public List<Category> getAllCategory(Category category, FPage paging, FSysInfo session){
		StringBuilder hql = new StringBuilder("FROM Category");
		StringBuilder countHql = new StringBuilder("SELECT COUNT(id) FROM Category");
		StringBuilder condition = new StringBuilder(" WHERE customerCode = :customerCode");
		
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(FBaseConstants.CUSTOMER_CODE, session.getCustomerCode());
		
		if(category != null){
			if (StringUtils.isNoneBlank(category.getName())) {
				condition.append(" and name LIKE :name");
				params.put("name", "%" + category.getName().trim() + "%");
			}
		}
		
		if(category.getType() != null){
			condition.append(" and type =:type");
			params.put("type", category.getType());
		}
		
		if (null != paging) { 
			condition.append(" order by " + paging.getSortName() + " " + paging.getSortOrder());
		}
		
		countHql.append(condition);
		Long count = super.count(countHql.toString(), params);
		paging.setRowCount(count.intValue());
		
		hql.append(condition);
		return super.query(hql.toString(), params, paging.getPageNumber(), paging.getPageSize());
	}
	
	@Override
	public List<Category> getAllCategory(Category category, FPage paging, String customerCode){
		StringBuilder countHql = new StringBuilder("select count(id) from Category");
		StringBuilder hql = new StringBuilder("from Category");
		StringBuilder condition = new StringBuilder(" WHERE customerCode = :customerCode");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(FBaseConstants.CUSTOMER_CODE, customerCode);
		
		if(category != null){
			if (StringUtils.isNoneBlank(category.getName())) {
				condition.append(" and name LIKE :name");
				params.put("name", "%" + category.getName().trim() + "%");
			}
		}
		
		if(category.getType() != null){
			condition.append(" and type =:type");
			params.put("type", category.getType());
		}
		
		if (null != paging) { 
			condition.append(" order by " + paging.getSortName() + " " + paging.getSortOrder());
		}
		
		countHql.append(condition);
		Long total = this.count(countHql.toString(), params);
		paging.setRowCount(total.intValue()); 
		
		int totalPages = JQueryUtils.findTotalPages(total.intValue(), paging.getPageSize());
		paging.setPageCount(totalPages);
		
		hql.append(condition);
		return this.query(hql.toString(), params, paging.getPageNumber(), paging.getPageSize());
	}
}
