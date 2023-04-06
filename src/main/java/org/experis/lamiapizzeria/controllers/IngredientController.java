package org.experis.lamiapizzeria.controllers;

import org.experis.lamiapizzeria.models.Ingredient;
import org.experis.lamiapizzeria.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {
  
  @Autowired
  IngredientService ingredientService;
  
  @GetMapping
  public String index(Model model) {
    model.addAttribute("ingredients", ingredientService.getIngredients());
    model.addAttribute("ingredient", new Ingredient());
    return "ingredients/index";
  }
  
  @PostMapping("/create")
  public String create(@ModelAttribute("ingredient") Ingredient ingredient) {
    ingredientService.createNewIngredient(ingredient);
    return "redirect:/ingredients";
  }
}
