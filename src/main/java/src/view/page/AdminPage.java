package src.view.page;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class AdminPage extends JFrame {

    public AdminPage (Connection connection, Integer userId) {

        setTitle("Profile");
        setLayout(new MigLayout("center center, wrap, gapy 30"));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 245));
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(800,600);

        setVisible(true);

    }

    private void addComponents () {

    }

}
