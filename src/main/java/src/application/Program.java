package src.application;

import src.model.entities.*;

import src.model.services.*;

import src.util.*;

import src.validation.*;

public class Program {

    public static void main (String[] args) {

        runCategoryTable();

    }

    private static void runCategoryTable () {

        Category cat = new Category(1, "Electronics", 1);

        CategoryTable catTable = new CategoryTable();

        catTable.insert(cat);

        runProductTable(cat);

    }

    private static void runProductTable (Category cat) {

        Product product = new Product("Computer", 400.0, cat);

        ProductTable prodTable = new ProductTable();

        prodTable.insert(product);

    }

}
