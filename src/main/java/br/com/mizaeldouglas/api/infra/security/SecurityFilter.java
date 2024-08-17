package br.com.mizaeldouglas.api.infra.security;

import br.com.mizaeldouglas.api.domain.doctor.Doctor;
import br.com.mizaeldouglas.api.domain.patient.Patient;
import br.com.mizaeldouglas.api.repositories.doctor.DoctorRepository;
import br.com.mizaeldouglas.api.repositories.patient.PatientRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    TokenService tokenService;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    PatientRepository patientRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        var login = tokenService.validateToken(token);

        if (login != null) {
            Doctor doctor = doctorRepository.findByEmail(login).orElse(null);
            if (doctor != null) {
                var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_DOCTOR"));
                var authentication = new UsernamePasswordAuthenticationToken(doctor, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                Patient patient = patientRepository.findByEmail(login).orElse(null);
                if (patient != null) {
                    var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_PATIENT"));
                    var authentication = new UsernamePasswordAuthenticationToken(patient, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}