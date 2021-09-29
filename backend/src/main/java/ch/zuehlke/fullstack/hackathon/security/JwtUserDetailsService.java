package ch.zuehlke.fullstack.hackathon.security;

import ch.zuehlke.fullstack.hackathon.model.Game;
import ch.zuehlke.fullstack.hackathon.repository.GameRepository;
import ch.zuehlke.fullstack.hackathon.repository.InsightRepository;
import ch.zuehlke.fullstack.hackathon.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {


    private UserRepository userRepository;
    private GameRepository gameRepository;
    private InsightRepository insightRepository;

    @Autowired
    public JwtUserDetailsService(UserRepository userRepository, GameRepository gameRepository, InsightRepository insightRepository) {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.insightRepository = insightRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        ch.zuehlke.fullstack.hackathon.model.User user = userRepository.findByUsername(username);
        if (user != null) {
            return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
        }
        return null;
    }


    public UserDetails checkIfInInsight(String username, String password) throws Exception {
        ResponseEntity<String> response = insightRepository.getUserInformationsFromInsight(username);
        if (response.getStatusCode() == HttpStatus.OK) {
            ch.zuehlke.fullstack.hackathon.model.User newUser = new ch.zuehlke.fullstack.hackathon.model.User();
            ObjectMapper mapper = new ObjectMapper();
            ArrayNode json = (ArrayNode) mapper.readTree(response.getBody());
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            newUser.setUsername(username);
            newUser.setPassword(passwordEncoder.encode(password));
            newUser = userRepository.save(newUser);

            Game game = new Game();
            game.setFullName(json.get(0).get("FirstName").asText() + " " + json.get(0).get("LastName").asText());
            game.setPictureId(json.get(0).get("PictureId").asLong());
            game.setPicture(this.insightRepository.getUserPicture(game.getPictureId()));
            game.setUsername(newUser.getUsername());
            gameRepository.save(game);

            return new User(newUser.getUsername(), newUser.getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
