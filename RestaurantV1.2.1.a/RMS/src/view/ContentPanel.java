package view;

import javax.swing.*;
import java.awt.*;

public class ContentPanel extends JPanel {

    public ContentPanel() {
        setLayout(new BorderLayout());
        showPanel(new HomePanel());
    }

    public void showPanel(JComponent panel) {
        removeAll();
        add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}