package com.nova.repo;

import com.nova.models.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepoComments extends JpaRepository<Comments, Long> {
    List<Comments> findAllByGameid(long id);
}
