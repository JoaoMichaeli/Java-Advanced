package br.com.fiap.navi;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.stereotype.Service;

@Service
public class NaviService {

    private final ChatClient chatClient;

    public NaviService(ChatClient.Builder buider) {
        this.chatClient = buider.build();
    }

    public String translate(String text, String style) {
        var systemMessage = new SystemMessage("""
            Você é um tradutor criativo e adaptável chamado Navi.
            Sua tarefa é reescrever textos mantendo o mesmo significado,
            mas aplicando o estilo indicado pelo usuário.
            Responda APENAS com o texto traduzido, sem explicações adicionais.
        """);

        var userMessage = new UserMessage(String.format("""
            Traduza o seguinte texto para o estilo "%s":
            %s
        """, style, text));

        var response = chatClient
            .prompt()
            .messages(systemMessage, userMessage)
            .call()
            .content();

        return response != null && !response.isBlank() ? response : "Desculpe, não consegui traduzir o texto." ;
    }
}
