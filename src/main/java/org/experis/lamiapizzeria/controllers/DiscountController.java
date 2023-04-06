package org.experis.lamiapizzeria.controllers;

import jakarta.validation.Valid;
import org.experis.lamiapizzeria.exception.PizzaNotFoundException;
import org.experis.lamiapizzeria.models.Discount;
import org.experis.lamiapizzeria.repository.DiscountRepository;
import org.experis.lamiapizzeria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/discount")
public class DiscountController {
  
  @Autowired
  private PizzaService pizzaService;
  
  @Autowired
  private DiscountRepository discountRepository;
  
  @GetMapping
  public String index() {
    return "/discount/index";
  }
  
  @GetMapping("/create")
  public String create(Model model, @RequestParam(name = "pizza") Integer pizzaId) {
    Discount localDiscount = new Discount();
    localDiscount.setPizza(pizzaService.findById(pizzaId).orElseThrow(() ->
        new PizzaNotFoundException("no pizza found for id: " + pizzaId)));
    localDiscount.setStartDate(LocalDate.now());
    localDiscount.setEndDate(LocalDate.now().plusDays(1));
    model.addAttribute("discount", localDiscount);
    return "/discount/create";
  }
  
  @PostMapping("/create")
  public String createPost(@Valid @ModelAttribute("discount") Discount formDiscount, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "/discount/create";
    }
    discountRepository.save(formDiscount);
    return "redirect:/pizza/" + formDiscount.getPizza().getId();
  }
}
