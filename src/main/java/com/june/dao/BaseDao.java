package com.june.dao;

import com.june.core.utils.HibernateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * DAO层基类
 * Created by lenovo on 2017/7/24.
 */
public class BaseDao extends  HibernateDao{

    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Autowired
    public NamedParameterJdbcTemplate namedJdbcTemplate;
}
