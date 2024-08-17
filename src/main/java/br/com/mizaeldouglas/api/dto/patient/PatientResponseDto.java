package br.com.mizaeldouglas.api.dto.patient;

import java.io.Serializable;
import java.util.Date;

public record PatientResponseDto(Long id, String name, String email, String phone, String cpf, String address,
                                 Date birthDate) implements Serializable {
}