package br.com.fiap.brainup.service;

import br.com.fiap.brainup.controller.ApiController;
import br.com.fiap.brainup.model.Player;
import br.com.fiap.brainup.repository.QuizRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class ApiService {

    private final QuizRepository quizRepository;
    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    public ApiService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public ApiController.RoomResponse createRoom(String playerName){
        var player = new Player(playerName);
        quizRepository.getQuiz().addPlayer(player);

        sendEventToAdmins("player.joined", player);

        return new ApiController.RoomResponse(
                player.getName(),
                player.getId()
        );
    }

    private void sendEventToAdmins(String eventName, Object data) {
        List<SseEmitter> deadEmitters = new ArrayList<>();
        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(SseEmitter.event()
                        .name(eventName)
                        .data(data));
            } catch (Exception e) {
                deadEmitters.add(emitter);
            }
        }
        emitters.removeAll(deadEmitters);
    }

    public SseEmitter sseEmitter() {
        var emitter = new SseEmitter(0L);
        emitters.add(emitter);

        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        emitter.onError((e) -> emitters.remove(emitter));

        return emitter;
    }

    public void leaveRoom(UUID playerId) {
        Player player = quizRepository.getQuiz()
                .getPlayers()
                .stream()
                .filter(p -> p.getId().equals(playerId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Jogador não encontrado"
                ));

        player.setActive(false);

        sendEventToAdmins("player.exited", player);
    }
}
