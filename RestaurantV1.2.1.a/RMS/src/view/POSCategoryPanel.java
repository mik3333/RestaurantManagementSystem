package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class POSCategoryPanel extends JPanel {

    private final List<POSCategoryListener> listeners = new ArrayList<>();

    public POSCategoryPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(45, 55, 95));
        setPreferredSize(new Dimension(120, 400));
        setBorder(new EmptyBorder(12, 12, 12, 12));

        String[] categories = {"All Categories", "Drinks", "Burger", "Pizza", "BBQ"};
        for (String cat : categories) {
            add(createCategoryButton(cat));
            add(Box.createVerticalStrut(8));
        }

        add(Box.createVerticalGlue());
    }

    private JButton createCategoryButton(String category) {
        JButton btn = new JButton(category);
        btn.setBackground(new Color(45, 55, 95));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(e -> notifyListeners(category));
        return btn;
    }

    public void addCategoryListener(POSCategoryListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners(String category) {
        for (POSCategoryListener listener : listeners) {
            listener.onCategorySelected(category);
        }
    }

    public interface POSCategoryListener {
        void onCategorySelected(String category);
    }
}
