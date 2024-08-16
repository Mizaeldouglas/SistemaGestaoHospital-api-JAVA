package br.com.mizaeldouglas.api.repositories;

import br.com.mizaeldouglas.api.domain.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
  }