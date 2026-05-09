package hospital.management.system;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;

import java.sql.ResultSet;

public class Reception extends JFrame {

    Reception() {
        UITheme.installTheme();

        // â”€â”€â”€ Main layout â”€â”€â”€
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(UITheme.BG_PRIMARY);
        setContentPane(mainPanel);

        // â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â• LEFT SIDEBAR â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•
        JPanel sidebar = new JPanel();
        sidebar.setBackground(UITheme.BG_SECONDARY);
        sidebar.setPreferredSize(new Dimension(250, 0));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, UITheme.BORDER));

        // Logo area
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(UITheme.BG_SECONDARY);
        logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.Y_AXIS));
        logoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 15, 20));
        logoPanel.setMaximumSize(new Dimension(250, 100));
        logoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        try {
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/logo1.png"));
            Image image = i1.getImage().getScaledInstance(45, 40, Image.SCALE_SMOOTH);
            JLabel logoIcon = new JLabel(new ImageIcon(image));
            logoIcon.setAlignmentX(Component.LEFT_ALIGNMENT);
            logoPanel.add(logoIcon);
        } catch (Exception ignored) {}

        logoPanel.add(Box.createVerticalStrut(8));
        JLabel brandLabel = new JLabel("MediCare HMS");
        brandLabel.setFont(UITheme.subheadingFont());
        brandLabel.setForeground(UITheme.ACCENT);
        brandLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        logoPanel.add(brandLabel);

        JLabel tagline = new JLabel("Hospital Management");
        tagline.setFont(UITheme.smallFont());
        tagline.setForeground(UITheme.TEXT_MUTED);
        tagline.setAlignmentX(Component.LEFT_ALIGNMENT);
        logoPanel.add(tagline);

        sidebar.add(logoPanel);
        sidebar.add(Box.createVerticalStrut(10));

        // Separator
        JSeparator sep = UITheme.createSeparator();
        sep.setMaximumSize(new Dimension(250, 2));
        sidebar.add(sep);
        sidebar.add(Box.createVerticalStrut(10));

        // â”€â”€â”€ Navigation Menu Label â”€â”€â”€
        JLabel menuLabel = new JLabel("  MAIN MENU");
        menuLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
        menuLabel.setForeground(UITheme.TEXT_MUTED);
        menuLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        menuLabel.setBorder(BorderFactory.createEmptyBorder(5, 15, 8, 0));
        menuLabel.setMaximumSize(new Dimension(250, 25));
        sidebar.add(menuLabel);

        // Nav buttons
        String[] navLabels = {
            "\u2795  Add New Patient",
            "🛏️  Room Management",
            "🏢  Departments",
            "👥  Employee Info",
            "📋  Patient Info",
            "\u2705  Patient Discharge",
            "\u270F  Update Patient",
            "🚑  Ambulance",
            "🔍  Search Room"
        };

        ActionListener[] navActions = {
            e -> new NEW_PATIENT(),
            e -> new Room(),
            e -> new Department(),
            e -> new Employee_info(),
            e -> new Patient_info(),
            e -> new Patient_discharge(),
            e -> new Update_patient_details(),
            e -> new Ambulance(),
            e -> new Search_room()
        };

        for (int i = 0; i < navLabels.length; i++) {
            JButton navBtn = UITheme.createNavButton(navLabels[i], false);
            navBtn.addActionListener(navActions[i]);
            sidebar.add(navBtn);
        }

        sidebar.add(Box.createVerticalGlue());

        // Logout at bottom
        sidebar.add(Box.createVerticalStrut(10));
        JSeparator sep2 = UITheme.createSeparator();
        sep2.setMaximumSize(new Dimension(250, 2));
        sidebar.add(sep2);

        JButton logoutBtn = UITheme.createNavButton("🚪  Logout", false);
        logoutBtn.setForeground(UITheme.DANGER);
        logoutBtn.addActionListener(e -> {
            setVisible(false);
            dispose();
            new Login();
        });
        sidebar.add(logoutBtn);
        sidebar.add(Box.createVerticalStrut(15));

        mainPanel.add(sidebar, BorderLayout.WEST);

        // â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â• MAIN CONTENT â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(UITheme.BG_PRIMARY);

        // â”€â”€â”€ Header banner â”€â”€â”€
        JPanel headerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gp = new GradientPaint(0, 0, UITheme.BG_CARD,
                        getWidth(), 0, new Color(0x00, 0x6d, 0x8f));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(0, 140));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(25, 35, 25, 35));

        JPanel headerText = new JPanel();
        headerText.setOpaque(false);
        headerText.setLayout(new BoxLayout(headerText, BoxLayout.Y_AXIS));

        JLabel welcomeLabel = new JLabel("Welcome Back!");
        welcomeLabel.setFont(UITheme.headingFont());
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        headerText.add(welcomeLabel);

        headerText.add(Box.createVerticalStrut(6));

        JLabel welcomeSub = new JLabel("Manage your hospital operations from the dashboard below.");
        welcomeSub.setFont(UITheme.bodyFont());
        welcomeSub.setForeground(new Color(0xb0, 0xd0, 0xe0));
        welcomeSub.setAlignmentX(Component.LEFT_ALIGNMENT);
        headerText.add(welcomeSub);

        headerPanel.add(headerText, BorderLayout.CENTER);

        // Doctor image on right
        try {
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/dr.png"));
            Image img = i1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            JLabel drLabel = new JLabel(new ImageIcon(img));
            drLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            headerPanel.add(drLabel, BorderLayout.EAST);
        } catch (Exception ignored) {}

        contentPanel.add(headerPanel, BorderLayout.NORTH);

        // â”€â”€â”€ Stats Cards Grid â”€â”€â”€
        JPanel cardsArea = new JPanel();
        cardsArea.setBackground(UITheme.BG_PRIMARY);
        cardsArea.setBorder(BorderFactory.createEmptyBorder(25, 35, 25, 35));
        cardsArea.setLayout(new BoxLayout(cardsArea, BoxLayout.Y_AXIS));

        JLabel overviewLabel = new JLabel("Quick Overview");
        overviewLabel.setFont(UITheme.subheadingFont());
        overviewLabel.setForeground(UITheme.TEXT_PRIMARY);
        overviewLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        cardsArea.add(overviewLabel);
        cardsArea.add(Box.createVerticalStrut(15));

        // Fetch stats from DB
        String patientCount = "â€”", roomCount = "â€”", deptCount = "â€”", ambCount = "â€”";
        try {
            Conn c = new Conn();
            ResultSet rs1 = c.statement.executeQuery("SELECT COUNT(*) FROM patient_info");
            if (rs1.next()) patientCount = rs1.getString(1);

            ResultSet rs2 = c.statement.executeQuery("SELECT COUNT(*) FROM Room WHERE Availablity='Available'");
            if (rs2.next()) roomCount = rs2.getString(1);

            ResultSet rs3 = c.statement.executeQuery("SELECT COUNT(*) FROM department");
            if (rs3.next()) deptCount = rs3.getString(1);

            ResultSet rs4 = c.statement.executeQuery("SELECT COUNT(*) FROM Ambulance");
            if (rs4.next()) ambCount = rs4.getString(1);
        } catch (Exception ignored) {}

        JPanel cardsGrid = new JPanel(new GridLayout(1, 4, 18, 0));
        cardsGrid.setOpaque(false);
        cardsGrid.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        cardsGrid.setAlignmentX(Component.LEFT_ALIGNMENT);

        cardsGrid.add(UITheme.createStatsCard("Total Patients", patientCount, UITheme.ACCENT));
        cardsGrid.add(UITheme.createStatsCard("Available Rooms", roomCount, UITheme.SUCCESS));
        cardsGrid.add(UITheme.createStatsCard("Departments", deptCount, UITheme.WARNING));
        cardsGrid.add(UITheme.createStatsCard("Ambulances", ambCount, UITheme.DANGER));

        cardsArea.add(cardsGrid);
        cardsArea.add(Box.createVerticalStrut(30));

        // â”€â”€â”€ Quick Actions â”€â”€â”€
        JLabel actionsLabel = new JLabel("Quick Actions");
        actionsLabel.setFont(UITheme.subheadingFont());
        actionsLabel.setForeground(UITheme.TEXT_PRIMARY);
        actionsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        cardsArea.add(actionsLabel);
        cardsArea.add(Box.createVerticalStrut(15));

        JPanel actionsGrid = new JPanel(new GridLayout(1, 3, 18, 0));
        actionsGrid.setOpaque(false);
        actionsGrid.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        actionsGrid.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton qAddPatient = UITheme.createStyledButton("+ New Patient", UITheme.ButtonType.PRIMARY);
        qAddPatient.addActionListener(e -> new NEW_PATIENT());
        actionsGrid.add(qAddPatient);

        JButton qDischarge = UITheme.createStyledButton("Discharge Patient", UITheme.ButtonType.SUCCESS);
        qDischarge.addActionListener(e -> new Patient_discharge());
        actionsGrid.add(qDischarge);

        JButton qSearch = UITheme.createStyledButton("Search Rooms", UITheme.ButtonType.OUTLINE);
        qSearch.addActionListener(e -> new Search_room());
        actionsGrid.add(qSearch);

        cardsArea.add(actionsGrid);
        cardsArea.add(Box.createVerticalGlue());

        contentPanel.add(cardsArea, BorderLayout.CENTER);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // â”€â”€â”€ Frame setup â”€â”€â”€
        UITheme.setupFrame(this, "MediCare HMS â€” Dashboard", 1100, 700);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(900, 600));
        setVisible(true);
    }

    public static void main(String[] args) {
        UITheme.installTheme();
        SwingUtilities.invokeLater(Reception::new);
    }
}
