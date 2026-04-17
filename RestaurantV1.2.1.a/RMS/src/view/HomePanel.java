package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HomePanel extends JPanel {

    public HomePanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel imageLabel = new JLabel("🍔   🍜", SwingConstants.CENTER);
        imageLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 72));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel("Restaurant Management System", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(new Color(31, 38, 82));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(new EmptyBorder(24, 0, 0, 0));

        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(imageLabel);
        centerPanel.add(title);
        centerPanel.add(Box.createVerticalGlue());

        add(centerPanel, BorderLayout.CENTER);
    }
}