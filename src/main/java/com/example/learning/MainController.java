package com.example.learning;

import com.example.learning.users.Countries;
import com.example.learning.users.CustomerRegistrationDto;
import com.example.learning.users.UserExistsException;
import com.example.learning.users.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class MainController {

    private UserRegistrationService userRegistrationService;

    @Autowired
    public MainController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @GetMapping(value = {"/","/index"})
    public String homePage(){return "index";}

    @GetMapping("/login")
    public String loginPage(){return "login";}

    @GetMapping(value = "/register")
    public String registrationPage(Model model){
        model.addAttribute("customerFormData", new CustomerRegistrationDto());
        model.addAttribute("countries", Countries.values());

        return "registerForm";
    }

    @PostMapping(value = "/register")
    public String registerEffect(@ModelAttribute(name = "customerFormData") @Valid CustomerRegistrationDto customerFormData,
                                 BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("countries", Countries.values());
            return "registerForm";
        }
        try {
            userRegistrationService.registerUser(customerFormData);
        } catch (UserExistsException e) {
            model.addAttribute("userExistsException", e.getMessage());
            return "registerForm";
        }
        model.addAttribute("registrationData", customerFormData);
        return "registerEffect";
    }
}
