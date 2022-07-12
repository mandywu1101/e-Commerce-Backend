package com.fsse2203.project_backend.data.user.entity;

import com.fsse2203.project_backend.data.user.CreateUserData;
import com.fsse2203.project_backend.data.user.UserDetailsData;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userID;

    @Column(name = "firebase_uid")
    private String firebaseUid;

    @Column(name = "email")
    private String email;

    public UserEntity() {
    }

    public UserEntity(CreateUserData data) {
       this.firebaseUid = data.getFirebaseUid();
       this.email = data.getEmail();
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getFirebaseUserId() {
        return firebaseUid;
    }

    public void setFirebaseUserId(String firebaseUid) {
        this.firebaseUid = firebaseUid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
