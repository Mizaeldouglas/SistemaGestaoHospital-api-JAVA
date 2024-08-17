package br.com.mizaeldouglas.api.service.doctor;

import br.com.mizaeldouglas.api.domain.doctor.Doctor;
import br.com.mizaeldouglas.api.dto.doctor.DoctorRequestDto;
import br.com.mizaeldouglas.api.dto.doctor.DoctorResponseDto;
import br.com.mizaeldouglas.api.repositories.doctor.DoctorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }


    @Transactional
    public DoctorResponseDto create(DoctorRequestDto doctorRequestDto) {
        Doctor doctor = new Doctor();
        doctor.setName(doctorRequestDto.name());
        doctor.setEmail(doctorRequestDto.email());
        doctor.setPhone(doctorRequestDto.phone());
        doctor.setCrm(doctorRequestDto.crm());
        doctor.setSpecialty(doctorRequestDto.specialty());
        doctor.setPassword(doctorRequestDto.password());

        doctor = doctorRepository.save(doctor);

        return new DoctorResponseDto(
                doctor.getId(),
                doctor.getCrm(),
                doctor.getSpecialty(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getPhone()
        );
    }

    public DoctorResponseDto findById(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Doctor not Found"));
        return new DoctorResponseDto(
                doctor.getId(),
                doctor.getCrm(),
                doctor.getSpecialty(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getPhone()
        );
    }


    public List<DoctorResponseDto> findAll() {
        return doctorRepository.findAll().stream().map(doctor ->
                new DoctorResponseDto(
                        doctor.getId(),
                        doctor.getCrm(),
                        doctor.getSpecialty(),
                        doctor.getName(),
                        doctor.getEmail(),
                        doctor.getPhone()
                )
        ).toList();
    }

    @Transactional
    public DoctorResponseDto update(Long id, DoctorRequestDto doctorRequestDto) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();
            doctor.setName(doctorRequestDto.name() != null ? doctorRequestDto.name() : doctor.getName());
            doctor.setEmail(doctorRequestDto.email() != null ? doctorRequestDto.email() : doctor.getEmail());
            doctor.setPhone(doctorRequestDto.phone() != null ? doctorRequestDto.phone() : doctor.getPhone());
            doctor.setCrm(doctorRequestDto.crm() != null ? doctorRequestDto.crm() : doctor.getCrm());
            doctor.setSpecialty(doctorRequestDto.specialty() != null ? doctorRequestDto.specialty() : doctor.getSpecialty());
            doctor.setPassword(doctorRequestDto.password() != null ? doctorRequestDto.password() : doctor.getPassword());
            doctorRepository.save(doctor);

            return new DoctorResponseDto(
                    doctor.getId(),
                    doctor.getCrm(),
                    doctor.getSpecialty(),
                    doctor.getName(),
                    doctor.getEmail(),
                    doctor.getPhone()
            );
        } else {
            throw new IllegalArgumentException("Doctor not found");
        }
    }

    public void delete(Long id) {
        doctorRepository.deleteById(id);
    }
}
