package ramos.jefferson.boot.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ramos.jefferson.boot.dto.AthleteDTO;
import ramos.jefferson.boot.exception.BadRequestException;
import ramos.jefferson.boot.exception.ResourceNotFounException;
import ramos.jefferson.boot.service.AthleteService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/athlete", produces = MediaType.APPLICATION_JSON_VALUE)
public class AthleteController {
    
    @Autowired
    private AthleteService athleteService;
    
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody AthleteDTO athleteDTO) throws BadRequestException, ResourceNotFounException {
        return new ResponseEntity<>(athleteService.create(athleteDTO), HttpStatus.CREATED);
    }
    
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody AthleteDTO athleteDTO, @PathVariable("id") String stringId)
            throws BadRequestException, ResourceNotFounException {
        try {
            long id = Long.parseLong(stringId);
            return ResponseEntity.ok(athleteService.update(athleteDTO, id));
        } catch (NumberFormatException ex) {
            throw new BadRequestException("Invalid parameter: must be a number");
        }
    }
    
    @GetMapping("")
    public ResponseEntity<?> get(@RequestParam(required = false) Map<String, String> parameters) throws ResourceNotFounException {
        return ResponseEntity.ok(athleteService.findAll(parameters));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") String stringId) throws BadRequestException, ResourceNotFounException {
        try {
            long id = Long.parseLong(stringId);
            return ResponseEntity.ok(athleteService.findOne(id));
        } catch (NumberFormatException ex) {
            throw new BadRequestException("Invalid parameter: must be a number");
        }
    }
    
    @GetMapping("/athlete/{id}")
    public ResponseEntity<?> get(@RequestParam(required = false) Map<String, String> parameters, @PathVariable("id") String stringId)
            throws ResourceNotFounException, BadRequestException {
        try {
            long id = Long.parseLong(stringId);
            return ResponseEntity.ok(athleteService.findBySport(id, parameters));
        } catch (NumberFormatException ex) {
            throw new BadRequestException("Invalid parameter: must be a number");
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String stringId) throws BadRequestException, ResourceNotFounException {
        try {
            long id = Long.parseLong(stringId);
            athleteService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NumberFormatException ex) {
            throw new BadRequestException("Invalid parameter: must be a number");
        }
    }

}
