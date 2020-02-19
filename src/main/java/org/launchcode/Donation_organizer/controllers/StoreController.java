package org.launchcode.Donation_organizer.controllers;

import org.launchcode.Donation_organizer.models.Item;
import org.launchcode.Donation_organizer.models.User;
import org.launchcode.Donation_organizer.models.data.ItemRepository;
import org.launchcode.Donation_organizer.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class StoreController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ItemRepository itemRepository;
    @GetMapping("store")
    public String Store(Model model, HttpServletRequest request){
   Integer id = (Integer) request.getSession().getAttribute("user");
        Optional option =   userRepository.findById(id);
   User theUser = (User) option.get();
       model.addAttribute("user",theUser);
        model.addAttribute(new Item());
        model.addAttribute("items",theUser.getItems());
        return "storehome";
    }

    @PostMapping("save")
    public String processAddEmployerForm(@ModelAttribute @Valid Item newItem,
                                         Errors errors,HttpServletRequest request, Model model) {

        if (errors.hasErrors()) {
            return "store";
        }
        Integer id = (Integer) request.getSession().getAttribute("user");
        Optional option =   userRepository.findById(id);
        User theUser = (User) option.get();
        theUser.UpdateUser(newItem);
        userRepository.save(theUser);

        return "redirect:/store";
    }
}
