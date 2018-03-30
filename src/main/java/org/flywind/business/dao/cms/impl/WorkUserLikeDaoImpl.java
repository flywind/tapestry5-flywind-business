package org.flywind.business.dao.cms.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.flywind.business.common.constants.FSysConstants;
import org.flywind.business.dao.base.AbstractFBaseDao;
import org.flywind.business.dao.cms.WorkUserLikeDao;
import org.flywind.business.entities.cms.WorkUserLike;
import org.flywind.widgets.core.dao.FPage;
import org.springframework.stereotype.Repository;

@Repository
public class WorkUserLikeDaoImpl extends AbstractFBaseDao<WorkUserLike> implements WorkUserLikeDao {

	@Override
	public boolean delWorkUserLikeByUserId(Long userId) {
		String deleteHql ="delete from WorkUserLike where userId=:userId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(FSysConstants.USER_ID, userId);
		int count=super.executeHql(deleteHql, params);
		return count > 0 ? true : false;
	}
	
	@Override
	public boolean deleteWorkUserLike(Long workId, Long userId){
		String hql = "delete from WorkUserLike where userId=:userId and workId=:workId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(FSysConstants.USER_ID, userId);
		params.put("workId", workId);
		int count=super.executeHql(hql, params);
		return count > 0 ? true : false;
	}
	
	@Override
	public boolean checkWorkLikeExist(Long workId, Long userId){
		String hql = "SELECT COUNT(id) FROM WorkUserLike where userId=:userId and workId=:workId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(FSysConstants.USER_ID, userId);
		params.put("workId", workId);
		Long count=super.count(hql, params);
		return count > 0 ? true : false;
	}
	
	public Long countWorkUserLikeByWorkId(Long workId){
		String hql = "SELECT COUNT(id) FROM WorkUserLike where workId=:workId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("workId", workId);
		Long count=super.count(hql, params);
		return count;
	}
	
	@Override
	public List<WorkUserLike> getWorkUserLikeByUserId(Long userId, FPage pageingEntity){
		String countHql = "SELECT COUNT(id) FROM WorkUserLike where userId=:userId";
		String hql = "FROM WorkUserLike where userId=:userId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(FSysConstants.USER_ID, userId);
		Long count = super.count(countHql, params);
		pageingEntity.setRowCount(count.intValue());
		return super.query(hql, params,pageingEntity.getPageNumber(), pageingEntity.getPageSize());
	}
}
