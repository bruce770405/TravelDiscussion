package javaserver.repository;

import javaserver.entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<LoginEntity, Long> {

    LoginEntity findByUsername(String username);

    //	@Query(value = "select name,author,price from login b where b.name like %:name%")
    LoginEntity findByUsernameOrNickName(String username, String nickname);

    LoginEntity findByNickname(String nickname);
}
