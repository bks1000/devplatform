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
@Table(name = "formtype")
public class Formtype extends BaseDto {
	
	/*//alias
	public static final String TABLE_ALIAS = "Formtype";
	public static final String ALIAS_TID = "tid";
		public static final String ALIAS_TNAME = "tname";
	*/
	
	//date formats
	

	//columns START
	private Integer tid;
	private String tname;
	//columns END


	public Formtype(){
	}

	public Formtype(
		Integer tid
	){
		this.tid = tid;
	}

	

	public void setTid(Integer value) {
		this.tid = value;
	}
	
	@Id 
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "tid", unique = true, nullable = false, insertable = true, updatable = true, length = 10)
	public Integer getTid() {
		return this.tid;
	}
	
	@Column(name = "tname", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	public String getTname() {
		return this.tname;
	}
	
	public void setTname(String value) {
		this.tname = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this)
			.append("Tid",getTid())
			.append("Tname",getTname())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getTid())
			.append(getTname())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Formtype == false) return false;
		if(this == obj) return true;
		Formtype other = (Formtype)obj;
		return new EqualsBuilder()
			.append(getTid(),other.getTid())
			.append(getTname(),other.getTname())
			.isEquals();
	}
}

