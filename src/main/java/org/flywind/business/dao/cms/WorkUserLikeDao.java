package org.flywind.business.dao.cms;

import java.util.List;

import org.flywind.business.dao.base.FBaseDao;
import org.flywind.business.entities.cms.WorkUserLike;
import org.flywind.widgets.core.dao.FPage;

public interface WorkUserLikeDao extends FBaseDao<WorkUserLike> {

	public boolean delWorkUserLikeByUserId(Long userId);
	
	public boolean deleteWorkUserLike(Long workId, Long userId);
	
	public boolean checkWorkLikeExist(Long workId, Long userId);
	
	public Long countWorkUserLikeByWorkId(Long workId);
	
	public List<WorkUserLike> getWorkUserLikeByUserId(Long userId, FPage pageingEntity);
}
