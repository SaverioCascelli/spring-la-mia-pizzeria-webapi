package org.experis.lamiapizzeria.controllers;

import org.experis.lamiapizzeria.models.Pizza;
import org.experis.lamiapizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizza")
public class PizzaController {
  
  @Autowired
  private PizzaRepository pizzaRepository;
  
  
  @GetMapping
  public String index(Model model, @RequestParam(name = "search") Optional<String> keyword) {
    
    List<Pizza> pizzas;
    if (keyword.isEmpty()) {
      pizzas = pizzaRepository.findAll();
    } else {
      pizzas = pizzaRepository.findByNameContainingIgnoreCase(keyword.get());
    }
    model.addAttribute("pizzas", pizzas);
    return "/pizza/index";
  }
  
  @GetMapping("/{id}")
  public String show(@PathVariable("id") int pizzaId, Model model) {
    Pizza pizza = pizzaRepository.getReferenceById(pizzaId);
    model.addAttribute("pizza", pizza);
    return "/pizza/show";
  }
}
