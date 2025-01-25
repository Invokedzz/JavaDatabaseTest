package src;

import src.model.entities.ProdEntities.Category;
import src.model.enums.TypeProduct;
import src.model.services.ProdServices.CategoryTable;
import src.validation.CheckCategories;

public class DatabaseTest {

    public static void main (String[] args) {

        testTableCategory();

    }

    private static void testTableCategory () {

        Category category = new Category(1, TypeProduct.CLOTHES, 3);

        CategoryTable catTable = new CategoryTable();

        CheckCategories checkCategories = new CheckCategories();

        if (checkCategories.test(category)) {

            catTable.insert();

        }

    }

    private static void testTableProduct () {

    }

}
