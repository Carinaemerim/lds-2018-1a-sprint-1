package br.edu.ifrs.canoas.tads.tcc.repository;

import br.edu.ifrs.canoas.tads.tcc.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Created by rodrigo on 2/21/17.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}