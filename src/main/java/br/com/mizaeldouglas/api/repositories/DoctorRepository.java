package br.com.mizaeldouglas.api.repositories;

import br.com.mizaeldouglas.api.domain.doctor.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}