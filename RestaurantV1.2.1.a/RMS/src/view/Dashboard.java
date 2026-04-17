package view;

import model.User;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Dashboard extends JFrame implements SidebarListener {

    private final ContentPanel contentPanel;
    private final User user;

    public Dashboard() {
        this(new User("User", "Admin"));
    }

    public Dashboard(User user) {
        this.user = user;
        setTitle("Restaurant Management System");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(244, 248, 253));

        SidebarPanel sidebar = new SidebarPanel(this);
        contentPanel = new ContentPanel();

        add(sidebar, BorderLayout.WEST);
        add(createTopBar(), BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        contentPanel.showPanel(new HomePanel());
        setVisible(true);
    }

    private JComponent createTopBar() {
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(Color.WHITE);
        topBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(221, 224, 235)));
        topBar.setPreferredSize(new Dimension(0, 68));

        JLabel titleLabel = new JLabel("Dashboard");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(new Color(215, 72, 120));
        titleLabel.setBorder(new EmptyBorder(0, 24, 0, 0));

        JLabel userInfo = new JLabel("Username:" + "    " + user.getRole(), SwingConstants.RIGHT);
        userInfo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        userInfo.setForeground(new Color(102, 112, 131));
        userInfo.setBorder(new EmptyBorder(0, 0, 0, 24));

        topBar.add(titleLabel, BorderLayout.WEST);
        topBar.add(userInfo, BorderLayout.EAST);
        return topBar;
    }

    @Override
    public void onMenuSelected(String menu) {
        switch (menu) {
            case "Home":
                contentPanel.showPanel(new HomePanel());
                break;
            case "Categories":
                contentPanel.showPanel(new PlaceholderPanel("Categories"));
                break;
            case "Products":
                contentPanel.showPanel(new PlaceholderPanel("Products"));
                break;
            case "Tables":
                contentPanel.showPanel(new PlaceholderPanel("Tables"));
                break;
            case "Staff":
                contentPanel.showPanel(new PlaceholderPanel("Staff"));
                break;
            case "POS":
                contentPanel.showPanel(new POSPanel());
                break;
            case "Kitchen":
                contentPanel.showPanel(new PlaceholderPanel("Kitchen"));
                break;
            case "Reports":
                contentPanel.showPanel(new PlaceholderPanel("Reports"));
                break;
            case "Settings":
                contentPanel.showPanel(new PlaceholderPanel("Settings"));
                break;
            default:
                contentPanel.showPanel(new PlaceholderPanel(menu));
        }
    }
}