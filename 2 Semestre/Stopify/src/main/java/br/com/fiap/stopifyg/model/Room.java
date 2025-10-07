package br.com.fiap.stopifyg.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
public class Room {
    private String code;
    private Set<Player> players = new HashSet<>();

    public Room(Player player) {
        this.code = generateRoomCode();
        players.add(player);
    }

    private String generateRoomCode() {
        return UUID.randomUUID().toString().substring(0, 4).toUpperCase();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }
}
