package MovieBookSystem.ui;

import MovieBookSystem.dao.AddMovieDAO;
import model.AddMovie;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class AddMovieUI extends JFrame {
    private JTextField txtTheatreName;
    private JTextField txtName;
    private JTextField txtScreenNo;
    private JTextField txtPrice;

    private JTable tblMovies;
    private DefaultTableModel MoviesTableModel;

    public AddMovieUI() {
        setTitle("Manage Movies");
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(6, 2));
        JLabel lblTheatreName = new JLabel("TheatreName:");
        txtTheatreName = new JTextField();
        JLabel lblName = new JLabel("Name:");
        txtName = new JTextField();
        JLabel lblScreenNo = new JLabel("ScreenNo:");
        txtScreenNo = new JTextField();
        JLabel lblPrice = new JLabel("Price:");
        txtPrice = new JTextField();


        formPanel.add(lblTheatreName);
        formPanel.add(txtTheatreName);
        formPanel.add(lblName);
        formPanel.add(txtName);
        formPanel.add(lblScreenNo);
        formPanel.add(txtScreenNo);
        formPanel.add(lblPrice);
        formPanel.add(txtPrice);


        // Button Panel
        JPanel buttonPanel = new JPanel();
        JButton btnSave = new JButton("Save");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnClear = new JButton("Clear");

        buttonPanel.add(btnSave);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        // Table Panel
        String[] columnNames = {"ID","TheatreName", "Name", "ScreenNo", "Price"};
        MoviesTableModel = new DefaultTableModel(columnNames, 0);
        tblMovies = new JTable(MoviesTableModel);
        JScrollPane scrollPane = new JScrollPane(tblMovies);

        // Populate table with existing Movies
        loadMovies();

        // Add action listeners
        btnSave.addActionListener(this::saveMovie);
        btnUpdate.addActionListener(this::updateMovie);
        btnDelete.addActionListener(this::deleteMovie);
        btnClear.addActionListener(e -> clearForm());

        tblMovies.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadSelectedMovies();
            }
        });

        // Add panels to frame
        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    // Load Movies into the table
    private void loadMovies() {
        MoviesTableModel.setRowCount(0); // Clear the table
        List<AddMovie> movies = AddMovieDAO.getAllMovies();
        for (AddMovie movie : movies) {
            Object[] row = new Object[]{
                    movie.getId(),
                    movie.getTheatrename(),
                    movie.getName(),
                    movie.getScreenno(),
                    movie.getPrice(),
            };
            MoviesTableModel.addRow(row);
        }
    }

    // Save a new Movies
    private void saveMovie(ActionEvent e) {
        AddMovie movie = new AddMovie();
        movie.setName(txtName.getText());
        movie.setScreenno(Integer.parseInt(txtScreenNo.getText()));
        movie.setPrice(Integer.parseInt(txtPrice.getText()));

        AddMovieDAO.saveMovie(movie);
        JOptionPane.showMessageDialog(this, "Movies saved successfully!");

        loadMovies();  // Reload Movies to reflect the new Movie
        clearForm();
    }

    // Update an existing Movie
    private void updateMovie(ActionEvent e) {
        int selectedRow = tblMovies.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a Movies to update.");
            return;
        }

        int movieId = (int) tblMovies.getValueAt(selectedRow, 0);  // Get the Movie ID from the selected row

        AddMovie movie = AddMovieDAO.getMoviesById(movieId);
        if (movie != null) {
            movie.setName(txtName.getText());
            movie.setScreenno(Integer.parseInt(txtScreenNo.getText()));
            movie.setPrice(Integer.parseInt(txtPrice.getText()));

            AddMovieDAO.updateMovie(movie);
            JOptionPane.showMessageDialog(this, "Movie updated successfully!");

            loadMovies();  // Reload Movies to reflect the update
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this, "Movie not found!");
        }
    }

    // Delete an existing Movie
    private void deleteMovie(ActionEvent e) {
        int selectedRow = tblMovies.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a Movie to delete.");
            return;
        }

        int movieId = (int) tblMovies.getValueAt(selectedRow, 0);  // Get the Movies ID from the selected row
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this Movie?");
        if (confirm == JOptionPane.YES_OPTION) {
            AddMovieDAO.deleteMovies(movieId);
            JOptionPane.showMessageDialog(this, "Movie deleted successfully!");

            loadMovies();  // Reload Movies to reflect the deletion
            clearForm();
        }
    }

    // Clear form fields
    private void clearForm() {
        txtTheatreName.setText("");
        txtName.setText("");
        txtScreenNo.setText("");
        txtPrice.setText("");
        tblMovies.clearSelection();
    }

    // Load selected Movies details into form
    private void loadSelectedMovies() {
        int selectedRow = tblMovies.getSelectedRow();
        if (selectedRow != -1) {
            txtTheatreName.setText(tblMovies.getValueAt(selectedRow, 1).toString());
            txtName.setText(tblMovies.getValueAt(selectedRow, 2).toString());
            txtScreenNo.setText(tblMovies.getValueAt(selectedRow, 3).toString());
            txtPrice.setText(tblMovies.getValueAt(selectedRow, 4).toString());
        }
    }
}
