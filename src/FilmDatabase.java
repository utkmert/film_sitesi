import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmDatabase {

    // Veritabanı bağlantısı
    private static Connection connectDatabase() {
        try {
            String url = "jdbc:mysql://localhost:3306/filmsdb";
            String user = "root";
            String password = "637802"; // Şifrenizi buraya girin
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
            return null;
        }
    }


    public static void insertFilm(Film film) {
        String insertSQL = "INSERT INTO films (title, genre, year, rating) VALUES (?, ?, ?, ?);";
        try (Connection conn = connectDatabase(); PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, film.getTitle());
            pstmt.setString(2, film.getGenre());
            pstmt.setInt(3, film.getYear());
            pstmt.setDouble(4, film.getRating());
            pstmt.executeUpdate();
            System.out.println("Film inserted: " + film);
        } catch (SQLException e) {
            System.err.println("Error inserting film: " + e.getMessage());
        }
    }

    public static List<Film> readFilms() {
        List<Film> films = new ArrayList<>();
        String selectSQL = "SELECT * FROM films;";
        try (Connection conn = connectDatabase(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(selectSQL)) {
            while (rs.next()) {
                Film film = new Film(
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getInt("year"),
                        rs.getDouble("rating")
                );
                films.add(film);
            }
        } catch (SQLException e) {
            System.err.println("Error reading films: " + e.getMessage());
        }
        return films;
    }


    public static void updateFilm(String oldTitle, Film updatedFilm) {
        String updateSQL = "UPDATE films SET title = ?, genre = ?, year = ?, rating = ? WHERE title = ?;";
        try (Connection conn = connectDatabase(); PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            pstmt.setString(1, updatedFilm.getTitle());
            pstmt.setString(2, updatedFilm.getGenre());
            pstmt.setInt(3, updatedFilm.getYear());
            pstmt.setDouble(4, updatedFilm.getRating());
            pstmt.setString(5, oldTitle);  // Eski başlık (güncellenmek istenen başlık)
            pstmt.executeUpdate();
            System.out.println("Film updated: " + updatedFilm);
        } catch (SQLException e) {
            System.err.println("Error updating film: " + e.getMessage());
        }
    }

    public static void deleteFilm(String title) {
        String deleteSQL = "DELETE FROM films WHERE title = ?;";
        try (Connection conn = connectDatabase(); PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
            pstmt.setString(1, title);
            pstmt.executeUpdate();
            System.out.println("Film deleted: " + title);
        } catch (SQLException e) {
            System.err.println("Error deleting film: " + e.getMessage());
        }
    }
}
