package ch.zuehlke.fullstack.hackathon.controller;

import ch.zuehlke.fullstack.hackathon.model.AuthToken;
import ch.zuehlke.fullstack.hackathon.model.Game;
import ch.zuehlke.fullstack.hackathon.model.Login;
import ch.zuehlke.fullstack.hackathon.model.LoginResult;
import ch.zuehlke.fullstack.hackathon.service.LoginService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {


    LoginService loginService;


    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @ApiOperation(value = "Login",
            notes = "This is used for the controlling the login")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfull"),
            @ApiResponse(code = 500, message = "If something fails internally")})
    @PostMapping
    public ResponseEntity<?> login(@RequestBody Login login) throws Exception {

        Pair<Game, AuthToken> result = loginService.auth(login);
        LoginResult loginResult = new LoginResult();
        loginResult.setGame(result.getKey());
        loginResult.setAuthToken(result.getValue());
        return new ResponseEntity<>(loginResult, HttpStatus.OK);
    }

}
