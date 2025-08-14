package br.com.fiap.ovnis.ovnis;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OvnisService {

    private final  OvnisRepository repository;

    public OvnisService(OvnisRepository repository) {
        this.repository = repository;
    }

    public List<Ovnis> getAllSightings() {
        return repository.findAll();
    }

    public Ovnis save(Ovnis ovnis) {
        return repository.save(ovnis);
    }
}
