package MovieBookSystem.ui;

import MovieBookSystem.dao.AdminDAO;
import model.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BookMovieUI extends JFrame {
    private JTextField txtMovieName;

    public BookMovieUI() {
        setTitle("Book Movie");
        setSize(300, 200);
        setLayout(new FlowLayout());

        JLabel lblMovieName = new JLabel("Movie Name:");
        txtMovieName = new JTextField(20);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(this::saveMovie);

        add(lblMovieName);
        add(txtMovieName);
        add(btnSave);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void saveMovie(ActionEvent e) {
        String movieName = txtMovieName.getText();
        Admin admin = new Admin();
        admin.setName(movieName);

        AdminDAO.saveadmin(admin);
        JOptionPane.showMessageDialog(this, "Movie Book successfully!");
    }
}
