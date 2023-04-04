package org.experis.lamiapizzeria.service;

import org.experis.lamiapizzeria.exception.PizzaNotFoundException;
import org.experis.lamiapizzeria.models.Pizza;
import org.experis.lamiapizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {
  
  @Autowired
  PizzaRepository pizzaRepository;
  
  public Pizza createPizza(Pizza pizza) throws PizzaNotFoundException {
    
    Pizza localPizza = new Pizza();
    localPizza.setName(pizza.getName());
    localPizza.setDescription(pizza.getDescription());
    localPizza.setPrice(pizza.getPrice());
    return pizzaRepository.save(localPizza);
  }
  
  public List<Pizza> findByName(String keyword) {
    return pizzaRepository.findByNameContainingIgnoreCase(keyword);
  }
  
  public Optional<Pizza> findById(int id) {
    return pizzaRepository.findById(id);
  }
  
  
  public List<Pizza> findAll() {
    return pizzaRepository.findAll();
  }
  
  public boolean update(Pizza pizza) {
    boolean edited = false;
    pizzaRepository.save(pizza);
    
    return edited;
  }
  
}










