package com.itcast.util;

/*
Author: Galaxy
Time: 2022/1/21 22:56
Target: 创建Druid的工具类
*/

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtilsDataSource {
    private static DataSource ds;

    static {
        Properties properties = new Properties();
        try {
            properties.load(JdbcUtilsDataSource.class.getClassLoader().getResourceAsStream("conf/druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    // 归还connection对象至连接池
    public static void close(Statement statement, Connection conn) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet rs, Statement statement, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    // 返回连接池
    public static DataSource getDataSource() {
        return ds;
    }
}
