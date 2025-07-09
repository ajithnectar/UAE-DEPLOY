package com.nectar.userDemo.service;

import java.util.List;

import com.nectar.userDemo.beans.UserEntity;
import com.nectar.userDemo.dto.UserDTO;

public interface UserService {
	UserEntity createUser(UserDTO userDTO);

	List<UserEntity> getAllUsers();

	UserEntity updateUser(UserDTO userDTO);

	boolean deleteUser(Long id);

	UserEntity findUser(Long id);
}
