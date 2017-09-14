/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.dto.sys;

import com.june.dto.BaseDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rolemenu")
public class Rolemenu extends BaseDto {
	
	/*//alias
	public static final String TABLE_ALIAS = "Rolemenu";
	public static final String ALIAS_RID = "rid";
		public static final String ALIAS_MID = "mid";
	*/
	
	//date formats

	public Rolemenu(){}

	private Integer rid;
	private String mid;

	@Id
	@Column(name = "rid", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}
	@Id
	@Column(name = "mid", unique = false, nullable = false, insertable = true, updatable = true, length = 40)
	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}
}

