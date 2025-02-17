package src.view.page;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class DisplayABitMoreOfThePurchaseInfo extends JFrame {

    public DisplayABitMoreOfThePurchaseInfo (String neighbourhood, String houseNumber, String complement, String date) {

        setTitle("Purchase Info");
        setLayout(new MigLayout("center center, wrap, gapy 30"));
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 245));
        setSize(300, 520);
        setLocationRelativeTo(null);

        JTextField neighbourhoodField = new JTextField(neighbourhood, 15);

        neighbourhoodField.setEditable(false);

        JTextField houseNumberField = new JTextField(houseNumber, 15);

        houseNumberField.setEditable(false);

        JTextField complementField = new JTextField(complement, 15);

        complementField.setEditable(false);

        JTextField dateField = new JTextField(date, 15);

        dateField.setEditable(false);

        JButton cancelBtn = new JButton("Cancel");

        cancelBtn.addActionListener(e -> dispose());

        add(new JLabel("Neighbourhood:"));

        add(neighbourhoodField);

        add(new JLabel("House Number:"));

        add(houseNumberField);

        add(new JLabel("Complement:"));

        add(complementField);

        add(new JLabel("Purchase Date:"));

        add(dateField);

        add(cancelBtn);

        setVisible(true);

    }

}
