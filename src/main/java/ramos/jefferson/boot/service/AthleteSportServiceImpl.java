package ramos.jefferson.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ramos.jefferson.boot.entity.Athlete;
import ramos.jefferson.boot.entity.AthleteSport;
import ramos.jefferson.boot.entity.Sport;
import ramos.jefferson.boot.entity.key.AthleteSportPK;
import ramos.jefferson.boot.repository.AthleteSportRepository;

@Service
class AthleteSportServiceImpl implements AthleteSportService {

    @Autowired
    private AthleteSportRepository repository;

    @Override
    public AthleteSport save(Athlete athlete, Sport sport) {
        return repository.saveAndFlush(createAthleteSport(athlete, sport));
    }

    @Override
    public void delete(Athlete athlete, Sport sport) {
        repository.delete(createAthleteSport(athlete, sport));
    }

    @Override
    public void delete(Athlete athlete) {
        repository.delete(athlete);
    }

    @Override
    public void delete(Sport sport) {
        repository.delete(sport);
    }

    private AthleteSport createAthleteSport(Athlete athlete, Sport sport) {
        return new AthleteSport(createAthleteSportPK(athlete, sport));
    }

    private AthleteSportPK createAthleteSportPK(Athlete athlete, Sport sport) {
        return new AthleteSportPK(athlete, sport);
    }

}
