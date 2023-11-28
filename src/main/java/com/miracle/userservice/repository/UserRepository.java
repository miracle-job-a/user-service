package com.miracle.userservice.repository;

import com.miracle.userservice.dto.response.UserBaseInfoResponseDto;
import com.miracle.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAndPassword(String email, int password);

    boolean existsByEmail(String email);

    @Query("""
           SELECT new com.miracle.userservice.dto.response.UserBaseInfoResponseDto(u.email, u.name, u.phone, u.birth, u.address)
           FROM User u
           WHERE u.id = :id
           """)
    UserBaseInfoResponseDto findUserBaseInfoResponseDtoById(@Param("id") Long id);
}
