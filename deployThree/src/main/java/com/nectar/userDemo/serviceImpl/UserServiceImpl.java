package com.nectar.userDemo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nectar.userDemo.beans.UserEntity;
import com.nectar.userDemo.dto.UserDTO;
import com.nectar.userDemo.repository.UserDemoRepository;
import com.nectar.userDemo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserDemoRepository userDemoRepository;
    
    
    @Override
    public UserEntity createUser(UserDTO userDTO) {
    	UserEntity user = new UserEntity(userDTO.getName(), userDTO.getEmail(),userDTO.getMobile());
    	userDemoRepository.save(user);
        return user;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userDemoRepository.findByEmailEndingWith("@deployThree");
    }

    @Override
    public UserEntity updateUser(UserDTO userDTO) {
    	 Optional<UserEntity> existingUser = userDemoRepository.findById(userDTO.getId());
        if (existingUser.isPresent()) {
        	UserEntity userToUpdate = existingUser.get();
        	userToUpdate.setName(userDTO.getName());
        	userToUpdate.setEmail(userDTO.getEmail());
        	userToUpdate.setMobile(userDTO.getMobile());
        	return userDemoRepository.save(userToUpdate);
            
        }
        
        return null;
    }

    @Override
	public boolean deleteUser(Long id) {
		try {
			userDemoRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			LOGGER.error("error while deleting user with id {}", id);
			return false;
		}
	}

	@Override
	public UserEntity findUser(Long id) {
		UserEntity user = null;
		try {

			Optional<UserEntity> existingUser = userDemoRepository.findById(id);
			if (existingUser.isPresent()) {
				user = existingUser.get();
			}
		} catch (Exception e) {
			LOGGER.error("error while finding user with id {}", id);
		}
		return user;
	}
}
