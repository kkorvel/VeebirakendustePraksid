package jdbc;

import lombok.ToString;
import util.DbUtil;
import util.FileUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static jdbc.dao.getPersons;
import static jdbc.dao.getPersonsById;

@ToString
public class Main {

    public static String URL = "jdbc:hsqldb:file:${user.home}/data/jdbc/db;shutdown=true";

    public static void main(String[] args) throws Exception {

        // createTable();
//        insertPersons();
        String contents = FileUtil.readFileFromClasspath("schema.sql");
        try (Connection conn = DriverManager.getConnection(URL)) {

            DbUtil.insertFromFile(conn, contents);
//            System.out.println(conn);
        }

//        List<Person> personList = getPersons();
//        System.out.println(personList);
        List<Person> personList = getPersonsById();
        System.out.println(personList);
    }





    private static void executeUpdate(String sql) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

        stmt.executeUpdate(sql);
        }
    } private static void insertPersons() throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("insert into person (id, name) values (1, 'Jill')");
            stmt.executeUpdate("insert into person (id, name) values (2, 'Jack')");
            stmt.executeUpdate("insert into person (id, name) values (3, 'John')");

        }
    }

    private static void createTable() throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("CREATE TABLE PERSON (\n" +
                    "      id BIGINT NOT NULL PRIMARY KEY,\n" +
                    "      name VARCHAR(255) NOT NULL,\n" +
                    "      age int\n" +
                    "   );");
        }
    }

}
