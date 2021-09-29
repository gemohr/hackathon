package ch.zuehlke.fullstack.hackathon.repository;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class InsightRepository {

    private final String BASE_URI = "https://insight.zuehlke.com/";
    private RestTemplate restTemplate;
    HttpEntity<String> requestEntity;

    @Value("${email}")
    private String serviceUserEmail;

    @Value("${password}")
    private String serviceUserPassword;


    public ResponseEntity<String> getUserInformationsFromInsight(String username) throws Exception {
        restTemplate = new RestTemplate();
        requestEntity = generateAuthHeader();
        String getUserUrl = BASE_URI + "api/v1/employees?name=" + username;
        ResponseEntity<String> response = restTemplate.exchange(getUserUrl, HttpMethod.GET, requestEntity, String.class);
        if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            throw new Exception("INVALID_CREDENTIALS");
        } else if (response.getStatusCode() == HttpStatus.OK) {
            return response;
        }
        throw new IllegalStateException("Status code was not 200");
    }

    public byte[] getUserPicture(long pictureId) {
        restTemplate = new RestTemplate();
        requestEntity = generateAuthHeader();
        String getPictureUrl = BASE_URI + "api/v1/pictures/" + pictureId + "?placeholder=labore aute consectetur";
        ResponseEntity<byte[]> response = this.restTemplate.exchange(getPictureUrl, HttpMethod.GET, requestEntity, byte[].class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        throw new IllegalStateException("Status code was not 200");
    }

    private HttpEntity<String> generateAuthHeader() {
        String plainCreds = serviceUserEmail + ":" + serviceUserPassword;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        return new HttpEntity<>(headers);
    }

}
