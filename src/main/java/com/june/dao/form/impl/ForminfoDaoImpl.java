/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.dao.form.impl;

import com.june.core.utils.BeanUtils;
import com.june.dao.BaseDao;
import com.june.dao.form.IForminfoDao;
import com.june.dto.form.Formfieldmeta;
import com.june.dto.form.Forminfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ForminfoDaoImpl extends BaseDao implements IForminfoDao {
	


	public List<Forminfo> getForminfoList() {
		return this.find("from Forminfo");
	}
	
			
	public Forminfo getForminfoById(String Fid) {
		return get(Forminfo.class, Fid);
	}
	
	
	public void saveForminfo(Forminfo dto) {
		//saveOrUpdate(dto);
		if (exists(Forminfo.class,dto.getFid())){
			update(dto);
		}else {
			save(dto);
		}
	}
	
			
	public void delForminfoById(String Fid) {
		executeSql("DELETE FROM Forminfo WHERE Fid=?", Fid);
	}


	public void buildTable(String fid){
		//创建实体表
		Forminfo fi = getForminfoById(fid);
		//默认 Bean 名称会是小写开头的非限定类名
		FormfieldmetaDaoImpl ffimpl = (FormfieldmetaDaoImpl)BeanUtils.getInstance().getBean("formfieldmetaDaoImpl");
		List<Formfieldmeta> lst = ffimpl.getFormfieldmetaByFid(fid);
		/*DROP TABLE IF EXISTS `formfieldmeta`;
		 CREATE TABLE `formfieldmeta` (
		 `ffid` varchar(40) NOT NULL,
		 `fid` varchar(40) DEFAULT NULL,
		 `note` varchar(100) DEFAULT NULL,
		 `ffname` varchar(50) DEFAULT NULL,
		 `required` varchar(1) DEFAULT NULL,
		 `datatype` varchar(30) DEFAULT NULL,
		 `datalength` int(11) DEFAULT NULL,
		 `defaultvalue` varchar(50) DEFAULT NULL,
		 `sn` int(11) DEFAULT NULL,
		 PRIMARY KEY (`ffid`)
		 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;*/
		StringBuilder builder = new StringBuilder();
		String tableName = "c_"+fi.getFname();
		//builder.append("DROP TABLE IF EXISTS `"+tableName+"`;");
		executeSql("DROP TABLE IF EXISTS `"+tableName+"`;");//存在就删除

		builder.append("CREATE TABLE `"+tableName+"` (");
		builder.append("`id` varchar(40) NOT NULL,");
		for (Formfieldmeta meta:lst) {
			builder.append("`"+meta.getFfname()+"` "+meta.getDatatype()+"("+meta.getDatalength()+")"+("1".equals(meta.getRequired())?" NOT NULL":" ")+ ((meta.getDefaultvalue()!=null)?" DEFAULT "+meta.getDefaultvalue():"")+",");
		}
		builder.append("`ts` varchar(30),").append("`rs` char(1),");
		builder.append("`proc_inst_id` varchar(64),");//流程实例ID
		builder.append(" PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
		System.out.println(builder.toString());
		executeSql(builder.toString());

		//更新主表状态
		String sql = "update forminfo set iscreate='1' WHERE fid=?";
		executeSql(sql,fid);
	}
}