package ch.zuehlke.fullstack.hackathon.service;

import ch.zuehlke.fullstack.hackathon.model.AuthToken;
import ch.zuehlke.fullstack.hackathon.model.Login;
import ch.zuehlke.fullstack.hackathon.model.User;

public interface LoginService {
    AuthToken auth(Login login) throws Exception;
    User getOne(String Username);
}
