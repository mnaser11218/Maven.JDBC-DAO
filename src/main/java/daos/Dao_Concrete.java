package daos;

import java.sql.*;
import java.util.*;

public class Dao_Concrete<T> implements Dao<Boolean> {
//    static String username = "mohammed";
//    static String password = "mohammed";
//    static String dbUrl = "jdbc:mysql://localhost:3306/mystudents";
    ConnectionFactory connectionFactory = new ConnectionFactory();


    private User extractUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId( rs.getInt("id") );
        user.setName( rs.getString("name") );
        user.setAge( rs.getInt("age") );
        return user;
    }

    @Override
    public User findById(int id) throws SQLException {
//        Connection connection = DriverManager.getConnection(dbUrl, username, password);
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE id=" + id);
            if(rs.next())
            {
                User user = extractUserFromResultSet(rs);
                return user;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public Set<User> findAll() throws SQLException {
//        Connection connection = DriverManager.getConnection(dbUrl, username, password);
        Connection connection = ConnectionFactory.getConnection();

        Set<User> userList = new HashSet<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user");
            while(rs.next())
            {
                User user = extractUserFromResultSet(rs);
                userList.add(user);
            }
            return userList;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean update(User user) throws SQLException {
//        Connection connection = DriverManager.getConnection(dbUrl, username, password);
        Connection connection = ConnectionFactory.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE user SET name=?, password=?, age=? WHERE id=?");
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getAge());
            ps.setInt(4, user.getId());

            int i = ps.executeUpdate();

            if(i == 1) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public Boolean create(User user) throws SQLException {
//        Connection connection = DriverManager.getConnection(dbUrl, username, password);
        Connection connection = ConnectionFactory.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO user VALUES (?, ?,?, ?)");
            ps.setInt(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getAge());
            int i = ps.executeUpdate();

            if(i == 1) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public void delete(int id) throws SQLException {

//        Connection connection = DriverManager.getConnection(dbUrl, username, password);
        Connection connection = ConnectionFactory.getConnection();

        try {
            Statement stmt = connection.createStatement();
            int rs = stmt.executeUpdate("DELETE FROM user WHERE id=" + id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
