package com.retobbva.challenge.jpa;



import org.springframework.data.jpa.repository.JpaRepository;

import com.retobbva.challenge.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long>{


}
