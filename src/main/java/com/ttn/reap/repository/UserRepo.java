package com.ttn.reap.repository;

import com.ttn.reap.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User,Integer> {
    Optional<User> findByUsername(String userName);
    Optional<User> findUserByResetToken(String resetToken);
    List<User> findAll();

    Optional<User> findByUserId(int userId);
}
