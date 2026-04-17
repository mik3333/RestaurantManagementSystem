package view;

import javax.swing.*;

public class CashierDashboard extends JFrame {

    public CashierDashboard() {
        setTitle("Cashier Dashboard");
        setSize(400,300);
        setLayout(null);

        JButton orderBtn = new JButton("Create Order");
        orderBtn.setBounds(100,100,200,30);
        add(orderBtn);

        orderBtn.addActionListener(e -> new OrderScreen());

        setVisible(true);
    }
}