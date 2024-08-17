package br.com.mizaeldouglas.api.dto.patient;

import java.io.Serializable;
import java.util.Date;

public record PatientRequestDto(Long id, String cpf, String address, Date birthDate,String name,String email, String password, String phone) implements Serializable {
}