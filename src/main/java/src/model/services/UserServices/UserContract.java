package src.model.services.UserServices;

public interface UserContract {

    void updateName (String name, Integer id);

    void checkUserById (Integer id);

}
