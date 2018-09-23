package ramos.jefferson.boot.service;

import ramos.jefferson.boot.entity.Athlete;
import ramos.jefferson.boot.entity.AthleteSport;
import ramos.jefferson.boot.entity.Sport;

interface AthleteSportService {
    
    public AthleteSport save(Athlete athlete, Sport sport);
    
    public void delete(Athlete athlete, Sport sport);
    
    public void delete(Athlete athlete);
    
    public void delete(Sport sport);

}
