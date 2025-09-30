package com.cognitiveabilitygame.service;


// GameService.java

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognitiveabilitygame.dto.GameDTO;
import com.cognitiveabilitygame.entity.Game;
import com.cognitiveabilitygame.repository.GameRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameService {
    
    @Autowired
    private GameRepository gameRepository;
    
    public List<GameDTO> getAllActiveGames() {
        return gameRepository.findByActiveTrue().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<GameDTO> getRandomGames() {
        return gameRepository.findRandomActiveGames().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public Optional<Game> getGameById(Long id) {
        return gameRepository.findById(id);
    }
    
    public Game getGameByType(String type) {
        return gameRepository.findByType(type);
    }
    
    private GameDTO convertToDTO(Game game) {
        return new GameDTO(
                game.getId(),
                game.getName(),
                game.getType(),
                game.getDescription(),
                game.getDurationSeconds(),
                game.getMaxLevel()
        );
    }
}
