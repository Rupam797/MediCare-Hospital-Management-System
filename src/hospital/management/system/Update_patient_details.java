package hospital.management.system;

import javax.swing.*;
import java.awt.*;

import java.sql.ResultSet;

public class Update_patient_details extends JFrame {

    JComboBox<String> choice;
    JTextField textFieldRoom, textFieldINTime, textFieldAmount, textFieldPending;

    Update_patient_details() {
        // ─── Background ───
        JPanel bgPanel = UITheme.createGradientPanel();
        bgPanel.setLayout(new GridBagLayout());
        setContentPane(bgPanel);

        // ─── Main Card ───
        JPanel card = UITheme.createCardPanel();
        card.setLayout(new GridBagLayout());
        card.setPreferredSize(new Dimension(550, 500));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 25, 8, 25);
        gbc.gridx = 0;
        gbc.gridwidth = 2;

        // ─── Title ───
        gbc.gridy = 0;
        gbc.insets = new Insets(25, 25, 5, 25);
        JLabel titleLabel = UITheme.createTitleLabel("\u270F\uFE0F Update Patient Details");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(titleLabel, gbc);

        // ─── Separator ───
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 25, 15, 25);
        card.add(UITheme.createSeparator(), gbc);

        // 2-column layout
        gbc.gridwidth = 1;

        // ─── Name ───
        gbc.gridy = 2; gbc.gridx = 0;
        gbc.insets = new Insets(8, 25, 8, 10);
        gbc.weightx = 0.3;
        card.add(UITheme.createFormLabel("Patient Name"), gbc);
        
        gbc.gridx = 1;
        gbc.insets = new Insets(8, 10, 8, 25);
        gbc.weightx = 0.7;
        
        java.util.ArrayList<String> patients = new java.util.ArrayList<>();
        try {
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("select * from patient_info");
            while (resultSet.next()) {
                patients.add(resultSet.getString("Name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        choice = UITheme.createStyledComboBox(patients.toArray(new String[0]));
        card.add(choice, gbc);

        // ─── Room Number ───
        gbc.gridy = 3; gbc.gridx = 0;
        gbc.insets = new Insets(8, 25, 8, 10);
        card.add(UITheme.createFormLabel("Room Number"), gbc);
        
        gbc.gridx = 1;
        gbc.insets = new Insets(8, 10, 8, 25);
        textFieldRoom = UITheme.createStyledTextField();
        card.add(textFieldRoom, gbc);

        // ─── In Time ───
        gbc.gridy = 4; gbc.gridx = 0;
        gbc.insets = new Insets(8, 25, 8, 10);
        card.add(UITheme.createFormLabel("Admission Time"), gbc);
        
        gbc.gridx = 1;
        gbc.insets = new Insets(8, 10, 8, 25);
        textFieldINTime = UITheme.createStyledTextField();
        card.add(textFieldINTime, gbc);

        // ─── Amount Paid ───
        gbc.gridy = 5; gbc.gridx = 0;
        gbc.insets = new Insets(8, 25, 8, 10);
        card.add(UITheme.createFormLabel("Amount Paid (₹)"), gbc);
        
        gbc.gridx = 1;
        gbc.insets = new Insets(8, 10, 8, 25);
        textFieldAmount = UITheme.createStyledTextField();
        card.add(textFieldAmount, gbc);

        // ─── Pending ───
        gbc.gridy = 6; gbc.gridx = 0;
        gbc.insets = new Insets(8, 25, 8, 10);
        card.add(UITheme.createFormLabel("Pending Amount (₹)"), gbc);
        
        gbc.gridx = 1;
        gbc.insets = new Insets(8, 10, 8, 25);
        textFieldPending = UITheme.createStyledTextField();
        textFieldPending.setEditable(false);
        card.add(textFieldPending, gbc);

        // ─── Buttons ───
        gbc.gridy = 7; gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(25, 25, 25, 25);
        
        JPanel btnPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        btnPanel.setOpaque(false);

        JButton checkBtn = UITheme.createStyledButton("Check", UITheme.ButtonType.PRIMARY);
        checkBtn.addActionListener(e -> {
            String id = (String) choice.getSelectedItem();
            String q = "select * from Patient_info where Name='" + id + "'";
            try {
                Conn c = new Conn();
                ResultSet resultSet = c.statement.executeQuery(q);
                while (resultSet.next()) {
                    textFieldRoom.setText(resultSet.getString("Room_NUmber"));
                    textFieldINTime.setText(resultSet.getString("Time"));
                    textFieldAmount.setText(resultSet.getString("Deposite"));
                }
                ResultSet resultSet2 = c.statement.executeQuery("select * from room where Room_no='" + textFieldRoom.getText() + "'");
                while (resultSet2.next()) {
                    String price = resultSet2.getString("Price");
                    int amountPaid = Integer.parseInt(price) - Integer.parseInt(textFieldAmount.getText());
                    textFieldPending.setText("" + amountPaid);
                }
            } catch (Exception E) {
                E.printStackTrace();
            }
        });
        btnPanel.add(checkBtn);

        JButton updateBtn = UITheme.createStyledButton("Update", UITheme.ButtonType.SUCCESS);
        updateBtn.addActionListener(e -> {
            try {
                Conn c = new Conn();
                String q = (String) choice.getSelectedItem();
                String room = textFieldRoom.getText();
                String time = textFieldINTime.getText();
                String amount = textFieldAmount.getText();
                c.statement.executeUpdate("update Patient_info set Room_Number='" + room + "',Time='" + time + "',Deposite='" + amount + "' where name ='" + q + "'");
                UITheme.showSuccess(this, "Patient details updated successfully.");
                setVisible(false);
                dispose();
            } catch (Exception p) {
                p.printStackTrace();
            }
        });
        btnPanel.add(updateBtn);

        JButton backBtn = UITheme.createStyledButton("Back", UITheme.ButtonType.OUTLINE);
        backBtn.addActionListener(e -> {
            setVisible(false);
            dispose();
        });
        btnPanel.add(backBtn);

        card.add(btnPanel, gbc);
        bgPanel.add(card);

        // ─── Frame Setup ───
        UITheme.setupFrame(this, "MediCare HMS - Update Patient Details", 650, 600);
        setVisible(true);
    }

    public static void main(String[] args) {
        UITheme.installTheme();
        SwingUtilities.invokeLater(Update_patient_details::new);
    }
}
