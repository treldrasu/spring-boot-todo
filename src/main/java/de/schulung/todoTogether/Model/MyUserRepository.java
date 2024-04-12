package de.schulung.todoTogether.Model;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface MyUserRepository extends CrudRepository<MyUser, Long>{
    public Optional<MyUser> findByUsername(String usernam);
}
