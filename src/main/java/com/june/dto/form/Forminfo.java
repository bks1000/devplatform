/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.dto.form;

import com.june.dto.BaseDto;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

@Entity
@Table(name = "forminfo")
public class Forminfo extends BaseDto {
	
	//alias
	/*public static final String TABLE_ALIAS = "Forminfo";
	public static final String ALIAS_FID = "fid";
	public static final String ALIAS_TID = "tid";
	public static final String ALIAS_FNAME = "fname";
	public static final String ALIAS_FMARK = "fmark";
	public static final String ALIAS_TS = "ts";
	public static final String ALIAS_RS = "rs";*/
	
	//date formats
	

	//columns START
	private String fid;
	private Integer tid;
	private String fname;
	private String fmark;
	private String ts;
	private String rs;
	private String iscreate;
	//columns END


	public Forminfo(){
	}

	public Forminfo(
		String fid
	){
		this.fid = fid;
	}

	

	public void setFid(String value) {
		this.fid = value;
	}
	
	@Id 
	//@GenericGenerator(name="systemUUID",strategy="uuid")
	//@GeneratedValue(generator = "systemUUID")
	@Column(name = "fid", unique = true, nullable = false, insertable = true, updatable = true, length = 40)
	public String getFid() {
		return this.fid;
	}
	
	@Column(name = "tid", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public Integer getTid() {
		return this.tid;
	}
	
	public void setTid(Integer value) {
		this.tid = value;
	}
	
	@Column(name = "fname", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getFname() {
		return this.fname;
	}
	
	public void setFname(String value) {
		this.fname = value;
	}
	
	@Column(name = "fmark", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getFmark() {
		return this.fmark;
	}
	
	public void setFmark(String value) {
		this.fmark = value;
	}

	@Column(name = "ts",unique = false,nullable = true,insertable = true,updatable = true,length = 30)
	public String getTs() {
		return ts;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

	@Column(name = "rs",unique = false,nullable = true,insertable = true,updatable = true,length = 1)
	public String getRs() {
		return rs;
	}

	public void setRs(String rs) {
		this.rs = rs;
	}

	@Column(name = "iscreate",unique = false,nullable = true,insertable = true,updatable = true,length = 1)
	public String getIscreate() {
		return iscreate;
	}

	public void setIscreate(String iscreate) {
		this.iscreate = iscreate;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Fid",getFid())
			.append("Tid",getTid())
			.append("Fname",getFname())
			.append("Fmark",getFmark()).append("ts",getTs()).append("rs",getRs()).append("iscreate",getIscreate())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getFid())
			.append(getTid())
			.append(getFname())
			.append(getFmark()).append(getTs()).append(getRs()).append(getIscreate())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Forminfo == false) return false;
		if(this == obj) return true;
		Forminfo other = (Forminfo)obj;
		return new EqualsBuilder()
			.append(getFid(),other.getFid())
			.append(getTid(),other.getTid())
			.append(getFname(),other.getFname())
			.append(getFmark(),other.getFmark()).
			 append(getTs(),other.getTs()).append(getRs(),other.getRs()).append(getIscreate(),other.getIscreate())
			.isEquals();
	}
}

