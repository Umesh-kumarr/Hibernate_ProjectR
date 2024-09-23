package MovieBookSystem.ui;

import javax.swing.*;
import java.awt.*;

public class SelectAdminORUserUI extends JFrame {


    public SelectAdminORUserUI() {
        setTitle("Movie Booking System");
        setSize(300, 200);
        setLayout(new FlowLayout());



        JButton btnAdmin = new JButton("ADMIN");
        JButton btnUser = new JButton("USER");


        btnAdmin.addActionListener(e -> new AdminLoginUI().setVisible(true));
        btnUser.addActionListener(e -> new UserLoginUI().setVisible(true));



        add(btnAdmin);
        add(btnUser);




    }
}