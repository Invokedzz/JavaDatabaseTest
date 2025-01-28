package src;

import src.model.entities.ProdEntities.Category;

import src.model.entities.ProdEntities.Product;

import src.model.enums.ProductAvailability;

import src.model.enums.TypeProduct;

import src.model.services.ProdServices.CategoryTable;

import src.model.services.ProdServices.ProductTable;

import src.validation.CheckCategories;

import src.validation.CheckProducts;

import src.security.PassHash;

public class ProdDBTest {

    public static void main (String[] args) {

       testTableCategory();

        //checkHashedPasswords();

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



    }

    private static void checkHashedPasswords () {

        String randomPassword = "73289";

        String hashed = PassHash.generateHash(randomPassword);

        System.out.println(hashed); // omg the hash is real!?

        if (PassHash.checkChosenHash(randomPassword, hashed)) System.out.println("Yeah they're the same password");
        else System.out.println("No this is not true!");

    }

}
