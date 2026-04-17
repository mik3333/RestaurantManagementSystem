package view;

import javax.swing.*;

public class OrderScreen extends JFrame {

    public OrderScreen() {
        setTitle("Order");
        setSize(400,300);
        setLayout(null);

        JLabel label = new JLabel("Order Screen (Working)");
        label.setBounds(100,100,200,30);
        add(label);

        setVisible(true);
    }
}