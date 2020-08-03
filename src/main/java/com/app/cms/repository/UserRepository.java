package com.app.cms.repository;

import com.app.cms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByLoginAndIdNot(String login, Long id);

    boolean existsByLogin(String login);

    char[] getPassById(Long id);
}
