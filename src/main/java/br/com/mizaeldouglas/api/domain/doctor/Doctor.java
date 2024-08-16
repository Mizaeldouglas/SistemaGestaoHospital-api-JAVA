package br.com.mizaeldouglas.api.domain.doctor;

import br.com.mizaeldouglas.api.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "DOCTORS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Doctor extends User {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String crm;
    private String specialty;
}
