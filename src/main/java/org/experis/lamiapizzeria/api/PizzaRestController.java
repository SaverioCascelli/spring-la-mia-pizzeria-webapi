package org.experis.lamiapizzeria.api;

import org.experis.lamiapizzeria.exception.PizzaNotFoundException;
import org.experis.lamiapizzeria.models.Pizza;
import org.experis.lamiapizzeria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/pizza")
public class PizzaRestController {

    @Autowired
    PizzaService pizzaService;

    @GetMapping()
    public List<Pizza> index(@RequestParam(name = "search") Optional<String> keyword) {
        if (keyword.isEmpty()) {
            return pizzaService.findAll();
        } else {
            String search = keyword.get().trim();
            return pizzaService.findByName(search);
        }
    }

    @GetMapping("/{id}")
    public Pizza getById(@PathVariable Integer id) {
        Optional<Pizza> pizza = pizzaService.findById(id);
        if (pizza.isEmpty()) {
            throw new PizzaNotFoundException("pizza not founded for get");
        } else return pizza.get();
    }

    @PostMapping()
    public void create(@RequestBody Pizza pizza) {
        pizzaService.createPizza(pizza);
    }

    @PutMapping("/{id}/update")
    public void update(@PathVariable Integer id, @RequestBody Pizza pizza) {
        pizzaService.update(pizza, id);
    }

    @DeleteMapping("/{id}/delete")
    public void delete(@PathVariable Integer id) {
        pizzaService.deleteById(id);
    }

}
