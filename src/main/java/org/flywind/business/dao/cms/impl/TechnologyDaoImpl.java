package org.flywind.business.dao.cms.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.flywind.business.common.constants.FBaseConstants;
import org.flywind.business.dao.base.AbstractFBaseDao;
import org.flywind.business.dao.cms.TechnologyDao;
import org.flywind.business.entities.base.FSysInfo;
import org.flywind.business.entities.cms.Technology;
import org.flywind.widgets.core.dao.FPage;
import org.flywind.widgets.utils.JQueryUtils;
import org.springframework.stereotype.Repository;

/**
 * <p>Technology Dao Impl</p>
 * 
 * @author flywind(飞风)
 * @date 2016年6月22日
 * @网址：http://www.flywind.org
 * @QQ技术群：41138107(人数较多最好先加这个)或33106572
 * @since 1.0
 */
@Repository
public class TechnologyDaoImpl extends AbstractFBaseDao<Technology> implements TechnologyDao {

	@Override
	public List<Technology> findAll(Technology technology, FPage paging, FSysInfo session) {
		StringBuilder hql = new StringBuilder("FROM Technology");
		StringBuilder countHql = new StringBuilder("SELECT COUNT(id) FROM Technology");
		StringBuilder condition = new StringBuilder(" WHERE customerCode = :customerCode");
		
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(FBaseConstants.CUSTOMER_CODE, session.getCustomerCode());
		
		if(technology != null){
			
			if (StringUtils.isNoneBlank(technology.getTitle())) {
				condition.append(" and title LIKE :title");
				params.put("title", "%" + technology.getTitle().trim() + "%");
			}
			
			if (StringUtils.isNoneBlank(technology.getTitleEn())) {
				condition.append(" and title LIKE :titleEn");
				params.put("titleEn", "%" + technology.getTitleEn().trim() + "%");
			}
			
			if (technology.getTechnologyType() != null){
				condition.append(" and technologyType =:technologyType");
				params.put("technologyType", technology.getTechnologyType());
			}
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
	public Long countTechnologys(Technology technology, FPage paging, String customerCode){
		StringBuilder countHql = new StringBuilder("SELECT COUNT(id) FROM Technology");
		StringBuilder condition = new StringBuilder(" WHERE customerCode = :customerCode");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put(FBaseConstants.CUSTOMER_CODE, customerCode);
		if(null != technology.getTechnologyType()){
			condition.append(" and technologyType =:technologyType");
			params.put("technologyType", technology.getTechnologyType());
		}

		if (null != technology.getTitle()) {
			condition.append(" AND title LIKE :title");
			params.put("title", "%" + technology.getTitle().trim() + "%");
		}
		
		if (null != paging) { 
			condition.append(" order by " + paging.getSortName() + " " + paging.getSortOrder());
		}
		
		countHql.append(condition);
		
		return super.count(countHql.toString(), params);
	}
	
	@Override
	public List<Technology> findAll(Technology technology, FPage paging, String customerCode) {
		StringBuilder hql = new StringBuilder("FROM Technology");
		StringBuilder countHql = new StringBuilder("SELECT COUNT(id) FROM Technology");
		StringBuilder condition = new StringBuilder(" WHERE customerCode = :customerCode");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put(FBaseConstants.CUSTOMER_CODE, customerCode);
		if(null != technology.getTechnologyType()){
			condition.append(" and technologyType =:technologyType");
			params.put("technologyType", technology.getTechnologyType());
		}

		if (null != technology.getTitle()) {
			condition.append(" AND title LIKE :title");
			params.put("title", "%" + technology.getTitle().trim() + "%");
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
	
	public List<Technology> getListForLoop(Technology example, FPage page, String customerCode){
		
		String countHql = "select count(t.id) from Technology t where t.customerCode=:customerCode and isOpen = 1";
		String hql = "from Technology where customerCode=:customerCode and isOpen = 1";
		Map<String,Object> paramsc = new HashMap<String, Object>();
		Map<String,Object> params = new HashMap<String, Object>();
		
		if(example.getTechnologyType() != null){
			hql += " and technologyType=:technologyType";
			params.put("technologyType", example.getTechnologyType());
			countHql += " and t.technologyType=:technologyType";
			paramsc.put("technologyType", example.getTechnologyType());
		}

		if(StringUtils.isNotEmpty(example.getTitle())){
			hql += " and title like:title";
			params.put("title", "%"+example.getTitle()+"%");
			countHql += " and title like:title";
			paramsc.put("title", "%"+example.getTitle()+"%");
			
		}
		
		if(StringUtils.isNotEmpty(example.getTitleEn())){
			hql += " and titleEn like:titleEn";
			params.put("titleEn", "%"+example.getTitleEn()+"%");
			countHql += " and titleEn like:titleEn";
			paramsc.put("titleEn", "%"+example.getTitleEn()+"%");
			
		}

		paramsc.put("customerCode", customerCode);
		Long total = this.count(countHql, paramsc);
		page.setRowCount(total.intValue()); 
		
		params.put("customerCode", customerCode);
		
		int totalPages = JQueryUtils.findTotalPages(total.intValue(), page.getPageSize());
		page.setPageCount(totalPages);
		
		return this.query(hql, params, page.getPageNumber(), page.getPageSize());
	}
	
	public List<Technology> getListForHot(Technology example, FPage page, String customerCode){
		
		String countHql = "select count(t.id) from Technology t where t.customerCode=:customerCode";
		String hql = "from Technology where customerCode=:customerCode";
		Map<String,Object> paramsc = new HashMap<String, Object>();
		Map<String,Object> params = new HashMap<String, Object>();
		
		hql += " and isOpen = 1 and isHot = 1";
		countHql += " and t.isOpen = 1  and t.isHot = 1";

		paramsc.put("customerCode", customerCode);
		Long total = this.count(countHql, paramsc);
		page.setRowCount(total.intValue()); 
		
		params.put("customerCode", customerCode);
		
		int totalPages = JQueryUtils.findTotalPages(total.intValue(), page.getPageSize());
		page.setPageCount(totalPages);
		
		return this.query(hql, params, page.getPageNumber(), page.getPageSize());
	}
	
	public boolean hasTechnologyUsed(Integer technologyType){
		String hql = "SELECT count(id) FROM Technology WHERE technologyType = :technologyType";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("technologyType", technologyType);
		boolean result = this.count(hql, params) > 0 ? true : false;
		return result;
	}
}
