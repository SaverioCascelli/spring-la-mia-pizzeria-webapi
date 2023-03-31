package org.experis.lamiapizzeria.controllers;

import org.experis.lamiapizzeria.models.Pizza;
import org.experis.lamiapizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
      String search = keyword.get().trim();
      pizzas = pizzaRepository.findByNameContainingIgnoreCase(search);
    }
    model.addAttribute("pizzas", pizzas);
    return "/pizza/index";
  }
  
  @GetMapping("/{id}")
  public String show(@PathVariable("id") int pizzaId, Model model) {
    Pizza pizza = pizzaRepository.findById(pizzaId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No pizza found for id: " + pizzaId));
    model.addAttribute("pizza", pizza);
    return "/pizza/show";
  }
  
  @GetMapping("/create")
  public String create(Model model) {
    model.addAttribute("pizza", new Pizza());
    return "/pizza/create";
  }
  
  @PostMapping("/create")
  public String create(@ModelAttribute("pizza") Pizza formPizza) {
    
    pizzaRepository.save(formPizza);
    return "redirect:/pizza";
  }
}




























