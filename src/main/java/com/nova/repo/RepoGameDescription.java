package com.nova.repo;

import com.nova.models.GameDescription;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepoGameDescription extends CrudRepository<GameDescription, Long> {
    GameDescription findByGameid(long id);
}
