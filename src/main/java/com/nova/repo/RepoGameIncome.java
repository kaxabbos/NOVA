package com.nova.repo;

import com.nova.models.GameIncome;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepoGameIncome extends JpaRepository<GameIncome, Long> {
    GameIncome findByGameid(long id);

    GameIncome findByUserid(long id);

    List<GameIncome> findAllByUserid(long id);
}
