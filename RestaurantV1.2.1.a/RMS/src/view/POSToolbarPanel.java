package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class POSToolbarPanel extends JPanel {

    private final List<POSToolbarListener> listeners = new ArrayList<>();

    public POSToolbarPanel() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(new Color(74, 83, 134));
        setBorder(new EmptyBorder(12, 16, 12, 16));

        JLabel posLabel = new JLabel("🍴 POS", SwingConstants.LEFT);
        posLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        posLabel.setForeground(Color.WHITE);

        add(posLabel);
        add(Box.createHorizontalStrut(20));

        // KOT = Kitchen Order Ticket - sends order to kitchen
        String[] toolbarButtons = {"New", "Hold", "Bill List", "KOT", "Dine In"};
        for (String btn : toolbarButtons) {
            add(createToolbarButton(btn));
            add(Box.createHorizontalStrut(8));
        }

        add(Box.createHorizontalGlue());
    }

    private JButton createToolbarButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(new Color(239, 109, 137));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(80, 32));
        btn.addActionListener(e -> notifyListeners(text));
        return btn;
    }

    public void addToolbarListener(POSToolbarListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners(String action) {
        for (POSToolbarListener listener : listeners) {
            listener.onToolbarAction(action);
        }
    }

    public interface POSToolbarListener {
        void onToolbarAction(String action);
    }
}
