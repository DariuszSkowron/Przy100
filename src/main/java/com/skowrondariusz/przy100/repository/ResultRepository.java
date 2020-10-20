package com.skowrondariusz.przy100.repository;

import com.skowrondariusz.przy100.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

}
