package view;

import javax.swing.*;
import java.awt.*;

public class PaymentScreen extends JFrame {

    public PaymentScreen(double total) {

        setTitle("Payment");
        setSize(350,250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel root = new JPanel(new GridBagLayout());
        root.setBackground(new Color(245,245,245));
        add(root);

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        card.setPreferredSize(new Dimension(220,150));

        JLabel title = new JLabel("Payment");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JLabel totalLabel = new JLabel("Total: " + total);
        totalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton payBtn = new JButton("Pay");
        payBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        payBtn.setBackground(new Color(0,153,76));
        payBtn.setForeground(Color.WHITE);

        payBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Payment Successful");
            dispose();
        });

        card.add(title);
        card.add(Box.createVerticalStrut(10));
        card.add(totalLabel);
        card.add(Box.createVerticalStrut(15));
        card.add(payBtn);

        root.add(card);
        setVisible(true);
    }
}