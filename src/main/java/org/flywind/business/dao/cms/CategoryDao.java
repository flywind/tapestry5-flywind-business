package org.flywind.business.dao.cms;

import java.util.List;

import org.flywind.business.dao.base.FBaseDao;
import org.flywind.business.entities.base.FSysInfo;
import org.flywind.business.entities.cms.Category;
import org.flywind.widgets.core.dao.FPage;

public interface CategoryDao extends FBaseDao<Category> {

	public List<Category> getAllCategory(Category category, FPage paging, FSysInfo session);
	
	public List<Category> getAllCategory(Category category, FPage paging, String customerCode);
}
