package MovieBookSystem.ui;


import javax.swing.*;
import java.awt.*;

public class MainAdminUI extends JFrame {
    public MainAdminUI() {
        setTitle("Movie Booking System");
        setSize(400, 300);
        setLayout(new FlowLayout());

        JButton btnAddTheatre = new JButton("ADD Theatre");
        JButton btnAddMovies = new JButton("Add Movies");


        btnAddTheatre.addActionListener(e -> new AddtheatreUI().setVisible(true));
        btnAddMovies.addActionListener(e -> new AddMovieUI().setVisible(true));


        add(btnAddTheatre);
        add(btnAddMovies);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
