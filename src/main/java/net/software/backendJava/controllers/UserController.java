package net.software.backendJava.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.software.backendJava.models.request.UserDetailRequestModel;

@RestController
@RequestMapping("/users")
public class UserController {

  // Consultar información
  @GetMapping
  public String getUser(){
    return "Obtener usuarios";
  }

  // Creando información
  @PostMapping
  public String createUser(@RequestBody UserDetailRequestModel userDetails){
    return null;
  }
}