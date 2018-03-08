package org.flywind.business.services.cms;

import java.util.List;

import org.flywind.business.entities.base.FSysInfo;
import org.flywind.business.entities.cms.Category;
import org.flywind.widgets.core.dao.FPage;

public interface CategoryService {

	public Long createCategory(Category category);
	
	public void updateCategory(Category category);
	
	public boolean deleteById(Long id);
	
	public Category getCategoryById(Long id);
	
	public List<Category> getAllCategory(Category category, FPage paging, FSysInfo session);
	
	public List<Category> getAllCategory(Category category, FPage paging, String customerCode);
}
