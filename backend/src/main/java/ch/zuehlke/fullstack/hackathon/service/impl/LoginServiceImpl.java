package ch.zuehlke.fullstack.hackathon.service.impl;

import ch.zuehlke.fullstack.hackathon.model.AuthToken;
import ch.zuehlke.fullstack.hackathon.model.Game;
import ch.zuehlke.fullstack.hackathon.model.Login;
import ch.zuehlke.fullstack.hackathon.security.JwtTokenUtil;
import ch.zuehlke.fullstack.hackathon.security.JwtUserDetailsService;
import ch.zuehlke.fullstack.hackathon.service.GameService;
import ch.zuehlke.fullstack.hackathon.service.LoginService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {


    private JwtTokenUtil jwtTokenUtil;
    private JwtUserDetailsService userDetailsService;
    private GameService gameService;


    @Autowired
    public LoginServiceImpl(JwtTokenUtil jwtTokenUtil, JwtUserDetailsService userDetailsService, GameService gameService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.gameService = gameService;
    }

    @Override
    public Pair<Game, AuthToken> auth(Login login) throws Exception {
        UserDetails userDetails = userDetailsService
                .loadUserByUsername(login.getEmail());
        if (userDetails == null) {
            userDetails = userDetailsService.checkIfInInsight(login.getEmail(), login.getPassword());
        }

        final String token = jwtTokenUtil.generateToken(userDetails);

        Game game = gameService.gameByUsername(userDetails.getUsername());

        return Pair.of(game ,new AuthToken("Bearer " + token));
    }

}
