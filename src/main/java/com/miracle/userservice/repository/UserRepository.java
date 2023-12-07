package com.miracle.userservice.repository;

import com.miracle.userservice.dto.response.UserJoinListResponseDto;
import com.miracle.userservice.dto.response.UserListResponseDto;
import com.miracle.userservice.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);

    @Query("""
           SELECT new com.miracle.userservice.dto.response.UserListResponseDto(u.id, u.email, u.name, u.address, u.createdAt)
           FROM User u
           ORDER BY u.createdAt DESC
           """)
    Page<UserListResponseDto> findAllUserList(Pageable pageable);

    @Query("""
           SELECT new com.miracle.userservice.dto.response.UserJoinListResponseDto(u.id, u.email, u.name, u.createdAt)
           FROM User u
           WHERE u.createdAt >= :startDate AND u.createdAt < :endDate
           ORDER BY u.createdAt DESC
           """)
    Page<UserJoinListResponseDto> findJoinList(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, Pageable pageable);
}
