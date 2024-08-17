package br.com.mizaeldouglas.api.controller.doctor;


import br.com.mizaeldouglas.api.domain.doctor.Doctor;
import br.com.mizaeldouglas.api.dto.auth.ResponseDTO;
import br.com.mizaeldouglas.api.dto.doctor.DoctorDetailsDto;
import br.com.mizaeldouglas.api.dto.doctor.DoctorLoginRequestDTO;
import br.com.mizaeldouglas.api.dto.doctor.DoctorResponseDto;
import br.com.mizaeldouglas.api.infra.security.TokenService;
import br.com.mizaeldouglas.api.repositories.doctor.DoctorRepository;
import br.com.mizaeldouglas.api.service.doctor.DoctorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/doctor")
@Tag(name ="Doctors")
@SecurityRequirement(name = "bearerAuth")
public class DoctorController {

    private final DoctorService doctorService;
    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public DoctorController(DoctorService doctorService, DoctorRepository doctorRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.doctorService = doctorService;
        this.doctorRepository = doctorRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponseDto>> findAll() {
        List<DoctorResponseDto> doctors = doctorService.findAll();
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDetailsDto> findById(@PathVariable Long id) {
        DoctorDetailsDto doctorDetailsDto = doctorService.findById(id);
        return ResponseEntity.ok(doctorDetailsDto);
    }

    @PostMapping("/login")
    public ResponseEntity loginDoctor(@RequestBody DoctorLoginRequestDTO body) {
        Doctor doctor = this.doctorRepository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("Doctor not found"));
        if (passwordEncoder.matches(body.password(), doctor.getPassword())) {
            String token = this.tokenService.generateToken(doctor);
            return ResponseEntity.ok(new ResponseDTO(doctor.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity registerDoctor(@RequestBody Doctor doctor) {
        if (doctorRepository.findByEmail(doctor.getEmail()).isEmpty()) {
            doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
            doctorRepository.save(doctor);
            String token = tokenService.generateToken(doctor);
            return ResponseEntity.ok(new ResponseDTO(doctor.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }


}








//    @GetMapping
//    public ResponseEntity<List<DoctorResponseDto>> findAll(){
//        List<DoctorResponseDto> doctors = doctorService.findAll();
//        return ResponseEntity.ok(doctors);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<DoctorDetailsDto> getDoctorDetails(@PathVariable Long id){
//        DoctorDetailsDto doctorDetails = doctorService.getDoctorDetails(id);
//        return ResponseEntity.ok(doctorDetails);
//
//    }
//
//
//    @PostMapping("/login")
//    public ResponseEntity login(@RequestBody LoginRequestDTO body){
//        User user = this.userRepository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
//        if(passwordEncoder.matches(body.password(), user.getPassword())) {
//            String token = this.tokenService.generateToken(user);
//            return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
//        }
//        return ResponseEntity.badRequest().build();
//    }
//
//    @PostMapping("/login/doctor")
//    public ResponseEntity loginDoctor(@RequestBody DoctorLoginRequestDTO body){
//        Doctor doctor = this.doctorRepository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("Doctor not found"));
//        if(passwordEncoder.matches(body.password(), doctor.getPassword())) {
//            String token = this.tokenService.generateToken(doctor);
//            return ResponseEntity.ok(new ResponseDTO(doctor.getName(), token));
//        }
//        return ResponseEntity.badRequest().build();
//    }
//
//    @PostMapping("/login/patient")
//    public ResponseEntity loginPatient(@RequestBody PatientLoginRequestDTO body){
//        Patient patient = this.patientRepository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("Patient not found"));
//        if(passwordEncoder.matches(body.password(), patient.getPassword())) {
//            String token = this.tokenService.generateToken(patient);
//            return ResponseEntity.ok(new ResponseDTO(patient.getName(), token));
//        }
//        return ResponseEntity.badRequest().build();
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity register(@RequestBody RegisterRequestDTO body){
//        Optional<User> user = this.userRepository.findByEmail(body.email());
//
//        if(user.isEmpty()) {
//            User newUser = new User();
//            newUser.setPassword(passwordEncoder.encode(body.password()));
//            newUser.setEmail(body.email());
//            newUser.setName(body.name());
//            this.userRepository.save(newUser);
//
//            String token = this.tokenService.generateToken(newUser);
//            return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token));
//        }
//        return ResponseEntity.badRequest().build();
//    }
