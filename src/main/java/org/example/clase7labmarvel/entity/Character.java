package org.example.clase7labmarvel.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="characters")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private int id;

    @Column(name="name",nullable = false)
    @Size(min=3, max=100, message="El campo tiene que estar entre 3 y 100 caracteres.")
    @NotBlank(message = "No puede estar vacío.")
    private String name;

    @Column(name="url",nullable = false)
    @Size(min=3, max=100, message="El campo tiene que estar entre 3 y 100 caracteres.")
    @NotBlank(message = "No puede estar vacío.")
    private String url;

    @Column(name="identity")
    @Size(max=45, message="El campo no puede tener más de 45 caracteres")
    private String identity;

    @Column(name="align")
    @Size(max=45, message="El campo no puede tener más de 45 caracteres")
    private String align;

    @Column(name="eye")
    @Size(max=45, message="El campo no puede tener más de 45 caracteres")
    private String eye;

    @Column(name="hair")
    @Size(max=45, message="El campo no puede tener más de 45 caracteres")
    private String hair;

    @Column(name="sex")
    @Size(max=45, message="El campo no puede tener más de 45 caracteres")
    private String sex;

    @Column(name="gsm")
    @Size(max=45, message="El campo no puede tener más de 45 caracteres")
    private String gsm;

    @Column(name="alive")
    @Size(max=45, message="El campo no puede tener más de 45 caracteres")
    private String alive;

    @Column(name="appearances")
    @Positive(message = "Debe ser mayor que cero.")
    @Digits(integer = 5, fraction = 0)
    private int appearances;

    @Column(name="first_appearance")
    @Size(max=45, message="El campo no puede ser más de 45 caracteres")
    private String firstAppearance;

    @Column(name="year")
    @Positive(message = "Debe ser mayor que cero.")
    @Max(value = 2050,message = "El valor de año máximo es 2050")
    private int year;

}
