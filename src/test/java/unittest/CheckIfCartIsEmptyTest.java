package unittest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.model.entities.ProdEntities.Product;
import src.view.validations.payment.CheckIfCartIsEmpty;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CheckIfCartIsEmptyTest {

    private Component parent;

    @Test
    @DisplayName("Testing a case where the list is empty")
    void emptyListCase () {

        List <Product> productList = new ArrayList<>();

        assertFalse(CheckIfCartIsEmpty.isCartEmpty(parent, productList));

    }

    @Test
    @DisplayName("Testing a case where the list have some elements")
    void listContainingElementsCase () {

        List <Product> productList = new ArrayList<>();

        productList.add(new Product());

        productList.add(new Product());

        assertTrue(CheckIfCartIsEmpty.isCartEmpty(parent, productList));

    }

}