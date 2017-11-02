package dao;

import util.DbUtil;
import util.FileUtil;
import util.PropertyLoader;

import java.sql.DriverManager;
import java.sql.SQLException;

public class SetupDao {

    private static String DB_URL = new PropertyLoader().getProperty("javax.persistence.jdbc.url");

    public void createSchema() {
        String contents = FileUtil.readFileFromClasspath("schema.sql");

        try {
            DbUtil.insertFromString(DriverManager.getConnection(DB_URL), contents);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
