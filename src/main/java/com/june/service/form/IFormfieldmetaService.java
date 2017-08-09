/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.service.form;

import com.june.dto.form.Formfieldmeta;

import java.util.List;

public interface IFormfieldmetaService{
	
	public List<Formfieldmeta> getFormfieldmetaList();
	
			
	public Formfieldmeta getFormfieldmetaById(String Ffid);

	public List<Formfieldmeta> getFormfieldmetaByFid(String fid);
	
	public void saveFormfieldmeta(Formfieldmeta dto);
	
			
	public void delFormfieldmetaById(String Ffid);
	
}