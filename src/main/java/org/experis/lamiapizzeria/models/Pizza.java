package org.experis.lamiapizzeria.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "pizzas")
public class Pizza {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  
  private String name;
  @Lob
  private String description;
  private BigDecimal price;
  
  @Column(columnDefinition = "varchar(255) default 'https://upload.wikimedia.org/wikipedia/commons/a/a3/Eq_it-na_pizza-margherita_sep2005_sml.jpg'")
  private String imgUrl;
  
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
}
