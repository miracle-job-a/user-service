package com.miracle.userservice.entity;

import com.miracle.userservice.converter.AsymmetricCypherConverter;
import com.miracle.userservice.converter.EmailConverter;
import com.miracle.userservice.converter.SymmetricCypherConverter;
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

    @Convert(converter = EmailConverter.class)
    @Column(nullable = false, unique = true, length = 350)
    private String email;

    @Convert(converter = AsymmetricCypherConverter.class)
    @Column(nullable = false, length = 128)
    private String password;

    @Column(nullable = false, length = 30)
    private String name;

    @Convert(converter = SymmetricCypherConverter.class)
    @Column(nullable = false, length = 350)
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
    public User(String email, String password, String name, String phone, LocalDate birth, String address) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.birth = birth;
        this.address = address;
    }

    public void update(UserUpdateInfoRequestDto dto) {
        this.password = dto.getPassword();
        this.phone = dto.getPhone();
        this.address = dto.getAddress();
        stackIdSet.clear();
        stackIdSet.addAll(dto.getStackIdSet());
    }
}
