package MovieBookSystem.ui;




import MovieBookSystem.dao.UserDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UserLoginUI extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;

    public UserLoginUI() {
        setTitle("Movie Booking System - User Login");
        setSize(300, 200);
        setLayout(new FlowLayout());

        JLabel lblUsername = new JLabel("Username:");
        txtUsername = new JTextField(20);

        JLabel lblPassword = new JLabel("Password:");
        txtPassword = new JPasswordField(20);

        JButton btnLogin = new JButton("Login");
        JButton btnSignup = new JButton("SignUP");

        btnLogin.addActionListener(this::loginAction);
        btnSignup.addActionListener(e -> new SignUpUserUI().setVisible(true));

        add(lblUsername);
        add(txtUsername);
        add(lblPassword);
        add(txtPassword);
        add(btnLogin);
        add(btnSignup);



}

    private void loginAction(ActionEvent e) {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        boolean isAuthenticated = UserDAO.authenticate(username, password);


        if (isAuthenticated) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            new MainUserUI().setVisible(true);
            dispose(); // Close the login window
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password!");
        }

    }

}
