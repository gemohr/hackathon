package ch.zuehlke.fullstack.hackathon.model;

import lombok.Data;

@Data
public class LoginResult {

    private AuthToken authToken;
    private Game game;

}
