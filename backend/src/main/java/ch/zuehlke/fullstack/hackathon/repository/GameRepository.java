package ch.zuehlke.fullstack.hackathon.repository;

import ch.zuehlke.fullstack.hackathon.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    Optional<Game> findByUsername(String username);

}
