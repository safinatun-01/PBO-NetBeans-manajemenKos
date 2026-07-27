package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/ManajemenKos";
    private static final String USER = "root";
    private static final String PASS = "";
    private static Connection connection = null;

    // Melempar SQLException jika koneksi gagal, tidak pernah mengembalikan null
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Koneksi BERHASIL!");
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                if (!connection.isClosed()) { connection.close();
                    System.out.println("Koneksi ditutup.");
            } }
            catch (SQLException e) {  System.err.println("Gagal menutup koneksi: " + e.getMessage());
            }
            connection = null;
    } }
    // Shutdown hook – otomatis tutup saat aplikasi keluar
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(DatabaseConnection::closeConnection));
    }
}