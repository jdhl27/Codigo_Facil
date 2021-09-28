package net.software.backendJava.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.software.backendJava.UserRepository;
import net.software.backendJava.entities.UserEntity;
import net.software.backendJava.shared.dto.UserDTO;

@Service
public class UserService implements UserServiceInterface {

  @Autowired
  UserRepository userRepository;

  @Override
  public UserDTO createUser(UserDTO userDTO) {
    // TODO create user logic 

    UserEntity userEntity = new UserEntity();
    BeanUtils.copyProperties(userDTO, userEntity);

    userEntity.setEncryptedPassword("testpassword");
    userEntity.setUserId("testUserId");

    UserEntity storedUserDetail = userRepository.save(userEntity);

    UserDTO userToReturn = new UserDTO();
    BeanUtils.copyProperties(storedUserDetail, userToReturn);
    return userToReturn;
  }
  
  
}
