package me.paultje52.fontysChallenge.guldenSchijf.repository;

import jakarta.transaction.Transactional;
import me.paultje52.fontysChallenge.guldenSchijf.model.UserModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserModel, Integer> {
    @Query(value = "SELECT `id`,`username`,`password`FROM user where username =?1", nativeQuery = true)
    Optional<UserModel> findByUsername(String email);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user (`username`,`password`) VALUES (?1,?2)", nativeQuery = true)
    void addUser(String userName,String hashedPassword);
}