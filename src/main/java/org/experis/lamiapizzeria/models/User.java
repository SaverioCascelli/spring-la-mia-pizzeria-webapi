package org.experis.lamiapizzeria.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @NotEmpty
  @Column(nullable = false, unique = true)
  private String username;
  @Column(nullable = false)
  private String password;
  @NotEmpty
  private String name;
  
  @ManyToMany(fetch = FetchType.EAGER)
  private List<Role> roles;
  
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
  
  public String getUsername() {
    return username;
  }
  
  public void setUsername(String username) {
    this.username = username;
  }
  
  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  public List<Role> getRoles() {
    return roles;
  }
  
  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }
}
