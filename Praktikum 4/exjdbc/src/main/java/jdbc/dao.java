package jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jdbc.Main.URL;

public class dao {
    public static List<Person> getPersons() throws SQLException {
        List<Person> personList = new ArrayList<>();


        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            try (ResultSet resultSet = stmt.executeQuery("select * from person")) {
                while (resultSet.next()) {
                    Person person = new Person(
                            resultSet.getLong(1),
                            resultSet.getString(2),
                            resultSet.getInt(3));
                    personList.add(person);
//                    System.out.println(resultSet.getString(2));
                }
            }
        }
        return personList;
    }

    public static List<Person> getPersonsById() throws SQLException {
        List<Person> personList = new ArrayList<>();


        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            try (ResultSet resultSet = stmt.executeQuery("select * from person WHERE Id = '2'")) {
                while (resultSet.next()) {
                    Person person = new Person(
                            resultSet.getLong(1),
                            resultSet.getString(2),
                            resultSet.getInt(3));
                    personList.add(person);
//                    System.out.println(resultSet.getString(2));
                }
            }
        }
        return personList;
    }
}
