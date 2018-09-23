package ramos.jefferson.boot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ramos.jefferson.boot.dto.AthleteDTO;
import ramos.jefferson.boot.dto.SportDTO;
import ramos.jefferson.boot.entity.Athlete;
import ramos.jefferson.boot.entity.Sport;
import ramos.jefferson.boot.exception.BadRequestException;
import ramos.jefferson.boot.exception.ResourceNotFounException;
import ramos.jefferson.boot.repository.AthleteRepository;
import ramos.jefferson.boot.util.GeneralFunctions;

@Service
public class AthleteServiceImpl implements AthleteService {
    
    @Autowired
    private AthleteSportService athleteSportService;

    @Autowired
    private GeneralFunctions generalFunctions;

    @Autowired
    private SportService sportService;

    @Autowired
    private AthleteRepository repository;

    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public AthleteDTO create(AthleteDTO athleteDTO) throws ResourceNotFounException, BadRequestException {
        Athlete athlete = createAthlete(athleteDTO);
        athlete = repository.saveAndFlush(athlete);
        manageAthleteSport(athlete, athleteDTO.getSports());
        return createAthleteDTO(athlete);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public AthleteDTO update(AthleteDTO athleteDTO, long id) throws ResourceNotFounException, BadRequestException {
        Athlete athlete = createAthlete(athleteDTO);
        Athlete entity = getAthlete(id);
        if (!athlete.equals(entity)) {
            throw new BadRequestException("Must be the same object");
        }
        manageAthleteSport(athlete, athleteDTO.getSports());
        return createAthleteDTO(repository.saveAndFlush(athlete));
    }
    
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public void delete(long id) throws ResourceNotFounException {
        Athlete athlete = getAthlete(id);
        athleteSportService.delete(athlete);
        repository.delete(athlete);
    }

    @Override
    public Athlete getOne(long id) throws ResourceNotFounException {
        return getAthlete(id);
    }

    @Override
    public AthleteDTO findOne(long id) throws ResourceNotFounException {
        return createAthleteDTO(getAthlete(id));
    }

    @Override
    public List<AthleteDTO> findAll(Map<String, String> parameters) throws ResourceNotFounException {
        List<Athlete> athletes;
        if (generalFunctions.verifyParameters(parameters)) {
            athletes = repository.findAll(generalFunctions.createPageable(parameters)).getContent();
        } else {
            athletes = repository.findAll();
        }
        return createListAthleteDTO(athletes);
    }

    @Override
    public List<AthleteDTO> findBySport(long sportId, Map<String, String> parameters) throws ResourceNotFounException {
        Sport sport = sportService.getOne(sportId);
        List<AthleteDTO> athleteDTOs;
        if (generalFunctions.verifyParameters(parameters)) {
            athleteDTOs = repository.findAllBySport(sport, generalFunctions.createPageable(parameters));
        } else {
            athleteDTOs = repository.findAllBySport(sport);
        }
        athleteDTOs.forEach((athleteDTO) -> {
            try {
                athleteDTO.setSports(createListSports(athleteDTO.getId()));
            } catch (ResourceNotFounException ex) {
                throw new RuntimeException(ex);
            }
        });
        return athleteDTOs;
    }

    private Athlete getAthlete(long id) throws ResourceNotFounException {
        Athlete athlete = repository.findById(id).orElse(null);
        if (athlete == null) {
            throw new ResourceNotFounException("Athlete not found");
        }
        return athlete;
    }

    private Athlete createAthlete(AthleteDTO athleteDTO) {
        if (athleteDTO == null) {
            return null;
        }
        return new Athlete(athleteDTO.getId(), athleteDTO.getFirstName(), athleteDTO.getLastName(), athleteDTO.getNickname());
    }

    private AthleteDTO createAthleteDTO(Athlete athlete) throws ResourceNotFounException {
        if (athlete == null) {
            return null;
        }
        AthleteDTO athleteDTO = new AthleteDTO(athlete.getId(), athlete.getFirstName(), athlete.getLastName(), athlete.getNickname());
        athleteDTO.setSports(createListSports(athleteDTO.getId()));
        return athleteDTO;
    }

    private List<Long> createListSports(long athleteId) throws ResourceNotFounException {
        List<Long> sports = new ArrayList<>();
        List<SportDTO> sportDTOs = sportService.findByAthlete(athleteId, null);
        sportDTOs.forEach((sportDTO) -> {
            sports.add(sportDTO.getId());
        });
        return sports;
    }

    private List<AthleteDTO> createListAthleteDTO(List<Athlete> athletes) throws ResourceNotFounException {
        List<AthleteDTO> athleteDTOs = new ArrayList<>();
        athletes.forEach((athlete) -> {
            try {
                athleteDTOs.add(createAthleteDTO(athlete));
            } catch (ResourceNotFounException ex) {
                throw new RuntimeException(ex);
            }
        });
        return athleteDTOs;
    }
    
    private void manageAthleteSport(Athlete athlete, List<Long> sportIds) {
        athleteSportService.delete(athlete);
        List<Sport> sports = new ArrayList<>();
        sportIds.forEach((id) -> {
            try{
                sports.add(sportService.getOne(id));
            }catch(ResourceNotFounException ex) {
                
            }
        });
        sports.forEach((sport) -> { athleteSportService.save(athlete, sport); });
    }

}
