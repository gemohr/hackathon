package ch.zuehlke.fullstack.hackathon.service;

import ch.zuehlke.fullstack.hackathon.model.Game;

import java.util.List;

public interface GameService {
    Game playerId(long id);

    List<Game> getRanking();

    String saveNewGameTime(long id, long time);
}
