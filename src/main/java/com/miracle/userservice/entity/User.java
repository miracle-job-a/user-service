package com.miracle.userservice.entity;

import com.miracle.userservice.dto.request.UserUpdateInfoRequestDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false)
    private int password;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 20)
    private String phone;

    @Column(nullable = false)
    private LocalDate birth;

    @Column(nullable = false)
    private String address;

    @ElementCollection
    @CollectionTable(
            name = "user_stack",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "stack_id", nullable = false)
    private final Set<Long> stackIdSet = new HashSet<>();

    @ElementCollection
    @CollectionTable(
            name = "scrap",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "post_id", nullable = false)
    private final Set<Long> scrapPostIdSet = new HashSet<>();

    @Builder
    public User(String email, int password, String name, String phone, LocalDate birth, String address) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.birth = birth;
        this.address = address;
    }

    public void update(UserUpdateInfoRequestDto dto) {
        this.password = dto.getPassword().hashCode();
        this.phone = dto.getPhone();
        this.address = dto.getAddress();
        stackIdSet.clear();
        stackIdSet.addAll(dto.getStackIdSet());
    }
}
