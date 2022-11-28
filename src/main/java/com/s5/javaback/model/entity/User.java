package com.s5.javaback.model.entity;

import com.s5.javaback.util.constants.DateFormatConstants;
import com.s5.javaback.util.enums.UserStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(name = "created_at")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = DateFormatConstants.DATE_TIME_FORMAT)
    private LocalDateTime createdAt;
    @JoinColumn(name="image_id")
    @OneToOne(cascade = CascadeType.REFRESH)
    private Image image;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @Column(name = "roles_id")
    private Set<Role> roles=new HashSet<>();


    @OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Turn> turnList = new ArrayList<>();
    public void addRole(Role role) {
        roles.add(role);
    }
    public void removeRole(final Role role) {
        roles.remove(role);
    }
    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }

        if (status == null) {
            status = UserStatus.ENABLED;
        }
    }


}
