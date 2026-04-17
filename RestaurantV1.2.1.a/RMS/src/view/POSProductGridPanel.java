package view;

import controller.POSController;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class POSProductGridPanel extends JPanel {

    private final List<POSProductListener> listeners = new ArrayList<>();

    public POSProductGridPanel(POSController controller) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel gridPanel = new JPanel(new GridLayout(3, 4, 12, 12));
        gridPanel.setBackground(Color.WHITE);
        gridPanel.setBorder(new EmptyBorder(12, 12, 12, 12));

        // Get products from controller catalog
        List<String> products = new ArrayList<>(controller.getProductCatalog().keySet());
        
        for (String product : products) {
            gridPanel.add(createProductCard(product));
        }

        JScrollPane scroll = new JScrollPane(gridPanel);
        scroll.setBorder(null);
        add(scroll, BorderLayout.CENTER);
    }

    private JPanel createProductCard(String productName) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(220, 224, 235), 1));

        JLabel imageLabel = new JLabel("🍔", SwingConstants.CENTER);
        imageLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        imageLabel.setPreferredSize(new Dimension(120, 100));
        imageLabel.setMaximumSize(new Dimension(120, 100));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel(productName, SwingConstants.CENTER);
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        nameLabel.setForeground(new Color(50, 50, 50));
        nameLabel.setBorder(new EmptyBorder(8, 4, 8, 4));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(imageLabel);
        card.add(nameLabel);
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        card.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                notifyListeners(productName);
            }
        });

        return card;
    }

    public void addProductListener(POSProductListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners(String product) {
        for (POSProductListener listener : listeners) {
            listener.onProductSelected(product);
        }
    }

    public interface POSProductListener {
        void onProductSelected(String product);
    }
}
