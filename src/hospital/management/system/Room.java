package hospital.management.system;



import javax.swing.*;
import java.awt.*;

import java.sql.ResultSet;

public class Room extends JFrame {

    JTable table;

    Room() {
        // ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ Background ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬
        JPanel bgPanel = UITheme.createGradientPanel();
        bgPanel.setLayout(new BorderLayout(0, 15));
        bgPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setContentPane(bgPanel);

        // ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ Header ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        
        JLabel titleLabel = UITheme.createTitleLabel("Ã°Å¸â€ºÂÃ¯Â¸Â\uFE0F Room Management");
        headerPanel.add(titleLabel, BorderLayout.WEST);
        
        bgPanel.add(headerPanel, BorderLayout.NORTH);

        // ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ Main Card with Table ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬
        JPanel card = UITheme.createCardPanel();
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        table = new JTable();
        
        try {
            Conn c = new Conn();
            String q = "select * from Room";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(UITheme.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = UITheme.createStyledTable(table);
        card.add(scrollPane, BorderLayout.CENTER);

        bgPanel.add(card, BorderLayout.CENTER);

        // ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ Footer with Back Button ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.setOpaque(false);

        JButton backBtn = UITheme.createStyledButton("Back", UITheme.ButtonType.OUTLINE);
        backBtn.addActionListener(e -> {
            setVisible(false);
            dispose();
        });
        footerPanel.add(backBtn);

        bgPanel.add(footerPanel, BorderLayout.SOUTH);

        // ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ Frame Setup ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬
        UITheme.setupFrame(this, "MediCare HMS - Room Management", 900, 600);
        setVisible(true);
    }

    public static void main(String[] args) {
        UITheme.installTheme();
        SwingUtilities.invokeLater(Room::new);
    }
}
