package com.liujingwei.movie;

import java.sql.*;

public class JDBCUtils {
    public static final String connectionURL= "jdbc:mysql://localhost:3306/movie1" +
            "?useUnicode=true&characterEncoding=UTF8&useSSL=false"+"&serverTimezone=GMT%2B8";
    public static final String username = "root";
    public static final String password = "root";

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(connectionURL,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(ResultSet resultSet, PreparedStatement preparedStatement,Connection connection){
        try {
            if(resultSet!=null)
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(preparedStatement!=null)
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(connection!=null)
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
