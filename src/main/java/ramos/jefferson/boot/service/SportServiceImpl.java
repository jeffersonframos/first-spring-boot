package ramos.jefferson.boot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ramos.jefferson.boot.dto.SportDTO;
import ramos.jefferson.boot.entity.Athlete;
import ramos.jefferson.boot.entity.Sport;
import ramos.jefferson.boot.exception.BadRequestException;
import ramos.jefferson.boot.exception.ResourceNotFounException;
import ramos.jefferson.boot.repository.SportRepository;
import ramos.jefferson.boot.util.GeneralFunctions;

@Service
public class SportServiceImpl implements SportService {
    
    @Autowired
    private AthleteSportService athleteSportService;

    @Autowired
    private GeneralFunctions generalFunctions;

    @Autowired
    private AthleteService athleteService;

    @Autowired
    private SportRepository repository;

    @Override
    public SportDTO create(SportDTO sportDTO) throws BadRequestException {
        Sport sport = createSport(sportDTO);
        verify(sport);
        return createSportDTO(repository.saveAndFlush(sport));
    }

    @Override
    public SportDTO update(SportDTO sportDTO, long id) throws BadRequestException, ResourceNotFounException {
        Sport sport = createSport(sportDTO);
        Sport entity = getSport(id);
        if (!sport.equals(entity)) {
            throw new BadRequestException("Must be the same object");
        }
        return createSportDTO(repository.saveAndFlush(sport));
    }
    
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void delete(long id) throws ResourceNotFounException {
        Sport sport = getSport(id);
        athleteSportService.delete(sport);
        repository.delete(sport);
    }

    @Override
    public Sport getOne(long id) throws ResourceNotFounException {
        return getSport(id);
    }

    @Override
    public SportDTO findOne(long id) throws ResourceNotFounException {
        return createSportDTO(getSport(id));
    }

    @Override
    public Page<SportDTO> findAll(Map<String, String> parameters) {
        List<SportDTO> sportDTOs;
        if (generalFunctions.verifyParameters(parameters)) {
            sportDTOs = createListSportDTO(repository.findAll(generalFunctions.createPageable(parameters)).getContent());
        } else {
            sportDTOs = createListSportDTO(repository.findAll());
        }
        return new PageImpl<>(sportDTOs);
    }

    @Override
    public Page<SportDTO> findByAthlete(long athleteId, Map<String, String> parameters) throws ResourceNotFounException {
        Athlete athlete = athleteService.getOne(athleteId);
        List<SportDTO> sportDTOs;
        if (generalFunctions.verifyParameters(parameters)) {
            sportDTOs = repository.findByAthlete(athlete, generalFunctions.createPageable(parameters));
        } else {
            sportDTOs = repository.findByAthlete(athlete);
        }
        return new PageImpl<>(sportDTOs);
    }

    private SportDTO createSportDTO(Sport sport) {
        if (sport == null) {
            return null;
        }
        return new SportDTO(sport.getId(), sport.getName());
    }

    private Sport createSport(SportDTO sportDTO) {
        if (sportDTO == null) {
            return null;
        }
        return new Sport(sportDTO.getId(), sportDTO.getName());
    }

    private List<SportDTO> createListSportDTO(List<Sport> sports) {
        List<SportDTO> sportDTOs = new ArrayList<>();
        sports.forEach((sport) -> {
            sportDTOs.add(createSportDTO(sport));
        });
        return sportDTOs;
    }

    private Sport getSport(long id) throws ResourceNotFounException {
        Sport sport = repository.findById(id).orElse(null);
        if (sport == null) {
            throw new ResourceNotFounException("Sport not found");
        }
        return sport;
    }

    private void verify(Sport sport) throws BadRequestException {
        if (StringUtils.isEmpty(sport.getName())) {
            throw new BadRequestException("The sport name must not be empty");
        }
    }

}
