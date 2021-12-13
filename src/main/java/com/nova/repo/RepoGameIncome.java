package com.nova.repo;

import com.nova.models.GameIncome;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepoGameIncome extends CrudRepository<GameIncome, Long> {
    GameIncome findByGameid(long id);
    GameIncome findByUserid(long id);
    List<GameIncome> findAllByUserid(long id);
}
