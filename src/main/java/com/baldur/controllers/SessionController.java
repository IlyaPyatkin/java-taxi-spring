package com.baldur.controllers;

import com.baldur.model.pojo.User;
import com.baldur.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;


@Controller
@SessionAttributes("username")
public class SessionController {
    private static Logger LOGGER = Logger.getLogger(SessionController.class.getName());
    private final IUserService userService;

    @Autowired
    public SessionController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    protected ModelAndView login(@ModelAttribute User user, ModelMap model) {

        try {
            if (userService.auth(user.getUsername(), user.getPassword()) != null) {
                LOGGER.info(user.getUsername() + " has successfully logged in.");
                model.addAttribute("username", user.getUsername());
                return new ModelAndView("redirect:/order", model);
            }
        } catch (Exception e) {
            LOGGER.warning(e.getMessage());
        }
        return new ModelAndView("redirect:/index");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    protected ModelAndView logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();

        return new ModelAndView("redirect:/index.jsp");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    protected ModelAndView register(@ModelAttribute User user, ModelMap model) {
        try {
            boolean isRegistered = userService.userExists(username);

            if (!isRegistered && userService.register(username, password)) {
                model.addAttribute("username", user.getUsername());
                LOGGER.info("New user: " + username);
                return new ModelAndView("redirect:/order", model);
            }
        } catch (Exception e) {
            LOGGER.warning("Error registering user: " + e.getMessage());
        }
        return new ModelAndView("redirect:/register-fail.jsp");
    }
}
