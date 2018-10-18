package com.iss.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

/**
 * @author SongChong created by 2018/10/9 0009 15:51
 */
public class DBUtils {
    private static ResourceBundle rb = ResourceBundle.getBundle("DB");

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName(rb.getString("driver"));
            con = DriverManager.getConnection(rb.getString("url"), rb.getString("username"), rb.getString("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}