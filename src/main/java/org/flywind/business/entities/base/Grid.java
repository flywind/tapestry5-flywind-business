package org.flywind.business.entities.base;

import java.io.Serializable;
import java.util.List;

public class Grid implements Serializable {
	
	private static final long serialVersionUID = 1970259824146576792L;
	
	private List<?> list;
	
	private Object row;
	
	private Integer code;
	
	private String message;
	
	/**
	 * 总记录数
	 */
	private int rows = 0;


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

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public Object getRow() {
		return row;
	}

	public void setRow(Object row) {
		this.row = row;
	}
	
}
