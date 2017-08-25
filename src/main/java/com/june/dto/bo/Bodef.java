/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.dto.bo;

import com.june.dto.BaseDto;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "bodef")
public class Bodef extends BaseDto {
	
	/*//alias
	public static final String TABLE_ALIAS = "Bodef";
	public static final String ALIAS_BOID = "boid";
		public static final String ALIAS_BOMARK = "bomark";
		public static final String ALIAS_BONAME = "boname";
		public static final String ALIAS_ENTITYID = "entityid";
		public static final String ALIAS_TID = "tid";
		public static final String ALIAS_TS = "ts";
		public static final String ALIAS_RS = "rs";
	*/
	
	//date formats
	

	//columns START
	private String boid;
	private String bomark;
	private String boname;
	private String fid;
	private String fname;
	private String fmark;
	private Integer tid;
	private String ts;
	private String rs;
	//columns END


	public Bodef(){
	}

	public Bodef(
		String boid
	){
		this.boid = boid;
	}

	

	public void setBoid(String value) {
		this.boid = value;
	}
	
	@Id 
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "boid", unique = true, nullable = false, insertable = true, updatable = true, length = 40)
	public String getBoid() {
		return this.boid;
	}
	
	@Column(name = "bomark", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getBomark() {
		return this.bomark;
	}
	
	public void setBomark(String value) {
		this.bomark = value;
	}
	
	@Column(name = "boname", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getBoname() {
		return this.boname;
	}
	
	public void setBoname(String value) {
		this.boname = value;
	}
	
	@Column(name = "fid", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	public String getFid() {
		return this.fid;
	}
	
	public void setFid(String value) {
		this.fid = value;
	}

	@Column(name = "fname", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	@Column(name = "fmark", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getFmark() {
		return fmark;
	}

	public void setFmark(String fmark) {
		this.fmark = fmark;
	}

	@Column(name = "tid", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public Integer getTid() {
		return this.tid;
	}
	
	public void setTid(Integer value) {
		this.tid = value;
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
			.append("Boid",getBoid())
			.append("Bomark",getBomark())
			.append("Boname",getBoname())
			.append("Fid",getFid()).append("Fname",getFname()).append("Fmark",getFmark())
			.append("Tid",getTid())
			.append("Ts",getTs())
			.append("Rs",getRs())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getBoid())
			.append(getBomark())
			.append(getBoname())
			.append(getFid()).append(getFname()).append(getFmark())
			.append(getTid())
			.append(getTs())
			.append(getRs())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Bodef == false) return false;
		if(this == obj) return true;
		Bodef other = (Bodef)obj;
		return new EqualsBuilder()
			.append(getBoid(),other.getBoid())
			.append(getBomark(),other.getBomark())
			.append(getBoname(),other.getBoname())
			.append(getFid(),other.getFid())
				.append(getFname(),other.getFname())
				.append(getFmark(),other.getFname())
			.append(getTid(),other.getTid())
			.append(getTs(),other.getTs())
			.append(getRs(),other.getRs())
			.isEquals();
	}
}

