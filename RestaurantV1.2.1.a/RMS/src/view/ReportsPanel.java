package view;

import javax.swing.*;
import java.awt.*;

public class ReportsPanel extends JPanel {

    public ReportsPanel() {
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Reports Screen (Prototype)", SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 20));

        add(label, BorderLayout.CENTER);
    }
}