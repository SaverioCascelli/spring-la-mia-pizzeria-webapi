package org.experis.lamiapizzeria.controllers;

import jakarta.validation.Valid;
import org.experis.lamiapizzeria.exception.PizzaNotFoundException;
import org.experis.lamiapizzeria.models.Pizza;
import org.experis.lamiapizzeria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizza")
public class PizzaController {

//  @Autowired
//  private PizzaRepository pizzaRepository;
  
  @Autowired
  private PizzaService pizzaService;
  
  
  @GetMapping
  public String index(Model model, @RequestParam(name = "search") Optional<String> keyword) {
    
    List<Pizza> pizzas;
    if (keyword.isEmpty()) {
      pizzas = pizzaService.findAll();
    } else {
      String search = keyword.get().trim();
      pizzas = pizzaService.findByName(search);
    }
    model.addAttribute("pizzas", pizzas);
    return "/pizza/index";
  }
  
  @GetMapping("/{id}")
  public String show(@PathVariable("id") int pizzaId, Model model) {
    Pizza pizza = pizzaService.findById(pizzaId).orElseThrow(() -> new PizzaNotFoundException("pizza with ID: " + pizzaId + " not found"));
//        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No pizza found for id: " + pizzaId));
    model.addAttribute("pizza", pizza);
    return "/pizza/show";
  }
  
  @GetMapping("/create")
  public String create(Model model) {
    model.addAttribute("pizza", new Pizza());
    return "/pizza/create";
  }
  
  @PostMapping("/create")
  public String create(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
    
    if (bindingResult.hasErrors()) {
      return "/pizza/create";
    }
    pizzaService.createPizza(formPizza);
    return "redirect:/pizza";
  }
  
  @GetMapping("/update/{id}")
  public String update(Model model, @PathVariable("id") Integer id) {
    model.addAttribute("pizza", pizzaService.findById(id).orElseThrow(() -> new PizzaNotFoundException("pizza with id " + id + " not found")));
    return "/pizza/update";
  }
  
  @PostMapping("update/{id}")
  public String postUpdate(
      @ModelAttribute("pizza") Pizza pizza, BindingResult bindingResult, @PathVariable Integer id,
      RedirectAttributes redirectAttributes) {
    pizzaService.update(pizza);
    return "redirect:/pizza";
    
  }
}




























