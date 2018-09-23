package ramos.jefferson.boot.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ramos.jefferson.boot.dto.SportDTO;
import ramos.jefferson.boot.entity.Athlete;
import ramos.jefferson.boot.entity.Sport;

public interface SportRepository extends JpaRepository<Sport, Long> {
    
    @Query(QUERY_STRING)
    public List<SportDTO> findByAthlete(@Param("athlete") Athlete athlete);
    
    @Query(QUERY_STRING)
    public List<SportDTO> findByAthlete(@Param("athlete") Athlete athlete, Pageable pageable);
    
    static final String QUERY_STRING = "SELECT new ramos.jefferson.boot.dto.SportDTO(s.id,s.name) FROM Sport s "
            + "LEFT JOIN AthleteSport AS a ON a.athleteSportPK.sport = s "
            + "WHERE a.athleteSportPK.athlete = :athlete";

}
