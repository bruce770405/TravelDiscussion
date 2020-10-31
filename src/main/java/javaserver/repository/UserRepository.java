package javaserver.repository;

import javaserver.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    //	@Query(value = "select name,author,price from login b where b.name like %:name%")
    Optional<UserEntity> findByUsernameOrNickName(String username, String nickname);

    UserEntity findByNickname(String nickname);
}
