package com.skowrondariusz.przy100.repository;

import com.skowrondariusz.przy100.model.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ResultRepositoryIT {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ResultRepository resultRepository;


    @Test
    public void whenLowestScore_thenReturnLastResult() {

        Result testResult1 = new Result(30d, 50, "test2", 1230d);
        Result testResult2 = new Result(30d, 10, "test2", 1235d);
        entityManager.persist(testResult1);
        entityManager.flush();
        entityManager.persist(testResult2);
        entityManager.flush();

        assertThat(resultRepository.lowestScore())
                .isEqualTo(testResult1.getTotalScore());
    }


    @Test
    public void returnResultWithLowestScore() {
        Result testResult1 = new Result(30d, 50, "test2", 1230d);
        Result testResult2 = new Result(30d, 10, "test2", 1235d);
        entityManager.persist(testResult1);
        entityManager.flush();
        entityManager.persist(testResult2);
        entityManager.flush();

        assertThat(resultRepository.findFirstByOrderByTotalScoreAsc())
                .isEqualTo(testResult1);

    }

    @Test
    public void whenCount_thenReturnTotalSizeOfRepo() {
        Result testResult1 = new Result(30d, 50, "test2", 1230d);
        Result testResult2 = new Result(30d, 10, "test2", 1235d);
        entityManager.persist(testResult1);
        entityManager.flush();
        entityManager.persist(testResult2);
        entityManager.flush();

        assertThat(resultRepository.count()).isEqualTo(2);
    }
}
