package controller;

import model.User;

import java.util.Optional;

public class LoginController {

    public Optional<User> authenticate(String username, String password) {
        if (username == null || password == null) {
            return Optional.empty();
        }

        String user = username.trim();
        String pass = password.trim();

        if (pass.equals("123")) {
            if (user.equalsIgnoreCase("cashier") || user.equalsIgnoreCase("admin") || user.equalsIgnoreCase("manager")) {
                String role = user.equalsIgnoreCase("admin") ? "Admin"
                        : user.equalsIgnoreCase("manager") ? "Manager"
                        : "Cashier";
                return Optional.of(new User(user, role));
            }
        }

        return Optional.empty();
    }
}
