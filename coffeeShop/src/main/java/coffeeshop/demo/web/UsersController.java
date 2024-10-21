package coffeeshop.demo.web;

import coffeeshop.demo.model.binding.UserLoginBindingModel;
import coffeeshop.demo.model.binding.UserRegisterBindingModel;
import coffeeshop.demo.model.service.UserServiceModel;
import coffeeshop.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.Binding;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.Console;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UsersController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public String register()
    {
        return "register";
    }

    @GetMapping("/login")
    public String login(Model model)
    {

        if (!model.containsAttribute("isFound"))
        {
            model.addAttribute("isFound", true);
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid UserLoginBindingModel userLoginBindingModel,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes)
    {
        if(bindingResult.hasErrors())
        {
            redirectAttributes.addFlashAttribute("userLoginBindModel",
                    userLoginBindingModel);

            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.userLoginBindingModel"
                            ,bindingResult);
            return "redirect:login";
        }
        UserServiceModel userServiceModel =
                userService.findByUsernameAndPassword
                        (userLoginBindingModel.getUsername(),userLoginBindingModel.getPassword());
        if(userServiceModel == null)
        {
            redirectAttributes.addFlashAttribute("userLoginBindModel",
                    userLoginBindingModel);

            redirectAttributes.addFlashAttribute
                    ("isFound"
                            ,false);
            System.out.println("you're wrong :)");
            return "redirect:login";
        }

        userService.loginUser(userServiceModel.getId(), userLoginBindingModel.getUsername());

        return "redirect:/";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,

                                  BindingResult bindingResult, RedirectAttributes redirectAttributes)
    {
        if(bindingResult.hasErrors() || !userRegisterBindingModel.getPassword()
                .equals(userRegisterBindingModel.getConfirmPassword()))
        {
            redirectAttributes.addFlashAttribute
                    ("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.userRegisterBindingModel"
                            , bindingResult);

            return "redirect:register";
        }

        userService.registerUser(modelMapper
        .map(userRegisterBindingModel, UserServiceModel.class));

        return "redirect:login";
    }

    @GetMapping("/logout")
    private String logout(HttpSession httpSession)
    {
        httpSession.invalidate();

        return "redirect:/";
    }



    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel()
    {
        return new UserRegisterBindingModel();
    }

    @ModelAttribute
    public UserLoginBindingModel userLoginBindingModel() {return new UserLoginBindingModel();}

}
