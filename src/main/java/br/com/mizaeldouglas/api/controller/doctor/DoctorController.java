package br.com.mizaeldouglas.api.controller.doctor;


import br.com.mizaeldouglas.api.dto.doctor.DoctorRequestDto;
import br.com.mizaeldouglas.api.dto.doctor.DoctorResponseDto;
import br.com.mizaeldouglas.api.service.doctor.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "doctor")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<DoctorResponseDto> create(@RequestBody DoctorRequestDto request){
        DoctorResponseDto doctorResponseDto = doctorService.create(request);

        return ResponseEntity.ok(doctorResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponseDto>> findAll(){
        List<DoctorResponseDto> doctors = doctorService.findAll();
        return ResponseEntity.ok(doctors);
    }
}
