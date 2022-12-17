package com.example.s3.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author lkadai0801
 * @since 16/12/2022
 */

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "image")
    private String image;
}
