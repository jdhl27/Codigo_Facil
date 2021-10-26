package net.software.backendJava.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import net.software.backendJava.shared.dto.UserDTO;

public interface UserServiceInterface extends UserDetailsService {
  
  public UserDTO createUser(UserDTO userDTO);
  

}
