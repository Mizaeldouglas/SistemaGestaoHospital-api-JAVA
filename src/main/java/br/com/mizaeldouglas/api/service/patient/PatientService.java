package br.com.mizaeldouglas.api.service.patient;

import br.com.mizaeldouglas.api.domain.patient.Patient;
import br.com.mizaeldouglas.api.dto.patient.PatientRequestDto;
import br.com.mizaeldouglas.api.dto.patient.PatientResponseDto;
import br.com.mizaeldouglas.api.repositories.patient.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Transactional
    public PatientResponseDto create(PatientRequestDto request) {
        Patient patient = new Patient();
        patient.setName(request.name());
        patient.setEmail(request.email());
        patient.setPassword(request.password());
        patient.setPhone(request.phone());
        patient.setCpf(request.cpf());
        patient.setAddress(request.address());
        patient.setBirthDate(request.birthDate());

        patient = patientRepository.save(patient);

        return new PatientResponseDto(
                patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getPhone(),
                patient.getCpf(),
                patient.getAddress(),
                patient.getBirthDate()
        );
    }

    public PatientResponseDto findById(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Doctor not Found"));
        return new PatientResponseDto(
                patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getPhone(),
                patient.getCpf(),
                patient.getAddress(),
                patient.getBirthDate()
        );
    }

    public List<PatientResponseDto> findAll() {
        return patientRepository.findAll().stream().map(patient ->
                new PatientResponseDto(
                        patient.getId(),
                        patient.getName(),
                        patient.getEmail(),
                        patient.getPhone(),
                        patient.getCpf(),
                        patient.getAddress(),
                        patient.getBirthDate()
                )
        ).toList();
    }

    @Transactional
    public PatientResponseDto update(Long id, PatientRequestDto request) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);

        if (optionalPatient.isPresent()){
            Patient patient = optionalPatient.get();
            patient.setName(request.name() != null ? request.name() : patient.getName());
            patient.setEmail(request.email() != null ? request.email() : patient.getEmail());
            patient.setPassword(request.password() != null ? request.password() : patient.getPassword());
            patient.setPhone(request.phone() != null ? request.phone() : patient.getPhone());
            patient.setCpf(request.cpf() != null ? request.cpf() : patient.getCpf());
            patient.setAddress(request.address() != null ? request.address() : patient.getAddress());
            patient.setBirthDate(request.birthDate() != null ? request.birthDate() : patient.getBirthDate());
            patientRepository.save(patient);
            return new PatientResponseDto(
                    patient.getId(),
                    patient.getName(),
                    patient.getEmail(),
                    patient.getPhone(),
                    patient.getCpf(),
                    patient.getAddress(),
                    patient.getBirthDate()
            );
        }else {
            throw new IllegalArgumentException("Patient not found");
        }
    }

    public void delete(Long id) {
        patientRepository.deleteById(id);
    }
}
