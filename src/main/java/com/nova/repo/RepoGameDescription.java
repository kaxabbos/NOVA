package com.nova.repo;

import com.nova.models.GameDescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoGameDescription extends JpaRepository<GameDescription, Long> {
    GameDescription findByGameid(long id);
}
