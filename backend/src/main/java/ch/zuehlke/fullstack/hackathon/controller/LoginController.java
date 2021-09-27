package ch.zuehlke.fullstack.hackathon.controller;

import ch.zuehlke.fullstack.hackathon.model.Login;
import ch.zuehlke.fullstack.hackathon.service.LoginServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final LoginServiceImpl loginService;

    @Autowired
    public LoginController(LoginServiceImpl loginService) {
        this.loginService = loginService;
    }

    @ApiOperation(value = "Login",
            notes = "This is used for the controlling the login")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfull"),
            @ApiResponse(code = 500, message = "If something fails internally")})
    @PostMapping
    public ResponseEntity<?> login(@RequestBody Login login) {
        try {
             this.loginService.getInformationFrom(login);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(1, HttpStatus.OK);
    }
}
