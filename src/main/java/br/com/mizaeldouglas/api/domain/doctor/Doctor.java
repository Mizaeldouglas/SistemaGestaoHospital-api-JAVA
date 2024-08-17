package br.com.mizaeldouglas.api.domain.doctor;

import br.com.mizaeldouglas.api.domain.login.Login;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "TB_DOCTORS")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Doctor extends Login {
    private String crm;
    private String specialty;
}
