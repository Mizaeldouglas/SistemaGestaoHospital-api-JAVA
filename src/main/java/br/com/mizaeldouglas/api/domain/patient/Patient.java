package br.com.mizaeldouglas.api.domain.patient;

import br.com.mizaeldouglas.api.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "PATIENTS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient extends User {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private String address;
    private Date birthDate;



}
