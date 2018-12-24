package org.flywind.business.entities.base;

import java.io.Serializable;
import java.util.List;

public class Grid implements Serializable {
	
	private static final long serialVersionUID = 1970259824146576792L;
	
	private List<?> data;
	
	/**
	 * 总记录数
	 */
	private int rows = 0;

	/**
	 * @return the data
	 */
	public List<?> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List<?> data) {
		this.data = data;
	}

	/**
	 * @return the rowCount
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @param rowCount the rowCount to set
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	

	
	
	
}
