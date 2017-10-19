package dao;

import util.DbUtil;
import util.FileUtil;
import util.PropertyLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SetupDao {

    public void createSchema() {
        String statements = FileUtil.readFileFromClasspath("schema.sql");
        String dbUrl = PropertyLoader.getProperty("javax.persistence.jdbc.url");

        try (Connection c = DriverManager.getConnection(dbUrl)) {
            DbUtil.insertFromString(c, statements);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
