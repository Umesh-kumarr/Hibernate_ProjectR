package MovieBookSystem.ui;


import MovieBookSystem.dao.AdminDAO;
import model.Admin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SignUpadminUI extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;

    public SignUpadminUI() {
        setTitle("Movie Booking System - User Signup");
        setSize(300, 200);
        setLayout(new FlowLayout());

        JLabel lblUsername = new JLabel("Username:");
        txtUsername = new JTextField(20);

        JLabel lblPassword = new JLabel("Password:");
        txtPassword = new JPasswordField(20);

        JButton btnRegister = new JButton("Register");
        btnRegister.addActionListener(this::signupAction);

        add(lblUsername);
        add(txtUsername);
        add(lblPassword);
        add(txtPassword);
        add(btnRegister);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void signupAction(ActionEvent e) {

        String username1 = txtUsername.getText();
        String password1 = new String(txtPassword.getPassword());

        Admin admin = new Admin();
        admin.setUsername(username1);
        admin.setPassword(password1);

        AdminDAO.saveadmin(admin);
        JOptionPane.showMessageDialog(this, "Registration successful!");
    }
}
