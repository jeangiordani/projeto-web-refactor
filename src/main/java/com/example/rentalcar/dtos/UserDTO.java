package com.example.rentalcar.dtos;

import com.example.rentalcar.entities.Role;
import com.example.rentalcar.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serial;
import java.io.Serializable;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class UserDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "Campo obrigatório")
    private String firstName;

    @NotBlank(message = "Campo obrigatório")
    private String lastName;

    @NotBlank(message = "Campo obrigatório")
    @Email(message = "Email inválido")
    private String email;
    private LocalDate birthday;

    @NotBlank(message = "Campo obrigatório")
    @Pattern(regexp = "(^(\\d{3}.\\d{3}.\\d{3}-\\d{2})|(\\d{11})$)", message = "CPF inválido")
    private String cpf;

    @NotNull(message = "Campo obrigatório")
    private Integer cnh;
    private Integer phoneNumber;
    private Role role;

    public UserDTO() {
    }

    public UserDTO(Long id, String firstName, String lastName, String email, LocalDate birthday, String cpf, Integer cnh, Integer phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.cpf = cpf;
        this.cnh = cnh;
        this.phoneNumber = phoneNumber;
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.birthday = user.getBirthday();
        this.cpf = user.getCpf();
        this.cnh = user.getCnh();
        this.phoneNumber = user.getPhoneNumber();
        this.role = user.getRole();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        Locale LOCALE = Locale.ITALIAN;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy").withLocale(LOCALE);
        this.birthday = LocalDate.parse(birthday, formatter);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getCnh() {
        return cnh;
    }

    public void setCnh(Integer cnh) {
        this.cnh = cnh;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
