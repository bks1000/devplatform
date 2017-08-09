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
@Table(name = "formfieldmeta")
public class Formfieldmeta extends BaseDto {
	
	//alias
	/*public static final String TABLE_ALIAS = "Formfieldmeta";
	public static final String ALIAS_FFID = "ffid";
	public static final String ALIAS_FID = "fid";
	public static final String ALIAS_NOTE = "note";
	public static final String ALIAS_FFNAME = "ffname";
	public static final String ALIAS_REQUIRED = "required";
	public static final String ALIAS_DATATYPE = "datatype";
	public static final String ALIAS_DATALENGTH = "datalength";
	public static final String ALIAS_DEFAULTVALUE = "defaultvalue";
	public static final String ALIAS_SN = "sn";*/
	
	//date formats
	

	//columns START
	private String ffid;
	private String fid;
	private String note;
	private String ffname;
	private String required;
	private String datatype;
	private Integer datalength;
	private String defaultvalue;
	private Integer sn;
	//columns END


	public Formfieldmeta(){
	}

	public Formfieldmeta(
		String ffid
	){
		this.ffid = ffid;
	}

	

	public void setFfid(String value) {
		this.ffid = value;
	}
	
	@Id 
	//@GenericGenerator(name="systemUUID",strategy="uuid")
	//@GeneratedValue(generator = "systemUUID")
	@Column(name = "ffid", unique = true, nullable = false, insertable = true, updatable = true, length = 40)
	public String getFfid() {
		return this.ffid;
	}
	
	@Column(name = "fid", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	public String getFid() {
		return this.fid;
	}
	
	public void setFid(String value) {
		this.fid = value;
	}
	
	@Column(name = "note", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getNote() {
		return this.note;
	}
	
	public void setNote(String value) {
		this.note = value;
	}
	
	@Column(name = "ffname", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getFfname() {
		return this.ffname;
	}
	
	public void setFfname(String value) {
		this.ffname = value;
	}
	
	@Column(name = "required", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getRequired() {
		return this.required;
	}
	
	public void setRequired(String value) {
		this.required = value;
	}
	
	@Column(name = "datatype", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getDatatype() {
		return this.datatype;
	}
	
	public void setDatatype(String value) {
		this.datatype = value;
	}
	
	@Column(name = "datalength", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public Integer getDatalength() {
		return this.datalength;
	}
	
	public void setDatalength(Integer value) {
		this.datalength = value;
	}
	
	@Column(name = "defaultvalue", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getDefaultvalue() {
		return this.defaultvalue;
	}
	
	public void setDefaultvalue(String value) {
		this.defaultvalue = value;
	}
	
	@Column(name = "sn", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public Integer getSn() {
		return this.sn;
	}
	
	public void setSn(Integer value) {
		this.sn = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this)
			.append("Ffid",getFfid())
			.append("Fid",getFid())
			.append("Note",getNote())
			.append("Ffname",getFfname())
			.append("Required",getRequired())
			.append("Datatype",getDatatype())
			.append("Datalength",getDatalength())
			.append("Defaultvalue",getDefaultvalue())
			.append("Sn",getSn())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getFfid())
			.append(getFid())
			.append(getNote())
			.append(getFfname())
			.append(getRequired())
			.append(getDatatype())
			.append(getDatalength())
			.append(getDefaultvalue())
			.append(getSn())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Formfieldmeta == false) return false;
		if(this == obj) return true;
		Formfieldmeta other = (Formfieldmeta)obj;
		return new EqualsBuilder()
			.append(getFfid(),other.getFfid())
			.append(getFid(),other.getFid())
			.append(getNote(),other.getNote())
			.append(getFfname(),other.getFfname())
			.append(getRequired(),other.getRequired())
			.append(getDatatype(),other.getDatatype())
			.append(getDatalength(),other.getDatalength())
			.append(getDefaultvalue(),other.getDefaultvalue())
			.append(getSn(),other.getSn())
			.isEquals();
	}
}

