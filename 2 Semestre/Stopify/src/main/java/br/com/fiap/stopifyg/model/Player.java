package br.com.fiap.stopifyg.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Player {
    private UUID id = UUID.randomUUID();
    private String name;

    public Player(String playerName) {
        this.name = playerName;
    }
}
