package br.com.mizaeldouglas.api.repositories;

import br.com.mizaeldouglas.api.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByEmail(String email);
}
