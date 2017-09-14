/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.dto.sys;

import com.june.dto.BaseDto;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

@Entity
@Table(name = "organization")
public class Organization extends BaseDto {
	
	/*//alias
	public static final String TABLE_ALIAS = "Organization";
	public static final String ALIAS_ORGID = "orgid";
		public static final String ALIAS_ORGNAME = "orgname";
		public static final String ALIAS_ENABLED = "enable";
		public static final String ALIAS_PORGID = "porgid";
		public static final String ALIAS_TS = "ts";
		public static final String ALIAS_RS = "rs";
	*/
	
	//date formats
	

	//columns START
	private Integer orgid;
	private String orgname;
	private String enable;
	private Integer porgid;
	private String ts;
	private String rs;
	//columns END


	public Organization(){
	}

	public Organization(
		Integer orgid
	){
		this.orgid = orgid;
	}

	

	public void setOrgid(Integer value) {
		this.orgid = value;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orgid", unique = true, nullable = false, insertable = true, updatable = true, length = 10)
	public Integer getOrgid() {
		return this.orgid;
	}
	
	@Column(name = "orgname", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getOrgname() {
		return this.orgname;
	}
	
	public void setOrgname(String value) {
		this.orgname = value;
	}
	
	@Column(name = "enable", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getEnable() {
		return this.enable;
	}
	
	public void setEnable(String value) {
		this.enable = value;
	}
	
	@Column(name = "porgid", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public Integer getPorgid() {
		return this.porgid;
	}
	
	public void setPorgid(Integer value) {
		this.porgid = value;
	}
	
	@Column(name = "ts", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getTs() {
		return this.ts;
	}
	
	public void setTs(String value) {
		this.ts = value;
	}
	
	@Column(name = "rs", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getRs() {
		return this.rs;
	}
	
	public void setRs(String value) {
		this.rs = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this)
			.append("Orgid",getOrgid())
			.append("Orgname",getOrgname())
			.append("Enabled",getEnable())
			.append("Porgid",getPorgid())
			.append("Ts",getTs())
			.append("Rs",getRs())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getOrgid())
			.append(getOrgname())
			.append(getEnable())
			.append(getPorgid())
			.append(getTs())
			.append(getRs())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Organization == false) return false;
		if(this == obj) return true;
		Organization other = (Organization)obj;
		return new EqualsBuilder()
			.append(getOrgid(),other.getOrgid())
			.append(getOrgname(),other.getOrgname())
			.append(getEnable(),other.getEnable())
			.append(getPorgid(),other.getPorgid())
			.append(getTs(),other.getTs())
			.append(getRs(),other.getRs())
			.isEquals();
	}
}

