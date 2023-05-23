package com.retobbva.challenge.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retobbva.challenge.model.RequestModel;

public interface RequestRepository extends JpaRepository<RequestModel, Long>{

}
