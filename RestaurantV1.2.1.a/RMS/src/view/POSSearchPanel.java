package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class POSSearchPanel extends JPanel {

    private final List<POSSearchListener> listeners = new ArrayList<>();

    public POSSearchPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(Color.WHITE);

        JTextField searchField = new JTextField(20);
        searchField.setText("Search");
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.addActionListener(e -> notifyListeners(searchField.getText()));

        JButton searchBtn = new JButton("🔍");
        searchBtn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        searchBtn.setFocusPainted(false);
        searchBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchBtn.addActionListener(e -> notifyListeners(searchField.getText()));

        add(searchField);
        add(searchBtn);
    }

    public void addSearchListener(POSSearchListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners(String query) {
        for (POSSearchListener listener : listeners) {
            listener.onSearch(query);
        }
    }

    public interface POSSearchListener {
        void onSearch(String query);
    }
}
