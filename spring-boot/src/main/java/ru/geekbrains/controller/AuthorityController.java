package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.persistence.entity.Authority;
import ru.geekbrains.persistence.entity.Customer;
import ru.geekbrains.service.AuthorityService;
import ru.geekbrains.service.CustomerService;

@Controller
@RequestMapping("/admin/authorities")
public class AuthorityController {

    private final AuthorityService authorityService;

    @Autowired
    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String allCustomers(Model model) {
        model.addAttribute("authorities", authorityService.findAll());
        return "authorities";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createCustomerFrom(Model model) {
        model.addAttribute("authority", new Authority());
        model.addAttribute("action", "create");
        return "authority";
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String editForm(@RequestParam("id") Long id, Model model) {
        model.addAttribute("authority", authorityService.getAuthorityById(id));
        model.addAttribute("action", "edit");
        return "authority";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute("authority") Authority authority){
        authorityService.save(authority);
        return "redirect:/admin/authorities";
    }
}
