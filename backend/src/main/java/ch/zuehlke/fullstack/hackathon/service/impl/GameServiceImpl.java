package ch.zuehlke.fullstack.hackathon.service.impl;

import ch.zuehlke.fullstack.hackathon.model.Game;
import ch.zuehlke.fullstack.hackathon.repository.GameRepository;
import ch.zuehlke.fullstack.hackathon.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {


    private final GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game playerId(long id) {
        Optional<Game> game = gameRepository.findById(id);
        return game.orElse(null);
    }

    @Override
    public List<Game> getRanking() {
        List<Game> games = gameRepository.findAll(Sort.by(Sort.Direction.ASC, "time"));
        AtomicInteger index = new AtomicInteger();
        List<Game> ranking = games.stream()
                .map(game -> {
                    index.getAndIncrement();
                    game.setPos(index.get());
                    return game;
                }).collect(Collectors.toList());
        return ranking;
    }

    @Override
    public String saveNewGameTime(long id, long time) {
        Game game = gameRepository.findById(id).orElseThrow(() -> new InternalError("No Game Found to update you are a cheater!!!"));
        if (game.getTime() > time || game.getTime() == 0) {
            game.setTime(time);
            game.setDate(new Date());
            gameRepository.save(game);
            return "better";
        }
        return "worse than before";
    }

}
