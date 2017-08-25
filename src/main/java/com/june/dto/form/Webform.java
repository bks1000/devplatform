/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.dto.form;

import com.june.dto.BaseDto;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "webform")
public class Webform extends BaseDto {
	
	/*//alias
	public static final String TABLE_ALIAS = "Webform";
	public static final String ALIAS_WID = "wid";
		public static final String ALIAS_TID = "tid";
		public static final String ALIAS_TNAME = "tname";
		public static final String ALIAS_TITLE = "title";
		public static final String ALIAS_FORMKEY = "formkey";
		public static final String ALIAS_WMARK = "wmark";
		public static final String ALIAS_BOID = "boid";
		public static final String ALIAS_FORMTYPE = "formtype";
		public static final String ALIAS_FORMHTML = "formhtml";
	*/
	
	//date formats
	

	//columns START
	private String wid;
	private Integer tid;
	private String tname;
	private String title;
	private String formkey;
	private String wmark;
	private String boid;
	private String boname;
	private String formtype;
	private String formhtml;
	//columns END


	public Webform(){
	}

	public Webform(
		String wid
	){
		this.wid = wid;
	}

	

	public void setWid(String value) {
		this.wid = value;
	}
	
	@Id 
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "wid", unique = true, nullable = false, insertable = true, updatable = true, length = 40)
	public String getWid() {
		return this.wid;
	}
	
	@Column(name = "tid", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public Integer getTid() {
		return this.tid;
	}
	
	public void setTid(Integer value) {
		this.tid = value;
	}
	
	@Column(name = "tname", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getTname() {
		return this.tname;
	}
	
	public void setTname(String value) {
		this.tname = value;
	}
	
	@Column(name = "title", unique = false, nullable = false, insertable = true, updatable = true, length = 100)
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String value) {
		this.title = value;
	}
	
	@Column(name = "formkey", unique = false, nullable = false, insertable = true, updatable = true, length = 40)
	public String getFormkey() {
		return this.formkey;
	}
	
	public void setFormkey(String value) {
		this.formkey = value;
	}
	
	@Column(name = "wmark", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getWmark() {
		return this.wmark;
	}
	
	public void setWmark(String value) {
		this.wmark = value;
	}
	
	@Column(name = "boid", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	public String getBoid() {
		return this.boid;
	}
	
	public void setBoid(String value) {
		this.boid = value;
	}

	@Column(name = "boname", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getBoname() {
		return boname;
	}

	public void setBoname(String boname) {
		this.boname = boname;
	}
	
	@Column(name = "formtype", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getFormtype() {
		return this.formtype;
	}
	
	public void setFormtype(String value) {
		this.formtype = value;
	}
	
	@Column(name = "formhtml", unique = false, nullable = true, insertable = true, updatable = true, length = 2000)
	public String getFormhtml() {
		return this.formhtml;
	}
	
	public void setFormhtml(String value) {
		this.formhtml = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this)
			.append("Wid",getWid())
			.append("Tid",getTid())
			.append("Tname",getTname())
			.append("Title",getTitle())
			.append("Formkey",getFormkey())
			.append("Wmark",getWmark())
			.append("Boid",getBoid())
			.append("Formtype",getFormtype())
			.append("Formhtml",getFormhtml())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getWid())
			.append(getTid())
			.append(getTname())
			.append(getTitle())
			.append(getFormkey())
			.append(getWmark())
			.append(getBoid())
			.append(getFormtype())
			.append(getFormhtml())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Webform == false) return false;
		if(this == obj) return true;
		Webform other = (Webform)obj;
		return new EqualsBuilder()
			.append(getWid(),other.getWid())
			.append(getTid(),other.getTid())
			.append(getTname(),other.getTname())
			.append(getTitle(),other.getTitle())
			.append(getFormkey(),other.getFormkey())
			.append(getWmark(),other.getWmark())
			.append(getBoid(),other.getBoid())
			.append(getFormtype(),other.getFormtype())
			.append(getFormhtml(),other.getFormhtml())
			.isEquals();
	}
}

