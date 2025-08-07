package br.com.fiap.SuperPoderes.powers;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PowerService {

    private final PowerRepository powerRepository;


    public PowerService(PowerRepository powerRepository) {
        this.powerRepository = powerRepository;
    }

    public List<Power> getAllPowers(){
        return powerRepository.findAll();
    }

    public List<Power> getForOrderOfUselessness(){
        return powerRepository.findAllByOrderByNivelInutilidadeDesc();
    }
}
