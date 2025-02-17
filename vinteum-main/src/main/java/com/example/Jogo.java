package com.example;

public class Jogo {

    protected Monte monte = new Monte();
    protected Jogador jogador = new Jogador();
    protected Computador computador = new Computador();

    public Jogo() {
        monte.embaralhar();
    }

    public Carta distribuiCartaParaJogador(Jogador jogador){
        if (jogador.parou()){
            return null;
        }

        var carta = monte.virar();
        jogador.receberCarta(carta);
        return carta;
    }

    public String resultado(){
        if (jogadorEstourou() && computadorEstourou() || empataram()){
            return "Empatou";
        }
        if (computadorEstourou() || !jogadorEstourou() && jogadorGanhou() ){
            return "Você ganhou";
        }

        return "Você perdeu";
    }

    public boolean acabou(){
        return jogadorEstourou() || computadorEstourou() || osDoisPararam();
    }

    private boolean jogadorGanhou(){
        return jogador.getPontos() > computador.getPontos();
    }

    private boolean empataram(){
        return jogador.getPontos() == computador.getPontos();
    }

    private boolean jogadorEstourou(){
        return jogador.getPontos() > 21;
    }

    private boolean computadorEstourou(){
        return computador.getPontos() > 21;
    }

    private boolean osDoisPararam(){
        return jogador.parou() && computador.parou();
    }
}
