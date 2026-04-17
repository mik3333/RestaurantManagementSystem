package view;

import controller.LoginController;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Optional;

public class LoginForm extends JFrame {

    private final LoginController loginController = new LoginController();
    private final JTextField username = createTextField();
    private final JPasswordField password = createPasswordField();

    public LoginForm() {
        setTitle("Restaurant System - Login");
        setSize(540, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        add(createRootPanel());
        setVisible(true);
    }

    private JPanel createRootPanel() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(new Color(240, 242, 247));
        root.add(createHeaderPanel(), BorderLayout.NORTH);
        root.add(createCardContainer(), BorderLayout.CENTER);
        return root;
    }

    private JPanel createHeaderPanel() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBackground(new Color(39, 56, 103));
        header.setPreferredSize(new Dimension(480, 180));
        header.setBorder(new EmptyBorder(18, 0, 18, 0));

        JLabel logoLabel = new JLabel("\uD83C\uDF7D", SwingConstants.CENTER);
        logoLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 46));
        logoLabel.setForeground(Color.WHITE);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel headerTitle = new JLabel("Restaurant Management System", SwingConstants.CENTER);
        headerTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        headerTitle.setForeground(Color.WHITE);
        headerTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerTitle.setBorder(new EmptyBorder(12, 0, 0, 0));

        header.add(logoLabel);
        header.add(headerTitle);
        return header;
    }

    private JPanel createCardContainer() {
        JPanel cardContainer = new JPanel(new GridBagLayout());
        cardContainer.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(24, 0, 24, 0);

        cardContainer.add(createCardPanel(), gbc);
        return cardContainer;
    }

    private JPanel createCardPanel() {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 214, 222), 1),
                new EmptyBorder(28, 28, 28, 28)
        ));
        card.setPreferredSize(new Dimension(440, 460));
        card.setMaximumSize(new Dimension(440, 460));
        card.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(createTitleLabel("Login"));
        card.add(createDescriptionLabel("Please enter your username and password."));
        card.add(createFieldLabel("Username"));
        card.add(username);
        card.add(createFieldLabel("Password"));
        card.add(password);
        card.add(Box.createVerticalStrut(30));
        card.add(createButtonRow());

        return card;
    }

    private JLabel createTitleLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 20));
        label.setForeground(new Color(34, 40, 62));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private JLabel createDescriptionLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        label.setForeground(new Color(110, 118, 143));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(new EmptyBorder(8, 0, 20, 0));
        return label;
    }

    private JLabel createFieldLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        label.setForeground(new Color(95, 101, 120));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setMaximumSize(new Dimension(360, 18));
        label.setBorder(new EmptyBorder(14, 0, 6, 0));
        return label;
    }

    private JTextField createTextField() {
        JTextField field = new JTextField();
        field.setPreferredSize(new Dimension(360, 42));
        field.setMaximumSize(new Dimension(360, 42));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(202, 209, 216), 1),
                new EmptyBorder(10, 12, 10, 12)
        ));
        field.setForeground(new Color(42, 50, 78));
        return field;
    }

    private JPasswordField createPasswordField() {
        JPasswordField field = new JPasswordField();
        field.setPreferredSize(new Dimension(360, 42));
        field.setMaximumSize(new Dimension(360, 42));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(202, 209, 216), 1),
                new EmptyBorder(10, 12, 10, 12)
        ));
        field.setForeground(new Color(42, 50, 78));
        return field;
    }

    private JPanel createButtonRow() {
        JPanel buttonRow = new JPanel(new GridLayout(1, 2, 20, 0));
        buttonRow.setOpaque(false);
        buttonRow.setPreferredSize(new Dimension(400, 60));
        buttonRow.setMaximumSize(new Dimension(400, 60));

        buttonRow.add(createButton("Login", new Color(56, 142, 60), this::login));
        buttonRow.add(createButton("Exit", new Color(211, 47, 47), () -> System.exit(0)));

        return buttonRow;
    }

    private JButton createButton(String text, Color color, Runnable action) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(160, 44));
        button.setMaximumSize(new Dimension(160, 44));
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.addActionListener(e -> action.run());
        return button;
    }

    private void login() {
        Optional<User> result = loginController.authenticate(username.getText(), new String(password.getPassword()));
        if (result.isPresent()) {
            new Dashboard(result.get());
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
}
