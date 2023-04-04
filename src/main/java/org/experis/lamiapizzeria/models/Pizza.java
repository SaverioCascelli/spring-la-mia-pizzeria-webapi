package org.experis.lamiapizzeria.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

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
  @Positive(message = "Il prezzo deve essere positivo")
  private BigDecimal price;
  
  public Pizza() {
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
