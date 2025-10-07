package com.fiap.stopify.service;

import com.fiap.stopify.controller.ApiController;
import com.fiap.stopify.model.Player;
import com.fiap.stopify.model.Room;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiService {
    private List<Room> rooms = new ArrayList<>();

    public ApiController.RoomResponse createRoom(String playerName) {
        var player = new Player(playerName);
        var room = new Room(player);
        rooms.add(room);
        return new ApiController.RoomResponse(
                room.getCode(),
                room.getPlayers(),
                player.getId()
        );
    }

    public Room getRoomByCode(String code) {
        return rooms.stream()
                .filter(room -> room.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found"));
    }

    public ApiController.RoomResponse joinRoom(String code, String playerName) {
        var room = getRoomByCode(code);
        var player = new Player(playerName);
        room.addPlayer(player);
        sendEventToRoom(code, "player.joined", player);
        return new ApiController.RoomResponse(
                room.getCode(),
                room.getPlayers(),
                player.getId()
        );
    }
}
