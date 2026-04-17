package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class SidebarPanel extends JPanel {

    private final SidebarListener listener;

    public SidebarPanel(SidebarListener listener) {
        this.listener = listener;

        setLayout(new BorderLayout());
        setBackground(new Color(34, 44, 82));
        setPreferredSize(new Dimension(220, 0));

        add(createBrandPanel(), BorderLayout.NORTH);
        add(createMenuPanel(), BorderLayout.CENTER);
        add(createFooterPanel(), BorderLayout.SOUTH);
    }

    private JComponent createBrandPanel() {
        JPanel brand = new JPanel();
        brand.setLayout(new BoxLayout(brand, BoxLayout.Y_AXIS));
        brand.setBackground(new Color(34, 44, 82));
        brand.setBorder(new EmptyBorder(24, 20, 24, 20));

        JLabel icon = new JLabel("\uD83C\uDF7D", SwingConstants.CENTER);
        icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 46));
        icon.setForeground(Color.WHITE);
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel("POS System", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(new EmptyBorder(12, 0, 0, 0));

        brand.add(icon);
        brand.add(title);
        return brand;
    }

    private JComponent createMenuPanel() {
        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.setBackground(new Color(34, 44, 82));
        menu.setBorder(new EmptyBorder(10, 0, 10, 0));

        String[] menuItems = {
            "Home", "Categories", "Products",
            "Tables", "Staff", "POS",
            "Kitchen", "Reports", "Settings"
        };

        for (String item : menuItems) {
            JButton btn = createMenuButton(item);
            menu.add(btn);
            menu.add(Box.createVerticalStrut(8));
        }

        return menu;
    }

    private JButton createMenuButton(String text) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setBackground(new Color(34, 44, 82));
        btn.setForeground(Color.WHITE);
        btn.setOpaque(true);
        btn.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(e -> listener.onMenuSelected(text));
        return btn;
    }

    private JComponent createFooterPanel() {
        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(new Color(34, 44, 82));
        footer.setBorder(new EmptyBorder(16, 20, 20, 20));

        JButton exitBtn = new JButton("Exit");
        exitBtn.setFocusPainted(false);
        exitBtn.setBackground(new Color(211, 47, 47));
        exitBtn.setForeground(Color.WHITE);
        exitBtn.setOpaque(true);
        exitBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exitBtn.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        exitBtn.addActionListener(e -> System.exit(0));

        footer.add(exitBtn, BorderLayout.SOUTH);
        return footer;
    }

    private static class PlateIcon implements Icon {
        private final int size;
        private final Color color;

        public PlateIcon(int size, Color color) {
            this.size = size;
            this.color = color;
        }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int plateSize = size;
            int plateX = x;
            int plateY = y;
            int plateMargin = 4;

            g2.setColor(color);
            g2.setStroke(new BasicStroke(4f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.draw(new Ellipse2D.Float(plateX + plateMargin, plateY + plateMargin, plateSize - plateMargin * 2, plateSize - plateMargin * 2));

            int forkStartX = plateX + 18;
            int spoonStartX = plateX + plateSize - 18;
            int utensilTop = plateY + 14;
            int utensilBottom = plateY + plateSize - 14;

            g2.setStroke(new BasicStroke(4f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.draw(new Line2D.Float(forkStartX, utensilTop, forkStartX + 14, utensilBottom - 6));
            g2.draw(new Line2D.Float(forkStartX + 4, utensilTop + 2, forkStartX + 18, utensilTop + 16));
            g2.draw(new Line2D.Float(forkStartX + 10, utensilTop + 4, forkStartX + 24, utensilTop + 18));

            g2.draw(new Line2D.Float(spoonStartX, utensilBottom - 8, spoonStartX - 20, utensilTop + 8));
            g2.draw(new Ellipse2D.Float(spoonStartX - 28, utensilTop + 4, 16, 26));

            g2.dispose();
        }

        @Override
        public int getIconWidth() {
            return size;
        }

        @Override
        public int getIconHeight() {
            return size;
        }
    }
}