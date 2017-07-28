/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.dto.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.june.dto.BaseDto;
import org.hibernate.annotations.GenericGenerator;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "menu")
public class Menu extends BaseDto {
	
	//alias
	public static final String TABLE_ALIAS = "Menu";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAME = "name";
	public static final String ALIAS_PARENTID="parentid";
	public static final String ALIAS_IDX = "idx";
	public static final String ALIAS_URL = "url";
	public static final String ALIAS_TS = "ts";
	public static final String ALIAS_RS="rs";
	public static final String ALIAS_REMARK = "remark";
	
	//date formats
	

	//columns START
	private String id;
	private String name;
	private String parentid;
	private Integer idx;
	private String url;
	private String ts;
	private String rs;
	private String remark;
	//columns END


	public Menu(){
	}

	public Menu(String id){
		this.id = id;
	}

	public void setId(String value) {
		this.id = value;
	}
	
	@Id 
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "id", unique = true, nullable = false, insertable = true, updatable = true, length = 40)
	public String getId() {
		return this.id;
	}
	
	@Column(name = "name", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getName() {
		return this.name;
	}
	
	public void setName(String value) {
		this.name = value;
	}

	@Column(name = "parentid", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	
	@Column(name = "idx", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public Integer getIdx() {
		return this.idx;
	}
	
	public void setIdx(Integer value) {
		this.idx = value;
	}
	
	@Column(name = "url", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getUrl() {
		return this.url;
	}
	
	public void setUrl(String value) {
		this.url = value;
	}

	@Column(name = "ts", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getTs() {
		return ts;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

	@Column(name = "rs", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getRs() {
		return rs;
	}

	public void setRs(String rs) {
		this.rs = rs;
	}
	
	@Column(name = "remark", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String value) {
		this.remark = value;
	}


	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("Name",getName()).append("Parentid",getParentid())
			.append("Idx",getIdx())
			.append("Url",getUrl())
			.append("Rs",getRs()).append("Ts",getTs())
			.append("Remark",getRemark())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getName()).append(getParentid())
			.append(getIdx())
			.append(getUrl())
			.append(getRs()).append(getTs())
			.append(getRemark())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Menu == false) return false;
		if(this == obj) return true;
		Menu other = (Menu)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getName(),other.getName()).append(getParentid(),other.getParentid())
			.append(getIdx(),other.getIdx())
			.append(getUrl(),other.getUrl())
			.append(getTs(),other.getTs()).append(getRs(),other.getRs())
			.append(getRemark(),other.getRemark())
			.isEquals();
	}
}

