package ramos.jefferson.boot.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ramos.jefferson.boot.dto.AthleteDTO;
import ramos.jefferson.boot.entity.Athlete;
import ramos.jefferson.boot.entity.Sport;

public interface AthleteRepository extends JpaRepository<Athlete, Long> {
    
    @Query(QUERY_STRING)
    public List<AthleteDTO> findAllBySport(@Param("sport") Sport sport);
    
    @Query(QUERY_STRING)
    public List<AthleteDTO> findAllBySport(@Param("sport") Sport sport, Pageable pageable);
    
    static final String QUERY_STRING = "SELECT new ramos.jefferson.boot.dto.AthleteDTO(a.id,a.firstName,a.lastName,a.nickname) "
            + "FROM Athlete a "
            + "LEFT JOIN AthleteSport AS at ON at.athleteSportPK.athlete = a "
            + "WHERE at.athleteSportPK.sport = :sport";

}
