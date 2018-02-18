package javaserver.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import javaserver.model.Webdata;


/*
 * 
這個存儲庫是一個接口，將允許您執行涉及contact對象的各種操作。
它通過擴展Spring Data Commons中定義的PagingAndSortingRepository接口來獲得這些操作。
在運行時，Spring Data REST會自動創建這個接口的實現。
然後，它將使用@RepositoryRestResource註釋來指導Spring MVC創建RESTful端點/contact。
 * 
 * Spring Data JPA 查詢可以分成兩類：
名稱定義查詢：Spring Data JPA 存在的目的，透過 interface method name 的定義來表示查詢的內容。
自行實作查詢：為補足名稱定義查詢的不足，可以「自訂查詢語法」或者提供「真正的實作」。

 * 
 */
public interface DataJpaRepository extends JpaRepository<Webdata,Long> {
//	@Param("username")
	Webdata findByUsername(String username);
	List<Webdata> findByUsernameLike(String username);
	
}
