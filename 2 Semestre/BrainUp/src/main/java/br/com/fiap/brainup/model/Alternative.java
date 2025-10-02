package br.com.fiap.brainup.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Alternative {
    private UUID id =  UUID.randomUUID();

    private String text;

    private Boolean correct;
}
