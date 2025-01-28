package src.model.services.ProdServices;

public interface ProductContract {

    void updateName (String name, Integer id);

    void updatePrice (Double price, Integer id);

}
