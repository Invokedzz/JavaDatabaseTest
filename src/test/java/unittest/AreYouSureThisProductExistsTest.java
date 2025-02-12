package unittest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.model.entities.ProdEntities.Product;
import src.view.validations.product.page.AreYouSureThisProductExists;
import java.util.List;
import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AreYouSureThisProductExistsTest {

    private Component parent;

    @Test
    @DisplayName("Empty list case (no product was found)")
    void emptyListCase () {

        List <Product> emptyList = new ArrayList<>();

        assertFalse(AreYouSureThisProductExists.searchForProductInAList(parent, emptyList));

    }

    @Test
    @DisplayName("Other way around -> product found")
    void elementsInsideTheList () {

        List <Product> listContainingElements = new ArrayList<>();

        listContainingElements.add(new Product());

        assertTrue(AreYouSureThisProductExists.searchForProductInAList(parent, listContainingElements));

    }

}