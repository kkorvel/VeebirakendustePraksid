package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import exweb.Post;
import javafx.geometry.Pos;
import util.DataSourceProvider;

public class PostDao {

    List<Post> posts = new ArrayList<>();

    public List<Post> getPosts() {
        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = conn.createStatement()) {

            try (ResultSet r = stmt.executeQuery("select * from post")) {
                while (r.next()) {
                    posts.add(new Post(r.getLong(1), r.getString(2), r.getString(3)));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return posts;
    }

    public void insertPosts(Post post) {
        String query = "INSERT INTO post (id, title, text)" +
                " VALUES (NEXT VALUE FOR seq1, ?, ?)" ;

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setString(1,post.getTitle());
            preparedStatement.setString(2,post.getText());
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePost(Long id) {
        String query = "delete from post where id  = ?" ;

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}