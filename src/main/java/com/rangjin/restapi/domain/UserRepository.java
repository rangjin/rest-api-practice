package com.rangjin.restapi.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUid(String email);

    Optional<User> findByUidAndProvider(String uid, String provider);

}
