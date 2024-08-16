package br.com.mizaeldouglas.api.domain.user;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class User {
    private String Name;
    private String email;
    private String password;
    private String phone;
}
