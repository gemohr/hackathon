package ch.zuehlke.fullstack.hackathon.service;

import ch.zuehlke.fullstack.hackathon.model.AuthToken;
import ch.zuehlke.fullstack.hackathon.model.Login;
import ch.zuehlke.fullstack.hackathon.model.User;
import ch.zuehlke.fullstack.hackathon.repository.GameRepository;
import ch.zuehlke.fullstack.hackathon.repository.UserRepository;
import ch.zuehlke.fullstack.hackathon.security.JwtTokenUtil;
import ch.zuehlke.fullstack.hackathon.security.JwtUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoginServiceImpl implements LoginService {



    private final UserRepository userRepository;

    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private JwtUserDetailsService userDetailsService;


    @Autowired
    public LoginServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, JwtUserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public AuthToken auth(Login login) throws Exception {
        UserDetails userDetails = userDetailsService
                .loadUserByUsername(login.getEmail());
        if(userDetails == null) {
            userDetails = userDetailsService.checkIfInInsight(login.getEmail(), login.getPassword());
        }

        final String token = jwtTokenUtil.generateToken(userDetails);

        return new AuthToken("Bearer "+token);
    }


    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @Override
    public User getOne(String username) {
        return userRepository.findByUsername(username);
    }


}
