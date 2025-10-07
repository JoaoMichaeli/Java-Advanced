package br.com.fiap.brainup.controller;

import br.com.fiap.brainup.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final ApiService apiService;

    public record RoomRequest(String playerName) {}
    public record LeaveRoomRequest(UUID playerId) {}
    public record RoomResponse(String players, UUID playerId) {}

    @PostMapping("/start")
    public RoomResponse startRoom(@RequestBody RoomRequest request) {
        return apiService.createRoom(request.playerName());
    }

    @GetMapping("/stream/admin")
    public SseEmitter streamAdmin() {
        return apiService.sseEmitter();
    }

    @PostMapping("/exit")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void exitRoom(@RequestBody LeaveRoomRequest request) {
        apiService.leaveRoom(request.playerId());
    }

}
