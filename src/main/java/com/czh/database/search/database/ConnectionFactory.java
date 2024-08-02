package com.czh.database.search.database;

import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection connection(String host,
                                        String port,
                                        String database,
                                        String username,
                                        String password) {
        String url = "jdbc:mysql://"+ host +":"+ port +"/"+ database
                +"?autoReconnect=true&useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&useSSL=false&serverTimezone=UTC";

        try {
            Class.forName("mysql-connector-j");
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("数据库驱动没有找到");
            // todo 抛出异常和全局异常处理
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("连接数据库出错，请检查数据相关参数");
            // todo 抛出异常和全局异常处理
        }
        return null;
    }
}
