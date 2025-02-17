package com.example;

import java.util.ArrayList;
import java.util.List;

public class Monte {

    private List<Carta> cartas = new ArrayList<>();

    public Monte(){
        for(int numero = 1; numero <= 13; numero++){
            cartas.add (new Carta(numero, Naipe.Hearts));
            cartas.add (new Carta(numero, Naipe.Clubs));
            cartas.add (new Carta(numero, Naipe.Diamonds));
            cartas.add (new Carta(numero, Naipe.Spader));
        }
    }
}
