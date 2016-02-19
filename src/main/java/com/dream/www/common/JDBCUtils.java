package com.dream.www.common;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * User: changfangfang
 * Date: 2016/2/1
 * Email: fangfang.c@asou.com
 * 功能描述:
 */
public class JDBCUtils {
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

    public static DataSource getDataSource(){
        return dataSource;
    }

    //获得链接
    public static Connection getConnect() throws SQLException {
        return dataSource.getConnection();
    }

    public static void release(Statement statement , Connection connection){
        if (statement!=null){
            try{
                statement.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            statement = null;
        }

        if (connection!=null){
            try{
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            connection = null;
        }
    }

    public static void release(ResultSet resultSet , Statement statement , Connection connection){
        if (resultSet!=null){
            try {
                resultSet.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            resultSet = null;
        }
        release(statement,connection);
    }
}
