import util.DataSourceProvider;
import util.DbUtil;
import util.FileUtil;
import util.PropertyLoader;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        System.out.println(PropertyLoader.getProperties());
        String url = PropertyLoader.getProperty("jdbc.url");
        System.out.println(url);
        String schema = FileUtil.readFileFromClasspath("schema.sql");
        DataSourceProvider.setDbUrl(url);

        try(Connection connection = DataSourceProvider.getDataSource().getConnection()){

            DbUtil.insertFromString(connection, schema);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
