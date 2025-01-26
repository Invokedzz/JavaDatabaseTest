package src;

import src.model.entities.ProdEntities.Category;

import src.model.entities.ProdEntities.Product;

import src.model.enums.ProductAvailability;

import src.model.enums.TypeProduct;

import src.model.services.ProdServices.CategoryTable;

import src.model.services.ProdServices.ProductTable;

import src.validation.CheckCategories;

import src.validation.CheckProducts;

public class DatabaseTest {

    public static void main (String[] args) {

       testTableCategory();

    }

    private static void testTableCategory () {

        Category category = new Category(5, TypeProduct.CLOTHES, 4);

        System.out.println(category.getType().name());

        CategoryTable catTable = new CategoryTable(category);

        CheckCategories checkCategories = new CheckCategories();

        /* if (checkCategories.test(category)) {

            catTable.insert();

        } */

        //catTable.display(); // Okay, everything is working :)

        testTableProduct(category);

    }

    private static void testTableProduct (Category cat) {

        Product product = new Product("BLACK COAT", 100.0, 3,
                ProductAvailability.IN_STOCK, cat);

        CheckProducts checkProducts = new CheckProducts();

        checkProducts.test(product);

        ProductTable prodTable = new ProductTable(product);

        //prodTable.display();

        prodTable.deleteComponent();

        //prodTable.deleteAll();

    }

}
