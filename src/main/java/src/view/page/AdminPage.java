package src.view.page;

import net.miginfocom.swing.MigLayout;
import src.model.entities.UserEntities.Admin;
import src.model.services.UserServices.AdminTable;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class AdminPage extends JFrame {

    private final JButton viewProductStockBtn, editProfileBtn, cancelBtn;

    public AdminPage (Connection connection, Integer userId) {

        setTitle("Profile");
        setLayout(new MigLayout("center center, wrap 1, gapy 30"));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 245));
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(800,600);

        AdminTable adminTable = new AdminTable();

        Admin admin = adminTable.obtainUserProperties(connection, userId);

        viewProductStockBtn = new JButton("View Stock");

        editProfileBtn = new JButton("Edit Profile");

        cancelBtn = new JButton("Cancel");

        setBtnIcons();

        createViewProductStockBtnAction();

        createCancelBtnAction();

        addComponents(admin);

        setVisible(true);

    }

    private Label greetingsLabel (Admin admin) {

        String text = String.format("""
                        Hello, %s
                        """
                , admin.getEmail());


        return new Label(text);

    }

    private void createViewProductStockBtnAction () {

        viewProductStockBtn.addActionListener(e -> new ProductCentral());

    }

    private void createCancelBtnAction () {

        cancelBtn.addActionListener(e -> dispose());

    }

    private void setBtnIcons () {

        ImageIcon stockIcon = new ImageIcon("/Users/samunoinv/IdeaProjects/JavaDatabaseTest/src/main/java/src/view/img/ReadyStock - Porky.png");

        ImageIcon profileIcon = new ImageIcon("/Users/samunoinv/IdeaProjects/JavaDatabaseTest/src/main/java/src/view/img/ProfileImg.png");

        viewProductStockBtn.setIcon(stockIcon);

        editProfileBtn.setIcon(profileIcon);

        viewProductStockBtn.setIcon(new ImageIcon(stockIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        editProfileBtn.setIcon(new ImageIcon(profileIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));

    }

    private void addComponents (Admin admin) {

        add(greetingsLabel(admin), "cell 0 0, align center");

        add(new JLabel("Welcome! You now have the ability to manage all the products in the store."), "cell 0 1, align center");

        add(new JLabel("Feel free to add, update, or remove items as needed to ensure everything is running smoothly."), "cell 0 2, align center");

        add(new JLabel("If you need any assistance, don't hesitate to reach out!"), "cell 0 3, align center");

        add(viewProductStockBtn, "cell 0 4, align center");

        add(editProfileBtn, "cell 0 4, align center");

        add(cancelBtn, "cell 0 4, align center");

    }

}
