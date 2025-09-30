package com.cognitiveabilitygame.dto;
public class GameDTO {
    private Long id;
    private String name;
    private String type;
    private String description;
    private Integer durationSeconds;
    private Integer maxLevel;
    
    // Constructors
    public GameDTO() {}
    
    public GameDTO(Long id, String name, String type, String description, Integer durationSeconds, Integer maxLevel) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.durationSeconds = durationSeconds;
        this.maxLevel = maxLevel;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Integer getDurationSeconds() { return durationSeconds; }
    public void setDurationSeconds(Integer durationSeconds) { this.durationSeconds = durationSeconds; }
    
    public Integer getMaxLevel() { return maxLevel; }
    public void setMaxLevel(Integer maxLevel) { this.maxLevel = maxLevel; }
}

