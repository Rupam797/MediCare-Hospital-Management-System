package hospital.management.system;



import javax.swing.*;
import java.awt.*;

import java.sql.ResultSet;

public class Search_room extends JFrame {

    JComboBox<String> choice;
    JTable table;

    Search_room() {
        // â”€â”€â”€ Background â”€â”€â”€
        JPanel bgPanel = UITheme.createGradientPanel();
        bgPanel.setLayout(new BorderLayout(0, 15));
        bgPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setContentPane(bgPanel);

        // â”€â”€â”€ Header â”€â”€â”€
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        
        JLabel titleLabel = UITheme.createTitleLabel("🔍 Search Rooms");
        headerPanel.add(titleLabel, BorderLayout.WEST);
        
        // â”€â”€â”€ Filter Area â”€â”€â”€
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        filterPanel.setOpaque(false);

        JLabel label2 = UITheme.createFormLabel("Status:");
        filterPanel.add(label2);

        choice = UITheme.createStyledComboBox(new String[]{"Available", "Occupied"});
        filterPanel.add(choice);

        JButton searchBtn = UITheme.createStyledButton("Search", UITheme.ButtonType.PRIMARY);
        searchBtn.addActionListener(e -> {
            String q = "select * from Room where Availablity='" + choice.getSelectedItem() + "'";
            try {
                Conn c = new Conn();
                ResultSet resultSet = c.statement.executeQuery(q);
                table.setModel(UITheme.resultSetToTableModel(resultSet));
            } catch (Exception E) {
                E.printStackTrace();
            }
        });
        filterPanel.add(searchBtn);
        
        headerPanel.add(filterPanel, BorderLayout.EAST);
        bgPanel.add(headerPanel, BorderLayout.NORTH);

        // â”€â”€â”€ Main Card with Table â”€â”€â”€
        JPanel card = UITheme.createCardPanel();
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        table = new JTable();
        
        try {
            Conn c = new Conn();
            String q = "select * from room";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(UITheme.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = UITheme.createStyledTable(table);
        card.add(scrollPane, BorderLayout.CENTER);

        bgPanel.add(card, BorderLayout.CENTER);

        // â”€â”€â”€ Footer with Back Button â”€â”€â”€
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.setOpaque(false);

        JButton backBtn = UITheme.createStyledButton("Back", UITheme.ButtonType.OUTLINE);
        backBtn.addActionListener(e -> {
            setVisible(false);
            dispose();
        });
        footerPanel.add(backBtn);

        bgPanel.add(footerPanel, BorderLayout.SOUTH);

        // â”€â”€â”€ Frame Setup â”€â”€â”€
        UITheme.setupFrame(this, "MediCare HMS â€” Search Rooms", 900, 600);
        setVisible(true);
    }

    public static void main(String[] args) {
        UITheme.installTheme();
        SwingUtilities.invokeLater(Search_room::new);
    }
}
