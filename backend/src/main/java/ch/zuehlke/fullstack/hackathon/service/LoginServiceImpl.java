package ch.zuehlke.fullstack.hackathon.service;

import ch.zuehlke.fullstack.hackathon.model.Game;
import ch.zuehlke.fullstack.hackathon.model.Login;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@Service
public class LoginServiceImpl implements LoginService {


    final String uri = "http://insight.zuehlke.com/api/v1/user";

    @Override
    public void getInformationFrom(Login login) {
        RestTemplate restTemplate = new RestTemplate();
        String plainCreds = login.getEmail()+":"+login.getPassword();
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);

        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, request, String.class);
        System.out.println(response);
    }
}
