import com.google.gson.Gson;
import javax.swing.*;
import java.awt.*;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class FilmGUI {

    private JFrame frame;
    private JTextField titleField;
    private JTextField genreField, yearField, ratingField;
    private JButton insertButton, updateButton, deleteButton, showButton, fetchButton;
    private JTextArea textArea;
    private JPanel panel, buttonPanel;
    private JScrollPane scrollPane;

    public FilmGUI() {
        // JFrame oluşturuluyor ve ayarları yapılıyor
        frame = new JFrame("Film Sitesi");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(240, 240, 240)); // Arka plan rengini değiştir

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 15, 15)); // Grid düzeni ile form elemanları
        panel.setBackground(new Color(255, 255, 255)); // Panel arka planı
        frame.add(panel, BorderLayout.NORTH);

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        titleLabel.setForeground(new Color(0, 0, 0)); // Yazı rengini siyah yap

        titleField = new JTextField();
        titleField.setFont(new Font("Arial", Font.PLAIN, 14));
        titleField.setBackground(new Color(255, 255, 255));

        JLabel genreLabel = new JLabel("Genre:");
        genreLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        genreLabel.setForeground(new Color(0, 0, 0));

        genreField = new JTextField();
        genreField.setFont(new Font("Arial", Font.PLAIN, 14));
        genreField.setBackground(new Color(255, 255, 255));
        genreField.setEditable(false);  // Bu alan otomatik doldurulacak

        JLabel yearLabel = new JLabel("Year:");
        yearLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        yearLabel.setForeground(new Color(0, 0, 0));

        yearField = new JTextField();
        yearField.setFont(new Font("Arial", Font.PLAIN, 14));
        yearField.setBackground(new Color(255, 255, 255));
        yearField.setEditable(false);  // Bu alan otomatik doldurulacak

        JLabel ratingLabel = new JLabel("Rating:");
        ratingLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        ratingLabel.setForeground(new Color(0, 0, 0));

        ratingField = new JTextField();
        ratingField.setFont(new Font("Arial", Font.PLAIN, 14));
        ratingField.setBackground(new Color(255, 255, 255));
        ratingField.setEditable(false);

        // Panel'e bileşenleri ekle
        panel.add(titleLabel);
        panel.add(titleField);
        panel.add(genreLabel);
        panel.add(genreField);
        panel.add(yearLabel);
        panel.add(yearField);
        panel.add(ratingLabel);
        panel.add(ratingField);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(240, 240, 240));

        // Butonlar
        insertButton = new JButton("Insert");
        insertButton.setFont(new Font("Arial", Font.BOLD, 14));
        insertButton.setBackground(new Color(34, 139, 34));
        insertButton.setForeground(Color.WHITE);
        insertButton.setFocusPainted(false);

        updateButton = new JButton("Update");
        updateButton.setFont(new Font("Arial", Font.BOLD, 14));
        updateButton.setBackground(new Color(70, 130, 180));
        updateButton.setForeground(Color.WHITE);
        updateButton.setFocusPainted(false);

        deleteButton = new JButton("Delete");
        deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
        deleteButton.setBackground(new Color(255, 69, 0));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);

        showButton = new JButton("Show Films");
        showButton.setFont(new Font("Arial", Font.BOLD, 14));
        showButton.setBackground(new Color(255, 165, 0));
        showButton.setForeground(Color.WHITE);
        showButton.setFocusPainted(false);

        fetchButton = new JButton("Fetch Film");
        fetchButton.setFont(new Font("Arial", Font.BOLD, 14));
        fetchButton.setBackground(new Color(255, 215, 0));
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setFocusPainted(false);

        insertButton.addActionListener(e -> insertFilm());
        updateButton.addActionListener(e -> updateFilm());
        deleteButton.addActionListener(e -> deleteFilm());
        showButton.addActionListener(e -> showFilms());
        fetchButton.addActionListener(e -> fetchFilmDetails());

        buttonPanel.add(insertButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(showButton);
        buttonPanel.add(fetchButton);

        frame.add(buttonPanel, BorderLayout.CENTER);

        textArea = new JTextArea(10, 40);
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea.setBackground(new Color(240, 240, 240));
        textArea.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));


        scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        frame.add(scrollPane, BorderLayout.SOUTH);


        frame.setVisible(true);
    }

    private void fetchFilmDetails() {
        String title = titleField.getText().trim();
        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a film title!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // OMDB API'den film bilgilerini al
            String url = "http://www.omdbapi.com/?apikey=81ff43ac&t=" + title;
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            Gson gson = new Gson();
            FilmAPIResponse response = gson.fromJson(reader, FilmAPIResponse.class);

            if (response.getResponse().equals("True")) {
                genreField.setText(response.getGenre());
                yearField.setText(response.getYear());
                ratingField.setText(response.getImdbRating());
            } else {
                JOptionPane.showMessageDialog(frame, "Film not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error fetching film details: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void insertFilm() {
        String title = titleField.getText();
        String genre = genreField.getText();
        String year = yearField.getText();
        String rating = ratingField.getText();

        if (title.isEmpty() || genre.isEmpty() || year.isEmpty() || rating.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


        Film newFilm = new Film(title, genre, Integer.parseInt(year), Double.parseDouble(rating));
        FilmDatabase.insertFilm(newFilm);


        titleField.setText("");
        genreField.setText("");
        yearField.setText("");
        ratingField.setText("");
    }

    private void updateFilm() {
        String title = titleField.getText();

        if (title == null || title.trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a film title.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {

            String url = "http://www.omdbapi.com/?apikey=81ff43ac&t=" + title;
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            Gson gson = new Gson();
            FilmAPIResponse response = gson.fromJson(reader, FilmAPIResponse.class);

            if (response.getResponse().equals("True")) {
                genreField.setText(response.getGenre());
                yearField.setText(response.getYear());
                ratingField.setText(response.getImdbRating());

                // Alanları aktif hale getiriyoruz, yani kullanıcı bilgileri değiştirebilir.
                genreField.setEditable(true);
                yearField.setEditable(true);
                ratingField.setEditable(true);

                int confirm = JOptionPane.showConfirmDialog(frame, "Do you want to update this film?", "Confirm Update", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    String updatedGenre = genreField.getText();
                    String updatedYear = yearField.getText();
                    String updatedRating = ratingField.getText();

                    Film updatedFilm = new Film(title, updatedGenre, Integer.parseInt(updatedYear), Double.parseDouble(updatedRating));
                    FilmDatabase.updateFilm(title, updatedFilm);

                    JOptionPane.showMessageDialog(frame, "Film updated successfully!");


                    titleField.setText("");
                    genreField.setText("");
                    yearField.setText("");
                    ratingField.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Film not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error fetching film details: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteFilm() {
        String title = JOptionPane.showInputDialog(frame, "Enter the title of the film to delete:");
        if (title != null && !title.trim().isEmpty()) {
            FilmDatabase.deleteFilm(title);
        }
    }

    private void showFilms() {
        List<Film> films = FilmDatabase.readFilms();
        textArea.setText("");

        if (films.isEmpty()) {
            textArea.append("No films available.");
        } else {
            for (Film film : films) {
                textArea.append(film.toString() + "\n\n");
            }
        }
    }

    public static void main(String[] args) {
        new FilmGUI();
    }
}
