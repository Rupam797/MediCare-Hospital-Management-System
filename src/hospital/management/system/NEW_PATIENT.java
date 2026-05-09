package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.util.Date;

public class NEW_PATIENT extends JFrame implements ActionListener {

    JComboBox<String> comboBox;
    JTextField textFieldNumber, textName, textFieldDisease, textFieldDeposite;
    JRadioButton r1, r2, r3;
    JComboBox<String> roomCombo;
    JLabel date;
    JButton b1, b2;

    NEW_PATIENT() {
        // â”€â”€â”€ Background â”€â”€â”€
        JPanel bgPanel = UITheme.createGradientPanel();
        bgPanel.setLayout(new GridBagLayout());
        setContentPane(bgPanel);

        // â”€â”€â”€ Main card â”€â”€â”€
        JPanel card = UITheme.createCardPanel();
        card.setLayout(new GridBagLayout());
        card.setPreferredSize(new Dimension(600, 580));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 25, 5, 25);
        gbc.gridx = 0;
        gbc.gridwidth = 2;

        // â”€â”€â”€ Title â”€â”€â”€
        gbc.gridy = 0;
        gbc.insets = new Insets(25, 25, 5, 25);
        JLabel titleLabel = UITheme.createTitleLabel("New Patient Registration");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(titleLabel, gbc);

        // â”€â”€â”€ Separator â”€â”€â”€
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 25, 15, 25);
        card.add(UITheme.createSeparator(), gbc);

        // Now use 2-column layout for form fields
        gbc.gridwidth = 1;

        // â”€â”€â”€ ID Type â”€â”€â”€
        gbc.gridy = 2; gbc.gridx = 0;
        gbc.insets = new Insets(5, 25, 5, 10);
        gbc.weightx = 0.3;
        card.add(UITheme.createFormLabel("ID Type"), gbc);
        gbc.gridx = 1;
        gbc.insets = new Insets(5, 10, 5, 25);
        gbc.weightx = 0.7;
        comboBox = UITheme.createStyledComboBox(new String[]{"Aadhar Card", "Voter Id", "Driving License"});
        card.add(comboBox, gbc);

        // â”€â”€â”€ Number â”€â”€â”€
        gbc.gridy = 3; gbc.gridx = 0;
        gbc.insets = new Insets(5, 25, 5, 10);
        gbc.weightx = 0.3;
        card.add(UITheme.createFormLabel("Number"), gbc);
        gbc.gridx = 1;
        gbc.insets = new Insets(5, 10, 5, 25);
        gbc.weightx = 0.7;
        textFieldNumber = UITheme.createStyledTextField();
        card.add(textFieldNumber, gbc);

        // â”€â”€â”€ Name â”€â”€â”€
        gbc.gridy = 4; gbc.gridx = 0;
        gbc.insets = new Insets(5, 25, 5, 10);
        gbc.weightx = 0.3;
        card.add(UITheme.createFormLabel("Patient Name"), gbc);
        gbc.gridx = 1;
        gbc.insets = new Insets(5, 10, 5, 25);
        gbc.weightx = 0.7;
        textName = UITheme.createStyledTextField();
        card.add(textName, gbc);

        // â”€â”€â”€ Gender â”€â”€â”€
        gbc.gridy = 5; gbc.gridx = 0;
        gbc.insets = new Insets(5, 25, 5, 10);
        gbc.weightx = 0.3;
        card.add(UITheme.createFormLabel("Gender"), gbc);
        gbc.gridx = 1;
        gbc.insets = new Insets(5, 10, 5, 25);
        gbc.weightx = 0.7;

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        genderPanel.setOpaque(false);
        r1 = UITheme.createStyledRadioButton("Male");
        r2 = UITheme.createStyledRadioButton("Female");
        r3 = UITheme.createStyledRadioButton("Others");
        ButtonGroup bg = new ButtonGroup();
        bg.add(r1); bg.add(r2); bg.add(r3);
        r1.setSelected(true);
        genderPanel.add(r1); genderPanel.add(r2); genderPanel.add(r3);
        card.add(genderPanel, gbc);

        // â”€â”€â”€ Disease â”€â”€â”€
        gbc.gridy = 6; gbc.gridx = 0;
        gbc.insets = new Insets(5, 25, 5, 10);
        gbc.weightx = 0.3;
        card.add(UITheme.createFormLabel("Disease"), gbc);
        gbc.gridx = 1;
        gbc.insets = new Insets(5, 10, 5, 25);
        gbc.weightx = 0.7;
        textFieldDisease = UITheme.createStyledTextField();
        card.add(textFieldDisease, gbc);

        // â”€â”€â”€ Room â”€â”€â”€
        gbc.gridy = 7; gbc.gridx = 0;
        gbc.insets = new Insets(5, 25, 5, 10);
        gbc.weightx = 0.3;
        card.add(UITheme.createFormLabel("Room"), gbc);
        gbc.gridx = 1;
        gbc.insets = new Insets(5, 10, 5, 25);
        gbc.weightx = 0.7;

        // Load rooms from DB
        java.util.ArrayList<String> rooms = new java.util.ArrayList<>();
        try {
            Conn c = new Conn();
            ResultSet resultset = c.statement.executeQuery("select * from Room");
            while (resultset.next()) {
                rooms.add(resultset.getString("Room_No"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        roomCombo = UITheme.createStyledComboBox(rooms.toArray(new String[0]));
        card.add(roomCombo, gbc);

        // â”€â”€â”€ Time â”€â”€â”€
        gbc.gridy = 8; gbc.gridx = 0;
        gbc.insets = new Insets(5, 25, 5, 10);
        gbc.weightx = 0.3;
        card.add(UITheme.createFormLabel("Admission Time"), gbc);
        gbc.gridx = 1;
        gbc.insets = new Insets(5, 10, 5, 25);
        gbc.weightx = 0.7;
        Date date1 = new Date();
        date = UITheme.createValueLabel("" + date1);
        card.add(date, gbc);

        // â”€â”€â”€ Deposit â”€â”€â”€
        gbc.gridy = 9; gbc.gridx = 0;
        gbc.insets = new Insets(5, 25, 5, 10);
        gbc.weightx = 0.3;
        card.add(UITheme.createFormLabel("Deposit (â‚¹)"), gbc);
        gbc.gridx = 1;
        gbc.insets = new Insets(5, 10, 5, 25);
        gbc.weightx = 0.7;
        textFieldDeposite = UITheme.createStyledTextField();
        card.add(textFieldDeposite, gbc);

        // â”€â”€â”€ Buttons â”€â”€â”€
        gbc.gridy = 10; gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 25, 25, 25);
        JPanel btnPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        btnPanel.setOpaque(false);

        b1 = UITheme.createStyledButton("Register Patient", UITheme.ButtonType.SUCCESS);
        b1.addActionListener(this);
        btnPanel.add(b1);

        b2 = UITheme.createStyledButton("Back", UITheme.ButtonType.OUTLINE);
        b2.addActionListener(this);
        btnPanel.add(b2);

        card.add(btnPanel, gbc);

        bgPanel.add(card);

        // â”€â”€â”€ Frame â”€â”€â”€
        UITheme.setupFrame(this, "MediCare HMS â€” New Patient", 660, 650);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            Conn c = new Conn();
            String radioBTN = null;
            if (r1.isSelected()) {
                radioBTN = "Male";
            } else if (r2.isSelected()) {
                radioBTN = "Female";
            } else {
                radioBTN = "Others";
            }
            String s1 = (String) comboBox.getSelectedItem();
            String s2 = textFieldNumber.getText();
            String s3 = textName.getText();
            String s4 = radioBTN;
            String s5 = textFieldDisease.getText();
            String s6 = (String) roomCombo.getSelectedItem();
            String s7 = date.getText();
            String s8 = textFieldDeposite.getText();

            try {
                String q = "insert into patient_info values ('" + s1 + "','" + s2 + "','" + s3 + "','" + s4 + "','" + s5 + "','" + s6 + "','" + s7 + "','" + s8 + "')";
                String q1 = "update Room set Availablity ='Occupied' where Room_No = " + s6;
                c.statement.executeUpdate(q);
                c.statement.executeUpdate(q1);
                UITheme.showSuccess(this, "Patient registered successfully!");
                setVisible(false);
                dispose();
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else {
            setVisible(false);
            dispose();
        }
    }

    public static void main(String[] args) {
        UITheme.installTheme();
        SwingUtilities.invokeLater(NEW_PATIENT::new);
    }
}
