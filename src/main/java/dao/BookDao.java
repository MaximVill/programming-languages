package dao;

import config.DatabaseConnection;
import model.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    public void createTableIfNotExists() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS books (
                id INT AUTO_INCREMENT PRIMARY KEY,
                visitor_id INT NOT NULL,
                title VARCHAR(255) NOT NULL,
                author VARCHAR(255) NOT NULL,
                publishing_year INT,
                isbn VARCHAR(20),
                publisher VARCHAR(255),
                FOREIGN KEY (visitor_id) REFERENCES visitors(id)
            )
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    public boolean exists(int visitorId, String isbn) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT 1 FROM books WHERE visitor_id = ? AND isbn = ?")) {
            ps.setInt(1, visitorId);
            ps.setString(2, isbn);
            return ps.executeQuery().next();
        }
    }

    public void insert(int visitorId, String title, String author, int year, String isbn, String publisher) throws SQLException {
        if (exists(visitorId, isbn)) return;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO books (visitor_id, title, author, publishing_year, isbn, publisher) VALUES (?, ?, ?, ?, ?, ?)")) {
            ps.setInt(1, visitorId);
            ps.setString(2, title);
            ps.setString(3, author);
            ps.setInt(4, year);
            ps.setString(5, isbn);
            ps.setString(6, publisher);
            ps.executeUpdate();
        }
    }

    public List<Book> findAllOrderedByYear() throws SQLException {
        return findByQuery("SELECT * FROM books ORDER BY publishing_year");
    }

    public List<Book> findAllBeforeYear(int year) throws SQLException {
        return findByQuery("SELECT * FROM books WHERE publishing_year < " + year);
    }

    private List<Book> findByQuery(String sql) throws SQLException {
        List<Book> list = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Book(
                        rs.getInt("id"),
                        rs.getInt("visitor_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("publishing_year"),
                        rs.getString("isbn"),
                        rs.getString("publisher")
                ));
            }
        }
        return list;
    }

    public void dropTables() throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS books");
            stmt.execute("DROP TABLE IF EXISTS visitors");
        }
    }
}