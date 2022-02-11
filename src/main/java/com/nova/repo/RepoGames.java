package com.nova.repo;

import com.nova.models.Games;
import com.nova.models.enums.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepoGames extends JpaRepository<Games, Long> {
    List<Games> findAllByGenre(Genre genre);

    List<Games> findAllByYear(int year);

    List<Games> findAllByUserid(long userid);

    Games findByNameAndGenre(String name, Genre genre);

    Games findById(long id);
}
