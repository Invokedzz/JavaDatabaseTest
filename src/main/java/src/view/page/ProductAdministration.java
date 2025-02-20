package src.view.page;

import net.miginfocom.swing.MigLayout;
import src.model.entities.ProdEntities.Category;
import src.model.entities.ProdEntities.Product;
import src.model.enums.ProductAvailability;
import src.model.services.ProdServices.ProductTable;
import src.security.AdminCredentials;
import src.security.ObtainImgPath;
import src.view.util.CreateProductImg;
import src.view.validations.product.page.AnalyzeAvailabilityInput;
import src.view.validations.product.page.AreYouSureThisProductExists;
import src.view.validations.product.page.CheckNumericalInput;
import src.view.validations.product.page.CheckProductInfoInOrderToUpdate;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;


public class ProductAdministration extends JFrame {

    private final JTextField searchField;
    private final JPanel productPanel;

    public ProductAdministration(Connection connection) {

        setTitle("Product Administration");
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 245));
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(800, 600);

        JPanel searchPanel = new JPanel(new BorderLayout());
        searchField = new JTextField();
        searchField.setToolTipText("Search for products");
        JButton searchButton = new JButton("Search");

        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(searchPanel, BorderLayout.CENTER);

        JButton addButton = new JButton("Add New Product");
        addButton.setBackground(new Color(0, 150, 136));
        addButton.setForeground(Color.BLACK);
        addButton.setFocusPainted(false);
        addButton.setBorderPainted(false);
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        topPanel.add(addButton, BorderLayout.WEST);

        add(topPanel, BorderLayout.NORTH);

        productPanel = new JPanel();
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(productPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        ProductTable productTable = new ProductTable();
        List<Product> productList = productTable.displayProducts(connection);

        displayProducts(productList, connection);

        searchButton.addActionListener(e -> {

            List <Product> productsFound = productTable.
                    searchForProducts(connection, searchField.getText());

            if (!AreYouSureThisProductExists.searchForProductInAList(this, productsFound)) return;

            displayProducts(productsFound, connection);

        });

        addButton.addActionListener(e -> {

            JFrame createProductFrame = new JFrame("Create new Product");
            createProductFrame.setLayout(new MigLayout("center center, wrap 1, gapy 20"));
            createProductFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            createProductFrame.getContentPane().setBackground(new Color(245, 245, 245));
            createProductFrame.setLocationRelativeTo(null);
            createProductFrame.setResizable(false);
            createProductFrame.setSize(600,750);

            JTextField productNameField = new JTextField(15);
            JTextField priceField = new JTextField(15);
            JTextField quantityField = new JTextField(15);
            JTextField availabilityField = new JTextField(15);
            JTextField categoryField = new JTextField(15);

            JButton createProductBtn = new JButton("Create Product");

            JButton cancelBtn = new JButton("Cancel");

            JLabel imageLabel = new JLabel("No image selected");
            JButton selectImageButton = new JButton("Select Image");

            CreateProductImg.setupImg(createProductFrame, imageLabel, selectImageButton);

            createProductBtn.addActionListener(createEvent -> {

                if (!CheckNumericalInput.isThisValueNumerical(this, categoryField.getText())) return;

                if (!AnalyzeAvailabilityInput.lookForAvailabilityText(this, availabilityField.getText())) return;

                Integer categoryId = Integer.valueOf(categoryField.getText());

                Category category = new Category(categoryId);

                String productCode = AdminCredentials.generateTicket();

                Product productThatIsAboutToBeCreated = new Product(productNameField.getText(), priceField.getText(),
                        quantityField.getText(), ProductAvailability.valueOf(availabilityField.getText()), category,
                        productCode, ObtainImgPath.imagePath);

                ProductTable newTable = new ProductTable(productThatIsAboutToBeCreated);

                if (!CheckProductInfoInOrderToUpdate.validateProduct(this, productThatIsAboutToBeCreated)) return;

                newTable.insert();

                JOptionPane.showMessageDialog(this, "Product created!");

                List <Product> queryProducts = productTable.displayProducts(connection);

                displayProducts(queryProducts, connection);

            });

            cancelBtn.addActionListener(cancelEvent -> createProductFrame.dispose());

            createProductFrame.add(new JLabel("Product Image:"));
            createProductFrame.add(imageLabel);
            createProductFrame.add(selectImageButton);
            createProductFrame.add(new JLabel("Name:"));
            createProductFrame.add(productNameField);
            createProductFrame.add(new JLabel("Price"));
            createProductFrame.add(priceField);
            createProductFrame.add(new JLabel("Quantity:"));
            createProductFrame.add(quantityField);
            createProductFrame.add(new JLabel("Availability:"));
            createProductFrame.add(availabilityField);
            createProductFrame.add(new JLabel("Category:"));
            createProductFrame.add(categoryField);
            createProductFrame.add(createProductBtn);
            createProductFrame.add(cancelBtn);

            createProductFrame.setVisible(true);

        });

        setVisible(true);

    }

    private void displayProducts(List<Product> productList, Connection connection) {
        productPanel.removeAll();

        ProductTable productTable = new ProductTable();

        for (Product product : productList) {

            Integer productId = productTable.obtainProductId(connection, product.getName());

            JPanel productItemPanel = new JPanel();
            productItemPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            productItemPanel.setBackground(new Color(255, 255, 255));
            productItemPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
            productItemPanel.setPreferredSize(new Dimension(750, 80));
            productItemPanel.setMaximumSize(new Dimension(750, 80));
            productItemPanel.setMinimumSize(new Dimension(750, 80));

            ImageIcon originalImg = productTable.obtainProductImg(connection, productId);

            Image scaledImg = originalImg.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);

            ImageIcon resizedImg = new ImageIcon(scaledImg);

            JLabel productLabel = new JLabel("<html><strong style='color:black;'>" + product.getName() + "</strong><br>" +
                    "<span style='color:black;'>Price: " + product.getPrice() + "</span><br>" +
                    "<span style='color:black;'>Quantity:" + product.getQuantity() + "</span></html>");

            productLabel.setIcon(resizedImg);

            productItemPanel.add(productLabel);

            JButton editButton = new JButton("Edit");
            editButton.setBackground(new Color(0, 150, 136));
            editButton.setForeground(Color.BLACK);
            editButton.setFocusPainted(false);
            editButton.setFont(new Font("Arial", Font.BOLD, 12));

            editButton.addActionListener(e -> {

                JFrame editProductFrame = new JFrame("Edit Product");
                editProductFrame.setLayout(new MigLayout("center center, wrap 1, gapy 30"));
                editProductFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                editProductFrame.getContentPane().setBackground(new Color(245, 245, 245));
                editProductFrame.setLocationRelativeTo(null);
                editProductFrame.setResizable(false);
                editProductFrame.setSize(400,600);

                JTextField productNameField = new JTextField(product.getName(), 15);
                JTextField priceField = new JTextField(product.getPrice(), 15);
                JTextField quantityField = new JTextField(product.getQuantity(), 15);
                JTextField availabilityField = new JTextField(product.getAvailability().toString(), 15);

                JButton updateProductBtn = new JButton("Update Product");
                JButton cancelBtn = new JButton("Cancel");

                updateProductBtn.addActionListener(updateEvent -> {
                    Product updatedProduct = new Product(
                            productNameField.getText(),
                            priceField.getText(),
                            quantityField.getText(),
                            ProductAvailability.valueOf(availabilityField.getText())
                    );

                    if (!CheckProductInfoInOrderToUpdate.validateProduct(editProductFrame, updatedProduct))
                        return;

                    Double price = Double.valueOf(priceField.getText());
                    Integer quantity = Integer.valueOf(quantityField.getText());

                    productTable.updateProduct(connection, productNameField.getText(), price, quantity, productId);

                    JOptionPane.showMessageDialog(editProductFrame, "Product updated!");

                    editProductFrame.dispose();

                    List <Product> queryProducts = productTable.displayProducts(connection);

                    displayProducts(queryProducts, connection);

                });

                cancelBtn.addActionListener(cancelEvent -> editProductFrame.dispose());

                editProductFrame.add(new JLabel("Name:"));
                editProductFrame.add(productNameField);
                editProductFrame.add(new JLabel("Price"));
                editProductFrame.add(priceField);
                editProductFrame.add(new JLabel("Quantity:"));
                editProductFrame.add(quantityField);
                editProductFrame.add(new JLabel("Availability:"));
                editProductFrame.add(availabilityField);
                editProductFrame.add(updateProductBtn);
                editProductFrame.add(cancelBtn);

                editProductFrame.setVisible(true);

            });

            productItemPanel.add(editButton);

            JButton deleteButton = new JButton("Delete");
            deleteButton.setBackground(new Color(255, 69, 58));
            deleteButton.setForeground(Color.BLACK);
            deleteButton.setFocusPainted(false);
            deleteButton.setFont(new Font("Arial", Font.BOLD, 12));

            deleteButton.addActionListener(e -> {

                int response = JOptionPane.showConfirmDialog(null, "Do you really want to proceed?",
                        "Confirm"
                        , JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                if (response == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(this, "Product deleted!");
                    productTable.deleteComponent(connection, productId);
                    List <Product> updatedProductList = productTable.displayProducts(connection);
                    displayProducts(updatedProductList, connection);
                }

            });

            productItemPanel.add(deleteButton);

            productPanel.add(productItemPanel);
        }

        productPanel.revalidate();
        productPanel.repaint();

    }

}

