package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.controller.repr.AuthorityRepr;
import ru.geekbrains.controller.repr.UserRepr;
import ru.geekbrains.persistence.entity.Authority;
import ru.geekbrains.persistence.entity.User;
import ru.geekbrains.service.AuthorityService;
import ru.geekbrains.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class UserController {

    private final UserService userService;
    private final AuthorityService authorityService;
    private final List<Authority> allAuthoritiesList;

    @Autowired
    public UserController(UserService userService, AuthorityService authorityService) {
        this.userService = userService;
        this.authorityService = authorityService;
        this.allAuthoritiesList = authorityService.findAllWithoutUsers();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findAllUsersWithoutAuthorities());
        model.addAttribute("authorities", authorityService.findAll());
        return "users";
    }

    @GetMapping("/registerByAdmin")
    public String registerPageByAdmin(Model model) {
        model.addAttribute("user", new UserRepr());
        return "registerByAdmin";
    }


    @RequestMapping(value = "addAuthorityToUser", method = RequestMethod.POST)
    public String addAuthorityToUser(@ModelAttribute("newAuthority") AuthorityRepr authorityRepr,
                                     @RequestParam("userId") Long userId,
                                     Model model) {

        User user = userService.findUserByIdWithAuthorities(userId);
        Authority authority = authorityService.getAuthorityById(authorityRepr.getId());
        user.getAuthorities().add(authority);
        userService.save(user);

        return "redirect:/admin/users/edit?id=" + userId;
    }

    @RequestMapping(value = "deleteAuthorityOfUser", method = RequestMethod.POST)
    public String deleteAuthorityOfUser(@ModelAttribute("newAuthority") AuthorityRepr authorityRepr,
                                     @RequestParam("userId") Long userId,
                                     Model model) {

        User user = userService.findUserByIdWithAuthorities(userId);
        Authority authority = authorityService.getAuthorityById(authorityRepr.getId());
        user.getAuthorities().remove(authority);
        userService.save(user);

        return "redirect:/admin/users/edit?id=" + userId;
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String editForm(@RequestParam("id") Long id, Model model) {
        User user = userService.findUserByIdWithAuthorities(id);
        model.addAttribute("user", user);
        model.addAttribute("action", "edit");


        //составим список, ролей, которых нет у пользователя и будем выводить только этот список в форме выбора
        List<Authority> listAuthForSelection = new ArrayList<>();
        List<Authority> listAuthUserHas = user.getAuthorities();
        if(listAuthUserHas.size()==0){
            listAuthForSelection = allAuthoritiesList;
        }else{
            for (Authority auth: allAuthoritiesList) {
                if(!listAuthUserHas.contains(auth)){
                    listAuthForSelection.add(auth);
                }
            }
        }

        model.addAttribute("authorities", listAuthForSelection);
        AuthorityRepr authorityRepr = new AuthorityRepr();
        authorityRepr.setIdUser(user.getId());
        model.addAttribute("newAuthority", authorityRepr);

        model.addAttribute("authoritiesOfUser",listAuthUserHas);
        return "userAccount";
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public String delete(@ModelAttribute("user") UserRepr userRepr) {
        userService.deleteUser(userRepr);
        return "redirect:/admin/users";
    }


    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin/users";
    }
}
