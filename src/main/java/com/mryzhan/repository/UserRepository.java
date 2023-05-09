package com.mryzhan.repository;

import com.mryzhan.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String username);

    @Query("SELECT u FROM User u WHERE u.role.description = 'Manager' ")
    List<User> findAllByRole_DescriptionIsManager();
    @Transactional
    void deleteByUserName(String username);

}
