package com.armao.app;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import javax.xml.transform.Result;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;

/**
 * Unit test for simple App.
 */
public class SqlLiteDbTest
    extends TestCase
{
    private SqlLiteDb db;
    protected void setUp() {
    }

    public static String createFileNameFullPath(String filename) {
        Path filepath = Paths.get(System.getProperty("user.dir"));
        String result = Paths.get(filepath.toString(), filename).toString();
        return result;
    }

    public void testBasic() {
        String dbname = "test.db";
        String filename = createFileNameFullPath(dbname);

        this.db = new SqlLiteDb(filename, true);
        int create_db_result = this.db.execUpdateCmd("CREATE TABLE IF NOT EXISTS SqlLiteDbTest (\n" +
                " col1 TEXT PRIMARY KEY, \n" +
                " col2 INT NULL);");
        int insert_row_result = this.db.execUpdateCmd("INSERT INTO SqlLiteDbTest (col1, col2) " +
                "VALUES(\"col1\", 2);");
        JSONArray read_all_result = this.db.execQueryCmd("SELECT * FROM SqlLiteDbTest;");
        assertEquals("Verify written to database matches original data",
                read_all_result.toString(), "[{\"col2\":2,\"col1\":\"col1\"}]");
        return;
    }

    public void testQueryAll(){

    }

}
