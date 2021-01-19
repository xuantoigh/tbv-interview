package com.interview.tbv.repository;

import com.interview.tbv.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by JM on 08/20/2019
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByEmailOrUserNameOrPhoneNumber(String email, String userName, String phoneNumber);
}
