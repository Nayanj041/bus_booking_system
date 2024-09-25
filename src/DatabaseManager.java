import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseManager {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bbsystem";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "nayan@041";

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            System.out.println("Database connection established successfully.");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, "Failed to connect to database", ex);
            throw new RuntimeException("Failed to connect to database", ex);
        }
    }

    public void disconnect() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed successfully.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, "Failed to close database connection", ex);
        }
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    public int executeUpdate(String sql, Object... params) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
        return preparedStatement.executeUpdate();
    }

    public ResultSet executeParameterizedQuery(String sql, Object... params) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
        return preparedStatement.executeQuery();
    }

    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, "Error checking connection status", ex);
            return false;
        }
    }

    public void beginTransaction() throws SQLException {
        connection.setAutoCommit(false);
    }

    public void commitTransaction() throws SQLException {
        connection.commit();
        connection.setAutoCommit(true);
    }

    public void rollbackTransaction() throws SQLException {
        connection.rollback();
        connection.setAutoCommit(true);
    }

    // Example of a specific method for the bus booking system
    public boolean checkBookingExists(String userName, String journeyDate) throws SQLException {
        String sql = "SELECT COUNT(*) FROM book WHERE User_Name = ? AND DOJ = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, userName);
            pstmt.setString(2, journeyDate);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    // Add more specific methods for your bus booking system as needed
}
