/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.dao.bo;

import com.june.dto.bo.Bodef;

import java.util.List;

public interface IBodefDao {
	
	
	public List<Bodef> getBodefList();
	
			
	public Bodef getBodefById(String Boid);
	
	
	public void saveBodef(Bodef dto);
	
			
	public void delBodefById(String Boid);
	
}