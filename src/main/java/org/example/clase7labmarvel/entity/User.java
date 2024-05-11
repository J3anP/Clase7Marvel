package org.example.clase7labmarvel.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private int id;

    @Column(name="fullname",nullable = false)
    @Size(max=45,message="Debe tener menos de 45 caracteres")
    private String fullname;

    @Column(name="username",nullable = false)
    @Size(max=45,message="Debe tener menos de 45 caracteres")
    private String username;

    @Column(name="email")
    @Size(max=45,message="Debe tener menos de 45 caracteres")
    private String email;

    @Column(name="password",nullable = false)
    @Size(max=100,message="Debe tener menos de 100 caracteres")
    private String password;

    @Column(name="status",nullable = false)
    @Size(max=1,message = "Como maximo un caracter")
    private int status;


}
