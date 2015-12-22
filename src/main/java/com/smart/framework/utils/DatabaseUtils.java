package com.smart.framework.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @package org.smart4j.utils
 * @file DatabaseUtils.java
 * @description Database manipulation Utils
 * @author   nilu
 * @version 1.0
 *
 */
public class DatabaseUtils {
    /**
     * 日志组件
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(DatabaseUtils.class);
    
    /**
     * 数据库连接池
     */
    private final static BasicDataSource DATA_SOURCE;
    private final static QueryRunner QUERY_RUNNER;
    private final static ThreadLocal<Connection> LOCAL_CONNECTION;
    
    static {
        QUERY_RUNNER = new QueryRunner();
        LOCAL_CONNECTION = new ThreadLocal<Connection>();
        
        Properties props = PropsUtils.loadProps("config.properties");
        final String driver = PropsUtils.getString(props, "jdbc.driver");
        final String url = PropsUtils.getString(props, "jdbc.url");
        final String username = PropsUtils.getString(props, "jdbc.username");
        final String password = PropsUtils.getString(props, "jdbc.password");
        
        DATA_SOURCE = new BasicDataSource();
        DATA_SOURCE.setDriverClassName(driver);
        DATA_SOURCE.setUrl(url);
        DATA_SOURCE.setUsername(username);
        DATA_SOURCE.setPassword(password);
    }
    
    /**
     * @description get connection
     * @time 2015-12-14下午04:59:45
     * @return
     * @author   nilu
     */
    public static Connection getConnection() {
        Connection conn = LOCAL_CONNECTION.get();
        if(GyUtils.isNull(conn)) {
            try {
                conn = DATA_SOURCE.getConnection();
                LOCAL_CONNECTION.set(conn);
            } catch (SQLException e) {
                LOGGER.error("can not get connection!", e);
            }
        }
        return conn;
    }
    
    /**
     * @description query entity List
     * @time 2015-12-16下午09:21:41
     * @param <T>
     * @param sql
     * @param clazz
     * @param args
     * @return
     * @author   nilu
     */
    public static <T> List<T> queryEntityList(String sql, Class<T> clazz, Object... args) {
        Connection conn = getConnection();
        List<T> result = null;
        try {
            result = QUERY_RUNNER.query(conn, sql, new BeanListHandler<T>(clazz), args);
        } catch (SQLException e) {
            LOGGER.error("dao query EntityList failure!", e);
            throw new RuntimeException(e);
        }
        return result;
    }
    
    /**
     * 
     * @description query 
     * @time 2015-12-16下午09:21:59
     * @param sql
     * @param args
     * @return
     * @author   nilu
     */
    public static List<Map<String, Object>> executeQuery(String sql, Object... args) {
        Connection conn = null;
        List<Map<String, Object>> queryResult = null;
        try {
            conn = getConnection();
            queryResult = QUERY_RUNNER.query(conn, sql, new MapListHandler());
        } catch(SQLException e) {
            LOGGER.error("dao query failure", e);
            throw new RuntimeException(e);
        }
        return queryResult;
    }
    
    /**
     * @description update
     * @time 2015-12-16下午09:22:11
     * @param sql
     * @param args
     * @return
     * @author   nilu
     */
    public static int executeUpdate(String sql, Object... args) {
        Connection conn = null;
        int recode = -1;
        try {
            conn = getConnection();
            recode = QUERY_RUNNER.update(conn, sql, args);
        } catch(SQLException e) {
            LOGGER.error("dao update failure", e);
            throw new RuntimeException(e);
        }
        return recode;
    }
    
    /**
     * 
     * @description insert entity
     * @time 2015-12-16下午09:22:40
     * @param <T>
     * @param entityClass
     * @param fieldMap
     * @return
     * @author   nilu
     */
    public static <T> boolean insertEntity(Class<T> entityClass, Map<String, Object> fieldMap) {
        if(GyUtils.isNull(fieldMap)) {
            LOGGER.error("can not insert entity : fieldMap is empty");
            throw new RuntimeException("can not insert entity : fieldMap is empty");
        }
        final String tableName = entityClass.getSimpleName();
        final String sql = getInsertSQL(tableName, fieldMap);
        final Object[] params = fieldMap.values().toArray();
        return executeUpdate(sql, params) == 1;
    }
    
    /**
     * @description query entity
     * @time 2015-12-16下午09:22:51
     * @param <T>
     * @param entityClass
     * @param id
     * @return
     * @author   nilu
     */
    public static <T> T queryEntity(Class<T> entityClass, long id) {
        Connection conn = null;
        T result;
        try {
            final String sql = "select * from " + entityClass.getSimpleName() + " where id = ?";
            conn = getConnection();
            result = QUERY_RUNNER.query(conn, sql, new BeanHandler<T>(entityClass), id);
        } catch(SQLException e) {
            LOGGER.error("dao query failure", e);
            throw new RuntimeException(e);
        }
        return result;
    }
    
    /**
     * @description update entity
     * @time 2015-12-16下午09:23:00
     * @param <T>
     * @param entityClass
     * @param id
     * @param fieldMap
     * @return
     * @author   nilu
     */
    public static <T> boolean updateEntity(Class<T> entityClass, long id, Map<String, Object> fieldMap) {
        if(GyUtils.isNull(fieldMap)) {
            LOGGER.error("can not update entity : fieldMap is empty");
            throw new RuntimeException("can not insert entity : fieldMap is empty");
        }
        final String tableName = entityClass.getSimpleName();
        final String sql = getUpdateSQL(tableName, fieldMap);
        List<Object> params = new ArrayList<Object>();
        params.addAll(fieldMap.values());
        params.add(id);
        return executeUpdate(sql, params.toArray()) == 1;
    }
    
    /**
     * @description delete entity
     * @time 2015-12-16下午09:23:11
     * @param <T>
     * @param entityClass
     * @param id
     * @return
     * @author   nilu
     */
    public static <T> boolean deleteEntity(Class<T> entityClass, long id) {
        final String tableName = entityClass.getSimpleName();
        final String sql = "delete from " + tableName + " where id = ?";
        return executeUpdate(sql, id) == 1;
    }

    /**
     * @description Assembling insert sql
     * @time 2015-12-16下午09:25:37
     * @param tableName
     * @param fieldMap
     * @return
     * @author   nilu
     */
    private static String getInsertSQL(String tableName, Map<String, Object> fieldMap) {
        StringBuilder sql = new StringBuilder();
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();
        sql.append("insert into ");
        sql.append(tableName);
        
        columns.append("(");
        values.append("(");
        for(Map.Entry<String, Object> entry : fieldMap.entrySet()) {
            columns.append(entry.getKey()).append(",");
            values.append("?,");
        }
        columns.replace(columns.lastIndexOf(","), columns.length(), ")");
        values.replace(values.lastIndexOf(","), values.length(), ")");
        sql.append(columns);
        sql.append(" values");
        sql.append(values);
        return sql.toString();
    }
    
    /**
     * @description Assembling update sql
     * @time 2015-12-16下午09:26:40
     * @param tableName
     * @param fieldMap
     * @return
     * @author   nilu
     */
    private static String getUpdateSQL(String tableName, Map<String, Object> fieldMap) {
        StringBuilder sql = new StringBuilder();
        StringBuilder columns = new StringBuilder();
        sql.append("update ");
        sql.append(tableName);
        sql.append(" set ");
        
        for(Map.Entry<String, Object> entry : fieldMap.entrySet()) {
            columns.append(entry.getKey()).append("=?, ");
        }
        columns.replace(columns.lastIndexOf(","), columns.length(), "");
        sql.append(columns);
        sql.append(" where id = ?");
        return sql.toString();
    }
    
    /**
     * @description read sql file and excute it
     * @time 2015-12-16下午09:26:49
     * @param filePath
     * @throws IOException
     * @author   nilu
     */
    public static void executeSqlFile(String filePath) throws IOException {
        InputStream in = DatabaseUtils.class.getClassLoader().getResourceAsStream(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String sql;
        //init test data
        while(!GyUtils.isNull(sql = reader.readLine())) {
            DatabaseUtils.executeUpdate(sql);
        }
    }
}
