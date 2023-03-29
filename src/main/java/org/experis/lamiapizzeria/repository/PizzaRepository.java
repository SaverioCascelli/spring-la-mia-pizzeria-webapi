package org.experis.lamiapizzeria.repository;

import org.experis.lamiapizzeria.models.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza,Integer> {
}
