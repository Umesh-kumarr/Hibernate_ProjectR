package MovieBookSystem.ui;

import javax.swing.*;
import java.awt.*;


public class MainUserUI extends JFrame {
    public  MainUserUI() {
        setTitle("Movie Booking System");
        setSize(400, 300);
        setLayout(new FlowLayout());

        JButton btnBookMovie= new JButton("Book Movie");


        btnBookMovie.addActionListener(e -> new BookMovieUI().setVisible(true));


        add(btnBookMovie);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

