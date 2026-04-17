package view;

import javax.swing.*;

public class LoginForm extends JFrame {

    public LoginForm() {

        setTitle("Login");
        setSize(300,200);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 🔹 USERNAME LABEL
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(20,20,80,25);
        add(userLabel);

        // 🔹 USERNAME FIELD
        JTextField username = new JTextField();
        username.setBounds(100,20,150,25);
        add(username);

        // 🔹 PASSWORD LABEL
        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(20,60,80,25);
        add(passLabel);

        // 🔹 PASSWORD FIELD
        JPasswordField password = new JPasswordField();
        password.setBounds(100,60,150,25);
        add(password);

        // 🔹 BUTTON
        JButton btn = new JButton("Login");
        btn.setBounds(100,100,100,30);
        add(btn);

        btn.addActionListener(e -> {
            String user = username.getText().trim();
            String pass = new String(password.getPassword()).trim();

            if(user.equalsIgnoreCase("cashier") && pass.equals("123")) {
                JOptionPane.showMessageDialog(this, "Login Success");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Login");
            }
        });

        setVisible(true);
    }
}