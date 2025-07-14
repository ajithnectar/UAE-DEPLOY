package com.nectar.userDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nectar.userDemo.beans.UserEntity;
import java.util.List;
@Repository
public interface UserDemoRepository extends JpaRepository<UserEntity, Long>{

	boolean existsByName(String name);
	List<UserEntity> findByEmailEndingWith(String string);
}
