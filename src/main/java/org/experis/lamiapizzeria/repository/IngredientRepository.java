package org.experis.lamiapizzeria.repository;

import org.experis.lamiapizzeria.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

}
