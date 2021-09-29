package ch.zuehlke.fullstack.hackathon.controller;

import ch.zuehlke.fullstack.hackathon.model.Game;
import ch.zuehlke.fullstack.hackathon.service.GameService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/game")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @ApiOperation(value = "Ranking",
            notes = "Here you will see the ranking")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfull"),
            @ApiResponse(code = 500, message = "If something fails internally")})

    @GetMapping("/ranking")
    public ResponseEntity<List<Game>> getRanking() {

        List<Game> ranking = gameService.getRanking();

        return new ResponseEntity<>(ranking, HttpStatus.OK);

    }

    @ApiOperation(value = "Ranking",
            notes = "Here you will see the ranking")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfull"),
            @ApiResponse(code = 500, message = "If something fails internally")})

    @GetMapping("/byUser")

    public ResponseEntity<Game> getGameByUsername(@RequestParam(value="username", required = true) String username) {
        return new ResponseEntity<>(gameService.gameByUsername(username), HttpStatus.OK);
    }


}
