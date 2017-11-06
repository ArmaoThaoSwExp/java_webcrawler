package com.armao.app;

import org.json.JSONArray;

import java.sql.*;


public class SqlLiteDb implements DbInterface {
    private Connection conn;
    private String url;

    public SqlLiteDb(String url) {
        this.url = url;
    }

    public SqlLiteDb(String url, boolean deletePrev) {
        this.url = "jdbc:sqlite:" + url;

        if (deletePrev == true) {
            DbInterface.deleteDB(url);
        }
    }

    public Connection connect() {
        this.conn = DbInterface.connect(this.url);
        return this.conn;
    }

    public boolean disconnect() {
        boolean result = DbInterface.disconnect(this.conn);
        if (result == true) {
            this.conn = null;
        }
        return result;
    }

    public int execUpdateCmd(String cmd){
        int result;
        result = DbInterface.execUpdateCmd(this.url, cmd);
        return result;
    }

    public JSONArray execQueryCmd(String cmd){
        JSONArray result;
        result = DbInterface.executeQueryCmd(this.url, cmd);
        return result;
    }

    public static void main(String[] args) {
        System.out.println("SQLite DB");
    }

//    @Override
//    protected void finalize() throws Throwable {
//        /* Do nothing for now */
//    }
}
