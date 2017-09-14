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
@Table(name = "users")
public class Users extends BaseDto {
	
	/*//alias
	public static final String TABLE_ALIAS = "Users";
	public static final String ALIAS_UID = "uid";
		public static final String ALIAS_UNAME = "uname";
		public static final String ALIAS_NICKNAME = "nickname";
		public static final String ALIAS_PWD = "pwd";
		public static final String ALIAS_EMAIL = "email";
		public static final String ALIAS_MOBILE = "mobile";
		public static final String ALIAS_ENABLE = "enable";
		public static final String ALIAS_TS = "ts";
		public static final String ALIAS_RS = "rs";
		public static final String ALIAS_ORGID = "orgid";
	*/
	
	//date formats
	

	//columns START
	private Integer uid;
	private String uname;
	private String nickname;
	private String pwd;
	private String email;
	private String mobile;
	private String enable;
	private String ts;
	private String rs;
	private Integer orgid;
	//columns END


	public Users(){
	}

	public Users(
		Integer uid
	){
		this.uid = uid;
	}

	

	public void setUid(Integer value) {
		this.uid = value;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uid", unique = true, nullable = false, insertable = true, updatable = true, length = 10)
	public Integer getUid() {
		return this.uid;
	}
	
	@Column(name = "uname", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getUname() {
		return this.uname;
	}
	
	public void setUname(String value) {
		this.uname = value;
	}
	
	@Column(name = "nickname", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getNickname() {
		return this.nickname;
	}
	
	public void setNickname(String value) {
		this.nickname = value;
	}
	
	@Column(name = "pwd", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getPwd() {
		return this.pwd;
	}
	
	public void setPwd(String value) {
		this.pwd = value;
	}
	
	@Column(name = "email", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String value) {
		this.email = value;
	}
	
	@Column(name = "mobile", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getMobile() {
		return this.mobile;
	}
	
	public void setMobile(String value) {
		this.mobile = value;
	}
	
	@Column(name = "enable", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getEnable() {
		return this.enable;
	}
	
	public void setEnable(String value) {
		this.enable = value;
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
	
	@Column(name = "orgid", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public Integer getOrgid() {
		return this.orgid;
	}
	
	public void setOrgid(Integer value) {
		this.orgid = value;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Uid",getUid())
			.append("Uname",getUname())
			.append("Nickname",getNickname())
			.append("Pwd",getPwd())
			.append("Email",getEmail())
			.append("Mobile",getMobile())
			.append("Enable",getEnable())
			.append("Ts",getTs())
			.append("Rs",getRs())
			.append("Orgid",getOrgid())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getUid())
			.append(getUname())
			.append(getNickname())
			.append(getPwd())
			.append(getEmail())
			.append(getMobile())
			.append(getEnable())
			.append(getTs())
			.append(getRs())
			.append(getOrgid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Users == false) return false;
		if(this == obj) return true;
		Users other = (Users)obj;
		return new EqualsBuilder()
			.append(getUid(),other.getUid())
			.append(getUname(),other.getUname())
			.append(getNickname(),other.getNickname())
			.append(getPwd(),other.getPwd())
			.append(getEmail(),other.getEmail())
			.append(getMobile(),other.getMobile())
			.append(getEnable(),other.getEnable())
			.append(getTs(),other.getTs())
			.append(getRs(),other.getRs())
			.append(getOrgid(),other.getOrgid())
			.isEquals();
	}
}

