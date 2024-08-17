package br.com.mizaeldouglas.api.domain.patient;

import br.com.mizaeldouglas.api.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Table(name = "TB_PATIENTS")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient  extends User {
    private String cpf;
    private String address;
    private Date birthDate;
}
