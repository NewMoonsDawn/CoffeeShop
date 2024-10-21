package coffeeshop.demo.web;

import coffeeshop.demo.model.view.OrderViewModel;
import coffeeshop.demo.sec.CurrentUser;
import coffeeshop.demo.service.OrderService;
import coffeeshop.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final OrderService orderService;
    private final UserService userService;

    public HomeController(CurrentUser currentUser, OrderService orderService, UserService userService) {
        this.currentUser = currentUser;
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping()
    public String index(Model model)
    {
        if(currentUser.getId() == null){
            return "index";
        }

        List<OrderViewModel> orders = orderService.findAllOrderOrderByPriceDesc();

        model.addAttribute("orders", orderService.findAllOrderOrderByPriceDesc());
        model.addAttribute("totalTime", orders.
                stream()
        .map(orderViewModel -> orderViewModel.getCategory().getNeededTime())
                .reduce(Integer::sum)
                .orElse(null));

        model.addAttribute("users",userService.findAllByOrdersCountDescending());

        return "home";

    }


}
