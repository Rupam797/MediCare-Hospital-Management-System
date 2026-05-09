package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    JTextField textField;
    JPasswordField jPasswordField;
    JButton b1, b2;

    Login() {
        // â”€â”€â”€ Gradient background panel â”€â”€â”€
        JPanel bgPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gp = new GradientPaint(0, 0, UITheme.BG_PRIMARY,
                        getWidth(), getHeight(), UITheme.BG_SECONDARY);
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        bgPanel.setLayout(new GridBagLayout());
        setContentPane(bgPanel);

        // â”€â”€â”€ Center login card â”€â”€â”€
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // Card background
                g2.setColor(new Color(0x0f, 0x34, 0x60, 230));
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20));
                // Card border
                g2.setColor(UITheme.BORDER);
                g2.setStroke(new BasicStroke(1.5f));
                g2.draw(new RoundRectangle2D.Float(1, 1, getWidth() - 2, getHeight() - 2, 20, 20));
                g2.dispose();
            }
        };
        card.setOpaque(false);
        card.setLayout(new GridBagLayout());
        card.setPreferredSize(new Dimension(440, 480));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 30, 6, 30);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridwidth = 2;

        // â”€â”€â”€ Hospital Logo â”€â”€â”€
        gbc.gridy = 0;
        gbc.insets = new Insets(25, 30, 5, 30);
        try {
            ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/logo1.png"));
            Image i1 = imageIcon.getImage().getScaledInstance(80, 72, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(i1));
            logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
            card.add(logoLabel, gbc);
        } catch (Exception ignored) {}

        // â”€â”€â”€ Title â”€â”€â”€
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 30, 2, 30);
        JLabel titleLabel = new JLabel("MediCare HMS");
        titleLabel.setFont(UITheme.headingFont());
        titleLabel.setForeground(UITheme.ACCENT);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(titleLabel, gbc);

        // â”€â”€â”€ Subtitle â”€â”€â”€
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 30, 20, 30);
        JLabel subtitleLabel = new JLabel("Hospital Management System");
        subtitleLabel.setFont(UITheme.smallFont());
        subtitleLabel.setForeground(UITheme.TEXT_SECONDARY);
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(subtitleLabel, gbc);

        // â”€â”€â”€ Username label â”€â”€â”€
        gbc.gridy = 3;
        gbc.insets = new Insets(5, 30, 2, 30);
        JLabel namelabel = UITheme.createFormLabel("Username");
        card.add(namelabel, gbc);

        // â”€â”€â”€ Username field â”€â”€â”€
        gbc.gridy = 4;
        gbc.insets = new Insets(2, 30, 10, 30);
        textField = UITheme.createStyledTextField();
        card.add(textField, gbc);

        // â”€â”€â”€ Password label â”€â”€â”€
        gbc.gridy = 5;
        gbc.insets = new Insets(5, 30, 2, 30);
        JLabel passwordLabel = UITheme.createFormLabel("Password");
        card.add(passwordLabel, gbc);

        // â”€â”€â”€ Password field â”€â”€â”€
        gbc.gridy = 6;
        gbc.insets = new Insets(2, 30, 20, 30);
        jPasswordField = UITheme.createStyledPasswordField();
        card.add(jPasswordField, gbc);

        // â”€â”€â”€ Buttons panel â”€â”€â”€
        gbc.gridy = 7;
        gbc.insets = new Insets(5, 30, 25, 30);
        JPanel btnPanel = new JPanel(new GridLayout(1, 2, 12, 0));
        btnPanel.setOpaque(false);

        b1 = UITheme.createStyledButton("Login", UITheme.ButtonType.PRIMARY);
        b1.addActionListener(this);
        btnPanel.add(b1);

        b2 = UITheme.createStyledButton("Cancel", UITheme.ButtonType.OUTLINE);
        b2.addActionListener(this);
        btnPanel.add(b2);

        card.add(btnPanel, gbc);

        // â”€â”€â”€ Add card to background â”€â”€â”€
        bgPanel.add(card);

        // â”€â”€â”€ Frame setup â”€â”€â”€
        UITheme.setupFrame(this, "MediCare HMS â€” Login", 600, 580);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(500, 520));
        setVisible(true);
    }

    public static void main(String[] args) {
        UITheme.installTheme();
        SwingUtilities.invokeLater(Login::new);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            try {
                Conn c = new Conn();
                String user = textField.getText();
                String pass = new String(jPasswordField.getPassword());

                String q = "select* from login where ID='" + user + "' and PW='" + pass + "'";
                ResultSet resultSet = c.statement.executeQuery(q);

                if (resultSet.next()) {
                    new Reception();
                    setVisible(false);
                } else {
                    UITheme.showError(this, "Invalid username or password!");
                }

            } catch (Exception E) {
                E.printStackTrace();
            }
        } else {
            System.exit(0);
        }
    }
}
