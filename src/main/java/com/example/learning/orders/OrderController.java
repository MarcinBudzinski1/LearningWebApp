package com.example.learning.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderController {

    private final OrdersService ordersService;

    @Autowired
    public OrderController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @RequestMapping(value = "/placeOrder")
    public String makeAnOrder(Model model) {
        model.addAttribute("order", ordersService.placeOrder());
        return "orderFinished";
    }
}
