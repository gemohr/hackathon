package ch.zuehlke.fullstack.hackathon.service;

import ch.zuehlke.fullstack.hackathon.model.Game;
import ch.zuehlke.fullstack.hackathon.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {


    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game playerId(long id){
        return gameRepository.findById(id).get();
    }

    public List<Game> getRanking() {
        return gameRepository.findAll(Sort.by(Sort.Direction.ASC, "pos"));
    }

    public void setNewUser() {

    }

}
