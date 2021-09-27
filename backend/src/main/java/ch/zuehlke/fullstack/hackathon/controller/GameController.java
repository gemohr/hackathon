package ch.zuehlke.fullstack.hackathon.controller;

import ch.zuehlke.fullstack.hackathon.model.Game;
import ch.zuehlke.fullstack.hackathon.service.GameService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/game")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/player/{id}")
    public ResponseEntity<?> getPlayerById(@PathVariable("id") long id) {
        return new ResponseEntity<>(gameService.playerId(id), HttpStatus.OK);
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


}
