package org.flywind.business.entities.cms;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.flywind.business.entities.base.FBase;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="td_c_category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="javaClassName")
public class Category extends FBase {

	private String name;
	
	private Integer type;
	
	private String page;
	
	/**
	 * 分类名称
	 * @return the name
	 */
	@Column(name = "name", nullable = true, length = 200)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 页面名字 t:page
	 * @return
	 */
	@Column(name = "page", nullable = true, length = 200)
	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	/**
	 *	分类
	 * @return the type
	 * return 1: Technology分类
	 * return 2: Work分类
	 */
	@Column(name = "type", nullable = false, length = 3)
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
