package org.experis.lamiapizzeria.service;

import org.experis.lamiapizzeria.models.Ingredient;
import org.experis.lamiapizzeria.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {
  
  @Autowired
  IngredientRepository ingredientRepository;
  
  public List<Ingredient> getIngredients() {
    return ingredientRepository.findAll();
  }
  
  
}
