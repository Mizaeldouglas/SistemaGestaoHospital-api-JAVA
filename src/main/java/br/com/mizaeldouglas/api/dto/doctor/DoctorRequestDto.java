package br.com.mizaeldouglas.api.dto.doctor;

import java.io.Serializable;

public record DoctorRequestDto(Long id, String crm, String specialty,String name,String email, String password, String phone) implements Serializable {
}
