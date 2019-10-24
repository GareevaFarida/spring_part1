package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.persistence.CustomerRepository;
import ru.geekbrains.persistence.entity.Customer;

@Controller
@RequestMapping("customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String allCustomers(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "customers";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createCustomerFrom(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("action", "create");
        return "customer";
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String editForm(@RequestParam("id") Long id, Model model) {
        model.addAttribute("customer", customerRepository.findById(id));
        model.addAttribute("action", "edit");
        return "customer";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String editForm(@ModelAttribute("customer") Customer customer) {
        customerRepository.update(customer);
        return "customer";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createCategory(@ModelAttribute("customer") Customer customer) {
        customerRepository.create(customer);
        return "redirect:/customers";
    }
}
