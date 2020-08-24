package com.app.cms.repository;

import com.app.cms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryMethods {
    boolean existsByLoginAndIdNot(String login, Long id);

    boolean existsByLogin(String login);

    char[] getPassById(Long id);

    @Modifying
    @Query("update User u set u.password = :password where u.id = :id")
    void updatePassword(@Param(value = "id") long id, @Param(value = "password") char[] password);

}
