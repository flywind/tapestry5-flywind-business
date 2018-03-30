package org.flywind.business.services.cms.impl;

import java.util.List;

import org.flywind.business.common.constants.FLogConstants;
import org.flywind.business.common.utils.FBaseUtil;
import org.flywind.business.common.utils.FLog;
import org.flywind.business.dao.cms.CategoryDao;
import org.flywind.business.dao.cms.WorkDao;
import org.flywind.business.dao.cms.WorkUserLikeDao;
import org.flywind.business.entities.base.FSysInfo;
import org.flywind.business.entities.cms.Category;
import org.flywind.business.entities.cms.Work;
import org.flywind.business.entities.cms.WorkUserLike;
import org.flywind.business.services.cms.WorkService;
import org.flywind.widgets.core.dao.FPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkServiceImpl implements WorkService {

	@Autowired
	private WorkDao exampleDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private WorkUserLikeDao workUserLikeDao;
	
	@FLog(infokey=FLogConstants.CREATE_WORK, optype = FLogConstants.CREATE)
	public Long create(Work o){
		return exampleDao.save(o);
	}
	
	@FLog(infokey=FLogConstants.UPDATE_WORK, optype = FLogConstants.UPDATE)
	public void update(Work o){
		exampleDao.update(o);
	}
	
	@FLog(infokey=FLogConstants.DELETE_WORK, optype = FLogConstants.DELETE)
	public void delete(Work o){
		exampleDao.delete(o);
	}
	
	@FLog(infokey=FLogConstants.DELETE_WORK, optype = FLogConstants.DELETE)
	public boolean deleteById(Long id){
		return exampleDao.deleteById(Work.class, id);
	}
	
	@FLog(infokey=FLogConstants.DELETE_WORK, optype = FLogConstants.DELETE)
	public void deleteByIds(String ids){
		List<Long> list = FBaseUtil.idsToList(ids);
		for(Long id : list){
			exampleDao.deleteById(Work.class, id);
		}
	}
	
	//@FLog(infokey=FLogConstants.QUERY_WORK)
	public Work getById(Long id){
		return exampleDao.getById(Work.class, id);
	}
	
	//@FLog(infokey=FLogConstants.QUERY_WORK)
	public List<Work> findAll(Work example, FPage paging, FSysInfo session, String lanage){
		List<Work> list = exampleDao.findAll(example, paging, session);
		
		FPage p = new FPage();
		p.setPageSize(50);
		Category category = new Category();
		
		for(Work t : list){
			List<Category> categorys = categoryDao.getAllCategory(category, p, session.getCustomerCode());
			for(Category s : categorys){
				if(s.getId().intValue() == t.getType()){
					if("zh-cn".equalsIgnoreCase(lanage)){
						t.setTypeName(s.getName());
					}else{
						t.setTypeName(s.getName());
					}
					
				}
			}
		}
		
		return list;
	}
	
	//@FLog(infokey=FLogConstants.QUERY_WORK)
	public List<Work> findAll(Work example, FPage paging, String customerCode){
		return exampleDao.findAll(example, paging, customerCode);
	}
	
	public List<Work> findAllPictures(Work example, FPage paging, Long userId, String customerCode){
		List<Work> works = exampleDao.findAll(example, paging, customerCode);
		if(userId != 0L){
			for(Work w : works){
				boolean checkWorkLikeExist = workUserLikeDao.checkWorkLikeExist(w.getId(), userId);
				Long totalLikes = workUserLikeDao.countWorkUserLikeByWorkId(w.getId());
				if(checkWorkLikeExist){
					w.setIsLike(Boolean.TRUE);
				}
				if(totalLikes > 0L){
					w.setTotalLikes(totalLikes);
				}
			}
		}else{
			for(Work w : works){
				Long totalLikes = workUserLikeDao.countWorkUserLikeByWorkId(w.getId());
				if(totalLikes > 0L){
					w.setTotalLikes(totalLikes);
				}
			}
		}
		return works;
	}
	
	//@FLog(infokey=FLogConstants.QUERY_WORK)
	public List<Work> getListForLoop(Work example, FPage page, String customerCode){
		return exampleDao.getListForLoop(example, page, customerCode);
	}
	
	//@FLog(infokey=FLogConstants.QUERY_WORK)
	public List<Work> getListForHot(Work example, FPage page, String customerCode){
		return exampleDao.getListForHot(example, page, customerCode);
	}
	
	public List<Work> getAllList(Work example, FPage page, String customerCode){
		return exampleDao.getAllList(example, page, customerCode);
	}
	
	public boolean createWorkUserLike(WorkUserLike o){
		boolean checkExitLike = workUserLikeDao.checkWorkLikeExist(o.getWorkId(), o.getUserId());
		if(checkExitLike){
			workUserLikeDao.deleteWorkUserLike(o.getWorkId(), o.getUserId());
			return false;
		}
		workUserLikeDao.save(o);
		return true;
	}
	
	public Long getAllLikesByWorkId(Long workId){
		return workUserLikeDao.countWorkUserLikeByWorkId(workId);
	}
	
	@Override
	public List<WorkUserLike> getWorkLikesByUserId(Long userId, FPage page, String customerCode){
		List<WorkUserLike> wuls = workUserLikeDao.getWorkUserLikeByUserId(userId, page);
		for(WorkUserLike wul : wuls){
			Work w = exampleDao.getById(Work.class, wul.getWorkId());
			w.setIsLike(Boolean.TRUE);
			wul.setLikeWork(w);
		}
		return wuls;
	}
}
