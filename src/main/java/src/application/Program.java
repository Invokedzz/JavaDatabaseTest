package src.application;

import src.model.entities.*;

import src.model.services.*;

import src.util.*;

import src.validation.*;

public class Program {

    public static void main (String[] args) {

        runTables();

    }

    private static void runTables () {

        Category cat = new Category(1, "Electronics", 1);

        Product product = new Product("Mouse", 50.0, cat);

        System.out.println(product.getCategory());

        //CategoryTable catTable = new CategoryTable(cat);

        ProductTable prodTable = new ProductTable(product);

        // fix the connection issue

        prodTable.insert();

        //catTable.insert();


    }


}
