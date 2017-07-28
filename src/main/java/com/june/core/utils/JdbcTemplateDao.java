package com.june.core.utils;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.AbstractSqlParameterSource;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;

//import org.springframework.jdbc.support.lob.LobHandler;

/**
 * Created by lenovo on 2017/7/28.
 */
public class JdbcTemplateDao {
    private static Logger logger = Logger.getLogger(JdbcTemplateDao.class);

    private static JdbcTemplate jdbcTemplate = null;
    private static NamedParameterJdbcTemplate namedParameterJdbcTemplate = null;
    //private static LobHandler lobHandler = null;

    public static JdbcTemplate getJdbcTemplate() {
        Lock lock = new ReentrantLock();
        try {
            lock.lock();
            if (jdbcTemplate == null) {
                try {
                    jdbcTemplate = (JdbcTemplate) BeanUtils.getInstance().getBean("jdbcTemplate",JdbcTemplate.class);
                } catch (BeansException e) {
                }

                if (jdbcTemplate == null) {
                    DataSource ds = (DataSource) BeanUtils.getInstance().getBean("dataSource", DataSource.class);
                    if (ds == null) {
                        ds = (DataSource) BeanUtils.getInstance().getBean("dataSource",DataSource.class);
                    }
                    jdbcTemplate = new JdbcTemplate(ds);
                }
            }
        } finally {
            lock.unlock();
        }
        return jdbcTemplate;
    }

    public static NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        Lock lock = new ReentrantLock();
        try {
            lock.lock();
            if (namedParameterJdbcTemplate == null) {
                if (jdbcTemplate == null) {
                    jdbcTemplate = getJdbcTemplate();
                }
                namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
            }
        } finally {
            lock.unlock();
        }
        return namedParameterJdbcTemplate;
    }

    /*public static LobHandler getLobHandler() {
        Lock lock = new ReentrantLock();
        try {
            lock.lock();
            if (lobHandler == null) {
                lobHandler = (LobHandler) BeanUtils.getInstance().getBean("oracleLobHandler",LobHandler.class);
            }
        } finally {
            lock.unlock();
        }
        return lobHandler;
    }*/

    protected static BeanPropertySqlParameterSource paramBeanMapper( Object object) {
        return new BeanPropertySqlParameterSource(object);
    }

    protected static MapSqlParameterSource paramMapMapper(Map<String,Object> object) {
        return new MapSqlParameterSource(object);
    }

    @SuppressWarnings("unchecked")
    protected static AbstractSqlParameterSource paramMapper(Object object) {
        if ((object instanceof Map)) {
            return paramMapMapper((Map<String,Object>) object);
        }
        return paramBeanMapper(object);
    }

    protected static AbstractSqlParameterSource[] paramListMapper(List<Map<String, Object>> list) {
        if (list != null) {
            AbstractSqlParameterSource[] sources = new AbstractSqlParameterSource[list.size()];
            for (int i = 0; i < list.size(); i++) {
                Object item = list.get(i);
                sources[i] = paramMapper(item);
            }
            return sources;
        }
        return null;
    }

    protected static <T> ParameterizedBeanPropertyRowMapper<T> resultBeanMapper( Class<T> clazz) {
        return ParameterizedBeanPropertyRowMapper.newInstance(clazz);
    }

    public static String[] getColumnFields(String sql, Object... args){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<String> list = new ArrayList<String>();

        try {
            con = DataSourceUtils.getConnection(getJdbcTemplate().getDataSource());
            pstmt = con.prepareStatement(sql);
            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    pstmt.setObject(i + 1, args[i]);
                }
            }
            rs = pstmt.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                String name = meta.getColumnName(i);
                list.add(name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            try {
                rs.close();
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DataSourceUtils.releaseConnection(con, getJdbcTemplate().getDataSource());

        }
        return (String[]) list.toArray(new String[list.size()]);
    }

    public static int update(String sql, Object... args) {
        return getJdbcTemplate().update(sql, args);
    }

    public static int update(String sql, Map<String, Object> paramMap) {
        return getNamedParameterJdbcTemplate().update(sql, paramMapMapper(paramMap));
    }

    public static <T> int update(String sql,Class<T> clz, T paramBean) {
        return getNamedParameterJdbcTemplate().update(sql,paramBeanMapper(paramBean));
    }

    public static int[] batchUpdate(String... sql) {
        return getJdbcTemplate().batchUpdate(sql);
    }

    public static int[] batchUpdate(String sql, List<Map<String, Object>> paramlist) {
        return getNamedParameterJdbcTemplate().batchUpdate(sql,paramListMapper(paramlist));
    }

    public static List<Map<String, Object>> queryForList(String sql, Object... args){
        return JdbcTemplateDao.getJdbcTemplate().queryForList(sql, args);
    }

    public static List<Map<String, Object>> queryForList(String sql, Map<String, Object> paramMap){
        return getNamedParameterJdbcTemplate().queryForList(sql, paramMapMapper(paramMap));
    }

    public static Map<String, Object> queryForMap(String sql, Object... args) {
        List<Map<String, Object>> list = queryForList(sql, args);
        if ((list != null) && (list.size() > 0)) {
            return (Map<String, Object>) list.get(0);
        }
        return null;
    }

    public static Map<String, Object> queryForMap(String sql, Map<String,Object> args) {
        return getNamedParameterJdbcTemplate().queryForMap(sql, paramMapMapper(args));
    }

    public static <T> T queryForScalar(String sql, final Class<T> clz, Object... args) {
        return getJdbcTemplate().queryForObject(sql,clz, args);
    }

    public static <T> T queryForScalar(String sql, final Class<T> clz, Map<String, Object> paramMap) {
        return getNamedParameterJdbcTemplate().queryForObject(sql, paramMapMapper(paramMap), clz);
    }

    public static <T> T queryForBean(String sql, Class<T> clz, Object... objects) {
        List<T> list = queryForListBean(sql, clz, objects);
        if ((list != null) && (list.size() > 0)) {
            return list.get(0);
        }
        return null;
    }

    public static <T> List<T> queryForListBean(String sql, final Class<T> clz, Object... args){
        return getJdbcTemplate().query(sql, resultBeanMapper(clz), args);
    }

    public static Page queryForPage(String sql,Map<String, Object> paramMap){
        Page page = new Page();
        try {
            int pageSize = paramMap.get("pageSize")==null?10:Integer.parseInt(paramMap.get("pageSize").toString());
            int pageIndex = paramMap.get("pageIndex")==null?0:Integer.parseInt(paramMap.get("pageIndex").toString());
            int startIndex = pageIndex*pageSize;
            int endIndex = (pageIndex + 1)*pageSize;

            String pageSql = "select table__.* from (select rownum as rownum_,table_.* from ("+sql+") table_) table__ where table__.rownum_ > "+startIndex+" and table__.rownum_ <= "+endIndex;

            List<Map<String, Object>> data = queryForList(pageSql, paramMap);
            page.setData(data);

            pageSql = "select count(1) from ("+sql+")";
            int count = queryForScalar(pageSql, Integer.class, paramMap);
            page.setTotal(count);

            page.setCode(1);
        } catch (Exception e) {
            logger.error(e.getMessage());
            page.setCode(-1);
        }
        page.setMsg("");
        return page;
    }

    public static Page queryForPage(String sql,Map<String, Object> paramMap,Object... args){
        Page page = new Page();
        try {
            int pageSize = paramMap.get("pageSize")==null?10:Integer.parseInt(paramMap.get("pageSize").toString());
            int pageIndex = paramMap.get("pageIndex")==null?0:Integer.parseInt(paramMap.get("pageIndex").toString());
            int startIndex = pageIndex*pageSize;
            int endIndex = (pageIndex + 1)*pageSize;

            String pageSql = "select table__.* from (select rownum as rownum_,table_.* from ("+sql+") table_) table__ where table__.rownum_ > "+startIndex+" and table__.rownum_ <= "+endIndex;

            List<Map<String, Object>> data = queryForList(pageSql, args);
            page.setData(data);

            pageSql = "select count(1) from ("+sql+")";
            int count = queryForScalar(pageSql, Integer.class, args);
            page.setTotal(count);

            page.setCode(1);
        } catch (Exception e) {
            logger.error(e.getMessage());
            page.setCode(-1);
        }
        page.setMsg("");
        return page;
    }


    public static Page queryForPage(String sql,int pageSize,int pageIndex,Object... args){
        Page page = new Page();
        try {
            int startIndex = pageIndex*pageSize;
            int endIndex = (pageIndex + 1)*pageSize;

            String pageSql = "select table__.* from (select rownum as rownum_,table_.* from ("+sql+") table_) table__ where table__.rownum_ > "+startIndex+" and table__.rownum_ <= "+endIndex;

            List<Map<String, Object>> data = queryForList(pageSql, args);
            page.setData(data);

            pageSql = "select count(1) from ("+sql+")";
            int count = queryForScalar(pageSql, Integer.class, args);
            page.setTotal(count);

            page.setCode(1);
        } catch (Exception e) {
            logger.error(e.getMessage());
            page.setCode(-1);
        }
        page.setMsg("");
        return page;
    }

    /**
     * 查看SQL
     * @param sql
     * @param map
     * @return String
     * @exception
     * @since  1.0.0
     */
    public static String getSql(String sql,Map<String, Object> map) {
        for ( Map.Entry<String, Object> entry : map.entrySet()) {
            sql = Matcher.quoteReplacement(sql).replaceAll(":"+entry.getKey().toString(), entry.getValue().toString());
        }
        return sql;
    }

    public static String getSql(String sql,Object... params) {
        int j = 0;
        for(int i = 0;i < sql.length();i++){
            if(sql.charAt(i) == '?'){
                sql = sql.replaceFirst("\\?", "'" + String.valueOf(params[j++]) + "'");//替换结束后再给本身赋值
            }
        }
        logger.info(sql);
        return sql;
    }
}
