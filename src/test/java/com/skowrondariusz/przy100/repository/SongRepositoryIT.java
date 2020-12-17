package com.skowrondariusz.przy100.repository;

import com.skowrondariusz.przy100.model.Question;
import com.skowrondariusz.przy100.model.Result;
import com.skowrondariusz.przy100.model.Song;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SongRepositoryIT {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SongRepository songRepository;


    @Test
    public void whenFindById_thenReturnExpectedResult() {

        Song testSong= new Song("TEST", "testLink");
        entityManager.persist(testSong);
        entityManager.flush();


        Song foundSong = songRepository.findById(testSong.getId());

        assertThat(foundSong.getId())
                .isEqualTo(testSong.getId());

    }

}
