package com.example.s3.repository;

import com.example.s3.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lkadai0801
 * @since 16/12/2022
 */

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
