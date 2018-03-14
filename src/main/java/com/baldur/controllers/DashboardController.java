package com.baldur.controllers;

import com.baldur.model.pojo.Order;
import com.baldur.services.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.logging.Logger;

@Controller
@SessionAttributes("username")
public class DashboardController {
    private static Logger LOGGER = Logger.getLogger(DashboardController.class.getName());
    private final IOrderService orderService;

    @Autowired
    public DashboardController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    protected ModelAndView order(ModelMap model) {
        try {
            if (model.containsAttribute("username")) {
                String username = (String) model.get("username");

                List<Order> orders = orderService.getOrders(username);
                model.addAttribute("orders", orders);
                return new ModelAndView("/dashboard", model);
            }
        } catch (Exception e) {
            LOGGER.warning(e.getMessage());
        }
        return new ModelAndView("redirect:/index");
    }


    @RequestMapping(value = "/order", method = RequestMethod.POST)
    protected ModelAndView addOrder(@ModelAttribute Order order, ModelMap model) {
        try {
            String username;
            if (model.containsAttribute("username")) {
                username = (String) model.get("username");
                if (orderService.create(username, order))
                    LOGGER.info("New order created for " + username);
            }
            return order(model);
        } catch (Exception e) {
            LOGGER.warning(e.getMessage());
        }
        return new ModelAndView("redirect:/index");
    }
}
