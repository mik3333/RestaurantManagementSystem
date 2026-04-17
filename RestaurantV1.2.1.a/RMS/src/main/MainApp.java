package main;

import javax.swing.SwingUtilities;
import view.LoginForm;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginForm();
        });
    }
}