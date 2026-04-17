package view;

import javax.swing.*;
import java.awt.*;

public class PlaceholderPanel extends JPanel {

    public PlaceholderPanel(String title) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel label = new JLabel(title, SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 24));
        label.setForeground(new Color(50, 58, 91));

        add(label, BorderLayout.CENTER);
    }
}
