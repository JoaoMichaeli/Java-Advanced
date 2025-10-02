package br.com.fiap.stopifyg.service;

import br.com.fiap.stopifyg.controller.ApiController;
import br.com.fiap.stopifyg.model.Player;
import br.com.fiap.stopifyg.model.Room;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class ApiService {

    private List<Room> rooms = new ArrayList<>();
    private Map<String, List<SseEmitter>> emitters = new ConcurrentHashMap<>(); //thread-safe

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

    private void sendEventToRoom(String code, String eventName, Object data) {
        var roomEmitters = emitters.get(code);
        if (roomEmitters == null) return;

        var deadEmitters = new ArrayList<SseEmitter>();
        for (var emitter : roomEmitters) {
            try {
                emitter.send(SseEmitter.event()
                        .name(eventName)
                        .data(data));
            } catch (Exception e) {
                deadEmitters.add(emitter);
            }
        }
        roomEmitters.removeAll(deadEmitters);

    }

    public SseEmitter createRoomStream(String code) {
        var emitter = new SseEmitter(0L);
        emitters.computeIfAbsent(code, k -> new CopyOnWriteArrayList<>()).add(emitter);
        return emitter;
    }

    public void leaveRoom(String code, UUID playerId) {
        var room = getRoomByCode(code);
        var player = room.getPlayers().stream()
                .filter(p -> p.getId().equals(playerId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found"));
        room.removePlayer(player);
        sendEventToRoom(code, "player.left", player);
    }
}
