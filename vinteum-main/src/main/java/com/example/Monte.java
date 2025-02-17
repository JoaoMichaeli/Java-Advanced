package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Monte {

    private List<Carta> cartas = new ArrayList<>();

    public Monte(){
        // criar 52 cartas
        for(int numero = 1; numero <= 13; numero++){
            for(Naipe naipe : Naipe.values()){ //ForEach
                cartas.add (new Carta(numero, naipe));
            }
        }
    }

    public void embaralhar(){
        Collections.shuffle(cartas);
    }

    public Carta virar(){
        return cartas.remove(0);
    }
}
