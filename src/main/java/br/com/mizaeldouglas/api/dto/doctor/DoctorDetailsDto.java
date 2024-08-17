package br.com.mizaeldouglas.api.dto.doctor;

import java.io.Serializable;

public record DoctorDetailsDto(String name, String email, String phone, String crm,
                               String specialty) implements Serializable {
}