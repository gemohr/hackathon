package ch.zuehlke.fullstack.hackathon.service;

import ch.zuehlke.fullstack.hackathon.model.AuthToken;
import ch.zuehlke.fullstack.hackathon.model.Game;
import ch.zuehlke.fullstack.hackathon.model.Login;
import org.apache.commons.lang3.tuple.Pair;

public interface LoginService {
    Pair<Game, AuthToken> auth(Login login) throws Exception;
}
