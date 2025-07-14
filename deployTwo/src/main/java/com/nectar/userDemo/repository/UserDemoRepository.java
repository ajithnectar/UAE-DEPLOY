package com.nectar.userDemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nectar.userDemo.beans.UserEntity;

@Repository
public interface UserDemoRepository extends JpaRepository<UserEntity, Long>{

	boolean existsByName(String name);
	
	List<UserEntity> findByEmailEndingWith(String string);

}
