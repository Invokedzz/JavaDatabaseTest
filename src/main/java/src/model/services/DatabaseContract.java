package src.model.services;

import src.model.entities.Product;

public interface DatabaseContract {

    void insert ( Product product );

    void display ();

}
