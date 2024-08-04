package com.example.aws_handson.controllers;

import com.example.aws_handson.entities.User;
import com.example.aws_handson.repositories.UsersRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.NoSuchElementException;

@Controller
public class UsersController {
    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/login")
    public String loginPage(HttpSession session){
        if(session.getAttribute("user") != null)
            return "redirect:/shop";

        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user,HttpSession session){
        User found = usersRepository.findById(user.getId())
                .filter(it -> it.getPassword().equals(user.getPassword()))
                .orElseThrow(() -> new NoSuchElementException("user not found."));

        session.setAttribute("user",found);

        return "redirect:/shop";
    }
}
