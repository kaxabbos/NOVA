package com.nova.repo;

import com.nova.models.GameComments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepoGameComments extends JpaRepository<GameComments, Long> {
    List<GameComments> findAllByGameid(long id);
}
