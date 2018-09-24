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
import ramos.jefferson.boot.dto.SportDTO;
import ramos.jefferson.boot.exception.BadRequestException;
import ramos.jefferson.boot.exception.ResourceNotFounException;
import ramos.jefferson.boot.service.SportService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/sport", produces = MediaType.APPLICATION_JSON_VALUE)
public class SportController {
    
    @Autowired
    private SportService sportService;
    
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody SportDTO sportDTO) throws BadRequestException {
        return new ResponseEntity<>(sportService.create(sportDTO), HttpStatus.CREATED);
    }
    
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody SportDTO sportDTO, @PathVariable("id") String stringId)
            throws BadRequestException, ResourceNotFounException {
        try {
            long id = Long.parseLong(stringId);
            return ResponseEntity.ok(sportService.update(sportDTO, id));
        } catch (NumberFormatException ex) {
            throw new BadRequestException("Invalid parameter: must be a number");
        }
    }
    
    @GetMapping("")
    public ResponseEntity<?> get(@RequestParam(required = false) Map<String, String> parameters) {
        return ResponseEntity.ok(sportService.findAll(parameters));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") String stringId) throws BadRequestException, ResourceNotFounException {
        try {
            long id = Long.parseLong(stringId);
            return ResponseEntity.ok(sportService.findOne(id));
        } catch (NumberFormatException ex) {
            throw new BadRequestException("Invalid parameter: must be a number");
        }
    }
    
    @GetMapping("/athlete/{id}")
    public ResponseEntity<?> get(@RequestParam(required = false) Map<String, String> parameters, @PathVariable("id") String stringId)
            throws ResourceNotFounException, BadRequestException {
        try {
            long id = Long.parseLong(stringId);
            return ResponseEntity.ok(sportService.findByAthlete(id, parameters));
        } catch (NumberFormatException ex) {
            throw new BadRequestException("Invalid parameter: must be a number");
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String stringId) throws BadRequestException, ResourceNotFounException {
        try {
            long id = Long.parseLong(stringId);
            sportService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NumberFormatException ex) {
            throw new BadRequestException("Invalid parameter: must be a number");
        }
    }

}
