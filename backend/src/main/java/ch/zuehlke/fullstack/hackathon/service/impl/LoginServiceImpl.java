package ch.zuehlke.fullstack.hackathon.service.impl;

import ch.zuehlke.fullstack.hackathon.model.AuthToken;
import ch.zuehlke.fullstack.hackathon.model.Login;
import ch.zuehlke.fullstack.hackathon.security.JwtTokenUtil;
import ch.zuehlke.fullstack.hackathon.security.JwtUserDetailsService;
import ch.zuehlke.fullstack.hackathon.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {


    private JwtTokenUtil jwtTokenUtil;
    private JwtUserDetailsService userDetailsService;


    @Autowired
    public LoginServiceImpl(JwtTokenUtil jwtTokenUtil, JwtUserDetailsService userDetailsService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public AuthToken auth(Login login) throws Exception {
        UserDetails userDetails = userDetailsService
                .loadUserByUsername(login.getEmail());
        if (userDetails == null) {
            userDetails = userDetailsService.checkIfInInsight(login.getEmail(), login.getPassword());
        }

        final String token = jwtTokenUtil.generateToken(userDetails);

        return new AuthToken("Bearer " + token);
    }

}
