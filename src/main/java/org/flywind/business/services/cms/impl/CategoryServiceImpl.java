package org.flywind.business.services.cms.impl;

import java.util.List;

import org.flywind.business.dao.cms.CategoryDao;
import org.flywind.business.dao.cms.TechnologyDao;
import org.flywind.business.entities.base.FSysInfo;
import org.flywind.business.entities.cms.Category;
import org.flywind.business.services.cms.CategoryService;
import org.flywind.widgets.core.dao.FPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private TechnologyDao technologyDao;
	
	public Long createCategory(Category category){
		return categoryDao.save(category);
	}
	
	public void updateCategory(Category category){
		categoryDao.saveOrUpdate(category);
	}
	
	public Category getCategoryById(Long id){
		return categoryDao.getById(Category.class, id);
	}
	
	public boolean deleteById(Long id){
		boolean result = technologyDao.hasTechnologyUsed(id.intValue());
		if(!result){
			categoryDao.deleteById(Category.class, id);
			return true;
		}
		return false;
	}
	
	public List<Category> getAllCategory(Category category, FPage paging, FSysInfo session){
		return categoryDao.getAllCategory(category, paging, session);
	}
	
	public List<Category> getAllCategory(Category category, FPage paging, String customerCode){
		return categoryDao.getAllCategory(category, paging, customerCode);
	}
}
