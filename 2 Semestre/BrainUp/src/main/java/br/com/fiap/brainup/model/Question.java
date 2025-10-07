package br.com.fiap.brainup.model;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Question {
    private UUID id =  UUID.randomUUID();

    private String text;

    private List<Alternative> alternatives;
}
