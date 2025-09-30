package com.cognitiveabilitygame.controller;

// GameController.java

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cognitiveabilitygame.dto.GameDTO;
import com.cognitiveabilitygame.service.GameService;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {
    
    @Autowired
    private GameService gameService;
    
    @GetMapping
    public ResponseEntity<List<GameDTO>> getAllActiveGames() {
        List<GameDTO> games = gameService.getAllActiveGames();
        return ResponseEntity.ok(games);
    }
    
    @GetMapping("/random")
    public ResponseEntity<List<GameDTO>> getRandomGames() {
        List<GameDTO> randomGames = gameService.getRandomGames();
        return ResponseEntity.ok(randomGames);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<GameDTO> getGameById(@PathVariable Long id) {
        return gameService.getGameById(id)
                .map(game -> ResponseEntity.ok(new GameDTO(
                        game.getId(),
                        game.getName(),
                        game.getType(),
                        game.getDescription(),
                        game.getDurationSeconds(),
                        game.getMaxLevel()
                )))
                .orElse(ResponseEntity.notFound().build());
    }
}

