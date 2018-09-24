package ramos.jefferson.boot.service;

import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import ramos.jefferson.boot.dto.AthleteDTO;
import ramos.jefferson.boot.entity.Athlete;
import ramos.jefferson.boot.exception.BadRequestException;
import ramos.jefferson.boot.exception.ResourceNotFounException;

public interface AthleteService {
    
    public AthleteDTO create(AthleteDTO athleteDTO) throws ResourceNotFounException, BadRequestException;
    
    public AthleteDTO update(AthleteDTO athleteDTO, long id) throws ResourceNotFounException, BadRequestException;
    
    public void delete(long id) throws ResourceNotFounException;
    
    public Athlete getOne(long id) throws ResourceNotFounException;
    
    public AthleteDTO findOne(long id) throws ResourceNotFounException;
    
    public Page<AthleteDTO> findAll(Map<String, String> parameters) throws ResourceNotFounException;
    
    public Page<AthleteDTO> findBySport(long sportId, Map<String, String> parameters) throws ResourceNotFounException;

}
