package dao;

import config.DatabaseConnection;
import model.Music;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MusicDao {
    public void createTableIfNotExists() throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE SCHEMA IF NOT EXISTS study");
            String sql = """
                CREATE TABLE IF NOT EXISTS study.music (
                    id INT PRIMARY KEY,
                    name TEXT NOT NULL
                )
                """;
            stmt.execute(sql);
        }
    }

    public void populateFromSqlScript(String sql) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    public List<Music> findAll() throws SQLException {
        List<Music> list = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, name FROM study.music")) {
            while (rs.next()) {
                list.add(new Music(rs.getInt("id"), rs.getString("name")));
            }
        }
        return list;
    }

    public List<Music> findWithoutMAndT() throws SQLException {
        List<Music> list = new ArrayList<>();
        String sql = """
            SELECT id, name FROM study.music
            WHERE LOWER(name) NOT LIKE '%m%' AND LOWER(name) NOT LIKE '%t%'
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Music(rs.getInt("id"), rs.getString("name")));
            }
        }
        return list;
    }

    public void insert(String name) throws SQLException {
        int nextId;
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT COALESCE(MAX(id), 0) + 1 FROM study.music")) {
            rs.next();
            nextId = rs.getInt(1);
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO study.music (id, name) VALUES (?, ?)")) {
            ps.setInt(1, nextId);
            ps.setString(2, name);
            ps.executeUpdate();
        }
    }
}