package org.example.clase7labmarvel.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
    @Size(max=500, message="El campo no puede ser más de 500 caracteres")
    private String nombre;

    @Column(name="url",nullable = false)
    @Size(max=500, message="El campo no puede ser más de 500 caracteres")
    private String url;

    @Column(name="identity")
    @Size(max=45, message="El campo no puede ser más de 45 caracteres")
    private String identidad;

    @Column(name="align")
    @Size(max=45, message="El campo no puede ser más de 45 caracteres")
    private String align;

    @Column(name="eye")
    @Size(max=45, message="El campo no puede ser más de 45 caracteres")
    private String eye;

    @Column(name="hair")
    @Size(max=45, message="El campo no puede ser más de 45 caracteres")
    private String hair;

    @Column(name="sex")
    @Size(max=45, message="El campo no puede ser más de 45 caracteres")
    private String sex;

    @Column(name="gsm")
    @Size(max=45, message="El campo no puede ser más de 45 caracteres")
    private String gsm;

    private int appearances;

    @Column(name="firstappearance")
    @Size(max=45, message="El campo no puede ser más de 45 caracteres")
    private String alive;

    private int year;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIdentidad() {
        return identidad;
    }

    public void setIdentidad(String identidad) {
        this.identidad = identidad;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public String getEye() {
        return eye;
    }

    public void setEye(String eye) {
        this.eye = eye;
    }

    public String getHair() {
        return hair;
    }

    public void setHair(String hair) {
        this.hair = hair;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getGsm() {
        return gsm;
    }

    public void setGsm(String gsm) {
        this.gsm = gsm;
    }

    public int getAppearances() {
        return appearances;
    }

    public void setAppearances(int appearances) {
        this.appearances = appearances;
    }

    public String getAlive() {
        return alive;
    }

    public void setAlive(String alive) {
        this.alive = alive;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
