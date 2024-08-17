package br.com.mizaeldouglas.api.dto.doctor;

import java.io.Serializable;

public record DoctorDetailsDto(Long id, String crm, String specialty, String name, String email, String phone) implements Serializable {
}