package MovieBookSystem.ui;

import MovieBookSystem.dao.UserDAO;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SignUpUserUI  extends JFrame {
        private JTextField txtUsername;
        private JPasswordField txtPassword;

        public SignUpUserUI() {
            setTitle("Movie Booking System - User SignUp");
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
            String username = txtUsername.getText();
            String password = new String(txtPassword.getPassword());

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);

            UserDAO.saveUser(user);
            JOptionPane.showMessageDialog(this, "Registration successful!");

        }
    }

