package ramos.jefferson.boot.service;

import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import ramos.jefferson.boot.dto.SportDTO;
import ramos.jefferson.boot.entity.Sport;
import ramos.jefferson.boot.exception.BadRequestException;
import ramos.jefferson.boot.exception.ResourceNotFounException;

public interface SportService {
    
    public SportDTO create(SportDTO sportDTO) throws BadRequestException;
    
    public SportDTO update(SportDTO sportDTO, long id) throws BadRequestException, ResourceNotFounException;
    
    public void delete(long id) throws ResourceNotFounException;
    
    public Sport getOne(long id) throws ResourceNotFounException;
    
    public SportDTO findOne(long id) throws ResourceNotFounException;
    
    public Page<SportDTO> findAll(Map<String, String> parameters);
    
    public Page<SportDTO> findByAthlete(long athleteId, Map<String, String> parameters) throws ResourceNotFounException;

}
