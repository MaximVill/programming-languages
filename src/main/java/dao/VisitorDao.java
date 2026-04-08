package dao;

import config.DatabaseConnection;
import model.Visitor;
import java.sql.*;

public class VisitorDao {
    public void createTableIfNotExists() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS visitors (
                id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(100) NOT NULL,
                surname VARCHAR(100) NOT NULL,
                phone VARCHAR(20),
                subscribed BOOLEAN
            )
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    public int findOrCreate(String name, String surname, String phone, boolean subscribed) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT id FROM visitors WHERE name = ? AND surname = ?")) {
            ps.setString(1, name);
            ps.setString(2, surname);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("id");
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO visitors (name, surname, phone, subscribed) VALUES (?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, name);
            ps.setString(2, surname);
            ps.setString(3, phone);
            ps.setBoolean(4, subscribed);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        }
    }
}