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
@Table(name = "userrole")
public class Userrole extends BaseDto {
	
	/*//alias
	public static final String TABLE_ALIAS = "Userrole";
	public static final String ALIAS_UID = "uid";
		public static final String ALIAS_RID = "rid";
	*/
	
	//date formats

	public Userrole(){}

	private Integer uid;
	private Integer rid;

	@Id
	@Column(name = "rid", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	@Id
	@Column(name = "uid", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}
}

