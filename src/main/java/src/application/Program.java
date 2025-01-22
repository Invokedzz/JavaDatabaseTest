package src.application;

import src.model.entities.*;

import src.model.services.*;

import src.util.*;
import src.validation.CheckProducts;

public class Program {

    public static void main (String[] args) {

        runOperations();

    }

    private static void runOperations () {

        Product product = new Product( 0, "Ball", 50.0 );

        CheckProducts check = new CheckProducts(product);

        ProductPredicate predicate = ( prod ) -> !prod.getName().isEmpty() && prod.getPrice() > 0;

        Product productChecked = check.validateProduct(product, predicate);

        ProductTable insert = new ProductTable();

        insert.insert(productChecked);

    }

}
