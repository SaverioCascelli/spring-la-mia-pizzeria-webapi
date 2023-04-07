package org.experis.lamiapizzeria.service;


import org.experis.lamiapizzeria.auth.DatabaseUserDetails;
import org.experis.lamiapizzeria.models.User;
import org.experis.lamiapizzeria.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class DatabaseUserDetailService implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;
  
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByUsername(username);
    if (user.isPresent()) {
      return new DatabaseUserDetails(user.get());
    } else {
      throw new UsernameNotFoundException("User with username " + username + " not found");
    }
  }
}
