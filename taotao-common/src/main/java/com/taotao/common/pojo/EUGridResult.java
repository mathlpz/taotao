package com.taotao.common.pojo;

import java.util.List;


public class EUGridResult {
	
	private long total;
	private List<?> rows;
	
	public EUGridResult(){
		super();
	}
	
	public EUGridResult(long total, List<?> list) {
		this.total = total;
		this.rows = list;
	}
	
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
	
	
}
