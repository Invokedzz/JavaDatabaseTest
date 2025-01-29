package src.model.services.UserServices;

public interface UserContract {

    void updateName (String name, Integer id);

    String getStoredPassword (String email);

    boolean checkUserById (Integer id);

}
