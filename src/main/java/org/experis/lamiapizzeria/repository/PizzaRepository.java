package org.experis.lamiapizzeria.repository;

import org.experis.lamiapizzeria.models.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
  public List<Pizza> findByNameContainingIgnoreCase(String keyword);
}
