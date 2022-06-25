package com.amanTech.registration.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.amanTech.registration.dto.UserDetail;

@Repository
public interface UserDetailRepo extends CrudRepository<UserDetail, Integer> {

	List<UserDetail> findByEmail(String email);

	List<UserDetail> findByMobNo(String mobNo);
	
	@Query(value = "SELECT * FROM user_detail WHERE (email = :username or mob_no= :username) and password= :password", nativeQuery = true)
    List<UserDetail> validateLogin(@Param("username") String username, @Param("password") String password);

}
