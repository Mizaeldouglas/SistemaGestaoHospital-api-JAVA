package br.com.mizaeldouglas.api.dto.doctor;

import java.io.Serializable;

public record DoctorResponseDto(Long id, String crm, String specialty, String name, String email, String phone) implements Serializable {
}
