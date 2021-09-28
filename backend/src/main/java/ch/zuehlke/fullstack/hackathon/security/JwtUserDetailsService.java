package ch.zuehlke.fullstack.hackathon.security;

import java.util.ArrayList;

import ch.zuehlke.fullstack.hackathon.model.Game;
import ch.zuehlke.fullstack.hackathon.repository.GameRepository;
import ch.zuehlke.fullstack.hackathon.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GameRepository gameRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        ch.zuehlke.fullstack.hackathon.model.User user = userRepository.findByUsername(username);
        if ( user != null ) {
            return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
        }
        return null;
    }


    public UserDetails checkIfInInsight(String username, String password) throws Exception {
            ResponseEntity<String> response = getInformationsFromInsight(username);
            if(response.getStatusCode() == HttpStatus.OK) {
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
                gameRepository.save(game);

                return new User(newUser.getUsername(), newUser.getPassword(), new ArrayList<>());
            } else {
                throw new UsernameNotFoundException("User not found with username: " + username);
            }
    }

    private final String uri = "https://insight.zuehlke.com/api/v1/employees?name=";

    private ResponseEntity<String> getInformationsFromInsight(String username) throws Exception {

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>(generateHeader());
        ResponseEntity<String> response = restTemplate.exchange(uri+username, HttpMethod.GET, request, String.class);
        if(response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            throw new Exception("INVALID_CREDENTIALS");
        } else {
            return response;
        }
    }

    private HttpHeaders generateHeader(){
        String plainCreds =  "georg.mohr@zuehlke.com:Hid93GRMBOS!";
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        return headers;
    }
}
