package br.com.futuroDev.APISustentavel.Repository;

import br.com.futuroDev.APISustentavel.Model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);
    boolean existsByUsername(String username);
}
