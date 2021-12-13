package com.nova.repo;

import com.nova.models.GameComments;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepoGameComments extends CrudRepository<GameComments, Long> {
    List<GameComments> findAllByGameid(long id);
}
