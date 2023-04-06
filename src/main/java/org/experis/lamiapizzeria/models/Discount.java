package org.experis.lamiapizzeria.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "discounts")
public class Discount {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @NotEmpty
  private String title;
  @NotNull
  private LocalDate startDate;
  @NotNull
  private LocalDate endDate;
  @ManyToOne
  @JoinColumn(name = "pizza_id", nullable = false)
  private Pizza pizza;
  @DecimalMin("0.01")
  private BigDecimal discountPercent;
  
  public Discount() {
  }
  
  public BigDecimal getDiscountPercent() {
    return discountPercent;
  }
  
  public void setDiscountPercent(BigDecimal discountPercent) {
    this.discountPercent = discountPercent;
  }
  
  public Integer getId() {
    return id;
  }
  
  public void setId(Integer id) {
    this.id = id;
  }
  
  public String getTitle() {
    return title;
  }
  
  public void setTitle(String title) {
    this.title = title;
  }
  
  public LocalDate getStartDate() {
    return startDate;
  }
  
  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }
  
  public LocalDate getEndDate() {
    return endDate;
  }
  
  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }
  
  public Pizza getPizza() {
    return pizza;
  }
  
  public void setPizza(Pizza pizza) {
    this.pizza = pizza;
  }
}
