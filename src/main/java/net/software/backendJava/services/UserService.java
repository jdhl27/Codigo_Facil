package net.software.backendJava.services;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.software.backendJava.UserRepository;
import net.software.backendJava.entities.UserEntity;
import net.software.backendJava.shared.dto.UserDTO;

@Service
public class UserService implements UserServiceInterface {

  @Autowired
  UserRepository userRepository;

  @Autowired
  BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public UserDTO createUser(UserDTO userDTO) {

    if (userRepository.findUserByEmail(userDTO.getEmail()) != null) 
      throw new RuntimeException("User already exist");
      
    // TODO create user logic 
    UserEntity userEntity = new UserEntity();
    BeanUtils.copyProperties(userDTO, userEntity);

    UUID userId = UUID.randomUUID();
    userEntity.setUserId(userId.toString());
    
    userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));

    UserEntity storedUserDetail = userRepository.save(userEntity);

    UserDTO userToReturn = new UserDTO();
    BeanUtils.copyProperties(storedUserDetail, userToReturn);
    return userToReturn;  
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    UserEntity userEntity = userRepository.findUserByEmail(email);

    if(userEntity == null) {
        throw new UsernameNotFoundException(email);
    }
    return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>() );
}
  
  
}
