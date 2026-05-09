package hospital.management.system;

import javax.swing.*;
import java.awt.*;

import java.sql.ResultSet;
import java.util.Date;

public class Patient_discharge extends JFrame {

    JComboBox<String> choice;
    JLabel RNo, INTime, OUTime;

    Patient_discharge() {
        // ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ Background ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬
        JPanel bgPanel = UITheme.createGradientPanel();
        bgPanel.setLayout(new GridBagLayout());
        setContentPane(bgPanel);

        // ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ Main Card ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬
        JPanel card = UITheme.createCardPanel();
        card.setLayout(new GridBagLayout());
        card.setPreferredSize(new Dimension(500, 450));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 25, 10, 25);
        gbc.gridx = 0;
        gbc.gridwidth = 2;

        // ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ Title ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬
        gbc.gridy = 0;
        gbc.insets = new Insets(25, 25, 5, 25);
        JLabel titleLabel = UITheme.createTitleLabel("Patient Discharge");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(titleLabel, gbc);

        // ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ Separator ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 25, 15, 25);
        card.add(UITheme.createSeparator(), gbc);

        // 2-column layout
        gbc.gridwidth = 1;

        // ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ Patient ID ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬
        gbc.gridy = 2; gbc.gridx = 0;
        gbc.insets = new Insets(10, 25, 10, 10);
        gbc.weightx = 0.4;
        card.add(UITheme.createFormLabel("Patient ID"), gbc);
        
        gbc.gridx = 1;
        gbc.insets = new Insets(10, 10, 10, 25);
        gbc.weightx = 0.6;
        
        java.util.ArrayList<String> patients = new java.util.ArrayList<>();
        try {
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("select * from patient_info");
            while (resultSet.next()) {
                patients.add(resultSet.getString("number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        choice = UITheme.createStyledComboBox(patients.toArray(new String[0]));
        card.add(choice, gbc);

        // ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ Room Number ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬
        gbc.gridy = 3; gbc.gridx = 0;
        gbc.insets = new Insets(10, 25, 10, 10);
        card.add(UITheme.createFormLabel("Room Number"), gbc);
        
        gbc.gridx = 1;
        gbc.insets = new Insets(10, 10, 10, 25);
        RNo = UITheme.createValueLabel("ÃƒÂ¢Ã¢â€šÂ¬Ã¢â‚¬Â");
        card.add(RNo, gbc);

        // ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ In Time ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬
        gbc.gridy = 4; gbc.gridx = 0;
        gbc.insets = new Insets(10, 25, 10, 10);
        card.add(UITheme.createFormLabel("Admission Time"), gbc);
        
        gbc.gridx = 1;
        gbc.insets = new Insets(10, 10, 10, 25);
        INTime = UITheme.createValueLabel("ÃƒÂ¢Ã¢â€šÂ¬Ã¢â‚¬Â");
        card.add(INTime, gbc);

        // ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ Out Time ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬
        gbc.gridy = 5; gbc.gridx = 0;
        gbc.insets = new Insets(10, 25, 10, 10);
        card.add(UITheme.createFormLabel("Discharge Time"), gbc);
        
        gbc.gridx = 1;
        gbc.insets = new Insets(10, 10, 10, 25);
        Date date = new Date();
        OUTime = UITheme.createValueLabel("" + date);
        card.add(OUTime, gbc);

        // ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ Buttons ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬
        gbc.gridy = 6; gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(25, 25, 25, 25);
        
        JPanel btnPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        btnPanel.setOpaque(false);

        JButton checkBtn = UITheme.createStyledButton("Check", UITheme.ButtonType.PRIMARY);
        checkBtn.addActionListener(e -> {
            Conn c = new Conn();
            try {
                ResultSet resultSet = c.statement.executeQuery("select * from patient_info where number='" + choice.getSelectedItem() + "'");
                while (resultSet.next()) {
                    RNo.setText(resultSet.getString("Room_number"));
                    INTime.setText(resultSet.getString("Time"));
                }
            } catch (Exception E) {
                E.printStackTrace();
            }
        });
        btnPanel.add(checkBtn);

        JButton dischargeBtn = UITheme.createStyledButton("Discharge", UITheme.ButtonType.DANGER);
        dischargeBtn.addActionListener(e -> {
            Conn c = new Conn();
            try {
                c.statement.executeUpdate("delete from patient_info where number='" + choice.getSelectedItem() + "'");
                c.statement.executeUpdate("update room set Availablity='Available' where Room_no='" + RNo.getText() + "'");
                UITheme.showSuccess(this, "Patient discharged successfully.");
                setVisible(false);
                dispose();
            } catch (Exception E) {
                E.printStackTrace();
            }
        });
        btnPanel.add(dischargeBtn);

        JButton backBtn = UITheme.createStyledButton("Back", UITheme.ButtonType.OUTLINE);
        backBtn.addActionListener(e -> {
            setVisible(false);
            dispose();
        });
        btnPanel.add(backBtn);

        card.add(btnPanel, gbc);
        bgPanel.add(card);

        // ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ Frame Setup ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬ÃƒÂ¢Ã¢â‚¬ÂÃ¢â€šÂ¬
        UITheme.setupFrame(this, "MediCare HMS - Patient Discharge", 600, 550);
        setVisible(true);
    }

    public static void main(String[] args) {
        UITheme.installTheme();
        SwingUtilities.invokeLater(Patient_discharge::new);
    }
}
