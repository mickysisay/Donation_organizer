package org.launchcode.Donation_organizer.controllers;

import org.launchcode.Donation_organizer.models.User;
import org.launchcode.Donation_organizer.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("subscription")
public class SubscriptionController {
    @Autowired
    UserRepository userRepository;

}
