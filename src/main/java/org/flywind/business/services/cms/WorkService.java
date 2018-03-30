package org.flywind.business.services.cms;

import java.util.List;

import org.flywind.business.entities.base.FSysInfo;
import org.flywind.business.entities.cms.Work;
import org.flywind.business.entities.cms.WorkUserLike;
import org.flywind.widgets.core.dao.FPage;

/**
 * <p>Work Service</p>
 * 
 * @author flywind(飞风)
 * @date 2016年6月22日
 * @网址：http://www.flywind.org
 * @QQ技术群：41138107(人数较多最好先加这个)或33106572
 * @since 1.0
 */
public interface WorkService {
	
	public Long create(Work o);
	
	public void update(Work o);
	
	public void delete(Work o);
	
	public boolean deleteById(Long id);
	
	public void deleteByIds(String ids);
	
	public Work getById(Long id);

	public List<Work> findAll(Work example, FPage paging, FSysInfo session, String lanage);
	
	public List<Work> findAll(Work example, FPage paging, String customerCode);
	
	public List<Work> findAllPictures(Work example, FPage paging, Long userId, String customerCode);
	
	public List<Work> getListForLoop(Work example, FPage paging, String customerCode);
	
	public List<Work> getListForHot(Work example, FPage paging, String customerCode);
	
	public List<Work> getAllList(Work example, FPage page, String customerCode);
	
	public boolean createWorkUserLike(WorkUserLike o);
	
	public Long getAllLikesByWorkId(Long workId);
	
	public List<WorkUserLike> getWorkLikesByUserId(Long userId, FPage page, String customerCode);
}
