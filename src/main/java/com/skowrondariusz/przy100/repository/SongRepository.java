package com.skowrondariusz.przy100.repository;

import com.skowrondariusz.przy100.model.Question;
import com.skowrondariusz.przy100.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    Song findById (long id);


}
