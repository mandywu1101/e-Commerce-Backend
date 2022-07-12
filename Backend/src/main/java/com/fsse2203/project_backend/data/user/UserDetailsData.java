package com.fsse2203.project_backend.data.user;

import com.fsse2203.project_backend.data.user.entity.UserEntity;

public class UserDetailsData {
    private Integer userID;
    private String firebaseUserId;
    private String email;

    public UserDetailsData(UserEntity userEntity) {
        this.userID = userEntity.getUserID();
        this.firebaseUserId = userEntity.getFirebaseUserId();
        this.email = userEntity.getEmail();
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getFirebaseUserId() {
        return firebaseUserId;
    }

    public void setFirebaseUserId(String firebaseUserId) {
        this.firebaseUserId = firebaseUserId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
