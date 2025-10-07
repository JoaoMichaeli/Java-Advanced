package com.fiap.stopify.controller;

import com.fiap.stopify.model.Player;
import com.fiap.stopify.model.Room;
import com.fiap.stopify.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final ApiService apiService;

    public record RoomRequest(String playerName) {}
    public record LeaveRoomRequest(UUID playerId) {}
    public record RoomResponse(String code, Set<Player> players, UUID playerId) {}

    @PostMapping("room")
    public RoomResponse createRoom(@RequestBody RoomRequest request) {
        return apiService.createRoom(request.playerName());
    }

    @GetMapping("/room/{code}")
    public Room getRoom(@PathVariable String code) {
        return apiService.getRoomByCode(code);
    }

    @PostMapping("/room/{code}/join")
    public RoomResponse joinRoom(@PathVariable String code, @RequestBody RoomRequest request){
        return apiService.joinRoom(code, request.playerName());
    }

    @GetMapping("/stream/room/{code}")
    public SseEmitter streamRoom(@PathVariable String code) {
        return apiService.streamRoom(code);
    }

    @PostMapping("/room/{code}/leave")
    public void leaveRoom(@PathVariable String code, @RequestBody LeaveRoomRequest request){
        apiService.leaveRoom(code, request.playerId());
    }

}
