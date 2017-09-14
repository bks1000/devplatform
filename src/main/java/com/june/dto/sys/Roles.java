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
@Table(name = "roles")
public class Roles extends BaseDto {
	
	/*//alias
	public static final String TABLE_ALIAS = "Roles";
	public static final String ALIAS_RID = "rid";
		public static final String ALIAS_RNAME = "rname";
		public static final String ALIAS_DESCRIPTION = "description";
		public static final String ALIAS_ISADMIN = "isadmin";
	*/
	
	//date formats
	

	//columns START
	private Integer rid;
	private String rname;
	private String description;
	private String isadmin;
	//columns END


	public Roles(){
	}

	public Roles(
		Integer rid
	){
		this.rid = rid;
	}

	

	public void setRid(Integer value) {
		this.rid = value;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rid", unique = true, nullable = false, insertable = true, updatable = true, length = 10)
	public Integer getRid() {
		return this.rid;
	}
	
	@Column(name = "rname", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getRname() {
		return this.rname;
	}
	
	public void setRname(String value) {
		this.rname = value;
	}
	
	@Column(name = "description", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String value) {
		this.description = value;
	}
	
	@Column(name = "isadmin", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getIsadmin() {
		return this.isadmin;
	}
	
	public void setIsadmin(String value) {
		this.isadmin = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this)
			.append("Rid",getRid())
			.append("Rname",getRname())
			.append("Description",getDescription())
			.append("Isadmin",getIsadmin())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getRid())
			.append(getRname())
			.append(getDescription())
			.append(getIsadmin())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Roles == false) return false;
		if(this == obj) return true;
		Roles other = (Roles)obj;
		return new EqualsBuilder()
			.append(getRid(),other.getRid())
			.append(getRname(),other.getRname())
			.append(getDescription(),other.getDescription())
			.append(getIsadmin(),other.getIsadmin())
			.isEquals();
	}
}

