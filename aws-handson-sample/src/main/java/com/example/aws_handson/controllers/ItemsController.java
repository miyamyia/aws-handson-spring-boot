package com.example.aws_handson.controllers;

import com.example.aws_handson.entities.Item;
import com.example.aws_handson.repositories.ItemsRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/shop")
public class ItemsController {
    @Autowired
    private ItemsRepository itemsRepository;

    @GetMapping
    public String getItems(Model model){
        List<Item> items = itemsRepository.findAll();
        model.addAttribute("items",items);
        return "shop";
    }

    @GetMapping("/cart")
    public String cart(){
        return "cart";
    }

    @PostMapping("/cart")
    public String intoCart(@RequestParam int id, HttpSession session){
        Item item = itemsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("user not found."));
        Object obj = session.getAttribute("cart");
        List<Item> cart;
        if(obj != null) {
            cart = (List<Item>) obj;
        }else {
            cart = new ArrayList<>();
            session.setAttribute("cart",cart);
        }

        cart.add(item);
        session.setAttribute("cart",cart);

        return "redirect:/shop";
    }

}
