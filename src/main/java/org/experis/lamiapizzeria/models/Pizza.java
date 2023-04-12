package org.experis.lamiapizzeria.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "pizzas")
public class Pizza {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Lob
  @Size(max = 255, message = "La descrizione ha una grandezza massima di 255 caratteri")
  private String description;
  @Column(columnDefinition = "varchar(255) default 'https://upload.wikimedia.org/wikipedia/commons/a/a3/Eq_it-na_pizza-margherita_sep2005_sml.jpg'")
  private String imgUrl = "https://upload.wikimedia.org/wikipedia/commons/a/a3/Eq_it-na_pizza-margherita_sep2005_sml.jpg";
  @NotEmpty(message = "Il campo 'nome' non può essere vuoto.")
  private String name;
  @DecimalMin(message = "Il prezzo deve essere positivo", value = "0.1")
  @NotNull(message = "Il campo non può essere vuoto")
  private BigDecimal price;
  
  @OneToMany(mappedBy = "pizza")
  private List<Discount> discountList;
  @ManyToMany
  @JoinTable(
      name = "ingredient_pizza",
      joinColumns = @JoinColumn(name = "pizza_id"),
      inverseJoinColumns = @JoinColumn(name = "ingredient_id")
  )
  private List<Ingredient> ingredients;
  
  public Pizza() {
  }
  
  public List<Ingredient> getIngredients() {
    return ingredients;
  }
  
  public void setIngredients(List<Ingredient> ingredients) {
    this.ingredients = ingredients;
  }
  
  public List<Discount> getDiscountList() {
    return discountList;
  }
  
  public void setDiscountList(List<Discount> discountList) {
    this.discountList = discountList;
  }
  
  public String getImgUrl() {
    return imgUrl;
  }
  
  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }
  
  public Integer getId() {
    return id;
  }
  
  public void setId(Integer id) {
    this.id = id;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getDescription() {
    return description;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  public BigDecimal getPrice() {
    return price;
  }
  
  public void setPrice(BigDecimal price) {
    this.price = price;
  }
  
  @Override
  public String toString() {
    return "Pizza{" +
        "id=" + id +
        ", description='" + description + '\'' +
        ", imgUrl='" + imgUrl + '\'' +
        ", name='" + name + '\'' +
        ", price=" + price +
        '}';
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Pizza pizza)) return false;
    
    return getId() != null ? getId().equals(pizza.getId()) : pizza.getId() == null;
  }
  
  @Override
  public int hashCode() {
    return getId() != null ? getId().hashCode() : 0;
  }
}
