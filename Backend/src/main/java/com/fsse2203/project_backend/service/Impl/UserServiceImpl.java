package com.fsse2203.project_backend.service.Impl;

import com.fsse2203.project_backend.data.user.CreateUserData;
import com.fsse2203.project_backend.data.user.UserDetailsData;
import com.fsse2203.project_backend.data.user.entity.UserEntity;
import com.fsse2203.project_backend.repository.UserRepository;
import com.fsse2203.project_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserDetailsData getUserDetailsByFirebaseUid(String firebaseUid) {
        if (userRepository.findByFirebaseUid(firebaseUid) == null){
            return null;
        }
        return new UserDetailsData(getUserEntityByFirebaseUid(firebaseUid));
    }

    public UserEntity getUserEntityByFirebaseUid(String firebaseUid){
        return userRepository.findByFirebaseUid(firebaseUid);
    }

    @Override
    public UserDetailsData CreateUserEntity(CreateUserData data) {
        UserEntity entity = new UserEntity(data);
        entity = userRepository.save(entity);
        return new UserDetailsData(entity);
    }

    public UserEntity getUserEntity(String firebaseUid){
      UserEntity userEntity = userRepository.findByFirebaseUid(firebaseUid);
      return userEntity;
    }
}
