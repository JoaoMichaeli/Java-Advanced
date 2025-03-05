package br.com.fiap.gyma.model;

import java.util.Random;

public class Exercises {
    private Long id;
    private String name;
    private String icon;
    
    public Exercises(Long id, String name, String icon) {
        this.id = Math.abs(new Random().nextLong());
        this.name = name;
        this.icon = icon;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }
}
