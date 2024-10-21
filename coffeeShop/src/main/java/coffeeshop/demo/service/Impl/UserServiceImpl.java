package coffeeshop.demo.service.Impl;

import coffeeshop.demo.model.entity.User;
import coffeeshop.demo.model.service.UserServiceModel;
import coffeeshop.demo.model.view.UserViewModel;
import coffeeshop.demo.repository.UserRepository;
import coffeeshop.demo.sec.CurrentUser;
import coffeeshop.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel map) {
        User user = modelMapper.map(map, User.class);

        return modelMapper.map(userRepository.save(user),UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        System.out.println(userRepository.findByUsernameAndPassword(username,password));
        return userRepository.findByUsernameAndPassword(username, password)
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public void loginUser(Long id, String username) {
        currentUser.setId(id);
        currentUser.setUsername(username);
    }

    @Override
    public User findById(Long id) {
        return userRepository
                .findById(id)
                .orElse(null);
    }

    @Override
    public List<UserViewModel> findAllByOrdersCountDescending() {
        return userRepository.findAllByOrdersCountDescending()
                .stream()
                .map(user -> {
                    UserViewModel userViewModel = new UserViewModel();
                    userViewModel.setUsername(user.getUsername());
                    userViewModel.setCountOfOrders(user.getOrders().size());

                    return userViewModel;
                })
                .collect(Collectors.toList());
    }

}
