package ch.zuehlke.fullstack.hackathon.service;

import ch.zuehlke.fullstack.hackathon.model.Game;
import ch.zuehlke.fullstack.hackathon.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {


    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game playerId(long id){
        Optional<Game> game = gameRepository.findById(id);
        return game.orElse(null);
    }

    public List<Game> getRanking() {
        return gameRepository.findAll(Sort.by(Sort.Direction.ASC, "pos"));
    }

    public String saveNewGameTime(long id, long time) {
        Game game = gameRepository.findById(id).orElseThrow(() -> new InternalError("No Game Found to update you are a cheater!!!"));
        if(game.getTime() > time || game.getTime() == 0) {
            game.setTime(time);
            game.setDate(new Date());
            gameRepository.save(game);
            return "better";
        }
        return "worse than before";
    }

}
