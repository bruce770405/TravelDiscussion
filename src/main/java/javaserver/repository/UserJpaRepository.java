package javaserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import javaserver.model.Login;

/*
 * 
 * 啟動你的應用程序和Spring數據（已被Boot，SQL或NoSQL自動配置）將自動制定一組具體的操作：
save(Login)
delete(Login)
find(Login)
find(Long)
findAll()
 */
public interface UserJpaRepository extends JpaRepository<Login, Long> {
	List<Login> findByUsernameLike(String username);
	Login findByUsername(String username);
}
