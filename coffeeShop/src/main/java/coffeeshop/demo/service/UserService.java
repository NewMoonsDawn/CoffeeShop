package coffeeshop.demo.service;

import coffeeshop.demo.model.entity.User;
import coffeeshop.demo.model.service.UserServiceModel;
import coffeeshop.demo.model.view.UserViewModel;

import java.util.List;

public interface UserService {
    public UserServiceModel registerUser(UserServiceModel map);

    public UserServiceModel findByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    User findById(Long id);


    List<UserViewModel> findAllByOrdersCountDescending();
}
