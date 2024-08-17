package br.com.mizaeldouglas.api.controller.patient;

import br.com.mizaeldouglas.api.domain.patient.Patient;
import br.com.mizaeldouglas.api.dto.auth.ResponseDTO;
import br.com.mizaeldouglas.api.dto.patient.PatientLoginRequestDTO;
import br.com.mizaeldouglas.api.dto.patient.PatientRequestDto;
import br.com.mizaeldouglas.api.dto.patient.PatientResponseDto;
import br.com.mizaeldouglas.api.infra.security.TokenService;
import br.com.mizaeldouglas.api.repositories.patient.PatientRepository;
import br.com.mizaeldouglas.api.service.patient.PatientService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/patient")
@Tag(name ="Patients")
@SecurityRequirement(name = "bearerAuth")
public class PatientController {

    private final PatientService patientService;
    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public PatientController(PatientService patientService, PatientRepository patientRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.patientService = patientService;
        this.patientRepository = patientRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDto> update(@PathVariable Long id, @RequestBody PatientRequestDto request){
        PatientResponseDto response =  patientService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDto>> findAll(){
        List<PatientResponseDto> response = patientService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDto> findById(@PathVariable Long id){
        PatientResponseDto response = patientService.findById(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PatientResponseDto> delete(@PathVariable Long id){
        patientService.findById(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/login")
    public ResponseEntity loginPatient(@RequestBody PatientLoginRequestDTO body) {
        Patient patient = this.patientRepository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("Patient not found"));
        if (passwordEncoder.matches(body.password(), patient.getPassword())) {
            String token = this.tokenService.generateToken(patient);
            return ResponseEntity.ok(new ResponseDTO(patient.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity registerPatient(@RequestBody Patient patient) {
        if (patientRepository.findByEmail(patient.getEmail()).isEmpty()) {
            patient.setPassword(passwordEncoder.encode(patient.getPassword()));
            patientRepository.save(patient);
            String token = tokenService.generateToken(patient);
            return ResponseEntity.ok(new ResponseDTO(patient.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}
