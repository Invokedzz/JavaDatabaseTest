package src.model.services.UserServices;

public interface UserContract {

    void updateName (String name, Integer id);

    boolean checkUserById (Integer id);

}
