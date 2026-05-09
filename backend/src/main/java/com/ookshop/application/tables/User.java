package com.ookshop.application.tables;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "USER")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String fullName;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    @Size(max = 100)
    private String login;

    @Column(unique = true)
    @Size(max = 100)
    private String password;

    @Column
    private LocalDateTime creationDate;

    @OneToOne(mappedBy = "user", cascade = CascadeType.PERSIST)
    private UserAccount userAccount;

    @OneToOne(mappedBy = "user", cascade = CascadeType.PERSIST)
    private CompanyRole companyRole;
}
