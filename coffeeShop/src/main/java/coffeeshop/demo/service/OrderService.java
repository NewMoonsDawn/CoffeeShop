package coffeeshop.demo.service;

import coffeeshop.demo.model.service.OrderServiceModel;
import coffeeshop.demo.model.view.OrderViewModel;
import java.util.List;

public interface OrderService {


    void addOrder(OrderServiceModel orderServiceModel);

    List<OrderViewModel> findAllOrderOrderByPriceDesc();

    void readyOrder(Long id);
}
