package com.softdev.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCredential {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int DNI;
    private String name;
    private String email;
    private String password;
    private String Role;
}
