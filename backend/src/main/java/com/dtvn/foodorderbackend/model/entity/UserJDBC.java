package com.dtvn.foodorderbackend.model.entity;

import com.mysql.cj.conf.HostInfo;
import com.mysql.cj.jdbc.ConnectionImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@SuppressWarnings("all")
public class UserJDBC {
    String SQL;

    public User findUserByUsername(String username) throws SQLException {
        // TODO: Open connection
        Connection connection = new ConnectionImpl(new HostInfo());
        // TODO: Process SQL Statement
        String sqlStatement = "";
        if (SQL == "MYSQL") {
            sqlStatement = "SELECT * from user where status = 'VERIFIED' limit 10";
        }else if(SQL == "MS SQL Server"){
            sqlStatement = "SELECT top 10 * from user where status = 'VERIFIED' limit 10";
        }
        // TODO: Extract result from result set
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlStatement);

        User user = new User();
        user.setPassword(resultSet.getString("password"));
        // TODO: More operation
        return user;
    }
}
