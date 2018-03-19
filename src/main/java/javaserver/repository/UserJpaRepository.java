package javaserver.repository;

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
//	List<Login> findByUsernameLike(String username);
//	Login findById(long id);
	Login findByUsername(String username);
	Login findByNickname(String nickname);
	//對應param id
//	@Query("from AccountInfo a where a.accountId = :id") 
//    Login findByAccountId(@Param("id")Long accountId); 

}
