package org.flywind.business.entities.cms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="td_c_work_user_like")
public class WorkUserLike {

	/**
	 * Id
	 */
	private Long id;

	/**
	 * User id
	 */
	private Long userId;
	
	/**
	 * Role id
	 */
	private Long workId;
	
	private Work likeWork;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
	public Long getId(){
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "user_id", nullable = false)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "work_id", nullable = false)
	public Long getWorkId() {
		return workId;
	}

	public void setWorkId(Long workId) {
		this.workId = workId;
	}

	@Transient
	public Work getLikeWork() {
		return likeWork;
	}

	public void setLikeWork(Work likeWork) {
		this.likeWork = likeWork;
	}
}
