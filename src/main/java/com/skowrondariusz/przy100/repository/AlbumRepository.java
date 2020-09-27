package com.skowrondariusz.przy100.repository;

import com.skowrondariusz.przy100.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Question, Long> {

}
